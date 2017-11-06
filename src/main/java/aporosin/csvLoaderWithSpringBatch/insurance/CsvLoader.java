package aporosin.csvLoaderWithSpringBatch.insurance;

import aporosin.csvLoaderWithSpringBatch.CsvLoaderWithSpringBatchApplication;
import aporosin.csvLoaderWithSpringBatch.CustomLocalDateTimeEditor;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.core.io.FileSystemResource;

import java.beans.PropertyEditor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.*;

public class CsvLoader<T> implements ICsvLoader<T> {

    private static String DELIMITER = ";";
    private String delimiter;

    public void setHeaderNames(String[] headerNames) {
        this.headerNames = headerNames;
    }

    private String[] headerNames;

    public CsvLoader(){
        delimiter = DELIMITER;
    }

    public CsvLoader(String delimiter){
        this.delimiter = delimiter;
    }

    public CsvLoader(String delimiter, String[] headerNames){
        this.delimiter = delimiter;
        this.headerNames = headerNames;
    }

    @Override
    public List<T> loadCsv(File csvFile, Class<T> targetType) {

        FlatFileItemReader<T> reader = csvFileItemReader(csvFile, targetType);
        List<T> results = new ArrayList<>();

        try {
            reader.open(new ExecutionContext());
            T result = reader.read();

            while(result != null)
            {
                results.add(result);
                result = reader.read();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            reader.close();
            return results;
        }
    }

    public FlatFileItemReader<T> csvFileItemReader(File csvFile, Class<T> targetType) {

        FlatFileItemReader<T> insuranceReader = new FlatFileItemReader<>();
        insuranceReader.setResource(new FileSystemResource(csvFile));

        // read CSV header into names if not defined
        if(headerNames == null) {

            String headerLine = null;

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {

                headerLine = reader.readLine();
                reader.close();
                headerNames = Arrays.stream(headerLine.split(delimiter)).map(s -> s.trim().replaceAll("^\"|\"$", "")).toArray(String[]::new);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //skip header
        insuranceReader.setLinesToSkip(1);

        // set up line mapper
        LineMapper<T> mapper = createInsuranceInLineMapper(headerNames, targetType);
        insuranceReader.setLineMapper(mapper);

        return insuranceReader;
    }

    private LineMapper<T> createInsuranceInLineMapper(String[] names, Class<T> targetType) {

        DefaultLineMapper<T> mapper = new DefaultLineMapper<>();
        LineTokenizer tokenizer = createInsurenceLineTokenizer(names);
        mapper.setLineTokenizer(tokenizer);

        FieldSetMapper<T> fieldsMapper = createInsuranceFieldMapper(targetType);
        mapper.setFieldSetMapper(fieldsMapper);

        return mapper;
    }

    private FieldSetMapper<T> createInsuranceFieldMapper(Class<T> targetType) {

        BeanWrapperFieldSetMapper<T> insuranceMapper = new BeanWrapperFieldSetMapper<>();
        insuranceMapper.setTargetType(targetType);
        Map<Object, PropertyEditor> editors = new HashMap<>();
        editors.put(LocalDateTime.class, new CustomLocalDateTimeEditor(true));
        editors.put(Boolean.class, new CustomBooleanEditor("Y", "N", false));
        editors.put(BigDecimal.class, new CustomNumberEditor(BigDecimal.class, NumberFormat.getInstance(), true));

        insuranceMapper.setCustomEditors(editors);
        return insuranceMapper;
    }

    private LineTokenizer createInsurenceLineTokenizer(String[] names) {

        DelimitedLineTokenizer insuranceLineTokenizer = new DelimitedLineTokenizer();
        insuranceLineTokenizer.setDelimiter(delimiter);
        insuranceLineTokenizer.setNames(names);
        return insuranceLineTokenizer;
    }
}
