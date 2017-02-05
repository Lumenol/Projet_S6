package mariaLost.items.model;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import mariaLost.items.interfaces.Drawable;
import mariaLost.mainApp.model.Parameters;

/**
 * Created by elsacollet on 01/02/2017.
 */
public class Item implements mariaLost.items.interfaces.Item, Drawable {

    public Rectangle2D size;
    protected Point2D position;
    private String name;
    private String spriteName;
    private boolean passable;


    /**
     * Constructeur d'un objet
     * @param codeItem code du fichier transformant en Item
     */
    public Item(int codeItem, int x, int y){
        switch (codeItem) {
            case 0:
                this.name="dirt";
                this.spriteName = "dirt.png";
                this.passable = true;
                this.position = new Point2D(x, y);
                size = new Rectangle2D(x *Parameters.CASE_WIDTH, y* Parameters.CASE_HEIGHT, Parameters.CASE_WIDTH, Parameters.CASE_HEIGHT);
                break;
            case 1:
                this.name="wall";
                this.spriteName = "wall.png";
                this.passable = false;
                this.position = new Point2D(x, y);
                this.size = new Rectangle2D(x, y, Parameters.CASE_WIDTH, Parameters.CASE_HEIGHT);
                break;
            default:
                break;
        }
    }

    public Item(String name, String spriteName, boolean passable){
        this.name=name;
        this.spriteName = spriteName;
        this.passable = passable;
        this.position = new Point2D(0, 0);
        this.size = new Rectangle2D(0, 0, 0, 0);
    }

    @Override
    public Point2D getPosition() {
        return position;
    }

    @Override
    public boolean isPassable() {
        return passable;
    }

    @Override
    public Rectangle2D getBounds() {
        return size;
    }

    @Override
    public Image getImage() {
       return new Image("file:resources/Images/"+ spriteName);
    }

    public String getName() {
        return name;
    }
    public String getSpriteName() {
        return spriteName;
    }
    public Rectangle2D getSize() {
        return size;
    }
}
