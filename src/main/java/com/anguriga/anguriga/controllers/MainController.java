package com.anguriga.anguriga.controllers;

import com.anguriga.anguriga.Main;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    //https://stackoverflow.com/questions/34785417/javafx-fxml-controller-constructor-vs-initialize-method
    @FXML
    protected Label saldo;

    @FXML
    protected void newATM(Event e) {
        try{
            Main.startATM(new Stage(), 0);
        }catch (IOException ex){
            System.out.println("Impossibile aprire un nuovo ATM");
            System.out.println(ex.getMessage());
        }
    }
    @FXML
    protected void versamento(Event e) {
        Stage currentStage = (Stage)((Node) e.getSource()).getScene().getWindow();
        Main.startModal(currentStage, "versamento", new int[]{640, 320}, new int[]{0,0}, true);
    }
    @FXML
    protected void prelievo(Event e) {
        Stage currentStage = (Stage)((Node) e.getSource()).getScene().getWindow();
        Main.startModal(currentStage, "prelievo", new int[]{640, 320}, new int[]{0,0}, true);
    }
    @FXML
    protected void bollettini(Event e) {
        Stage currentStage = (Stage)((Node) e.getSource()).getScene().getWindow();
        Main.startModal(currentStage, "bollettini", new int[]{900, 470}, new int[]{0,75}, false);

    }

}