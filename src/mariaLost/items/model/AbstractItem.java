package mariaLost.items.model;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import mariaLost.items.interfaces.Drawable;
import mariaLost.items.interfaces.Item;

/**
 * Created by crede on 06/02/2017.
 */
public abstract class AbstractItem implements Item, Drawable {
    protected Point2D position;
    private javafx.geometry.Dimension2D size;

    public AbstractItem(double x, double y, double width, double height) {
        this.size = new Dimension2D(width, height);
        this.position = new Point2D(x, y);
    }

    @Override

    public Point2D getPosition() {
        return position;
    }

    @Override
    public Rectangle2D getBounds() {
        return new Rectangle2D(position.getX(), position.getY(), size.getWidth(), size.getHeight());
    }
}
