package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;
import utils.ConfigReader;

public class LoginSteps extends CommonMethods {

    @Then("admin user is successfully logged in")
    public void admin_user_is_successfully_logged_in() {
      //  System.out.println("test passed");
        Assert.assertTrue(dash.welcomeMessage.isDisplayed());
     //   tearDown(); #the step is commented because we don't need it anymore; we're using Hooks class
    }

    @When("user enters valid ess credentials")
    public void user_enters_valid_ess_credentials() {
        WebElement usernameField = driver.findElement(By.id("txtUsername"));
        sendText(usernameField,"tts12345");

        WebElement passwordField = driver.findElement(By.id("txtPassword"));
        sendText(passwordField,"Hum@nhrm");

    }

    @Then("ess user is successfully logged in")
    public void ess_user_is_successfully_logged_in() {
        Assert.assertFalse(dash.welcomeMessage.isDisplayed());
      //living it for validation
      //  tearDown(); #the step is commented because we don't need it anymore; we're using Hooks class
    }

    @When("user enters invalid credentials")
    public void user_enters_invalid_credentials() {
        WebElement usernameField = driver.findElement(By.id("txtUsername"));
        sendText(usernameField,"tts12345");

        WebElement passwordField = driver.findElement(By.id("txtPassword"));
        sendText(passwordField,"Hum");
    }

    @Then("user see error message on the screen")
    public void user_see_error_message_on_the_screen() {
        getText(login.errorMsg);
        elementIsDisplayed(login.errorMsg);
      //  tearDown(); #the step is commented because we don't need it anymore; we're using Hooks class

        //homework - verify error message for this

       // Assert.assertTrue(login.errorMsg.isDisplayed());
    }

    //HW Task1
    @When("user enters invalid username and valid password")
    public void user_enters_invalid_username_and_valid_password() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    //HW Task2
    @When("user enters valid username and invalid password")
    public void user_enters_valid_username_and_invalid_password() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    //HW Task3
    @When("user enters blank username and valid password")
    public void user_enters_blank_username_and_valid_password() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    //HW Task4
    @When("user enters valid username and blank password")
    public void user_enters_valid_username_and_blank_password() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }


}
