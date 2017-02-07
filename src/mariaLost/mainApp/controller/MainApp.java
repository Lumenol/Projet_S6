package mariaLost.mainApp.controller;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mariaLost.gamePlay.controller.GameLayoutController;
import mariaLost.gamePlay.controller.MenuBarController;
import mariaLost.player.controller.PlayerDetailsController;
import mariaLost.player.controller.PlayerOverviewController;
import mariaLost.player.model.Player;
import mariaLost.player.model.PlayerReader;

import java.io.IOException;
import java.util.List;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane root;
    private PlayerReader playerReader;
    private ObservableList<Player> playerList = FXCollections.observableArrayList();

    //Ressources FXML
    private String rootFilePath = "../../mainApp/view/root.fxml";
    private String playFilePath = "../../gamePlay/view/playLayout.fxml";
    private String playerOverviewFilePath = "../../player/view/playerOverview.fxml";
    private String playerDetailsFilePath = "../../player/view/playerDetails.fxml";


    public MainApp() {
        System.out.println("Main App ");
    }

    public static void main(String[] args) {
        launch(args);
    }

    public BorderPane getRoot() {
        return root;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        playerReader = new PlayerReader(this);

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Maria Lost - " + playerReader.getPlayerFilePath().getName());
        this.primaryStage.getIcons().add(new Image("file:resources/Images/logo.png"));

        //Player pour test
     //   Player p = new Player("Elsa", 0, 0);
        showRoot();
       // showPlayLayout(p);
      //  showRoot();
        showPlayerData();
    }

    public void showRoot(){
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource(rootFilePath));

            root = loader.load();
            root.setMinSize(mariaLost.mainApp.model.Parameters.PAGE_WIDTH, mariaLost.mainApp.model.Parameters.PAGE_HEIGHT);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

            RootController controller = loader.getController();

            controller.setMainApp(this);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showPlayerData() {
        try {
            //Load personOverView
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(playerOverviewFilePath));
            AnchorPane playerOverview = loader.load();;

            //Set personOverView dans le centre de la fenetre
            root.setCenter(playerOverview);

            //Give the controller access to the mainApp
            PlayerOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showNewPlayer(Player player) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(playerDetailsFilePath));
            AnchorPane newPlayer = loader.load();
            root.setCenter(newPlayer);
            PlayerDetailsController controller = loader.getController();
            controller.setMainApp(this);
            controller.setPlayer(player);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void showPlayLayout(Player player) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(playFilePath));
            ButtonBar menuBar = loader.load();
            root.setTop(menuBar);
            menuBar.autosize();
            // Give the controller access to the main app.
            MenuBarController controller = loader.getController();
            controller.setMainApp(this);


            //Set the game layout
            GameLayoutController controllerGame = new GameLayoutController(player);
            controllerGame.setMainApp(this);
            controllerGame.startGame();
            root.setCenter(controllerGame.getPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PlayerReader getPlayerReader() {
        return playerReader;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public ObservableList<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> list){
        this.playerList.clear();
        this.playerList.addAll(list);
    }

    /**
     * Enregistre les changements sur la liste de player
     * Add
     * @param player
     */
    public void addPlayerList(Player player) {
        playerList.add(player);
        playerReader.savePlayerToFile();
    }

    /**
     * Supprime un joueur de la liste et met à jour le fichier de sauvegarde
     */
    public void deletePlayerFromList(Player player){
        playerList.remove(player);
        playerReader.savePlayerToFile();

    }
}
