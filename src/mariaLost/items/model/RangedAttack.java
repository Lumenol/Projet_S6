package mariaLost.items.model;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.util.Duration;
import mariaLost.gamePlay.tools.DebitOnlyMonnayeur;
import mariaLost.gamePlay.tools.Direction;
import mariaLost.gamePlay.tools.Timer;
import mariaLost.items.interfaces.Item;
import mariaLost.items.model.animation.Animation;

/**
 * This class represents ranged attacks.
 * It extends AbstractMobileItem, which means it is intended to be added to the item list in World.
 * @author loic
 *
 */
public class RangedAttack extends AbstractMobileItem {

    private static Duration DUREE_MIN = new Duration(20);

    private int damage;
    private Animation animation;
    private Timer attackTimer;


    public RangedAttack(Point2D startingPoint, Dimension2D areaOfEffect, Direction direction, int damage, Animation animation, Duration duration, double speedLimite) {
        super(startingPoint.getX(), startingPoint.getY(), areaOfEffect.getWidth(), areaOfEffect.getHeight(), new DebitOnlyMonnayeur(0), speedLimite, 0);
        setSpeed(direction.getDirection());
        this.damage = damage;
        this.animation = animation;
        attackTimer = new Timer(duration);
        attackTimer.start();
        attackTimer.mustLast(DUREE_MIN);
    }
    /**
	 * Getter for this attack current image.
	 * @return an Image
	 */
    public Image getImage() {
        return animation.getImage();
    }
    /**
	 * Getter for the attack damage.
	 * @return an int
	 */
    public int getDamage() {
        return damage;
    }
    /**
	 * Return true if the attack attackTimer is not over.
	 * @return a Boolean
	 */
    public boolean isRunning() {
        return !attackTimer.isOver();
    }

    /**
     * Return true if the life of this ranged attack if finished
     * @return a boolean
     */
    public boolean isFinished() {
        return attackTimer.isOver();
    }
    
    /**
     * The action this ranged attack do when it hit something.
     * If it hits an enemy, it deals damage and disapears.
     * If it hits something else which is non passable, it disapears
     */
    @Override
    public void action(Item o) {
        if (o instanceof AbstractEnemy) {
            ((AbstractEnemy) o).agro();
            ((AbstractEnemy) o).takeDamage(getDamage());
            attackTimer.end();
            setSpeed(Point2D.ZERO);
        }
        if (!o.isPassable()) {
            attackTimer.end();
        }
    }

}
