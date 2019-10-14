package dataModels;

import com.google.gson.JsonArray;

import java.util.HashMap;

public class CardDataModel {
    private String id;
    private String title;
    private String listId;
    private String description;
    private HashMap<Integer, LabelDataModel> labelsId;
    private String membersId;

    public CardDataModel(){

    }

    public CardDataModel (String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HashMap<Integer, LabelDataModel> getLabelsId(JsonArray jsonArray) {
        HashMap<Integer, LabelDataModel> map = new HashMap<>();
        for (int counter = 0; counter <= jsonArray.size(); counter++) {
            LabelDataModel labelDataModel = new LabelDataModel();
            labelDataModel.setId(jsonArray.get(counter).getAsJsonObject().get("id").getAsString());
            labelDataModel.setName(jsonArray.get(counter).getAsJsonObject().get("name").getAsString());
            labelDataModel.setColor(jsonArray.get(counter).getAsJsonObject().get("color").getAsString());
            map.put(counter, labelDataModel);
        }
        return map;
    }

    public void setLabelsId(HashMap<Integer, LabelDataModel> labelsId) {
        this.labelsId = labelsId;
    }

    public String getMembersId() {
        return membersId;
    }

    public void setMembersId(String membersId) {
        this.membersId = membersId;
    }
}

