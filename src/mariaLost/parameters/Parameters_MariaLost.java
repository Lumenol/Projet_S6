package mariaLost.parameters;

import javafx.scene.image.Image;
import javafx.util.Duration;

/**
 * Created by elsacollet on 05/02/2017.
 */
public class Parameters_MariaLost {
    public static final int CASE_WIDTH = 50;
    public static final int CASE_HEIGHT = 50;
    public static final int MOVABLE_ITEM_WIDTH = 40;
    public static final int MOVABLE_ITEM_HEIGHT = 40;
    public static final int PLAY_PAGE_WIDTH = 600;
    public static final int PLAY_PAGE_HEIGHT = 600;
    public static final int PAGE_WIDTH = 600;
    public static final int PAGE_HEIGHT = 600;


    public static final Duration DAMAGE_RECOVERY_TIME = new Duration(1000);

    /**
     * Gestion des fichiers xml
     */

    public static final String FILEPATH_PLAY = "../../gamePlay/view/playLayout.fxml";
    public static final String FILEPATH_USER_OVERVIEW = "../../user/view/userOverview.fxml";
    public static final String FILEPATH_USER_DETAILS = "../../user/view/userDetails.fxml";
    public static final String FILEPATH_PLAYER_BAR = "../../gamePlay/view/playerBar.fxml";
    public static final String FILEPATH_MAP = "resources/Floor/";
    public static final String FILENAME_FILE_USER = "resources/configuration.xml";
    public static final String FILEPATH_ENDPAGE = "../../gamePlay/view/endPage.fxml";
    ;
    public static final String FILEPATH_DEFAUTL_MAP = "resources/Floor/0.txt";

    /**
     * Gestion des images
     */

    public static final Image IMAGE_LOGO = new Image("file:resources/Images/logo.png");
    public static final Image IMAGE_GOLD = new Image("file:resources/Images/gold.png");
    public static final Image IMAGE_HEART = new Image("file:resources/Images/heart.png", 40, 40, true, true);
    public static final Image IMAGE_WALL = new Image("file:resources/Images/wall.png");
    public static final Image IMAGE_DIRT = new Image("file:resources/Images/dirt.png");
    public static final Image IMAGE_END_CASE = new Image("file:resources/Images/floor_green.png");
    public static final Image SPIDER = new Image("file:resources/Images/spider/spider_sprite_sheet.png");
    public static final Image FIREBALL = new Image("file:resources/Images/fireball/fireball_sprite_sheet.png");
    public static final Image TRANSPARENT_IMAGE = new Image("file:resources/Images/transparant_image.png");

    /**
     * Name of images
     */


    public static final String AVATAR_DEFAULT = "file:resources/Images/WalkingAnimationPlayer/Front/1.png";

    /**
     * Gameplay
     */

    public static final int GAME_OVER_CODE = 1;
    public static final int NEXT_LEVEL_CODE = 0;
    public static final int SCORE_LOOSE_GAME_OVER = -100;
    public static final double LIFE_POINT_START_PLAYER = 100;
    public static final double LIFE_POINT_START_ENNEMIE = 50;
    public static final int QUANTITEE_ARGENT_PIECE = 10;
    public static final int QUANTITEE_ARGENT_SPIDER = 20;
    public static final int DAMAGE_FIREBALL = (int) (LIFE_POINT_START_ENNEMIE / 5);
    public static double DELAY_BETWEEN_FIREBALL = 250;
    public static Duration BLINKING_TIME = new Duration(500);

}
