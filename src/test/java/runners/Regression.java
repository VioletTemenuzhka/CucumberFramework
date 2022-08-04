package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
//features we use to provide the path of all the features files
        features = "src/test/resources/features",
        //execute like this it will give an error,
        // because we didn't provide the path to ste step definitions

        // glue is where we find implementations for gherkin steps
        // we provide the path of package to get all the steps definitions
        glue = "steps",
        // by default the value of dryRun is false which means it is disabled
        // dryRun we use to get the step definitions of undefined steps
        //if we set it to true, it will quickly scan all gherkin steps whether they are implemented or not
        //if we set it to true, it stops actual execution
        //to execute scripts in real time, we should set this value to false
        dryRun = false,

        //monochrome means the console output for cucumber test is having irrelevant information
        //when we set it to true, it simply removes all the irrelevant information
        monochrome = true,

        //we can execute more than one tag using the logical operators (or, and)
        //whatever the scenario you're currently working always provide @test (this means under development)
        tags = "@regression"


)

public class Regression {
    //here we'll create new scenarios
}
