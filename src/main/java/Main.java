import services.ExcelHelper;
import services.JsonHelper;

import java.util.HashMap;

public class Main {

    public static void main( String[] args )
    {
        HashMap<String, Object> map =  new ExcelHelper().readFromExcel("C:\\Trelloimigration\\src\\main\\resources\\Template.xlsx");
        new JsonHelper().createJSON(map, "C:\\Trelloimigration\\src\\main\\resources\\Template.json");
    }

}
