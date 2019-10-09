package services.helper;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class StringHelper {
    private Logger log = Logger.getLogger(this.getClass().getName());


    public boolean compareStringsWithLog(String originalString, String stringForComparison){
        PropertyConfigurator.configure(System.getProperty("user.dir") + "/src/main/resources/log4j.properties");
        boolean stringsAreEquals = true;

        if (!originalString.equals(stringForComparison)) {
            stringsAreEquals = false;
            log.info("Values \"" + originalString + "\" and \"" + stringForComparison + "\" are not equals");
        }
        return stringsAreEquals;
    }
}
