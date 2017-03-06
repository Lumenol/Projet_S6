package mariaLost.gamePlay.view;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import mariaLost.parameters.Parameters_MariaLost;

/**
 * Created by elsacollet on 06/02/2017.
 */
public class PlayerBarController {

    @FXML
    private GridPane rootPane;
    private Life lifeBar;
    @FXML
    private ImageView moneyImageView;
    @FXML
    private Label moneyLabel;
    @FXML
    private Label nameUserLabel;
    @FXML
    private Label levelLabel;
    @FXML
    private Label scoreLabel;

    private SimpleDoubleProperty life = new SimpleDoubleProperty();
    private SimpleIntegerProperty money = new SimpleIntegerProperty();
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleIntegerProperty level = new SimpleIntegerProperty();
    private SimpleIntegerProperty score = new SimpleIntegerProperty();

    public PlayerBarController() {

    }

    public void refresh() {
        moneyLabel.setText(money.getValue().toString());
        scoreLabel.setText(score.getValue().toString());
        nameUserLabel.setText(name.getValue());
        lifeBar.setProgress(life.get());
    }


    @FXML
    public void initialize() {
        moneyImageView.setImage(new Image(Parameters_MariaLost.IMAGE_GOLD));
        lifeBar = new Life(5, 2);
        rootPane.add(lifeBar, 0, 0);
    }

    public SimpleDoubleProperty lifeProperty() {
        return life;
    }

    public SimpleIntegerProperty moneyProperty() {
        return money;
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }


    public SimpleIntegerProperty levelProperty() {
        return level;
    }

    public SimpleIntegerProperty scoreProperty() {
        return score;
    }
}
