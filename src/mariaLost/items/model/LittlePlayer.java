package mariaLost.items.model;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import mariaLost.items.interfaces.Drawable;
import mariaLost.items.interfaces.Movable;

/**
 * Created by elsacollet on 01/02/2017.
 */
public class LittlePlayer extends Item implements Movable{


    private Point2D speed = new Point2D(0, 0);
    private BoundingBox boundingBox = new BoundingBox(10 * 16, 10 * 16, 14, 14);
    /**
     * Constructeur d'un objet petit joeur sur le plateau
     * @param name name of the real player
     * @param spriteName image use to represente the player
     * @param passable capacity of an item to be passable or not
     */
    public LittlePlayer(String name, String spriteName, boolean passable) {
        super(name, spriteName, passable);
    }

    public int getSize(){
        return 40;
    }

    @Override
    public Point2D getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(Point2D speed) {
        this.speed =speed;
    }

    @Override
    public void setSpeed(double vx, double vy) {
        setSpeed(new Point2D(vx, vy));
    }

    @Override
    public void setPosition(Point2D position) {
        boundingBox = new BoundingBox(position.getX(), position.getY(), boundingBox.getWidth(), boundingBox.getHeight());

    }
    @Override
    public void setPosition(double x, double y) {

        setPosition(new Point2D(x, y));
    }


    public Point2D getPosition() {
        return new Point2D(boundingBox.getMinX(), boundingBox.getMinY());
    }

    public BoundingBox getBoundingBox() {
        return boundingBox;
    }

}
