package com.anguriga.anguriga.classes;

public class SaldoUpdater implements Runnable {
    private BankAccount conto;
    private String tipo;
    private double importo;

    public SaldoUpdater(BankAccount conto, String tipo, double importo) {
        setConto(conto);
        setTipo(tipo);
        setImporto(importo);
    }

    private void setConto(BankAccount conto) {
        if(conto != null) {
            this.conto = conto;
        }else {
            System.out.println("Devi avere un conto");
            System.exit(0);
        }
    }

    private void setTipo(String tipo) {
        if(tipo.equals("versamento") || tipo.equals("prelievo")) {
            this.tipo = tipo;
        }else {
            System.out.println("Errore nel tipo di azione da eseguire sul conto");
        }
    }

    private void setImporto(double importo) {
        if(importo > 0){
            if(tipo.equals("prelievo") && conto.readSaldo()-importo >= -BankAccount.MAX_DEBITO) {
                this.importo = importo;
            }else if(tipo.equals("versamento")) {
                this.importo = importo;
            }else{
                System.out.println("Non abbastanza soldi sul conto");
                System.exit(0);
            }
        }else{
            System.out.println("Puoi effettuare il " + tipo + " di solo importi positivi");
        }
    }

    @Override
    public void run() {
        if(tipo.equals("versamento")) {
            conto.deposito(importo);
        } else {
            conto.prelievo(importo);
        }
    }
}
