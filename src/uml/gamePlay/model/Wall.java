package uml.gamePlay.model;

import javafx.scene.image.Image;

/**
 * Created by crede on 06/02/2017.
 */
public class Wall extends AbstractItem {

    private static final Image image = new Image("file:resources/Images/wall.png");

    public Wall(double x, double y) {
        super(x, y, Parameters.CASE_WIDTH, Parameters.CASE_HEIGHT);
    }

    @Override
    public boolean isPassable() {
        return false;
    }

    @Override
    public Image getImage() {
        return image;
    }
}
