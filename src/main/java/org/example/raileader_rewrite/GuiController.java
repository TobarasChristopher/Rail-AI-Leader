package org.example.raileader_rewrite;

import com.mxgraph.view.mxGraph;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import com.mxgraph.layout.*;
import com.mxgraph.swing.*;
import com.mxgraph.util.*;
import org.jgrapht.*;
import org.jgrapht.graph.*;
import javafx.embed.swing.SwingNode;
import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.stage.Stage;


public class GuiController implements Initializable{
    @FXML
    private StackPane graphPane;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SwingNode swingNode = new SwingNode();
        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();

        graph.getModel().beginUpdate();
        try {
            Object v1 = graph.insertVertex(parent, null, "Hello", 20, 20, 80, 30);
            Object v2 = graph.insertVertex(parent, null, "World!", 240, 150, 80, 30);
            graph.insertEdge(parent, null, "Edge", v1, v2);
        } finally {
            graph.getModel().endUpdate();
        }
        graph.setDropEnabled(false);
        mxGraphComponent graphComponent = new mxGraphComponent(graph);

        swingNode.setContent(graphComponent);

        graphPane.getChildren().add(swingNode);


    }

    /*protected Graph<String, DefaultEdge> graphLayout;

    protected mxGraphComponent graphComponent;

    public void initialize() {
        // Create a new JGraphX graph
        mxGraph jgxGraph = new mxGraph();
        Object parent = jgxGraph.getDefaultParent();

        // Initialize JGraphX graph component
        graphComponent = new mxGraphComponent(jgxGraph);
        graphComponent.setConnectable(false);
        graphComponent.getGraph().setAllowDanglingEdges(false);
        graphComponent.getGraph().setCellsDeletable(false);

        // Set the size of the graph component to match the size of the graphPane
        graphComponent.setSize(602,521);

        // Create a SwingNode to hold the mxGraphComponent
        SwingNode swingNode = new SwingNode();
        swingNode.setContent(graphComponent);
        // Add JGraphX graph component to the graphPane
        graphPane.getChildren().add(swingNode);

        // Create a new JGraphT graph
        graphLayout = new DefaultUndirectedGraph<>(DefaultEdge.class);

        // Handle adding nodes and edges dynamically
    }

    private void addNode(String nodeName) {
        graphLayout.addVertex(nodeName);
        graphComponent.getGraph().getModel().beginUpdate();
        try {
            graphComponent.getGraph().insertVertex(graphComponent.getGraph().getDefaultParent(), null, nodeName, 20, 20, 80, 30);
        } finally {
            graphComponent.getGraph().getModel().endUpdate();
        }
    }

    // Method to add an edge to the graph
    private void addEdge(String sourceNode, String targetNode) {
        graphLayout.addEdge(sourceNode, targetNode);
        graphComponent.getGraph().getModel().beginUpdate();
        try {
            graphComponent.getGraph().insertEdge(graphComponent.getGraph().getDefaultParent(), null, "", graphComponent.getGraph().getVertexToCellMap().get(sourceNode), graphComponent.getGraph().getVertexToCellMap().get(targetNode));
        } finally {
            graphComponent.getGraph().getModel().endUpdate();
        }
    }*/



    @FXML
    protected void goToSchedule(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/raileader_rewrite/schedule-view.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected void goToDebug(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/raileader_rewrite/debug-view.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}