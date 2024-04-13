package nodefactory;

import com.mxgraph.view.mxGraph;

public interface GraphCell {
    Object createCell(mxGraph graph, Object parent);
}
