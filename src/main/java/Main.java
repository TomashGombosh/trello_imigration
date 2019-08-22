import services.CardsImpl;
import services.helper.ExcelHelper;

public class Main {

    public static void main( String[] args )
    {
        new ExcelHelper().getColumn("C:\\trello_imigration\\src\\main\\resources\\Template.xlsx", 3);
        new CardsImpl().validate("A.F.J Solution Desing");
    }

}
