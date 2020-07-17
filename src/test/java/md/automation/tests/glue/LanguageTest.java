package md.automation.tests.glue;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import md.automation.core.poms.CursMD.CursMD;
import md.automation.core.utils.SeleniumUtils;

import java.util.List;

public class LanguageTest {
    private static CursMD cursMD;

    @Before
    public void driverInit() {
        SeleniumUtils.getInstance().getDriver().manage().deleteAllCookies();
        cursMD = CursMD.getInstance(SeleniumUtils.getInstance().getDriver());
    }

    @Given("^Main page is opened$")
    public void mainPageIsOpened() {
        cursMD.openHomePage();
    }

    @When("^Page language was changed on '(.+)'$")
    public void pageLanguageWasChangedOnLang(String lang) {
        cursMD.changeLanguage(lang);
    }

    @Then("^Check if page was translated correctly via menu '(.+)'$")
    public void checkIfPageWasTranslatedCorrectlyViaMenu(List<String> menuOptions) {
        cursMD.isMenuTranslatedCorrectly(menuOptions);
    }


    @And("^Page '(.+)' also was changed correctly depending on the language$")
    public void pageHeaderAlsoWasTranslatedCorrectlyDependingOnTheLang(String pageTab) {
        cursMD.isCorrectTab(pageTab);
    }



}
