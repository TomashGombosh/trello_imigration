package services;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonHelper {

    private static void createJson(HashMap<String, String> map, String file){
        JSONObject employeeDetails = new JSONObject(map);
        JSONArray employeeList = new JSONArray();
        employeeList.add(employeeDetails);
        //Write JSON file
        try (FileWriter files = new FileWriter(file)) {

            files.write(employeeList.toJSONString());
            files.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createJSON(HashMap<String, String> map, String file){
        createJson(map, file);
    }
}
