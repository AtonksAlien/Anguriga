package com.anguriga.anguriga.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PV_Controller implements Initializable {
    @FXML
    protected Label titolo;

    @FXML
    private void closeWindow() {
        ((Stage) titolo.getScene().getWindow()).close();
    }

    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        System.out.println("PV_Controller.initialize");
    }
}

