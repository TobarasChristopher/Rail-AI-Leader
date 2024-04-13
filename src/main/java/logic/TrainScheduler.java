package logic;

import com.mxgraph.model.mxCell;
import com.mxgraph.view.mxGraph;
import nodefactory.Train;
import org.example.raileader_rewrite.LayoutCreator;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.ArrayList;
import java.util.List;

public class TrainScheduler {
    public mxGraph graph;

    public List<Train> trains = new ArrayList<>();

    public TrainScheduler(mxGraph graph, List<Train> trains) {
        this.graph = graph;
        this.trains = trains;
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

            List<Object> pathCells = findPath(train);
            System.out.println(pathCells);

            for (Object cell : pathCells) {
                // Highlight each cell individually
                setCellValue(cell, train.getName());
                highlightPath(cell);
                System.out.println("Sending Highlight Method");

                // Add some delay to better visualize the highlighting process
                //Thread.sleep(1000);

            }






            //Move to Position, Remove to Position
            Object cell = graph.getModel().setValue(train.getDestination(), train.getName());
            Object cell1 = graph.getModel().setValue(train.getPointOfOrigin(), "");

        }

    }



    public List<Object> findPath(Train train) {
        Object origin = train.getPointOfOrigin();
        Object destination = train.getDestination();

        List<Object> pathCells = new ArrayList<>();
        pathCells.add(origin);

        // Start traversing from the origin
        Object currentCell = origin;
        while (currentCell != null && !currentCell.equals(destination)) {
            Object[] outgoingEdges = graph.getOutgoingEdges(currentCell);
            if (outgoingEdges != null && outgoingEdges.length > 0) {
                // Just take the first outgoing edge for simplicity
                Object nextEdge = outgoingEdges[0];
                Object targetCell = graph.getModel().getTerminal(nextEdge, false);
                if (targetCell != null) {
                    //pathCells.add(nextEdge); // Add the edge to pathCells
                    pathCells.add(targetCell); // Add the target vertex to pathCells
                    currentCell = targetCell;
                } else {
                    // No target cell found for the edge
                    break;
                }
            } else {
                System.out.println("Error! Path Could not be found!");
                break;
            }
        }

        return pathCells;
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


}
