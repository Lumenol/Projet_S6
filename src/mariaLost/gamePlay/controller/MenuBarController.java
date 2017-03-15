package mariaLost.gamePlay.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import mariaLost.mainApp.controller.Starter;

/**
 * Created by elsacollet on 23/01/2017.
 * Classe gerant la page playLayout qui s'occupe de la fenetre de jeu a proprement parlé.
 * Donne un acces au menu
 * Permet l'affichage du score et du jeu du joueur
 */
public class MenuBarController {
    private Starter start;
    @FXML
    private Label nameLabel;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label levelLabel;

    public MenuBarController() {
        start = Starter.getInstance();
    }


    /**
     * Methode pour l'affichage du background
     */
    @FXML
    public void initialize() {
    }


    public void setUser() {
        if (this.start.getCurrentUser() != null) {
            System.out.print(this.start.getCurrentUser().getName());

            this.nameLabel.setText(start.getCurrentUser().getName());
            this.scoreLabel.setText(Integer.toString(start.getCurrentUser().getScore()));
            this.levelLabel.setText(Integer.toString(start.getCurrentUser().getLevel()));
        }
    }

    @FXML
    public void handleMenu() {
        try {
            start.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
