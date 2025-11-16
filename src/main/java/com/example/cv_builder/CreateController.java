package com.example.cv_builder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CreateController {

    @FXML
    void openGenerateCV(ActionEvent event) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("generate.fxml"));

        Scene scene = new Scene(root, 900, 700);
        scene.getStylesheets().add(
                getClass().getResource("generate.css").toExternalForm()
        );

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
