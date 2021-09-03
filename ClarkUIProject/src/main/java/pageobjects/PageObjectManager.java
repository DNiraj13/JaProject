package pageobjects;

import org.openqa.selenium.WebDriver;
import util.PropertyReaders;

public class PageObjectManager {

    public static  WebDriver driver;
    private ContractsPage contractsPage;
    private RequestsPage requestsPage;
    private RequestSelectionCategoryPage requestSelectionCategoryPage;
    private PropertyReaders propertyReaders;


    public PageObjectManager(WebDriver driver){
        this.driver=driver;
        System.out.printf("PageObjectMgrDriver:"+driver);
    }

    public ContractsPage getContractsPage(){
        return (contractsPage == null) ? contractsPage = new ContractsPage(driver) : contractsPage;
    }

    public RequestsPage getRequestsPage(){
        return (requestsPage == null) ? requestsPage = new RequestsPage(driver) : requestsPage;
    }

    public RequestSelectionCategoryPage getRequestSelectionCategoryPage(){
        return (requestSelectionCategoryPage == null) ? requestSelectionCategoryPage = new RequestSelectionCategoryPage(driver) : requestSelectionCategoryPage;
    }

    public PropertyReaders getPropertyReadersPage(){
        return (propertyReaders == null) ? propertyReaders = new PropertyReaders() : propertyReaders;
    }
}
