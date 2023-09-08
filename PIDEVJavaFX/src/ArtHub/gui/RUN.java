/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArtHub.gui;


import static java.awt.Color.blue;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.awt.Dimension;


/**
 *
 */
public class RUN extends Application {

    @Override
    public void start(Stage primaryStage) {
        

        try {   
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root );
            scene.setFill(Color.LIGHTGRAY);
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setTitle("ArtBox");
            primaryStage.setScene(scene);
            primaryStage.setResizable(true);
            
            primaryStage.show();

        } catch (IOException ex) {
            Logger.getLogger(RUN.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
