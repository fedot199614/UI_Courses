package md.automation;

import md.automation.core.properties.GeneralProperties;
import md.automation.core.properties.LinksProperties;
import md.automation.core.utils.FileManager;
import md.automation.core.utils.HttpClient;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
//import helpers.SeleniumUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/main/java/md/automation/core/features")
public class TestRunner {

    public static void initStep() throws Exception {
        System.out.println("Prestaging was started..");
        //data setup & props parsing
        LinksProperties.readProperty();
        GeneralProperties.readProperty();
        //get val curs from bnm and write to data file
        HttpClient.initHttpTimeOutConnection(30000,30000);
        FileManager.writeToFile(HttpClient.get(LinksProperties.bnm_ro_official_exchange_link+GeneralProperties.date),
                GeneralProperties.val_curs_data_file_location_ro);
        FileManager.writeToFile(HttpClient.get(LinksProperties.bnm_ru_official_exchange_link+GeneralProperties.date),
                GeneralProperties.val_curs_data_file_location_ru);
        FileManager.writeToFile(HttpClient.get(LinksProperties.bnm_en_official_exchange_link+GeneralProperties.date),
                GeneralProperties.val_curs_data_file_location_en);
        System.out.println("Prestaging was ended..");
    }

    @BeforeClass
    public static void methodBeforeClassIsLoaded() throws Exception {
        initStep();
    }

    @AfterClass
    public static void methodAfterExecutingIsDone() {
        //Optional.ofNullable(CursSD.driver).ifPresent(WebDriver::quit);
    }
}
