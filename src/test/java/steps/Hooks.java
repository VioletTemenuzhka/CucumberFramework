package steps;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.CommonMethods;

public class Hooks extends CommonMethods {

    @Before //this is the pre condition
    public void start(){
        openBrowserAndLaunchApplication();
    }

    @After //this is the post condition

    public void end(Scenario scenario){
        // Scenario class from Cucumber holds the complete information of our execution
        byte[] pic;
        //pic is the object which is going to be an array of byte
        if (scenario.isFailed()){
           pic = takeScreenshot("failed/"+scenario.getName());
        }else{
            pic = takeScreenshot("passed/"+scenario.getName());
        }
        //it will attach the pic in the report
        // scenario class holds the entire info about the execution
        scenario.attach(pic, "image/png",scenario.getName());
        tearDown();
    }
}
