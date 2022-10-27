package com.anguriga.anguriga.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PV_Controller {
    private final String[][] dbTesti = new String[][]{
            new String[]{"Versamento", "Inserisci la somma da versare", "VERSA"},
            new String[]{"Prelievo", "Inserisci la somma da prelevare", "PRELEVA"},
    };
    private String[] testi;

    @FXML
    protected Label titolo;

    @FXML
    private void closeWindow() {
        ((Stage) titolo.getScene().getWindow()).close();
    }

    @FXML
    public void initialize() {
        titolo.setText(testi[0]);
    }

    public PV_Controller(String titolo){
        if(titolo.equalsIgnoreCase("versamento")) {
            testi = dbTesti[0];
        }else if(titolo.equalsIgnoreCase("prelievo")){
            testi = dbTesti[1];
        }
    }
}

