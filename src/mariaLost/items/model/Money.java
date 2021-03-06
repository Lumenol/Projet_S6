package mariaLost.items.model;

import javafx.scene.image.Image;
import mariaLost.gamePlay.tools.DebitOnlyMonnayeur;
import mariaLost.items.interfaces.ActionableItem;
import mariaLost.items.interfaces.Item;
import mariaLost.parameters.Parameters_MariaLost;

/**
 * Created by crede on 09/02/2017.
 */
public class Money extends AbstractItem implements ActionableItem {


    public Money(double x, double y) {
        this(x, y, 0);
    }

    public Money(double x, double y, double solde) {
        super(x, y, Parameters_MariaLost.MOVABLE_ITEM_WIDTH, Parameters_MariaLost.MOVABLE_ITEM_HEIGHT, new DebitOnlyMonnayeur(solde));
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
        return Parameters_MariaLost.IMAGE_GOLD;
    }

}
