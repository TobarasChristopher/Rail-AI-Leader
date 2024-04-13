package nodefactory;

import com.mxgraph.view.mxGraph;
import org.example.raileader_rewrite.GuiController;
import org.example.raileader_rewrite.LayoutCreator;

public class Node extends LayoutCreator implements GraphCell {
    Object parent;
    String id;
    Object value;
    Double x;
    Double y;
    Double width;
    Double height;

    public Node(Object parent, String id, Object value, double x, double y, double width, double height){
        this.parent = parent;
        this.id = id;
        this.value = value;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }



    public Object getParent(){
        return parent;
    }
    public String getId(){
        return id;
    }
    public Object getValue(){
        return value;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public double getWidth(){
        return width;
    }
    public double getHeight(){
        return height;
    }

    @Override
    public Object createCell(mxGraph graph, Object parent) {
        return createNode(graph, parent);
    }

    public Object createNode(mxGraph graph, Object parent) {
        graph.getModel().beginUpdate();
        try {
            return graph.insertVertex(parent, id, this.value, x, y, width, height);
        } finally {
            graph.getModel().endUpdate();
        }
    }
}
