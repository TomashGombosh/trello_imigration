package dataModels;

import com.google.gson.JsonArray;
import services.helper.StringHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class BoardDataModel {
    private String id;
    private String name;
    private String description;
    private HashMap<Integer, LabelDataModel> labels;
    private HashMap<Integer, ListDataModel> lists;

    public BoardDataModel() {

    }

    public BoardDataModel(String id){
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HashMap<Integer, LabelDataModel> getLabels(JsonArray jsonArray) {
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

    public void setLabels(HashMap<Integer, LabelDataModel> labels) {
        this.labels = labels;
    }

    public HashMap<Integer, ListDataModel> getLists(JsonArray jsonArray) {
        HashMap<Integer, ListDataModel> map = new HashMap<>();
        for (int counter = 0; counter <= jsonArray.size(); counter++) {
            ListDataModel labelDataModel = new ListDataModel();
            labelDataModel.setId(jsonArray.get(counter).getAsJsonObject().get("id").getAsString());
            labelDataModel.setName(jsonArray.get(counter).getAsJsonObject().get("name").getAsString());
            map.put(counter, labelDataModel);
        }
        return map;
    }

    public HashMap<Integer, LabelDataModel> defaultLabels() {
        HashMap<Integer, LabelDataModel> map = new HashMap<>();
        map.put(0, new LabelDataModel("green"));
        map.put(1, new LabelDataModel("yellow"));
        map.put(2, new LabelDataModel("orange"));
        map.put(3, new LabelDataModel("red"));
        map.put(4, new LabelDataModel("purple"));
        map.put(5, new LabelDataModel("blue"));
        map.put(6, new LabelDataModel("sky"));
        map.put(7, new LabelDataModel("lime"));
        map.put(8, new LabelDataModel("pink"));
        map.put(9, new LabelDataModel("black"));
        return map;
    }

    public boolean isDataTheSame(BoardDataModel boardDataModel) {
        boolean same = true;
        ArrayList<Boolean> resultList = new ArrayList<>();
        if (this.id != null && boardDataModel.id != null) {
            resultList.add(new StringHelper().compareStringsWithLog(this.id, boardDataModel.id));
        }
        if (this.name != null && boardDataModel.name != null) {
            resultList.add(new StringHelper().compareStringsWithLog(this.name, boardDataModel.name));
        }
        if (this.description != null && boardDataModel.description != null) {
            resultList.add(new StringHelper().compareStringsWithLog(this.description, boardDataModel.description));
        }
        if (this.labels != null && boardDataModel.labels != null) {
                for (int i = 0; i < this.labels.size(); i++) {
                resultList.add(new StringHelper().compareStringsWithLog(this.labels.get(i).getId(), boardDataModel.labels.get(i).getId()));
                resultList.add(new StringHelper().compareStringsWithLog(this.labels.get(i).getName(), boardDataModel.labels.get(i).getName()));
            }
        }
        if (this.lists != null && boardDataModel.lists != null) {
            for (int i = 0; i <= lists.size(); i++) {
                resultList.add(new StringHelper().compareStringsWithLog(this.lists.get(i).getId(), boardDataModel.lists.get(i).getId()));
                resultList.add(new StringHelper().compareStringsWithLog(this.lists.get(i).getName(), boardDataModel.lists.get(i).getName()));
            }
        }
        for(int i = 0; i< resultList.size();i++){
            same&=resultList.get(i);
        }
        return same;
    }
}
