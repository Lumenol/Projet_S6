package mariaLost.gamePlay.model;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.util.Duration;
import mariaLost.gamePlay.interfaces.Model;
import mariaLost.gamePlay.tools.Direction;
import mariaLost.items.interfaces.Drawable;
import mariaLost.items.interfaces.Item;
import mariaLost.items.model.AbstractMobileItem;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by crede on 06/02/2017.
 */
public class World implements Model {

    private AbstractFloor floor;
    private Collection<AbstractMobileItem> mobileItem = new LinkedList<>();

    private AbstractMobileItem player;

    private ScheduledService<Void> moteur = new ScheduledService<Void>() {
        @Override
        protected Task<Void> createTask() {
            return new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    Dimension2D dimension = floor.getDimension();
                    MoteurPhysique.move(floor.getItemFromSquare(new Rectangle2D(0, 0, dimension.getWidth(), dimension.getHeight())), mobileItem);
                    return null;
                }
            };
        }
    };


    public World(AbstractMobileItem player) {
        this.player = player;
        mobileItem.add(player);

        moteur.setPeriod(Duration.millis(16));
    }

    public void loadFloorFromFile(String fileName) throws Exception {
        floor = new FloorFromFile(fileName);
        Dimension2D dimension = floor.getDimension();
        player.setSpeed(Point2D.ZERO);
        Collection<? extends Item> itemFromSquare = floor.getItemFromSquare(new Rectangle2D(0, 0, dimension.getWidth(), dimension.getHeight()));

        for (Iterator<? extends Item> iterator = itemFromSquare.iterator(); iterator.hasNext(); ) {
            Item next = iterator.next();
            if (next.isPassable()) {
                player.setPosition(next.getPosition());
                break;
            }
        }

    }

    @Override
    public Collection<Collection<? extends Drawable>> getDrawableFromSquare(Rectangle2D square) {
        Collection<Collection<? extends Drawable>> floorDrawableFromSquare = floor.getDrawableFromSquare(square);
        floorDrawableFromSquare.add(mobileItem);
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
        player.setSpeed(direction.getDirection());
    }

    @Override
    public void start() {
        moteur.start();
    }

    @Override
    public void stop() {
        moteur.cancel();
    }


    @Override
    public Point2D centerOfPlayer() {
        Rectangle2D playerBounds = player.getBounds();
        double centerX = playerBounds.getWidth() / 2;
        double centerY = playerBounds.getHeight() / 2;
        return new Point2D(playerBounds.getMinX() + centerX, playerBounds.getMinY() + centerY);
    }
}
