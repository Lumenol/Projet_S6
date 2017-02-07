package mariaLost.gamePlay.controller;

import javafx.animation.AnimationTimer;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import mariaLost.gamePlay.model.World;
import mariaLost.gamePlay.tools.Direction;
import mariaLost.gamePlay.view.FloorView;
import mariaLost.gamePlay.view.LittlePlayerBarController;
import mariaLost.mainApp.controller.MainApp;
import mariaLost.mainApp.model.Parameters;
import mariaLost.player.model.Player;

/**
 * Created by elsacollet on 01/02/2017.
 */
public class GameLayoutController {

    private Player player;
    private World world;
    private FloorView mapview;
    private MainApp mainApp;
    private BorderPane page;
    private Group layout;
    private String littlePlayerBarPath = "../../gamePlay/view/littlePlayerBar.fxml";


    public GameLayoutController(Player player) {
        this.player = player;
        this.world = new World(new mariaLost.gamePlay.model.Player());
        try {
            world.loadFloorFromFile("resources/Floor/ground_map.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Création du canevas
        this.mapview = new FloorView(world.getDimension());
        this.page = new BorderPane();
        page.setMinSize(Parameters.SQUARE_WIDTH, Parameters.SQUARE_HEIGHT);
        playerBar();
    }


    public void setPlayer(Player p) {
        this.player = p;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void playerBar() {
        try {

            System.out.println("Anchor pane");

            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(getClass().getResource(littlePlayerBarPath));
            AnchorPane littlePlayerOverview = loader2.load();
            LittlePlayerBarController controller2 = loader2.getController();
            controller2.setMainApp(this);
            controller2.setPlayer(player);
            controller2.setBar();
            page.setTop(littlePlayerOverview);


            //           littlePlayerOverview.setMinSize(Parameters.SQUARE_WIDTH, 50);
            //         littlePlayerOverview.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
            // page.setTop(littlePlayerOverview);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void startGame() {


        AnimationTimer at = new AnimationTimer() {
            @Override
            public void handle(long now) {
                Canvas canvas = mapview.getCanvas();
                canvas.requestFocus();
                mapview.draw(world.getDrawableFromSquare(new Rectangle2D(0, 0, canvas.getWidth(), canvas.getHeight())));
            }
        };
        page.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        page.setCenter(mapview.getCanvas());
        SimpleObjectProperty<Direction> direction = new SimpleObjectProperty<>(Direction.ANY);
        /**
         * Actions à réaliser sur le CANVAS
         */
        /**
         * Méthode pour arreter le déplacement quand le joueur cesse d'appuyer.
         */
        mapview.getCanvas().setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                direction.set(Direction.ANY);
                world.setDirectionPlayer(direction.get());
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
                System.out.println(event.getCode());
                switch (event.getCode()) {
                    case Z:
                    case UP:
                        direction.set(direction.get().compose(Direction.UP));
                        break;

                    case S:
                    case DOWN:
                        direction.set(direction.get().compose(Direction.DOWN));
                        break;

                    case Q:
                    case LEFT:
                        direction.set(direction.get().compose(Direction.LEFT));
                        break;

                    case D:
                    case RIGHT:
                        direction.set(direction.get().compose(Direction.RIGHT));
                        break;
                    default:
                }
                world.setDirectionPlayer(direction.get());
            }
        });
        world.start();
        at.start();
        System.out.println("TEST affichage vue: \n");
    }


    public BorderPane getPage() {
        return page;
    }
}
