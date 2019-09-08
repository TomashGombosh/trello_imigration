package dataModels;

public class CardDataModel {
    private String id;
    private String title;
    private String description;
    private String listId;
    private String labelsId;
    private String membersId;

    public CardDataModel(){

    }

    public CardDataModel (String title, String description, String listId, String labelsId, String membersId){
        this.title = title;
        this.description = description;
        this.listId = listId;
        this.labelsId = labelsId;
        this.membersId = membersId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public String getLabelsId() {
        return labelsId;
    }

    public void setLabelsId(String labelsId) {
        this.labelsId = labelsId;
    }

    public String getMembersId() {
        return membersId;
    }

    public void setMembersId(String membersId) {
        this.membersId = membersId;
    }
}

