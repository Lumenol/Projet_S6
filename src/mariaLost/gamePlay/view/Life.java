package mariaLost.gamePlay.view;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Created by crede on 22/02/2017.
 */
public class Life extends Pane {

    private VBox vBox = new VBox();


    private SimpleDoubleProperty progress = new SimpleDoubleProperty(0);


    public Life(int width, int height) {

        getChildren().add(vBox);

        for (int i = 0; i < height; i++) {
            LifeBar lifeBar = new LifeBar(width);
            lifeBar.progressProperty().bind((progress.subtract((double) i / height)).multiply(height));
            vBox.getChildren().add(lifeBar);
        }


    }

    public void setProgress(double progress) {
        this.progress.set(progress);
    }

    public SimpleDoubleProperty progressProperty() {
        return progress;
    }
}
