package mariaLost.player.view;

import javafx.scene.image.Image;
import javafx.util.Duration;

public class AnimationWalkingRight extends Animation {

	public AnimationWalkingRight(){
        super(Duration.millis(100), new Image("file:resources/Images/WalkingAnimationPlayer/Go_Right/1.png"), new Image("file:resources/Images/WalkingAnimationPlayer/Go_Right/2.png"));
    }

}
