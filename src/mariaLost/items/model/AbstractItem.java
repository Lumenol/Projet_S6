package mariaLost.items.model;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import mariaLost.gamePlay.tools.Direction;
import mariaLost.gamePlay.tools.Monnayeur;
import mariaLost.items.interfaces.ActionableItem;
import mariaLost.items.interfaces.Drawable;
import mariaLost.items.interfaces.Item;
import mariaLost.items.interfaces.MobileItem;

/**
 * Created by crede on 06/02/2017.
 */
public abstract class AbstractItem implements Item, MobileItem, ActionableItem, Drawable {
    protected Point2D position;
    private Point2D previousPosition;
    private javafx.geometry.Dimension2D size;

    private Monnayeur monnayeur;

    public AbstractItem(double x, double y, double width, double height, Monnayeur m) {
        this.size = new Dimension2D(width, height);
        this.position = new Point2D(x, y);
        this.previousPosition=new Point2D(x, y);;
        monnayeur = m;
    }

    @Override
    public Monnayeur getMonnayeur() {
        return monnayeur;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Point2D getDestination() {
        return null;
    }

    @Override
    public void setDestination(Point2D destination) {

    }

    @Override
    public boolean isOnDestination() {
        return false;
    }

    @Override
    public Point2D getSpeed() {
        return Point2D.ZERO;
    }

    @Override
    public void setSpeed(Point2D speed) {

    }

    @Override
    public void action(Item o) {

    }

    @Override
    public void setSpeed(double vx, double vy) {

    }

    @Override
    public void setPosition(double x, double y) {

    }

    @Override
    public Point2D getPosition() {
        return position;
    }

    @Override
    public void setPosition(Point2D position) {

    }
    
    /**
     * 
     * @return
     */
	public boolean positionHasChanged(){
		return !this.getPosition().equals(previousPosition);
	}
	public void setPreviousPositionToActual(){
        this.previousPosition=new Point2D(this.position.getX(),this.position.getY());
	}
	
    @Override
    public Rectangle2D getBounds() {
        return new Rectangle2D(position.getX(), position.getY(), size.getWidth(), size.getHeight());
    }


    public Point2D center() {
        return new Point2D(this.getBounds().getMinX() + this.getBounds().getWidth() / 2
                , this.getBounds().getMinY() + this.getBounds().getHeight() / 2);
    }

    public Point2D pointDirection(Point2D point) {
        return point.subtract(this.center()).normalize();
    }

    // Return true if 2 item are considered aligned with a 5 pixel margin
    protected boolean aligned(Item item1Bound, Item item2Bound) {
        Point2D center1 = item1Bound.center();
        Point2D center2 = item2Bound.center();
        int margin = 5;
        return (center1.getX() - margin <= center2.getX() && center2.getX() <= center1.getX() + margin) ||
                (center1.getY() - margin <= center2.getY() && center2.getY() <= center1.getY() + margin);
    }

    public boolean isUp(Item item) {
        return pointDirection(item.center()).getY() <= Math.sin(-Math.PI / 4);
    }

    public boolean isUp(Point2D point) {
        return pointDirection(point).getY() <= Math.sin(-Math.PI / 4);
    }

    public boolean isRight(Item item) {
        return pointDirection(item.center()).getX() >= Math.cos(Math.PI / 4);
    }

    public boolean isRight(Point2D point) {
        return pointDirection(point).getX() >= Math.cos(Math.PI / 4);
    }

    public boolean isLeft(Item item) {
        return pointDirection(item.center()).getX() <= Math.cos(3 * Math.PI / 4);
    }

    public boolean isLeft(Point2D point) {
        return pointDirection(point).getX() <= Math.cos(3 * Math.PI / 4);
    }

    public boolean isDown(Item item) {
        return pointDirection(item.center()).getY() >= Math.sin(Math.PI / 4);
    }

    public boolean isDown(Point2D point) {
        return pointDirection(point).getY() >= Math.sin(Math.PI / 4);
    }

    public boolean isUpperLeft(Item item) {
        Point2D direction = pointDirection(item.center());
        return Math.cos(5 * Math.PI / 6) < direction.getX()
                && direction.getX() < Math.cos(2 * Math.PI / 3)
                && direction.getY() <= 0;
    }

    public boolean isUpperLeft(Point2D point) {
        Point2D direction = pointDirection(point);
        return Math.cos(5 * Math.PI / 6) < direction.getX()
                && direction.getX() < Math.cos(2 * Math.PI / 3)
                && direction.getY() <= 0;
    }

    public boolean isLowerLeft(Item item) {
        Point2D direction = pointDirection(item.center());
        return Math.cos(5 * Math.PI / 6) < direction.getX()
                && direction.getX() < Math.cos(2 * Math.PI / 3)
                && direction.getY() >= 0;
    }

    public boolean isLowerLeft(Point2D point) {
        Point2D direction = pointDirection(point);
        return Math.cos(5 * Math.PI / 6) < direction.getX()
                && direction.getX() < Math.cos(2 * Math.PI / 3)
                && direction.getY() >= 0;
    }

    public boolean isUpperRight(Item item) {
        Point2D direction = pointDirection(item.center());
        return direction.getX() > Math.cos(Math.PI / 3)
                && direction.getX() < Math.cos(Math.PI / 6)
                && direction.getY() <= 0;
    }

    public boolean isUpperRight(Point2D point) {
        Point2D direction = pointDirection(point);
        return direction.getX() > Math.cos(Math.PI / 3)
                && direction.getX() < Math.cos(Math.PI / 6)
                && direction.getY() <= 0;
    }

    public boolean isULowerRight(Item item) {
        Point2D direction = pointDirection(item.center());
        return direction.getX() > Math.cos(Math.PI / 3)
                && direction.getX() < Math.cos(Math.PI / 6)
                && direction.getY() >= 0;
    }

    public boolean isLowerRight(Point2D point) {
        Point2D direction = pointDirection(point);
        return direction.getX() > Math.cos(Math.PI / 3)
                && direction.getX() < Math.cos(Math.PI / 6)
                && direction.getY() >= 0;
    }


    public Point2D speedToAlign(Item item) {
        if (isRight(item) || (isLeft(item))) {
            if (this.center().getY() < item.center().getY()) {
                return Direction.DOWN.getDirection();
            } else {
                return Direction.UP.getDirection();
            }
        } else {
            if (this.center().getX() < item.center().getX()) {
                return Direction.RIGHT.getDirection();
            } else {
                return Direction.LEFT.getDirection();
            }
        }
    }


}
