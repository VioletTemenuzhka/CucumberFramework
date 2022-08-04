package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

import java.util.List;

public class DashboardPage extends CommonMethods {

    @FindBy (xpath = "//*[@class='menu']/ul/li")
    public List<WebElement> dashboardTabs; //we're using List because they are multiple tabs

    @FindBy (id = "welcome")
    public WebElement welcomeMessage;

    //create the constructor
    public DashboardPage(){
        PageFactory.initElements(driver,this);
    }


}
