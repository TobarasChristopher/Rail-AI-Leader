package org.example.raileader_rewrite;

import java.io.IOException;
import com.mxgraph.model.mxCell;
import com.mxgraph.view.mxGraph;
import logic.ExceptionHandler;
import logic.PerformanceHandler;
import logic.ScheduleManager;
import logic.TimeManager;
import nodefactory.Train;
import java.util.*;
import java.util.Timer;
import java.util.concurrent.*;

public class LayoutCreator {


    public mxGraph graph = new mxGraph();
    public Object parent = graph.getDefaultParent();
    TimeManager timeManager = TimeManager.getInstance();
    PerformanceHandler performanceHandler = PerformanceHandler.getInstance();
    ExceptionHandler exceptionHandler = ExceptionHandler.getInstance();
    public HashMap<String, Object> vertexMap = new HashMap<>();
    private Map<Object, String> originalStyles = new HashMap<>();

    public List<Train> storedTrains = new ArrayList<>();
    public BlockingQueue<Train> trainsQ = new LinkedBlockingQueue<>();
    private Timer timer;
    private boolean start;
    ScheduleManager scheduleManager = ScheduleManager.getInstance();



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
            //Origin Node
            Object v0 = createVertex("0", "0", 20, 20, 80, 30,"fillColor=orange");
            //Example Impossible Node
            Object TestNode = createVertex("TestNode", "TestNode", -40, -40, 80, 30,"fillColor=green");


            Object CNPlatform1 = createVertex("CNPlatform1", "CNPlatform1", 20, 380, 80, 30,"fillColor=green");
            Object CNPlatform2 = createVertex("CNPlatform2", "CNPlatform2", 20, 320, 80, 30,"fillColor=green");
            Object CNPlatform3 = createVertex("CNPlatform3", "CNPlatform3", 20, 260, 80, 30,"fillColor=green");
            Object CNPlatform4 = createVertex("CNPlatform4", "CNPlatform4", 20, 200, 80, 30,"fillColor=green");
            Object CNPlatform5 = createVertex("CNPlatform5", "CNPlatform5", 20, 140, 80, 30,"fillColor=green");
            Object CNPlatform6 = createVertex("CNPlatform6", "CNPlatform6", 20, 80, 80, 30,"fillColor=green");
            Object CNPlatform7 = createVertex("CNPlatform7", "CNPlatform7", 20, 20, 80, 30,"fillColor=green");

            Object CPlat31ConEn = createVertex("CPlat31ConEn", "CPlat31ConEn", 440, 260, 80, 30,"fillColor=grey");
            Object CPlat31ConEx = createVertex("CPlat31ConEx", "CPlat31ConEx", 300, 380, 80, 30,"fillColor=grey");
            Object CPlat31ConSEn = createVertex("CPlat31ConSEn", "CPlat31ConSEn", 720, 260, 80, 30,"fillColor=grey");
            Object CPlat31ConSEx = createVertex("CPlat31ConSEx", "CPlat31ConSEx", 580, 380, 80, 30,"fillColor=grey");
            Object CPlat32Con = createVertex("CPlat32Con", "CPlat32Con", 160, 260, 80, 30,"fillColor=grey");
            Object CPlat34ConEn = createVertex("CPlat34ConEn", "CPlat34ConEn", 300, 260, 80, 30,"fillColor=grey");

            Object CPlat34ConEnS = createVertex("CPlat34ConEnS", "CPlat34ConEnS", 860, 260, 80, 30,"fillColor=grey");
            Object CPlat34ConExS = createVertex("CPlat34ConExS", "CPlat34ConExS", 720, 200, 80, 30,"fillColor=grey");


            Object CPlat34ConEx = createVertex("CPlat34ConEx", "CPlat34ConEx", 160, 200, 80, 30,"fillColor=grey");
            Object CPlat54ConEx = createVertex("CPlat54ConEx", "CPlat54ConEx", 300, 200, 80, 30,"fillColor=grey");
            Object CPlat54ConEn = createVertex("CPlat54ConEn", "CPlat54ConEn", 440, 140, 80, 30,"fillColor=grey");

