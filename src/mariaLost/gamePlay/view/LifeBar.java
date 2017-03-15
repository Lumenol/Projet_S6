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
import mariaLost.mainApp.controller.Starter;
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

        getChildren().addAll(back, redLine, heart);
        Image image = new Image(Parameters_MariaLost.IMAGE_HEART, 40, 40, true, true);

        heart.setMaxSize(nbCoeur * image.getWidth(), image.getHeight());

        //Ajoute les coeurs a la ligne
        for (int i = 0; i < nbCoeur; i++)
            heart.getChildren().addAll(new ImageView(image));

        SimpleDoubleProperty ratio = new SimpleDoubleProperty(0);

        redLine.setFill(Color.RED);

        //ajuste la position du fond rouge a la position de la ligne
        redLine.translateXProperty().bind(heart.translateXProperty());
        redLine.translateYProperty().bind(heart.translateYProperty());

        //fait suivre la taille du fond rouge au remplissage des coeurs
        redLine.heightProperty().bind(heart.heightProperty());
        redLine.widthProperty().bind(ratio.multiply(heart.widthProperty()));

        //fond blanc des coeurs
        back.widthProperty().bind(widthProperty());
        back.heightProperty().bind(heightProperty());
        back.setFill(Color.WHITE);

        //Ajuste le taux de remplissage
        Starter start = Starter.getInstance();
        progress.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double v = newValue.doubleValue();
                System.out.println("v" + v);
                if (v < 0) {
                    v = 0;
                }else if (v > 1)
                    v = 1;
                ratio.set(v);
            }
        });

    }


    public SimpleDoubleProperty progressProperty() {
        return progress;
    }


}
