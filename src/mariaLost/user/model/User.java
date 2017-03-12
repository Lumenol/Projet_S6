package mariaLost.user.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import mariaLost.parameters.Parameters_MariaLost;

/**
 * Created by elsacollet on 23/01/2017.
 */
public class User {

    private final StringProperty name;
    private final IntegerProperty score;
    private final IntegerProperty level;
    private final StringProperty image;

    public User() {
        this.name = new SimpleStringProperty("");
        this.score = new SimpleIntegerProperty(0);
        this.level = new SimpleIntegerProperty(0);
        this.image = new SimpleStringProperty(Parameters_MariaLost.AVATAR_DEFAULT);
    }

    public User(String name) {
        this.name = new SimpleStringProperty(name);
        this.score = new SimpleIntegerProperty(0);
        this.level = new SimpleIntegerProperty(0);
        this.image = new SimpleStringProperty(Parameters_MariaLost.AVATAR_DEFAULT);

    }

    public User(String name, int score, int level) {
        this.name = new SimpleStringProperty(name);
        this.score = new SimpleIntegerProperty(score);
        this.level = new SimpleIntegerProperty(level);
        this.image = new SimpleStringProperty(Parameters_MariaLost.AVATAR_DEFAULT);
    }

    public String getName() {
        return name.get();
    }
    public int getScore() {
        return score.get();
    }
    public int getLevel() {
        return level.get();
    }
    public String getImage() {
        return image.get();
    }

    //Nécéssaire pour le userListWrapper
    public void setName(String name) {
        this.name.set(name);
    }
    public void setScore(int score) {
        this.score.set(score);
    }
    public void setLevel(int level) {
        this.level.set(level);
    }
    public void setImage(String image) {
        this.image.set(image);
    }


    public StringProperty nameProperty() {
        return name;
    }

    public IntegerProperty scoreProperty(){
        return this.score;
    }
    public StringProperty imageProperty() {
        return image;
    }





}
