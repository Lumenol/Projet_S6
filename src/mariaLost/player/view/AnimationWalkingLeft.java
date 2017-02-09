package mariaLost.player.view;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.image.Image;

public class AnimationWalkingLeft implements Animation{

	List<Image> frames;
	Iterator<Image> it;
	
	public AnimationWalkingLeft(){
		frames=new LinkedList<Image>();
		Image walkingLeft1=new Image("file:resources/Images/WalkingAnimationPlayer/Go_Left/1.png");
		frames.add(walkingLeft1);
		Image walkingLeft2=new Image("file:resources/Images/WalkingAnimationPlayer/Go_Left/2.png");
		frames.add(walkingLeft2);
		it=frames.iterator();
	}
	
	@Override
	public Image getImage() {
		if(!it.hasNext()){
			start();
		}
		return it.next();
	}

	@Override
	public void start() {
		it=frames.iterator();
	}
}
	
