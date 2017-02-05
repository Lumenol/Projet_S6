package mariaLost.gamePlay.view;

import javafx.geometry.Dimension2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import mariaLost.items.interfaces.Movable;
import mariaLost.items.model.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by elsacollet on 01/02/2017.
 */
public class FloorView {
    private Canvas canvas;


    public FloorView(Dimension2D dimension2D) {
        this.canvas = new Canvas();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void draw(Collection<? extends Item> itemListView){
        GraphicsContext context = this.canvas.getGraphicsContext2D();
        int x =0, y=0;
        for (Iterator<? extends Item> iterator = itemListView.iterator(); iterator.hasNext(); ) {
            Item next = iterator.next();
            x= (int) next.getPosition().getX();
            y= (int) next.getPosition().getY();

            Image im= new Image("file:resources/Images/"+next.getSpriteName());
            if(next instanceof Movable){
                context.drawImage(im, x, y, next.getSize()-10, next.getSize()-10);
            }else {
                context.drawImage(im, x, y, next.getSize(), next.getSize());
            }
        }
    }


}