            Object CPlat56ConEn = createVertex("CPlat56ConEn", "CPlat56ConEn", 580, 140, 80, 30,"fillColor=grey");
            Object CPlat56ConEx = createVertex("CPlat56ConEx", "CPlat56ConEx", 440, 80, 80, 30,"fillColor=grey");

            Object CPlat56AConEx = createVertex("CPlat56AConEx", "CPlat56AConEx", 720, 140, 80, 30,"fillColor=grey");
            Object CPlat56AConEn = createVertex("CPlat56AConEn", "CPlat56AConEn", 860, 80, 80, 30,"fillColor=grey");

            Object CPlat67ConEn = createVertex("CPlat67ConEn", "CPlat67ConEn", 580, 80, 80, 30,"fillColor=grey");
            Object CPlat67ConEx = createVertex("CPlat67ConEx", "CPlat67ConEx", 440, 20, 80, 30,"fillColor=grey");

            Object C35ConEn = createVertex("C35ConEn", "C35ConEn", 1140, 260, 80, 30,"fillColor=grey");
            Object C35ConEx = createVertex("C35ConEx", "C35ConEx", 860, 140, 80, 30,"fillColor=grey");

            Object CNMaynoothN = createVertex("CNMaynoothN", "CNMaynoothN", 1420, 80, 80, 30,"fillColor=grey");
            Object CNMaynoothS = createVertex("CNMaynoothS", "CNMaynoothS", 1420, 20, 80, 30,"fillColor=grey");


            //Object CN76En = createVertex("CNPlatform5", "CNPlatform5", 1560, 80, 80, 30,"fillColor=green");
            Object CN76Ex = createVertex("CN76Ex", "CN76Ex", 1560, 80, 80, 30,"fillColor=grey");
            Object CN76En = createVertex("CN76En", "CN76En", 1700,20, 80, 30,"fillColor=grey");

            Object CN65Ex = createVertex("CN65Ex", "CN65Ex", 1560, 140, 80, 30,"fillColor=grey");
            Object CN65En = createVertex("CN65En", "CN65En", 1700,80, 80, 30,"fillColor=grey");
            Object CN45Ex = createVertex("CN45Ex", "CN45Ex", 1700,200, 80, 30,"fillColor=grey");

            Object CN64En = createVertex("CN64En", "CN64En", 1980,80, 80, 30,"fillColor=grey");


            Object ConnollyNorthEx = createVertex("ConnollyNorthEx", "ConnollyNorthEx", 2260,20, 80, 30,"fillColor=green");
            Object ConnollyNorthEn = createVertex("ConnollyNorthEn", "ConnollyNorthEn", 2260,80, 80, 30,"fillColor=green");
            Object ConnollyNorthShunt = createVertex("ConnollyNorthShunt", "ConnollyNorthShunt", 2260,140, 80, 30,"fillColor=green");

            Object C65En = createVertex("C65En", "C65En", 2120,80, 80, 30,"fillColor=grey");
            Object C65Ex = createVertex("C65Ex", "C65Ex", 1980,140, 80, 30,"fillColor=grey");
            Object CN3Node = createVertex("CN3Node", "CN3Node", 1700,260, 80, 30,"fillColor=grey");

            Object MaynoothNEx = createVertex("CN76Ex", "CN76Ex", 1560, -40, 80, 30,"fillColor=grey");
            Object MaynoothSEx = createVertex("CN76Ex", "CN76Ex", 1560, -120, 80, 30,"fillColor=grey");




            //Southbound Lines
            graph.insertEdge(parent, null, "", CPlat31ConEx, CNPlatform1);
            graph.insertEdge(parent, null, "", CPlat32Con, CNPlatform2);
            graph.insertEdge(parent, null, "", CPlat32Con, CNPlatform3);
            graph.insertEdge(parent, null, "", CPlat34ConEx, CNPlatform4);
            graph.insertEdge(parent, null, "", CPlat54ConEn, CNPlatform5);
            graph.insertEdge(parent, null, "", CPlat56ConEx, CNPlatform6);
            graph.insertEdge(parent, null, "", CPlat67ConEx, CNPlatform7);

