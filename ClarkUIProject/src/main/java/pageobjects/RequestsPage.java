package pageobjects;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RequestsPage extends BasePage {

    WebDriver driver;
    By Loslegen=By.xpath("//span[text()='Loslegen']");
    By Privathaftpflicht_Category   = By.xpath("//p[text()='Pri\u00ADvat\u00ADhaft\u00ADpflicht']");
    private static Logger log=Logger.getLogger(ContractsPage.class.getName());

    public RequestsPage(WebDriver driver){
        this.driver=driver;
    }

    public void selectInsuranceTypes(String insuranceType){
        By insuranceTypeSelect= By.xpath("//span[text()='"+insuranceType+"']");
        log.info("User click on:"+insuranceType);
        clickElement(insuranceTypeSelect);
        waitForElementVisibility(Privathaftpflicht_Category);
        Assert.assertEquals(getElement(Privathaftpflicht_Category).getText(),"Pri\u00ADvat\u00ADhaft\u00ADpflicht");
        clickElement(Loslegen);
        log.info("user click on Loslegen");
    }

}
