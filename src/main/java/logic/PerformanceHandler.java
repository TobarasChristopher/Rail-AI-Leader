package logic;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import nodefactory.Train;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
//Singleton instance: https://www.geeksforgeeks.org/singleton-class-java/
public class PerformanceHandler {
    private static PerformanceHandler instance;
    private List<String[]> PerformanceData;
    TimeManager timeManager = TimeManager.getInstance();



    private PerformanceHandler() {
        PerformanceData = new ArrayList<>();
    }

    // Singleton getInstance method
    public static synchronized PerformanceHandler getInstance() {
        if (instance == null) {
            instance = new PerformanceHandler();
        }
        return instance;
    }

    // Method to handle exceptions
    public void addNewTrain(Train train, String currentTime, long duration) {

        String trainName = train.getName();
        LocalTime expectedTime = LocalTime.parse(train.getArrivalTime());
        LocalTime actualTime = LocalTime.parse(currentTime);
        String diffInSeconds = String.valueOf(ChronoUnit.SECONDS.between(expectedTime, actualTime));



        //find difference, then post both duration and arrival time
        String[] entry = {trainName, train.getArrivalTime(), currentTime, diffInSeconds, String.valueOf(duration)};
        PerformanceData.add(entry);

    }

    public List<String[]> getPerformanceData() {
        return PerformanceData;
    }
}


