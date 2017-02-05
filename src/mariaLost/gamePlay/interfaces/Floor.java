package mariaLost.gamePlay.interfaces;

import javafx.geometry.Dimension2D;
import javafx.geometry.Rectangle2D;
import mariaLost.items.model.Item;

import java.util.Collection;


/**
 * Created by elsacollet on 01/02/2017.
 */
public interface Floor<T> {
    Collection<? extends Item> getItemsFromSquare(Rectangle2D square);

    Dimension2D getDimension();

    void add(T item);

    void setLengthX(int i);

    void setLengthY(int i);
}
