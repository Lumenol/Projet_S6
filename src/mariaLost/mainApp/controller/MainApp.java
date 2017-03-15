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
import sun.applet.Main;

import java.io.IOException;
import java.util.List;


public class MainApp extends Application {

    private Stage primaryStage;
    private Starter frontalControler;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.frontalControler = Starter.getInstance(this);

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Maria Lost - ");
        this.primaryStage.getIcons().add(new Image(Parameters_MariaLost.IMAGE_LOGO));

        this. primaryStage.setMinWidth(Parameters_MariaLost.PAGE_WIDTH);
        this.primaryStage.setMinHeight(Parameters_MariaLost.PAGE_HEIGHT);
        this.frontalControler.start();
       // this.frontalControler.showUserData();
    }







    public Stage getPrimaryStage() {
        return primaryStage;
    }


}
