package md.automation.tests.glue;

import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import md.automation.core.poms.CursMD.CursMD;
import md.automation.core.utils.SeleniumUtils;
import org.assertj.core.api.Assertions;

import java.util.List;

public class TabsLanguageTest {
    private static CursMD cursMD;

    @Before
    public void driverInit() {
        SeleniumUtils.getInstance().getDriver().manage().deleteAllCookies();
        cursMD = CursMD.getInstance(SeleniumUtils.getInstance().getDriver());
    }

    @Then("^We should click on '(.*)' option and check if '(.*)' and '(.*)' was translated correctly$")
    public void weShouldClickOnMenuOptionAndCheckIfPageTabHeaderAndTabPageWasTranslatedCorrectly(List<String> menuExpected,List<String> pageTabHeader,List<String> pageTitle) {
        cursMD.getAllPages().forEach(pages->{
            System.out.println("Clicked on this tab "+pages.getMenuTitle());
            pages.openThisPage();
            Assertions.assertThat(pageTitle).contains(pages.getTitle().trim());
            Assertions.assertThat(pageTabHeader).contains(pages.getPageHeader().trim());
            //System.out.println(menuExpected.contains(pages.getMenuTitle().trim()));
            Assertions.assertThat(menuExpected).contains(pages.getMenuTitle().trim());
            System.out.println("Page tab header -> "+pages.getPageHeader()+" | Menu name -> "+pages.getMenuTitle()+" and PAGE TITLE  -> "+pages.getTitle()+" was successfully translated based on this language "+cursMD.getCurrentPageLanguage());
        });


    }
}
