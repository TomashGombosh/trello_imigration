package dataModels;

import com.google.gson.JsonArray;
import services.helper.StringHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class CardDataModel {
    private String id;
    private String name;
    private String listId;
    private String description;
    private HashMap<Integer, LabelDataModel> labels;
    private String membersId;
    private boolean closed;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public HashMap<Integer, LabelDataModel> getLabels() {
        return labels;
    }


    public void setLabels(HashMap<Integer, LabelDataModel> labelsId) {
        this.labels = labels;
    }

    public String getMembersId() {
        return membersId;
    }

    public void setMembersId(String membersId) {
        this.membersId = membersId;
    }

    public boolean getClosed(){
        return closed;
    }

    public void setClosed(boolean closed){
        this.closed = closed;
    }

    public boolean isDataTheSame(CardDataModel cardDataModel) {
        boolean same = true;
        ArrayList<Boolean> resultList = new ArrayList<>();
        if (this.id != null && cardDataModel.id != null) {
            resultList.add(new StringHelper().compareStringsWithLog(this.id, cardDataModel.id));
        }
        if (this.name != null && cardDataModel.name != null) {
            resultList.add(new StringHelper().compareStringsWithLog(this.name, cardDataModel.name));
        }
        if (this.description != null && cardDataModel.description != null) {
            resultList.add(new StringHelper().compareStringsWithLog(this.description, cardDataModel.description));
        }
        if (this.labels != null && cardDataModel.labels != null) {
            for (int i = 0; i < this.labels.size(); i++) {
                resultList.add(new StringHelper().compareStringsWithLog(this.labels.get(i).getColor(), cardDataModel.labels.get(i).getId()));
                resultList.add(new StringHelper().compareStringsWithLog(this.labels.get(i).getColor(), cardDataModel.labels.get(i).getColor()));
                resultList.add(new StringHelper().compareStringsWithLog(this.labels.get(i).getName(), cardDataModel.labels.get(i).getName()));
            }
        }
        for (int i = 0; i < resultList.size(); i++) {
            same &= resultList.get(i);
        }
        return same;
    }

}

