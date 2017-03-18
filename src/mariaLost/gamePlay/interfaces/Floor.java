package mariaLost.gamePlay.interfaces;

import javafx.geometry.Dimension2D;
import javafx.geometry.Rectangle2D;
import mariaLost.items.model.AbstractItem;

import java.util.Collection;
import java.util.Deque;

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
    Deque<? extends AbstractItem> getItemFromSquare(Rectangle2D square);

    /**
     * Retourne les dimmentions de l'etage
     *
     * @return dimensions
     */
    Dimension2D getDimension();

    /*
    Liste des items tel que les pieces et montre
     */
    Collection<AbstractItem> getItems();

    /**
     * @return Zone de départ
     */
    Rectangle2D getBeginning();

    /**
     * @return Zone de d'arrivée
     */
    Rectangle2D getEnd();

}
