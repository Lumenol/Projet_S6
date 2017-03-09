package mariaLost.items.model;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.util.Duration;
import mariaLost.gamePlay.tools.Direction;

public class AreaOfEffect {


	private Rectangle2D areaOfEffect;
	private Direction direction;
	private int distance;
	private Duration duration;
	

	public AreaOfEffect(Rectangle2D areaOfEffect, Direction direction, Duration duration, int distance) {
		this.areaOfEffect = areaOfEffect;
		this.direction = direction;
		this.duration = duration;
		this.distance = distance;
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
	//TODO add the distance component
	public boolean hasHit(Point2D attackStartingPoint,Rectangle2D itemBound,double timeElapsed){
		
		//System.out.println("area of effect has hit checking");
		double speed=distance/duration.toMillis();
		double distanceTraveled=speed*timeElapsed;
		
		Rectangle2D areaOfHit;
		if(direction.equals(Direction.RIGHT)){
			System.out.println("attack right");
			areaOfHit=new Rectangle2D(attackStartingPoint.getX()
					,attackStartingPoint.getY()-(areaOfEffect.getHeight()/2)
					,areaOfEffect.getWidth(),
					areaOfEffect.getHeight());
		}else if(direction.equals(Direction.LEFT)){
			System.out.println("attack left");
			areaOfHit=new Rectangle2D(attackStartingPoint.getX()-areaOfEffect.getWidth()
					,attackStartingPoint.getY()-(areaOfEffect.getHeight()/2)
					,areaOfEffect.getWidth(),
					areaOfEffect.getHeight());
		}else if(direction.equals(Direction.DOWN)){
			System.out.println("attack down");
			areaOfHit=new Rectangle2D(attackStartingPoint.getX()-(areaOfEffect.getWidth()/2)
					,attackStartingPoint.getY()+areaOfEffect.getHeight()
					,areaOfEffect.getWidth(),
					areaOfEffect.getHeight());
		}else{
			System.out.println("attack up");
			areaOfHit=new Rectangle2D(attackStartingPoint.getX()-(areaOfEffect.getWidth()/2)
					,attackStartingPoint.getY()-areaOfEffect.getHeight()
					,areaOfEffect.getWidth(),
					areaOfEffect.getHeight());
		}
		return areaOfHit.intersects(itemBound);
	}
	public Duration getDuration() {
		return duration;
	}
	
}
