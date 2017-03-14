package mariaLost.items.model;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.util.Duration;
import mariaLost.gamePlay.tools.DebitOnlyMonnayeur;
import mariaLost.gamePlay.tools.Direction;
import mariaLost.items.interfaces.Item;
import mariaLost.items.model.animation.Animation;

public class RangedAttack extends AbstractMobileItem {

    private static long DUREE_MIN = 20;

    protected int damage;
    protected Animation animation;
    protected Duration duration;
    protected double startTime;
    private boolean finish;


    public RangedAttack(Point2D startingPoint, Dimension2D areaOfEffect, Direction direction, int damage, Animation animation, Duration duration, double speedLimite) {
        super(startingPoint.getX(), startingPoint.getY(), areaOfEffect.getWidth(), areaOfEffect.getHeight(), new DebitOnlyMonnayeur(0), speedLimite);
        setSpeed(direction.getDirection());
        this.damage = damage;
        this.animation = animation;
        this.duration = duration;
        startTime = System.currentTimeMillis();
        finish = false;
    }

    public Image getImage() {
        return animation.getImage();
    }

    public int getDamage() {
        return damage;
    }

    public boolean isRunning() {
        return !finish && System.currentTimeMillis() - startTime < duration.toMillis();
    }

    @Override
    public boolean isPassable() {
        return true;
    }

    public boolean isFinished() {
        if (!isRunning()) {
            System.out.println("la boule de feu est fini");
        }
        return !isRunning() && System.currentTimeMillis() - startTime > DUREE_MIN;
    }

    @Override
    public void action(Item o) {

        if (o instanceof AbstractEnemy) {
            System.out.println("touché");
            ((AbstractEnemy) o).agro();
            ((AbstractEnemy) o).takeDamage(getDamage());
            //setting startime to 0 makes isFinished methode return true
            finish = true;
            setSpeed(Point2D.ZERO);
        }
    }

}
