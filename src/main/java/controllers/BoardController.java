package controllers;

import com.google.gson.JsonArray;
import config.Config;
import dataModels.BoardDataModel;
import dataModels.MemberDataModel;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.jetbrains.annotations.NotNull;
import com.google.gson.JsonParser;
import services.helper.ApiHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BoardController extends ApiHelper {
    private String url = "boards";

    public String getBoardId(MemberDataModel memberDataModel, BoardDataModel boardDataModel) {
        JsonParser parser = new JsonParser();
        HashMap<Integer, BoardDataModel> map = new HashMap<>();
        String url = "members/" + memberDataModel.getId() + "/boards";
        List<NameValuePair> headers = new ArrayList<>();
        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("key", Config.API_KEY));
        parameters.add(new BasicNameValuePair("token", Config.TOKEN));

        JsonArray jsonArray = (JsonArray) parser.parse(sendGetRequest(Config.TRELLO_API_URL, headers, url, parameters));
        for (int counter = 0; counter < jsonArray.size(); counter++) {
            BoardDataModel getBoardDataModel = new BoardDataModel();
            getBoardDataModel.setId(jsonArray.get(counter).getAsJsonObject().get("id").getAsString());
            getBoardDataModel.setName(jsonArray.get(counter).getAsJsonObject().get("name").getAsString());
            map.put(counter, getBoardDataModel);
        }
        for (int counter = 0; counter < map.size(); counter++) {
            BoardDataModel boardDataModel1 = map.get(counter);
            if (boardDataModel1.getName().equals(boardDataModel.getName())) {
                return boardDataModel1.getId();
            }
        }
        return null;
    }


    public void createBoard(@NotNull BoardDataModel boardDataModel) {
        List<NameValuePair> headers = new ArrayList<>();
        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("name", boardDataModel.getName()));
        if (boardDataModel.getDescription() != null)
            parameters.add(new BasicNameValuePair("desc", boardDataModel.getDescription()));
        parameters.add(new BasicNameValuePair("key", Config.API_KEY));
        parameters.add(new BasicNameValuePair("token", Config.TOKEN));
        sendPostRequest(Config.TRELLO_API_URL, headers, url, parameters);
    }
}
