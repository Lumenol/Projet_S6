package mariaLost.items.model;


import mariaLost.gamePlay.tools.Direction;
import mariaLost.items.model.animation.Animation;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.util.Duration;

public class MeleeAttack extends AbstractAttack{
	
	protected Point2D attackStartingPoint;
	protected boolean isAttacking=false;
	protected Dimension2D aoe;
	
	
	public MeleeAttack(Dimension2D areaOfEffect, Direction direction,int damage,Animation animation,Duration duration){
		super(damage,animation,duration,direction);
		this.aoe=areaOfEffect;
	}
	
	public void start(Point2D attackStartingPoint,Direction direction){
		changeDirectionAttack(direction);
		isAttacking=true;
		this.attackStartingPoint=attackStartingPoint;
		animation.play();
		timer.start();	
	}
	
	
	private void stop(){
		isAttacking=false;
		animation.stop();
		timer.end();
	}
	
	
	public boolean isRunning(){
		return isAttacking&&!timer.isOver();
	}
	
	
	public boolean hasHit(Rectangle2D itemBound){
		if(isRunning()){
			return hasHit(attackStartingPoint,itemBound);
		}
		stop();
		return false;
	}


	
	private boolean hasHit(Point2D attackStartingPoint,Rectangle2D itemBound){
		Rectangle2D areaOfHit;
		if(direction.equals(Direction.RIGHT)){
			areaOfHit=new Rectangle2D(attackStartingPoint.getX()
					,attackStartingPoint.getY()-(aoe.getHeight()/2)
					,aoe.getWidth(),
					aoe.getHeight());
		}else if(direction.equals(Direction.LEFT)){
			areaOfHit=new Rectangle2D(attackStartingPoint.getX()-aoe.getWidth()
					,attackStartingPoint.getY()-(aoe.getHeight()/2)
					,aoe.getWidth(),
					aoe.getHeight());
		}else if(direction.equals(Direction.DOWN)){
			areaOfHit=new Rectangle2D(attackStartingPoint.getX()-(aoe.getWidth()/2)
					,attackStartingPoint.getY()+aoe.getHeight()
					,aoe.getWidth(),
					aoe.getHeight());
		}else{
			areaOfHit=new Rectangle2D(attackStartingPoint.getX()-(aoe.getWidth()/2)
					,attackStartingPoint.getY()-aoe.getHeight()
					,aoe.getWidth(),
					aoe.getHeight());
		}
		return areaOfHit.intersects(itemBound);
	}
	
	private void changeDirectionAttack(Direction newDirection){
		if(direction.equals(Direction.LEFT)||direction.equals(Direction.RIGHT)){
			if(newDirection.equals(Direction.UP)||newDirection.equals(Direction.DOWN)){
				aoe=new Dimension2D(aoe.getHeight(),aoe.getWidth());
			}
		}else{
			if(newDirection.equals(Direction.LEFT)||newDirection.equals(Direction.RIGHT)){
				aoe= new Dimension2D(aoe.getHeight(),aoe.getWidth());
			}
		}
		direction=newDirection;
	}

}