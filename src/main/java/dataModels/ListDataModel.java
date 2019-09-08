package dataModels;

import com.google.gson.JsonArray;

import java.util.HashMap;

public class ListDataModel {
    private String id;
    private String name;
    private HashMap<Integer, CardDataModel> cards;
    private String idBoard;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<Integer, CardDataModel> getCards(JsonArray jsonArray) {
        HashMap<Integer, CardDataModel> map = new HashMap<>();
        for (int counter = 0; counter <= jsonArray.size(); counter++) {
            CardDataModel cardDataModel = new CardDataModel();
            cardDataModel.setId(jsonArray.get(counter).getAsJsonObject().get("id").getAsString());
            cardDataModel.setTitle(jsonArray.get(counter).getAsJsonObject().get("name").getAsString());
            cardDataModel.setListId(id);
            map.put(counter, cardDataModel);
        }
        return map;
    }

    public String getIdBoard() {
        return idBoard;
    }

    public void setIdBoard(String idBoard) {
        this.idBoard = idBoard;
    }
}
