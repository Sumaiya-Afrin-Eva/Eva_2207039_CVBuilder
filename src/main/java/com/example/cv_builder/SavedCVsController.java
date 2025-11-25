package com.example.cv_builder;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.scene.control.ScrollPane;

public class SavedCVsController {

    @FXML private ListView<CVData> listView;
    private final CVDao dao = new CVDao();

    @FXML
    private void backToHome(ActionEvent event) throws Exception {
        Parent homeRoot = FXMLLoader.load(getClass().getResource("home-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(homeRoot));
        stage.show();
    }

    @FXML
    public void initialize() {
        loadList();

        // ❌ REMOVE double-click auto-open
        // Now clicking item ONLY selects it — does nothing else.
        listView.setOnMouseClicked(evt -> {
            if (evt.getButton() == MouseButton.PRIMARY) {
                // Select only — no action
            }
        });
    }

    private void loadList() {
        DBTaskExecutor.run(
                () -> dao.getAll(),
                list -> javafx.application.Platform.runLater(() -> listView.setItems(list)),
                err -> err.printStackTrace()
        );
    }
    public void onNewCV(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("create.fxml"));
            Parent root = loader.load();

            ScrollPane scroll = new ScrollPane();
            scroll.setContent(root);
            scroll.setFitToWidth(true);

            Scene scene = new Scene(scroll, 900, 700);

            // APPLY CSS HERE ✔
            scene.getStylesheets().add(getClass().getResource("create.css").toExternalForm());

            Stage stage = new Stage();
            stage.setTitle("New CV");
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();

        } catch (Exception e) { e.printStackTrace(); }
    }
    public void onEditSelected(ActionEvent event) {
        CVData sel = listView.getSelectionModel().getSelectedItem();
        if (sel == null) return;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("create.fxml"));
            Parent root = loader.load();

            CreateController ctrl = loader.getController();
            ctrl.loadCV(sel);

            ScrollPane scroll = new ScrollPane();
            scroll.setContent(root);
            scroll.setFitToWidth(true);

            Scene scene = new Scene(scroll, 900, 700);

            // APPLY CSS HERE ✔
            scene.getStylesheets().add(getClass().getResource("create.css").toExternalForm());

            Stage stage = new Stage();
            stage.setTitle("Edit CV");
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.show();

        } catch (Exception e) { e.printStackTrace(); }
    }



    // ✔ OPEN SELECTED → go to generate.fxml
    public void onOpenSelected(ActionEvent event) {
        CVData sel = listView.getSelectionModel().getSelectedItem();
        if (sel == null) return;

        openGenerateFor(sel, event.getSource());
    }

    private void openGenerateFor(CVData cv, Object source) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("generate.fxml"));
            Parent root = loader.load();

            GenerateController controller = loader.getController();
            controller.setCVDataWithId(cv);

            Stage stage;
            if (source instanceof Node) {
                stage = (Stage) ((Node) source).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } else {
                stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onDeleteSelected(ActionEvent event) {
        CVData sel = listView.getSelectionModel().getSelectedItem();
        if (sel == null) return;

        // Delete from database
        DBTaskExecutor.run(
                () -> {
                    dao.deleteById(sel.getId());
                    return null;
                },
                ok -> {
                    // Remove from ListView
                    listView.getItems().remove(sel);
                },
                err -> err.printStackTrace()
        );
    }

}
