package mariaLost.items.model;

import javafx.scene.image.Image;
import mariaLost.parameters.Parameters_MariaLost;

/**
 * Created by elsacollet on 08/02/2017.
 */
public class MoneyItem extends AbstractItem {

    private static final Image image = new Image(Parameters_MariaLost.IMAGE_GOLD);

    public MoneyItem(double x, double y) {
        super(x, y, Parameters_MariaLost.MOVABLE_ITEM_WIDTH, Parameters_MariaLost.MOVABLE_ITEM_HEIGHT);
    }

    @Override
    public Image getImage() {
        return this.image;
    }

    @Override
    public boolean isPassable() {
        return true;
    }


}
