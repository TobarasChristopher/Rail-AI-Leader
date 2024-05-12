package nodefactory;

import com.mxgraph.model.mxCell;

import javax.swing.*;

public class Train {
    String name;

    Object destination;

    Object pointOfOrigin;

    String scheduledTime;
    String arrivalTime;

    public Train(String name, Object pointOfOrigin, Object destination, String scheduledTime, String arrivalTime){
        this.name = name;
        this.pointOfOrigin = pointOfOrigin;
        this.destination = destination;
        this.scheduledTime = scheduledTime;
        this.arrivalTime = arrivalTime;

    }

    public String getName(){
        return name;
    }

    public Object getDestination(){
        return destination;
    }

    public Object getPointOfOrigin(){
        return pointOfOrigin;
    }

    public String getScheduledTime(){
        return scheduledTime;
    }

    public String getArrivalTime(){
        return arrivalTime;
    }

}
