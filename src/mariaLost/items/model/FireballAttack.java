package mariaLost.items.model;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.util.Duration;
import mariaLost.gamePlay.tools.Direction;
import mariaLost.items.model.animation.AnimationFireball;

public class FireballAttack extends RangedAttack{

	public FireballAttack(Point2D startingPoint,Direction direction){
        super(startingPoint, new Dimension2D(40, 15), direction, 10, AnimationFireball.getFireball(direction), new Duration(3000), 5);
    }
	
}
