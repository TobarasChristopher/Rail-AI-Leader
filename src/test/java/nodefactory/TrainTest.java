package nodefactory;

import org.example.raileader_rewrite.LayoutCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrainTest {

    private Train train;

    @BeforeEach
    void setUp() {
        train = new Train("TestTrain","C35ConEn","CNPlatform1","TEST","TEST");
    }

    //Unit tests
    @Test
    void getName() {
        assertEquals("TestTrain", train.getName());
    }

    @Test
    void getDestination() {
        assertEquals("CNPlatform1", train.getDestination());
    }

    @Test
    void getPointOfOrigin() {
        assertEquals("C35ConEn", train.getPointOfOrigin());
    }

    @Test
    void getScheduledTime() {
        assertEquals("TEST", train.getScheduledTime());
    }

    @Test
    void getArrivalTime() {
        assertEquals("TEST", train.getArrivalTime());
    }
}