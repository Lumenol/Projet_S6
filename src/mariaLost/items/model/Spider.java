
package mariaLost.items.model;

import java.util.List;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.util.Duration;
import mariaLost.gamePlay.tools.Direction;
import mariaLost.items.interfaces.Item;
import mariaLost.items.model.animation.Animation;
import mariaLost.parameters.Parameters_MariaLost;

public class Spider extends AbstractEnemy{

	Movement goUp;
	Movement goDown;
	Movement goLeft;
	Movement goRight;
	Attack biteUp;
	Attack biteDown;
	Attack biteLeft;
	Attack biteRight;

	public Spider(double x, double y) {
		super(x, y,1);
		lifePoint=100;
		agroRadius=200;
		damageContact=10;
		attackRange=5;
		
		//goUp
		List<Image> spriteGoUp=SpriteSheetLoader.load(Parameters_MariaLost.SPIDER,5,10,1,1);
		spriteGoUp.addAll(SpriteSheetLoader.load(Parameters_MariaLost.SPIDER,5,10,5,10));
		Animation animationGoUp=new Animation(Duration.millis(100),spriteGoUp);
		goUp=new Movement(Duration.millis(600),Direction.UP,animationGoUp);

		//goLeft
		List<Image> spriteGoLeft=SpriteSheetLoader.load(Parameters_MariaLost.SPIDER,5,10,11,11);
		spriteGoLeft.addAll(SpriteSheetLoader.load(Parameters_MariaLost.SPIDER,5,10,15,20));
		Animation animationGoLeft=new Animation(Duration.millis(100),spriteGoLeft);
		goLeft=new Movement(Duration.millis(600),Direction.LEFT,animationGoLeft);
		
		//goDown
		List<Image> spriteGoDown=SpriteSheetLoader.load(Parameters_MariaLost.SPIDER,5,10,21,21);
		spriteGoDown.addAll(SpriteSheetLoader.load(Parameters_MariaLost.SPIDER,5,10,25,30));
		Animation animationGoDown=new Animation(Duration.millis(100),spriteGoDown);
		goDown=new Movement(Duration.millis(600),Direction.DOWN,animationGoDown);
		
		//goRight
		List<Image> spriteGoRight=SpriteSheetLoader.load(Parameters_MariaLost.SPIDER,5,10,31,31);
		spriteGoRight.addAll(SpriteSheetLoader.load(Parameters_MariaLost.SPIDER,5,10,35,40));
		Animation animationGoRight=new Animation(Duration.millis(100),spriteGoRight);
		goRight=new Movement(Duration.millis(600),Direction.RIGHT,animationGoRight);
		
		
		AreaOfEffect aoe=new AreaOfEffect(new Rectangle2D(0,0,10,10),Direction.UP,new Duration(400),0);

		//biteUp
		List<Image> spriteBiteUp=SpriteSheetLoader.load(Parameters_MariaLost.SPIDER,5,10,1,4);
		Animation animationBiteUp=new Animation(Duration.millis(100),spriteBiteUp);
		biteUp=new Attack(aoe,15,animationBiteUp);

		
		//biteLeft
		List<Image> spriteBiteLeft=SpriteSheetLoader.load(Parameters_MariaLost.SPIDER,5,10,11,14);
		Animation animationBiteLeft=new Animation(Duration.millis(100),spriteBiteLeft);
		biteLeft=new Attack(aoe,15,animationBiteLeft);


		//biteDown
		List<Image> spriteBiteDown=SpriteSheetLoader.load(Parameters_MariaLost.SPIDER,5,10,21,24);
		Animation animationBiteDown=new Animation(Duration.millis(100),spriteBiteDown);
		biteDown=new Attack(aoe,15,animationBiteDown);

		//biteRight
		List<Image> spriteBiteRight=SpriteSheetLoader.load(Parameters_MariaLost.SPIDER,5,10,31,34);
		Animation animationBiteRight=new Animation(Duration.millis(100),spriteBiteRight);
		biteRight=new Attack(aoe,15,animationBiteRight);

		actualMovement=goUp;
		actualAttack=biteUp;
	}
	
	
	@Override
	public void behave(Player player){
		
		if(!actualAttack.isRunning()){
			if(!actualMovement.isRunning()){
				//choosing an attack
				if(isInAttackRange(player.getBounds())&&aligned(this,player)){
					if(itemIsRight(player)){
						actualAttack=biteRight;
						actualAttack.start(getAttackStartingPoint(Direction.RIGHT),Direction.RIGHT);
					}else if(itemIsLeft(player)){
						actualAttack=biteLeft;
						actualAttack.start(getAttackStartingPoint(Direction.LEFT),Direction.LEFT);
					}else if(itemIsUp(player)){
						actualAttack=biteUp;
						actualAttack.start(getAttackStartingPoint(Direction.UP),Direction.UP);
					}else{
						actualAttack=biteDown;
						actualAttack.start(getAttackStartingPoint(Direction.DOWN),Direction.DOWN);
					}
					
				//choosing a movement	
				}else{
					if(itemIsRight(player)){
						actualMovement=goRight;
					}else if(itemIsLeft(player)){
						actualMovement=goLeft;
					}else if(itemIsUp(player)){
						actualMovement=goUp;
					}else{
						actualMovement=goDown;
					}
					actualMovement.start();
				}
			}else{
				//we are moving
				if(isInPlayerAxis(player)&&isInAttackRange(player.getBounds())){					
					if(aligned(this,player)){
						actualMovement.stop();
					}else{
						this.setSpeed(speedToAlign(player));
						return;
					}
				}
						
			}
			checkDamageContact(player);
		}else{
			//we are attacking
			if(actualAttack.hasHit(player.getBounds())){
				this.damageDealt=actualAttack.getDamage();
			}
			
		}	
		this.setSpeed(actualMovement.getSpeed());
	}
}
