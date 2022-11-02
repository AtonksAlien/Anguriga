package com.anguriga.anguriga.classes;

public class BankAccount extends User {
    private double saldo;
    private String cardNumber;
    public final static double MAX_DEBITO = 300;
    private boolean empty = true;

    public BankAccount(String nome, String cognome, String cardNumber, double saldo) {
        super(nome, cognome);
        setCardNumber(cardNumber);
        setSaldo(saldo);
    }
    public BankAccount(String nome, String cognome, String cardNumber) {
        super(nome, cognome);
        setCardNumber(cardNumber);
        setSaldo(0);
    }

    public String getCardNumber() {
        return "- - - - " + cardNumber.substring(4);
    }

    public void setCardNumber(String cardNumber) {
        if(cardNumber.length() == 8) {
            this.cardNumber = cardNumber;
        }else {
            System.out.println("Numero carta non valido");
            System.exit(0);
        }
    }

    private double getSaldo() {
        return saldo;
    }

    private void setSaldo(double saldo) {
        if(saldo >= -MAX_DEBITO){
            this.saldo = saldo;
        }else{
            System.out.println("Hai troppi debiti, impossibile aggiornare il saldo");
            System.exit(0);
        }
    }

    public synchronized void deposito(double importo) {
        while(!empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Qualcosa è andato storto nel depositare " + importo + "€");
                e.printStackTrace();
            }
        }

        if(importo > 0) {
            setSaldo(saldo + importo);
            empty = false;
            notifyAll();
        }else {
            System.out.println("Impossibile effettuare il versamento");
        }
    }

    public synchronized void prelievo(double importo) {
        while(!empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Qualcosa è andato storto nel prelevare " + importo + "€");
                e.printStackTrace();
            }
        }
        if(importo > 0 && saldo-importo >= -MAX_DEBITO) {
            setSaldo(saldo - importo);
            empty = false;
            notifyAll();
        }else {
            System.out.println("Impossibile effettuare il prelievo");
        }
    }

    public synchronized double readSaldo(){
        while(empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Qualcosa è andato storto nel leggere il saldo");
                e.printStackTrace();
            }
        }
        empty = true;
        notifyAll();
        return getSaldo();
    }

}
