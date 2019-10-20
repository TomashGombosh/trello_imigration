package controllers.trello;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import config.Config;
import dataModels.*;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.jetbrains.annotations.NotNull;
import services.helper.ApiHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListController extends ApiHelper {
    private String url = "lists";
    private HashMap<Integer, ListDataModel> getBoardLists;
    private BoardDataModel primaryBoard;
    private JsonParser parser;

    public ListController(String boardId) {
        parser = new JsonParser();
        primaryBoard = new BoardDataModel(boardId);
        getBoardLists = getBoardLists();
    }

    public String getListId(ListDataModel listDataModel) {
        HashMap<Integer, ListDataModel> map = getBoardLists();
        for (int counter = 0; counter < map.size(); counter++) {
            ListDataModel listDataModel1 = map.get(counter);
            if (listDataModel1.getName().equals(listDataModel.getName())) {
                return listDataModel1.getId();
            }
        }
        return null;
    }

    public ListDataModel getListInfo(String idOrName) {
        HashMap<Integer, ListDataModel> map = getBoardLists();
        for (int counter = 0; counter < map.size(); counter++) {
            ListDataModel listDataModel1 = map.get(counter);
            if (listDataModel1.getName().equals(idOrName) || listDataModel1.getId().equals(idOrName)) {
                return listDataModel1;
            }
        }
        return new ListDataModel("No Such Board");
    }

    private HashMap<Integer, ListDataModel> getBoardLists() {
        HashMap<Integer, ListDataModel> map = new HashMap<>();
        String url = "boards/" + primaryBoard.getId() + "/lists";
        List<NameValuePair> headers = new ArrayList<>();
        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("key", Config.API_KEY));
        parameters.add(new BasicNameValuePair("token", Config.TOKEN));
        parameters.add(new BasicNameValuePair("cards", "all"));
        JsonArray jsonArray = (JsonArray) parser.parse(sendGetRequest(Config.TRELLO_API_URL, headers, url, parameters));
        for (int counter = 0; counter < jsonArray.size(); counter++) {
            ListDataModel getListDataModel = new ListDataModel();
            getListDataModel.setId(jsonArray.get(counter).getAsJsonObject().get("id").getAsString());
            getListDataModel.setName(jsonArray.get(counter).getAsJsonObject().get("name").getAsString());
            getListDataModel.setCards(getCards(jsonArray.get(counter).getAsJsonObject().get("cards").getAsJsonArray()));
            map.put(counter, getListDataModel);
        }
        return map;
    }


    private boolean validateListInfo(ListDataModel listDataModel) {
        for (int counter = 0; counter < getBoardLists.size(); counter++) {
            if (getBoardLists.get(counter).isDataTheSame(listDataModel))
                return true;
        }
        return false;
    }

    public ListDataModel createList(@NotNull ListDataModel listDataModel) {
        ListDataModel getListDataModel = new ListDataModel();
        if (!validateListInfo(listDataModel)) {
            List<NameValuePair> headers = new ArrayList<>();
            List<NameValuePair> parameters = new ArrayList<>();
            parameters.add(new BasicNameValuePair("name", listDataModel.getName()));
            parameters.add(new BasicNameValuePair("key", Config.API_KEY));
            parameters.add(new BasicNameValuePair("token", Config.TOKEN));
            parameters.add(new BasicNameValuePair("idBoard", primaryBoard.getId()));
            JsonObject jsonObject = (JsonObject) parser.parse(sendPostRequest(Config.TRELLO_API_URL, headers, url, parameters));
            getListDataModel.setId(jsonObject.getAsJsonObject().get("id").getAsString());
            getListDataModel.setName(jsonObject.getAsJsonObject().get("name").getAsString());
            getListDataModel.setCards(getCards(jsonObject.getAsJsonArray("cards")));

            return getListDataModel;
        } else {
            getListDataModel.setId("List Already Created");
            return getListDataModel;
        }
    }

    public ListDataModel updateList(@NotNull ListDataModel updateListDataModel, @NotNull String name) {
        ListDataModel actualBoardDataModel = getListInfo(name);
        if (!validateListInfo(updateListDataModel)) {

        } else {
            actualBoardDataModel.setId("Board Already Created");
            return actualBoardDataModel;
        }
        return null;
    }


    public ListDataModel closeList(@NotNull ListDataModel listDataModel) {
        List<NameValuePair> parameters = new ArrayList<>();
        ListDataModel closedDataModel = new ListDataModel();
        if (validateListInfo(listDataModel)) {
            List<NameValuePair> headers = new ArrayList<>();
            parameters.add(new BasicNameValuePair("key", Config.API_KEY));
            parameters.add(new BasicNameValuePair("token", Config.TOKEN));
            parameters.add(new BasicNameValuePair("value", "true"));
            String durl = url + "/" + listDataModel.getId() + "/closed";
            JsonObject jsonObject = (JsonObject) parser.parse(sendPutRequest(Config.TRELLO_API_URL, headers, durl, parameters));
            closedDataModel.setId(jsonObject.getAsJsonObject().get("id").getAsString());
            closedDataModel.setName(jsonObject.getAsJsonObject().get("name").getAsString());
            closedDataModel.setClosed(Boolean.parseBoolean(jsonObject.getAsJsonObject().get("closed").getAsString()));
            return closedDataModel;
        } else {
            closedDataModel.setId("No such list in the board " + primaryBoard.getId());
            return closedDataModel;
        }
    }

    private HashMap<Integer, CardDataModel> getCards(JsonArray cardObject) {
        if (cardObject == null) {
            return null;
        } else {
            HashMap<Integer, CardDataModel> cardMap = new HashMap<>();
            for (int cardCounter = 0; cardCounter < cardObject.size(); cardCounter++) {
                CardDataModel cards = new CardDataModel();
                cards.setId(cardObject.get(cardCounter).getAsJsonObject().get("id").getAsString());
                cards.setName(cardObject.get(cardCounter).getAsJsonObject().get("name").getAsString());
                cardMap.put(cardCounter, cards);
            }
            return cardMap;
        }
    }
}
