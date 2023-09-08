/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArtHub.gui;

import ArtHub.entities.User;
import ArtHub.services.UserCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import java.awt.Dimension;


public class LoginController implements Initializable {

    @FXML
    private TextField tfusername;
    @FXML
    private PasswordField tfpassword;
    @FXML
    private Button login;
    @FXML
    private Button reinit;
    @FXML
    private Hyperlink signup;
    static public User CurrentUser = new User();
   static public String userHomeFolder = System.getProperty("user.home");
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    

    @FXML
    private void Authentification(ActionEvent event) {
        try{
        UserCRUD us = new UserCRUD();
         User usr = new User();
         
         usr.setUsername(tfusername.getText());
         usr.setPwd_user(tfpassword.getText());
         boolean verify = us.Authentifier(usr.getUsername(),usr.getPwd_user());
         boolean verifyAd = false;
         
        
                if (verify) {
                try {
                     CurrentUser = us.AssignCurrentUser(usr.getUsername(),usr.getPwd_user());
                  Stage primaryStage;
                  
                  if ("+".equals(CurrentUser.getRef_admin())) {
                      
                         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Home.fxml"));  
                      Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
                          
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Host an event" );
           
            stage.setScene(new Scene(root1));
            
            stage.show();
                  
                  } 
                  else  {
                     FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("feed gui.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Host an event" );
            
           
            Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
            
            stage.setScene(new Scene(root1, screenSize.getWidth(), screenSize.getHeight()));
            
            stage.show();
   
                  }
          
                Stage CurrentStage = (Stage)login.getScene().getWindow();
                CurrentStage.close();
                    
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                    try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Home.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Host an event");

            stage.setScene(new Scene(root1));

            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FRONT_EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
                    
                    
                   

               
            }

         
        } catch(SQLException ex){Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);}
    }

    @FXML
    private void forgotpwd(ActionEvent event) {
    }
  
    @FXML
    private void register(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddUser.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Host an event" );
            
            
            stage.setScene(new Scene(root1));
            
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    
}
