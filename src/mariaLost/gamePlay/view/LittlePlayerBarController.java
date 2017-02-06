package mariaLost.gamePlay.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import mariaLost.mainApp.controller.MainApp;
import mariaLost.player.model.Player;

/**
 * Created by elsacollet on 06/02/2017.
 */
public class LittlePlayerBarController {

    private GameLayoutController mainApp;
    private Player player;
    AnchorPane littlePlayerOverview;
    @FXML
    private Label moneyLabel;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label levelLabel;
    @FXML
    private ImageView heartImageView;
    @FXML
    protected ImageView moneyImageView;
    @FXML
    private Label namePlayerLabel;

    @FXML
    public void initialize(){
        System.out.println("initialisation game layout");

    }
    public void setPlayer(Player player){
        this.player = player;
    }
    public void setMainApp(GameLayoutController main){
        this.mainApp = main;
    }

    public LittlePlayerBarController() {
    }
    public void setBar(){
        this.scoreLabel.setText(Integer.toString(this.player.getScore()));
        this.levelLabel.setText(Integer.toString(this.player.getLevel())) ;
        this.namePlayerLabel.setText(this.player.getName()) ;
        this.moneyLabel.setText("0");
        this.heartImageView.setImage(new Image("file:resources/Images/heart3.png"));
        this.moneyImageView.setImage(new Image("file:resources/Images/moneyFull.png"));
    }

}
