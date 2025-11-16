package com.example.cv_builder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.control.Label;

public class GenerateController {

    @FXML private Label fullNameLabel;
    @FXML private Label jobTitleLabel;

    @FXML private Label emailLabel;
    @FXML private Label phoneLabel;
    @FXML private Label addressLabel;

    @FXML private Label aboutLabel;
    @FXML private Label skillsLabel;
    @FXML private Label languagesLabel;

    @FXML private Label experienceLabel;
    @FXML private Label educationLabel;

    // Set CV Data
    public void setCVData(String fullName,
                          String jobTitle,
                          String email,
                          String phone,
                          String address,
                          String about,
                          String skills,
                          String languages,
                          String experience,
                          String education) {

        fullNameLabel.setText(fullName);
        jobTitleLabel.setText(jobTitle);

        emailLabel.setText("Email: " + email);
        phoneLabel.setText("Phone: " + phone);
        addressLabel.setText("Address: " + address);

        aboutLabel.setText(about);
        skillsLabel.setText(skills);
        languagesLabel.setText(languages);

        experienceLabel.setText(experience);
        educationLabel.setText(education);
    }

    @FXML
    private void backToHome(ActionEvent event) throws Exception {
        Parent homeRoot = FXMLLoader.load(getClass().getResource("home.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(homeRoot));
        stage.show();
    }
}
