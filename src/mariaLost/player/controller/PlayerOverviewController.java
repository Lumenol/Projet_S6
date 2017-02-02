package mariaLost.player.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import mariaLost.mainApp.controller.MainApp;
import mariaLost.player.model.Player;

/**
 * Created by elsacollet on 23/01/2017.
 */
public class PlayerOverviewController {

    @FXML
    private TableView<Player> tablePlayer;

    @FXML
    private TableColumn<Player, String> nameColumn;

    @FXML
    private Label nameLabel;
    @FXML
    private Label scoreLabel;
    @FXML
    private  Label levelLabel;

    private MainApp mainApp;


    public void setMainApp(MainApp mA) {
        this.mainApp = mA;
        //Add observable list
        tablePlayer.setItems(mainApp.getPlayerList());
    }

    public PlayerOverviewController() {
    }

    @FXML
    public void initialize(){
        nameColumn.setCellValueFactory(cellData-> cellData.getValue().nameProperty());

        showPlayerData(null);
        tablePlayer.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPlayerData(newValue)
        );
    }

    /**
     * Change in the right panel the detail of the player when one is selected
     * otherwise shows empty textField
     * @param player
     */
    private void showPlayerData(Player player) {
        if(player != null){
            nameLabel.setText(player.getName());
            scoreLabel.setText(Integer.toString( player.getScore()) );
            levelLabel.setText(Integer.toString(player.getLevel()));
        }else{
            nameLabel.setText("");
            scoreLabel.setText("");
            levelLabel.setText("");
        }
    }

    @FXML
    private void handleNewPlayer(){
        mainApp.showNewPlayer(null);
    }

    @FXML
    private void handleDelete(){
        Player selectedPlayer = tablePlayer.getSelectionModel().getSelectedItem();
        if(selectedPlayer !=null){
            mainApp.deletePlayerFromList(selectedPlayer);
            tablePlayer.getItems().remove(selectedPlayer);
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No player selected");
            alert.setContentText("You must choose on the left table the player you want to delete.");
            alert.showAndWait();
        }
    }
    @FXML
    private void handleEdit(){
        if(tablePlayer.getSelectionModel().getSelectedIndex() >=0){
            mainApp.showNewPlayer(tablePlayer.getSelectionModel().getSelectedItem());

        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No player selected");
            alert.setContentText("You must choose a player on the left table to edit.");
            alert.showAndWait();
        }
    }


    @FXML
    private void handlePlay(){
        if(tablePlayer.getSelectionModel().getSelectedIndex() >=0){
            mainApp.showPlayLayout(tablePlayer.getSelectionModel().getSelectedItem());
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No player selected");
            alert.setContentText("You must choose a player on the left table to play or create a new player above.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleSave(){
        mainApp.getPlayerReader().savePlayerToFile();
    }


    @FXML
    private void handleClose(){
        mainApp.getPlayerReader().savePlayerToFile();

        System.exit(0);
    }


    //TODO link to API documentation
    @FXML
    private void handleAbout(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Maria Lost");
        alert.setHeaderText("Information générales");
        alert.setContentText("Cette application a été réalisée dans " +
                "le cadre de la Licence 3 par, Elsa Collet, Louis-Maxime Crédeville et Loic Labourbe" );
        alert.showAndWait();

    }

}
