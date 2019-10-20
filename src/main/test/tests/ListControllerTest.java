package tests;

import controllers.trello.ListController;
import dataModels.CardDataModel;
import dataModels.ListDataModel;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;

public class ListControllerTest {
    private ListDataModel defaultListDataModel = new ListDataModel();

    @BeforeTest
    @Parameters(value = {"boardId"})
    private void addDataToModel(String boardId) {
        defaultListDataModel.setName("Test_Unit");
        defaultListDataModel.setIdBoard(boardId);
    }

    @Test(priority = 1)
    @Parameters(value = {"listId", "listName"})
    public void getListId(String listId, String listName) {
        ListDataModel listDataModel = new ListDataModel();
        listDataModel.setName(listName);
        String actualListId = new ListController(defaultListDataModel.getIdBoard()).getListId(listDataModel);
        Assert.assertEquals(listId, actualListId, "List Id Should be the same");
    }

    @Test(priority = 2)
    @Parameters(value = {"listId", "listName", "cardId", "cardName"})
    public void getListInfoById(String listId, String listName, String cardId, String cardName) {
        ListDataModel expectedListDataModel = new ListDataModel();
        ListDataModel actualListDataModel;
        CardDataModel cardDataModel = new CardDataModel();
        HashMap<Integer, CardDataModel> cardInList = new HashMap<>();

        cardDataModel.setId(cardId);
        cardDataModel.setName(cardName);
        cardInList.put(0, cardDataModel);

        expectedListDataModel.setName(listName);
        expectedListDataModel.setCards(cardInList);

        actualListDataModel = new ListController(defaultListDataModel.getIdBoard()).getListInfo(listId);
        Assert.assertTrue(expectedListDataModel.isDataTheSame(actualListDataModel), "The data in the List should be the same");
    }

    @Test(priority = 3)
    @Parameters(value = {"listId", "listName", "cardId", "cardName"})
    public void getListInfoByName(String listId, String listName, String cardId, String cardName) {
        ListDataModel expectedListDataModel = new ListDataModel();
        ListDataModel actualListDataModel;
        CardDataModel cardDataModel = new CardDataModel();
        HashMap<Integer, CardDataModel> cardInList = new HashMap<>();

        cardDataModel.setId(cardId);
        cardDataModel.setName(cardName);
        cardInList.put(0, cardDataModel);

        expectedListDataModel.setId(listId);
        expectedListDataModel.setCards(cardInList);

        actualListDataModel = new ListController(defaultListDataModel.getIdBoard()).getListInfo(listName);
        Assert.assertTrue(expectedListDataModel.isDataTheSame(actualListDataModel), "The data in the List should be the same");
    }

    @Test(priority = 4)
    public void createList() {
        ListDataModel createdList = new ListController(defaultListDataModel.getIdBoard()).createList(defaultListDataModel);
        ListDataModel actualList = new ListController(defaultListDataModel.getIdBoard()).getListInfo(defaultListDataModel.getName());
        defaultListDataModel.setId(actualList.getId());
        Assert.assertTrue(createdList.isDataTheSame(actualList), "List should be the same");
    }

    @Test(priority = 5)
    public void closeList() {
        ListDataModel deleteList = new ListController(defaultListDataModel.getIdBoard()).closeList(defaultListDataModel);
        Assert.assertTrue(deleteList.isClosed(), "List should be closed");
    }

    @Test(priority = 6)
    @Parameters(value = "listName")
    public void createDuplicateList(String listName) {
        ListDataModel alreadyCreatedList = new ListDataModel();
        alreadyCreatedList.setName(listName);
        ListDataModel exception = new ListController(defaultListDataModel.getIdBoard()).createList(alreadyCreatedList);
        Assert.assertTrue(exception.getId().equals("Board Already Created"));
    }

    @Test(priority = 7)
    public void deleteNotExistList() {
        ListDataModel notExistList = new ListDataModel();
        notExistList.setName("XXXXXXX");
        notExistList.setId("XXXXXXXXX");
        ListDataModel exception = new ListController(defaultListDataModel.getIdBoard()).closeList(notExistList);
        Assert.assertTrue(exception.getId().equals("No such list in the board " + defaultListDataModel.getIdBoard()));
    }
}
