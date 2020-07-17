package md.automation.core.utils;

import md.automation.core.properties.GeneralProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static java.util.concurrent.TimeUnit.SECONDS;

public class SeleniumUtils {
    private static SeleniumUtils SELENIUM_UTILS = null;
    private  WebDriver driver;

    private SeleniumUtils(WebDriver driver){
        this.driver = driver;
    }



    public static void clickJS(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }


    public static List<String> parseTextForWebElementToCollection(List<WebElement> webElements,boolean sorted){
        List<String> temp = new ArrayList<>();
        for(WebElement element : webElements){
            temp.add(element.getText());
        }
        //List<String> temp = webElements.stream().collect(ArrayList::new, (list, item) -> list.add(item.getText()), ArrayList::addAll);
        //System.out.println("---------------------------");
        //temp.forEach(v-> System.out.println(v+" AFTER COPY |"));
        if(sorted){
            Collections.sort(temp);
            return temp;
        }else {
            return temp;
        }
    }

    public static List<String> parseTextForWebElementToCollection(List<WebElement> webElements,String attributeName,boolean sorted){
        List<String> temp = webElements.stream().collect(ArrayList::new, (list, item) -> list.add(item.getText()+" "+item.getAttribute(attributeName)), ArrayList::addAll);;
        if(sorted){
            Collections.sort(temp);
            return temp;
        }else {
            return temp;
        }
    }

    public static SeleniumUtils getInstance(){
        if(SELENIUM_UTILS == null){
            System.setProperty("webdriver.chrome.driver", SeleniumUtils.class.getClassLoader().getResource(GeneralProperties.chrome_driver).getPath());
            WebDriver driver = new ChromeDriver();

            driver.manage().timeouts().pageLoadTimeout(30, SECONDS);
            driver.manage().timeouts().implicitlyWait(30, SECONDS);
            driver.manage().window().maximize();

            SELENIUM_UTILS = new SeleniumUtils(driver);
            return SELENIUM_UTILS;
        }

        return SELENIUM_UTILS;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
