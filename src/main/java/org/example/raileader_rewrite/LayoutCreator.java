package org.example.raileader_rewrite;

import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import javafx.fxml.Initializable;
import logic.ScheduleManager;
import logic.TimeManager;
import logic.TrainScheduler;
import nodefactory.Node;
import nodefactory.Train;
import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.Timer;

public class LayoutCreator {


    public mxGraph graph = new mxGraph();
    public Object parent = graph.getDefaultParent();
    TimeManager timeManager = TimeManager.getInstance();
    public List<Object> vertexList = new ArrayList<>();

    public List<Train> trains = new ArrayList<>();
    public List<Train> storedTrains = new ArrayList<>();
    public List<Train> sortedTrains = new ArrayList<>();

    private Timer timer;
    private boolean start;
    ScheduleManager scheduleManager = ScheduleManager.getInstance();
    private static final int PATH_CHECK_DELAY = 1000; // 1 second
    private static final int MOVE_DELAY = 10000; // 10 seconds


    public LayoutCreator(){

        timer = new Timer();

        start = false;

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {



                ImportTrains();
                CheckStoredTrains();
                if(start){
                    MoveTrain();
                }
            }
        }, 0, 1000);

        graph.getModel().beginUpdate();
        try {

            Object v0 = createVertex("0", "0", 20, 20, 80, 30,"fillColor=orange");



            Object v1 = createVertex("1", "1", 20, 20, 80, 30,"fillColor=orange");
            Object v2 = createVertex("2", "2", 20, 20, 80, 30,"fillColor=lightblue");
            Object v3 = createVertex("3", "3", 20, 20, 80, 30,"fillColor=lightblue");
            Object v4 = createVertex("4", "4", 20, 20, 80, 30,"fillColor=lightblue");
            Object v5 = createVertex("5", "5", 500, 250, 80, 30,"fillColor=lightblue");
            Object v6 = createVertex("6", "6", 500, 250, 80, 30,"fillColor=lightblue");
            Object v7 = createVertex("7", "7", 20, 20, 80, 30,"fillColor=lightblue");
            Object v8 = createVertex("8", "8", 20, 20, 80, 30,"fillColor=lightblue");
            Object v9 = createVertex("9", "9", 20, 20, 80, 30,"fillColor=lightblue");
            Object v10 = createVertex("10", "10", 20, 20, 80, 30,"fillColor=orange");
            Object v11 = createVertex("11", "11", 20, 20, 80, 30,"fillColor=orange");
            Object v12 = createVertex("12", "12", 20, 20, 80, 30,"fillColor=lightblue");
            Object v13 = createVertex("13", "13", 20, 20, 80, 30,"fillColor=lightblue");
            Object v14 = createVertex("14", "14", 20, 20, 80, 30,"fillColor=lightblue");
            Object v15 = createVertex("15", "15", 20, 20, 80, 30,"fillColor=lightblue");
            Object v16 = createVertex("16", "16", 20, 20, 80, 30,"fillColor=lightblue");
            Object v17 = createVertex("17", "17", 20, 20, 80, 30,"fillColor=lightblue");
            Object v18 = createVertex("18", "18", 20, 20, 80, 30,"fillColor=lightblue");
            Object v19 = createVertex("19", "19", 20, 20, 80, 30,"fillColor=lightblue");
            Object v20 = createVertex("20", "20", 20, 20, 80, 30,"fillColor=orange");

            Object v21 = createVertex("21", "21", 20, 20, 80, 30,"fillColor=lightblue");
            Object v22 = createVertex("22", "22", 20, 20, 80, 30,"fillColor=lightblue");
            Object v23 = createVertex("23", "23", 20, 20, 80, 30,"fillColor=lightblue");
            Object v24 = createVertex("24", "24", 20, 20, 80, 30,"fillColor=orange");





            graph.insertEdge(parent, null, "", v1, v2);
            graph.insertEdge(parent, null, "", v2, v3);
            graph.insertEdge(parent, null, "", v3, v4);
            graph.insertEdge(parent, null, "", v4, v5);
            graph.insertEdge(parent, null, "", v5, v6);
            graph.insertEdge(parent, null, "", v6, v7);
            graph.insertEdge(parent, null, "", v7, v8);
            graph.insertEdge(parent, null, "", v8, v9);
            graph.insertEdge(parent, null, "", v9, v10);
            graph.insertEdge(parent, null, "", v11, v12);
            graph.insertEdge(parent, null, "", v12, v13);
            graph.insertEdge(parent, null, "", v13, v14);
            graph.insertEdge(parent, null, "", v14, v15);
            graph.insertEdge(parent, null, "", v15, v16);
            graph.insertEdge(parent, null, "", v16, v17);
            graph.insertEdge(parent, null, "", v17, v18);
            graph.insertEdge(parent, null, "", v18, v19);
            graph.insertEdge(parent, null, "", v19, v20);

            graph.insertEdge(parent, null, "", v14, v5);
            graph.insertEdge(parent, null, "", v4, v15);

            graph.insertEdge(parent, null, "", v16, v21);
            graph.insertEdge(parent, null, "", v21, v22);
            graph.insertEdge(parent, null, "", v22, v23);
            graph.insertEdge(parent, null, "", v23, v24);






        } finally {
            graph.getModel().endUpdate();
        }

        mxIGraphLayout layout = new mxHierarchicalLayout(graph);
        ((mxHierarchicalLayout) layout).setOrientation(SwingConstants.WEST);
        layout.execute(parent);
    }
    private Object createVertex(String id, String label, int x, int y, int width, int height, String style) {
        Object vertex = graph.insertVertex(parent, id, label, x, y, width, height, style);
        vertexList.add(vertex); // Store the vertex object with its ID
        return vertex;
    }
    public List<Object> getVertexList(){
        return vertexList;
    }

    private void ImportTrains(){
        List<String[]> rawData = scheduleManager.getRawData();

        if (rawData != null && !rawData.isEmpty()) {
            for (String[] row : rawData) {
                String name = row[0];
                Integer pointOfOrigin = Integer.valueOf(row[1]);
                Integer destination = Integer.valueOf(row[2]);
                String scheduledTime = row[3];
                String arrivalTime = row[4];

                Train train = new Train(name, vertexList.get(pointOfOrigin), vertexList.get(destination), scheduledTime, arrivalTime);
                System.out.println(train);
                storedTrains.add(train);
            }
        }
    }
    private void CheckStoredTrains() {

        String currentTime = timeManager.getCurrentTimeString();
        if (!storedTrains.isEmpty()) {
            Iterator<Train> iterator = storedTrains.iterator();
            while (iterator.hasNext()) {
                Train train = iterator.next();
                if (train.getScheduledTime().equals(currentTime) || train.getScheduledTime().equals("TEST")) {
                    AddTrains(train);
                    iterator.remove();
                }
            }

        }
    }

    private void AddTrains(Train train){
        Object origin = train.getPointOfOrigin();
        Object cell = graph.getModel().setValue(origin, train.getName());
        trains.add(train);
        getValueStyle(origin);
    }

    private void getValueStyle(Object Vertex){

        System.out.println(graph.getModel().getStyle(Vertex));
        System.out.println();
    }


    public mxGraph initLayout() {

        return graph;
    }

    public void setCellValue(Object cell, String value) {
        graph.getModel().beginUpdate();
        try {
            if (cell instanceof mxCell) {
                graph.getModel().setValue(cell, value);
            }
        } finally {
            graph.getModel().endUpdate();
        }
    }

    public void Start(){
        start = true;
    }
    public void Stop(){
        start = false;
    }

    public void MoveTrain() {
        System.out.println("Running!");

        for (Iterator<Train> iterator = trains.iterator(); iterator.hasNext();) {
            Train train = iterator.next();

            List<Object> activePath = findPath(train);

            try {
                Thread.sleep(PATH_CHECK_DELAY);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            if (!activePath.isEmpty()) {
                highlightPath(activePath);
                try {
                    Thread.sleep(MOVE_DELAY);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                Object cell = graph.getModel().setValue(train.getDestination(), train.getName());
                Object cell1 = graph.getModel().setValue(train.getPointOfOrigin(), "");

                sortedTrains.add(train); // Add the train to the sorted list
                iterator.remove(); // Remove the train from the original list
            } else {
                System.out.println("Train " + train.getName() + " could not be moved");
            }
        }

        // Now sortedTrains contains the trains that were successfully moved
        // You can use sortedTrains as needed
    }



    public List<Object> findPath(Train train) {
        Object origin = train.getPointOfOrigin();
        Object destination = train.getDestination();
        boolean pathFound = false;
        Object currentCell = origin;
        List<Object> activePath = new ArrayList<>();
        activePath.clear();
        activePath.add(origin);


        List<Object> pathCells = new ArrayList<>();
        Stack<Object> nodeList = new Stack<>();
        Stack<Integer> backTrack = new Stack<>();
        pathCells.add(origin);



        while (currentCell != null && !currentCell.equals(destination)) {   //while the current node isnt destination or empty

            Object[] outgoingEdges = graph.getOutgoingEdges(currentCell);   //grab connections



            if (outgoingEdges != null && outgoingEdges.length > 0 ) {       //if there are connections

                if (outgoingEdges.length > 1){
                    //System.out.println("Junction Detected!");
                    //System.out.println("junction at: "+activePath.size());
                    backTrack.push(activePath.size());
                }

                for (Object edge : outgoingEdges) {                           //for each connection
                    //System.out.println(edge);                               //add to nodeList
                    nodeList.push(edge);
                }
            } else {

                //System.out.println("Dead end! Backtracking");
                //System.out.println("current pos" +activePath.size());

                int lastJunction = backTrack.pop();
                int size = activePath.size();
                //int difference = size - lastJunction;

                        //System.out.println("backtracking by "+difference);

                for(int i = lastJunction; i < size; i++){

                    Object removeItem = activePath.getLast();
                    activePath.remove(removeItem);
                }



            }

            if (nodeList.isEmpty()) {
                break;
            }

            Object nextEdge = nodeList.pop();                                       //pop the most recent
            Object targetCell = graph.getModel().getTerminal(nextEdge, false);  //find the target cell


            if (targetCell != null) {                                               //if target aint empty
                //pathCells.add(nextEdge); // Add the edge to pathCells             //add to list
                pathCells.add(targetCell); // Add the target vertex to pathCells
                currentCell = targetCell;                                           //make current cell
                activePath.add(currentCell);

            } else {
                System.out.println("Error! No target cell found? How does this even happen");
                break;

            }

        }

        if(currentCell.equals(destination)) {
            //System.out.println("Path found!");


            pathFound = true;
            return activePath;
        }


        System.out.println("Error! Path Could not be found!");
        activePath.clear();
        return activePath;


    }

    public void highlightPath(List<Object> activePath ) {
        for (Object cell : activePath) {

            graph.getModel().beginUpdate();
            try {
                if (cell instanceof mxCell && ((mxCell) cell).isVertex()) {
                    //System.out.println("Coloring :" + cell);
                    Object style = graph.getModel().setStyle(cell,"fillColor=red");

                }
            } finally {
                graph.getModel().endUpdate();
            }
        }
    }








//BREAKPOINT





}