            graph.insertEdge(parent, null, "", CPlat54ConEx, CPlat34ConEx);
            graph.insertEdge(parent, null, "", CPlat34ConEn, CPlat32Con);

            graph.insertEdge(parent, null, "", CPlat31ConSEn, CPlat31ConSEx);
            graph.insertEdge(parent, null, "", CPlat31ConSEx, CPlat31ConEx);
            graph.insertEdge(parent, null, "", CPlat31ConEn, CPlat31ConEx);
            graph.insertEdge(parent, null, "", CPlat31ConSEn, CPlat31ConEn);
            graph.insertEdge(parent, null, "", CPlat31ConEn, CPlat34ConEn);
            graph.insertEdge(parent, null, "", CPlat34ConExS, CPlat54ConEx);
            graph.insertEdge(parent, null, "", CPlat34ConEnS, CPlat31ConSEn);
            graph.insertEdge(parent, null, "", CPlat34ConEn, CPlat34ConEx);

            graph.insertEdge(parent, null, "", CPlat56AConEx, CPlat56ConEn);
            graph.insertEdge(parent, null, "", CPlat56AConEn, CPlat67ConEn);
            graph.insertEdge(parent, null, "", CPlat67ConEn, CPlat56ConEx);
            graph.insertEdge(parent, null, "", CPlat67ConEn, CPlat67ConEx);


            graph.insertEdge(parent, null, "", CPlat54ConEn, CPlat54ConEx);
            graph.insertEdge(parent, null, "", CPlat56ConEn, CPlat54ConEn);
            graph.insertEdge(parent, null, "", CPlat56AConEn, CPlat56AConEx);
            graph.insertEdge(parent, null, "", CPlat56ConEn, CPlat56ConEx);
            graph.insertEdge(parent, null, "", CPlat34ConEnS, CPlat34ConExS);

            graph.insertEdge(parent, null, "", C35ConEn, C35ConEx);
            graph.insertEdge(parent, null, "", C35ConEn, CPlat34ConEnS);
            graph.insertEdge(parent, null, "", C35ConEx, CPlat56AConEx);

            graph.insertEdge(parent, null, "", CNMaynoothN, CPlat56AConEn);
            graph.insertEdge(parent, null, "", CNMaynoothS, CPlat67ConEx);

            graph.insertEdge(parent, null, "", CN65Ex, C35ConEx);
            graph.insertEdge(parent, null, "", CN45Ex, CPlat34ConExS);

            graph.insertEdge(parent, null, "", CN65En, CN76Ex);
            graph.insertEdge(parent, null, "", CN76Ex, CNMaynoothN);
            graph.insertEdge(parent, null, "", CN76En, CNMaynoothS);
            graph.insertEdge(parent, null, "", MaynoothNEx, CNMaynoothN);
            graph.insertEdge(parent, null, "", MaynoothSEx, CNMaynoothS);


            graph.insertEdge(parent, null, "", CN65En, CN65Ex);
            graph.insertEdge(parent, null, "", CN45Ex, CN65Ex);
            graph.insertEdge(parent, null, "", CN76En, CN76Ex);

            graph.insertEdge(parent, null, "", ConnollyNorthEx, CN76En);
            graph.insertEdge(parent, null, "", CN3Node, C35ConEn);

            graph.insertEdge(parent, null, "", ConnollyNorthEn, C65En);
            graph.insertEdge(parent, null, "", C65En, CN64En);
            graph.insertEdge(parent, null, "", C65En, C65Ex);

            graph.insertEdge(parent, null, "", CN64En, CN65En);
            graph.insertEdge(parent, null, "", CN64En, CN45Ex);

            graph.insertEdge(parent, null, "", C65Ex, CN3Node);
            graph.insertEdge(parent, null, "", ConnollyNorthShunt, C65Ex);

