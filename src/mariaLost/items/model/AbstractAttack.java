package mariaLost.items.model;

import javafx.scene.image.Image;
import javafx.util.Duration;
import mariaLost.gamePlay.tools.Direction;
import mariaLost.items.model.animation.Animation;

public abstract class AbstractAttack {
	protected int damage; 
	protected Animation animation;
	protected Duration duration;
	protected double startTime;
	protected Direction direction;
	
	public AbstractAttack(int damage, Animation animation, Duration duration, Direction direction) {
		this.damage = damage;
		this.animation = animation;
		this.duration = duration;
		this.startTime = System.currentTimeMillis();
		this.direction = direction;
	}
	
	public Image getImage() {
		return animation.getImage();
	}

	public int getDamage() {
		return damage;
	}

	public boolean isRunning() {
		return System.currentTimeMillis()-startTime<duration.toMillis();
	}
	
}
