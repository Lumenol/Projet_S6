package mariaLost.items.model;

import javafx.scene.image.Image;
import javafx.util.Duration;
import mariaLost.gamePlay.tools.Direction;
import mariaLost.gamePlay.tools.Timer;
import mariaLost.items.model.animation.Animation;

public abstract class AbstractAttack {
    protected int damage;
    protected Animation animation;
    protected Direction direction;
    protected Timer timer;

    public AbstractAttack(int damage, Animation animation, Duration duration, Direction direction) {
        this.damage = damage;
        this.animation = animation;
        this.direction = direction;
        timer = new Timer(duration);
    }

    public Image getImage() {
        return animation.getImage();
    }

    public int getDamage() {
        return damage;
    }


}
