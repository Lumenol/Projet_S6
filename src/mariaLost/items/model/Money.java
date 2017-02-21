package mariaLost.items.model;

import javafx.scene.image.Image;
import mariaLost.gamePlay.tools.DebitOnlyMonnayeur;
import mariaLost.items.interfaces.ActionableItem;
import mariaLost.items.interfaces.Item;
import mariaLost.parameters.Parameters_MariaLost;

/**
 * Created by elsacollet on 09/02/2017.
 */
public class Money extends AbstractItem implements ActionableItem {

    private static final Image image = new Image(Parameters_MariaLost.IMAGE_GOLD);

    public Money(double x, double y) {
        super(x, y, Parameters_MariaLost.MOVABLE_ITEM_WIDTH, Parameters_MariaLost.MOVABLE_ITEM_HEIGHT, new DebitOnlyMonnayeur(10));
    }

    @Override
    public boolean isPassable() {
        return true;
    }

    @Override
    public void action(Item o) {
        getMonnayeur().Credit(o.getMonnayeur());
    }

    @Override
    public boolean isFinished() {
        return getMonnayeur().getValue() <= 0;
    }

    @Override
    public Image getImage() {
        return image;
    }

}
