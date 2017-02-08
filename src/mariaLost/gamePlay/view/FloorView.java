package mariaLost.gamePlay.view;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import mariaLost.items.interfaces.Drawable;
import mariaLost.mainApp.model.Parameters;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by elsacollet on 01/02/2017.
 */
public class FloorView {
    private Canvas canvas;


    public FloorView() {
        this.canvas = new Canvas(Parameters.NUMBER_OF_CASE_X * mariaLost.parameters.Parameters.CASE_WIDTH, Parameters.NUMBER_OF_CASE_Y * mariaLost.parameters.Parameters.CASE_HEIGHT);
        //this.canvas.autosize();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void draw(Collection<Collection<? extends Drawable>> itemListView, Point2D subtract) {
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
