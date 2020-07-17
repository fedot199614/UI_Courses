package md.automation.tests.glue;

import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import md.automation.core.poms.CursMD.CursMD;
import md.automation.core.utils.SeleniumUtils;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class OfficialExchangeRateTest {
    private static CursMD cursMD;

    @Before
    public void driverInit() {
        SeleniumUtils.getInstance().getDriver().manage().deleteAllCookies();
        cursMD = CursMD.getInstance(SeleniumUtils.getInstance().getDriver());
    }

    @Then("^Open Exchange rates page$")
    public void openExchangeRatesPage() {
        cursMD.openExchangeRatesPage();
    }

    @Then("^Click on official exchange rate tab$")
    public void clickOnOfficialExchangeRateTab() {
        cursMD.getExchangeRatesPage().openExchangeRateTab();
    }

    @Then("^Check if official exchange rate was translated correctly based on language '(.*)'$")
    public void checkIfOfficialExchangeRateWasTranslatedCorrectlyBasedOnLanguageLang(String lang) throws JAXBException, IOException {
        cursMD.getExchangeRatesPage().isExchangeRateCorrect(lang);
    }
}
