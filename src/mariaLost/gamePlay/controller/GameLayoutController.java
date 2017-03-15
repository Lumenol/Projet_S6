package mariaLost.gamePlay.controller;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import mariaLost.gamePlay.model.World;
import mariaLost.gamePlay.tools.ClicOnMapEvent;
import mariaLost.gamePlay.tools.Direction;
import mariaLost.gamePlay.view.GameView;
import mariaLost.items.model.FireballAttack;
import mariaLost.items.model.Player;
import mariaLost.mainApp.controller.Starter;
import mariaLost.parameters.Parameters_MariaLost;
import mariaLost.user.model.User;

import java.io.IOException;

/**
 * Created by elsacollet on 01/02/2017.
 */
public class GameLayoutController {

    private User user;
    private World world;
    private Starter start;
    private GameView gameView;
    private Player player;

    public GameLayoutController(User user) {
        start = Starter.getInstance();
        this.user = user;
        this.player = new Player();
        this.world = new World(this.player);
        try {
            gameView = new GameView(world);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void setUser(User p) {
        this.user = p;
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
        gameView.addEventHandler(ClicOnMapEvent.CLIC_ON_MAP_EVENT_TYPE, new EventHandler<ClicOnMapEvent>() {
            @Override
            public void handle(ClicOnMapEvent event) {
                if (event.getButton().equals(MouseButton.SECONDARY))
                    world.setPlayerDestination(new Point2D(event.getX(), event.getY()));
                if (event.getButton().equals(MouseButton.PRIMARY)){
                	Point2D point=new Point2D(event.getX(), event.getY());
                    world.add(FireballAttack.getFireball(player.getAttackStartingPoint(point), new Direction(player.pointDirection(point))));
                }	
            }
        });

        gameView.nameProperty().bind(user.nameProperty());

        gameView.moneyProperty().bind(player.getMonnayeur().valueProperty());

        /*
        Accroche la vie du joueur a la barre de vie de la vue calcul le pourcentage de vie du joueur en considérent qu'il a ça vie maximal a ce moment
         */
        gameView.lifeProperty().bind(player.lifePointPropertie().divide(player.lifePointPropertie().get()));


        world.start();
        gameView.start();
        animationTimer.start();
        gameView.requestFocus();

        //Met en pause quand la fenetre n'ai plus selectionné
        start.getMainApp().getPrimaryStage().focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                world.start();
                gameView.start();
            } else {
                world.stop();
                gameView.stop();
                gameView.clearKey();
            }
        });


    }


    public void gameOver(int code){
        switch (code){
            case Parameters_MariaLost.GAME_OVER_CODE :
                start.getCurrentUser().setScore(start.getCurrentUser().getScore() - Parameters_MariaLost.SCORE_LOOSE_GAME_OVER);
                start.start();
                break;
            case Parameters_MariaLost.NEXT_LEVEL_CODE :
                start.getCurrentUser().setScore(start.getCurrentUser().getScore() + (int) player.getMonnayeur().getValue());
                break;
            default:
                start.start();

        }

    }
    public BorderPane getPage() {
        return gameView;
    }
}
