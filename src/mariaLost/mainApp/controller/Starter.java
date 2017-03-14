package mariaLost.mainApp.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mariaLost.gamePlay.controller.GameLayoutController;
import mariaLost.gamePlay.controller.MenuBarController;
import mariaLost.gamePlay.view.EndPageController;
import mariaLost.parameters.Parameters_MariaLost;
import mariaLost.user.controller.UserDetailsController;
import mariaLost.user.controller.UserOverviewController;
import mariaLost.user.model.User;
import mariaLost.user.model.UserReader;
import sun.applet.Main;

import java.io.IOException;
import java.util.List;

/**
 * Created by elsacollet on 14/03/2017.
 */
public class Starter {
    private BorderPane root;
    private UserReader userReader;
    private ObservableList<User> userList = FXCollections.observableArrayList();

    private User currentUser;
    private int indexCurrentUser;

    private static Starter instance;
    private static MainApp mainApp;

    public static Starter getInstance(MainApp m){
        mainApp = m;
        instance = new Starter();
        return instance;
    }
    public static Starter getInstance() {
        return instance;
    }

    private Starter(){}


    public void start(){
        this.userReader = new UserReader(mainApp);
        this.root = new BorderPane();
        this.root.setMinSize(Parameters_MariaLost.PAGE_WIDTH, Parameters_MariaLost.PAGE_HEIGHT);
        Scene scene = new Scene(root);
        mainApp.getPrimaryStage().setScene(scene);
        mainApp.getPrimaryStage().show();
        this.currentUser = null;
        showUserData();

    }

    public MainApp getMainApp(){
        return mainApp;
    }

    /**
     * Fenetre de démarrage de l'applicaton présentant les different joueurs dans un tableau
     */
    public void showUserData() {
        try {
            //Load personOverView
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(Parameters_MariaLost.FILEPATH_USER_OVERVIEW));
            AnchorPane userOverview = loader.load();
            //Set personOverView dans le centre de la fenetre
            this.root.setCenter(userOverview);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showNewUser(User user) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(Parameters_MariaLost.FILEPATH_USER_DETAILS));
            AnchorPane newUer = loader.load();
            this.root.setCenter(newUer);
            UserDetailsController controller = loader.getController();
            controller.setUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showPlayLayout(User user) {
        this.currentUser= user;
       // mainApp.getPrimaryStage().setFullScreen(true);

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(Parameters_MariaLost.FILEPATH_PLAY));

            ButtonBar menuBar = loader.load();

           // this.root.setPrefSize(Parameters_MariaLost.PLAY_PAGE_WIDTH, Parameters_MariaLost.PLAY_PAGE_HEIGHT);
            this.root.setTop(menuBar);

            menuBar.autosize();

            // Give the controller access to the main app.
            MenuBarController controller = loader.getController();
            controller.setUser();


            //Set the game layout
            GameLayoutController controllerGame = new GameLayoutController(user);


            controllerGame.startGame();

            this.root.setCenter(controllerGame.getPage());
            System.out.println("Controller set user done");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UserReader getUserReader() {
        return userReader;
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
        this.userList.add(user);
        this.userReader.saveUserToFile();
    }

    /**
     * Supprime un joueur de la liste et met à jour le fichier de sauvegarde
     */
    public void deleteUserFromList(User user) {
        this.userList.remove(user);
        this.userReader.saveUserToFile();

    }
    public void updateCurrentUser(){
        System.out.println("update ");

        this.userList.remove(currentUser);
        addUserList(currentUser);
    }

    public User getCurrentUser() {
        return this.currentUser;
    }


    public void gameOver(int code, int money){
        System.out.println("money : "+money);
        switch (code){
            case Parameters_MariaLost.GAME_OVER_CODE :
                System.out.println("game over ");
                currentUser.setScore(currentUser.getScore() - Parameters_MariaLost.SCORE_LOOSE_GAME_OVER);

                break;
            case Parameters_MariaLost.NEXT_LEVEL_CODE :
                System.out.println("Next Level ");

                currentUser.setScore(currentUser.getScore() + money);
                currentUser.setLevel(currentUser.getLevel() + 1);
                break;
            default:
                start();

        }
        updateCurrentUser();
       // this.root.getChildren().removeAll();

        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(Parameters_MariaLost.FILEPATH_ENDPAGE));
            BorderPane endPage = loader.load();
            EndPageController endPageController = loader.getController();
            endPageController.setUser(currentUser, code);
            root.setTop(new AnchorPane());
            root.setCenter(endPage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}