package com.anguriga.anguriga.classes;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

public class Transazione {
    private double importo;
    private String tipo;
    private GregorianCalendar data;
    private String title;
    private String icon;
    private String color;

    public static final Set<String> icons = Set.of("cuore", "dollaro", "carta");
    public static final List<String> colors = List.of("#FFB21E", "#37B7FF", "#4255FF"); //Giallo - Azzurro - Blu

    public Transazione(double importo, String tipo, GregorianCalendar data, String title, String icon, String color) {
        setImporto(importo);
        setTipo(tipo);
        setData(data);
        setTitle(title);
        setIcon(icon);
        setColor(color);
    }
    public Transazione(double importo, String tipo, GregorianCalendar data) {
        setImporto(importo);
        setData(data);
        setTipo(tipo);
        if(tipo.equalsIgnoreCase("versamento")) {
            setTitle("Versamento");
            setIcon("dollaro");
            setColor(colors.get(1));
        }else if(tipo.equalsIgnoreCase("prelievo")) {
            setTitle("Prelievo");
            setIcon("carta");
            setColor(colors.get(0));
        }else{
            System.out.println("Inserire gli altri dettagli manualmente");
            System.exit(0);
        }
    }

    public void setImporto(double importo) {
        if(importo > 0 && importo <= BankAccount.MAX_TRANSAZIONE){
            this.importo = importo;
        }else{
            System.out.println("Importo non valido");
            System.exit(0);
        }
    }

    public void setTipo(String tipo) {
        if(tipo.equalsIgnoreCase("versamento") || tipo.equalsIgnoreCase("prelievo") || tipo.equalsIgnoreCase("bollettino")) {
            this.tipo = tipo;
        }else{
            System.out.println("Tipo transazione non valido");
            System.exit(0);
        }
    }

    public void setData(GregorianCalendar data) {
        GregorianCalendar now = new GregorianCalendar();
        if(data.getTimeInMillis() < now.getTimeInMillis()+10){
            data.setLenient(false);
            try{
                data.getTimeInMillis();
                this.data = data;
            }catch (IllegalArgumentException e){
                System.out.println("Data non valida.");
                System.exit(0);
            }
        }else {
            System.out.println("Data futura non valida");
            System.exit(0);
        }
    }

    public void setTitle(String title) {
        if(title.length() > 1) {
            title = title.substring(0, 1).toUpperCase() + title.substring(1).toLowerCase();
            this.title = title;
        }else{
            System.out.println("Titolo non valido");
            System.exit(0);
        }
    }

    public void setIcon(String icon) {
        if(icons.contains(icon)) {
            this.icon = "/icons/"+icon+".png";
        }else{
            System.out.println("Icona non valida");
            System.exit(0);
        }
    }

    public void setColor(String color) {
        if(color.charAt(0) == '#') {
            color = color.substring(1);
            if(color.length() == 3 || color.length() == 6){
                if(colors.contains("#"+color)) {
                    this.color = "#"+color;
                }else{
                    System.out.println("Colore non esistente");
                    System.exit(0);
                }
            }else{
                System.out.println("Colore non valido");
                System.exit(0);
            }
        }else{
            System.out.println("Colore non valido");
            System.exit(0);
        }
    }

    public double getImporto() {
        return importo;
    }

    public String getTipo() {
        return tipo;
    }

    public GregorianCalendar getData() {
        return data;
    }

    public String getTitle() {
        return title;
    }

    public String getIcon() {
        return icon;
    }

    public String getColor() {
        return color;
    }

    public HBox generateCard(){
        //ICONA
        ImageView icon = new ImageView(new Image("file:src/main/resources/"+this.icon));
        icon.setFitHeight(35);
        icon.setFitWidth(45);

        //PANE per icona
        Pane pane = new StackPane();
        pane.setPrefWidth(80);
        pane.setStyle("-fx-background-color: "+this.color+";");
        pane.getStyleClass().add("notifica-img");
        pane.setPadding(new Insets(0, 15, 0, 15));
        pane.getChildren().add(icon);
        StackPane.setAlignment(icon, Pos.CENTER);

        //VBOX testi
        VBox vBox = new VBox();
        vBox.setPrefSize(192, 39); //w - h
        vBox.setPadding(new Insets(3, 0, 0, 10));

        //Titolo
        Label title = new Label(this.title);
        title.getStyleClass().add("notifica-title");
        vBox.getChildren().add(title);

        //Data
        Label data = new Label(this.data.get(GregorianCalendar.DAY_OF_MONTH)+" "+convertMonth(this.data.get(GregorianCalendar.MONTH))+" "+this.data.get(GregorianCalendar.YEAR));
        data.getStyleClass().add("notifica-data");
        data.setPadding(new Insets(3, 0, 0, 0));
        vBox.getChildren().add(data);

        //IMPORTO
        String s = "";
        if(tipo.equalsIgnoreCase("versamento")) {
            s = "+";
        }else {
            s = "-";
        }
        Label importo = new Label(s + this.importo +" €");
        importo.setTextAlignment(TextAlignment.CENTER);
        importo.getStyleClass().add("notifica-price");
        if(this.tipo.equalsIgnoreCase("versamento")){
            importo.getStyleClass().add("bg-green");
        }else if(this.tipo.equalsIgnoreCase("prelievo")){
            importo.getStyleClass().add("bg-red");
        }else{
            importo.getStyleClass().add("bg-yellow");
        }
        importo.setPadding(new Insets(0, 10, 0, 10));
        importo.setPrefHeight(60);

        HBox card = new HBox(pane, vBox, importo);
        card.alignmentProperty().set(javafx.geometry.Pos.CENTER_LEFT);
        card.setPrefHeight(60);
        card.getStyleClass().add("notifica");

        return card;
    }

    public String convertMonth(int month){
        return switch (month) {
            case 0 -> "Gennaio";
            case 1 -> "Febbraio";
            case 2 -> "Marzo";
            case 3 -> "Aprile";
            case 4 -> "Maggio";
            case 5 -> "Giugno";
            case 6 -> "Luglio";
            case 7 -> "Agosto";
            case 8 -> "Settembre";
            case 9 -> "Ottobre";
            case 10 -> "Novembre";
            case 11 -> "Dicembre";
            default -> "Mese non valido";
        };
    }

    public void addTransazione(FlowPane flowPane){
        if(flowPane != null){
            Platform.runLater(() -> {
                flowPane.getChildren().add(0, generateCard());
                if(flowPane.getChildren().size() > 5){
                    flowPane.getChildren().remove(5);
                }
            });
        }else{
            System.out.println("FlowPane non valido");
            System.exit(0);
        }

    }
}
