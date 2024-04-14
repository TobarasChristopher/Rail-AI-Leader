package org.example.raileader_rewrite;


import com.mxgraph.view.mxGraph;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import com.mxgraph.layout.*;
import com.mxgraph.swing.*;
import com.mxgraph.util.*;
import logic.TimeManager;
import logic.TrainScheduler;
import nodefactory.Train;
import org.jgrapht.*;
import org.jgrapht.graph.*;
import javafx.embed.swing.SwingNode;
import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.stage.Stage;


public class GuiController implements Initializable{
    @FXML
    private StackPane graphPane;
    private mxGraph graph;
    @FXML
    private Label clock;
    LayoutCreator layoutCreator = new LayoutCreator();

    TimeManager timeManager = TimeManager.getInstance();

    public List<Train> trains;
    TrainScheduler trainScheduler = new TrainScheduler(graph, trains);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SwingNode swingNode = new SwingNode();
        graph = layoutCreator.initLayout();

        graph.setDropEnabled(false);
        mxGraphComponent graphComponent = new mxGraphComponent(graph);

        swingNode.setContent(graphComponent);

        graphPane.getChildren().add(swingNode);

        startClock();



    }

    private void startClock(){
        // Create a timeline to update the clock label every second
        Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.seconds(1), event -> {
            clock.setText(timeManager.getCurrentTimeString());
        }));
        timeline.setCycleCount(Animation.INDEFINITE); // Run indefinitely
        timeline.play(); // Start the timeline
    }




    @FXML
    protected void goToSchedule(ActionEvent event) throws IOException {
        openWindow("/org/example/raileader_rewrite/schedule-view.fxml");
    }
    @FXML
    protected void goToDebug(ActionEvent event) throws IOException {
        openWindow("/org/example/raileader_rewrite/debug-view.fxml");
    }
    @FXML
    protected void goToSettings(ActionEvent event) throws IOException {
        openWindow("/org/example/raileader_rewrite/setting-view.fxml");
    }
    @FXML
    protected void goToManualControl(ActionEvent event) throws IOException {
        openWindow("/org/example/raileader_rewrite/manual-view.fxml");
    }
    @FXML
    protected void goToLogs(ActionEvent event) throws IOException {
        openWindow("/org/example/raileader_rewrite/log-view.fxml");
    }
    private void openWindow(String fxmlPath) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = fxmlLoader.load();
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void start(ActionEvent event) throws IOException {
        layoutCreator.start();
        System.out.println("Start Pressed!");
    }
    @FXML
    protected void removeNode(ActionEvent event) throws IOException {
/*        graph.getModel().beginUpdate();

        try {
            Object[] selection = graph.getSelectionCells();
            for (Object cell : selection) {
                System.out.println(cell);
                graph.getModel().remove(selection);
            }
        } finally {
            graph.getModel().endUpdate();
        }*/
    }
}

