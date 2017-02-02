package mariaLost.items.model;

import javafx.geometry.Point2D;
import mariaLost.items.interfaces.Movable;

/**
 * Created by elsacollet on 01/02/2017.
 */
public class LittlePlayer extends Item implements Movable {


    private Point2D speed = new Point2D(0, 0);

    /**
     * Constructeur d'un objet petit joeur sur le plateau
     *
     * @param name       name of the real player
     * @param spriteName image use to represente the player
     * @param passable   capacity of an item to be passable or not
     */
    public LittlePlayer(String name, String spriteName, boolean passable) {
        super(name, spriteName, passable);
    }

    public int getSize() {
        return size;
    }

    @Override
    public Point2D getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(Point2D speed) {
        this.speed = speed;
    }

    @Override
    public void setSpeed(double vx, double vy) {
        setSpeed(new Point2D(vx, vy));
    }

    @Override
    public void setPosition(Point2D position) {
        super.position = position;
    }

    @Override
    public void setPosition(double x, double y) {
        setPosition(new Point2D(x, y));
    }

}
