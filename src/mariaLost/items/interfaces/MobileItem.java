package mariaLost.items.interfaces;

import javafx.geometry.Point2D;

/**
 * Created by crede on 31/01/2017.
 */
public interface MobileItem extends Item {

    /**
     * current speed getter
     *
     * @return a Point2D
     */
    Point2D getSpeed();

    /**
     * Speed setter
     *
     * @param speed
     */
    void setSpeed(Point2D speed);

    /**
     * Speed setter
     *
     * @param vx
     * @param vy
     */
    void setSpeed(double vx, double vy);

    /**
     * Position setter
     *
     * @param position
     */
    void setPosition(Point2D position);

    /**
     * Position setter
     *
     * @param position
     */
    void setPosition(double x, double y);

    /**
     * Destination getter
     *
     * @return
     */
    Point2D getDestination();

    /**
     * Destination setter
     *
     * @param destination
     */
    void setDestination(Point2D destination);

    /**
     * Return true if this item is on its destination
     *
     * @return a boolean
     */
    boolean isOnDestination();

}
