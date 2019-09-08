package dataModels;

import com.google.gson.JsonArray;

import java.util.HashMap;

public class BoardDataModel {
    private String id;
    private String name;
    private String description;
    private HashMap<Integer, LabelDataModel> labels;
    private HashMap<Integer, ListDataModel> lists;

    public BoardDataModel(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HashMap<Integer, LabelDataModel> getLabels(JsonArray jsonArray) {
        HashMap<Integer, LabelDataModel> map = new HashMap<>();
        for(int counter = 0; counter <=jsonArray.size(); counter++){
            LabelDataModel labelDataModel = new LabelDataModel();
            labelDataModel.setId(jsonArray.get(counter).getAsJsonObject().get("id").getAsString());
            labelDataModel.setName(jsonArray.get(counter).getAsJsonObject().get("name").getAsString());
            labelDataModel.setColor(jsonArray.get(counter).getAsJsonObject().get("color").getAsString());
            map.put(counter, labelDataModel);
        }
        return map;
    }

    public HashMap<Integer, ListDataModel> getLists(JsonArray jsonArray) {
        HashMap<Integer, ListDataModel> map = new HashMap<>();
        for(int counter = 0; counter <=jsonArray.size(); counter++){
            ListDataModel labelDataModel = new ListDataModel();
            labelDataModel.setId(jsonArray.get(counter).getAsJsonObject().get("id").getAsString());
            labelDataModel.setName(jsonArray.get(counter).getAsJsonObject().get("name").getAsString());
            map.put(counter, labelDataModel);
        }
        return map;
    }
}
