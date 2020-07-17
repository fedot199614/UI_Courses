package md.automation.tests.glue;

import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import md.automation.core.poms.CursMD.CursMD;
import md.automation.core.utils.SeleniumUtils;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class CurrencyFormatTest {
    private static CursMD cursMD;

    @Before
    public void driverInit() {
        SeleniumUtils.getInstance().getDriver().manage().deleteAllCookies();
        cursMD = CursMD.getInstance(SeleniumUtils.getInstance().getDriver());
    }

    @Then("^Open currency list$")
    public void openCurrencyList() {
        cursMD.openCurrencyList();
    }

    @Then("^Check if currency list was translated correctly based on language '(.+)'$")
    public void checkIfCurrencyListWasTranslatedCorrectly(String lang) throws JAXBException, IOException {
        cursMD.isCurrencyListCorrect(lang);
    }
}
