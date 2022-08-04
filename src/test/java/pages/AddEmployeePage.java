package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class AddEmployeePage extends CommonMethods {

    @FindBy (id = "firstName")
    public WebElement firstNameField;

    @FindBy (id = "middleName")
    public WebElement middleNameField;

    @FindBy (id = "lastName")
    public WebElement lastNameField;

    @FindBy (id = "btnSave")
    public WebElement saveButton;

   // WebElement fullName = driver.findElement(By.xpath("//div[@id = 'profile-pic']/child::h1"));
    @FindBy (xpath = "//div[@id = 'profile-pic']/child::h1")
    public WebElement fullNameOfNewEmployee;

    @FindBy (id = "employeeId")
    public WebElement empIDLocator;

    @FindBy (id = "photofile")
    public WebElement photograph;

    @FindBy (id = "chkLogin")
    public WebElement checkBox;

    @FindBy (id = "user_name")
    public WebElement createUsername;

    @FindBy (id = "user_password")
    public WebElement createPassword;

    @FindBy (id = "re_password")
    public WebElement confirmPassword;


    //creating the constructor of the page to initialize the instance variables
    AddEmployeePage(){
        PageFactory.initElements(driver, this);
    }
}
