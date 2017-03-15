package mariaLost.items.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import mariaLost.gamePlay.tools.Monnayeur;
import mariaLost.items.interfaces.MobileItem;
import mariaLost.parameters.Parameters_MariaLost;

/**
 * Created by crede on 06/02/2017.
 */
public abstract class AbstractMobileItem extends AbstractItem implements MobileItem {
    protected double speedLimite;
    private Point2D speed = new Point2D(0, 0);
    private Point2D destination = null;
    protected DoubleProperty lifePoint = new SimpleDoubleProperty(Parameters_MariaLost.LIFE_POINT_START);


    public AbstractMobileItem(double x, double y, double width, double height, Monnayeur m) {
        this(x, y, width, height, m, 1.0);
    }

    public AbstractMobileItem(double x, double y, double width, double height, Monnayeur m, double speedLimite) {
        super(x, y, width, height, m);
        this.speedLimite = speedLimite;
    }

    @Override
    public void setSpeed(double vx, double vy) {
        setSpeed(new Point2D(vx, vy));
    }

    @Override
    public boolean isPassable() {
        return false;
    }

    @Override
    public Point2D getSpeed() {
        setDestination(getDestination());
        return speed;
    }

    @Override
    public void setSpeed(Point2D speed) {
        this.speed = speed.normalize().multiply(speedLimite);
    }

    @Override
    public void setPosition(Point2D position) {
        this.position = position;
    }

    @Override
    public void setPosition(double x, double y) {
        setPosition(new Point2D(x, y));
    }

    @Override
    public Point2D getDestination() {
        return destination;
    }

    @Override
    public void setDestination(Point2D destination) {
        if (this.destination != null && destination == null) {
            setSpeed(Point2D.ZERO);
        }
        this.destination = destination;
        if (destination != null) {
            Rectangle2D bounds = getBounds();
            double centerX = bounds.getWidth() / 2;
            double centerY = bounds.getHeight() / 2;
            Point2D center = new Point2D(bounds.getMinX() + centerX, bounds.getMinY() + centerY);
            setSpeed(destination.subtract(center));
        }
    }

    @Override
    public boolean isOnDestination() {
        return getDestination() != null && this.getBounds().contains(destination);
    }

   /* public ReadOnlyDoubleProperty getLifePoint(){
        return lifePoint;
    }*/
    public double getLifePoint(){
        return lifePoint.doubleValue();
    }

    public DoubleProperty lifePointPropertie() {
        return lifePoint;
    }
    public void setLifePoint(double lifePoint) {
        this.lifePoint.set(lifePoint);
    }

}
