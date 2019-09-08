import controllers.BoardController;
import controllers.CardController;
import controllers.ListController;
import dataModels.BoardDataModel;
import dataModels.CardDataModel;
import dataModels.ListDataModel;
import dataModels.MemberDataModel;
import org.apache.log4j.Logger;
import services.CardsImpl;
import services.helper.ExcelHelper;

public class Main {
    public static void main( String[] args )
    {
        Logger log = Logger.getLogger("Main");

        MemberDataModel memberDataModel = new MemberDataModel();
        memberDataModel.setId("tomashgombosh1");
        BoardDataModel boardDataModel = new BoardDataModel();
        boardDataModel.setName("Trello_migration");
        ListDataModel listDataModel = new ListDataModel();
        listDataModel.setName("Java_test");
        listDataModel.setIdBoard(new BoardController().getBoardId(memberDataModel, boardDataModel));
        new ListController().createList(listDataModel);
        boolean result =  new CardsImpl().validate("Test");
        log.info(result);
    }

}
