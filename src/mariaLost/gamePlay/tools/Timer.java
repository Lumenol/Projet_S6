package mariaLost.gamePlay.tools;

import javafx.util.Duration;

public class Timer {
	
	private Duration duration;
	private double startTime=0;
	private boolean hasBeenStarted=false;
		
	public Timer(Duration duration){
		this.duration=duration;
	}
	
	public boolean isOver(){
		return System.currentTimeMillis()-startTime>duration.toMillis();
	}
	
	public void start(){
		if(!hasBeenStarted){
			startTime=System.currentTimeMillis();
			hasBeenStarted=true;
		}
	}
	
	public Duration timeElapsed(){
		if(hasBeenStarted){
			return new Duration(System.currentTimeMillis()-startTime);
		}
		return new Duration(0);
	}
	
	
	public void end(){
		hasBeenStarted=false;
		startTime=0;
	}
}
