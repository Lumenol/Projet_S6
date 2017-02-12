package mariaLost.items.model;

import javafx.geometry.Point2D;
import mariaLost.items.interfaces.MobileItem;

/**
 * Created by crede on 06/02/2017.
 */
public abstract class AbstractMobileItem extends AbstractItem implements MobileItem {
    protected double speedLimite;
    private Point2D speed = new Point2D(0, 0);
    private Point2D destination=new Point2D(0,0);

    public AbstractMobileItem(double x, double y, double width, double height) {
        this(x, y, width, height, 1.0);
    }

    public AbstractMobileItem(double x, double y, double width, double height, double speedLimite) {
        super(x, y, width, height);
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
    public void setDestination(Point2D destination){
    	this.destination=destination;
    }
    
    @Override
    public Point2D getDestination(){
    	return destination;
    }
    @Override
    public boolean isOnDestination(){
    	return this.getBounds().contains(destination);
    }
    
}
