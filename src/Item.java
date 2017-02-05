import javafx.geometry.Bounds;
import javafx.geometry.Point2D;


/**
 * Created by crede on 31/01/2017.
 */
public interface Item {
    Point2D getPosition();

    boolean isPassable();

    Bounds getBounds();
}