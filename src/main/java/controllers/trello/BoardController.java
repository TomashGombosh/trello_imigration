package controllers.trello;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import config.Config;
import dataModels.BoardDataModel;
import dataModels.LabelDataModel;
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
    private HashMap<Integer, BoardDataModel> getUserBoards;
    private MemberDataModel defaultUser;
    private HashMap<Integer, LabelDataModel> defaultLabels = new BoardDataModel().defaultLabels();
    private JsonParser parser;

    public BoardController() {
        parser = new JsonParser();
        defaultUser = new MemberDataModel();
        defaultUser.setId(Config.MEMBER);
        getUserBoards = getBoardsInfo();
    }


    public String getBoardId(BoardDataModel boardDataModel) {
        HashMap<Integer, BoardDataModel> map = getBoardsInfo();
        for (int counter = 0; counter < map.size(); counter++) {
            BoardDataModel boardDataModel1 = map.get(counter);
            if (boardDataModel1.getName().equals(boardDataModel.getName())) {
                return boardDataModel1.getId();
            }
        }
        return null;
    }

    public BoardDataModel getBoardInfo(String idOrName) {
        HashMap<Integer, BoardDataModel> map = getBoardsInfo();
        for (int counter = 0; counter < map.size(); counter++) {
            BoardDataModel boardDataModel1 = map.get(counter);
            if (boardDataModel1.getName().equals(idOrName) || boardDataModel1.getId().equals(idOrName)) {
                return boardDataModel1;
            }
        }
        return new BoardDataModel("No Such Board");
    }

    private boolean validateBoardInfo(BoardDataModel boardDataModel) {
        for (int counter = 0; counter < getUserBoards.size(); counter++) {
            if (getUserBoards.get(counter).isDataTheSame(boardDataModel))
                return true;
        }
        return false;
    }

    private HashMap<Integer, BoardDataModel> getBoardsInfo() {
        HashMap<Integer, BoardDataModel> map = new HashMap<>();
        String url = "members/" + defaultUser.getId() + "/boards";
        List<NameValuePair> headers = new ArrayList<>();
        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("key", Config.API_KEY));
        parameters.add(new BasicNameValuePair("token", Config.TOKEN));
        JsonArray jsonArray = (JsonArray) parser.parse(sendGetRequest(Config.TRELLO_API_URL, headers, url, parameters));
        for (int counter = 0; counter < jsonArray.size(); counter++) {
            BoardDataModel getBoardDataModel = new BoardDataModel();
            getBoardDataModel.setId(jsonArray.get(counter).getAsJsonObject().get("id").getAsString());
            getBoardDataModel.setName(jsonArray.get(counter).getAsJsonObject().get("name").getAsString());
            getBoardDataModel.setDescription(jsonArray.get(counter).getAsJsonObject().get("desc").getAsString());
            getBoardDataModel.setLabels(getLabels(jsonArray.get(counter).getAsJsonObject().getAsJsonObject("labelNames")));
            map.put(counter, getBoardDataModel);
        }
        return map;
    }

    public BoardDataModel createBoard(@NotNull BoardDataModel boardDataModel) {
        if (!validateBoardInfo(boardDataModel)) {
            BoardDataModel getBoardDataModel = new BoardDataModel();
            List<NameValuePair> headers = new ArrayList<>();
            List<NameValuePair> parameters = new ArrayList<>();
            parameters.add(new BasicNameValuePair("name", boardDataModel.getName()));
            if (boardDataModel.getDescription() != null)
                parameters.add(new BasicNameValuePair("desc", boardDataModel.getDescription()));
            parameters.add(new BasicNameValuePair("key", Config.API_KEY));
            parameters.add(new BasicNameValuePair("token", Config.TOKEN));
            JsonObject jsonObject = (JsonObject) parser.parse(sendPostRequest(Config.TRELLO_API_URL, headers, url, parameters));
            getBoardDataModel.setId(jsonObject.getAsJsonObject().get("id").getAsString());
            getBoardDataModel.setName(jsonObject.getAsJsonObject().get("name").getAsString());
            getBoardDataModel.setDescription(jsonObject.getAsJsonObject().get("desc").getAsString());
            getBoardDataModel.setLabels(getLabels(jsonObject.getAsJsonObject().getAsJsonObject("labelNames")));
            return getBoardDataModel;
        } else {
            return new BoardDataModel("Board Already Created");
        }
    }

    public BoardDataModel updateBoard(@NotNull BoardDataModel updateBoardDataModel, @NotNull String name) {
        BoardDataModel actualBoardDataModel = getBoardInfo(name);
        if (!validateBoardInfo(updateBoardDataModel)) {

        } else {
            actualBoardDataModel.setId("Board Already Created");
            return actualBoardDataModel;
        }
        return null;
    }


    public String deleteBoard(@NotNull BoardDataModel boardDataModel) {
        List<NameValuePair> parameters = new ArrayList<>();
        if (validateBoardInfo(boardDataModel)) {
            List<NameValuePair> headers = new ArrayList<>();
            parameters.add(new BasicNameValuePair("key", Config.API_KEY));
            parameters.add(new BasicNameValuePair("token", Config.TOKEN));
            String durl = url + "/" + boardDataModel.getId();
            return sendDeleteRequest(Config.TRELLO_API_URL, headers, durl, parameters);
        } else {
            return "Board with that data is not present";
        }
    }

    private HashMap<Integer, LabelDataModel> getLabels(JsonObject labelObject) {
        HashMap<Integer, LabelDataModel> labelMap = new HashMap<>();
        for (int labelCounter = 0; labelCounter < labelObject.size(); labelCounter++) {
            LabelDataModel label = new LabelDataModel();
            label.setId(defaultLabels.get(labelCounter).getId());
            label.setName(labelObject.get(defaultLabels.get(labelCounter).getId()).getAsString());
            labelMap.put(labelCounter, label);
        }
        return labelMap;
    }

}
