package webdriversupport;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import util.PropertyReaders;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class DriverManager {

    static String browser= null;
    private static DriverManager instance = new DriverManager();

    private DriverManager() {  }

    public static DriverManager getInstance(){
        return  instance;
    }

    ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>(){
        @Override
        protected WebDriver initialValue() {
            try{
                browser=new PropertyReaders().readProperty("BROWSER");
            }catch (IOException e){
                e.printStackTrace();
            }
            switch (browser){
                case "firefox":
                    return getFireFoxDriver();
                case "chrome":
                    return getChromeDriver();
                default:
                    return getChromeDriver();
            }
        }
    };

    public WebDriver getDriver()
    {
        return driver.get();
    }

    private static WebDriver getChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("--disable-notifications");
        WebDriver chromeDriver = new ChromeDriver(chromeOptions);
        chromeDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        chromeDriver.manage().window().maximize();
        return chromeDriver;
    }

    private static WebDriver getFireFoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        WebDriver firefoxDriver = new FirefoxDriver();
        firefoxDriver.manage().window().maximize();
        return firefoxDriver;
    }

    public  void closeDriver(){
        driver.get().close();
        driver.remove();
    }

}
