package mariaLost.gamePlay.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import mariaLost.gamePlay.controller.GameLayoutController;
import mariaLost.user.model.User;

/**
 * Created by elsacollet on 06/02/2017.
 */
public class LittlePlayerBarController {

    @FXML
    protected ImageView moneyImageView;
    AnchorPane littlePlayerOverview;
    private GameLayoutController mainApp;
    private User player;
    @FXML
    private Label moneyLabel;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label levelLabel;
    @FXML
    private ImageView heartImageView;
    @FXML
    private Label namePlayerLabel;

    public LittlePlayerBarController() {
    }

    @FXML
    public void initialize() {
        System.out.println("initialisation game layout");

    }

    public void setPlayer(User player) {
        this.player = player;
    }

    public void setMainApp(GameLayoutController main) {
        this.mainApp = main;
    }

    public void setBar() {
        this.scoreLabel.setText(Integer.toString(this.player.getScore()));
        this.levelLabel.setText(Integer.toString(this.player.getLevel()));
        this.namePlayerLabel.setText(this.player.getName());
        this.moneyLabel.setText("0");
        this.heartImageView.setImage(new Image("file:resources/Images/heart3.png"));
        this.moneyImageView.setImage(new Image("file:resources/Images/moneyFull.png"));
    }

}
