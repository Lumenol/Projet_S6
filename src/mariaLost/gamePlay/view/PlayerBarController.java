package mariaLost.gamePlay.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mariaLost.gamePlay.controller.GameLayoutController;
import mariaLost.items.model.Player;
import mariaLost.parameters.Parameters_MariaLost;
import mariaLost.user.model.User;

/**
 * Created by elsacollet on 06/02/2017.
 */
public class PlayerBarController {


    @FXML
    protected ImageView moneyImageView;
    private GameLayoutController mainApp;
    private User user;
    @FXML
    private Label moneyLabel;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label levelLabel;
    @FXML
    private ImageView heartImageView;
    @FXML
    private Label nameUserLabel;

    public PlayerBarController() {
    }

    @FXML
    public void initialize() {
        System.out.println("initialisation game layout");

    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setMainApp(GameLayoutController main) {
        this.mainApp = main;
    }

    public void setBar(Player p) {
        this.scoreLabel.setText(Integer.toString(this.user.getScore()));
        this.levelLabel.setText(Integer.toString(this.user.getLevel()));
        this.nameUserLabel.setText(this.user.getName());
        //this.moneyLabel.textProperty().bind(p.moneyProperty().asString()); // ne marche pas car modification de la propriété par un thread autre que FX
        this.moneyLabel.setText(Integer.toString(p.getMoney()));
        // this.heartImageView.setImage(new Image("file:resources/Images/heart3.png"));
        this.moneyImageView.setImage(new Image(Parameters_MariaLost.IMAGE_GOLD));
    }

}
