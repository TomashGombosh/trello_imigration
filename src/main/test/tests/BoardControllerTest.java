package tests;

import controllers.trello.BoardController;
import dataModels.BoardDataModel;
import dataModels.MemberDataModel;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BoardControllerTest {

    @Test
    @Parameters(value = {"boardName", "memberName", "boardId"})
    public void getBoardId(String boardName, String memberName, String boardId){
        MemberDataModel memberDataModel = new MemberDataModel();
        memberDataModel.setId(memberName);
        BoardDataModel boardDataModel = new BoardDataModel();
        boardDataModel.setName(boardName);
        String actualBoardId = new BoardController().getBoardId(boardDataModel);
        Assert.assertEquals(actualBoardId, boardId, "Board id should be the same");
    }
}
