package org.example.raileader_rewrite;

import javafx.animation.PauseTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import logic.ScheduleManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScheduleView {
        @FXML
        private Button closeButton;
        @FXML
        private Button confirmButton;
        @FXML
        private TableView<String[]> tableView;
        @FXML
        private TableColumn<String[], String> col1;
        @FXML
        private TableColumn<String[], String> col2;
        @FXML
        private TableColumn<String[], String> col3;
        @FXML
        private TableColumn<String[], String> col4;
        @FXML
        private TableColumn<String[], String> col5;
        List<String[]> trainData = new ArrayList<>();
        ScheduleManager scheduleManager = ScheduleManager.getInstance();

        boolean confirmation = false;


        @FXML
        protected void initialize() {

                confirmation = false;
                // Initialize TableColumn cell value factories
                col1.setCellValueFactory(cellData -> {
                        if (cellData.getValue().length > 0) {
                                return new SimpleStringProperty(cellData.getValue()[0]);
                        } else {
                                return new SimpleStringProperty("");
                        }
                });
                col2.setCellValueFactory(cellData -> {
                        if (cellData.getValue().length > 1) {
                                return new SimpleStringProperty(cellData.getValue()[1]);
                        } else {
                                return new SimpleStringProperty("");
                        }
                });
                col3.setCellValueFactory(cellData -> {
                        if (cellData.getValue().length > 2) {
                                return new SimpleStringProperty(cellData.getValue()[2]);
                        } else {
                                return new SimpleStringProperty("");
                        }
                });
                col4.setCellValueFactory(cellData -> {
                        if (cellData.getValue().length > 3) {
                                return new SimpleStringProperty(cellData.getValue()[3]);
                        } else {
                                return new SimpleStringProperty("");
                        }
                });
                col5.setCellValueFactory(cellData -> {
                        if (cellData.getValue().length > 4) {
                                return new SimpleStringProperty(cellData.getValue()[4]);
                        } else {
                                return new SimpleStringProperty("");
                        }
                });
        }


        @FXML
        protected void goToMenu(ActionEvent event) throws IOException {
                // get a handle to the stage
                Stage stage = (Stage) closeButton.getScene().getWindow();
                // do what you have to do
                stage.close();
        }


        @FXML
        protected void uploadCSV(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Upload CSV File");
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
                File selectedFile = fileChooser.showOpenDialog(null);
                if (selectedFile != null) {
                        // Process the selected CSV file
                        try {
                                List<String[]> data = readCSV(selectedFile);
                                tableView.getItems().addAll(data);
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                }
        }
        private List<String[]> readCSV(File file) throws IOException {

                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                                // Assuming CSV fields are comma-separated
                                String[] values = line.split(",");
                                trainData.add(values);

                        }
                }
                return trainData;
        }
        @FXML
        private void confirm(ActionEvent event) {
                scheduleManager.setRawData(trainData);

                // get a handle to the stage
                Stage stage = (Stage) closeButton.getScene().getWindow();
                // do what you have to do
                stage.close();

        }




}
