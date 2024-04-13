package org.example.raileader_rewrite;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ScheduleView {
        @FXML
        private Button closeButton;

        @FXML
        protected void goToMenu(ActionEvent event) throws IOException {
                // get a handle to the stage
                Stage stage = (Stage) closeButton.getScene().getWindow();
                // do what you have to do
                stage.close();
        }
}
