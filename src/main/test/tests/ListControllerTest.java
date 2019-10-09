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
    private void addDataToModel() {
        defaultListDataModel.setName("Test_Unit");
    }
    @Test(priority = 1)
    @Parameters(value = {"listId", "listName","boardId"})
    public void getListId(String listId, String listName, String boardId){
        ListDataModel listDataModel = new ListDataModel();
        listDataModel.setName(listName);
        String actualListId = new ListController(boardId).getListId(listDataModel);
        Assert.assertEquals(listId, actualListId, "List Id Should be the same");
    }

    @Test(priority = 2)
    @Parameters(value = {"listId", "listName", "cardId", "cardName", "boardId"})
    public void getListInfoById(String listId, String listName, String cardId, String cardName, String boardId){
        ListDataModel expectedListDataModel = new ListDataModel();
        ListDataModel actualListDataModel;
        CardDataModel cardDataModel = new CardDataModel();
        HashMap<Integer, CardDataModel> cardInList = new HashMap<>();

        cardDataModel.setId(cardId);
        cardDataModel.setTitle(cardName);
        cardInList.put(0, cardDataModel);

        expectedListDataModel.setName(listName);
        expectedListDataModel.setCards(cardInList);

        actualListDataModel = new ListController(boardId).getListInfo(listId);
        Assert.assertTrue(expectedListDataModel.isDataTheSame(actualListDataModel), "The data in the List should be the same");
    }

    @Test(priority = 3)
    @Parameters(value = {"listId", "listName", "cardId", "cardName", "boardId"})
    public void getListInfoByName(String listId, String listName, String cardId, String cardName, String boardId){
        ListDataModel expectedListDataModel = new ListDataModel();
        ListDataModel actualListDataModel;
        CardDataModel cardDataModel = new CardDataModel();
        HashMap<Integer, CardDataModel> cardInList = new HashMap<>();

        cardDataModel.setId(cardId);
        cardDataModel.setTitle(cardName);
        cardInList.put(0, cardDataModel);

        expectedListDataModel.setId(listId);
        expectedListDataModel.setCards(cardInList);

        actualListDataModel = new ListController(boardId).getListInfo(listName);
        Assert.assertTrue(expectedListDataModel.isDataTheSame(actualListDataModel), "The data in the List should be the same");
    }

    @Test(priority = 4)
    @Parameters(value = {"boardId"})
    public void createList(String boardId){
        ListDataModel createdList = new ListController(boardId).createList(defaultListDataModel);
        ListDataModel actualList = new ListController(boardId).getListInfo(defaultListDataModel.getName());
        defaultListDataModel.setId(actualList.getId());
        Assert.assertTrue(createdList.isDataTheSame(actualList), "List should be the same");
    }

    @Test(priority = 5)
    @Parameters(value = {"boardId"})
    public void closeList(String boardId){
        ListDataModel deleteList = new ListController(boardId).closeList(defaultListDataModel);
        Assert.assertTrue(deleteList.isClosed(), "List should be closed");
    }

    @Test(priority = 6)
    @Parameters(value = {"listName", "boardId"})
    public void createDuplicateList(String listName, String boardId){
        ListDataModel alreadyCreatedList = new ListDataModel();
        alreadyCreatedList.setName(listName);
        ListDataModel exception =  new ListController(boardId).createList(alreadyCreatedList);
        Assert.assertTrue(exception.getId().equals("Board Already Created"));
    }

    @Test(priority = 7)
    @Parameters(value = {"boardId"})
    public void deleteNotExistList(String boardId){
        ListDataModel notExistList = new ListDataModel();
        notExistList.setName("XXXXXXX");
        notExistList.setId("XXXXXXXXX");
        ListDataModel exception =  new ListController(boardId).closeList(notExistList);
        Assert.assertTrue(exception.getId().equals("No such list in the board " + boardId));
    }
}
