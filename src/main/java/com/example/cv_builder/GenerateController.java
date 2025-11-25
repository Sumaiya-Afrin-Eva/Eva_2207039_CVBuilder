// ...existing code...
package com.example.cv_builder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

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
    @FXML private ImageView profileImageView;

    private int currentId = -1;
    private final CVDao dao = new CVDao();

    // existing setter used by CreateController before — replaced with this single method
    public void setCVDataWithId(CVData cv) {
        if (cv == null) return;
        currentId = cv.getId();
        fullNameLabel.setText(cv.getFullName());
        jobTitleLabel.setText(cv.getJobTitle());
        emailLabel.setText("Email: " + (cv.getEmail() == null ? "" : cv.getEmail()));
        phoneLabel.setText("Phone: " + (cv.getPhone() == null ? "" : cv.getPhone()));
        addressLabel.setText("Address:\n" + (cv.getAddress() == null ? "" : cv.getAddress()));
        aboutLabel.setText(cv.getProjects());
        skillsLabel.setText(cv.getSkills());
        languagesLabel.setText(cv.getLanguages());
        experienceLabel.setText(cv.getExperience());
        educationLabel.setText(cv.getEducation());
        if (cv.getImageUrl() != null && !cv.getImageUrl().isEmpty()) {
            try {
                Image image = new Image(cv.getImageUrl());
                profileImageView.setImage(image);
            } catch (Exception e) { e.printStackTrace(); }
        }
    }

    @FXML
    private void backToHome(ActionEvent event) throws Exception {
        Parent homeRoot = FXMLLoader.load(getClass().getResource("home-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(homeRoot));
        stage.show();
    }

    @FXML
    private void onDeleteAction(ActionEvent event) {
        if (currentId <= 0) return;
        DBTaskExecutor.run(
                () -> dao.delete(currentId),
                deleted -> {
                    // navigate back to saved list or home on success
                    javafx.application.Platform.runLater(() -> {
                        try {
                            Parent root = FXMLLoader.load(getClass().getResource("saved_cvs.fxml"));
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(new Scene(root));
                            stage.show();
                        } catch (Exception e) { e.printStackTrace(); }
                    });
                },
                err -> err.printStackTrace()
        );
    }

    @FXML
    private void onUpdateAction(ActionEvent event) {
        if (currentId <= 0) return;
        // load the same CV into Create form for editing
        DBTaskExecutor.run(
                () -> dao.getById(currentId).orElse(null),
                cv -> javafx.application.Platform.runLater(() -> {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("create.fxml"));
                        Parent root = loader.load();
                        CreateController ctrl = loader.getController();
                        ctrl.loadCV(cv);
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.setTitle("Edit CV");
                        stage.show();
                    } catch (Exception e) { e.printStackTrace(); }
                }),
                err -> err.printStackTrace()
        );
    }
}