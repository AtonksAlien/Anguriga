package com.anguriga.anguriga.controllers;

import com.anguriga.anguriga.classes.BankAccount;
import com.anguriga.anguriga.classes.SaldoUpdater;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PV_Controller implements BorderlessModal{
    private String[] testi;
    private String tipo;
    private BankAccount conto;

    @FXML
    protected Label titolo;
    @FXML
    protected TextField importo;
    @FXML
    protected Button button;

    @FXML
    public void closeWindow() {
        ((Stage) titolo.getScene().getWindow()).close();
    }

    @FXML
    public void initialize() {
        titolo.setText(testi[0]);
        importo.setPromptText(testi[1]);
        button.setText(testi[2]);
        Platform.runLater( () -> button.requestFocus() );
    }

    public PV_Controller(BankAccount conto, String titolo){
        String[][] dbTesti = new String[][]{
                new String[]{"Versamento", "Inserisci la somma da versare", "VERSA"},
                new String[]{"Prelievo", "Inserisci la somma da prelevare", "PRELEVA"},
        };

        if(conto != null){
            if(!titolo.isEmpty()){
                if(titolo.equalsIgnoreCase("versamento")) {
                    tipo = titolo;
                    testi = dbTesti[0];
                    this.conto = conto;
                }else if(titolo.equalsIgnoreCase("prelievo")){
                    tipo = titolo;
                    testi = dbTesti[1];
                    this.conto = conto;
                }else{
                    System.out.println("Titolo finestra non valido");
                    System.exit(0);
                }
            }else{
                System.out.println("Titolo finestra non specificato");
                System.exit(0);
            }
        }else{
            System.out.println("Conto non specificato");
            System.exit(0);
        }
    }

    @FXML
    protected void prelevaVersa(){
        //TODO: Controlli input
        double importo = 10;
        Thread saldoUpdater = new Thread(new SaldoUpdater(conto, tipo, importo));
        saldoUpdater.setName("SaldoUpdater");
        saldoUpdater.start();
    }

}

