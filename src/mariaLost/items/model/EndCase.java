package mariaLost.items.model;

import javafx.scene.image.Image;
import mariaLost.gamePlay.tools.DebitOnlyMonnayeur;
import mariaLost.parameters.Parameters_MariaLost;

/**
 * Created by crede on 07/02/2017.
 */
public class EndCase extends AbstractItem {

    private static final Image image = new Image(Parameters_MariaLost.IMAGE_END_CASE);

    public EndCase(double x, double y) {
        super(x, y, Parameters_MariaLost.CASE_WIDTH, Parameters_MariaLost.CASE_HEIGHT, new DebitOnlyMonnayeur(0));
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
