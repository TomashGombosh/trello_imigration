import controllers.CardController;
import org.apache.log4j.Logger;
import services.CardsImpl;
import services.helper.ExcelHelper;

public class Main {
    public static void main( String[] args )
    {
        Logger log = Logger.getLogger("Main");
        new ExcelHelper().getColumn("C:\\trello_imigration\\src\\main\\resources\\Template.xlsx", 3);
        boolean result =  new CardsImpl().validate("Test");
        log.info(new CardController().createCard("Adriana", "Adriana", "5d4be8e467afe951afd5b1f7", "5d4be8dfaf988c41f2303944", "5d3df461183dd23bd237c63f"));
        log.info(result);
    }

}
