package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import java.io.IOException;
import java.util.stream.Stream;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:cucumber",
        glue= "stepdefs",
        tags = ("@Smoke"),
        dryRun = false,
        monochrome = true,
        plugin = { "pretty","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)
public class TestRunner {
        
         private static String[] defaultOptions={
            "--glue", "stepdefs",
            "--tags", "not @wip", 
            "--tags", "not @ignore",
            "--tags", "not @merged",
            "--tags", "not @STB_SQL_DB_CONN",
            "--plugin", "pretty",
            "--plugin", "junit:target/cucumber-report/Cucumber.xml",
    };
    
    public static void main(String[] args) throws IOException{
        Stream<String> cucumberOptions = Stream.concat(Stream.of(defaultOptions),Stream.of(args));
        io.cucumber.core.cli.Main.main(cucumberOptions.toArray(String[]::new));
    }
        
        
}
