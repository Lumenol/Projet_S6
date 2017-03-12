
package mariaLost.items.model;

import java.util.List;

import javafx.geometry.Dimension2D;
import javafx.scene.image.Image;
import javafx.util.Duration;
import mariaLost.gamePlay.tools.Direction;
import mariaLost.items.model.animation.Animation;
import mariaLost.items.model.animation.SpriteSheetLoader;
import mariaLost.parameters.Parameters_MariaLost;

public class Spider extends AbstractEnemy{

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
		
		

		//biteUp
		List<Image> spriteBiteUp=SpriteSheetLoader.load(Parameters_MariaLost.SPIDER,5,10,1,4);
		Animation animationBiteUp=new Animation(Duration.millis(100),spriteBiteUp);
		meleeUp=new MeleeAttack(new Dimension2D(10,10),Direction.UP,15,animationBiteUp,new Duration(400));

		
		//biteLeft
		List<Image> spriteBiteLeft=SpriteSheetLoader.load(Parameters_MariaLost.SPIDER,5,10,11,14);
		Animation animationBiteLeft=new Animation(Duration.millis(100),spriteBiteLeft);
		meleeLeft=new MeleeAttack(new Dimension2D(10,10),Direction.LEFT,15,animationBiteLeft,new Duration(400));


		//biteDown
		List<Image> spriteBiteDown=SpriteSheetLoader.load(Parameters_MariaLost.SPIDER,5,10,21,24);
		Animation animationBiteDown=new Animation(Duration.millis(100),spriteBiteDown);
		meleeDown=new MeleeAttack(new Dimension2D(10,10),Direction.DOWN,15,animationBiteDown,new Duration(400));

		//biteRight
		List<Image> spriteBiteRight=SpriteSheetLoader.load(Parameters_MariaLost.SPIDER,5,10,31,34);
		Animation animationBiteRight=new Animation(Duration.millis(100),spriteBiteRight);
		meleeRight=new MeleeAttack(new Dimension2D(10,10),Direction.RIGHT,15,animationBiteRight,new Duration(400));

		actualMovement=goDown;
		actualAttack=meleeDown;
	}	
}
