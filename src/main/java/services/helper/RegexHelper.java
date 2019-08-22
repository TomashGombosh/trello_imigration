package services.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexHelper {
    WaitHelper wait = new WaitHelper();
    private String regexNumbers = "[-+]?[0-9]*\\.?[0-9]+";

    public List<Integer> getIntegersFromString(String string){
        List<Integer> listOfIntegers = new ArrayList<Integer>();
        Pattern pattern = Pattern.compile(regexNumbers);
        Matcher m = pattern.matcher(string);
        while (m.find()) {
            listOfIntegers.add(Integer.valueOf(m.group()));
        }
        wait.waitATime();
        return listOfIntegers;
    }

    public List<Double> getDoubleFromString(String string){
        List<Double> listOfIntegers = new ArrayList<Double>();
        Pattern pattern = Pattern.compile(regexNumbers);
        Matcher m = pattern.matcher(string);
        while (m.find()) {
            listOfIntegers.add(Double.valueOf(m.group()));
        }
        wait.waitATime();
        return listOfIntegers;
    }

    public List<String> getListStringsFromString(String string){
        List<String> listOfIntegers = new ArrayList<String>();
        Pattern pattern = Pattern.compile(regexNumbers);
        Matcher m = pattern.matcher(string);
        while (m.find()) {
            listOfIntegers.add(m.group());
        }
        wait.waitATime();
        return listOfIntegers;
    }
}
