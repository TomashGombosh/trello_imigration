package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static InputStream loadProperties() {
        try {
            return new FileInputStream(System.getProperty("user.dir") + "/users.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getPropeties(String properties){

        Properties prop = new Properties();
        // load a properties file
        try {
            prop.load(loadProperties());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(properties);
    }
    public static String FILE_LOCATION = getPropeties("FILE_LOCATION");
    public static String TRELLO_API_URL = "https://api.trello.com/1/";
    public static String API_KEY = getPropeties("API_KEY");
    public static String TOKEN = getPropeties("TOKEN");
    public static String MEMBER = getPropeties("MEMBER");

}
