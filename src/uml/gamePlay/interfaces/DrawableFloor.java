package uml.gamePlay.interfaces;

import javafx.geometry.Dimension2D;
import javafx.geometry.Rectangle2D;

import java.util.Collection;

/**
 * Created by crede on 06/02/2017.
 */
public interface DrawableFloor {

    /**
     * Retourne les Drawables present dans la zone organise par couches
     *
     * @param square zone
     * @return les plus profond en premier
     */
    Collection<Collection<? extends Drawable>> getDrawableFromSquare(Rectangle2D square);

    /**
     * Retourne les dimmention de l'etage
     *
     * @return dimensions
     */
    Dimension2D getDimension();
}
