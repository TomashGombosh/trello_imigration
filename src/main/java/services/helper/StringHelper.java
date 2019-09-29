package services.helper;

import org.apache.log4j.Logger;

public class StringHelper {
    private Logger log = Logger.getLogger(this.getClass().getName());

    public boolean compareStringsWithLog(String originalString, String stringForComparison){
        boolean stringsAreEquals = true;

        if (!originalString.equals(stringForComparison)) {
            stringsAreEquals = false;
            log.info("Values \"" + originalString + "\" and \"" + stringForComparison + "\" are not equals");
        }
        return stringsAreEquals;
    }
}
