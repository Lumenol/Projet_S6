package mariaLost.gamePlay.model.animation;

import javafx.scene.image.Image;
import javafx.util.Duration;

public class AnimationWalkingBack extends Animation {

    public AnimationWalkingBack() {
        super(Duration.millis(100), new Image("file:resources/Images/WalkingAnimationPlayer/Back/1.png"), new Image("file:resources/Images/WalkingAnimationPlayer/Back/2.png"));
    }

}
