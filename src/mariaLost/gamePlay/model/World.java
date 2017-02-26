package mariaLost.gamePlay.model;

import javafx.animation.AnimationTimer;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import mariaLost.gamePlay.interfaces.Model;
import mariaLost.gamePlay.tools.Direction;
import mariaLost.items.interfaces.Drawable;
import mariaLost.items.model.AbstractItem;
import mariaLost.items.model.AbstractMobileItem;
import mariaLost.parameters.Parameters_MariaLost;

import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by crede on 06/02/2017.
 */
public class World implements Model {

    private AbstractFloor floor;
    private Collection<AbstractItem> items = new LinkedList<>();

    private AbstractMobileItem player;

    private int world = 0;

    private String mapPath = Parameters_MariaLost.FILEPATH_MAP;

    private AnimationTimer moteur = new AnimationTimer() {
        private long time = 0;
        private long delay = 16000000L;

        @Override
        public void handle(long now) {
            if (now - time >= delay) {
                time = now;
                Dimension2D dimension = floor.getDimension();
                Collection<AbstractItem> itemFromSquare = (Collection<AbstractItem>) floor.getItemFromSquare(new Rectangle2D(0, 0, dimension.getWidth(), dimension.getHeight()));
                itemFromSquare.addAll(items);
                MoteurPhysique.move(itemFromSquare);
                items.removeIf(abstractItem -> abstractItem.isFinished());

                if (playerAtTheEnd()) {
                    world = (world + 1) % 3;
                    loadWorld(world);
                }
            }
        }
    };


    public World(AbstractMobileItem player) {
        this.player = player;
        items.add(player);
    }
    public void loadFloor(int hauteur, int largeur) throws Exception{
        floor = new GeneratedFloor(hauteur, largeur);
        Dimension2D dimension = floor.getDimension();
        player.setSpeed(Point2D.ZERO);
        player.setDestination(null);
        player.setPosition(floor.getBeginning().getMinX(), floor.getBeginning().getMinY());

        items.clear();
        items.add(player);
    }

    public void loadFloorFromFile(String fileName) throws Exception {
        floor = new FloorFromFile(fileName);
        Dimension2D dimension = floor.getDimension();
        player.setSpeed(Point2D.ZERO);
        player.setDestination(null);
        player.setPosition(floor.getBeginning().getMinX(), floor.getBeginning().getMinY());

        items.clear();
        items.add(player);
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
        if (floor == null) {
            loadWorld(world);
        }
        moteur.start();
    }

    private void loadWorld(int i) {
        try {
            loadFloor(Parameters_MariaLost.NUMBER_OF_CASE_Y, Parameters_MariaLost.NUMBER_OF_CASE_X);

           // loadFloorFromFile(mapPath + String.valueOf(i) + ".txt");
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

}
