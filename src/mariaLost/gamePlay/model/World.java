package mariaLost.gamePlay.model;

import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import mariaLost.gamePlay.interfaces.Model;
import mariaLost.gamePlay.tools.Direction;
import mariaLost.items.Controller.EnemyController;
import mariaLost.items.interfaces.Drawable;
import mariaLost.items.model.AbstractEnemy;
import mariaLost.items.model.AbstractItem;
import mariaLost.items.model.AbstractMobileItem;
import mariaLost.items.model.Money;
import mariaLost.mainApp.controller.Starter;
import mariaLost.parameters.Parameters_MariaLost;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by crede on 06/02/2017.
 */
public class World implements Model {

    public static World instance = null;

    private AbstractFloor floor;
    private Deque<AbstractItem> items = new LinkedList<>();
    private Starter start;
    private AbstractMobileItem player;

    //Indique quand le niveau est fini
    private BooleanProperty finish = new SimpleBooleanProperty(false);

    private String mapPath = Parameters_MariaLost.FILEPATH_MAP;

    private AnimationTimer moteur = new AnimationTimer() {
        private long time = 0;
        //Limite a 60 Hz
        private long delay = 16000000L;

        //boucle de rafraîchissement
        @Override
        public void handle(long now) {
            if (now - time >= delay) {
                time = now;
                //recupere tout les items
                Dimension2D dimension = floor.getDimension();
                Collection<AbstractItem> itemFromSquare = (Collection<AbstractItem>) floor.getItemFromSquare(
                        new Rectangle2D(0, 0, dimension.getWidth(), dimension.getHeight()));
                itemFromSquare.addAll(items);
                EnemyController.handleEnemies(itemFromSquare);

                //Fait bouge les items
                PhysicalMotor.move(itemFromSquare);
                //Retire les items qui on terminer leur action

                Collection<AbstractItem> aAjouter = new LinkedList<>();

                //supprime les items fini
                for (Iterator<AbstractItem> iterator = items.iterator(); iterator.hasNext(); ) {
                    AbstractItem next = iterator.next();
                    if (next.isFinished()) {
                        iterator.remove();
                        //Si c'est un ennemi ajoute des piece a ça mort
                        if (next instanceof AbstractEnemy) {
                            Point2D nextPosition = next.getPosition();
                            Money money = new Money(nextPosition.getX(), nextPosition.getY(), next.getMonnayeur().getValue());
                            aAjouter.add(money);

                        }
                    }
                }

                items.addAll(aAjouter);
                //Le joueur n'a plus de vie ou a atteint la du donjon
                if (player.getLifePoint() <= 0 || playerAtTheEnd()) {
                    finish.set(true);
                }
            }
        }
    };

    //Définie le joueur
    public World(AbstractMobileItem player) {
        start = Starter.getInstance();
        this.player = player;
        items.add(player);
    }

    public ReadOnlyBooleanProperty finishProperty() {
        return finish;
    }

    public void loadFloorFromFile(String fileName) throws Exception {
        try {
            floor = new FloorFromFile(fileName);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            floor = new FloorFromFile(fileName);

        }

    }

    @Override
    public Deque<Collection<? extends Drawable>> getDrawableFromSquare(Rectangle2D square) {
        Deque<Collection<? extends Drawable>> floorDrawableFromSquare = floor.getDrawableFromSquare(square);
        LinkedList linkedList = new LinkedList();
        floorDrawableFromSquare.addLast(linkedList);
        items.forEach(abstractItem -> {
            if (abstractItem.getBounds().intersects(square)) linkedList.add(abstractItem);
        });
        return floorDrawableFromSquare;
    }

    @Override
    public Dimension2D getDimension() {
        if (floor != null)
            return floor.getDimension();
        return new Dimension2D(0, 0);
    }


    @Override
    public void setDirectionPlayer(Direction direction) {
        if (!direction.equals(Direction.ANY) || player.getDestination() == null) {
            player.setDestination(null);
            player.setSpeed(direction.getDirection());
        }
    }

    @Override
    public void start() {
        if (!finishProperty().get()) {
            if (floor == null) {
                loadWorld(start.getCurrentUser().getLevel());
            }
            moteur.start();
        }
    }


    private void loadWorld(int i) {
        try {
            this.floor = new GenerateLaby(i);
            if (floor == null) {
                i = i % 4;
                loadFloorFromFile(mapPath + String.valueOf(i) + ".txt");
            }
            player.setSpeed(Point2D.ZERO);
            player.setDestination(null);
            player.setPosition(floor.getBeginning().getMinX(), floor.getBeginning().getMinY());

            items.clear();
            items.add(player);
            items.addAll(floor.getItems());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        moteur.stop();
    }


    @Override
    public Point2D centerOfPlayer() {
        Rectangle2D playerBounds = player.getBounds();
        double centerX = playerBounds.getWidth() / 2;
        double centerY = playerBounds.getHeight() / 2;
        return new Point2D(playerBounds.getMinX() + centerX, playerBounds.getMinY() + centerY);
    }

    public boolean playerAtTheEnd() {
        return floor.getEnd().contains(centerOfPlayer()) || player.getBounds().contains(floor.getEnd());
    }

    public void setPlayerDestination(Point2D coordinate) {
        player.setDestination(coordinate);
    }

    public void addFisrt(AbstractItem item) {
        items.addFirst(item);
    }

}
