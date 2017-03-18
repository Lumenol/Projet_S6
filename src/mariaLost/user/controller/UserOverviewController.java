package mariaLost.user.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mariaLost.mainApp.controller.Starter;
import mariaLost.parameters.Parameters_MariaLost;
import mariaLost.user.model.User;

/**
 * Created by elsacollet on 23/01/2017.
 * Cette classe controleur gere la vue d'accueil
 */
public class UserOverviewController {


    public Button playButton;
    public Button new_Player_Button;
    @FXML
    private TableView<User> tableUser;

    @FXML
    private TableColumn<User, String> nameColumn;
    @FXML
    private TableColumn<User, Number> scoreColumn;
    @FXML
    private TableColumn<User, Number> levelColumn;
    @FXML
    private Label nameLabel;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label levelLabel;
    @FXML
    private ImageView avatarImageView;

    private Starter start;


    public UserOverviewController() {
        this.start = Starter.getInstance();
        //Add observable list
    }

    /* Met à jour la fenetre en chargeant les colonnes
     */
    @FXML
    public void initialize() {
        this.tableUser.setItems(start.getUserList());
        this.nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        this.scoreColumn.setCellValueFactory(cellData -> cellData.getValue().scoreProperty());
        this.levelColumn.setCellValueFactory(cellData -> cellData.getValue().levelProperty());
        if (start.getCurrentUser() != null) {
            this.tableUser.getSelectionModel().select(start.getCurrentUser());
            showUserData(start.getCurrentUser());
        } else {
            showUserData(null);
        }
        this.tableUser.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    showUserData(newValue);
                    playButton.setDefaultButton(true);
                    new_Player_Button.setDefaultButton(false);
                }
        );


    }

    /**
     * Change in the top panel the detail of the user when one is selected
     * otherwise shows empty textField ad the logo
     *
     * @param user
     */
    private void showUserData(User user) {
        if (user != null) {
            this.nameLabel.setText(user.getName());
            this.scoreLabel.setText(Integer.toString(user.getScore()));
            this.levelLabel.setText(Integer.toString(user.getLevel()));
            this.avatarImageView.setImage(new Image(user.getImage()));
        } else {
            start.setCurrentUser(null);
            this.nameLabel.setText("");
            this.scoreLabel.setText("");
            this.levelLabel.setText("");
            this.avatarImageView.setImage(Parameters_MariaLost.IMAGE_LOGO);

        }
    }

    @FXML
    private void handleNewUser() {
        start.showNewUser(null);
    }

    @FXML
    private void handleDelete() {
        User selectedUser = tableUser.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            this.start.deleteUserFromList(selectedUser);
            this.tableUser.getItems().remove(selectedUser);
            start.setCurrentUser(null);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No player selected");
            alert.setContentText("You must choose on the left table the player you want to delete.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleEdit() {
        if (this.tableUser.getSelectionModel().getSelectedIndex() >= 0) {
            User user = this.tableUser.getSelectionModel().getSelectedItem();
            this.start.showNewUser(user);
            start.setCurrentUser(user);

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No player selected");
            alert.setContentText("You must choose a player on the left table to edit.");
            alert.showAndWait();
        }
    }


    @FXML
    private void handlePlay() {
        if (this.tableUser.getSelectionModel().getSelectedIndex() >= 0) {
            this.start.showPlayLayout(this.tableUser.getSelectionModel().getSelectedItem());
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No player selected");
            alert.setContentText("You must choose a player on the left table to play or create a new player above.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleSave() {
        this.start.getUserReader().saveUserToFile();
    }


    @FXML
    private void handleClose() {
        this.start.getUserReader().saveUserToFile();

        System.exit(0);
    }


    @FXML
    private void handleAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Maria Lost");
        alert.setHeaderText("Informations générales");
        alert.setContentText("Cette application a été réalisée dans " +
                "le cadre de la Licence 3 par, Elsa Collet, Louis-Maxime Crédeville et Loic Labourbe");
        alert.showAndWait();

    }

    @FXML
    private void handleRules() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Maria Lost - Règles du Jeu ");
        alert.setHeaderText("Règles du jeu");
        alert.setContentText(
                "Aider Maria à sortir de là !"
                        + "\t\tObjectif\n"
                        + "Rejoignez la sortie du labyrinthe en ramassant le plus d'or possible."
                        + "En tuant les monstres, vous augmentez votre score.\n"
                        + "Si vous mourez, toutes les pièces ramassées sont perdues et vous perdez des points.\n"
                        + "Vous pouvez retourner au menu principal en cliquant sur le bouton Menu\n"
                        + "ATTENTION votre progression pour ce niveau sera perdue\n\n"
                        + "\t\tCommandes\n"
                        + "\tDéplacements\n"
                        + "Utilisez les touches ZQSD, les flèches ou faites un clic droit pour vous déplacer\n"
                        + "\tAttaque\n"
                        + "Faites un clic gauche là où vous souhaitez envoyer une boule de feu.\n"
        );
        alert.showAndWait();
    }
}
