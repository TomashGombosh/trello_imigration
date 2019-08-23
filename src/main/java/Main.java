import org.apache.log4j.Logger;
import services.CardsImpl;
import services.helper.ExcelHelper;

public class Main {
    public static void main( String[] args )
    {
        Logger log = Logger.getLogger("Main");
        new ExcelHelper().getColumn("C:\\trello_imigration\\src\\main\\resources\\Template.xlsx", 3);
        boolean result =  new CardsImpl().validate("Test");
        log.info(result);
    }

}
