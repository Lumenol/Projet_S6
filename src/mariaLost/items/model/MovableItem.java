package mariaLost.items.model;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import mariaLost.items.interfaces.Movable;
import mariaLost.mainApp.model.Parameters;

/**
 * Created by elsacollet on 01/02/2017.
 */
public class MovableItem extends Item implements Movable, mariaLost.items.interfaces.Item {


    private Point2D speed = new Point2D(0, 0);
    private Rectangle2D size;
    /**
     * Constructeur d'un objet petit joeur sur le plateau
     *
     * @param name       name of the real player
     * @param spriteName image use to represente the player
     * @param passable   capacity of an item to be passable or not
     */
    public MovableItem(String name, String spriteName, boolean passable) {
        super(name, spriteName, passable);
        size = new Rectangle2D(super.getPosition().getX(), super.getPosition().getY(), Parameters.MOVABLE_ITEM_WIDTH, Parameters.MOVABLE_ITEM_HEIGHT);
    }

    public MovableItem(MovableItem movableItem){
        super(movableItem.getName(), movableItem.getSpriteName(), movableItem.isPassable());
        super.size = new Rectangle2D(super.getPosition().getX(), super.getPosition().getY(), Parameters.MOVABLE_ITEM_WIDTH, Parameters.MOVABLE_ITEM_HEIGHT);

    }

    public Rectangle2D getSize() {
        return this.size;
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

    @Override
    public Point2D getPosition() {
        return super.getPosition();
    }

    @Override
    public boolean isPassable() {
        return super.isPassable();
    }

    @Override
    public Rectangle2D getBounds() {
        return new Rectangle2D(position.getX(), position.getY(), size.getHeight(), size.getWidth());
    }
}
