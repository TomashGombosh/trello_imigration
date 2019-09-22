package controllers.trello;

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
    private HashMap<Integer, BoardDataModel> getUserBoards;
    private MemberDataModel defaultUser;

    public BoardController() {
        defaultUser = new MemberDataModel();
        defaultUser.setId(Config.MEMBER);
        HashMap<Integer, BoardDataModel> getUserBoards = getBoardsInfo();
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

    public BoardDataModel getBoardInfo(BoardDataModel boardDataModel) {
        HashMap<Integer, BoardDataModel> map = getBoardsInfo();
        for (int counter = 0; counter < map.size(); counter++) {
            BoardDataModel boardDataModel1 = map.get(counter);
            if (boardDataModel1.getName().equals(boardDataModel.getName())) {
                return boardDataModel1;
            }
        }
        return null;
    }

    private boolean validateBoardInfo(BoardDataModel boardDataModel) {
        for (int counter = 0; counter < getUserBoards.size(); counter++) {
            if (getUserBoards.get(counter).equals(boardDataModel))
                return true;
        }
        return false;
    }

    private HashMap<Integer, BoardDataModel> getBoardsInfo() {
        JsonParser parser = new JsonParser();
        HashMap<Integer, BoardDataModel> map = new HashMap<>();
        String url = "members/" + defaultUser.getId() + "/boards";
        List<NameValuePair> headers = new ArrayList<>();
        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("key", Config.API_KEY));
        parameters.add(new BasicNameValuePair("token", Config.TOKEN));
        String s = sendGetRequest(Config.TRELLO_API_URL, headers, url, parameters);
        JsonArray jsonArray = (JsonArray) parser.parse(sendGetRequest(Config.TRELLO_API_URL, headers, url, parameters));
        for (int counter = 0; counter < jsonArray.size(); counter++) {
            BoardDataModel getBoardDataModel = new BoardDataModel();
            getBoardDataModel.setId(jsonArray.get(counter).getAsJsonObject().get("id").getAsString());
            getBoardDataModel.setName(jsonArray.get(counter).getAsJsonObject().get("name").getAsString());
            map.put(counter, getBoardDataModel);
        }
        return map;
    }

    public String createBoard(@NotNull BoardDataModel boardDataModel) {
        if (validateBoardInfo(boardDataModel)) {
            List<NameValuePair> headers = new ArrayList<>();
            List<NameValuePair> parameters = new ArrayList<>();
            parameters.add(new BasicNameValuePair("name", boardDataModel.getName()));
            if (boardDataModel.getDescription() != null)
                parameters.add(new BasicNameValuePair("desc", boardDataModel.getDescription()));
            parameters.add(new BasicNameValuePair("key", Config.API_KEY));
            parameters.add(new BasicNameValuePair("token", Config.TOKEN));
            sendPostRequest(Config.TRELLO_API_URL, headers, url, parameters);
            return "Success";
        } else {
            return "Board with that data is present";
        }
    }
}
