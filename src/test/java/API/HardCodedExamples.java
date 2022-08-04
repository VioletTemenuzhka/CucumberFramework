package API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

// provide here the following tag to sort the methods in alphabetical order and rename the methods to start with letters
// ascending means increasing
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardCodedExamples {

    // we're creating static or instance variables, bc the visibility is throughout the class

    // baseURI = baseURL (you can find it in the swagger document
    // to follow the HTTP protocol, you need to write http://
    // Postman allows you to connect with the baseURL directly; don't have to pass the protocol
    // Assign the value to a String variable
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NTc5OTEzMDksImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY1ODAzNDUwOSwidXNlcklkIjoiMzk5MyJ9.PMD-hATW49rR4sc4AYGrYohvmdkReezf3euQTIwVG2c";
// we just provided 2 variables on a class level

    // initialize the employee_id as a static variable, bc you'll use it
    static String employee_id;

    @Test
    public void aCreateEmployee() {
        // first prepare the request --> we need baseURL (will be taken automatically, since we created the variable above),
        // headers and body(copy-paste from Postman)
        // call RequestSpecification class (which is a part of RestAssured) and assign the value to the request object
        RequestSpecification request = given().header("Content-Type", "application/json").
                header("Authorization", token).body("{\n" +
                        "  \"emp_firstname\": \"Mira\",\n" +
                        "  \"emp_lastname\": \"Sheen\",\n" +
                        "  \"emp_middle_name\": \"Tan\",\n" +
                        "  \"emp_gender\": \"F\",\n" +
                        "  \"emp_birthday\": \"2000-07-16\",\n" +
                        "  \"emp_status\": \"Probation\",\n" +
                        "  \"emp_job_title\": \"QA\"\n" +
                        "}");

        // hit the end point; to make a call, use WHEN keyword to perform action
        // in .post() provide the end point from the swagger document
        // response is the object of the Response class that holds the information of the call
        Response response = request.when().post("/createEmployee.php");

        // to print response
        // pretty keyword follows the BDD functionality
        response.prettyPrint();
        response.then().assertThat().statusCode(201); // relying only on the status code is not a right approach

        // Hamcrest matchers
        response.then().assertThat().body("Message", equalTo("Employee Created")); //equalTo() import static method select Matchers.equalTo from org.hamcrest
        // if we pass value like this (emp_firstname) it will fail, because we're trying to find it not in the right place
        // type the object and then the emp_firstname
        //java.lang.AssertionError: 1 expectation failed.
        //JSON path emp_firstname doesn't match.
        //Expected: Mira
        //  Actual: null
        response.then().assertThat().body("Employee.emp_firstname", equalTo("Mira"));

        // capture the employee ID
        // using jsonPath() to specify the key in the body so that it can return the value against it
        employee_id = response.jsonPath().getString("Employee.employee_id");
        System.out.println(employee_id);
    }

    @Test
    public void bGetCreatedEmployee() {
        // make a request
        RequestSpecification request = given().header("Content-Type", "application/json").
                header("Authorization", token).queryParam("employee_id", employee_id);

        // make a response
        Response response = request.when().get("/getOneEmployee.php");
        response.prettyPrint();

        response.then().assertThat().statusCode(200);

        // capture the employee id from the response of get call body
        String tempId = response.jsonPath().getString("employee.employee_id");

        Assert.assertEquals(tempId, employee_id);
    }

    @Test
    public void cUpdateEmployee() {
        RequestSpecification preparedRequest = given().header("Content-Type", "application/json").
                header("Authorization", token).body("{\n" +
                        "  \"employee_id\": \"" + employee_id + "\",\n" + // "37909A"-->this is hardcoded. provide not hardcoded value
                        "  \"emp_firstname\": \"Miro\",\n" +
                        "  \"emp_lastname\": \"Taylor\",\n" +
                        "  \"emp_middle_name\": \"JC\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"2022-06-22\",\n" +
                        "  \"emp_status\": \"probation\",\n" +
                        "  \"emp_job_title\": \"QA\"\n" +
                        "}");

        Response response = preparedRequest.when().put("/updateEmployee.php");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    // method/function to get the data
    @Test
    public void dGetUpdatedEmployee(){
        RequestSpecification request = given().header("Content-Type", "application/json").
                header("Authorization", token).queryParam("employee_id", employee_id);

        Response response = request.get("/getOneEmployee.php");
        response.then().assertThat().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void eGetAllEmployees(){
        RequestSpecification request = given().header("Content-Type","application/json").
                header("Authorization", token);

        Response response = request.when().get("/getAllEmployees.php");

        // this method returns String
        String allEmployees = response.prettyPrint();

        // this method returns value against the provided key
        response.jsonPath();

        // to call the method, create an object of the JsonPatch class, because this is how you'll be able to use selective data
        JsonPath js = new JsonPath(allEmployees);

        // retrieving the total number of employees
        int count = js.getInt("Employees.size()");
        System.out.println(count);

        // to print only all the employeeIds from all the employees, use for loop
        for (int i=0; i<count; i++){
            String empId = js.getString("Employees["+i+"].employee_id");
            System.out.println(empId);
        }

    }
}
