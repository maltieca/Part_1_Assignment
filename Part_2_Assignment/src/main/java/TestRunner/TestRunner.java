package TestRunner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


    @RunWith(Cucumber.class)
    @CucumberOptions(plugin = {"pretty"}, features = "src/main/Features", glue = "src/test/java/stepDefinitions")

    public class TestRunner {
    }



