module com.anguriga.anguriga {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.anguriga.anguriga to javafx.fxml;
    exports com.anguriga.anguriga;
    exports com.anguriga.anguriga.controllers;
    opens com.anguriga.anguriga.controllers to javafx.fxml;
}