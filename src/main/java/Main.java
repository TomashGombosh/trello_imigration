import services.ExcelHelper;
import services.JsonHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main( String[] args )
    {
        new ExcelHelper().getColumn("C:\\trello_imigration\\src\\main\\resources\\Template.xlsx", 3);
    }

}
