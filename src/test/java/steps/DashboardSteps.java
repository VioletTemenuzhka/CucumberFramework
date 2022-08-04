package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;

import java.util.ArrayList;
import java.util.List;

public class DashboardSteps extends CommonMethods {

    @Then("user verifies all the dashboard tabs")
    public void user_verifies_all_the_dashboard_tabs(DataTable dataTable) {
        //io.cucumber.datatable.DataTable --> delete this and provide DataTable from io.cucumber.datatable
        //we're going to use List<> because we're having only values

        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.

        //we have to compare the data in order to verify it

        //this data is coming from the feature file
        //we need the data from line 10 in Dashboard.feature to be stored as a List
        //assigning the value, because we already declare it in Dashboard.feature file
        List<String> expectedTabs = dataTable.asList();


        //this data is coming from the application
        //initializing, declaring  the list and then provide the data with the for each loop
        List<String> actualTabs = new ArrayList<>();
        //1st create an object of the dashboardPage in PageInitializer
        //2nd create for each loop to take every element one by one
        for (WebElement ele:dash.dashboardTabs) {
            //dash is the object of DashboardPage
            //dashboardTabs has the list of all elements
            actualTabs.add(ele.getText()); //capture all the text from the elements
        }
        System.out.println(actualTabs);//coming from my execution
        System.out.println(expectedTabs);//coming from my feature file

        //Assert.assertEquals(actualTabs,expectedTabs);

        //if assertion is passed it will not give you any information and will execute our code
        //if assertions is failed it will give you an error with comparison
        Assert.assertTrue(expectedTabs.equals(actualTabs));
    }
}
