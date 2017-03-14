package mariaLost.items.model.animation;

import javafx.util.Duration;
import mariaLost.gamePlay.tools.Direction;
import mariaLost.parameters.Parameters_MariaLost;

public class AnimationFireball{
	
	 public static Animation getFireball(Direction direction) {	 
		 if(direction.equals(Direction.LEFT)){
			 return new Animation(Duration.millis(800),SpriteSheetLoader.load(Parameters_MariaLost.FIREBALL,8,8,1,8));
		 }else if(direction.equals(Direction.UP_LEFT)){				 
			 return new Animation(Duration.millis(800),SpriteSheetLoader.load(Parameters_MariaLost.FIREBALL,8,8,9,16));
		 }else if(direction.equals(Direction.UP)){ 
			 return new Animation(Duration.millis(800),SpriteSheetLoader.load(Parameters_MariaLost.FIREBALL,8,8,17,24));
		 }else if(direction.equals(Direction.UP_RIGHT)){				 
			 return  new Animation(Duration.millis(800),SpriteSheetLoader.load(Parameters_MariaLost.FIREBALL,8,8,25,32));
		 }else	 if(direction.equals(Direction.RIGHT)){
			 return new Animation(Duration.millis(800),SpriteSheetLoader.load(Parameters_MariaLost.FIREBALL,8,8,33,40));
		 }else if(direction.equals(Direction.DOWN_RIGHT)){
			 return  new Animation(Duration.millis(800),SpriteSheetLoader.load(Parameters_MariaLost.FIREBALL,8,8,41,48));			 
		 }else if(direction.equals(Direction.DOWN)){
			 return  new Animation(Duration.millis(800),SpriteSheetLoader.load(Parameters_MariaLost.FIREBALL,8,8,49,56));
		 }else{
			 return  new Animation(Duration.millis(800),SpriteSheetLoader.load(Parameters_MariaLost.FIREBALL,8,8,57,64));
		 }
	 }	  
}
