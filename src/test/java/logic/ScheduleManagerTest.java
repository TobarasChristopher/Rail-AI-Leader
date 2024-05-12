package logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleManagerTest {

    public ScheduleManager scheduleManager = ScheduleManager.getInstance();
    List<String[]> trainData = new ArrayList<>();

    @BeforeEach
    void setUp(){
        trainData.add(new String[]{"Train01", "C35ConEn","CNPlatform1","TEST","TEST"});
        trainData.add(new String[]{"Train02", "C35ConEn","CNPlatform5","TEST","TEST"});
    }

    //Integration Test
    @Test
    void getRawData() {
        scheduleManager.setRawData(trainData);
        List<String[]> newData = new ArrayList<>();

        newData = scheduleManager.getRawData();
        assertFalse(newData.isEmpty(), "Data is received once");
        newData = scheduleManager.getRawData();
        assertNull(newData, "Data is null as it is already sent");
    }
}