package mariaLost.gamePlay.view;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseButton;

/**
 * Created by crede on 15/02/2017.
 */
public class ClicOnMap extends Event {

    public static EventType<ClicOnMap> CLIC_ON_MAP = new EventType<>();

    private Point2D coordonne;
    private MouseButton button;

    public ClicOnMap(MouseButton buttton, double x, double y) {
        super(CLIC_ON_MAP);
        coordonne = new Point2D(x, y);
        this.button = buttton;
    }

    @Override
    public String toString() {
        return "ClicOnMap{" +
                "coordonne=" + coordonne +
                ", button=" + button +
                '}';
    }

    public MouseButton getButton() {
        return button;
    }

    public double getX() {
        return coordonne.getX();
    }

    public double getY() {
        return coordonne.getY();
    }
}
