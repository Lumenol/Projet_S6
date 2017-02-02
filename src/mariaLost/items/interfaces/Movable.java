package mariaLost.items.interfaces;

import javafx.geometry.Point2D;

/**
 * Created by elsacollet on 01/02/2017.
 */
public interface Movable extends Item {
    Point2D getSpeed();

    void setSpeed(Point2D speed);

    void setSpeed(double vx, double vy);

    void setPosition(Point2D position);

    void setPosition(double x, double y);
}
