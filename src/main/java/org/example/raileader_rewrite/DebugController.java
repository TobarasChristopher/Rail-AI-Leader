package org.example.raileader_rewrite;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import logic.PerformanceHandler;
import logic.TimeManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DebugController {
    @FXML
    private Button closeButton;
    @FXML
    private Button exportButton;
    @FXML
    private Label clock;
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

    List<String[]> PerformanceData = new ArrayList<>();
    TimeManager timeManager = TimeManager.getInstance();
    PerformanceHandler performanceHandler = PerformanceHandler.getInstance();

    @FXML
    protected void initialize() {
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
        startClock();
        // Set up the TableView
        setUpTableView();
    }
    @FXML
    protected void goToMenu(javafx.event.ActionEvent event) throws IOException {
        // get a handle to the stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    private void startClock() {
        // Create a timeline to update the clock label every second
        Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.seconds(1), event -> {
            clock.setText(timeManager.getCurrentTimeString());
        }));
        timeline.setCycleCount(Animation.INDEFINITE); // Run indefinitely
        timeline.play(); // Start the timeline
    }



    private void setUpTableView() {
        PerformanceData = performanceHandler.getPerformanceData();
        if (!(PerformanceData == null)){
            tableView.getItems().addAll(PerformanceData);
        }

    }
    @FXML
    protected void exportToCSV() {
        // Create a FileChooser dialog to choose where to save the CSV file
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export results to CSV");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File file = fileChooser.showSaveDialog(new Stage());

        if (file != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                // Write column headers
                writer.write("Train Name,Expected Time,Actual Time,Time Difference(S),Path Duration(ns)\n");

                // Write data from TableView to CSV file
                for (String[] rowData : tableView.getItems()) {
                    for (int i = 0; i < rowData.length; i++) {
                        writer.write("\"" + rowData[i] + "\"");
                        if (i < rowData.length - 1) {
                            writer.write(",");
                        }
                    }
                    writer.write("\n");
                }

                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
