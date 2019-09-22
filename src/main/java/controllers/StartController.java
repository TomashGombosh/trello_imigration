package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartController implements Initializable {
    @FXML
    private Button useExcel;

    @FXML
    private Button useDesktop;

    @Override
    public void initialize(URL location, ResourceBundle resources){
    }

    @FXML
    private void goToExcel(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/use-excel.fxml"));
            Parent parent = loader.load();
            ((Stage)useExcel.getScene().getWindow()).setScene(new Scene(parent, 455, 475));
        } catch (IOException eox) {
            eox.printStackTrace();
        }
    }

    @FXML
    private void goToDesktop(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/use-desktop.fxml"));
            Parent parent = loader.load();
            ((Stage)useDesktop.getScene().getWindow()).setScene(new Scene(parent, 455, 475));
        } catch (IOException eox) {
            eox.printStackTrace();
        }
    }
}
