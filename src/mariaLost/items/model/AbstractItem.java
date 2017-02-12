package mariaLost.items.model;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import mariaLost.items.interfaces.ActionableItem;
import mariaLost.items.interfaces.Drawable;
import mariaLost.items.interfaces.Item;
import mariaLost.items.interfaces.MobileItem;

/**
 * Created by crede on 06/02/2017.
 */
public abstract class AbstractItem implements Item, MobileItem, ActionableItem, Drawable {
    protected Point2D position;
    private javafx.geometry.Dimension2D size;

    public AbstractItem(double x, double y, double width, double height) {
        this.size = new Dimension2D(width, height);
        this.position = new Point2D(x, y);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Point2D getDestination() {
        return null;
    }

    @Override
    public void setDestination(Point2D destination) {

    }

    @Override
    public boolean isOnDestination() {
        return false;
    }

    @Override
    public Point2D getSpeed() {
        return Point2D.ZERO;
    }

    @Override
    public void setSpeed(Point2D speed) {

    }

    @Override
    public void action(Item o) {

    }

    @Override
    public void setSpeed(double vx, double vy) {

    }

    @Override
    public void setPosition(double x, double y) {

    }

    @Override
    public Point2D getPosition() {
        return position;
    }

    @Override
    public void setPosition(Point2D position) {

    }

    @Override
    public Rectangle2D getBounds() {
        return new Rectangle2D(position.getX(), position.getY(), size.getWidth(), size.getHeight());
    }
}
