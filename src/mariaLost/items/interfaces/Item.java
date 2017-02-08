package mariaLost.items.interfaces;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;


/**
 * Created by crede on 31/01/2017.
 */
public interface Item {
    /**
     * Permet de savoir si un Item a fini ça vie
     *
     * @return vrai si fini
     */
    boolean isFinished();

    /**
     * Position d'un Item dans le plan
     * @return
     */
    Point2D getPosition();

    /**
     * Vrai si l'Item est franchissable
     * @return
     */
    boolean isPassable();

    /**
     * Zone occupe par l'Item
     * @return
     */
    Rectangle2D getBounds();
}
