package mariaLost.items.model;

import javafx.scene.image.Image;
import mariaLost.gamePlay.tools.DebitOnlyMonnayeur;
import mariaLost.parameters.Parameters_MariaLost;

/**
 * Created by crede on 06/02/2017.
 */
public class Wall extends AbstractItem {

    private static final Image image = new Image(Parameters_MariaLost.IMAGE_WALL);

    public Wall(double x, double y) {
        super(x, y, Parameters_MariaLost.CASE_WIDTH, Parameters_MariaLost.CASE_HEIGHT, new DebitOnlyMonnayeur(0));
    }

    @Override
    public boolean isPassable() {
        return false;
    }

    @Override
    public Image getImage() {
        return image;
    }
}
