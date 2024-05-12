package org.example.raileader_rewrite;


import com.mxgraph.view.mxGraph;
import com.sun.jdi.connect.LaunchingConnector;
import nodefactory.Train;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class LayoutCreatorTest {


    private Train train;
    private LayoutCreator layoutCreator = new LayoutCreator();
    public mxGraph graph = new mxGraph();
    public HashMap<String, Object> vertexMap = layoutCreator.getVertexList();

    //Functional Tests
    @Test
    void findPath() {
        train = new Train("TestTrain",vertexMap.get("C35ConEn"),vertexMap.get("CNPlatform1"),"TEST","TEST");
        List<Object> path = layoutCreator.findPath(train);
        assertNotNull(path, "The path returned must exist");
        assertFalse(path.isEmpty(), "The path returned must have entries");
    }
    @Test
    void findNoPath() {
        train = new Train("TestTrain",vertexMap.get("C35ConEn"),vertexMap.get("CN65Ex"),"TEST","TEST");
        List<Object> path = layoutCreator.findPath(train);
        assertNotNull(path, "The path returned must exist");
        assertTrue(path.isEmpty(), "The path returned must not contain anything");
    }

    @Test
    void checkForLock() {
        graph = layoutCreator.initLayout();
        graph.getModel().setStyle(vertexMap.get("CPlat34ConEnS"), "fillColor=red");
        train = new Train("TestTrain",vertexMap.get("C35ConEn"),vertexMap.get("CNPlatform1"),"TEST","TEST");
        List<Object> path = layoutCreator.findPath(train);
        boolean check = layoutCreator.checkForLock(path, "fillColor=red");
        assertTrue(check, "The path must be detected as occupied");
    }
    @Test
    void checkForNoLock() {
        train = new Train("TestTrain",vertexMap.get("C35ConEn"),vertexMap.get("CNPlatform1"),"TEST","TEST");
        List<Object> path = layoutCreator.findPath(train);
        boolean check = layoutCreator.checkForLock(path, "fillColor=red");
        assertFalse(check, "The path must be detected as free");
    }

    //System Tests
    @Test
    void PathHighlightTest() {
        boolean check = true;

        train = new Train("TestTrain",vertexMap.get("C35ConEn"),vertexMap.get("CNPlatform1"),"TEST","TEST");
        List<Object> path = layoutCreator.findPath(train);
        layoutCreator.highlightPath(path);
        for (Object cell : path) {
            if(!Objects.equals(graph.getModel().getStyle(cell), "fillColor=red")){
                //System.out.println("Red Lock Detected!");
                check = false;
            }
        }

        assertTrue(check, "Path must display red when path found");
    }
}