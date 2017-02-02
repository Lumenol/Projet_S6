package mariaLost.gamePlay.interfaces;

import javafx.geometry.Dimension2D;
import javafx.geometry.Rectangle2D;
import mariaLost.items.interfaces.Item;

import java.util.Collection;


/**
 * Created by elsacollet on 01/02/2017.
 */
public interface Floor {
    Collection<? extends Item> getItemFromSquare(Rectangle2D square);

    Dimension2D getDimension();
}
