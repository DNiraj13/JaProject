package stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import pageobjects.*;
import util.PropertyReaders;
import webdriversupport.DriverManager;

import java.awt.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ApplicationStepDefs {
    PropertyReaders propertyReaders;
    public WebDriver driver;
    ContractsPage contractsPage;
    RequestsPage requestsPage;
    RequestSelectionCategoryPage requestSelectionCategoryPage;
    PageObjectManager pageObjectManager;


    @Given("user is in contract page")
    public void user_is_in_contract_page() throws IOException, InterruptedException {
        driver=DriverManager.getInstance().getDriver();
        pageObjectManager=new PageObjectManager(driver);
        propertyReaders=new PropertyReaders();
        driver.get(propertyReaders.readProperty("URL"));
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(10000);
        //BasePage basePage=new BasePage();
        contractsPage= pageObjectManager.getContractsPage();
        contractsPage.acceptCookies();

    }

    @Then("user select the {string} from contract page")
    public void userSelectTheFromContractPage(String menu) {
        contractsPage=pageObjectManager.getContractsPage();
        contractsPage.selectMenu(menu);
    }

    @Then("user select the {string} from request Page")
    public void userSelectTheFromRequestPage(String insuranceType) {
            requestsPage=pageObjectManager.getRequestsPage();
            requestsPage.selectInsuranceTypes(insuranceType);
    }

    @And("user select the {string}, {string}, {string} and {string}")
    public void userSelectTheAnd(String familienstatus, String anstellung, String rente, String selbstbeteiligung) throws InterruptedException, AWTException, IOException {
            requestSelectionCategoryPage= pageObjectManager.getRequestSelectionCategoryPage();
            requestSelectionCategoryPage.userRequestSelection(familienstatus,rente);

    }
}
