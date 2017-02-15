package mariaLost.gamePlay.controller;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import mariaLost.gamePlay.model.World;
import mariaLost.gamePlay.tools.Direction;
import mariaLost.gamePlay.view.ClicOnMap;
import mariaLost.gamePlay.view.GameView;
import mariaLost.gamePlay.view.PlayerBarController;
import mariaLost.items.model.Player;
import mariaLost.mainApp.controller.MainApp;
import mariaLost.user.model.User;

import java.io.IOException;

/**
 * Created by elsacollet on 01/02/2017.
 */
public class GameLayoutController {

    private User user;
    private World world;
    private MainApp mainApp;
    private GameView gameView;
    private Player player;

    private PlayerBarController controller;


    public GameLayoutController(User user) {
        this.user = user;
        this.player = new Player();
        this.world = new World(this.player);

        try {
            gameView = new GameView(world);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setUser(User p) {
        this.user = p;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }


    public void startGame() {

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                Direction d = Direction.ANY;
                if (gameView.isKeyPressed(KeyCode.Z) || gameView.isKeyPressed(KeyCode.UP))
                    d = d.compose(Direction.UP);
                if (gameView.isKeyPressed(KeyCode.S) || gameView.isKeyPressed(KeyCode.DOWN))
                    d = d.compose(Direction.DOWN);
                if (gameView.isKeyPressed(KeyCode.Q) || gameView.isKeyPressed(KeyCode.LEFT))
                    d = d.compose(Direction.LEFT);
                if (gameView.isKeyPressed(KeyCode.D) || gameView.isKeyPressed(KeyCode.RIGHT))
                    d = d.compose(Direction.RIGHT);
                world.setDirectionPlayer(d);
            }
        };

        //directionScheduledService.setPeriod(Duration.millis(100));
        //directionScheduledService.setOnSucceeded(event -> world.setDirectionPlayer((Direction) event.getSource().getValue()));


        // Lorsque que l'on clique quelque part on défini une destination pour le personnage
        gameView.addEventHandler(ClicOnMap.CLIC_ON_MAP, new EventHandler<ClicOnMap>() {
            @Override
            public void handle(ClicOnMap event) {
                System.out.println(event);
                if (event.getButton().equals(MouseButton.PRIMARY))
                    world.setPlayerDestination(new Point2D(event.getX(), event.getY()));
            }
        });


        world.start();
        gameView.start();
        animationTimer.start();
        gameView.requestFocus();
        gameView.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.booleanValue()) gameView.requestFocus();
        });

        System.out.println("TEST affichage vue: \n");
    }


    public BorderPane getPage() {
        return gameView;
    }
}
