package logic;

import nodefactory.Train;

import java.util.ArrayList;

import java.util.List;

public class ScheduleManager {
    private static ScheduleManager instance;
    private List<Train> trainData;

    private List<String[]> rawData;

    private ScheduleManager() {
        trainData = new ArrayList<>();
    }

    public static synchronized ScheduleManager getInstance() {
        if (instance == null) {
            instance = new ScheduleManager();
        }
        return instance;
    }



    public void setRawData(List<String[]> rawData) {
        this.rawData = rawData;
    }



    public List<String[]> getRawData() {
        List<String[]> rawSentData;
        rawSentData = rawData;
        rawData = null;

        return rawSentData;
    }

}