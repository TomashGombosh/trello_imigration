package controllers.trello;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import config.Config;
import dataModels.CardDataModel;
import dataModels.LabelDataModel;
import dataModels.ListDataModel;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.jetbrains.annotations.NotNull;
import services.helper.ApiHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CardController extends ApiHelper {
    private String url = "cards";
    private HashMap<Integer, CardDataModel> getCardsInList;
    private ListDataModel primaryList;
    private JsonParser parser;

    public CardController(String listId) {
        parser = new JsonParser();
        primaryList = new ListDataModel(listId);
       // getCardsInList = getCardsInfo();
    }

    //TODO add 'Add, Update, Delete' to that controller
    /*
    private HashMap<Integer, CardDataModel> getCardsInfo() {
        HashMap<Integer, CardDataModel> map = new HashMap<>();
        String url = "lists/" + primaryList.getId() + "/cards";
        List<NameValuePair> headers = new ArrayList<>();
        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("key", Config.API_KEY));
        parameters.add(new BasicNameValuePair("token", Config.TOKEN));
        JsonArray jsonArray = (JsonArray) parser.parse(sendGetRequest(Config.TRELLO_API_URL, headers, url, parameters));
        for (int counter = 0; counter < jsonArray.size(); counter++) {
            CardDataModel getCardDataModel = new CardDataModel();
            getCardDataModel.setId(jsonArray.get(counter).getAsJsonObject().get("id").getAsString());
            getCardDataModel.setTitle(jsonArray.get(counter).getAsJsonObject().get("name").getAsString());
            getCardDataModel.setLabelsId(jsonArray.get(counter).getAsJsonObject().get("cards").getAsJsonArray());
            map.put(counter, getListDataModel);
        }
        return map;
    }

    public void createCard(@NotNull CardDataModel cardDataModel) {
        List<NameValuePair> headers = new ArrayList<>();
        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("name", cardDataModel.getTitle()));
        parameters.add(new BasicNameValuePair("desc", cardDataModel.getDescription()));
        parameters.add(new BasicNameValuePair("idList", cardDataModel.getListId()));
        if (cardDataModel.getLabelsId() != null)
            parameters.add(new BasicNameValuePair("idLabels", cardDataModel.getLabelsId()));
        if (cardDataModel.getMembersId() != null)
            parameters.add(new BasicNameValuePair("idMembers", cardDataModel.getMembersId()));
        parameters.add(new BasicNameValuePair("key", Config.API_KEY));
        parameters.add(new BasicNameValuePair("token", Config.TOKEN));
        sendPostRequest(Config.TRELLO_API_URL, headers, url, parameters);
    }

    private HashMap<Integer, LabelDataModel> getLabels(JsonArray labelsArray) {
        HashMap<Integer, LabelDataModel> labelMap = new HashMap<>();
        for (int labelCounter = 0; labelCounter < labelsArray.size(); labelCounter++) {
            LabelDataModel label = new LabelDataModel();
            label.setId(labelsArray.get(labelCounter).getAsJsonObject().get("id").getAsString());
            label.setName(labelsArray.get(labelCounter).getAsJsonObject().get("name").getAsString());
            label.setColor(labelsArray.get(labelCounter).getAsJsonObject().get("color").getAsString());
            labelMap.put(labelCounter, label);
        }
        return labelMap;
    }*/

}
