package mariaLost.gamePlay.view;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import mariaLost.items.interfaces.Drawable;
import mariaLost.parameters.Parameters_MariaLost;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by elsacollet on 01/02/2017.
 */
public class FloorView {
    private Canvas canvas;


    public FloorView() {
        this.canvas = new Canvas(
                Parameters_MariaLost.NUMBER_OF_CASE_X * Parameters_MariaLost.CASE_WIDTH
                , Parameters_MariaLost.NUMBER_OF_CASE_Y * Parameters_MariaLost.CASE_HEIGHT);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void draw(Collection<Collection<? extends Drawable>> itemListView, Point2D subtract) {
        subtract = new Point2D((int) subtract.getX(), (int) subtract.getY());
        GraphicsContext context = this.canvas.getGraphicsContext2D();
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (Iterator<Collection<? extends Drawable>> iterator = itemListView.iterator(); iterator.hasNext(); ) {
            Collection<? extends Drawable> next = iterator.next();
            for (Iterator<? extends Drawable> iterator1 = next.iterator(); iterator1.hasNext(); ) {
                Drawable drawable = iterator1.next();
                Rectangle2D drawableBounds = drawable.getBounds();
                context.drawImage(drawable.getImage(), drawableBounds.getMinX() - subtract.getX(), drawableBounds.getMinY() - subtract.getY(), drawableBounds.getWidth(), drawableBounds.getHeight());
            }
        }

    }

}
