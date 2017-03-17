package mariaLost.parameters;

import javafx.geometry.Dimension2D;
import javafx.scene.image.Image;
import javafx.util.Duration;
import mariaLost.items.model.animation.SpriteSheetLoader;

/**
 * Created by elsacollet on 05/02/2017.
 */
public class Parameters_MariaLost {
    public static final int CASE_WIDTH = 150;
    public static final int CASE_HEIGHT = 150;



    public static final int MOVABLE_ITEM_WIDTH = 70;
    public static final int MOVABLE_ITEM_HEIGHT = 80;
    public static final int PLAY_PAGE_WIDTH = 800;
    public static final int PLAY_PAGE_HEIGHT = 800;
    public static final int PAGE_WIDTH = 600;
    public static final int PAGE_HEIGHT = 600;


    /**
     * Gestion des fichiers xml
     */

    public static final String FILEPATH_PLAY = "../../gamePlay/view/playLayout.fxml";
    public static final String FILEPATH_USER_OVERVIEW = "../../user/view/userOverview.fxml";
    public static final String FILEPATH_USER_DETAILS = "../../user/view/userDetails.fxml";
    public static final String FILEPATH_PLAYER_BAR = "../../gamePlay/view/playerBar.fxml";
    public static final String FILEPATH_MAP = "resources/Floor/";
    public static final String FILENAME_FILE_USER = "resources/users.xml";
    public static final String FILEPATH_ENDPAGE = "../../gamePlay/view/endPage.fxml";
    public static final String FILEPATH_DEFAUTL_MAP = "resources/Floor/0.txt";

    /**
     * Gestion des images
     */

    public static final Image IMAGE_LOGO = new Image("file:resources/Images/logo.png");
    public static final Image IMAGE_GOLD = new Image("file:resources/Images/tiled/gold_nugget.png");
    public static final Image IMAGE_HEART = new Image("file:resources/Images/heart.png", 40, 40, true, true);
    public static final Image IMAGE_WALL = new Image("file:resources/Images/tiled/brick.png");
    public static final Image IMAGE_GROUND = new Image("file:resources/Images/tiled/sandstone_top.png");
    public static final Image IMAGE_END_CASE = new Image("file:resources/Images/tiled/trapdoor.png");


    /**
     * Name of images
     */


    public static final String AVATAR_DEFAULT = "file:resources/Images/WalkingAnimationPlayer/Front/1.png";
    public static final Image SPIDER = new Image("file:resources/Images/spider/spider_sprite_sheet.png");
    public static final Image SKELETON=new Image("file:resources/Images/skeleton/skeleton_sprite_sheet.png");
    public static final Image FIREBALL = new Image("file:resources/Images/fireball/fireball_sprite_sheet.png");
    public static final Image TRANSPARENT_IMAGE = new Image("file:resources/Images/transparant_image.png");
    /**
     * Gameplay
     */
    public static final Duration DAMAGE_RECOVERY_TIME = new Duration(500);
    public static final Duration FRAME_ANIMATION_DURATION = new Duration(100);
    public static final int GAME_OVER_CODE = 1;
    public static final int NEXT_LEVEL_CODE = 0;
    public static final int SCORE_LOOSE_GAME_OVER = -100;
    public static final int QUANTITEE_ARGENT_PIECE = 10;
    public static final double DELAY_BETWEEN_FIREBALL = 250;
    public static final Duration BLINKING_TIME = new Duration(500);
    public static final int NUMBER_OF_BLINK =4;
    public static final int ALIGNEMENT_MARGIN =5;

   
    /**
     * Fireball
     */
    
    public static final int FIREBALL_DAMAGE = 10;
    public static final int FIREBALL_SPEED = 11;
    public static final Duration FIREBALL_DURATION = new Duration(3000);
    
    /**
     * All enemies
     */
    public static final int DAMAGE_CONTACT=10;

    /**
     * Player
     */
    public static final int PLAYER_SPEED_LIMIT = 10;
    public static final double PLAYER_LIFE_POINT_START = 100;

    /**
     * Spider
     */
    public static final int SPIDER_SPRITE_NB_ROW=5;
    public static final int SPIDER_SPRITE_NB_COLUMN=10;
    //public static final double SPIDER_WIDTH = SpriteSheetLoader.load(SPIDER, SPIDER_SPRITE_NB_ROW, SPIDER_SPRITE_NB_COLUMN, 1, 1).get(0).getWidth();
    public static final double SPIDER_WIDTH = 80;
    //public static final double SPIDER_HEIGHT=SpriteSheetLoader.load(SPIDER, SPIDER_SPRITE_NB_ROW, SPIDER_SPRITE_NB_COLUMN, 1, 1).get(0).getHeight();
    public static final double SPIDER_HEIGHT=80;
    public static final Dimension2D SPIDER_ATTACK_DIMENSION = new Dimension2D(10, 10);
    public static final Duration SPIDER_ATTACK_DURATION = new Duration(400);
    public static final Duration SPIDER_MOVEMENT_DURATION = new Duration(250);
    public static final int SPIDER_SPEED_LIMIT = 8;
    public static final int  SPIDER_QUANTITEE_ARGENT = 20;
    public static final double  SPIDER_LIFE_POINT_START = 30;
    public static final int  SPIDER_AGRO_RADIUS=300;
    public static final int  SPIDER_ATTACK_RANGE=3;
    public static final int SPIDER_ATTACK_DAMAGE=15;

    /**
     * Skeleton
     */
    public static final int SKELETON_SPRITE_NB_ROW=21;
    public static final int SKELETON_SPRITE_NB_COLUMN=13;
    //public static final double SKELETON_WIDTH=SpriteSheetLoader.load(SKELETON, SKELETON_SPRITE_NB_ROW, SKELETON_SPRITE_NB_COLUMN, 131, 131).get(0).getWidth();
    public static final double SKELETON_WIDTH=80;
    //public static final double SKELETON_HEIGHT=SpriteSheetLoader.load(SKELETON, SKELETON_SPRITE_NB_ROW, SKELETON_SPRITE_NB_COLUMN, 131, 131).get(0).getHeight();
    public static final double SKELETON_HEIGHT=80;
    public static final Dimension2D SKELETON_ATTACK_DIMENSION = new Dimension2D(10, 10);
    public static final Duration SKELETON_ATTACK_DURATION = new Duration(600);
    public static final Duration SKELETON_MOVEMENT_DURATION = new Duration(300);
    public static final int SKELETON_SPEED_LIMIT = 6;
    public static final int  SKELETON_QUANTITEE_ARGENT = 40;
    public static final double  SKELETON_LIFE_POINT_START = 50;
    public static final int  SKELETON_AGRO_RADIUS=250;
    public static final int  SKELETON_ATTACK_RANGE=3;
    public static final int SKELETON_ATTACK_DAMAGE=20;

}
