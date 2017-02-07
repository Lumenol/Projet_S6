package mariaLost.gamePlay.interfaces;

import javafx.geometry.Dimension2D;
import javafx.geometry.Rectangle2D;
import mariaLost.items.interfaces.Item;

import java.util.Collection;

/**
 * Created by crede on 31/01/2017.
 */
public interface Floor {

    /**
     * Retourne les Items present dans la zone
     *
     * @param square zone
     * @return Item de la zone
     */
    Collection<? extends Item> getItemFromSquare(Rectangle2D square);

    /**
     * Retourne les dimmention de l'etage
     * @return dimensions
     */
    Dimension2D getDimension();

    Rectangle2D getBeginning();

    Rectangle2D getEnd();

}
