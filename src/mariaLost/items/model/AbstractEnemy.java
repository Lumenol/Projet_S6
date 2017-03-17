package mariaLost.items.model;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import mariaLost.gamePlay.tools.DebitOnlyMonnayeur;
import mariaLost.gamePlay.tools.Direction;
import mariaLost.gamePlay.tools.IncrementableCounter;
import mariaLost.gamePlay.tools.Timer;
import mariaLost.items.model.animation.Animation;
import mariaLost.parameters.Parameters_MariaLost;

/**
 * Abstract class for an enemy.
 *
 * @author Loïc
 */
public abstract class AbstractEnemy extends AbstractMobileItem {

    protected int agroRadius;
    protected int attackRange;
    protected int damageContact;
    protected Movement goUp;
    protected Movement goDown;
    protected Movement goLeft;
    protected Movement goRight;
    protected MeleeAttack meleeUp;
    protected MeleeAttack meleeDown;
    protected MeleeAttack meleeLeft;
    protected MeleeAttack meleeRight;
    protected Movement actualMovement;
    protected MeleeAttack actualAttack;
    protected Animation death;
    private int damageDealt = 0;
    private boolean isAgro = false;
    private Timer blinkTimer = new Timer(Parameters_MariaLost.BLINKING_TIME);
    private IncrementableCounter blinkCounter = new IncrementableCounter(Parameters_MariaLost.NUMBER_OF_BLINK);


    public AbstractEnemy(double x, double y, double width, double height, double speedLimit, double money, double lifePoint) {
        super(x, y, width, height, new DebitOnlyMonnayeur(money), speedLimit, lifePoint);
    }

    /**
     * Getter for agroRadius
     *
     * @return The agro radius of the enemy.
     */
    public int getAgroRadius() {
        return agroRadius;
    }

    /**
     * Getter for damageContact
     *
     * @return the amount of damage this enemy deal when it is touched.
     */
    public int getDamageContact() {
        return damageContact;
    }

    /**
     * Getter for isAgro
     *
     * @return true if the player has agro this enemy.
     */
    public boolean isAgro() {
        return isAgro;
    }

    /**
     * Set the isAgro field to True.
     */
    public void agro() {
        isAgro = true;
    }

    /**
     * Getter for the current image of this enemy.
     * If blinkTimer has been started, it return the true image if blinkCounter is over, if not a transparent image.
     *
     * @return an Image.
     */
    @Override
    public Image getImage() {
        if (getLifePoint() <= 0)
            return death.getImage();
        if (!blinkTimer.isOver()) {
            if (blinkCounter.isMaxvalue()) {
                blinkCounter.reset();
            } else {
                blinkCounter.increment();
                return Parameters_MariaLost.TRANSPARENT_IMAGE;
            }
        }
        return actualAttack.isRunning() ? actualAttack.getImage() : actualMovement.getImage();
    }

    /**
     * An enemy has finished if his death animation is over and is life points are <= 0
     *
     * @return true is this enemy has finished is life, false if not
     */
    @Override
    public boolean isFinished() {
        return death.isOver() && getLifePoint() <= 0;
    }

    /**
     * To check if this enemy has dealt damage.
     *
     * @return True if damage dealt is != 0, False if not.
     */
    public boolean hasDealtDamage() {
        return damageDealt != 0;
    }

    /**
     * Getter for damageDealt.
     *
     * @return the amount of damage dealt by this enemy.
     * @post damageDealt is set to 0.
     */
    public int getDamageDealt() {
        int damage = damageDealt;
        damageDealt = 0;
        return damage;
    }

    /**
     * Inflict damage to this enemy.
     * Start a blink timer to show visually that the enemy has been touched.
     * Start the death animation if life points reach 0.
     *
     * @param damage
     * @pre damage can't be inferior to zero.
     */
    public void takeDamage(int damage) {
        if (damage < 0) {
            throw new IllegalArgumentException("Les dégats infligés ne peuvent être négatif");
        }
        blinkTimer.start();
        if (getLifePoint() - damage > 0) {
            setLifePoint(getLifePoint() - damage);
        } else {
            setLifePoint(0);
            death.play();
            this.setSpeed(Direction.ANY.getDirection());
        }
    }

    /**
     * Choose a movement or an attack if this enemy is doing nothing.
     * Set damageDealt if the actual attack has hit the player.
     *
     * @param player The item representing the player.
     */
    public void behave(Player player) {
        if (getLifePoint() != 0) {
            if (!actualAttack.isRunning()) {
                if (!actualMovement.isRunning()) {
                    if (isInAttackRange(player.getBounds()) && this.aligned(player)) {
                        attackChoosing(player);
                    } else {
                        movementChoosing(player);
                    }
                } else {
                    alignToPlayer(player);
                }
                checkDamageContact(player);
            } else {
                if (actualAttack.hasHit(player.getBounds())) {
                    this.damageDealt = actualAttack.getDamage();
                }
            }
        }
    }

    /**
     * Choose an attack according to the position of the player.
     *
     * @param player The item representing the player.
     */
    public void attackChoosing(Player player) {
        if (isRight(player)) {
            actualAttack = meleeRight;
            actualAttack.start(getAttackStartingPoint(Direction.RIGHT));
        } else if (isLeft(player)) {
            actualAttack = meleeLeft;
            actualAttack.start(getAttackStartingPoint(Direction.LEFT));
        } else if (isUp(player)) {
            actualAttack = meleeUp;
            actualAttack.start(getAttackStartingPoint(Direction.UP));
        } else {
            actualAttack = meleeDown;
            actualAttack.start(getAttackStartingPoint(Direction.DOWN));
        }
        this.setSpeed(Direction.ANY.getDirection());
    }

