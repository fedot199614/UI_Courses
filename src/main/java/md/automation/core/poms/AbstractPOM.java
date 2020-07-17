package md.automation.core.poms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPOM {
    private WebDriver webDriver;

     public AbstractPOM(WebDriver driver){
         this.webDriver = driver;
         PageFactory.initElements(driver, this);
     }

    public WebDriver getWebDriver() {
        return webDriver;
    }
}
