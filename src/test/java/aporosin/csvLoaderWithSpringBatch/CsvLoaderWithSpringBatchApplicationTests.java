package aporosin.csvLoaderWithSpringBatch;

import aporosin.csvLoaderWithSpringBatch.insurance.*;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
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

    @Test
    public void insurenceExportToCsvTest() {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("csv/insurence_in.csv").getFile());
        Class<InsuranceIn> insuranceFromCSVClass = InsuranceIn.class;

        CsvLoader<InsuranceIn> loader = new CsvLoader<>();
        List<InsuranceIn> insurences = loader.loadCsv(file, insuranceFromCSVClass);

        CsvWriter<InsuranceIn> writer = new CsvWriter<>();
        writer.writeCsvFile(Paths.get("result123.csv"), insurences, InsuranceIn.class, true, Lists.newArrayList("in_id", "measurement_model_type", "calculation_frequency"));
    }

    @Test
    public void cashFlowInLoadTest() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("csv/cash_flow_in.csv").getFile());
        Class<CashFlowIn> cashFlowInClass = CashFlowIn.class;

        CsvLoader<CashFlowIn> loader = new CsvLoader<>();
        List<CashFlowIn> cashFlowIns = loader.loadCsv(file, cashFlowInClass);

        Assert.assertEquals(1797, cashFlowIns.size());

        CashFlowIn result = cashFlowIns.get(1796);

        // Verify fields are set
        Assert.assertEquals(1514, result.getInId());
        Assert.assertEquals("HK", result.getEntityCode());
        Assert.assertEquals("15/01/01", result.getMeasurementDate().format(DateTimeFormatter.ofPattern("yy/MM/dd")));
        Assert.assertEquals("PORTFOLIO_A", result.getPortfolioCode());



        Assert.assertEquals("15/01/01", result.getMeasurementDate().format(DateTimeFormatter.ofPattern("yy/MM/dd")));
    }
}
