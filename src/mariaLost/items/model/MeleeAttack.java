package mariaLost.items.model;


import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.util.Duration;
import mariaLost.gamePlay.tools.Direction;
import mariaLost.gamePlay.tools.Timer;
import mariaLost.items.model.animation.Animation;

/**
 * This class represents a melee attack.
 *
 * @author Loïc
 */
public class MeleeAttack {

	private int damage;
	private Animation animation;
	private Direction direction;
	private Timer attackTimer;
	private Point2D attackStartingPoint;
	private Dimension2D aoe;


	public MeleeAttack(Dimension2D areaOfEffect, Direction direction, int damage, Animation animation, Duration duration) {
		this.damage = damage;
		this.animation = animation;
		this.direction = direction;
		attackTimer = new Timer(duration);
		this.aoe = areaOfEffect;
	}

	/**
	 * Start the animation and timer of this melee attack.
	 *
	 * @param attackStartingPoint
	 * @param direction
	 */
	public void start(Point2D attackStartingPoint) {
		this.attackStartingPoint = attackStartingPoint;
		animation.play();
		attackTimer.start();
	}

	/**
	 * Stop the attack.
	 */
	private void stop() {
		animation.stop();
		attackTimer.end();
	}

	/**
	 * Return true if the attack timer is not over.
	 *
	 * @return a Boolean
	 */
	public boolean isRunning() {
		return !attackTimer.isOver();
	}

	/**
	 * Getter for this attack current image.
	 *
	 * @return an Image
	 */
	public Image getImage() {
		return animation.getImage();
	}

	/**
	 * Getter for the attack damage.
	 *
	 * @return an int
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * Determine if the attack is still running and if it has hit something.
	 * If the timer is over, call the stop method to stop the attack.
	 *
	 * @param itemBound The bound of item.
	 * @return
	 */
	public boolean hasHit(Rectangle2D itemBound) {
		if (isRunning()) {
			return hasHit(attackStartingPoint, itemBound);
		}
		stop();
		return false;
	}

	/**
	 * Determine if the attack has hit an item.
	 *
	 * @param attackStartingPoint The starting point of the attack in the plan
	 * @param itemBound           The bounds of the item we want to know if it is hit.
	 * @return a boolean
	 */
	private boolean hasHit(Point2D attackStartingPoint, Rectangle2D itemBound) {
		Rectangle2D areaOfHit;
		if (direction.equals(Direction.RIGHT)) {
			areaOfHit = new Rectangle2D(attackStartingPoint.getX()
					, attackStartingPoint.getY() - (aoe.getHeight() / 2)
					, aoe.getWidth(),
					aoe.getHeight());
		} else if (direction.equals(Direction.LEFT)) {
			areaOfHit = new Rectangle2D(attackStartingPoint.getX() - aoe.getWidth()
					, attackStartingPoint.getY() - (aoe.getHeight() / 2)
					, aoe.getWidth(),
					aoe.getHeight());
		} else if (direction.equals(Direction.DOWN)) {
			areaOfHit = new Rectangle2D(attackStartingPoint.getX() - (aoe.getWidth() / 2)
					, attackStartingPoint.getY() + aoe.getHeight()
					, aoe.getWidth(),
					aoe.getHeight());
		} else {
			areaOfHit = new Rectangle2D(attackStartingPoint.getX() - (aoe.getWidth() / 2)
					, attackStartingPoint.getY() - aoe.getHeight()
					, aoe.getWidth(),
					aoe.getHeight());
		}
		return areaOfHit.intersects(itemBound);
	}

}