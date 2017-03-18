package mariaLost.gamePlay.model;

import javafx.geometry.Dimension2D;
import javafx.geometry.Rectangle2D;
import mariaLost.gamePlay.interfaces.DrawableFloor;
import mariaLost.gamePlay.interfaces.Floor;
import mariaLost.items.interfaces.Drawable;
import mariaLost.items.model.AbstractItem;
import mariaLost.parameters.Parameters_MariaLost;

import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by crede on 06/02/2017.
 */
public abstract class AbstractFloor implements Floor, DrawableFloor {

    protected AbstractItem[][] items = null;
    protected Dimension2D dimension;
    protected Rectangle2D beginning = null;
    protected Rectangle2D end = null;
    protected Collection<AbstractItem> gettingItemList = new LinkedList<>();

    public AbstractFloor() {

    }

    @Override
    /**
     * Get all the items from a specific square.
     * @param square
     * 				The square to get the items from.
     * @return a Deque of abstract items
     */
    public Deque<? extends AbstractItem> getItemFromSquare(Rectangle2D square) {

        int minWidth = Math.max(0, (int) square.getMinX()) / Parameters_MariaLost.CASE_WIDTH;
        double maxWidth = Math.min(dimension.getWidth(), square.getMaxX()) / Parameters_MariaLost.CASE_WIDTH;

        int minHeight = Math.max(0, (int) square.getMinY()) / Parameters_MariaLost.CASE_HEIGHT;
        double maxHeight = Math.min(dimension.getHeight(), square.getMaxY()) / Parameters_MariaLost.CASE_WIDTH;

        LinkedList<AbstractItem> linkedList = new LinkedList<>();

        for (int i = minHeight; i < maxHeight; i++) {
            for (int j = minWidth; j < maxWidth; j++) {
                linkedList.add(items[i][j]);
            }
        }
        return linkedList;
    }

    @Override
    public Dimension2D getDimension() {
        return dimension;
    }

    @Override
    public Collection<AbstractItem> getItems() {
        return gettingItemList;
    }

    @Override
    public Rectangle2D getBeginning() {
        return beginning;
    }

    @Override
    public Rectangle2D getEnd() {
        return end;
    }

    /**
     * Get all the items from a specific square.
     *
     * @param square The square to get the items from.
     * @return a Deque of Drawable
     */
    @Override
    public Deque<Collection<? extends Drawable>> getDrawableFromSquare(Rectangle2D square) {
        LinkedList<Collection<? extends Drawable>> linkedList = new LinkedList<>();
        linkedList.addLast(getItemFromSquare(square));
        return linkedList;
    }
}
