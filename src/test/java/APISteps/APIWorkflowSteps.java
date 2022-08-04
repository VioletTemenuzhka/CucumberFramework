package APISteps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utils.APIConstants;
import utils.APIPayloadConstants;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class APIWorkflowSteps {

    RequestSpecification request;
    Response response;
    //create an instance variable employee_id, so you can use it for upcoming calls
    public static String employee_id;


    @Given("a request is prepared to create an employee")
    public void a_request_is_prepared_to_create_an_employee() {
      request = given().header(APIConstants.HEADER_CONTENT_TYPE, APIConstants.HEADER_CONTENT_TYPE_VALUE).
              header(APIConstants.HEADER_AUTHORIZATION, GenerateTokenSteps.token).
              body(APIPayloadConstants.createEmployeePayload());
    }

    @When("a POST call is made to create an employee")
    public void a_post_call_is_made_to_create_an_employee() {
        response = request.when().post(APIConstants.CREATE_EMPLOYEE_URI);
        response.prettyPrint();
    }

    @Then("the status code for the created employee is {int}")
    public void the_status_code_for_the_created_employee_is(Integer code) {
        response.then().assertThat().statusCode(code);
    }

    @And("the employee created contains key {string} and value {string}")
    public void theEmployeeCreatedContainsKeyAndValue(String key, String value) {
        response.then().assertThat().body(key, equalTo(value));
    }

    @And("the employee id {string} is stored as a global variable to be used for other calls")
    public void theEmployeeIdIsStoredAsAGlobalVariableToBeUsedForOtherCalls(String empId) {
      employee_id  = response.jsonPath().getString(empId);
    }

    //------------------------------------------------------------------------------------------
    // Second call for getting the employee after creating it

    @Given("a request is prepared to get the employee")
    public void a_request_is_prepared_to_get_the_employee() {
        request = given().header(APIConstants.HEADER_CONTENT_TYPE, APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION, GenerateTokenSteps.token).
                queryParam("employee_id", employee_id);
    }

    @When("a GET call is made to retrieve the created employee")
    public void a_get_call_is_made_to_retrieve_the_created_employee() {
        response = request.when().get(APIConstants.GET_ONE_EMPLOYEE_URI);
        response.prettyPrint();
    }

    @Then("the status code for this employee is {int}")
    public void the_status_code_for_this_employee_is(Integer statusCode) {
       response.then().assertThat().statusCode(statusCode);
    }

    @Then("the employee we are getting having ID {string} must match with the globally stored employee id")
    public void the_employee_we_are_getting_having_id_must_match_with_the_globally_stored_employee_id(String empId) {
       String tempId = response.jsonPath().getString(empId);
        Assert.assertEquals(tempId,employee_id);
    }

    @Then("the retrieved data at {string} object matches the data used to create the employee having employee id {string}")
    public void the_retrieved_data_at_object_matches_the_data_used_to_create_the_employee_having_employee_id(String empObject, String responseEmpId, DataTable dataTable) {
       // responseEmpId --> this we get from the get call after retrieving the report from the dataBase

        // create a map
        //this is the data coming from our feature file
        List<Map<String, String>> expectedData = dataTable.asMaps(String.class, String.class);

        //to get the data from the body and store it in a map
        Map<String,String> actualData = response.body().jsonPath().get(empObject); //using only get() bc we don't want any specific value,
                                                            // but the whole object which will return key=value pairs

        //get the information one by one using for each loop
        for (Map<String,String> singlePairOfData:expectedData) {
            //it will return the set of keys from the map
            Set<String> keys = singlePairOfData.keySet();
            //get on key at a time
            for(String key : keys){
                //expected value is coming from feature file
                String expectedValue = singlePairOfData.get(key);
                //actual value is coming from response
                String actualValue = actualData.get(key);
                Assert.assertEquals(expectedValue,actualValue);
            }
        }
    }


    @Given("a request is prepared to create an employee via json payload")
    public void a_request_is_prepared_to_create_an_employee_via_json_payload() {
        request = given().header(APIConstants.HEADER_CONTENT_TYPE, APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION, GenerateTokenSteps.token).
                body(APIPayloadConstants.createEmployeePayloadViaJson());
    }

    @Given("a request is prepared to create an employee via dynamic payload {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void a_request_is_prepared_to_create_an_employee_via_dynamic_payload
         (String firstName, String lastName, String middleName, String gender, String birthday, String empStatus, String jobTitle) {
        request = given().header(APIConstants.HEADER_CONTENT_TYPE, APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION, GenerateTokenSteps.token).
                body(APIPayloadConstants.createEmployeeDynamic(firstName,lastName,middleName,gender,birthday,empStatus,jobTitle));
    }
}
