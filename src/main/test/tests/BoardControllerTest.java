package tests;

import controllers.trello.BoardController;
import dataModels.BoardDataModel;
import dataModels.LabelDataModel;
import dataModels.MemberDataModel;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;

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

    @Test
    @Parameters(value = {"boardName","boardId", "desc", "label1", "label2", "label3"})
    public void getBoardInfoById(String boardName, String boardId, String desc, String label1,String label2,String label3){
        BoardDataModel expectedBoardDataModel = new BoardDataModel();
        BoardDataModel actualBoardDataModel = new BoardDataModel();
        LabelDataModel labelDataModel1 = new LabelDataModel();
        LabelDataModel labelDataModel2 = new LabelDataModel();
        LabelDataModel labelDataModel3 = new LabelDataModel();
        HashMap<Integer, LabelDataModel> labelDataModelHashMap = new HashMap<>();

        labelDataModel1.setId(label1);
        labelDataModel2.setId(label2);
        labelDataModel3.setId(label3);
        labelDataModelHashMap.put(1, labelDataModel1);
        labelDataModelHashMap.put(2, labelDataModel2);
        labelDataModelHashMap.put(3, labelDataModel3);
        expectedBoardDataModel.setName(boardName);
        expectedBoardDataModel.setDescription(desc);
        expectedBoardDataModel.setLabels(labelDataModelHashMap);

        actualBoardDataModel = new BoardController().getBoardInfo(boardId);

        Assert.assertEquals(expectedBoardDataModel, actualBoardDataModel, "Data in the models should be the same");
    }
}
