package mariaLost.user.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mariaLost.mainApp.controller.Starter;
import mariaLost.parameters.Parameters_MariaLost;
import mariaLost.user.model.User;

/**
 * Created by elsacollet on 24/01/2017.
 * <p>
 * Permet de modifier ou de créer un nouveau joueur.
 *
 * @author Elsa Collet
 */
public class UserDetailsController {

    private User user;
    private Starter start;

    @FXML
    private Label nameLabel;
    @FXML
    private TextField nameField;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label levelLabel;
    @FXML
    private ImageView avatarImageView;


    public UserDetailsController() {

        this.start = Starter.getInstance();
    }


    public void setUser(User user) {
        this.user = user;
        if (this.user != null) {
            nameLabel.setText("Edit player");
            nameField.setText(user.getName());
            scoreLabel.setText(Integer.toString(user.getScore()));
            levelLabel.setText(Integer.toString(user.getLevel()));
            this.avatarImageView.setImage(new Image(user.getImage()));

        } else {
            nameLabel.setText("New player");
            nameField.setText("");
            scoreLabel.setText("0");
            levelLabel.setText("0");
            avatarImageView.setImage(new Image(Parameters_MariaLost.AVATAR_DEFAULT));
        }
    }

    /**
     * @return true if the name field is correct
     */
    private boolean isValid() {
        String errorMessage = "";
        if (nameField.getText().length() == 0 || nameField.getText() == null) {
            errorMessage += "You must fill out all the blank";
        }
        if (errorMessage.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur formulaire");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
        return true;
    }

    @FXML
    private void handleCancel() {
        start.showUserData();
    }

    /**
     * Méthode pour sauvegarder un nouveau joeur ou pour en éditer un. On s'assure d'abords de supprimer
     * l'ancienne version et de recopier ses informations
     */
    @FXML
    private void handleSave() {
        if (isValid()) {
            if (this.user == null) {
                user = new User(nameField.getText());
                start.addUserList(user);
            } else {
                start.deleteUserFromList(user);
                user = new User(nameField.getText(), Integer.parseInt(scoreLabel.getText()), Integer.parseInt(levelLabel.getText()));
                start.addUserList(user);
            }
            start.setCurrentUser(user);
            start.showUserData();

        }

    }

}
