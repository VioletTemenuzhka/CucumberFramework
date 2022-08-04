package utils;

public class Constants {
    public static final String CONFIGURATION_FILEPATH = System.getProperty("user.dir") + "/src/test/resources/config/config.properties";
    //System.getProperty("user.dir") --> the path on the machine; dynamic; our project
//"/scr/test/resources/config/config.properties" --> the path of the project
    public static final int IMPLICIT_WAIT = 10;
    public static final int EXPLICIT_WAIT = 20;
    public static final String TESTDATA_FILEPATH = System.getProperty("user.dir") + "/src/test/resources/testdata/Batch12ExcelFile.xlsx";
    public static final String SCREENSHOT_FILEPATH = System.getProperty("user.dir") +"/Screenshots/";
}
