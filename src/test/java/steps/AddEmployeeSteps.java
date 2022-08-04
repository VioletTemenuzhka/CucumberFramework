package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import pages.EmployeeSearchPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;
import utils.Constants;
import utils.ExcelReader;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddEmployeeSteps extends CommonMethods {

    @When("user clicks on PIM options")
    public void user_clicks_on_pim_options() {
        //older version of writing the code
        //WebElement pimOption = driver.findElement(By.id("menu_pim_viewPimModule"));
        //pimOption.click();

        //new version of writing the code
        click(employeeSearchPage.pimOption);
    }
    @When("user clicks on add employee option")
    public void user_clicks_on_add_employee_option() {
        click(employeeSearchPage.addEmployeeOption);
    }
    @When("user enters firstname middlename and lastname")
    public void user_enters_firstname_middlename_and_lastname() {
        //hard codded data
      sendText(addEmployeePage.firstNameField,"dasir");
      sendText(addEmployeePage.middleNameField,"hamilia");
      sendText(addEmployeePage.lastNameField,"tolom");
    }

    @When("user clicks on save button")
    public void user_clicks_on_save_button() {
        click(addEmployeePage.saveButton);
    }
    @Then("employee added successfully")
    public void employee_added_successfully() {
        System.out.println("Employee added successfully");
    }

    @When("user enters {string} {string} and {string}")
    public void user_enters_and(String firstNameValue, String middleNameValue, String lastNameValue) {
        //no hard codded data
        sendText(addEmployeePage.firstNameField,firstNameValue);
        sendText(addEmployeePage.middleNameField,middleNameValue);
        sendText(addEmployeePage.lastNameField,lastNameValue);
    }

    @When("user provides {string} {string} and {string}")
    public void user_provides_and(String firstName, String middleName, String lastName) {
        sendText(addEmployeePage.firstNameField, firstName);
        sendText(addEmployeePage.middleNameField,middleName);
        sendText(addEmployeePage.lastNameField,lastName);
    }

    @When("user provides multiple employee data and verify they are added")
    public void user_provides_multiple_employee_data_and_verify_they_are_added(DataTable dataTable) throws InterruptedException {
        //io.cucumber.datatable.DataTable --> delete this and provide DataTable from io.cucumber.datatable
        //we're going to use List<Map<K,V>> If we use just map it will override the data every time
        List<Map<String,String>> employeeNames = dataTable.asMaps();
        for (Map<String,String> employee:employeeNames) {
            System.out.println(employee);
           String firstNameValue = employee.get("firstName");
           String middleNameValue = employee.get("middleName");
           String lastNameValue = employee.get("lastName");
            String fullNameNewEmployee = firstNameValue+" "+middleNameValue+" "+lastNameValue;

            sendText(addEmployeePage.firstNameField,firstNameValue);
            sendText(addEmployeePage.middleNameField, middleNameValue);
            sendText(addEmployeePage.lastNameField,lastNameValue);

            click(addEmployeePage.saveButton);
            Thread.sleep(3000);

            //verification of adding employee is HW
            //1st find the element and store it in AddEmployeePage
            //2nd iff condition
            if(addEmployeePage.fullNameOfNewEmployee.getText().equals(fullNameNewEmployee)){
                System.out.println("Employee "+fullNameNewEmployee+" "+"is added successfully");
                click(employeeSearchPage.addEmployeeOption);
            }else{
                System.out.println(fullNameNewEmployee+" doesn't match the entered data");
            }
        }
    }

    @When("user adds multiple employees from excel file using {string} sheet and verify the user added")
    public void user_adds_multiple_employees_from_excel_file_using_sheet_and_verify_the_user_added(String sheetName) throws InterruptedException {
        //String sheetName, because we're providing the name of the sheet we are using; the parameter from the feature file
        List<Map<String, String>> newEmployees = ExcelReader.excelIntoMap(Constants.TESTDATA_FILEPATH, sheetName);

        // List<Map<String, String>> --> excelIntoMap is returning List of Map
        // newEmployees --> the object we created
        // ExcelReader.excelIntoMap(Constants.TESTDATA_FILEPATH, sheetName); --> the method we're calling from ExcelReader class

        //iterate the values from the map one by one
        Iterator<Map<String, String>> itr = newEmployees.iterator();
        //we're using while loop
        //it checks whether the next element exists or not
        while (itr.hasNext()) {
            //it returns the key and value for employees
            Map<String, String> mapNewEmp = itr.next();
            //it returns the value against the key we provide
            System.out.println(mapNewEmp.get("FirstName"));
            System.out.println(mapNewEmp.get("MiddleName"));
            System.out.println(mapNewEmp.get("LastName"));

            //filling all the fields with the data coming from excel file
            sendText(addEmployeePage.firstNameField, mapNewEmp.get("FirstName"));
            sendText(addEmployeePage.middleNameField, mapNewEmp.get("MiddleName"));
            sendText(addEmployeePage.lastNameField, mapNewEmp.get("LastName"));

            //it will fetch the employee id from the attribute
            String empIdValue = addEmployeePage.empIDLocator.getAttribute("value");

            //to upload the photograph
            sendText(addEmployeePage.photograph, mapNewEmp.get("Photograph"));

            //select the checkbox
            if (!addEmployeePage.checkBox.isSelected()) {
                click(addEmployeePage.checkBox);
            }

            sendText(addEmployeePage.createUsername, mapNewEmp.get("Username"));
            sendText(addEmployeePage.createPassword, mapNewEmp.get("Password"));
            sendText(addEmployeePage.confirmPassword, mapNewEmp.get("Password"));
            click(addEmployeePage.saveButton);

            Thread.sleep(3000);
            //to verify the employee is added we will navigate to employee list option and will verify by capturing the id
            click(employeeSearchPage.empListOption);
            sendText(employeeSearchPage.idField, empIdValue);
            click(employeeSearchPage.searchButton);

            //it is returning the data from the row in results
            List<WebElement> rowData = driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr"));
            //the following loop is for asserts; it gets the row text
            for(int i=0; i<rowData.size(); i++){
                String rowText = rowData.get(i).getText();
                System.out.println(rowText);
                //providing the data in the same format
                String expectedData = empIdValue +" "+mapNewEmp.get("FirstName")+" "+
                        mapNewEmp.get("MiddleName")+" "+mapNewEmp.get("LastName");

                // checks if expected and actual data are equal
                Assert.assertEquals(expectedData,rowText);
            }
            // adding employee
            click(employeeSearchPage.addEmployeeOption);


            Thread.sleep(2000); //preferable use implicit wait

        }

    }
}
