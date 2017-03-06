package mariaLost.items.model;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.util.Duration;
import mariaLost.gamePlay.tools.Direction;
import mariaLost.items.model.animation.Animation;

public class Movement {
	
	protected Direction direction;
	private Duration duration;
	private long startTime;
	private Boolean isRunning=false;
	private Animation animation;
	
	
	
	public Movement(Duration duration, Direction direction,Animation animation){
		this.direction=direction;
		this.duration=duration;
		this.animation=animation;
	}
	
	public Movement(Direction direction,Animation animation){
		this.direction=direction;
		this.duration=Duration.INDEFINITE;
		this.animation=animation;
	}
	
	private void start(){
		startTime=System.currentTimeMillis();
		isRunning=true;
		animation.play();
	}
	
	private void stop(){
		isRunning=false;
		animation.stop();
	}
	
	
	protected boolean isOver(){
		return (System.currentTimeMillis()-startTime)>duration.toMillis();
	}
	public boolean isRunning(){
		return isRunning;
	}
	
	final public Image getImage(){
		return animation.getImage();
	}
	
	public Point2D getSpeed(){
		if(!isRunning){
			start();
		}
		if(isOver()){
			stop();
			return Direction.ANY.getDirection();
		}
		return direction.getDirection();
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
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		return true;
	}
	
	
	
}
