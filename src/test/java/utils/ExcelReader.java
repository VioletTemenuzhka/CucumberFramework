package utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class ExcelReader {

    //we're creating Workbook from apache.poi and making it static
    // in order to use it anywhere without creating an object every time
    static Workbook book;
    //we're creating static Sheet, because in each excel file we can have multiple sheets
    static Sheet sheet;

    //this method will open the excel file
    public static void openExcel(String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath); //filePath we're getting it from our constants
            book = new XSSFWorkbook (fis); //
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //this method will identify the sheet in the file we're going to take the data from
    public static void getSheet(String sheetName){
        sheet = book.getSheet(sheetName);
    }

    //this method will return the count or the rows in th excel file (including the header row)
    public static int getRowCount(){

        return sheet.getPhysicalNumberOfRows(); //get back the rows that are having data in them
    }

    //this method will return the count of the columns
    public static int getColsCount(int rowIndex){
        //get the physical number of cells for each row to find out how many columns we have
        return  sheet.getRow(rowIndex).getPhysicalNumberOfCells();
    }

    //this method will get the data from each cell in String format
    public static String getCellData(int rowIndex, int colIndex){
        return sheet.getRow(rowIndex).getCell(colIndex).toString();
    }

    //this method will take the data from the excel file in the List of Maps
    public static List<Map<String,String>> excelIntoMap(String filePath, String sheetName){
        //filePath we're passing from excel IntoMap method
        openExcel(filePath); //returning the excel file
        //sheetName we're passing from excel IntoMap method
        getSheet(sheetName); //returning the sheet

        //providing the list of maps and defining the ArrayList, because we're provide the data one by one in it
        List<Map<String,String>> ListData = new ArrayList<>();

        //outer loop for the rows (from index 1, because 0 is the header where are the keys)
        for(int row=1; row<getRowCount(); row++){
            //creating a map for every row; we use linked hashmap, because it takes care of the insertion order
            Map<String,String> map = new LinkedHashMap<>();
            //inner loop for the cols; every execution is returning one map
            for(int col=0; col<getColsCount(row); col++){
                map.put(getCellData(0, col),getCellData(row,col));
                //getCellData(0, col) --> keys /rowIndex is hardcoded, because all keys are in row with index 0
                //getCellData(row,col) --> values
            }
            //we're adding all maps in a list in order to not override and lose the previous data
            ListData.add(map);
        }
        //returning all the data from the file
        return ListData;
    }
}
