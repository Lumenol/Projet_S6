package mariaLost.items.model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.util.Duration;
import mariaLost.gamePlay.tools.Direction;
import mariaLost.gamePlay.tools.Timer;
import mariaLost.items.model.animation.Animation;

/**
 * This class is for movements.
 * It has a timer and need to be started.
 *
 * @author loic
 */
public class Movement {

    protected Direction direction;
    private Animation animation;
    private Timer movementTimer;


    public Movement(Duration duration, Direction direction, Animation animation) {
        this.direction = direction;
        this.animation = animation;
        this.movementTimer = new Timer(duration);
    }

    public void start() {
        animation.play();
        movementTimer.start();
    }

    public void stop() {
        animation.stop();
        movementTimer.end();

    }

    public boolean isRunning() {
        if (!movementTimer.isOver())
            return true;
        stop();
        return false;
    }

    final public Image getImage() {
        return animation.getImage();
    }

    public Point2D getSpeed() {
        if (!isRunning())
            return Direction.ANY.getDirection();
        return direction.getDirection();
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((direction == null) ? 0 : direction.hashCode());
		result = prime * result + ((movementTimer == null) ? 0 : movementTimer.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movement other = (Movement) obj;
		if (direction == null) {
			if (other.direction != null)
				return false;
		} else if (!direction.equals(other.direction))
			return false;
		if (movementTimer == null) {
			if (other.movementTimer != null)
				return false;
		} else if (!movementTimer.equals(other.movementTimer))
			return false;
		return true;
	}
}
