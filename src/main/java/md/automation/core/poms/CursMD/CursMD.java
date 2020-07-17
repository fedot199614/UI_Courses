package md.automation.core.poms.CursMD;


import md.automation.core.dto.xml.ValCurs;
import md.automation.core.poms.AbstractPOM;
import md.automation.core.poms.CursMD.pages.ExchangeRates;
import md.automation.core.poms.CursMD.pages.RatesEvolution;
import md.automation.core.poms.Page;
import md.automation.core.properties.LinksProperties;
import md.automation.core.properties.XPathProperties;
import md.automation.core.utils.FileManager;
import md.automation.core.utils.SeleniumUtils;

import md.automation.core.utils.XMLParser;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CursMD extends AbstractPOM {
    private static CursMD CURS_MD_INSTANCE = null;
    private static String pageUrl;
    private String currentPageLanguage;
     //main page elements
     @FindBy(xpath = XPathProperties.language_btn)
     public WebElement languageBtn;
     @FindBy(xpath = XPathProperties.language_btn_POPUP)
     public List<WebElement> languagesList;
     @FindBy(xpath = XPathProperties.menu)
     public List<WebElement> mainMenu;
     @FindBy(xpath = XPathProperties.currencyViBtn)
     public WebElement currencyViBtn;
     @FindBy(xpath = XPathProperties.currencyList)
     public List<WebElement> currencyList;
     //other pages
     private List<Page> allPages;
     private ExchangeRates exchangeRatesPage;
     private RatesEvolution ratesEvolutionPage;

     public static CursMD getInstance(WebDriver driver){
        if(CURS_MD_INSTANCE == null){
            CURS_MD_INSTANCE = new CursMD(driver);
            return CURS_MD_INSTANCE;
        }
        return CURS_MD_INSTANCE;
    }

     private CursMD(WebDriver driver) {
        super(driver);
         exchangeRatesPage = new ExchangeRates(driver);
         ratesEvolutionPage = new RatesEvolution(driver);
         pageUrl = LinksProperties.home_page;
    }

    public ExchangeRates getExchangeRatesPage() {
        return exchangeRatesPage;
    }
    public RatesEvolution getRatesEvolutionPage() {
        return ratesEvolutionPage;
    }

    public void openHomePage(){
        getWebDriver().get(pageUrl);
        currentPageLanguage = languageBtn.getText();
    }

     public void isMenuTranslatedCorrectly(List<String> expectedMenu){
        System.out.println("Checking menu language...");
        System.out.print("Actual menu -> "); mainMenu.forEach(v-> System.out.print(v.getText()+" "));
        System.out.println();
        System.out.print("Expected menu -> "); expectedMenu.forEach(v-> System.out.print(v+" "));
        System.out.println();
        for(int i =0; i< expectedMenu.size();i++ ){
            Assertions.assertThat(mainMenu.get(i+1).getText()).isEqualTo(expectedMenu.get(i));
        }
    }

     public void isCorrectTab(String tab){
        System.out.println("Checking web page Tab...");
        System.out.println("Actual Tab -> "+getWebDriver().getTitle());
        System.out.println("Expected Tab -> "+tab);
        Assertions.assertThat(getWebDriver().getTitle()).isEqualTo(tab);
    }

     public void openCurrencyList(){
        currencyViBtn.click();
    }

     public void changeLanguage(String language){
        if(language.equals(getCurrentPageLanguage())){
            System.out.println("This " +language+ " language already chosen...");
        }else{
            languageBtn.click();
            WebElement lanEl = languagesList.stream().filter(v->v.getText().equals(language)).findFirst().get();
            String languagePOPUP = lanEl.getText();
            System.out.println(getCurrentPageLanguage()+" was changed on "+languagePOPUP);
            setCurrentPageLanguage(languagePOPUP);
            //lanEl.click();
            SeleniumUtils.clickJS(getWebDriver(),lanEl);

        }
    }




    public String getCurrentPageLanguage(){
        if(this.currentPageLanguage != null){
            return currentPageLanguage;
        }else{
            throw new NullPointerException("Page language was not initialized!");
        }
    }

    public void setCurrentPageLanguage(String lng){
        this.currentPageLanguage = lng;
    }

    public void isCurrencyListCorrect(String lang) throws JAXBException, IOException {
        ValCurs valCurs = (ValCurs)  XMLParser.parseXML(
                FileManager.readFromFileBasedOnLang(lang),
                ValCurs.class);

        List<String> expectedResults = valCurs.getValute().stream()
                .collect(ArrayList::new, (list, item)->list.add(item.getCharCode()+" - "+item.getName()), ArrayList::addAll);

        //currencyList.forEach(v-> System.out.println(v.getText()+" ORIGINAL ELEMENT | "));

        System.out.println("Checking currency language...");
        System.out.print("Actual -> "); SeleniumUtils.parseTextForWebElementToCollection(currencyList,true).forEach(v-> System.out.print(v+" | "));
        System.out.println();
        
        Collections.sort(expectedResults);
        System.out.print("Expected -> "); expectedResults.forEach(v-> System.out.print(v+" | "));
        System.out.println();


        Assertions.assertThat(SeleniumUtils.parseTextForWebElementToCollection(currencyList,true)).isEqualTo(expectedResults);
    }


    public void openExchangeRatesPage(){
        exchangeRatesPage.openThisPage();
    }

    public void openRateEvolutionPage(){
        ratesEvolutionPage.openThisPage();
    }

    public List<Page> getAllPages(){
         List<Page> allPages = new ArrayList<>();
         allPages.add(exchangeRatesPage);
         allPages.add(ratesEvolutionPage);
         return allPages;
    }




}