            //NorthBound Lines
            graph.insertEdge(parent, null, "", CNPlatform1, CPlat31ConEx);
            graph.insertEdge(parent, null, "", CNPlatform2, CPlat32Con);
            graph.insertEdge(parent, null, "", CNPlatform3, CPlat32Con);
            graph.insertEdge(parent, null, "", CNPlatform4, CPlat34ConEx);

            graph.insertEdge(parent, null, "", CPlat31ConEx, CPlat31ConEn);
            graph.insertEdge(parent, null, "", CPlat34ConEx, CPlat34ConEn);
            graph.insertEdge(parent, null, "", CPlat32Con, CPlat34ConEn);
            graph.insertEdge(parent, null, "", CPlat34ConEn, CPlat31ConEn);

            graph.insertEdge(parent, null, "", CPlat31ConEn, CPlat31ConSEn);
            graph.insertEdge(parent, null, "", CPlat31ConSEn, CPlat34ConEnS);
            graph.insertEdge(parent, null, "", CPlat34ConEnS, C35ConEn);
            graph.insertEdge(parent, null, "", C35ConEn, CN3Node);
            graph.insertEdge(parent, null, "", CN3Node, C65Ex);
            graph.insertEdge(parent, null, "", C65Ex, C65En);
            graph.insertEdge(parent, null, "", C65Ex, ConnollyNorthShunt);
            graph.insertEdge(parent, null, "", C65En, ConnollyNorthEn);

            graph.insertEdge(parent, null, "", CNPlatform7, CPlat67ConEx);
            graph.insertEdge(parent, null, "", CPlat67ConEx, CNMaynoothS);



