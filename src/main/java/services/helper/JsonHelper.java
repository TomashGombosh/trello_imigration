package services.helper;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.HashMap;

public class JsonHelper {

    private static void createJson(String stringToParse, String file){
        JSONParser parser = new JSONParser();
        try {
            JSONObject json = (JSONObject) parser.parse(stringToParse);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

//    public void createJSON(HashMap<String, String> map, String file){
//        createJson(map, file);
//    }

    public boolean isDataPresentInJson(String object, String value){
        return object.contains(value);
    }
}
