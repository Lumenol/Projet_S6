package mariaLost.gamePlay.model;

import javafx.scene.image.Image;
import mariaLost.items.model.AbstractMobileItem;
import mariaLost.parameters.Parameters;
import mariaLost.player.view.Animation;
import mariaLost.player.view.AnimationNotWalking;
import mariaLost.player.view.AnimationWalkingLeft;
import mariaLost.player.view.AnimationWalkingRight;

/**
 * Created by crede on 06/02/2017.
 */
public class Player extends AbstractMobileItem {

    private Animation animation=new AnimationNotWalking();
    
    public Player() {
        this(0, 0);
    }

    public Player(double x, double y) {
        super(x, y, Parameters.MOVABLE_ITEM_WIDTH, Parameters.MOVABLE_ITEM_HEIGHT, 10);
    }

    @Override
    public Image getImage() {
    	double vx= this.getSpeed().getX();
    	double vy= this.getSpeed().getX();
		System.out.println("vx="+vx+"    vy="+vy);
    	
    	//player going right
    	if(vx==10&&vy==10){
    		if(!(animation instanceof AnimationWalkingRight)){
    			animation = new AnimationWalkingRight();
    		}
    	}
    	
    	//player going left
    	if(vx==-10&&vy==-10){
    		if(!(animation instanceof AnimationWalkingLeft)){
    			animation =new AnimationWalkingLeft();
    		}
    	}
    	
    	//player not mooving
    	if(vx==0&&vy==0){
    		if(!(animation instanceof AnimationNotWalking)){
    			animation =new AnimationNotWalking();
    		}
    	}
 
		return animation.getImage();
    }
}
