package com.example.cv_builder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;
import javafx.scene.Parent;

public class HomeController {

    @FXML
    void openCreateCV(ActionEvent event) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("create.fxml"));

        ScrollPane scroll = new ScrollPane();
        scroll.setContent(root);
        scroll.setFitToWidth(true);

        Scene createScene = new Scene(scroll, 900, 700);

        createScene.getStylesheets().add(
                getClass().getResource("create.css").toExternalForm());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(createScene);
        stage.setMaximized(true);
        stage.show();
    }
}
