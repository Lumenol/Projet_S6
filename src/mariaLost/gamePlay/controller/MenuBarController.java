package mariaLost.gamePlay.controller;

import javafx.fxml.FXML;
import mariaLost.mainApp.controller.MainApp;

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