            graph.setCellsLocked(true);
            graph.setCellsMovable(false);
            graph.setCellsDeletable(false);
            graph.setCellsSelectable(false);
            graph.setConnectableEdges(false);


        } finally {
            graph.getModel().endUpdate();
        }



    }

    public mxGraph initLayout() {

        return graph;
    }
    private Object createVertex(String id, String label, int x, int y, int width, int height, String style) {
        Object vertex = graph.insertVertex(parent, id, label, x, y, width, height, style);
        vertexMap.put(id, vertex); // Store the vertex object with its ID
        return vertex;
    }
    public HashMap<String, Object> getVertexList(){
        return vertexMap;
    }

    private void ImportTrains(){
        List<String[]> rawData = scheduleManager.getRawData();

        try{
            if (rawData != null && !rawData.isEmpty()) {
                for (String[] row : rawData) {

                    if (row.length < 5) {
                        throw new IllegalArgumentException("Incomplete data row: " + Arrays.toString(row));
                    }

                    String name = row[0];
                    String pointOfOrigin = row[1];
                    String destination = row[2];
                    String scheduledTime = row[3];
                    String arrivalTime = row[4];

                    Object originobj = vertexMap.get(pointOfOrigin);
                    Object destinationobj = vertexMap.get(destination);

                    if (originobj == null){
                        throw new IllegalArgumentException("Origin does not match layout cells!");
                    }
                    if (destinationobj == null){
                        throw new IllegalArgumentException("Destination does not match layout cells!");
                    }

                    Train train = new Train(name, originobj, destinationobj, scheduledTime, arrivalTime);

                    storedTrains.add(train);
                }
            }
        } catch (IllegalArgumentException e) {
            exceptionHandler.handleException(e, "An error occurred!", "Invalid data; Missing one or more fields! " + e.getMessage());
        }

    }

    public void stopTimer() {
        timer.cancel();
    }

    //Iterator knowledge: https://www.w3schools.com/java/java_iterator.asp
    private void CheckStoredTrains() {

        String currentTime = timeManager.getCurrentTimeString();
        if (!storedTrains.isEmpty()) {
            Iterator<Train> Storeiterator = storedTrains.iterator();
            while (Storeiterator.hasNext()) {
                Train train = Storeiterator.next();
                if (train.getScheduledTime().equals(currentTime) || train.getScheduledTime().equals("TEST")) {
                    AddTrains(train);
                    Storeiterator.remove();
                }
            }

        }
    }
    private void AddTrains(Train train){
        Object origin = train.getPointOfOrigin();
        try{
            Object cell = graph.getModel().setValue(origin, train.getName());
            trainsQ.offer(train);
        } catch (Exception e){
            exceptionHandler.handleException(e, "An error occurred!", train.getName()+" cannot be placed on its origin because it does not exist!");
        }
    }

    public void Start(){
        start = true;
    }
    public void Stop(){
        start = false;
    }


    public void MoveTrain() {
        // Process trains in the queue
        while (!trainsQ.isEmpty()) {
            Train train = trainsQ.poll(); // Retrieve and remove the train from the queue
            // Create a new thread for each train
            Thread thread = new Thread(() -> {
                long startTime = System.nanoTime(); // Record start time
                List<Object> activePath = findPathDFS(train.getPointOfOrigin(),train.getDestination()); // Find the path for the train

                long endTime = System.nanoTime(); // Record end time
                long duration = endTime - startTime; // Calculate duration in nanoseconds

                if (!(activePath ==null)) {
                    boolean containsColorStyle = checkForLock(activePath, "fillColor=red");
                    while (containsColorStyle) {
                        // Perform action if any node in the path contains the desired color style

                        try {
                            // Sleep for a set amount of time (e.g., 5 seconds)
                            Thread.sleep(3000); // Sleep for 5 seconds (5000 milliseconds)
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            exceptionHandler.handleException(e,e.getMessage(),"Thread for train"+train.getName()+"interrupted while sleeping: ");
                            return; // Exit the thread if interrupted
                        }
                        containsColorStyle = checkForLock(activePath, "fillColor=red");
                    }


                    highlightPath(activePath); // Highlight the path
                    try {
                        // Sleep for a set amount of time (e.g., 5 seconds)
                        Thread.sleep(5000); // Sleep for 5 seconds (5000 milliseconds)
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        exceptionHandler.handleException(e,e.getMessage(),"Thread for train"+train.getName()+"interrupted while sleeping: ");
                        return; // Exit the thread if interrupted
                    }

                    moveTrainOnGraph(train);
                    String currentTime = timeManager.getCurrentTimeString();
                    performanceHandler.addNewTrain(train, currentTime, duration);

                    try {
                        // Sleep for a set amount of time (e.g., 5 seconds)
                        Thread.sleep(5000); // Sleep for 5 seconds (5000 milliseconds)
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        exceptionHandler.handleException(e,e.getMessage(),"Thread for train"+train.getName()+"interrupted while sleeping: ");
                        return; // Exit the thread if interrupted
                    }
                    resetCellColors();
                    Object cell1 = graph.getModel().setValue(train.getDestination(), "");


                } else {
                    Exception er = new Exception();
                    exceptionHandler.handleException(er,er.getMessage(),train.getName() + " could not be moved! An Impossible path was found!");
                    try {
                        // Sleep for a set amount of time (e.g., 5 seconds)
                        Thread.sleep(10000); // Sleep for 5 seconds (5000 milliseconds)
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        exceptionHandler.handleException(e,e.getMessage(),"Thread for train"+train.getName()+"interrupted while sleeping: ");
                        return; // Exit the thread if interrupted
                    }
                    Object cell1 = graph.getModel().setValue(train.getDestination(), "");


                }
            });

            thread.start(); // Start the thread
        }
    }

    public synchronized void moveTrainOnGraph(Train train) {
        Object cell = graph.getModel().setValue(train.getDestination(), train.getName());
        Object cell1 = graph.getModel().setValue(train.getPointOfOrigin(), "");




    }


    //OBSOLETE ALGORITHM
    /*public synchronized List<Object> findPath(Train train) {
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

                    backTrack.push(activePath.size());
                }

                for (Object edge : outgoingEdges) {                           //for each connection
                                                //add to nodeList
                    nodeList.push(edge);
                }
            } else if(!backTrack.isEmpty()) {



                int lastJunction = backTrack.pop();
                int size = activePath.size();
                //int difference = size - lastJunction;



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


            while (targetCell != null && activePath.contains(currentCell) && activePath.contains(targetCell)) {
                // If so, continue popping edges until finding an unvisited target cell or until the stack is empty
                if (!nodeList.isEmpty()) {
                    nextEdge = nodeList.pop();
                    targetCell = graph.getModel().getTerminal(nextEdge, false);
                } else {
                    // Handle the case where the stack is empty
                    targetCell = null;
                    break;
                }

            }

            if (targetCell != null) { // If target is not null (i.e., a valid cell)
                // Add the target cell to the path and update currentCell
                pathCells.add(targetCell);
                currentCell = targetCell;
                activePath.add(currentCell);
            } else {
                // Handle the case where no unvisited target cell is found

                break;
            }

        }

        if(currentCell.equals(destination)) {



            pathFound = true;
            return activePath;
        }



        activePath.clear();
        return activePath;


    }*/

    public List<Object> findPathDFS(Object origin, Object destination) {
        Set<Object> visited = new HashSet<>();
        List<Object> path = new ArrayList<>();
        if (dfs(origin, destination, visited, path)){
            return path;
        }
        return null;
    }

    private boolean dfs(Object currentCell, Object destination, Set<Object> visited, List<Object> path) {
        if (visited.contains(currentCell)) {
            return false; // Detected a cycle, return false to stop traversing this path
        }

        visited.add(currentCell);
        path.add(currentCell);

        if (currentCell.equals(destination)) {
            return true; // Destination found, return true
        }

        Object[] edges = graph.getEdges(currentCell); // Get edges connected to the current cell

        for (Object edge : edges) {
            Object neighbor = graph.getModel().getTerminal(edge, false); // Get the neighboring cell
            if (!visited.contains(neighbor)) { // Check if neighbor has not been visited
                if (dfs(neighbor, destination, visited, path)) { // Recursively explore neighbor
                    return true; // If destination is found, return true
                }
            }
        }

        // Backtrack: Remove current cell from path
        path.remove(path.size() - 1);
        visited.remove(currentCell); // Remove current cell from visited set as well
        return false;
    }

    public synchronized void highlightPath(List<Object> activePath ) {
        for (Object cell : activePath) {

            graph.getModel().beginUpdate();
            try {
                if (cell instanceof mxCell && ((mxCell) cell).isVertex()) {

                    mxCell mxcell = (mxCell) cell;
                    originalStyles.put(mxcell, mxcell.getStyle());
                    graph.getModel().setStyle(cell,"fillColor=red");


                }
            } catch (NullPointerException ex) {
                exceptionHandler.handleException(ex,ex.getMessage(),"Null Pointer exception found at HighlightPath method");

            } finally {
                graph.getModel().endUpdate();
            }
        }


    }
    private synchronized void resetCellColors() {
        graph.getModel().beginUpdate();
        try {
            for (Map.Entry<Object, String> entry : originalStyles.entrySet()) {
                mxCell cell = (mxCell) entry.getKey();
                String originalStyle = entry.getValue();
                graph.getModel().setStyle(cell, originalStyle);
            }
            originalStyles.clear(); // Clear the map after resetting styles
        } catch (NullPointerException ex) {
            exceptionHandler.handleException(ex,ex.getMessage(),"Null Pointer exception found at resetCellColor method");

        } finally {
            graph.getModel().endUpdate();
        }
    }

    public synchronized boolean checkForLock(List<Object> activePath, String desiredColorStyle){
        boolean check = false;
        for (Object cell : activePath) {
            if (cell instanceof mxCell && ((mxCell) cell).isVertex()) {
                mxCell mxcell = (mxCell) cell;
                if(Objects.equals(graph.getModel().getStyle(cell), desiredColorStyle)){
                    check = true;
                }


            }

        }

        return check;
    }



}
