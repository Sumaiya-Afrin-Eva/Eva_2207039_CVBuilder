package com.example.cv_builder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;
import javafx.scene.Parent;
import java.io.IOException;

public class HomeController {

    @FXML
    void openCreateCV(ActionEvent event) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("create.fxml"));

        ScrollPane scroll = new ScrollPane();
        scroll.setContent(root);
        scroll.setFitToWidth(true);

        Scene createScene = new Scene(scroll, 900, 700);
        createScene.getStylesheets().add(
                getClass().getResource("create.css").toExternalForm()
        );

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(createScene);
        stage.setMaximized(true);
        stage.show();
    }

    @FXML
    void openSavedCVs(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("savedCVs.fxml"));

            ScrollPane scroll = new ScrollPane();
            scroll.setContent(root);
            scroll.setFitToWidth(true);

            Scene savedScene = new Scene(scroll, 900, 700);

            // reuse generate.css for consistent style
            savedScene.getStylesheets().add(
                    getClass().getResource("generate.css").toExternalForm()
            );

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(savedScene);
            stage.setMaximized(true);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
