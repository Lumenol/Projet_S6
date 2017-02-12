package mariaLost.gamePlay.model;

import javafx.scene.image.Image;
import mariaLost.items.model.AbstractItem;
import mariaLost.parameters.Parameters_MariaLost;

/**
 * Created by crede on 07/02/2017.
 */
public class EndCase extends AbstractItem {

    private static final Image image = new Image("file:resources/Images/floor_green.png");

    public EndCase(double x, double y) {
        super(x, y, Parameters_MariaLost.CASE_WIDTH, Parameters_MariaLost.CASE_HEIGHT);
    }

    @Override
    public boolean isPassable() {
        return true;
    }

    @Override
    public Image getImage() {
        return image;
    }


}
