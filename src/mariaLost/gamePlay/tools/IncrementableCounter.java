package mariaLost.gamePlay.tools;

public class IncrementableCounter {
	
	private int current=0;
	private int maxValue;
	
	public IncrementableCounter(int i){
		if(i<=0)
			throw new IllegalArgumentException("Un timer ne peut etre crée avec un argument inférieur ou égal à 0");
		maxValue=i;
	}
	public void increment(){
		if(current<maxValue){
			current++;
		}
	}
	public void reset(){
		current=0;
	}
		
	public boolean isMaxvalue(){
		return current==maxValue;
	}
	
}
