package mariaLost.gamePlay.model.animation;

import javafx.scene.image.Image;
import javafx.util.Duration;

public class AnimationWalkingFront extends Animation {

    public AnimationWalkingFront() {
        super(Duration.millis(100), new Image("file:resources/Images/WalkingAnimationPlayer/Front/1.png"), new Image("file:resources/Images/WalkingAnimationPlayer/Front/2.png"));
    }

}
