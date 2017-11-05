package aporosin.csvLoaderWithSpringBatch;

import aporosin.csvLoaderWithSpringBatch.insurance.InsuranceFromCSV;
import aporosin.csvLoaderWithSpringBatch.insurance.InsuranceIn;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// https://www.petrikainulainen.net/programming/spring-framework/spring-batch-tutorial-reading-information-from-a-file/
// http://beanio.org

@SpringBootApplication
public class CsvLoaderWithSpringBatchApplication {

	@Autowired
	private static FlatFileItemReader<InsuranceFromCSV> csvFileItemReader;

	public static void main(String[] args) {

//		ClassLoader classLoader = new CsvLoaderWithSpringBatchApplication().getClass().getClassLoader();
//		File file = new File(classLoader.getResource("csv/insurence_in.csv").getFile());
//
//		try (Scanner scanner = new Scanner(file)) {
//
//			while (scanner.hasNextLine()) {
//				String line = scanner.nextLine();
//				System.out.println(line);
//			}
//
//			scanner.close();
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		if(csvFileItemReader == null)
			csvFileItemReader = new CsvLoaderConfiguration().csvFileItemReader();

		try {
			List<InsuranceFromCSV> insurences = new ArrayList<>();
			csvFileItemReader.open(new ExecutionContext());
			InsuranceFromCSV read = csvFileItemReader.read();

			while(read != null)
			{
				insurences.add(read);
				read = csvFileItemReader.read();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			csvFileItemReader.close();
		}

		//SpringApplication.run(CsvLoaderWithSpringBatchApplication.class, args);
	}
}
