package mariaLost.items.model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import mariaLost.items.interfaces.ActionableItem;
import mariaLost.items.interfaces.Item;
import mariaLost.parameters.Parameters_MariaLost;

/**
 * Created by crede on 06/02/2017.
 */
public class BreakableWall extends AbstractItem implements ActionableItem {

    private AbstractItem me = null;

    public BreakableWall(double x, double y) {
        super(x, y, Parameters_MariaLost.CASE_WIDTH, Parameters_MariaLost.CASE_HEIGHT);
        me = new Wall(x, y);
    }

    @Override
    public boolean isPassable() {
        return me.isPassable();
    }

    @Override
    public Image getImage() {
        return me.getImage();
    }

    @Override
    public void action(Item o) {
        if (me instanceof Wall)
            if (o instanceof Player) {
                Point2D position = getPosition();
                me = new Ground(position.getX(), position.getY());
            }
    }
}
