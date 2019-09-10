package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;


public class StartController {
    @FXML
    private Button use_excel;

    @FXML
    public void initialize() {
    }

    @FXML
    private void changeScene(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/use-excel.fxml"));
            Parent parent = loader.load();
            ((Stage)use_excel.getScene().getWindow()).setScene(new Scene(parent, 455, 475));
        } catch (IOException eox) {
            eox.printStackTrace();
        }
    }

}
