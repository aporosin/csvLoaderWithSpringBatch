package aporosin.csvLoaderWithSpringBatch;

import aporosin.csvLoaderWithSpringBatch.insurance.InsuranceFromCSV;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.beans.PropertyEditor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//https://stackoverflow.com/questions/35068404/how-to-use-beanwrapperfieldsetmapper-to-map-a-subset-of-fields
//http://www.codingpedia.org/ama/spring-batch-tutorial-with-spring-boot-and-java-configuration/

@Configuration
public class CsvLoaderConfiguration {

    private static String DELIMITTER = ";";
    @Value("${insuranceCsvFile}")
    private File file;

    @Bean
    public FlatFileItemReader<InsuranceFromCSV> csvFileItemReader() {

        FlatFileItemReader<InsuranceFromCSV> insuranceReader = new FlatFileItemReader<>();
        //insuranceReader.setResource(new ClassPathResource("csv/insurence_in_simply.csv"));

        ClassLoader classLoader = new CsvLoaderWithSpringBatchApplication().getClass().getClassLoader();
        File file = new File(classLoader.getResource("csv/insurence_in_simply.csv").getFile());
        insuranceReader.setResource(new FileSystemResource(file));
        //insuranceReader.setResource(new FileSystemResource(file));

        // read header into names
        String[] names = null;
        String headerLine= null;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            headerLine = reader.readLine();
            reader.close();
            names = Arrays.stream(headerLine.split(DELIMITTER)).map(s -> s.trim().replaceAll("^\"|\"$", "")).toArray(String[]::new);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //skip header
        insuranceReader.setLinesToSkip(1);

        // set up line mapper
        LineMapper<InsuranceFromCSV> mapper = createInsuranceInLineMapper(names);
        insuranceReader.setLineMapper(mapper);

        return insuranceReader;
    }

    private LineMapper<InsuranceFromCSV> createInsuranceInLineMapper(String[] names) {

        DefaultLineMapper<InsuranceFromCSV> mapper = new DefaultLineMapper<>();
        LineTokenizer tokenizer = createInsurenceLineTokenizer(names);
        mapper.setLineTokenizer(tokenizer);

        FieldSetMapper<InsuranceFromCSV> fieldsMapper = createInsuranceFieldMapper();
        mapper.setFieldSetMapper(fieldsMapper);

        return mapper;
    }

    private FieldSetMapper<InsuranceFromCSV> createInsuranceFieldMapper() {

        BeanWrapperFieldSetMapper<InsuranceFromCSV> insuranceMapper = new BeanWrapperFieldSetMapper<>();
        insuranceMapper.setTargetType(InsuranceFromCSV.class);
        Map<Object, PropertyEditor> editors = new HashMap<>();
        editors.put(LocalDateTime.class, new CustomLocalDateTimeEditor(true));
        editors.put(Boolean.class, new CustomBooleanEditor("Y", "N", false));
        editors.put(BigDecimal.class, new CustomNumberEditor(BigDecimal.class, NumberFormat.getInstance(), true));

        insuranceMapper.setCustomEditors(editors);
        return insuranceMapper;
    }

    private LineTokenizer createInsurenceLineTokenizer(String[] names) {

        DelimitedLineTokenizer insuranceLineTokenizer = new DelimitedLineTokenizer();
        insuranceLineTokenizer.setDelimiter(DELIMITTER);
        insuranceLineTokenizer
                  .setNames(names);
//                .setNames(new String[]{
//                "inId",
//                "entityCode",
//                "measurementDate1",
//                "portfolioCode", "groupCode", "policyCode", "coverageCode",
//                "issueDate", "contractStartDate", "calculationFrequency",
//                "initialMeasurementFlag", "discountRateAnnual", "discountCurveCode", "riskAdjustmentRate" });
        return insuranceLineTokenizer;
    }

}
