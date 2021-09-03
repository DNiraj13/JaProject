package pageobjects;


import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import webdriversupport.DriverManager;

public class BasePage {
    public static  WebDriver driver;
    public static final Logger logger = Logger.getLogger(BasePage.class);
    private static final long TIMEOUT = 10;

    public BasePage(){
        this.driver= DriverManager.getInstance().getDriver();
        DOMConfigurator.configure("log4j.xml");
    }

    public WebElement getElement(By locator){
        WebElement element = null;
        int attempts = 0;
        while(attempts<3)
        {
            try {
                element=driver.findElement(locator);
                break;
            }catch (StaleElementReferenceException e){

            }
            attempts++;
        }
        return element;
    }

    public void waitForElementVisibility(By locator){
        try{
            WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }catch (Exception e){
        }
    }

    public void waitForVisibilityOfElement(WebElement element){
        int attempts=0;
        while (attempts<4){
            try{
                new WebDriverWait(driver,TIMEOUT).until(ExpectedConditions.visibilityOf(element));
                break;
            }catch (Exception e){
            }
            attempts++;
        }
    }

    public void clickElement(By locator){
        waitForElementVisibility(locator);
        waitForVisibilityOfElement(getElement(locator));
        getElement(locator).click();
    }



}