    /**
     * Return the point in then plan where to launch an attack.
     * It depends of the direction of the attack, and this enemy's dimension.
     *
     * @param direction The direction of the future attack.
     * @return a Point2D
     */
    protected Point2D getAttackStartingPoint(Direction direction) {
        if (direction.equals(Direction.RIGHT)) {
            return new Point2D(this.getPosition().getX() + this.getBounds().getWidth()
                    , this.getPosition().getY() + (this.getBounds().getHeight() / 2));
        } else if (direction.equals(Direction.LEFT)) {
            return new Point2D(this.getPosition().getX()
                    , this.getPosition().getY() + (this.getBounds().getHeight() / 2));
        } else if (direction.equals(Direction.DOWN)) {
            return new Point2D(this.getPosition().getX() + (this.getBounds().getWidth() / 2)
                    , this.getPosition().getY() + this.getBounds().getHeight());
        } else {
            return new Point2D(this.getPosition().getX() + (this.getBounds().getWidth() / 2)
                    , this.getPosition().getY());
        }
    }

    /**
     * Check is this enemy is close enough to attack the player.
     *
     * @param playerBound The enemy "hit box"
     * @return True if the player is in attack range, False if not.
     */
    protected boolean isInAttackRange(Rectangle2D playerBound) {
        Rectangle2D leftSide = new Rectangle2D(
                this.getBounds().getMinX() - attackRange
                , this.getBounds().getMinY()
                , this.getBounds().getWidth()
                , this.getBounds().getHeight());
        Rectangle2D upperSide = new Rectangle2D(
                this.getBounds().getMinX()
                , this.getBounds().getMinY() - attackRange
                , this.getBounds().getWidth()
                , this.getBounds().getHeight());
        Rectangle2D rightSide = new Rectangle2D(
                this.getBounds().getMinX() + attackRange
                , this.getBounds().getMinY()
                , this.getBounds().getWidth()
                , this.getBounds().getHeight());
        Rectangle2D rightLower = new Rectangle2D(
                this.getBounds().getMinX()
                , this.getBounds().getMinY() + attackRange
                , this.getBounds().getWidth()
                , this.getBounds().getHeight());

        return playerBound.intersects(leftSide)
                || playerBound.intersects(upperSide)
                || playerBound.intersects(rightSide)
                || playerBound.intersects(rightLower);
    }

    /**
     * Choose a movement according to the position of the player.
     * If the position of this enemy hasen't changed since last method call, choose a different movement.
     *
     * @param player The item representing the player.
     */
    public void movementChoosing(Player player) {
        if (!positionHasChanged()) {
            if (isRight(player) || isLeft(player)) {
                if (player.center().getY() <= this.center().getY()) {
                    actualMovement = goUp;
                } else {
                    actualMovement = goDown;
                }
            } else {
                if (player.center().getX() <= this.center().getX()) {
                    actualMovement = goLeft;
                } else {
                    actualMovement = goRight;
                }
            }
        } else {
            if (isRight(player)) {
                actualMovement = goRight;
            } else if (isLeft(player)) {
                actualMovement = goLeft;
            } else if (isUp(player)) {
                actualMovement = goUp;
            } else {
                actualMovement = goDown;
            }
        }
        setPreviousPositionToActual();
        actualMovement.start();
        this.setSpeed(actualMovement.getSpeed());
    }

    /**
     * Set this enemy's speed to get aligned to the player
     *
     * @param player The item representing the player.
     */
    public void alignToPlayer(Player player) {
        if (isInPlayerAxis(player) && isInAttackRange(player.getBounds())) {
            if (aligned(player)) {
                actualMovement.stop();
            } else {
                this.setSpeed(speedToAlign(player));
            }
        }
    }

    /**
     * Check if the player is in the same axis of this enemy
     *
     * @param player The item representing the player.
     * @return a boolean
     */
    protected boolean isInPlayerAxis(Player player) {
        Rectangle2D playerAxisRect;
        if (isLeft(player)) {
            playerAxisRect = new Rectangle2D(
                    player.getBounds().getMinX()
                    , player.getBounds().getMinY()
                    , this.getBounds().getMaxX() - player.getBounds().getMinX()
                    , player.getBounds().getHeight());
        } else if (isRight(player)) {
            playerAxisRect = new Rectangle2D(
                    player.getBounds().getMinX()
                    , player.getBounds().getMinY()
                    , player.getBounds().getMinX() - this.getBounds().getMinX()
                    , player.getBounds().getHeight());
        } else if (isDown(player)) {
            playerAxisRect = new Rectangle2D(
                    player.getBounds().getMinX()
                    , this.getBounds().getMinY()
                    , player.getBounds().getWidth()
                    , player.getBounds().getMaxY() - this.getBounds().getMinY());
        } else {
            playerAxisRect = new Rectangle2D(
                    player.getBounds().getMinX()
                    , player.getBounds().getMinY()
                    , player.getBounds().getWidth()
                    , this.getBounds().getMaxY() - player.getBounds().getMinY());
        }

        return playerAxisRect.intersects(player.getBounds());
    }

    /**
     * Set damageDealt to the damageContact field of this enemy if the player is in physical contact with this enemy.
     *
     * @param player The item representing the player.
     */
    protected void checkDamageContact(Player player) {
        Rectangle2D rect = new Rectangle2D(player.getPosition().getX() - 1
                , player.getPosition().getY() - 1
                , player.getBounds().getWidth() + 2
                , player.getBounds().getHeight() + 2);
        if (rect.intersects(this.getBounds()))
            damageDealt = damageContact;
    }

}

	

