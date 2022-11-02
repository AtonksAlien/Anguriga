package com.anguriga.anguriga.controllers;

import com.anguriga.anguriga.Main;
import com.anguriga.anguriga.classes.BankAccount;
import com.anguriga.anguriga.classes.SaldoReader;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    //https://stackoverflow.com/questions/34785417/javafx-fxml-controller-constructor-vs-initialize-method
    private BankAccount conto;

    @FXML
    protected Label saldo;
    @FXML
    protected Label cardNumber;
    @FXML
    protected Label nameCard;

    @FXML
    protected void newATM() {
        try{
            Main.startATM(new Stage(), 0);
        }catch (IOException ex){
            System.out.println("Impossibile aprire un nuovo ATM");
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void initialize() {
        Thread reader = new Thread(new SaldoReader(conto, saldo), "SaldoReader");
        reader.start();

        cardNumber.setText(conto.getCardNumber());
        nameCard.setText(conto.getNome() + " " + conto.getCognome());
    }

    @FXML
    protected void versamento(Event e) {
        Stage currentStage = (Stage)((Node) e.getSource()).getScene().getWindow();
        Main.startModal(currentStage, "versamento", new int[]{640, 320}, new int[]{0,0}, true, new PV_Controller(conto, "versamento"));
    }
    @FXML
    protected void prelievo(Event e) {
        Stage currentStage = (Stage)((Node) e.getSource()).getScene().getWindow();
        Main.startModal(currentStage, "prelievo", new int[]{640, 320}, new int[]{0,0}, true, new PV_Controller(conto, "prelievo"));
    }
    @FXML
    protected void bollettini(Event e) {
        Stage currentStage = (Stage)((Node) e.getSource()).getScene().getWindow();
        Main.startModal(currentStage, "bollettini", new int[]{900, 470}, new int[]{0,75}, false, null);

    }

    public MainController(BankAccount conto){
        if(conto != null) {
            this.conto = conto;
        }else {
            System.out.println("Conto Bancario non specificato");
            System.exit(0);
        }
    }
}