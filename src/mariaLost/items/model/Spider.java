package mariaLost.items.model;

import javafx.scene.image.Image;
import mariaLost.gamePlay.tools.Direction;
import mariaLost.items.model.animation.Animation;
import mariaLost.items.model.animation.SpriteSheetLoader;
import mariaLost.parameters.Parameters_MariaLost;

import java.util.List;

public class Spider extends AbstractEnemy {

    public Spider(double x, double y) {
        super(x, y,Parameters_MariaLost.SPIDER_WIDTH,Parameters_MariaLost.SPIDER_HEIGHT,Parameters_MariaLost.SPIDER_SPEED_LIMIT, Parameters_MariaLost.SPIDER_QUANTITEE_ARGENT, Parameters_MariaLost.SPIDER_LIFE_POINT_START);
        
        agroRadius = Parameters_MariaLost.SPIDER_AGRO_RADIUS;
        damageContact = Parameters_MariaLost.DAMAGE_CONTACT;
        attackRange = Parameters_MariaLost.SPIDER_ATTACK_RANGE;
        
        ////	goUp	////
        List<Image> spriteGoUp = SpriteSheetLoader.load(Parameters_MariaLost.SPIDER
        		,Parameters_MariaLost.SPIDER_SPRITE_NB_ROW
        		,Parameters_MariaLost.SPIDER_SPRITE_NB_COLUMN
        		,1
        		,1);
        spriteGoUp.addAll(SpriteSheetLoader.load(Parameters_MariaLost.SPIDER
        		, Parameters_MariaLost.SPIDER_SPRITE_NB_ROW
        		,Parameters_MariaLost.SPIDER_SPRITE_NB_COLUMN
        		,5
        		,10));
        
        Animation animationGoUp = new Animation(Parameters_MariaLost.FRAME_ANIMATION_DURATION, spriteGoUp);
        goUp = new Movement(Parameters_MariaLost.SPIDER_MOVEMENT_DURATION, Direction.UP, animationGoUp);

        ////	goLeft	////
        List<Image> spriteGoLeft = SpriteSheetLoader.load(Parameters_MariaLost.SPIDER
        		,Parameters_MariaLost.SPIDER_SPRITE_NB_ROW
        		,Parameters_MariaLost.SPIDER_SPRITE_NB_COLUMN
        		, 11
        		, 11);
        spriteGoLeft.addAll(SpriteSheetLoader.load(Parameters_MariaLost.SPIDER
        		, Parameters_MariaLost.SPIDER_SPRITE_NB_ROW
        		,Parameters_MariaLost.SPIDER_SPRITE_NB_COLUMN
        		, 15
        		, 20));
        Animation animationGoLeft = new Animation(Parameters_MariaLost.FRAME_ANIMATION_DURATION, spriteGoLeft);
        goLeft = new Movement(Parameters_MariaLost.SPIDER_MOVEMENT_DURATION, Direction.LEFT, animationGoLeft);

        ////	goDown	////
        List<Image> spriteGoDown = SpriteSheetLoader.load(Parameters_MariaLost.SPIDER
        		, Parameters_MariaLost.SPIDER_SPRITE_NB_ROW
        		,Parameters_MariaLost.SPIDER_SPRITE_NB_COLUMN
        		, 21
        		, 21);
        spriteGoDown.addAll(SpriteSheetLoader.load(Parameters_MariaLost.SPIDER
        		, Parameters_MariaLost.SPIDER_SPRITE_NB_ROW
        		,Parameters_MariaLost.SPIDER_SPRITE_NB_COLUMN
        		, 25
        		, 30));
        Animation animationGoDown = new Animation(Parameters_MariaLost.FRAME_ANIMATION_DURATION, spriteGoDown);
        goDown = new Movement(Parameters_MariaLost.SPIDER_MOVEMENT_DURATION, Direction.DOWN, animationGoDown);

        ////	goRight	////
        List<Image> spriteGoRight = SpriteSheetLoader.load(Parameters_MariaLost.SPIDER
        		, Parameters_MariaLost.SPIDER_SPRITE_NB_ROW
        		,Parameters_MariaLost.SPIDER_SPRITE_NB_COLUMN
        		, 31
        		, 31);
        spriteGoRight.addAll(SpriteSheetLoader.load(Parameters_MariaLost.SPIDER
        		, Parameters_MariaLost.SPIDER_SPRITE_NB_ROW
        		,Parameters_MariaLost.SPIDER_SPRITE_NB_COLUMN
        		, 35
        		, 40));
        Animation animationGoRight = new Animation(Parameters_MariaLost.FRAME_ANIMATION_DURATION, spriteGoRight);
        goRight = new Movement(Parameters_MariaLost.SPIDER_MOVEMENT_DURATION, Direction.RIGHT, animationGoRight);


        ////	attackUp	////
        List<Image> spriteBiteUp = SpriteSheetLoader.load(Parameters_MariaLost.SPIDER
        		, Parameters_MariaLost.SPIDER_SPRITE_NB_ROW
        		,Parameters_MariaLost.SPIDER_SPRITE_NB_COLUMN
        		, 1
        		, 4);
        Animation animationBiteUp = new Animation(Parameters_MariaLost.FRAME_ANIMATION_DURATION, spriteBiteUp);
        meleeUp = new MeleeAttack(Parameters_MariaLost.SPIDER_ATTACK_DIMENSION
        		, Direction.UP
        		,Parameters_MariaLost.SPIDER_ATTACK_DAMAGE
        		, animationBiteUp
        		, Parameters_MariaLost.SPIDER_ATTACK_DURATION);


        ////	attackLeft		////
        List<Image> spriteBiteLeft = SpriteSheetLoader.load(Parameters_MariaLost.SPIDER
        		, Parameters_MariaLost.SPIDER_SPRITE_NB_ROW
        		, Parameters_MariaLost.SPIDER_SPRITE_NB_COLUMN
        		, 11
        		, 14);
        Animation animationBiteLeft = new Animation(Parameters_MariaLost.FRAME_ANIMATION_DURATION, spriteBiteLeft);
        meleeLeft = new MeleeAttack(Parameters_MariaLost.SPIDER_ATTACK_DIMENSION
        		, Direction.LEFT, Parameters_MariaLost.SPIDER_ATTACK_DAMAGE
        		, animationBiteLeft
        		, Parameters_MariaLost.SPIDER_ATTACK_DURATION);


        ////	attackDown		////
        List<Image> spriteBiteDown = SpriteSheetLoader.load(Parameters_MariaLost.SPIDER
        		, Parameters_MariaLost.SPIDER_SPRITE_NB_ROW
        		,Parameters_MariaLost.SPIDER_SPRITE_NB_COLUMN
        		, 21
        		, 24);
        Animation animationBiteDown = new Animation(Parameters_MariaLost.FRAME_ANIMATION_DURATION, spriteBiteDown);
        meleeDown = new MeleeAttack(Parameters_MariaLost.SPIDER_ATTACK_DIMENSION, Direction.DOWN
        		, Parameters_MariaLost.SPIDER_ATTACK_DAMAGE, animationBiteDown
        		, Parameters_MariaLost.SPIDER_ATTACK_DURATION);

        ////	attackRight		////
        List<Image> spriteBiteRight = SpriteSheetLoader.load(Parameters_MariaLost.SPIDER
        		, Parameters_MariaLost.SPIDER_SPRITE_NB_ROW
        		,Parameters_MariaLost.SPIDER_SPRITE_NB_COLUMN
        		, 31
        		, 34);
        Animation animationBiteRight = new Animation(Parameters_MariaLost.FRAME_ANIMATION_DURATION, spriteBiteRight);
        meleeRight = new MeleeAttack(Parameters_MariaLost.SPIDER_ATTACK_DIMENSION
        		, Direction.RIGHT
        		, Parameters_MariaLost.SPIDER_ATTACK_DAMAGE
        		, animationBiteRight
        		, Parameters_MariaLost.SPIDER_ATTACK_DURATION);

        
        ////	Death		////

        List<Image> deathSprite = SpriteSheetLoader.load(Parameters_MariaLost.SPIDER
        		, Parameters_MariaLost.SPIDER_SPRITE_NB_ROW
        		,Parameters_MariaLost.SPIDER_SPRITE_NB_COLUMN
        		, 41
        		, 44);
        
        List<Image> lastSprite=SpriteSheetLoader.load(Parameters_MariaLost.SPIDER
        		, Parameters_MariaLost.SPIDER_SPRITE_NB_ROW
        		, Parameters_MariaLost.SPIDER_SPRITE_NB_COLUMN
        		, 44
        		, 44);
        deathSprite.addAll(lastSprite);
        deathSprite.addAll(lastSprite);
        deathSprite.addAll(lastSprite);

       
        death = new Animation(Parameters_MariaLost.FRAME_ANIMATION_DURATION, deathSprite);

        actualMovement = goDown;
        actualAttack = meleeDown;

        death.setAutoReplay(false);
    }
}
