package com.anguriga.anguriga.classes;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class SaldoReader implements Runnable {
    private BankAccount conto;
    private Label saldo;

    public SaldoReader(BankAccount conto, Label saldo) {
        setConto(conto);
        setSaldoLabel(saldo);
    }

    private void setConto(BankAccount conto) {
        if(conto != null) {
            this.conto = conto;
        }else {
            System.out.println("Devi avere un conto");
            System.exit(0);
        }
    }

    private void setSaldoLabel(Label saldo) {
        if(saldo != null) {
            this.saldo = saldo;
        }else {
            System.out.println("Label saldo non valida");
            System.exit(0);
        }
    }

    private void updateSaldoLabel(double importo) {
        Platform.runLater(() -> {
            saldo.setText(importo + " €");
        });
    }

    @Override
    public void run() {
        while(true) {
            updateSaldoLabel(conto.readSaldo());
        }
    }
}
