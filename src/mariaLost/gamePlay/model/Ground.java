package mariaLost.gamePlay.model;

import javafx.scene.image.Image;
import mariaLost.items.model.AbstractItem;
import mariaLost.parameters.Parameters;

/**
 * Created by crede on 06/02/2017.
 */
public class Ground extends AbstractItem {

    private static final Image image = new Image("file:resources/Images/dirt.png");

    public Ground(double x, double y) {
        super(x, y, Parameters.CASE_WIDTH, Parameters.CASE_HEIGHT);
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
