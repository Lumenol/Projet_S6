package mariaLost.gamePlay.view;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import mariaLost.parameters.Parameters_MariaLost;

/**
 * Created by crede on 22/02/2017.
 */
public class LifeBar extends Pane {

    private SimpleDoubleProperty progress = new SimpleDoubleProperty(0.5);
    private Rectangle redLine = new Rectangle();

    private Rectangle back = new Rectangle();

    private HBox heart = new HBox();

    public LifeBar(int nbCoeur) {


        progress.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double v = newValue.doubleValue();
                if (v < 0)
                    v = 0;
                else if (v > 1)
                    v = 1;
                redLine.setWidth(v * heart.getWidth());
            }
        });


        getChildren().addAll(back, redLine, heart);
        Image image = new Image(Parameters_MariaLost.IMAGE_HEART, 40, 40, true, true);

        heart.setMaxSize(nbCoeur * image.getWidth(), image.getHeight());

        for (int i = 0; i < nbCoeur; i++)
            heart.getChildren().addAll(new ImageView(image));

        redLine.setFill(Color.RED);

        redLine.translateXProperty().bind(heart.translateXProperty());
        redLine.translateYProperty().bind(heart.translateYProperty());

        redLine.heightProperty().bind(heart.heightProperty());

        back.widthProperty().bind(widthProperty());
        back.heightProperty().bind(heightProperty());
        back.setFill(Color.WHITE);
    }

    public SimpleDoubleProperty progressProperty() {
        return progress;
    }


}
