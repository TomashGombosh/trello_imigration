package dataModels;

import com.google.gson.JsonArray;
import services.helper.StringHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class ListDataModel {
    private String id;
    private String name;
    private HashMap<Integer, CardDataModel> cards;
    private String idBoard;
    private boolean closed;

    public ListDataModel() {

    }

    public ListDataModel(String id){
        this.id = id;
    }

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

    public void setCards(HashMap<Integer, CardDataModel> cards) {
        this.cards = cards;
    }

    public String getIdBoard() {
        return idBoard;
    }

    public void setIdBoard(String idBoard) {
        this.idBoard = idBoard;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public boolean isDataTheSame(ListDataModel listDataModel) {
        boolean same = true;
        ArrayList<Boolean> resultList = new ArrayList<>();
        if (this.id != null && listDataModel.id != null) {
            resultList.add(new StringHelper().compareStringsWithLog(this.id, listDataModel.id));
        }
        if (this.name != null && listDataModel.name != null) {
            resultList.add(new StringHelper().compareStringsWithLog(this.name, listDataModel.name));
        }
        if (this.cards != null && listDataModel.cards != null) {
            for (int i = 0; i < this.cards.size(); i++) {
                resultList.add(new StringHelper().compareStringsWithLog(this.cards.get(i).getId(), listDataModel.cards.get(i).getId()));
                resultList.add(new StringHelper().compareStringsWithLog(this.cards.get(i).getTitle(), listDataModel.cards.get(i).getTitle()));
            }
        }
        for(int i = 0; i< resultList.size();i++){
            same&=resultList.get(i);
        }
        return same;
    }
}
