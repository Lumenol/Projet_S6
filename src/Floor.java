import javafx.geometry.Dimension2D;
import javafx.geometry.Rectangle2D;

import java.util.Collection;

/**
 * Created by crede on 31/01/2017.
 */
public interface Floor {

    Collection<? extends Item> getItemFromSquare(Rectangle2D square);

    Dimension2D getDimension();

}
