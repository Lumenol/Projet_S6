package mariaLost.items.interfaces;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

/**
 * Created by crede on 31/01/2017.
 */
public interface Drawable {
    /**
     * Getter for an item bounds.
     *
     * @return a Rectangle2D which is the area taken by this item in the plan.
     */
    Rectangle2D getBounds();

    /**
     * Getter for an item image.
     *
     * @return an Image
     */
    Image getImage();
}
