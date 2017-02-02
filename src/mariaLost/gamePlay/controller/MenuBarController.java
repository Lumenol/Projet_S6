package mariaLost.gamePlay.controller;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import mariaLost.gamePlay.model.GroundFloor;
import mariaLost.gamePlay.view.FloorView;
import mariaLost.items.model.Item;
import mariaLost.mainApp.controller.MainApp;
import mariaLost.player.model.Player;

/**
 * Created by elsacollet on 23/01/2017.
 * Classe gerant la page playLayout qui s'occupe de la fenetre de jeu a proprement parlé.
 * Donne un acces au menu
 * Permet l'affichage du score et du jeu du joueur
 */
public class MenuBarController {
    private MainApp mainApp;

    public MenuBarController() {
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Methode pour l'affichage du background
     */
    @FXML
    public void initialize(){
        System.out.println("Initialisation");
    }


    @FXML
    public void handleMenu(){
       try{
           mainApp.start(mainApp.getPrimaryStage());
       }catch(Exception e){
           e.printStackTrace();
       }
    }

}
