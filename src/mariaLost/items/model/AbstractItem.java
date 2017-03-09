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
    private javafx.geometry.Dimension2D size;

    private Monnayeur monnayeur;

    public AbstractItem(double x, double y, double width, double height, Monnayeur m) {
        this.size = new Dimension2D(width, height);
        this.position = new Point2D(x, y);
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

    @Override
    public Rectangle2D getBounds() {
        return new Rectangle2D(position.getX(), position.getY(), size.getWidth(), size.getHeight());
    }
	public Point2D center(){
		return new Point2D(this.getBounds().getMinX()+this.getBounds().getWidth()/2
				,this.getBounds().getMinY()+this.getBounds().getHeight()/2);
	}
	private Point2D itemDirection(Point2D positionItem){
		return positionItem.subtract(this.center()).normalize();
	}
	
	// Return true if 2 item are considered aligned with a 5 pixel margin
	protected boolean aligned(Item item1Bound,Item item2Bound){
		Point2D center1=item1Bound.center();
		Point2D center2=item2Bound.center();
		int margin=5;
		return (center1.getX()-margin<=center2.getX()&&center2.getX()<=center1.getX()+margin)||
				(center1.getY()-margin<=center2.getY()&&center2.getY()<=center1.getY()+margin);
	}
	
	public boolean itemIsUp(Item item){
		return itemDirection(item.center()).getY()<Math.sin(-Math.PI/4);
	}
	public boolean itemIsRight(Item item){
		return itemDirection(item.center()).getX()>Math.cos(Math.PI/4);
	}
	public boolean itemIsLeft(Item item){
		return itemDirection(item.center()).getX()<Math.cos(3*Math.PI/4);
	}
	public boolean itemIsDown(Item item){
		return itemDirection(item.center()).getY()>Math.sin(Math.PI/4);
	}
	public Point2D speedToAlign(Item item){
		if(itemIsRight(item)||(itemIsLeft(item))){
			if(this.center().getY()<item.center().getY()){
				return Direction.DOWN.getDirection();
			}else{
				return Direction.UP.getDirection();
			}
		}else{
			if(this.center().getX()<item.center().getX()){
				return Direction.RIGHT.getDirection();
			}else{
				return Direction.LEFT.getDirection();
			}
		}
	}
	
	
}
