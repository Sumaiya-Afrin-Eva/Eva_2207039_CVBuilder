// ...existing code...
package com.example.cv_builder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

// ...existing code...
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
    private final CVDao dao = new CVDao();

    // track edit mode (id > 0 means update)
    private int editingId = -1;

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
        }
    }

    // Allow external callers to prefill this form for editing
    public void loadCV(CVData cv) {
        if (cv == null) return;
        editingId = cv.getId();
        fullNameField.setText(cv.getFullName());
        jobTitleField.setText(cv.getJobTitle());
        emailField.setText(cv.getEmail());
        phoneField.setText(cv.getPhone());
        addressField.setText(cv.getAddress());
        projectsField.setText(cv.getProjects());
        skillsField.setText(cv.getSkills());
        languagesField.setText(cv.getLanguages());
        experienceField.setText(cv.getExperience());
        educationField.setText(cv.getEducation());
        if (cv.getImageUrl() != null && !cv.getImageUrl().isEmpty()) {
            try {
                Image image = new Image(cv.getImageUrl());
                profileImageView.setImage(image);
            } catch (Exception ignored) {}
        }
    }

    @FXML
    void saveAndOpenGenerate(ActionEvent event) {
        CVData cv = new CVData();
        cv.setFullName(fullNameField.getText());
        cv.setJobTitle(jobTitleField.getText());
        cv.setEmail(emailField.getText());
        cv.setPhone(phoneField.getText());
        cv.setAddress(addressField.getText());
        cv.setProjects(projectsField.getText());
        cv.setSkills(skillsField.getText());
        cv.setLanguages(languagesField.getText());
        cv.setExperience(experienceField.getText());
        cv.setEducation(educationField.getText());
        cv.setImageUrl(selectedImageFile != null ? selectedImageFile.toURI().toString() : (profileImageView.getImage() != null ? profileImageView.getImage().getUrl() : null));

        final boolean isUpdate = editingId > 0;
        if (isUpdate) cv.setId(editingId);

        // run DB operation in background
        DBTaskExecutor.run(
                () -> {
                    if (isUpdate) {
                        dao.update(cv);
                        return cv; // still return the model for navigation
                    } else {
                        return dao.insert(cv);
                    }
                },
                resultCv -> {
                    // navigate on FX thread
                    javafx.application.Platform.runLater(() -> {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("generate.fxml"));
                            Parent root = loader.load();
                            GenerateController controller = loader.getController();
                            controller.setCVDataWithId(resultCv); // pass CVData (with id)
                            Scene scene = new Scene(root);
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    });
                },
                err -> {
                    err.printStackTrace();
                }
        );
    }
}