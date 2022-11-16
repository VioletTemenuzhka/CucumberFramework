package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.EmployeeSearchPage;
import utils.CommonMethods;
import utils.ConfigReader;
import utils.Constants;

import java.util.concurrent.TimeUnit;

import static utils.CommonMethods.driver;

public class EmployeeSearchSteps extends CommonMethods {
   // Step undefined
   // You can implement this step and 6 other step(s) using the snippet(s) below:
    //And is converted to When, because And is supporting tag

    @Given("user is navigated to HRMS Application")
    public void user_is_navigated_to_hrms_application() {
        openBrowserAndLaunchApplication();
    }

    @When("user enters valid admin credentials")
    public void user_enters_valid_admin_credentials() {
       // WebElement usernameField = driver.findElement(By.id("txtUsername"));
       // usernameField.sendKeys(ConfigReader.getPropertyValue("username"));
       // sendText(usernameField, ConfigReader.getPropertyValue("username"));
        //line 30 we're passing the value by reading the data from the config file in the configReader
        // using the getPropertyValue method that we created
        //ENCAPSULATION example:getUsernameBox() is a public method to get the private element from the login page
        sendText(login.getUsernameBox(), ConfigReader.getPropertyValue("username"));
        //WebElement passwordField = driver.findElement(By.id("txtPassword"));
        //passwordField.sendKeys(ConfigReader.getPropertyValue("password"));
        //sendText(passwordField,ConfigReader.getPropertyValue("password"));
        //driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
        //on line 37 we are passing the value for the IMPLICIT_WAIT instead of hardcodding it
        sendText(login.passwordBox, ConfigReader.getPropertyValue("password"));

    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        //WebElement loginButton = driver.findElement(By.id("btnLogin"));
        //click(loginButton);
        //loginButton.click();
        //driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT,TimeUnit.SECONDS);
        click(login.loginBtn);
    }

    @When("user navigates to employee list page")
    public void user_navigates_to_employee_list_page() {
       //WebElement pimOption = driver.findElement(By.id("menu_pim_viewPimModule"));
       // pimOption.click();
       // click(pimOption);
        click(employeeSearchPage.pimOption);

        //WebElement empListOption = driver.findElement(By.id("menu_pim_viewEmployeeList"));
        //empListOption.click();
        //click(empListOption);
        click(employeeSearchPage.empListOption);
    }

    @When("user enters valid employee id")
    public void user_enters_valid_employee_id() {
       // WebElement searchId = driver.findElement(By.id("empsearch_id"));
       // searchId.sendKeys("8510142");
       // sendText(searchId,"8510142");
        sendText(employeeSearchPage.idField, "8510142");
    }

    @When("clicks on search button")
    public void clicks_on_search_button() {
       // WebElement searchButton = driver.findElement(By.id("searchBtn"));
       // searchButton.click();
       // click(searchButton);
        click(employeeSearchPage.searchButton);
    }

    @Then("user is able to see employee information")
    public void user_is_able_to_see_employee_information() {
        System.out.println("Result displayed");
       // tearDown(); #the step is commented because we don't need it anymore; we're using Hooks class
    }

    @When("user enters valid employee name")
    public void userEntersValidEmployeeName() {
      //  driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT,TimeUnit.SECONDS);
      //  WebElement searchName = driver.findElement(By.xpath("(//*[@type = 'text'])[1]"));
      //  searchName.sendKeys("Zubair");
      //  sendText(searchName,"Zubair");
        sendText(employeeSearchPage.nameField, "Zubair");
        }
    }
