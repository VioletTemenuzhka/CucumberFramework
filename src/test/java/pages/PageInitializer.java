package pages;

import utils.CommonMethods;

public class PageInitializer {
    public static LoginPage login;
    public static EmployeeSearchPage employeeSearchPage;
    public static  AddEmployeePage addEmployeePage;
    public static DashboardPage dash;


    //this is the method to initialize the page objects
    public static void initializePageObjects(){
        //in this class we're creating all the objects of the classes
        login = new LoginPage();
        employeeSearchPage = new EmployeeSearchPage();
        addEmployeePage = new AddEmployeePage();
        dash = new DashboardPage();
    }
}
