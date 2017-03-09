package mariaLost.items.model;

import mariaLost.gamePlay.tools.Direction;
import mariaLost.items.model.animation.Animation;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class Attack{
	
	private AreaOfEffect aoe;
	private int damage;
	private Animation animation;
	private Duration duration;
	private double startTime=System.currentTimeMillis();
	private Point2D attackStartingPoint;
	private boolean isAttacking=false;
	private boolean hasDealtDamage;

	
	public Attack(AreaOfEffect aoe,int damage,Animation animation){
		this.aoe=aoe;
		this.damage=damage;
		this.animation=animation;
		this.duration=aoe.getDuration();
	}
	public void start(Point2D attackStartingPoint,Direction direction){
		aoe.changeDirectionAttack(direction);
		isAttacking=true;
		hasDealtDamage=false;
		this.attackStartingPoint=attackStartingPoint;
		startTime=System.currentTimeMillis();
		animation.play();
	}
	
	private void stop(){
		isAttacking=false;
		animation.stop();
	}
	
	public boolean isRunning(){
		return isAttacking&&(System.currentTimeMillis()-startTime)<duration.toMillis();
	}
	
	final public Image getImage(){
		return animation.getImage();
	}	
	
	public boolean hasHit(Rectangle2D itemBound){
		if(!hasDealtDamage){
			if(!isRunning()){
				stop();
				return false;
			}
			if(aoe.hasHit(attackStartingPoint,itemBound,System.currentTimeMillis()-startTime)){
				hasDealtDamage=true;
				return true;
			}
		}
		return false;
	}
	
	
	public int getDamage() {
		return damage;
	}
}
