package dataModels;

public class LabelDataModel {
    private String id;
    private String name;
    private String color;
    private String idBoard;
    private String uses;

    public LabelDataModel() {

    }

    public LabelDataModel(String id){
        this.id = id;
    }

    LabelDataModel(String color, String name) {
        this.color = color;
        this.name = name;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    public String getIdBoard() {
        return idBoard;
    }

    public void setIdBoard(String idBoard) {
        this.idBoard = idBoard;
    }

    public String getUses() {
        return uses;
    }

    public void setUses(String uses) {
        this.uses = uses;
    }
}



