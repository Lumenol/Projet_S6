package mariaLost.user.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mariaLost.mainApp.controller.MainApp;
import mariaLost.parameters.Parameters_MariaLost;
import mariaLost.user.model.User;

/**
 * Created by elsacollet on 23/01/2017.
 */
public class UserOverviewController {

    @FXML
    private TableView<User> tableUser;

    @FXML
    private TableColumn<User, String> nameColumn;
    @FXML
    private TableColumn<User, Integer> scoreColumn;
    @FXML
    private Label nameLabel;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label levelLabel;
    @FXML
    private ImageView avatarImageView;

    private MainApp mainApp;


    public UserOverviewController() {

    }

    public void setMainApp(MainApp mA) {
        this.mainApp = mA;
        //Add observable list
        this.tableUser.setItems(mainApp.getUserList());
    }

    @FXML
    public void initialize() {
        this.nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        //this.scoreColumn.setCellValueFactory(cellData -> cellData.getValue().scoreProperty());
        showUserData(null);
        this.tableUser.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showUserData(newValue)
        );
    }

    /**
     * Change in the right panel the detail of the user when one is selected
     * otherwise shows empty textField
     *
     * @param user
     */
    private void showUserData(User user) {
        if (user != null) {
            this.nameLabel.setText(user.getName());
            this.scoreLabel.setText(Integer.toString(user.getScore()));
            this.levelLabel.setText(Integer.toString(user.getLevel()));
            System.out.println("user.get image" + user.getImage());
            this.avatarImageView.setImage(new Image(user.getImage()));
        } else {
            this.nameLabel.setText("");
            this.scoreLabel.setText("");
            this.levelLabel.setText("");
            this.avatarImageView.setImage(new Image(Parameters_MariaLost.IMAGE_LOGO));

        }
    }

    @FXML
    private void handleNewUser() {
        mainApp.showNewUser(null);
    }

    @FXML
    private void handleDelete() {
        User selectedUser = tableUser.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            this.mainApp.deleteUserFromList(selectedUser);
            this.tableUser.getItems().remove(selectedUser);
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
            this.mainApp.showNewUser(this.tableUser.getSelectionModel().getSelectedItem());

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
            this.mainApp.showPlayLayout(this.tableUser.getSelectionModel().getSelectedItem());
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No player selected");
            alert.setContentText("You must choose a player on the left table to play or create a new player above.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleSave() {
        this.mainApp.getUserReader().saveUserToFile();
    }


    @FXML
    private void handleClose() {
        this.mainApp.getUserReader().saveUserToFile();

        System.exit(0);
    }


    //TODO link to API documentation
    @FXML
    private void handleAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Maria Lost");
        alert.setHeaderText("Information générales");
        alert.setContentText("Cette application a été réalisée dans " +
                "le cadre de la Licence 3 par, Elsa Collet, Louis-Maxime Crédeville et Loic Labourbe");
        alert.showAndWait();

    }

}
