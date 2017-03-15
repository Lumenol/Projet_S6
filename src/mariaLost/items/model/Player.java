package mariaLost.items.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import mariaLost.gamePlay.tools.Direction;
import mariaLost.gamePlay.tools.Monnayeur;
import mariaLost.gamePlay.tools.Timer;
import mariaLost.items.model.animation.*;
import mariaLost.parameters.Parameters_MariaLost;

/**
 * Created by crede on 06/02/2017.
 */
public class Player extends AbstractMobileItem {

    private DoubleProperty lifePoint = new SimpleDoubleProperty(100);
    private Animation animation = new AnimationWalkingFront();
    private Animation[] animations = {new AnimationWalkingFront(), new AnimationWalkingRight(), new AnimationWalkingBack(), new AnimationWalkingLeft()};
    private Timer recoveryTimer=new Timer(Parameters_MariaLost.DAMAGE_RECOVERY_TIME);
    private boolean clignotement=true;


    public Player() {
        this(0, 0);
    }

    public Player(double x, double y) {
        super(x, y, Parameters_MariaLost.MOVABLE_ITEM_WIDTH, Parameters_MariaLost.MOVABLE_ITEM_HEIGHT, new Monnayeur(0), 10);
    }

    public ReadOnlyDoubleProperty lifePointPropertie() {
        return lifePoint;
    }

	public void takeDamage(int damage){
		if(damage<0){
			throw new IllegalArgumentException("Les dégats infligés ne peuvent être négatif");
		}
		if(recoveryTimer.isOver()){
			recoveryTimer.start();
			if(lifePoint.intValue()-damage>0){
				lifePoint.set(lifePoint.intValue()-damage);
				System.out.println("damage taken = "+damage);
				System.out.println("new lifepoint = "+lifePoint);
			}else{
				lifePoint.set(0);
			}
		}	
	}


    @Override
    public void setSpeed(Point2D speed) {
        super.setSpeed(speed);
        if (speed.equals(Point2D.ZERO)) {
            animation.stop();
        } else {
            //calcul de l'angle de deplacement
            double angle = ((speed.getY() >= 0 ? 1 : -1) * speed.angle(Direction.RIGHT.getDirection()) + 360) % 360;
            if (45 <= angle && angle <= 135) {//bas
                changeAnimation(animations[0]);
            } else if (135 < angle && angle < 225) {//gauche
                changeAnimation(animations[3]);
            } else if (225 <= angle && angle <= 315) {//haut
                changeAnimation(animations[2]);
            } else {//droite
                changeAnimation(animations[1]);
            }
        }
    }
    
    public Point2D getAttackStartingPoint(Point2D point){
    	if(isRight(point))
    			return new Point2D(this.getPosition().getX()+this.getBounds().getWidth()
    	    			,this.getPosition().getY()+this.getBounds().getHeight()/2);
    	if(isLeft(point))
			return new Point2D(this.getPosition().getX()
	    			,this.getPosition().getY()+this.getBounds().getHeight()/2);
    	if(isUp(point))
			return new Point2D(this.getPosition().getX()+(this.getBounds().getWidth()/2)
	    			,this.getPosition().getY());
    	if(isDown(point))
    		return new Point2D(this.getPosition().getX()+this.getBounds().getWidth()/2
	    			,this.getPosition().getY()+this.getBounds().getHeight());
    	if(isUpperRight(point))
    			return new Point2D(this.getPosition().getX()+this.getBounds().getWidth()
    	    			,this.getPosition().getY());
    	if(isLowerRight(point))		
    			return new Point2D(this.getPosition().getX()+this.getBounds().getWidth()
    	    			,this.getPosition().getY()+this.getBounds().getHeight());
    	if(isUpperLeft(point))
    		return new Point2D(this.getPosition().getX()
	    			,this.getPosition().getY());
    	return new Point2D(this.getPosition().getX()
	    			,this.getPosition().getY()+this.getBounds().getHeight());
	}
    

    private void changeAnimation(Animation a) {
        if (a != animation) {
            animation.stop();
            animation = a;
        }
        animation.play();
    }

    @Override
    public Image getImage() {
    	if(!recoveryTimer.isOver()){
			if(clignotement){
				clignotement=false;
				return new Image(Parameters_MariaLost.TRANSPARANT_IMAGE);
			}else{
				clignotement=true;
			}
		}
        return animation.getImage();
    }


}
