package com.example.cv_builder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomeController {
    private void go(ActionEvent e, String fxml) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 900, 700));
            stage.show();
        }catch(Exception ex){ ex.printStackTrace(); }
    }

    @FXML void openCreateCV(ActionEvent e){ go(e, "create.fxml"); }
}

