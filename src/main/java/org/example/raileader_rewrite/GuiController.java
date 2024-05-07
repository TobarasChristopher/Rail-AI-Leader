package org.example.raileader_rewrite;


import com.mxgraph.view.mxGraph;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import logic.TimeManager;
import javafx.embed.swing.SwingNode;
import javafx.scene.layout.StackPane;
import com.mxgraph.swing.mxGraphComponent;

import java.net.URL;
import java.util.ResourceBundle;


public class GuiController implements Initializable{
    @FXML
    private StackPane graphPane;
    private mxGraph graph;
    @FXML
    private Label clock;
    LayoutCreator layoutCreator = new LayoutCreator();

    TimeManager timeManager = TimeManager.getInstance();

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
        layoutCreator.Start();
    }
    @FXML
    public void Stop(ActionEvent event) throws IOException {
        layoutCreator.Stop();
    }

}

