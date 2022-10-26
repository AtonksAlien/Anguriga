package com.anguriga.anguriga.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class BollettiniController {
    @FXML
    protected Button closeIcon;

    @FXML
    private void closeWindow() {
        ((Stage) closeIcon.getScene().getWindow()).close();
    }
}
