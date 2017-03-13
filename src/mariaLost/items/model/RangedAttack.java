package mariaLost.items.model;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.util.Duration;
import mariaLost.gamePlay.tools.Direction;
import mariaLost.gamePlay.tools.Monnayeur;
import mariaLost.items.interfaces.Item;
import mariaLost.items.model.animation.Animation;

public class RangedAttack extends AbstractMobileItem{

	protected int damage; 
	protected Animation animation;
	protected Duration duration;
	protected double startTime;
	
	
	public RangedAttack(Point2D startingPoint, Dimension2D areaOfEffect,Direction direction,int damage,Animation animation,Duration duration,double speedLimite) {
		super(startingPoint.getX(), startingPoint.getY(), areaOfEffect.getWidth(), areaOfEffect.getHeight(), new Monnayeur(0),speedLimite);
		setSpeed(direction.getDirection());
		this.damage=damage;
		this.animation=animation;
		this.duration=duration;
		startTime=System.currentTimeMillis();
	}
	
	public Image getImage() {
		return animation.getImage();
	}

	public int getDamage() {
		return damage;
	}

	public boolean isRunning() {
		return System.currentTimeMillis()-startTime<duration.toMillis();
	}
	
	@Override
	public boolean isPassable() {
	    return true;
	}

    public boolean isFinished() {
        return !isRunning();
    }
    
    @Override
    public void action(Item o) {
    	if(o instanceof AbstractEnemy)
    		((AbstractEnemy) o).takeDamage(getDamage());
    	startTime=0;
    }

}
