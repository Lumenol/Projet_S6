package mariaLost.player.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by elsacollet on 23/01/2017.
 */
public class Player {

    private final StringProperty name;
    private final IntegerProperty score;
    private final IntegerProperty level;

    public Player(){
        this.name= new SimpleStringProperty("");
        this.score= new SimpleIntegerProperty(0);
        this.level=new SimpleIntegerProperty(0);
    }
    public Player(String name){
        this.name= new SimpleStringProperty(name);
        this.score= new SimpleIntegerProperty(0);
        this.level=new SimpleIntegerProperty(0);
    }
    public Player(String name, int score, int level){
        this.name = new SimpleStringProperty(name);
        this.score = new SimpleIntegerProperty(score);
        this.level = new SimpleIntegerProperty(level);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getScore() {
        return score.get();
    }

    public IntegerProperty scoreProperty() {
        return score;
    }

    public void setScore(int score) {
        this.score.set(score);
    }

    public int getLevel() {
        return level.get();
    }

    public IntegerProperty levelProperty() {
        return level;
    }

    public void setLevel(int level) {
        this.level.set(level);
    }
}
