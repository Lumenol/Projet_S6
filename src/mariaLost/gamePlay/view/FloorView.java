package mariaLost.gamePlay.view;

import javafx.geometry.Dimension2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import mariaLost.items.interfaces.Drawable;
import mariaLost.items.interfaces.Movable;
import mariaLost.items.model.Item;
import mariaLost.mainApp.model.Parameters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by elsacollet on 01/02/2017.
 */
public class FloorView {
    private Canvas canvas;


    public FloorView(Dimension2D dimension2D) {
        this.canvas = new Canvas(Parameters.NUMBER_OF_CASE_X * Parameters.CASE_WIDTH, Parameters.NUMBER_OF_CASE_Y *Parameters.CASE_HEIGHT );
        //this.canvas.autosize();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void draw(Collection<? extends Item > itemListView){
        GraphicsContext context = this.canvas.getGraphicsContext2D();
        context.fill();
        for (Iterator<? extends Item> iterator = itemListView.iterator(); iterator.hasNext(); ) {
            Item next = iterator.next();
            context.drawImage(next.getImage() , next.getPosition().getX(), next.getPosition().getY(), next.getSize().getHeight(), next.getSize().getWidth());
        }
    }

}
