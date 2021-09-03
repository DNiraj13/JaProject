package stepdefs;



import io.cucumber.java.After;

import io.cucumber.java.Scenario;
import util.CommonFunctions;
import webdriversupport.DriverManager;

public class GeneralStepDefs {
    private static Scenario scenario=null;


    @After
    public void tearDown(Scenario scenario) {
        try{
            this.scenario=scenario;
            new CommonFunctions().takeScreenshot(scenario,DriverManager.getInstance().getDriver());
        }
        finally {
            DriverManager.getInstance().closeDriver();
        }
    }




}
