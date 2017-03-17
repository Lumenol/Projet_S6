package mariaLost.items.model;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import mariaLost.gamePlay.tools.Direction;
import mariaLost.items.model.animation.AnimationFireball;
import mariaLost.parameters.Parameters_MariaLost;

public class FireballAttack {

	private static Dimension2D horizontal=new Dimension2D(45, 20);
	private static Dimension2D vertical=new Dimension2D(20, 45);

	
    public static RangedAttack getFireball(Point2D startingPoint, Direction speed) {

        Direction direction = speed.adjust();
        Dimension2D dimension;
        
        if (direction.equals(Direction.RIGHT)) {
            dimension = horizontal;
            startingPoint = new Point2D(startingPoint.getX()
                    , startingPoint.getY() - dimension.getHeight() / 2);
            
        } else if (direction.equals(Direction.LEFT)) {
            dimension = horizontal;
            startingPoint = new Point2D(startingPoint.getX() - dimension.getWidth()
                    , startingPoint.getY() - dimension.getHeight() / 2);
            
        } else if (direction.equals(Direction.UP)) {
            dimension = vertical;
            startingPoint = new Point2D(startingPoint.getX() - dimension.getWidth() / 2
                    , startingPoint.getY() - dimension.getHeight());
            
        } else if (direction.equals(Direction.DOWN)) {
            dimension = vertical;
            startingPoint = new Point2D(startingPoint.getX() - dimension.getWidth() / 2
                    , startingPoint.getY());
            
        } else if (direction.equals(Direction.UP_RIGHT)) {
            dimension = horizontal;
            startingPoint = new Point2D(startingPoint.getX()
                    , startingPoint.getY() - dimension.getHeight());
            
        } else if (direction.equals(Direction.UP_LEFT)) {
            dimension = horizontal;
            startingPoint = new Point2D(startingPoint.getX() - dimension.getWidth()
                    , startingPoint.getY() - dimension.getHeight());

        } else if (direction.equals(Direction.DOWN_RIGHT)) {
            dimension = horizontal;
            
        } else {
            dimension = horizontal;
            startingPoint = new Point2D(startingPoint.getX() - dimension.getWidth()
                    , startingPoint.getY());
        }

        return new RangedAttack(startingPoint, dimension, speed, Parameters_MariaLost.FIREBALL_DAMAGE, 
        		AnimationFireball.getFireball(direction),Parameters_MariaLost.FIREBALL_DURATION, Parameters_MariaLost.FIREBALL_SPEED);
    }

}
