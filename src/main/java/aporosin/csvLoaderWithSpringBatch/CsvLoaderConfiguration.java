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
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

//https://stackoverflow.com/questions/35068404/how-to-use-beanwrapperfieldsetmapper-to-map-a-subset-of-fields
//http://www.codingpedia.org/ama/spring-batch-tutorial-with-spring-boot-and-java-configuration/

@Configuration
public class CsvLoaderConfiguration {


    @Value("${insuranceCsvFile}")
    private File file;

    @Bean
    public FlatFileItemReader<InsuranceFromCSV> csvFileItemReader() {

        FlatFileItemReader<InsuranceFromCSV> insuranceReader = new FlatFileItemReader<>();
        //insuranceReader.setResource(new ClassPathResource("csv/insurence_in.csv"));

        ClassLoader classLoader = new CsvLoaderWithSpringBatchApplication().getClass().getClassLoader();
        File file = new File(classLoader.getResource("csv/insurence_in.csv").getFile());
        insuranceReader.setResource(new FileSystemResource(file));
        //insuranceReader.setResource(new FileSystemResource(file));

        //skip header
        insuranceReader.setLinesToSkip(1);

        // set up line mapper
        LineMapper<InsuranceFromCSV> mapper = createInsuranceInLineMapper();
        insuranceReader.setLineMapper(mapper);

        return insuranceReader;
    }

    private LineMapper<InsuranceFromCSV> createInsuranceInLineMapper() {

        DefaultLineMapper<InsuranceFromCSV> mapper = new DefaultLineMapper<>();
        LineTokenizer tokenizer = createInsurenceLineTokenizer();
        mapper.setLineTokenizer(tokenizer);

        FieldSetMapper<InsuranceFromCSV> fieldsMapper = createInsuranceFieldMapper();
        mapper.setFieldSetMapper(fieldsMapper);

        return mapper;
    }

    private FieldSetMapper<InsuranceFromCSV> createInsuranceFieldMapper() {

        BeanWrapperFieldSetMapper<InsuranceFromCSV> insuranceMapper = new BeanWrapperFieldSetMapper<>();
        insuranceMapper.setTargetType(InsuranceFromCSV.class);
        Map<Class<LocalDateTime>, CustomDateEditor> editors = new HashMap<>();
        editors.put(LocalDateTime.class, //new CustomLocalDateTimeEditor(true));
                new CustomDateEditor(new SimpleDateFormat("yy-MM-dd"), true));
        insuranceMapper.setCustomEditors(editors);
        return insuranceMapper;
    }

    private LineTokenizer createInsurenceLineTokenizer() {

        DelimitedLineTokenizer insuranceLineTokenizer = new DelimitedLineTokenizer();
        //insuranceLineTokenizer.setDelimiter(";");
        insuranceLineTokenizer.setNames(new String[]{
                "inId",
                "entityCode",
                "measurementDate1",
                "portfolioCode", "groupCode", "policyCode", "coverageCode",
                "issueDate", "contractStartDate", "calculationFrequency"});
        return insuranceLineTokenizer;
    }

}
