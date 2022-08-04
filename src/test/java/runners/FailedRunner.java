package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        //features we use to provide the path of all the failed features files
        features = "@target/failed.txt",

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

        //html report will be generated under target folder
        plugin = {"pretty"
        }
        //"html:target/cucumber.html" --> html report which will be maintained in the target folder;
        // the name of the report is cucumber, the extension is .html --> this report we're using for temporary purposes
        // and we're overriding it to save memory
        //"pretty" --> it is adding the steps (description and definition) you're executing in the console
        //"json:target/cucumber.json" --> javaScript object notation; json is a colorfull report; json report in folder target, file cucumber.json

)
public class FailedRunner {
}
