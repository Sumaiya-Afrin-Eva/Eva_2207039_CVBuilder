package com.example.cv_builder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateController {

    @FXML private TextField fullNameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;
    @FXML private TextField addressField;
    @FXML private TextField educationField;
    @FXML private TextField projectsField;
    @FXML private TextField skillsField;
    @FXML private TextField experienceField;


    @FXML
    void openGenerateCV(ActionEvent event) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("preview.fxml"));
        Parent root = loader.load();

        // Controller access
        GenerateController controller = loader.getController();

        controller.setCVData(
                fullNameField.getText(),
                " ",
                emailField.getText(),
                phoneField.getText(),
                addressField.getText(),
                projectsField.getText(),
                skillsField.getText(),
                " ",
                experienceField.getText(),
                educationField.getText()
        );

        ScrollPane scroll = new ScrollPane();
        scroll.setContent(root);
        scroll.setFitToWidth(true);
        scroll.setFitToHeight(true);
        scroll.setPannable(true);

        Scene scene = new Scene(scroll, 900, 700);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
