package mariaLost.gamePlay.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mariaLost.items.model.Player;
import mariaLost.mainApp.controller.Starter;
import mariaLost.parameters.Parameters_MariaLost;
import mariaLost.user.model.User;

/**
 * Created by elsacollet on 14/03/2017.
 */
public class EndPageController {

    private Starter start;
    private User user;
    @FXML
    private Label nameLabel;

    @FXML
    private Label text;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label levelLabel;
    @FXML
    private ImageView avatarImageView;
    @FXML
    private Label moneyLabel;

    @FXML
    private Label lifeLabel;

    public EndPageController() {
        start = Starter.getInstance();
    }


    public void setUser(User user, int code, Player player) {
        this.user = user;

        if (this.user != null) {
            this.nameLabel.setText(user.getName());
            this.scoreLabel.setText(Integer.toString(user.getScore()));
            this.levelLabel.setText(Integer.toString(user.getLevel()));
            this.avatarImageView.setImage(new Image(user.getImage()));
            this.moneyLabel.setText(Double.toString(player.getMonnayeur().getValue()));
            this.lifeLabel.setText(Double.toString(player.getLifePoint()));
            switch (code) {
                case Parameters_MariaLost.NEXT_LEVEL_CODE:
                    this.text.setText("Well Done !");
                    break;
                case Parameters_MariaLost.GAME_OVER_CODE:
                    this.text.setText("Game Over !");
            }
        }
    }

    @FXML
    public void handlePlay() {
        start.showPlayLayout(user);
    }

    @FXML
    public void handleMenu() {
        start.start();
    }


}
