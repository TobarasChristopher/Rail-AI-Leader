package nodefactory;

import com.mxgraph.model.mxICell;
import com.mxgraph.view.mxGraph;

public class PointOfInterest extends Node implements GraphCell {


    public PointOfInterest(Object parent, String id, Object value, double x, double y, double width, double height) {
        super(parent, id, value, x, y, width, height);

    }

    public Object setTrain() {
        return parent;
    }

    public Object getTrain() {
        return parent;
    }

    @Override
    public Object createCell(mxGraph graph, Object parent) {
        return createPOINode(graph, parent);
    }
    public mxICell createPOINode(mxGraph graph, Object parent) {
        graph.getModel().beginUpdate();
        try {
            mxICell cell = (mxICell) graph.insertVertex(parent, id, value, x, y, width, height);
            return cell;
        } finally {
            graph.getModel().endUpdate();
        }
    }
}
