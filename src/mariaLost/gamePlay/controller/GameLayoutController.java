package mariaLost.gamePlay.controller;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import mariaLost.gamePlay.model.World;
import mariaLost.gamePlay.tools.Direction;
import mariaLost.gamePlay.view.FloorView;
import mariaLost.gamePlay.view.PlayerBarController;
import mariaLost.items.model.Player;
import mariaLost.mainApp.controller.MainApp;
import mariaLost.parameters.Parameters_MariaLost;
import mariaLost.user.model.User;

/**
 * Created by elsacollet on 01/02/2017.
 */
public class GameLayoutController {

    private User user;
    private World world;
    private FloorView mapview;
    private MainApp mainApp;
    private BorderPane page;
    private Player player;

    private PlayerBarController controller;


    public GameLayoutController(User user) {
        this.user = user;
        this.player = new Player();
        this.world = new World(this.player);

        //Création du canevas
        this.mapview = new FloorView();
        this.page = new BorderPane();
        page.setMinSize(Parameters_MariaLost.CASE_WIDTH, Parameters_MariaLost.CASE_HEIGHT);
        playerBar();
    }


    public void setUser(User p) {
        this.user = p;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void playerBar() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(Parameters_MariaLost.FILEPATH_PLAYER_BAR));
            AnchorPane barPlayerOverview = loader.load();
            controller = loader.getController();
            controller.setMainApp(this);
            controller.setUser(user);
            controller.setBar(player);
            page.setTop(barPlayerOverview);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void startGame() {

        AnimationTimer at = new AnimationTimer() {
            @Override
            public void handle(long now) {

                controller.setBar(player);

                Canvas canvas = mapview.getCanvas();
                canvas.requestFocus();
                Point2D centerOfPlayer = world.centerOfPlayer();
                Point2D subtract = centerOfPlayer.subtract(canvas.getWidth() / 2, canvas.getHeight() / 2);
                mapview.draw(world.getDrawableFromSquare(new Rectangle2D(subtract.getX(), subtract.getY(), canvas.getWidth(), canvas.getHeight())), subtract);
            }
        };
        page.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        page.setCenter(mapview.getCanvas());

        boolean touche[] = new boolean[4];

        /**
         * Actions à réaliser sur le CANVAS
         */
        /**
         * Méthode pour arreter le déplacement quand le joueur cesse d'appuyer.
         */
        mapview.getCanvas().setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case Z:
                case UP:
                    touche[0] = false;
                    break;

                case S:
                case DOWN:
                    touche[1] = false;
                    break;

                case Q:
                case LEFT:
                    touche[2] = false;
                    break;

                case D:
                case RIGHT:
                    touche[3] = false;
                    break;
                default:
            }
            Direction d = Direction.ANY;
            if (touche[0])
                d = d.compose(Direction.UP);
            if (touche[1])
                d = d.compose(Direction.DOWN);
            if (touche[2])
                d = d.compose(Direction.LEFT);
            if (touche[3])
                d = d.compose(Direction.RIGHT);
            world.setDirectionPlayer(d);
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
                    case Z:
                    case UP:
                        touche[0] = true;
                        break;

                    case S:
                    case DOWN:
                        touche[1] = true;
                        break;

                    case Q:
                    case LEFT:
                        touche[2] = true;
                        break;

                    case D:
                    case RIGHT:
                        touche[3] = true;
                        break;
                    default:
                }
                Direction d = Direction.ANY;
                if (touche[0])
                    d = d.compose(Direction.UP);
                if (touche[1])
                    d = d.compose(Direction.DOWN);
                if (touche[2])
                    d = d.compose(Direction.LEFT);
                if (touche[3])
                    d = d.compose(Direction.RIGHT);
                world.setDirectionPlayer(d);
            }
        });

        // Lorsque que l'on clique quelque part on défini une destination pour le personnage
        mapview.getCanvas().setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                //TODO verification destination valide
                Point2D subtract = world.centerOfPlayer().subtract(mapview.getCanvas().getWidth() / 2, mapview.getCanvas().getHeight() / 2);
                Point2D destination = new Point2D(event.getX(), event.getY());
                world.setPlayerDestination(destination.add(subtract));
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
