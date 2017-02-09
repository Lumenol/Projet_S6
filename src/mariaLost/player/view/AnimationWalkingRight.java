package mariaLost.player.view;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.image.Image;

public class AnimationWalkingRight implements Animation{

	List<Image> frames;
	Iterator<Image> it;
	
	public AnimationWalkingRight(){
		frames=new LinkedList<Image>();
		Image walkingRight1=new Image("file:resources/Images/WalkingAnimationPlayer/Go_Right/1.png");
		frames.add(walkingRight1);
		Image walkingRight2=new Image("file:resources/Images/WalkingAnimationPlayer/Go_Right/2.png");
		frames.add(walkingRight2);
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
