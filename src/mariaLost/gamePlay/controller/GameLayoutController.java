package mariaLost.gamePlay.controller;

import javafx.animation.AnimationTimer;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import mariaLost.gamePlay.model.GroundFloor;
import mariaLost.gamePlay.view.FloorView;
import mariaLost.items.model.Item;
import mariaLost.items.model.LittlePlayer;
import mariaLost.items.model.Motor;
import mariaLost.mainApp.controller.MainApp;
import mariaLost.player.model.Player;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by elsacollet on 01/02/2017.
 */
public class GameLayoutController {

    private Player player;
    private GroundFloor map;
    private FloorView mapView;
    private MainApp mainApp;
    private LittlePlayer littlePlayer;
    private ArrayList<LittlePlayer> listMovableItem;
    private Group layout;


    public GameLayoutController(Player player) {
        this.littlePlayer = new LittlePlayer(player.getName(), "player.png", false);
        this.map = new GroundFloor();
   //     this.mapView = new FloorView(map);
        this.listMovableItem = new ArrayList<>();
        this.listMovableItem.add(littlePlayer);
        System.out.println(listMovableItem.size());
        System.out.println(map.getFloorList().size());

    }

    public void setPlayer(Player p) {
        this.player = p;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void startGame(){
        System.out.println("demarrage player");

        Canvas canvas = new Canvas();
        littlePlayer.setPosition(90, 150);
        mainApp.getRoot().heightProperty();
        canvas.heightProperty().bind(mainApp.getRoot().heightProperty());
        canvas.widthProperty().bind(mainApp.getRoot().widthProperty());
        //   draw(canvas, mapView.getFloorList());
        //  draw(canvas, listMovableItem);

        ScheduledService<Void> SS = new ScheduledService<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        Motor.mooving(map.getFloorList(), listMovableItem);
                        return null;
                    }
                };
            }
        };
        SS.setPeriod(Duration.millis(240));
        SS.start();
        AnimationTimer at = new AnimationTimer() {
            @Override
            public void handle(long now) {
                canvas.requestFocus();
                draw(canvas,  map.getFloorList());
                draw(canvas, listMovableItem);
                //System.out.println(perso.getVitesse());
            }
        };
        at.start();
        canvas.requestFocus();
        this.layout = new Group();

        layout.minWidth(map.getLengthX());
        layout.minHeight(map.getLengthY());

        //    this.layout.getChildren().add(mapView.getCanvas());
        this.layout.getChildren().add(canvas);

        layout.setAutoSizeChildren(true);

        canvas.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                System.out.println("onkeyPress");
                switch (event.getCode()) {
                    case Z:
                        System.err.println(KeyCode.Z);
                        littlePlayer.setSpeed(littlePlayer.getSpeed().add(0, -1));
                        break;

                    case S:

                        System.err.println(KeyCode.S);
                        littlePlayer.setSpeed(littlePlayer.getSpeed().add(0, 1));
                        break;

                    case Q:

                        System.err.println(KeyCode.Q);

                        littlePlayer.setSpeed(littlePlayer.getSpeed().add(-1, 0));
                        break;

                    case D:

                        System.err.println(KeyCode.D);
                        littlePlayer.setSpeed(littlePlayer.getSpeed().add(1, 0));
                        break;

                    case SPACE:
                        littlePlayer.setSpeed(0, 0);
                }
            }
        });

        System.out.println("TEST affichage vue: \n");
    }



    /**
     * Méthode pour déssiner le canevas et les objets mouvants dessus
     * @param canvas
     * @param listItem
     */
    public void draw(Canvas canvas, Iterable<? extends Item> listItem) {
        //System.out.println("draw !");
        GraphicsContext context = canvas.getGraphicsContext2D();
        int x =0, y=0;
        for (Iterator<? extends Item> iterator = listItem.iterator(); iterator.hasNext(); ) {
            Item next = iterator.next();
                x= (int) next.getPosition().getX();
                y= (int) next.getPosition().getY();

            Image im= new Image("file:resources/Images/"+next.getSpriteName());
            //System.out.println("draw x  y " + x +" "+ y + " "+ next.getName());
           // System.out.println(" item " + next.getName() + map.getNbCaseY());

            context.drawImage(im, x, y, next.getSize(), next.getSize());

       /*     if (next instanceof Movable) {
                context.setFill(Color.GREEN);
            } else if (next.isPassable()) {
                context.setFill(Color.BLUE);
            } else {
                context.setFill(Color.RED);
            }*/
         //   Bounds boundary = next.getBounds();
          //  context.fillRect(10, 10, 10, 10 );
          //  context.fillRect(boundary.getMinX()*ratio, boundary.getMinY()*ratio, boundary.getWidth()*ratio, boundary.getHeight()*ratio);
        }
    }

    public Group getGroup(){
        return layout;
    }
}
