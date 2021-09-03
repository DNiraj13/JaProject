package pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ContractsPage extends BasePage {
    private WebDriver driver;
    By Akzeptieren= By.xpath("//button[text()='Akzeptieren']");
    private static Logger log=Logger.getLogger(ContractsPage.class.getName());

    public ContractsPage(WebDriver driver)
    {
        this.driver=driver;
    }

    public void acceptCookies(){
        List<WebElement> elms=driver.findElements(Akzeptieren);
        if(elms.size()>0)
        clickElement(Akzeptieren);
        log.info("User clicked on Akzeptieren");
    }

    public void selectMenu(String menuPassed){
        By menuOption= By.xpath("//a[text()='"+menuPassed+"']");
        clickElement(menuOption);
        log.info("User clicked on menu:"+menuPassed);
    }

}
