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

public class RangedAttack extends AbstractMobileItem {

    private static Duration DUREE_MIN = new Duration(20);

    protected int damage;
    protected Animation animation;
    private Timer timer;


    public RangedAttack(Point2D startingPoint, Dimension2D areaOfEffect, Direction direction, int damage, Animation animation, Duration duration, double speedLimite) {
        super(startingPoint.getX(), startingPoint.getY(), areaOfEffect.getWidth(), areaOfEffect.getHeight(), new DebitOnlyMonnayeur(0), speedLimite);
        setSpeed(direction.getDirection());
        this.damage = damage;
        this.animation = animation;
        timer=new Timer(duration);
        timer.start();
        timer.mustLast(DUREE_MIN);
    }

    public Image getImage() {
        return animation.getImage();
    }

    public int getDamage() {
        return damage;
    }

    public boolean isRunning() {
    	return !timer.isOver();
    }

    @Override
    public boolean isPassable() {
        return true;
    }

    public boolean isFinished() {
    	return timer.isOver();
    }

    @Override
    public void action(Item o) {
        if (o instanceof AbstractEnemy) {
            System.out.println("touché");
            ((AbstractEnemy) o).agro();
            ((AbstractEnemy) o).takeDamage(getDamage());
            timer.end();
            setSpeed(Point2D.ZERO);
        }
    }

}
