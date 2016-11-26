/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carsense.gui;

import carsense.gui.controller.MainFrameController;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author dem_l
 */
public class FXMLCarSense extends Application {
    
    @FXML
    MainFrameController controller;
    
    @Override
    public void start(Stage stage) throws IOException {
        /*
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        */
        stage.getIcons().add(new Image("file:img/carsense_icon.png"));
        stage.setTitle("Car Sense - CERI M2");
        Pane myPane = (Pane)FXMLLoader.load(getClass().getResource("fxml/MainFrame.fxml"));
        Scene myScene = new Scene(myPane);
        stage.setScene(myScene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
