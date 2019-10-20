package tests;

import controllers.trello.BoardController;
import controllers.trello.CardController;
import dataModels.CardDataModel;
import dataModels.LabelDataModel;
import dataModels.ListDataModel;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;

public class CardControllerTest {
    private CardDataModel defaultCardDataModel = new CardDataModel();

    @BeforeTest
    @Parameters(value = {"listId"})
    private void addDataToModel(String listId) {
        defaultCardDataModel.setListId(listId);
        defaultCardDataModel.setName("UNIT_TEST");
    }

    @Test(priority = 1)
    @Parameters(value = {"cardName", "cardId"})
    public void getCardId(String cardName, String cardId) {
        ListDataModel listDataModel = new ListDataModel(defaultCardDataModel.getListId());
        CardDataModel cardDataModel = new CardDataModel();
        cardDataModel.setName(cardName);
        String actualCardId = new CardController(listDataModel.getId()).getCardId(cardDataModel);
        Assert.assertEquals(actualCardId, cardId, "Card Id should be the same");
    }

    @Test(priority = 2)
    @Parameters(value = {"cardName", "cardId", "labelId", "labelName", "labelColor"})
    public void getCardInfoById(String cardName, String cardId, String labelId, String labelName, String labelColor){
        CardDataModel expectedListDataModel = new CardDataModel();
        CardDataModel actualCardDataModel;
        LabelDataModel labelDataModel = new LabelDataModel();
        HashMap<Integer, LabelDataModel> labels = new HashMap<>();

        labelDataModel.setId(labelId);
        labelDataModel.setName(labelName);
        labelDataModel.setColor(labelColor);
        labels.put(0, labelDataModel);

        expectedListDataModel.setName(cardName);
        expectedListDataModel.setLabels(labels);

        actualCardDataModel = new CardController(defaultCardDataModel.getListId()).getCardInfo(cardId);
        Assert.assertTrue(expectedListDataModel.isDataTheSame(actualCardDataModel), "The data in the List should be the same");
    }

    @Test(priority = 3)
    @Parameters(value = {"cardName", "cardId", "labelId", "labelName", "labelColor"})
    public void getCardInfoByName(String cardName, String cardId, String labelId, String labelName, String labelColor){
        CardDataModel expectedListDataModel = new CardDataModel();
        CardDataModel actualCardDataModel;
        LabelDataModel labelDataModel = new LabelDataModel();
        HashMap<Integer, LabelDataModel> labels = new HashMap<>();

        labelDataModel.setId(labelId);
        labelDataModel.setName(labelName);
        labelDataModel.setColor(labelColor);
        labels.put(0, labelDataModel);

        expectedListDataModel.setId(cardId);
        expectedListDataModel.setLabels(labels);

        actualCardDataModel = new CardController(defaultCardDataModel.getListId()).getCardInfo(cardName);
        Assert.assertTrue(expectedListDataModel.isDataTheSame(actualCardDataModel), "The data in the List should be the same");
    }

    @Test(priority = 4)
    public void createBoard() {
        CardDataModel createdCardModel = new CardController(defaultCardDataModel.getListId()).createCard(defaultCardDataModel);
        CardDataModel actualCardModel = new CardController(defaultCardDataModel.getListId()).getCardInfo(defaultCardDataModel.getName());
        defaultCardDataModel.setId(createdCardModel.getId());
        Assert.assertTrue(actualCardModel.isDataTheSame(createdCardModel), "Data in the models should be the same");
    }

    @Test(priority = 5)
    public void deleteBoard() {
        String result = new CardController(defaultCardDataModel.getListId()).deleteCard(defaultCardDataModel);
        Assert.assertTrue(result.contains("{\"limits\":{}}"));
    }
}
