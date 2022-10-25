package com.anguriga.anguriga.classes;

public class User {
    private String nome;
    private String cognome;

    public User(String nome, String cognome) {
        setNome(nome);
        setCognome(cognome);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if(!nome.isEmpty()){
            this.nome = nome;
        }else{
            System.exit(0);
        }
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        if(!cognome.isEmpty()){
            this.cognome = cognome;
        }else{
            System.exit(0);
        }
    }
}
