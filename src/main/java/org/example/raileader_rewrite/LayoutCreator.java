package org.example.raileader_rewrite;

import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import javafx.fxml.Initializable;
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
    private List<Object> vertexList = new ArrayList<>();
    public List<Train> trains = new ArrayList<>();

    public List<Train> storedTrains = new ArrayList<>();

    private Timer timer;

    public LayoutCreator(){

        timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                checkTimeAndAddTrains();
            }
        }, 0, 1000);

        graph.getModel().beginUpdate();
        try {


            Object v1 = createVertex("1", "1", 20, 20, 80, 30,"fillColor=orange");
            Object v2 = createVertex("2", "2", 20, 20, 80, 30,"");
            Object v3 = createVertex("3", "3", 20, 20, 80, 30,"");
            Object v4 = createVertex("4", "4", 20, 20, 80, 30,"");
            Object v5 = createVertex("5", "5", 500, 250, 80, 30,"fillColor=orange");
            Object v6 = createVertex("6", "6", 500, 250, 80, 30,"fillColor=orange");
            Object v7 = createVertex("7", "7", 20, 20, 80, 30,"");
            Object v8 = createVertex("8", "8", 20, 20, 80, 30,"");
            Object v9 = createVertex("9", "9", 20, 20, 80, 30,"");
            Object v10 = createVertex("10", "10", 20, 20, 80, 30,"");
            Object v11 = createVertex("11", "11", 20, 20, 80, 30,"");
            Object v12 = createVertex("12", "12", 20, 20, 80, 30,"");
            Object v13 = createVertex("13", "13", 20, 20, 80, 30,"");
            Object v14 = createVertex("14", "14", 20, 20, 80, 30,"");
            Object v15 = createVertex("15", "15", 20, 20, 80, 30,"");
            Object v16 = createVertex("16", "16", 20, 20, 80, 30,"");
            Object v17 = createVertex("17", "17", 20, 20, 80, 30,"");
            Object v18 = createVertex("18", "18", 20, 20, 80, 30,"");
            Object v19 = createVertex("19", "19", 20, 20, 80, 30,"");
            Object v20 = createVertex("20", "20", 20, 20, 80, 30,"");
            Object v21 = createVertex("21", "21", 20, 20, 80, 30,"");


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



        } finally {
            graph.getModel().endUpdate();
        }

        mxIGraphLayout layout = new mxHierarchicalLayout(graph);
        ((mxHierarchicalLayout) layout).setOrientation(SwingConstants.WEST);
        layout.execute(parent);
    }

    private void checkTimeAndAddTrains() {
        String currentTime = timeManager.getCurrentTimeString();
        if(!storedTrains.isEmpty()) {
            for (Train train : storedTrains) {
                if (train.getScheduledTime().equals(currentTime)) {
                    // Add the train to the graph
                    Object cell = graph.getModel().setValue(train.getPointOfOrigin(), train.getName());
                    trains.add(train);
                    storedTrains.remove(train);


                }
            }
        } else {
            System.out.println("No trains to sort!");
        }
    }

    private Object createVertex(String id, String label, int x, int y, int width, int height, String style) {
        Object vertex = graph.insertVertex(parent, id, label, x, y, width, height, style);
        vertexList.add(vertex); // Store the vertex object with its ID
        return vertex;
    }

    public Object getVertexById(Object vertex) {
        return vertexList;
    }

    public mxGraph initLayout() {



        Train train1 = new Train("Train01",vertexList.get(0),vertexList.get(19),"16:53:30", "12:00:05");
        storedTrains.add(train1); //add to arraylist

        Train train2 = new Train("Train02",vertexList.get(10),vertexList.get(9),"16:53:35", "12:00:05");
        storedTrains.add(train2);


        //graph.getModel().setValue(train1.getPointOfOrigin(), train1.getName());
        //graph.getModel().setValue(train2.getPointOfOrigin(), train2.getName());

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

    public void start(){
        for (Train train : trains){

            boolean pathFound = findPath(train);

            if(pathFound == true){
                Object cell = graph.getModel().setValue(train.getDestination(), train.getName());
                Object cell1 = graph.getModel().setValue(train.getPointOfOrigin(), "");
            }else{
                System.out.println("Train could not be moved");
            }


        }

    }



    public boolean findPath(Train train) {
        Object origin = train.getPointOfOrigin();
        Object destination = train.getDestination();
        boolean pathFound = false;
        Object currentCell = origin;


        List<Object> pathCells = new ArrayList<>();
        Stack<Object> nodeList = new Stack<>();
        pathCells.add(origin);



        while (currentCell != null && !currentCell.equals(destination)) {   //while the current node isnt destination or empty
            Object[] outgoingEdges = graph.getOutgoingEdges(currentCell);   //grab connections

            if (outgoingEdges != null && outgoingEdges.length > 0 ) {       //if there are connections

                for (Object edge : outgoingEdges) {                           //for each connection
                    System.out.println(edge);                               //add to nodeList
                    nodeList.push(edge);
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

            } else {
                System.out.println("Error! No target cell found? How does this even happen");
                break;

            }





/*            else {
                System.out.println("Error! Path Could not be found!");
                break;

            }*/
        }

        if(currentCell.equals(destination)) {   //while the current node isnt destination or empty
            System.out.println("Path found!");
            pathFound = true;
            return pathFound;
        }


        System.out.println("Error! Path Could not be found!");
        return pathFound;




        /*for (Object cell : pathCells) {
            // Highlight each cell individually
            setCellValue(cell, train.getName());
            highlightPath(cell);
            System.out.println("Sending Highlight Method");

            // Add some delay to better visualize the highlighting process
            //Thread.sleep(1000);

        }
        pathFound = true;
        return pathFound;*/
    }

    public void highlightPath(Object cell) {
        graph.getModel().beginUpdate();
        try {
            if (cell instanceof mxCell && ((mxCell) cell).isVertex()) {
                System.out.println("Coloring :"+cell );
                ((mxCell) cell).setStyle("fillColor=red"); // Highlighting vertex
                System.out.println("Set style to yellow!");
            }
        } finally {
            graph.getModel().endUpdate();
        }
    }








//BREAKPOINT





}
