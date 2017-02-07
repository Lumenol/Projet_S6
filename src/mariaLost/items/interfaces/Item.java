package mariaLost.items.interfaces;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;


/**
 * Created by crede on 31/01/2017.
 */
public interface Item {
    Point2D getPosition();

    boolean isPassable();

    Rectangle2D getBounds();
}
