package mariaLost.items.interfaces;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

/**
 * Created by crede on 31/01/2017.
 */
public interface Drawable {
    /**
     * @return Surface de l'objet dans le plan
     */
    Rectangle2D getBounds();

    /**
     * Image de l'objet
     *
     * @return
     */
    Image getImage();
}
