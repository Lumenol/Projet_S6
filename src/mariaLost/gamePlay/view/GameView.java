package mariaLost.gamePlay.view;

import javafx.animation.AnimationTimer;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import mariaLost.gamePlay.interfaces.Followable;
import mariaLost.parameters.Parameters_MariaLost;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by crede on 15/02/2017.
 */
public class GameView extends BorderPane {

    private PlayerBarController barController;
    private FloorView floorView;
    private AnimationTimer animationTimer;

    private Map<KeyCode, Boolean> keyState = new HashMap<>();

    public GameView(Followable floor) throws IOException {
        //Charge le fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Parameters_MariaLost.FILEPATH_PLAYER_BAR));
        Pane bar = loader.load();
        barController = loader.getController();
        setTop(bar);

        //Crée le Canvas principal
        floorView = new FloorView(floor, Parameters_MariaLost.PLAY_PAGE_WIDTH, Parameters_MariaLost.PLAY_PAGE_HEIGHT);
        setCenter(floorView);

        //Centre du joueur
        SimpleObjectProperty<Point2D> centerPlayer = new SimpleObjectProperty<>(Point2D.ZERO);
        floorView.folowProperty().bind(centerPlayer);

        //Enregistre l'état des touche
        floorView.setOnKeyPressed(event -> keyState.put(event.getCode(), true));
        floorView.setOnKeyReleased(event -> keyState.put(event.getCode(), false));

//rafraichi la vu
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                requestFocus();
                centerPlayer.set(floor.centerOfPlayer());
                barController.refresh();
                floorView.refresh();
            }
        };

    }

    public void clearKey() {
        keyState.clear();
    }

    @Override
    public void requestFocus() {
        floorView.requestFocus();
    }

    public boolean isKeyPressed(KeyCode keyCode) {
        return keyState.getOrDefault(keyCode, false);
    }

    public void start() {
        animationTimer.start();
    }

    public void stop() {
        animationTimer.stop();
    }

    public SimpleDoubleProperty lifeProperty() {
        return barController.lifeProperty();
    }

    public SimpleIntegerProperty moneyProperty() {
        return barController.moneyProperty();
    }

    public SimpleStringProperty nameProperty() {
        return barController.nameProperty();
    }

    public SimpleIntegerProperty levelProperty() {
        return barController.levelProperty();
    }

    public SimpleIntegerProperty scoreProperty() {
        return barController.scoreProperty();
    }
}
