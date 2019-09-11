package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class UseExcelController {
    @FXML
    private Button back;

    @FXML
    public void initialize() {
    }

    @FXML
    private void goBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/start.fxml"));
            Parent parent = loader.load();
            ((Stage)back.getScene().getWindow()).setScene(new Scene(parent, 455, 475));
        } catch (IOException eox) {
            eox.printStackTrace();
        }
    }
}
