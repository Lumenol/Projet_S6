package mariaLost.gamePlay.interfaces;

import javafx.geometry.Point2D;

/**
 * Created by crede on 06/02/2017.
 */
public interface Followable extends DrawableFloor {
    /**
     * @return point au centre du personnage
     */
    Point2D centerOfPlayer();
}
