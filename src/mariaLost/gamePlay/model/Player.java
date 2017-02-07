package mariaLost.gamePlay.model;

import javafx.scene.image.Image;
import mariaLost.items.model.AbstractMobileItem;
import mariaLost.parameters.Parameters;

/**
 * Created by crede on 06/02/2017.
 */
public class Player extends AbstractMobileItem {

    private static final Image image = new Image("file:resources/Images/player.png");

    public Player() {
        this(0, 0);
    }

    public Player(double x, double y) {
        super(x, y, Parameters.MOVABLE_ITEM_WIDTH, Parameters.MOVABLE_ITEM_HEIGHT, 10);
    }

    @Override
    public Image getImage() {
        return image;
    }
}
