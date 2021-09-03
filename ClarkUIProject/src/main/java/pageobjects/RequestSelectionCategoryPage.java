package pageobjects;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v87.network.Network;
import org.openqa.selenium.devtools.v87.network.model.Headers;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Optional;

public class RequestSelectionCategoryPage extends BasePage{
    WebDriver driver;
    Robot r;
    By Familienstatus= By.xpath("//span[text()='Single']");
    //By Familienstatus_Selection=By.xpath("//input[@id='Familie mit Kindern']/following::span[1]");
    By in_Rente = By.xpath("//span[text()='in Rente']/following::span[1]");
    By in_RenteSelection= By.xpath("//input[@id='Ja']/following::span[1]");
    By Meine_Top3_Anzeigen= By.xpath("//span[text()='Meine Top 3 anzeigen']");
    By OfferTextValidation= By.xpath("//h1[text()='Dein Angebot ist da!']");
    By Zum_Angebot= By.xpath("//span[text()='Zum Angebot']");
    By PriceInfo_FirstInsurance= By.xpath("//div[1]/div[@class='_price-info_15tkx4']/p[1]");
    By Validation_PriceText= By.xpath("//button[1]/div/div[@class='_feedback_xn0nsm']/p[1]");
    By Request_Validation = By.xpath("//button[1]/div/div[2]/div[text()='Privathaftpflicht']");
    By offers_ClarkIcon= By.xpath("//a[@class='ember-view page-header-name-clark _brand_1rlfr8']");
    private static Logger log=Logger.getLogger(RequestSelectionCategoryPage.class.getName());
    PageObjectManager pageObjectManager=new PageObjectManager(driver);

    public RequestSelectionCategoryPage(WebDriver driver)
    {
        this.driver=driver;
    }

    public void userRequestSelection(String familySelect, String Rente) throws InterruptedException, AWTException, IOException {
        log.info("User select the FamilyOption and RenteOption");
        if(!familySelect.equals("Singel"))
        {   By Familienstatus_Selection= By.xpath("//input[@id='"+familySelect+"']/following::span[1]");
            clickElement(Familienstatus);
            clickElement(Familienstatus_Selection);
        }
        if(!Rente.equals("Nein"))
        {
            clickElement(in_Rente);
            clickElement(in_RenteSelection);
        }
        clickElement(Meine_Top3_Anzeigen);
        waitForElementVisibility(OfferTextValidation);
        Assert.assertEquals("Dein Angebot ist da!",getElement(OfferTextValidation).getText().trim());
        clickElement(Zum_Angebot);
        waitForElementVisibility(PriceInfo_FirstInsurance);
        String price=getElement(PriceInfo_FirstInsurance).getText().trim();
        System.out.println("Price:"+price);
        Thread.sleep(5000);
        authenticateUserUseRobot();
        Thread.sleep(2000);
        List<WebElement> elm= driver.findElements(offers_ClarkIcon);
        if(elm.size()>0)
            clickElement(offers_ClarkIcon);

        waitForElementVisibility(Request_Validation);
        String offerPrc=getElement(Validation_PriceText).getText();
        System.out.println("offerStatement:"+offerPrc);
        Assert.assertTrue(offerPrc.contains(price));
    }

    public  void authenticateUser() throws InterruptedException {
        Thread.sleep(1000);
        String urlAuth=driver.getCurrentUrl();
        System.out.println("URLAUTH:"+urlAuth);
        String username="clark";
        String password="We-L0ve-Insurance";
        DevTools devTools = ((ChromeDriver)driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));
        Map<String, Object> headers=new HashMap<>();
        String basicAuth = "Basic "+ new String(new Base64().encode(String.format("%s:%s",username,password).getBytes()));
        headers.put("Authorization",basicAuth);
        devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));
        driver.get(urlAuth);
    }

    public void authenticateUserUseRobot() throws AWTException, InterruptedException, IOException {
        r=new Robot();
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        String userName=pageObjectManager.getPropertyReadersPage().readProperty("USERNAME");
        Transferable transferable = new StringSelection(userName);
        clipboard.setContents(transferable, null);
        clickUsingRobotControl(r,KeyEvent.VK_V);
        clickUsingRobot(r,KeyEvent.VK_TAB);
        String passWord=pageObjectManager.getPropertyReadersPage().readProperty("PASSWORD");
        Transferable transferable1 = new StringSelection(passWord);
        clipboard.setContents(transferable1, null);
        clickUsingRobotControl(r,KeyEvent.VK_V);
        clickUsingRobot(r,KeyEvent.VK_TAB);
        clickUsingRobot(r,KeyEvent.VK_ENTER);
        Thread.sleep(2000);
        log.info("User enters the basic authentication credentials");
    }

    public void clickUsingRobot(Robot r,int keyCode) throws InterruptedException {
        r.keyPress(keyCode);
        r.keyRelease(keyCode);
        Thread.sleep(100);
    }

    public void clickUsingRobotControl(Robot r,int keyCode) throws InterruptedException {
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(keyCode);
        Thread.sleep(100);
        r.keyRelease(keyCode);
        r.keyRelease(KeyEvent.VK_CONTROL);
        Thread.sleep(50);
    }


}
