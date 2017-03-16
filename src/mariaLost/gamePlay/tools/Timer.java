package mariaLost.gamePlay.tools;

import javafx.util.Duration;

public class Timer {
	
	private Duration duration;
	private double startTime=0;
	private boolean running=false;
	private Duration minimum;
		
	public Timer(Duration duration){
		this.duration=duration;
		minimum=duration;
	} 
	
	public boolean isOver(){
		if(System.currentTimeMillis()-startTime<minimum.toMillis()){
			return false;
		}
		if(System.currentTimeMillis()-startTime>duration.toMillis()){
			running=false;
		}
		return !running;
	}

	
	public void start(){
		startTime=System.currentTimeMillis();
		running=true;
	}
	
	
	
	public void end(){
		startTime=0;
		running=false;
	}
	
	
	public void mustLast(Duration duration){
		minimum=duration;
	}
	
}
