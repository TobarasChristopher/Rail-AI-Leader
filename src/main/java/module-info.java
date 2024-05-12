module org.example.raileader_rewrite {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires eu.hansolo.tilesfx;
    requires com.github.vlsi.mxgraph.jgraphx;
    requires javafx.swing;

    opens org.example.raileader_rewrite to javafx.fxml;
    exports org.example.raileader_rewrite;
}