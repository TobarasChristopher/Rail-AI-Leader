package nodefactory;

import javax.swing.*;

public class Train {
    String name;

    Object destination;

    Object pointOfOrigin;

    public Train(String name, Object pointOfOrigin, Object destination){
        this.name = name;
        this.pointOfOrigin = pointOfOrigin;
        this.destination = destination;

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

}
