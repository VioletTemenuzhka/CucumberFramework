package utils;

import org.json.JSONObject;

public class APIPayloadConstants {

    public static String createEmployeePayload(){
        String crateEmployee = "{\n" +
                "  \"emp_firstname\": \"Tiana\",\n" +
                "  \"emp_lastname\": \"BR\",\n" +
                "  \"emp_middle_name\": \"Hill\",\n" +
                "  \"emp_gender\": \"F\",\n" +
                "  \"emp_birthday\": \"2000-08-16\",\n" +
                "  \"emp_status\": \"Probation\",\n" +
                "  \"emp_job_title\": \"QA\"\n" +
                "}";

        return crateEmployee;
    }

    //passing the body from json object
    public static String createEmployeePayloadViaJson(){
        JSONObject obj = new JSONObject();
        obj.put("emp_firstname", "Tiana");
        obj.put("emp_lastname", "BR");
        obj.put("emp_middle_name", "Hill");
        obj.put("emp_gender", "F");
        obj.put("emp_birthday", "2000-08-16");
        obj.put("emp_status", "Probation");
        obj.put("emp_job_title", "QA");

        return obj.toString();
    }
    // passing the body via polymorphism
    public static String createEmployeeDynamic(String firstName, String lastName, String middleName,
                                               String gender, String birthday, String empStatus, String jobTitle){
        JSONObject obj = new JSONObject();
        obj.put("emp_firstname", firstName);
        obj.put("emp_lastname", lastName);
        obj.put("emp_middle_name", middleName);
        obj.put("emp_gender", gender);
        obj.put("emp_birthday", birthday);
        obj.put("emp_status", empStatus);
        obj.put("emp_job_title", jobTitle);

        return obj.toString();
    }
}
