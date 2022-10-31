package com.anguriga.anguriga.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class BollettiniController implements BorderlessModal{
    @FXML
    protected Button closeIcon;

    @FXML
    public void closeWindow() {
        ((Stage) closeIcon.getScene().getWindow()).close();
    }
}
