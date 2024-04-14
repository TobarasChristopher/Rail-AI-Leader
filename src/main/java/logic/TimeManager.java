package logic;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeManager {
    // Singleton instance
    private static TimeManager instance;

    // Private constructor to prevent instantiation
    private TimeManager() {
        // Optional: Initialize any necessary variables or settings
    }

    // Static method to get the instance of TimeProvider
    public static synchronized TimeManager getInstance() {
        if (instance == null) {
            instance = new TimeManager();
        }
        return instance;
    }

    // Method to get the current time as a LocalTime object
    public LocalTime getCurrentTime() {
        return LocalTime.now();
    }

    // Method to get the current time as a constantly updating 24-hour digital format string
    public String getCurrentTimeString() {
        return LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}
