package com.anguriga.anguriga.classes;

public class BankAccount extends User {
    private double saldo;
    private String cardNumber;
    public final static double MAX_DEBITO = 300;

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

    public synchronized double getSaldo() {
        return saldo;
    }

    public synchronized void setSaldo(double saldo) {
        if(saldo >= -MAX_DEBITO){
            this.saldo = saldo;
        }else{
            System.out.println("Hai troppi debiti, impossibile aggiornare il saldo");
            System.exit(0);
        }
    }


}
