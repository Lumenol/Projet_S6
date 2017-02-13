package mariaLost.items.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import mariaLost.gamePlay.tools.Direction;
import mariaLost.items.model.animation.*;
import mariaLost.parameters.Parameters_MariaLost;

/**
 * Created by crede on 06/02/2017.
 */
public class Player extends AbstractMobileItem {

    private IntegerProperty money = new SimpleIntegerProperty(0);
    private Animation animation = new AnimationWalkingFront();
    private Animation[] animations = {new AnimationWalkingFront(), new AnimationWalkingRight(), new AnimationWalkingBack(), new AnimationWalkingLeft()};

    public Player() {
        this(0, 0);
    }

    public Player(double x, double y) {
        super(x, y, Parameters_MariaLost.MOVABLE_ITEM_WIDTH, Parameters_MariaLost.MOVABLE_ITEM_HEIGHT, 10);
    }

    public void addMoney(int price) {
        System.out.println("add money " + money);

        setMoney(getMoney() + price);
    }

    public int getMoney() {
        return money.get();
    }

    public void setMoney(int money) {
        this.money.set(money);
    }

    public ReadOnlyIntegerProperty moneyProperty() {
        return money;
    }

    @Override
    public void setSpeed(Point2D speed) {
        super.setSpeed(speed);
        if (speed.equals(Point2D.ZERO)) {
            animation.stop();
        } else {
            //calcul de l'angle de deplacement
            double angle = ((speed.getY() >= 0 ? 1 : -1) * speed.angle(Direction.RIGHT.getDirection()) + 360) % 360;
            if (45 <= angle && angle <= 135) {//bas
                changeAnimation(animations[0]);
            } else if (135 < angle && angle < 225) {//gauche
                changeAnimation(animations[3]);
            } else if (225 <= angle && angle <= 315) {//haut
                changeAnimation(animations[2]);
            } else {//droite
                changeAnimation(animations[1]);
            }
        }


    }

    @Override //change la vitesse pour que le joueur se dirige vers sa destination
    public void setDestination(Point2D destination) {
        super.setDestination(destination);
    }


    private void changeAnimation(Animation a) {
        if (a != animation) {
            animation.stop();
            animation = a;
        }
        animation.play();
    }

    @Override
    public Image getImage() {
        return animation.getImage();
    }
}
