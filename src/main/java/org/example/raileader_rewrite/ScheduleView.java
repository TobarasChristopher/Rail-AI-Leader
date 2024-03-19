package org.example.raileader_rewrite;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ScheduleView {
        @FXML
        private Label welcomeText;

        @FXML
        protected void onHelloButtonClick() {
            welcomeText.setText("Welcome to JavaFX Application!");
        }
}
