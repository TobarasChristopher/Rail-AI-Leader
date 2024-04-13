package org.example.raileader_rewrite;

import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import javafx.fxml.Initializable;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;

public class LayoutCreator {


    public mxGraph graph = new mxGraph();
    public Object parent = graph.getDefaultParent();

    public List<Train> trains = new ArrayList<>();


    public mxGraph initLayout() {


        graph.getModel().beginUpdate();
        try {


            Object v1 = graph.insertVertex(parent, "1", "1", 20, 20, 80, 30,"fillColor=orange");
            Object v2 = graph.insertVertex(parent, "2", "2", 20, 20, 80, 30);
            Object v3 = graph.insertVertex(parent, "3", "3", 20, 20, 80, 30);
            Object v4 = graph.insertVertex(parent, "4", "4", 20, 20, 80, 30);
            Object v5 = graph.insertVertex(parent, "5", "5", 500, 250, 80, 30,"fillColor=orange");
            Object v6 = graph.insertVertex(parent, "6", "6", 500, 250, 80, 30,"fillColor=orange");
            Object v7 = graph.insertVertex(parent, "7", "7", 20, 20, 80, 30);
            Object v8 = graph.insertVertex(parent, "8", "8", 20, 20, 80, 30);
            Object v9 = graph.insertVertex(parent, "9", "9", 20, 20, 80, 30);
            Object v10 = graph.insertVertex(parent, "10", "10", 20, 20, 80, 30);
            Object v11 = graph.insertVertex(parent, "11", "11", 20, 20, 80, 30);
            Object v12 = graph.insertVertex(parent, "12", "12", 20, 20, 80, 30);
            Object v13 = graph.insertVertex(parent, "13", "13", 20, 20, 80, 30);
            Object v14 = graph.insertVertex(parent, "14", "14", 20, 20, 80, 30);
            Object v15 = graph.insertVertex(parent, "15", "15", 20, 20, 80, 30);
            Object v16 = graph.insertVertex(parent, "16", "16", 20, 20, 80, 30);
            Object v17 = graph.insertVertex(parent, "17", "17", 20, 20, 80, 30);
            Object v18 = graph.insertVertex(parent, "18", "18", 20, 20, 80, 30);
            Object v19 = graph.insertVertex(parent, "19", "19", 20, 20, 80, 30);
            Object v20 = graph.insertVertex(parent, "20", "20", 20, 20, 80, 30);
            Object v21 = graph.insertVertex(parent, "21", "21", 20, 20, 80, 30);



            Train train1 = new Train("Train01",v1,v10 );
            trains.add(train1);

            Train train2 = new Train("Train02",v11,v20 );
            trains.add(train2);


            Object cell = graph.getModel().setValue(train1.getPointOfOrigin(), train1.getName());


            System.out.println(v1);


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
