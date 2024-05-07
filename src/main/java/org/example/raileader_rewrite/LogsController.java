package org.example.raileader_rewrite;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import logic.ExceptionHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.Alert;


import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogsController {
    @FXML
    private Button closeButton;

    @FXML
    private TableView<String> logTable;

    @FXML
    private TableColumn<String, String> logColumn;

    @FXML
    private Button exportButton;

    @FXML
    protected void initialize() {
        // Initialize table columns
        logColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));

        // Populate table with exception log entries
        populateLogTable();
    }

    @FXML
    protected void goToMenu() throws IOException {
        // get a handle to the stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    private void populateLogTable() {
        // Get the exception handler instance
        ExceptionHandler exceptionHandler = ExceptionHandler.getInstance();

        // Get the exception log
        ArrayList<String> exceptionLog = exceptionHandler.getExceptionLog();

        // Populate the table with log entries
        for (String logEntry : exceptionLog) {
            logTable.getItems().add(logEntry);
        }
    }

    @FXML
    protected void exportToCSV(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File file = fileChooser.showSaveDialog(exportButton.getScene().getWindow());

        if (file != null) {
            try (FileWriter writer = new FileWriter(file)) {
                ObservableList<String> items = logTable.getItems();
                for (String item : items) {
                    writer.write(item + "\n");
                }
                showAlert("Export Successful", "Data exported to " + file.getAbsolutePath(), Alert.AlertType.INFORMATION);
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Export Error", "Error occurred while exporting data", Alert.AlertType.ERROR);
            }
        }
    }

    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void exportToCSV(javafx.event.ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File file = fileChooser.showSaveDialog(exportButton.getScene().getWindow());

        if (file != null) {
            try (FileWriter writer = new FileWriter(file)) {
                ObservableList<String> items = logTable.getItems();
                for (String item : items) {
                    writer.write(item + "\n");
                }
                showAlert("Export Successful", "Data exported to " + file.getAbsolutePath(), Alert.AlertType.INFORMATION);
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Export Error", "Error occurred while exporting data", Alert.AlertType.ERROR);
            }
        }
    }
}