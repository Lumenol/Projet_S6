package mariaLost.gamePlay.tools;

/**
 * This class is a counter. It needs a max value when created. 
 * It can be incremented by one but can exceed its max value.
 * It can be reset to 0.
 * It offers a method to know if the counter is at its max value.
 * @author loic
 *
 */
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
