package org.example.raileader_rewrite;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import logic.TimeManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DebugController implements Initializable {
    @FXML
    private Button closeButton;

    @FXML
    private Label clock;

    TimeManager timeManager = TimeManager.getInstance();

    @FXML
    protected void goToMenu(ActionEvent event) throws IOException {
        // get a handle to the stage
        Stage stage = (Stage) closeButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    private void startClock(){
        // Create a timeline to update the clock label every second
        Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.seconds(1), event -> {
            clock.setText(timeManager.getCurrentTimeString());
        }));
        timeline.setCycleCount(Animation.INDEFINITE); // Run indefinitely
        timeline.play(); // Start the timeline
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startClock();
    }
}
