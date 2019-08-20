package dataModels;

import config.Config;
import services.ExcelHelper;

public class ExcelDataModel {
    private String  board;
    private String list;
    private String title;
    private String description;
    private String label;
    private ExcelHelper excelHelper = new ExcelHelper();

    public ExcelDataModel(String board, String list, String title, String description, String label) {
        this.board = board;
        this.list = list;
        this.title = title;
        this.description = description;
        this.label = label;
    }

    public ExcelDataModel getExcelData(int row){
        this.board = excelHelper.getColumn(Config.FILE_LOCATION, 0).get(row);
        this.list = excelHelper.getColumn(Config.FILE_LOCATION, 1).get(row);
        this.title = excelHelper.getColumn(Config.FILE_LOCATION, 2).get(row);
        this.description = excelHelper.getColumn(Config.FILE_LOCATION, 3).get(row);
        this.label = excelHelper.getColumn(Config.FILE_LOCATION, 4).get(row);
        return this;
    }

    public String getBoard(){
        return board;
    }

    public void setBoard(String board){
        this.board = board;
    }

    public String getList(){
        return list;
    }

    public void setList(String list){
        this.list = list;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getLabel(){
        return label;
    }

    public void setLabel(String label){
        this.label = label;
    }
}
