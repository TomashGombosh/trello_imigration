package tests;

import controllers.trello.BoardController;
import dataModels.BoardDataModel;
import dataModels.LabelDataModel;
import dataModels.MemberDataModel;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;

public class BoardControllerTest {
    BoardDataModel defaultBoardDataModel = new BoardDataModel();
    LabelDataModel defaultLabelDataModel = new LabelDataModel();

    @BeforeSuite
    private void addDataToModel() {
        defaultBoardDataModel.setName("Test");
    }

    @Test
    @Parameters(value = {"boardName", "memberName", "boardId"})
    public void getBoardId(String boardName, String memberName, String boardId) {
        MemberDataModel memberDataModel = new MemberDataModel();
        memberDataModel.setId(memberName);
        BoardDataModel boardDataModel = new BoardDataModel();
        boardDataModel.setName(boardName);
        String actualBoardId = new BoardController().getBoardId(boardDataModel);
        Assert.assertEquals(actualBoardId, boardId, "Board id should be the same");
    }

    @Test
    @Parameters(value = {"boardName", "boardId", "desc", "label1", "label1name", "label2", "label2name", "label3", "label3name"})
    public void getBoardInfoById(String boardName, String boardId, String desc, String label1, String label1name, String label2, String label2name, String label3, String label3name) {
        BoardDataModel expectedBoardDataModel = new BoardDataModel();
        BoardDataModel actualBoardDataModel;
        LabelDataModel labelDataModel1 = new LabelDataModel();
        LabelDataModel labelDataModel2 = new LabelDataModel();
        LabelDataModel labelDataModel3 = new LabelDataModel();
        HashMap<Integer, LabelDataModel> labelDataModelHashMap = new HashMap<>();

        labelDataModel1.setId(label1);
        labelDataModel1.setName(label1name);
        labelDataModel2.setId(label2);
        labelDataModel2.setName(label2name);
        labelDataModel3.setId(label3);
        labelDataModel3.setName(label3name);
        labelDataModelHashMap.put(0, labelDataModel1);
        labelDataModelHashMap.put(1, labelDataModel2);
        labelDataModelHashMap.put(2, labelDataModel3);
        expectedBoardDataModel.setName(boardName);
        expectedBoardDataModel.setDescription(desc);
        expectedBoardDataModel.setLabels(labelDataModelHashMap);

        actualBoardDataModel = new BoardController().getBoardInfo(boardId);

        Assert.assertTrue(expectedBoardDataModel.isDataTheSame(actualBoardDataModel), "Data in the models should be the same");
    }

    @Test
    @Parameters(value = {"boardName", "boardId", "desc", "label1", "label1name", "label2", "label2name", "label3", "label3name"})
    public void getBoardInfoByName(String boardName, String boardId, String desc, String label1, String label1name, String label2, String label2name, String label3, String label3name) {
        BoardDataModel expectedBoardDataModel = new BoardDataModel();
        BoardDataModel actualBoardDataModel;
        LabelDataModel labelDataModel1 = new LabelDataModel();
        LabelDataModel labelDataModel2 = new LabelDataModel();
        LabelDataModel labelDataModel3 = new LabelDataModel();
        HashMap<Integer, LabelDataModel> labelDataModelHashMap = new HashMap<>();

        labelDataModel1.setId(label1);
        labelDataModel1.setName(label1name);
        labelDataModel2.setId(label2);
        labelDataModel2.setName(label2name);
        labelDataModel3.setId(label3);
        labelDataModel3.setName(label3name);
        labelDataModelHashMap.put(0, labelDataModel1);
        labelDataModelHashMap.put(1, labelDataModel2);
        labelDataModelHashMap.put(2, labelDataModel3);
        expectedBoardDataModel.setId(boardId);
        expectedBoardDataModel.setDescription(desc);
        expectedBoardDataModel.setLabels(labelDataModelHashMap);

        actualBoardDataModel = new BoardController().getBoardInfo(boardName);

        Assert.assertTrue(expectedBoardDataModel.isDataTheSame(actualBoardDataModel), "Data in the models should be the same");
    }

    @Test
    public void createBoard() {
        BoardDataModel createdBoardData = new BoardController().createBoard(defaultBoardDataModel);
        BoardDataModel actualBoardData = new BoardController().getBoardInfo(defaultBoardDataModel.getName());
        defaultBoardDataModel.setId(createdBoardData.getId());
        Assert.assertTrue(actualBoardData.isDataTheSame(createdBoardData), "Data in the models should be the same");
    }

    @Test
    public void deleteBoard() {
        String result = new BoardController().deleteBoard(defaultBoardDataModel);
        Assert.assertTrue(result.equals(""));
    }
}
