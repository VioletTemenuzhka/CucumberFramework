package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.PageInitializer;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static pages.PageInitializer.initializePageObjects;

public class CommonMethods extends PageInitializer {

    public static WebDriver driver;

    public void openBrowserAndLaunchApplication() {
        ConfigReader.readProperties(Constants.CONFIGURATION_FILEPATH);
        switch (ConfigReader.getPropertyValue("browser")) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("Invalid browser name");
        }
        driver.get(ConfigReader.getPropertyValue("url"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
        initializePageObjects();
    }
    //for filepath we need to make it common for all machines
    //D:\Nush SDET Course\Eclipse\CucumberFrameworkBatch12\src\test\resources\config\config.properties --
    //D:\Nush SDET Course\Eclipse\-->machine path
    //CucumberFrameworkBatch12\src\test\resources\config\config.properties-->project path

    //this method will first clear and then pass text
    public static void sendText(WebElement element, String textToSend) {
        element.clear();
        element.sendKeys(textToSend);
    }

    //this method
    public static WebDriverWait getWait() {
        WebDriverWait wait = new WebDriverWait(driver, Constants.EXPLICIT_WAIT);
        return wait;
    }

    //this method will wait for element to be clickable
    public static void waitForClickability(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    //this method will wait for clikcability and then click on the webelement
    public static void click(WebElement element) {
        waitForClickability(element);
        element.click();
    }

    public static JavascriptExecutor getJSExecutor() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js;
    }

    public static void jsClick(WebElement element) {
        getJSExecutor().executeScript("arguments[0].click();", element);
    }

    //this method will verify if the WebElement is displayed
    public static void elementIsDisplayed(WebElement element) {
        System.out.println(element.isDisplayed());
    }

    //this method will get the text from an WebElement
    public static void getText(WebElement element) {
        System.out.println(element.getText());
    }

    //this method will take screenshot
    // in terms to take a screenshot cucumber always provides the array of byte
    public static byte[] takeScreenshot(String fileName) {
        //we're passing fileName because we need to save the screenshot somewhere
        //TakesScreenshot interface is being taken from selenium
        TakesScreenshot ts = (TakesScreenshot) driver;
        //we're using output type as byte/ picBytes is the object we declare for array of byte.
        byte[] picBytes = ts.getScreenshotAs(OutputType.BYTES);
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);

        //we use FileUtils to copy the file from sourceFile to the new file
        try {
            //in order to not overriding the screenshots, we're the date and time so every screenshot has i=unique name
            FileUtils.copyFile(sourceFile, new File(Constants.SCREENSHOT_FILEPATH + fileName + " " + getTimeStamp("yyyy-MM-dd-HH-mm-ss")+ ".png"));
            //provide new file location in the framework
            //then call the method getTimeStamp() and provide the stamp for the date and time as follows:
            // "yyyy-MM-dd-HH-mm-ss" --> this is a predefined format of defining date and time
            //and provide the fileType (we're using .png here)
        } catch (IOException e) {
            e.printStackTrace();
        }
        return picBytes;
    }

    //this time will get the date and it will format it
    public static String getTimeStamp(String pattern) {
        Date date = new Date();
        //to format the date according to our choice we want to implement it in this function
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    //this method will quit the browser
    public static void tearDown() {
        driver.quit();
    }
}
