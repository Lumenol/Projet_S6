package mariaLost.gamePlay.tools;

import javafx.geometry.Point2D;

/**
 * Created by crede on 07/02/2017.
 */
public class Direction {

    public static final Direction ANY = new Direction(0, 0);

    public static final Direction UP = new Direction(0, -1);

    public static final Direction DOWN = new Direction(0, 1);

    public static final Direction LEFT = new Direction(-1, 0);

    public static final Direction RIGHT = new Direction(1, 0);

    public static final Direction UP_LEFT = UP.compose(LEFT);

    public static final Direction UP_RIGHT = UP.compose(RIGHT);

    public static final Direction DOWN_LEFT = DOWN.compose(LEFT);

    public static final Direction DOWN_RIGHT = DOWN.compose(RIGHT);
    private Point2D direction;

    public Direction(double x, double y) {
        direction = new Point2D(x, y).normalize();
    }

    public Direction(Point2D point) {
        direction = point.normalize();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Direction direction1 = (Direction) o;

        return getDirection().equals(direction1.getDirection());

    }

    @Override
    public int hashCode() {
        return getDirection().hashCode();
    }

    public Point2D getDirection() {
        return direction;
    }

    public Direction compose(Direction direction) {
        Point2D d = direction.getDirection().add(this.getDirection());
        return new Direction(d.getX(), d.getY());
    }

    public Direction adjust() {
        if (direction.getX() > Math.cos(Math.PI / 6))
            return Direction.RIGHT;
        if (direction.getX() < Math.cos(5 * Math.PI / 6))
            return Direction.LEFT;
        if (-direction.getY() > Math.sin(Math.PI / 3))
            return Direction.UP;
        if (-direction.getY() < Math.sin(5 * Math.PI / 3))
            return Direction.DOWN;
        if (direction.getX() > Math.cos(Math.PI / 3))
            if (direction.getY() < 0) {
                return Direction.UP_RIGHT;
            } else {
                return Direction.DOWN_RIGHT;
            }
        if (direction.getY() < 0) {
            return Direction.UP_LEFT;
        } else {
            return Direction.DOWN_LEFT;
        }
    }

}
