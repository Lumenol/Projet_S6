package mariaLost.user.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by elsacollet on 23/01/2017.
 */
public class User {

    private final StringProperty name;
    private final IntegerProperty score;
    private final IntegerProperty level;

    public User() {
        this.name = new SimpleStringProperty("");
        this.score = new SimpleIntegerProperty(0);
        this.level = new SimpleIntegerProperty(0);
    }

    public User(String name) {
        this.name = new SimpleStringProperty(name);
        this.score = new SimpleIntegerProperty(0);
        this.level = new SimpleIntegerProperty(0);
    }

    public User(String name, int score, int level) {
        this.name = new SimpleStringProperty(name);
        this.score = new SimpleIntegerProperty(score);
        this.level = new SimpleIntegerProperty(level);
    }

    public String getName() {
        return name.get();
    }

    //Nécéssaire pour le userListWrapper
    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public int getScore() {
        return score.get();
    }

    public void setScore(int score) {
        this.score.set(score);
    }

    public int getLevel() {
        return level.get();
    }

    public void setLevel(int level) {
        this.level.set(level);
    }
}
