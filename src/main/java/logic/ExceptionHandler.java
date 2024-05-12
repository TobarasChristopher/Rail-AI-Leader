package logic;

import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.util.ArrayList;

public class ExceptionHandler {
    private static ExceptionHandler instance;
    private ArrayList<String> exceptionLog;
    TimeManager timeManager = TimeManager.getInstance();



    private ExceptionHandler() {
        exceptionLog = new ArrayList<>();
    }

    // Singleton getInstance method
    public static synchronized ExceptionHandler getInstance() {
        if (instance == null) {
            instance = new ExceptionHandler();
        }
        return instance;
    }

    // Method to handle exceptions
    public void handleException(Exception e, String title, String headerText) {
        // Log the exception or perform any necessary actions

        // Display an error message to the user
        logException(e, title, headerText);
        displayErrorAlert(title, headerText, e.getMessage());
    }
    private void logException(Exception e, String title, String header) {
        // Get current time
        String currentTime = timeManager.getCurrentTimeString();

        // Format exception details
        String exceptionDetails = currentTime + " - " + title + " - " + header + ": " + e.getMessage();

        // Add exception details to the log
        exceptionLog.add(exceptionDetails);
    }
    public void displayErrorAlert(String title, String headerText, String contentText) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(headerText);
            alert.setContentText(contentText);
            alert.showAndWait();
        });
    }

    public ArrayList<String> getExceptionLog() {
        return exceptionLog;
    }
}
