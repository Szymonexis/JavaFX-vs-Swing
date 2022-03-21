module com.example.javafxexample {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires org.json;

    opens com.example.javafxexample to javafx.fxml;
    exports com.example.javafxexample;
    exports com.example.javafxexample.json;
    opens com.example.javafxexample.json to javafx.fxml;
    exports com.example.javafxexample.enums;
    opens com.example.javafxexample.enums to javafx.fxml;
    exports com.example.javafxexample.controllers;
    opens com.example.javafxexample.controllers to javafx.fxml;
}