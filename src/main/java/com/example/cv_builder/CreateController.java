package com.example.cv_builder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import java.io.File;


public class CreateController {

    @FXML private TextArea fullNameField;
    @FXML private TextArea emailField;
    @FXML private TextArea phoneField;
    @FXML private TextArea addressField;
    @FXML private TextArea educationField;
    @FXML private TextArea projectsField;
    @FXML private TextArea skillsField;
    @FXML private TextArea experienceField;
    @FXML private TextArea languagesField;
    @FXML private TextArea jobTitleField;


    @FXML private ImageView profileImageView;

    private File selectedImageFile;

    @FXML
    void selectProfileImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Profile Image");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        File file = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
        if (file != null) {
            selectedImageFile = file;
            Image image = new Image(file.toURI().toString());
            profileImageView.setImage(image);
            System.out.println("Selected image: " + file.getAbsolutePath());
        } else {
            System.out.println("No file selected.");
        }
    }
    @FXML
    void openGenerateCV(ActionEvent event) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("generate.fxml"));
        Parent root = loader.load();

        GenerateController controller = loader.getController();

        controller.setCVData(
                fullNameField.getText(),
                jobTitleField.getText(),
                emailField.getText(),
                phoneField.getText(),
                addressField.getText(),
                projectsField.getText(),
                skillsField.getText(),
                languagesField.getText(),
                experienceField.getText(),
                educationField.getText(),
                selectedImageFile != null ? selectedImageFile.toURI().toString() : null
        );

        ScrollPane scroll = new ScrollPane();
        scroll.setContent(root);
        scroll.setFitToWidth(true);
        scroll.setFitToHeight(true);
        scroll.setPannable(true);

        Scene createScene = new Scene(scroll, 900, 700);

        createScene.getStylesheets().add(
                getClass().getResource("generate.css").toExternalForm());


        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(createScene);
        stage.setMaximized(true);
        stage.show();
    }
}
