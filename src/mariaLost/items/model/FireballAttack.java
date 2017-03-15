package mariaLost.items.model;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.util.Duration;
import mariaLost.gamePlay.tools.Direction;
import mariaLost.items.model.animation.AnimationFireball;

public class FireballAttack {

    public static RangedAttack getFireball(Point2D startingPoint, Direction speed) {

        Direction direction = speed.adjust();

        Dimension2D dimension;
        if (direction.equals(Direction.RIGHT)) {
            dimension = new Dimension2D(40, 15);
            startingPoint = new Point2D(startingPoint.getX()
                    , startingPoint.getY() - dimension.getHeight() / 2);
        } else if (direction.equals(Direction.LEFT)) {
            dimension = new Dimension2D(40, 15);
            startingPoint = new Point2D(startingPoint.getX() - dimension.getWidth()
                    , startingPoint.getY() - dimension.getHeight() / 2);
        } else if (direction.equals(Direction.UP)) {
            dimension = new Dimension2D(15, 40);
            startingPoint = new Point2D(startingPoint.getX() - dimension.getWidth() / 2
                    , startingPoint.getY() - dimension.getHeight());
        } else if (direction.equals(Direction.DOWN)) {
            dimension = new Dimension2D(15, 40);
            startingPoint = new Point2D(startingPoint.getX() - dimension.getWidth() / 2
                    , startingPoint.getY());


        } else if (direction.equals(Direction.UP_RIGHT)) {
            dimension = new Dimension2D(40, 20);
            startingPoint = new Point2D(startingPoint.getX()
                    , startingPoint.getY() - dimension.getHeight());

        } else if (direction.equals(Direction.UP_LEFT)) {
            dimension = new Dimension2D(40, 20);
            startingPoint = new Point2D(startingPoint.getX() - dimension.getWidth()
                    , startingPoint.getY() - dimension.getHeight());

        } else if (direction.equals(Direction.DOWN_RIGHT)) {
            dimension = new Dimension2D(40, 20);

        } else {
            dimension = new Dimension2D(40, 20);
            startingPoint = new Point2D(startingPoint.getX() - dimension.getWidth()
                    , startingPoint.getY());
        }

        return new RangedAttack(startingPoint, dimension, speed, 25, AnimationFireball.getFireball(direction), new Duration(3000), 4);


    }

}
