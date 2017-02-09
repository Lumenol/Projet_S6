package mariaLost.player.view;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javafx.scene.image.Image;

public class AnimationNotWalking implements Animation {
	
	
	List<Image> frames;
	Iterator<Image> it;
	
	public AnimationNotWalking(){
		frames=new LinkedList<Image>();
		Image walkingRight1=new Image("file:resources/Images/WalkingAnimationPlayer/Front/1.png");
		frames.add(walkingRight1);
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
