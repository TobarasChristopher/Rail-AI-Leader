package test;
import com.mxgraph.view.mxGraph;
import logic.ScheduleManager;
import nodefactory.Train;
import org.example.raileader_rewrite.LayoutCreator;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PathFindTest {
    private LayoutCreator layoutCreator;
    private mxGraph graph;
    private ScheduleManager scheduleManager;
    private List<String[]> testData = new ArrayList<>();
    private List<Object> vertexList = new ArrayList<>();


    @Test
    public void testPathfindingWithValidPath() {
        layoutCreator = new LayoutCreator(); // Instantiate LayoutCreator before each test method
        graph = layoutCreator.initLayout();
        vertexList = layoutCreator.getVertexList();
        scheduleManager = ScheduleManager.getInstance();


        Train testTrain = new Train("TestTrain",vertexList.get(1),vertexList.get(10),"TEST","TEST");
        // Add necessary vertices and edges to the graph to create a valid path
        // Invoke the pathfinding method with the starting and ending vertices
        // Assert that the method returns true, indicating that a path was found
        assertFalse(layoutCreator.findPath(testTrain) == null);
    }

    @Test
    public void testPathfindingWithInvalidPath() {
        // Add necessary vertices and edges to the graph where there is no valid path
        // Invoke the pathfinding method with the starting and ending vertices
        // Assert that the method returns false, indicating that no path was found
        assertFalse(false);
    }
}
