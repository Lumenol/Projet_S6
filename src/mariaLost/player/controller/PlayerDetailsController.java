package mariaLost.player.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import mariaLost.mainApp.controller.MainApp;
import mariaLost.player.model.Player;

/**
 * Created by elsacollet on 24/01/2017.
 *
 */
public class PlayerDetailsController {

    private Player player;
    private MainApp mainApp;

    @FXML
    private Label nameLabel;
    @FXML
    private TextField nameField;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label levelLabel;

    public void setMainApp(MainApp mApp) {
        this.mainApp = mApp;
    }

    public PlayerDetailsController() {
    }

    @FXML
    public void initialize(){
    }

    public void setPlayer(Player player){
        this.player = player;
        if(this.player != null) {
            nameLabel.setText("Edit player");
            nameField.setText(player.getName());
            scoreLabel.setText(Integer.toString(player.getScore()) );
            levelLabel.setText(Integer.toString(player.getLevel()) );

        }else{
            nameLabel.setText("New player");
            nameField.setText("");
            scoreLabel.setText("0");
            levelLabel.setText("0");
        }
    }

    /**
     *
     * @return
     */
    private boolean isValid(){
        String errorMessage = "";
        if(nameField.getText().length()==0 || nameField.getText()==null){
            errorMessage +="You must fill out all the blank";
        }
        if(errorMessage.length()>0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur formulaire");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
        return true;
    }
    @FXML
    private void handleCancel(){
      mainApp.showPlayerData();
    }

    /**
     * Méthode pour sauvegarder un nouveau joeur ou pour en éditer un. On s'assure d'abords de supprimer
     * l'ancienne version et de recopier ses informations
     */
    @FXML
    private void handleSave(){
        if(isValid()) {
            if (this.player == null) {
                player = new Player(nameField.getText());
                mainApp.addPlayerList(player);
            } else {
                mainApp.deletePlayerFromList(player);
                player = new Player(nameField.getText(), Integer.parseInt(scoreLabel.getText()), Integer.parseInt(levelLabel.getText()));
                mainApp.addPlayerList(player);
            }
            mainApp.showPlayerData();

        }

    }

}
