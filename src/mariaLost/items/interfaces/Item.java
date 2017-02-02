package mariaLost.items.interfaces;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;

/**
 * Created by elsacollet on 01/02/2017.
 */
public interface Item {
    Point2D getPosition();

    boolean isPassable();

    Bounds getBounds();

}
