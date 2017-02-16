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
import mariaLost.parameters.Parameters_MariaLost;
import mariaLost.user.controller.UserDetailsController;
import mariaLost.user.controller.UserOverviewController;
import mariaLost.user.model.User;
import mariaLost.user.model.UserReader;

import java.io.IOException;
import java.util.List;


public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane root;
    private UserReader userReader;
    private ObservableList<User> userList = FXCollections.observableArrayList();


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
    public void start(Stage primaryStage) throws Exception {
        this.userReader = new UserReader(this);

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Maria Lost - " + userReader.getUserFile().getName());
        this.primaryStage.getIcons().add(new Image(Parameters_MariaLost.IMAGE_LOGO));

        primaryStage.setMinWidth(2 * Parameters_MariaLost.PAGE_WIDTH);
        primaryStage.setMinHeight(2 * Parameters_MariaLost.PAGE_HEIGHT);

        primaryStage.setFullScreen(true);

        showRoot();
        showUserData();
    }

    public void showRoot() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource(Parameters_MariaLost.FILEPATH_ROOT));

            root = loader.load();
            root.setMinSize(Parameters_MariaLost.PAGE_WIDTH, Parameters_MariaLost.PAGE_HEIGHT);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

            RootController controller = loader.getController();

            controller.setMainApp(this);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showUserData() {
        try {
            //Load personOverView
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(Parameters_MariaLost.FILEPATH_USER_OVERVIEW));
            AnchorPane userOverview = loader.load();
            ;

            //Set personOverView dans le centre de la fenetre
            root.setCenter(userOverview);

            //Give the controller access to the mainApp
            UserOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showNewUser(User user) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(Parameters_MariaLost.FILEPATH_USER_DETAILS));
            AnchorPane newUer = loader.load();
            root.setCenter(newUer);
            UserDetailsController controller = loader.getController();
            controller.setMainApp(this);
            controller.setUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showPlayLayout(User user) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(Parameters_MariaLost.FILEPATH_PLAY));
            ButtonBar menuBar = loader.load();
            root.setTop(menuBar);
            menuBar.autosize();
            // Give the controller access to the main app.
            MenuBarController controller = loader.getController();
            controller.setMainApp(this);


            //Set the game layout
            GameLayoutController controllerGame = new GameLayoutController(user);
            controllerGame.setMainApp(this);
            controllerGame.startGame();
            root.setCenter(controllerGame.getPage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UserReader getUserReader() {
        return userReader;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public ObservableList<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> list) {
        this.userList.clear();
        this.userList.addAll(list);
    }

    /**
     * Enregistre les changements sur la liste de user
     * Add
     *
     * @param user
     */
    public void addUserList(User user) {
        userList.add(user);
        userReader.saveUserToFile();
    }

    /**
     * Supprime un joueur de la liste et met à jour le fichier de sauvegarde
     */
    public void deleteUserFromList(User user) {
        userList.remove(user);
        userReader.saveUserToFile();

    }
}
