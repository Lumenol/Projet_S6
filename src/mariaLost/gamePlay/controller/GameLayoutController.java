package mariaLost.gamePlay.controller;

import javafx.animation.AnimationTimer;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import mariaLost.gamePlay.model.Floor;
import mariaLost.gamePlay.view.FloorView;
import mariaLost.items.interfaces.Movable;
import mariaLost.items.model.Item;
import mariaLost.items.model.MovableItem;

import mariaLost.items.model.Motor;
import mariaLost.mainApp.controller.MainApp;
import mariaLost.mainApp.model.Parameters;
import mariaLost.player.model.Player;

import java.awt.*;

import static javafx.scene.paint.Color.ALICEBLUE;

/**
 * Created by elsacollet on 01/02/2017.
 */
public class GameLayoutController {

    private Player player;
    private Floor<Item> map;
    private Floor< MovableItem> movable;
    private FloorView mapview;
    private MainApp mainApp;
    private BorderPane page;
    private Group layout;

    public GameLayoutController(Player player) {
        this.map = new Floor<>();
        this.movable = new Floor<>(player.getName());
        //Création du canevas
        this.mapview = new FloorView(map.getDimension());
        this.page = new BorderPane();
        page.setMinSize(Parameters.SQUARE_WIDTH, Parameters.SQUARE_HEIGHT);
    }

    public void setPlayer(Player p) {
        this.player = p;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void startGame(){
        ScheduledService<Void> SS = new ScheduledService<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                       Motor.mooving(map.getItemList(), movable.getItemList());
                        return null;
                    }
                };
            }
        };

        SS.setPeriod(Duration.millis(240));
        AnimationTimer at = new AnimationTimer() {
            @Override
            public void handle(long now) {
                mapview.getCanvas().requestFocus();
                mapview.draw(map.getItemList());
                mapview.draw(movable.getItemList());
            }
        };
        SS.start();
        at.start();


        mapview.getCanvas().requestFocus();
        page.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        AnchorPane littlePlayerOverview = new AnchorPane();
        littlePlayerOverview.setMinSize(Parameters.SQUARE_WIDTH, 50);
        littlePlayerOverview.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));


        page.setCenter(mapview.getCanvas());
        page.setTop(littlePlayerOverview);


     //   layout.setAutoSizeChildren(true);

        /**
         * Actions à réaliser sur le CANVAS
         */
        /**
         * Méthode pour arreter le déplacement quand le joueur cesse d'appuyer.
         */
        mapview.getCanvas().setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                movable.getLittlePlayer().setSpeed(0, 0);
            }
        });
        /**
         * TODO Voir pour la vitesse de départ qui est un peu lente
         */
        /**
         * Méthode pour faire avancer le joeur dans le canevas
         */
        mapview.getCanvas().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case Z :
                        movable.getLittlePlayer().setSpeed(movable.getLittlePlayer().getSpeed().add(0, -1));
                        break;
                    case UP:
                        movable.getLittlePlayer().setSpeed(movable.getLittlePlayer().getSpeed().add(0, -1));
                        break;

                    case S:
                        movable.getLittlePlayer().setSpeed(movable.getLittlePlayer().getSpeed().add(0, 1));
                        break;
                    case DOWN:
                        movable.getLittlePlayer().setSpeed(movable.getLittlePlayer().getSpeed().add(0, 1));
                        break;

                    case Q:
                        movable.getLittlePlayer().setSpeed(movable.getLittlePlayer().getSpeed().add(-1, 0));
                        break;
                    case LEFT:
                        movable.getLittlePlayer().setSpeed(movable.getLittlePlayer().getSpeed().add(-1, 0));
                        break;

                    case D:
                        movable.getLittlePlayer().setSpeed(movable.getLittlePlayer().getSpeed().add(1, 0));
                        break;
                    case RIGHT:
                        movable.getLittlePlayer().setSpeed(movable.getLittlePlayer().getSpeed().add(1, 0));
                        break;
                    case SPACE:
                        movable.getLittlePlayer().setSpeed(0, 0);
                }
            }
        });

        System.out.println("TEST affichage vue: \n");
    }




    public BorderPane getPage(){
        return page;
    }
}
