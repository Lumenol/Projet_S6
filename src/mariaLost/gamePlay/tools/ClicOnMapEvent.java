package mariaLost.gamePlay.tools;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseButton;

/**
 * Created by crede on 15/02/2017.
 */
public class ClicOnMapEvent extends Event {

    public static final EventType<ClicOnMapEvent> CLIC_ON_MAP_EVENT_TYPE = new EventType<>(Event.ANY, "CLIC_ON_MAP_EVENT_TYPE");

    private Point2D coordonne;
    private MouseButton button;

    public ClicOnMapEvent(MouseButton buttton, double x, double y) {
        super(CLIC_ON_MAP_EVENT_TYPE);
        coordonne = new Point2D(x, y);
        this.button = buttton;
    }

    @Override
    public String toString() {
        return "ClicOnMapEvent{" +
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
