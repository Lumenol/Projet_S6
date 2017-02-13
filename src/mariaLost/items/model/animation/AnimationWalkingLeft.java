package mariaLost.items.model.animation;

import javafx.scene.image.Image;
import javafx.util.Duration;

public class AnimationWalkingLeft extends Animation {


    public AnimationWalkingLeft() {
        super(Duration.millis(100), new Image("file:resources/Images/WalkingAnimationPlayer/Go_Left/1.png"), new Image("file:resources/Images/WalkingAnimationPlayer/Go_Left/2.png"));
    }

}

