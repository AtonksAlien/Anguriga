package com.anguriga.anguriga;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        startATM(stage, 250);
        startATM(new Stage(), 1200);
    }

    public static void startATM(Stage stage, int x) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setTitle("Homepage");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.getIcons().add(new Image("file:src/main/resources/icon.png"));
        stage.setX(x);
        stage.setY(150);
        stage.show();
    }

    public static void startModal(Stage currentStage, String type) {
        int width = 640;
        int height = 320;

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("versamento-prelievo.fxml"));
        try{
            Scene scene = new Scene(fxmlLoader.load(), width, height);
            Stage stage = new Stage();
            stage.setTitle(type.equals("versamento") ? "Versamento" : "Prelievo");
            stage.setResizable(false);
            stage.setScene(scene);
            //Spawn at center
            stage.setX(currentStage.getX() + (currentStage.getWidth() - width) / 2);
            stage.setY(currentStage.getY() + (currentStage.getHeight() - height) / 2);
            //As modal
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(currentStage);
            stage.getIcons().add(new Image("file:src/main/resources/icon.png"));
            stage.show();
        }catch (IOException e){
            System.out.println("Impossibile aprire la finestra di "+type);
            System.out.println(e.getMessage());
        }

    }
    public static void main(String[] args) {
        launch();
    }
}