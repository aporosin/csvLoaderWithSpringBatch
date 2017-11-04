package aporosin.csvLoaderWithSpringBatch;

import aporosin.csvLoaderWithSpringBatch.insurance.InsuranceIn;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import java.io.File;

//https://stackoverflow.com/questions/35068404/how-to-use-beanwrapperfieldsetmapper-to-map-a-subset-of-fields
//http://www.codingpedia.org/ama/spring-batch-tutorial-with-spring-boot-and-java-configuration/

@Configuration
public class CsvLoaderConfiguration {


    @Value("${insuranceCsvFile}")
    private File file;

    @Bean
    ItemReader<InsuranceIn> csvFileItemReader() {

        FlatFileItemReader<InsuranceIn> insuranceReader = new FlatFileItemReader<>();
        //insuranceReader.setResource(new ClassPathResource("csv/insurence_in.csv"));
        insuranceReader.setResource(new FileSystemResource(file));

        //skip header
        insuranceReader.setLinesToSkip(1);

        // set up line mapper
        LineMapper<InsuranceIn> mapper = createInsuranceInLineMapper();
        insuranceReader.setLineMapper(mapper);

        return insuranceReader;
    }

    private LineMapper<InsuranceIn> createInsuranceInLineMapper() {

        DefaultLineMapper<InsuranceIn> mapper = new DefaultLineMapper<>();
        LineTokenizer tokenizer = createInsurenceLineTokenizer();
        mapper.setLineTokenizer(tokenizer);

        FieldSetMapper<InsuranceIn> fieldsMapper = createInsuranceFieldMapper();
        mapper.setFieldSetMapper(fieldsMapper);

        return mapper;
    }

    private FieldSetMapper<InsuranceIn> createInsuranceFieldMapper() {

        BeanWrapperFieldSetMapper<InsuranceIn> insuranceMapper = new BeanWrapperFieldSetMapper<>();
        insuranceMapper.setTargetType(InsuranceIn.class);
        return insuranceMapper;
    }

    private LineTokenizer createInsurenceLineTokenizer() {

        DelimitedLineTokenizer insuranceLineTokenizer = new DelimitedLineTokenizer();
        //insuranceLineTokenizer.setDelimiter(";");
        //insuranceLineTokenizer.setNames(new String[]{"name", "emailAddress", "purchasedPackage"});
        return insuranceLineTokenizer;
    }

}
