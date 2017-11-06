package aporosin.csvLoaderWithSpringBatch;

import aporosin.csvLoaderWithSpringBatch.insurance.CsvLoader;
import aporosin.csvLoaderWithSpringBatch.insurance.InsuranceFromCSV;
import aporosin.csvLoaderWithSpringBatch.insurance.InsuranceIn;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.List;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class CsvLoaderWithSpringBatchApplicationTests {


	@Test
	public void insurenaceInSimpliefiedLoadTest() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("csv/insurence_in_simply.csv").getFile());
        Class<InsuranceFromCSV> insuranceFromCSVClass = InsuranceFromCSV.class;

        CsvLoader<InsuranceFromCSV> loader = new CsvLoader<>();
        List<InsuranceFromCSV> insurences = loader.loadCsv(file, insuranceFromCSVClass);

        Assert.assertEquals(3, insurences.size());
    }

    @Test
    public void insuranceInLoadTest() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("csv/insurence_in.csv").getFile());
        Class<InsuranceIn> insuranceFromCSVClass = InsuranceIn.class;

        CsvLoader<InsuranceIn> loader = new CsvLoader<>();
        List<InsuranceIn> insurences = loader.loadCsv(file, insuranceFromCSVClass);

        Assert.assertEquals(1, insurences.size());
    }

}
