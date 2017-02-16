package mariaLost.gamePlay.view;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import mariaLost.gamePlay.interfaces.DrawableFloor;
import mariaLost.items.interfaces.Drawable;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

/**
 * Created by elsacollet on 01/02/2017.
 */
public class FloorView extends Canvas {

    private Dimension2D dimensionGameWindow;
    private Point2D origin = Point2D.ZERO;
    private SimpleObjectProperty<Point2D> folow = new SimpleObjectProperty<>(Point2D.ZERO);

    private SimpleDoubleProperty ratioX = new SimpleDoubleProperty(1);
    private SimpleDoubleProperty ratioY = new SimpleDoubleProperty(1);

    private DrawableFloor floor;


    public FloorView(DrawableFloor floor, double width, double height) {
        super(width, height);
        dimensionGameWindow = new Dimension2D(width, height);
        this.floor = floor;

        scaleXProperty().bind(ratioX);
        scaleYProperty().bind(ratioY);

        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Point2D point2D = new Point2D(event.getX(), event.getY());
                point2D = point2D.add(origin);
                fireEvent(new ClicOnMap(event.getButton(), point2D.getX(), point2D.getY()));
            }
        });

        folow.addListener(new ChangeListener<Point2D>() {
            @Override
            public void changed(ObservableValue<? extends Point2D> observable, Point2D oldValue, Point2D newValue) {
                double x = Math.max(0, newValue.getX() - dimensionGameWindow.getWidth() / 2);
                double y = Math.max(0, newValue.getY() - dimensionGameWindow.getHeight() / 2);

                x = Math.min(x, floor.getDimension().getWidth() - dimensionGameWindow.getWidth());
                y = Math.min(y, floor.getDimension().getHeight() - dimensionGameWindow.getHeight());

                origin = new Point2D(x, y);
            }
        });

    }

    public void refresh() {
        Rectangle2D area = new Rectangle2D(origin.getX(), origin.getY(), dimensionGameWindow.getWidth(), dimensionGameWindow.getHeight());
        Deque<Collection<? extends Drawable>> drawableFromSquare = floor.getDrawableFromSquare(area);
        draw(drawableFromSquare, origin);
    }

    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    public void resize(double width, double height) {
        /*setWidth(width);
        setHeight(height);*/

        ratioX.set(width / dimensionGameWindow.getWidth());
        ratioY.set(height / dimensionGameWindow.getHeight());

    }

    @Override
    public double prefWidth(double height) {
        return dimensionGameWindow.getWidth();
    }

    @Override
    public double prefHeight(double width) {
        return dimensionGameWindow.getHeight();
    }

    @Override
    public double minWidth(double height) {
        return prefWidth(height) / 2;
    }

    @Override
    public double minHeight(double width) {
        return prefHeight(width) / 2;
    }

    @Override
    public double maxWidth(double height) {
        return Double.MAX_VALUE;
    }

    @Override
    public double maxHeight(double width) {
        return Double.MAX_VALUE;
    }

    public Point2D getOrigin() {
        return origin;
    }

    public void setFolow(Point2D folow) {
        this.folow.set(folow);
    }

    public SimpleObjectProperty<Point2D> folowProperty() {
        return folow;
    }

    public void draw(Collection<Collection<? extends Drawable>> itemListView, Point2D origin) {
        origin = new Point2D((int) origin.getX(), (int) origin.getY());
        GraphicsContext context = getGraphicsContext2D();
        context.fillRect(0, 0, getWidth(), getHeight());
        for (Iterator<Collection<? extends Drawable>> iterator = itemListView.iterator(); iterator.hasNext(); ) {
            Collection<? extends Drawable> next = iterator.next();
            for (Iterator<? extends Drawable> iterator1 = next.iterator(); iterator1.hasNext(); ) {
                Drawable drawable = iterator1.next();
                Rectangle2D drawableBounds = drawable.getBounds();
                context.drawImage(drawable.getImage(), (drawableBounds.getMinX() - origin.getX()), (drawableBounds.getMinY() - origin.getY()), drawableBounds.getWidth(), drawableBounds.getHeight());
            }
        }
    }

}
