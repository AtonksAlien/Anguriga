package com.anguriga.anguriga.controllers;

import com.anguriga.anguriga.classes.BankAccount;
import com.anguriga.anguriga.classes.SaldoUpdater;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

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
    protected Label errorLabel;

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
        double importo = 0;
        try{
            importo = Double.parseDouble(this.importo.getText());
            if(importo <= 0){
                setError("L'importo deve essere maggiore di 0");
            }else{
                if(importo > BankAccount.MAX_TRANSAZIONE){
                    setError("Importo troppo elevato");
                }else{
                    if(tipo.equalsIgnoreCase("prelievo") && conto.readSaldo(true)-importo < -BankAccount.MAX_DEBITO){
                        setError("Non hai abbastanza denaro");
                    }else{
                        Thread saldoUpdater = new Thread(new SaldoUpdater(conto, tipo, importo), "SaldoUpdater");
                        saldoUpdater.start();
                    }
                }
            }
        }catch (Exception e){
            setError("Importo non valido");
        }
    }

    private void setError(String error){
        errorLabel.setText(error);
        button.setDisable(true);
        button.setText("ERRORE");
        button.setStyle("-fx-background-color: linear-gradient(to top right, #D22A2A, #A92620)");

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        button.setText(testi[2]);
                        button.setDisable(false);
                        button.setStyle("-fx-background-color: linear-gradient(to top right, #42D22A, #20A926);");
                        errorLabel.setText("");
                    }
                });
            }
        }, 1500);
    }
}

