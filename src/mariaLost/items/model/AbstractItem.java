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
import mariaLost.parameters.Parameters_MariaLost;

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
        // previous position needs to be different from position at the begining
        this.previousPosition = new Point2D(0,0);
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
     * Determine if this item has moved
     *
     * @return True if the actual position is different from previous position
     */
    public boolean positionHasChanged() {
        return !this.getPosition().equals(previousPosition);
    }

    /**
     * Set the previous position to the actual position
     */
    public void setPreviousPositionToActual() {
        this.previousPosition = new Point2D(this.position.getX(), this.position.getY());
    }

    /**
     * Getter for item bounds
     *
     * @return a Rectangle2D which is the bound of this item.
     */
    @Override
    public Rectangle2D getBounds() {
        return new Rectangle2D(position.getX(), position.getY(), size.getWidth(), size.getHeight());
    }

    /**
     * Getter for the center of an item.
     *
     * @return a Point2D which is the center of this item bounds.
     */
    public Point2D center() {
        return new Point2D(this.getBounds().getMinX() + this.getBounds().getWidth() / 2
                , this.getBounds().getMinY() + this.getBounds().getHeight() / 2);
    }

    /**
     * Getter for the direction from this item to the Point2D "point"
     *
     * @param point The Point2d used to get the direction.
     * @return a Point2D as a vector from this item to the point parameter
     */
    public Point2D pointDirection(Point2D point) {
        return point.subtract(this.center()).normalize();
    }

    /**
     * Determine if an item is aligned with another item.
     * Two items are aligned if they have the same X or Y with a margin.
     * Margin is determined in parameters.
     *
     * @param itemBound The item bound to see if it is aligned with this item.
     * @return a boolean
     */
    protected boolean aligned(Item itemBound) {
        Point2D thisCenter = this.center();
        Point2D itemCenter = itemBound.center();
        return (thisCenter.getX() - Parameters_MariaLost.ALIGNEMENT_MARGIN <= itemCenter.getX() && itemCenter.getX() <= thisCenter.getX() + Parameters_MariaLost.ALIGNEMENT_MARGIN) ||
                (thisCenter.getY() - Parameters_MariaLost.ALIGNEMENT_MARGIN <= itemCenter.getY() && itemCenter.getY() <= thisCenter.getY() + Parameters_MariaLost.ALIGNEMENT_MARGIN);
    }

    /**
     * Determine if an item is upper than this item.
     *
     * @param item
     * @return a Boolean
     */
    public boolean isUp(Item item) {
        return pointDirection(item.center()).getY() <= Math.sin(-Math.PI / 4);
    }

    /**
     * Determine if an point is upper than this item.
     *
     * @param point
     * @return a Boolean
     */
    public boolean isUp(Point2D point) {
        return pointDirection(point).getY() <= Math.sin(-Math.PI / 4);
    }

    /**
     * Determine if an item is on the right of this item.
     *
     * @param item
     * @return a Boolean
     */
    public boolean isRight(Item item) {
        return pointDirection(item.center()).getX() >= Math.cos(Math.PI / 4);
    }

    /**
     * Determine if an point is on the right of this item.
     *
     * @param point
     * @return a Boolean
     */
    public boolean isRight(Point2D point) {
        return pointDirection(point).getX() >= Math.cos(Math.PI / 4);
    }

    /**
     * Determine if an item is on the left of this item.
     *
     * @param item
     * @return a Boolean
     */
    public boolean isLeft(Item item) {
        return pointDirection(item.center()).getX() <= Math.cos(3 * Math.PI / 4);
    }

    /**
     * Determine if an point is on the left of this item.
     *
     * @param point
     * @return a Boolean
     */
    public boolean isLeft(Point2D point) {
        return pointDirection(point).getX() <= Math.cos(3 * Math.PI / 4);
    }

    /**
     * Determine if an item is lower than this item.
     *
     * @param item
     * @return a Boolean
     */
    public boolean isDown(Item item) {
        return pointDirection(item.center()).getY() >= Math.sin(Math.PI / 4);
    }

    /**
     * Determine if an point is lower to this item.
     *
     * @param point
     * @return a Boolean
     */
    public boolean isDown(Point2D point) {
        return pointDirection(point).getY() >= Math.sin(Math.PI / 4);
    }

    /**
     * Determine if an item is on the upper left of this item.
     *
     * @param item
     * @return a Boolean
     */
    public boolean isUpperLeft(Item item) {
        Point2D direction = pointDirection(item.center());
        return Math.cos(5 * Math.PI / 6) < direction.getX()
                && direction.getX() < Math.cos(2 * Math.PI / 3)
                && direction.getY() <= 0;
    }

    /**
     * Determine if an point is on the upper left of this item.
     *
     * @param point
     * @return a Boolean
     */
    public boolean isUpperLeft(Point2D point) {
        Point2D direction = pointDirection(point);
        return Math.cos(5 * Math.PI / 6) < direction.getX()
                && direction.getX() < Math.cos(2 * Math.PI / 3)
                && direction.getY() <= 0;
    }

    /**
     * Determine if an item is on the lower left of this item.
     *
     * @param item
     * @return a Boolean
     */
    public boolean isLowerLeft(Item item) {
        Point2D direction = pointDirection(item.center());
        return Math.cos(5 * Math.PI / 6) < direction.getX()
                && direction.getX() < Math.cos(2 * Math.PI / 3)
                && direction.getY() >= 0;
    }

    /**
     * Determine if an point is on the lower left of this item.
     *
     * @param point
     * @return a Boolean
     */
    public boolean isLowerLeft(Point2D point) {
        Point2D direction = pointDirection(point);
        return Math.cos(5 * Math.PI / 6) < direction.getX()
                && direction.getX() < Math.cos(2 * Math.PI / 3)
                && direction.getY() >= 0;
    }

    /**
     * Determine if an item is on the upper right of this item.
     *
     * @param item
     * @return a Boolean
     */
    public boolean isUpperRight(Item item) {
        Point2D direction = pointDirection(item.center());
        return direction.getX() > Math.cos(Math.PI / 3)
                && direction.getX() < Math.cos(Math.PI / 6)
                && direction.getY() <= 0;
    }

    /**
     * Determine if an point is on the upper right of this item.
     *
     * @param point
     * @return a Boolean
     */
    public boolean isUpperRight(Point2D point) {
        Point2D direction = pointDirection(point);
        return direction.getX() > Math.cos(Math.PI / 3)
                && direction.getX() < Math.cos(Math.PI / 6)
                && direction.getY() <= 0;
    }

    /**
     * Determine if an item is on the lower right of this item.
     *
     * @param item
     * @return a Boolean
     */
    public boolean isLowerRight(Item item) {
        Point2D direction = pointDirection(item.center());
        return direction.getX() > Math.cos(Math.PI / 3)
                && direction.getX() < Math.cos(Math.PI / 6)
                && direction.getY() >= 0;
    }

    /**
     * Determine if an point is on the lower right of this item.
     *
     * @param point
     * @return a Boolean
     */
    public boolean isLowerRight(Point2D point) {
        Point2D direction = pointDirection(point);
        return direction.getX() > Math.cos(Math.PI / 3)
                && direction.getX() < Math.cos(Math.PI / 6)
                && direction.getY() >= 0;
    }

    /**
     * This method is used to get the correct speed to align with an item.
     *
     * @param item The item we want to align.
     * @return a Point2D which is the direction we need to go to align with item.
     */
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
