package mariaLost.items.model;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;

/**
 * Created by elsacollet on 01/02/2017.
 */
public class Item implements mariaLost.items.interfaces.Item {

    public final static int size = 30;
    private String name;
    private String spriteName;
    private boolean passable;
    private Point2D position;


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
                break;
            case 1:
                this.name="wall";
                this.spriteName = "wall.png";
                this.passable = true;
                this.position = new Point2D(x, y);
                break;
            default:
                break;
        }
    }

    public Item(String name, String spriteName, boolean passable){
        this.name=name;
        this.spriteName = spriteName;
        this.passable = passable;
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
    public Bounds getBounds() {
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpriteName() {
        return spriteName;
    }

    public void setSpriteName(String spriteName) {
        this.spriteName = spriteName;
    }

    public void setPassable(boolean passable) {
        this.passable = passable;
    }



    public int getSize() {
        return size;
    }







}
