package mariaLost.items.model;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import mariaLost.gamePlay.tools.Direction;

public class AreaOfEffect {


	private Rectangle2D areaOfEffect;
	private Direction direction;
	

	public AreaOfEffect(Rectangle2D areaOfEffect, Direction direction) {
		this.areaOfEffect = areaOfEffect;
		this.direction = direction;
	}
	

	public void changeDirectionAttack(Direction newDirection){
		if(direction.equals(Direction.LEFT)||direction.equals(Direction.RIGHT)){
			if(newDirection.equals(Direction.UP)||newDirection.equals(Direction.DOWN)){
				areaOfEffect=new Rectangle2D(0,0,areaOfEffect.getHeight(),areaOfEffect.getWidth());
			}
		}else{
			if(newDirection.equals(Direction.LEFT)||newDirection.equals(Direction.RIGHT)){
				areaOfEffect= new Rectangle2D(0,0,areaOfEffect.getHeight(),areaOfEffect.getWidth());
			}
		}
		direction=newDirection;
	}
	
	// return true if an item intersect with the rectangle2D areaofEffect put into the plan according to a starting point and a direction
	public boolean hasHit(Point2D attackStartingPoint,Rectangle2D itemBound){
		Rectangle2D areaOfHit;
		if(direction.equals(Direction.RIGHT)){
			areaOfHit=new Rectangle2D(attackStartingPoint.getX()
					,attackStartingPoint.getY()-(areaOfEffect.getHeight()/2)
					,areaOfEffect.getWidth(),
					areaOfEffect.getHeight());
		}else if(direction.equals(Direction.LEFT)){
			areaOfHit=new Rectangle2D(attackStartingPoint.getX()-areaOfEffect.getWidth()
					,attackStartingPoint.getY()-(areaOfEffect.getHeight()/2)
					,areaOfEffect.getWidth(),
					areaOfEffect.getHeight());
		}else if(direction.equals(Direction.DOWN)){
			areaOfHit=new Rectangle2D(attackStartingPoint.getX()-(areaOfEffect.getWidth()/2)
					,attackStartingPoint.getY()+areaOfEffect.getHeight()
					,areaOfEffect.getWidth(),
					areaOfEffect.getHeight());
		}else{
			areaOfHit=new Rectangle2D(attackStartingPoint.getX()-(areaOfEffect.getWidth()/2)
					,attackStartingPoint.getY()-areaOfEffect.getHeight()
					,areaOfEffect.getWidth(),
					areaOfEffect.getHeight());
		}
		return areaOfHit.intersects(itemBound);
	}

	
}
