package mariaLost.items.model;

import javafx.scene.image.Image;
import mariaLost.gamePlay.tools.Direction;
import mariaLost.items.model.animation.Animation;
import mariaLost.items.model.animation.SpriteSheetLoader;
import mariaLost.parameters.Parameters_MariaLost;

import java.util.List;
/**
 * Skeleton Class
 * @author loic
 *
 */
public class Skeleton  extends AbstractEnemy{

	public Skeleton(double x
, double y) {
		super(x, y
				, Parameters_MariaLost.SKELETON_WIDTH
				, Parameters_MariaLost.SKELETON_HEIGHT
				, Parameters_MariaLost.SKELETON_SPEED_LIMIT
				, Parameters_MariaLost.SKELETON_QUANTITEE_ARGENT
				, Parameters_MariaLost.SKELETON_LIFE_POINT_START);
		
		agroRadius = Parameters_MariaLost.SKELETON_AGRO_RADIUS;
		damageContact = Parameters_MariaLost.DAMAGE_CONTACT;
		attackRange = Parameters_MariaLost.SKELETON_ATTACK_RANGE;
		
		
        ////	goUp	////
		List<Image> spriteGoUp = SpriteSheetLoader.load(Parameters_MariaLost.SKELETON
									, Parameters_MariaLost.SKELETON_SPRITE_NB_ROW
									, Parameters_MariaLost.SKELETON_SPRITE_NB_COLUMN
									, 105
									, 113);
		Animation animationGoUp = new Animation(Parameters_MariaLost.FRAME_ANIMATION_DURATION, spriteGoUp);
		goUp = new Movement(Parameters_MariaLost.SKELETON_MOVEMENT_DURATION, Direction.UP, animationGoUp);

        ////	goLeft	////
		List<Image> spriteGoLeft = SpriteSheetLoader.load(Parameters_MariaLost.SKELETON
									, Parameters_MariaLost.SKELETON_SPRITE_NB_ROW
									, Parameters_MariaLost.SKELETON_SPRITE_NB_COLUMN				
									, 118
									, 123);
		Animation animationGoLeft = new Animation(Parameters_MariaLost.FRAME_ANIMATION_DURATION, spriteGoLeft);
		goLeft = new Movement(Parameters_MariaLost.SKELETON_MOVEMENT_DURATION, Direction.LEFT, animationGoLeft);

        ////	goDown	////
		List<Image> spriteGoDown = SpriteSheetLoader.load(Parameters_MariaLost.SKELETON
									, Parameters_MariaLost.SKELETON_SPRITE_NB_ROW
									, Parameters_MariaLost.SKELETON_SPRITE_NB_COLUMN
									, 131
									, 136);
		Animation animationGoDown = new Animation(Parameters_MariaLost.FRAME_ANIMATION_DURATION, spriteGoDown);
		goDown = new Movement(Parameters_MariaLost.SKELETON_MOVEMENT_DURATION, Direction.DOWN, animationGoDown);

        ////	goRight	////
		List<Image> spriteGoRight = SpriteSheetLoader.load(Parameters_MariaLost.SKELETON
									, Parameters_MariaLost.SKELETON_SPRITE_NB_ROW
									, Parameters_MariaLost.SKELETON_SPRITE_NB_COLUMN			
									, 144
									, 149);
		Animation animationGoRight = new Animation(Parameters_MariaLost.FRAME_ANIMATION_DURATION, spriteGoRight);
		goRight = new Movement(Parameters_MariaLost.SKELETON_MOVEMENT_DURATION, Direction.RIGHT, animationGoRight);


        ////	attackUp	////
		List<Image> spriteAttackUp = SpriteSheetLoader.load(Parameters_MariaLost.SKELETON
									, Parameters_MariaLost.SKELETON_SPRITE_NB_ROW
									, Parameters_MariaLost.SKELETON_SPRITE_NB_COLUMN				
									, 157
									, 162);
		Animation animationAttackUp = new Animation(Parameters_MariaLost.FRAME_ANIMATION_DURATION, spriteAttackUp);
		meleeUp = new MeleeAttack(Parameters_MariaLost.SKELETON_ATTACK_DIMENSION
								, Direction.UP
								,Parameters_MariaLost.SKELETON_ATTACK_DAMAGE
								, animationAttackUp
								, Parameters_MariaLost.SKELETON_ATTACK_DURATION);

        ////	attackLeft		////
		List<Image> spriteAttackLeft = SpriteSheetLoader.load(Parameters_MariaLost.SKELETON
										, Parameters_MariaLost.SKELETON_SPRITE_NB_ROW
										, Parameters_MariaLost.SKELETON_SPRITE_NB_COLUMN				
										, 170
										, 175);
		Animation animationAttackLeft = new Animation(Parameters_MariaLost.FRAME_ANIMATION_DURATION, spriteAttackLeft);
		meleeLeft = new MeleeAttack(Parameters_MariaLost.SKELETON_ATTACK_DIMENSION
									, Direction.LEFT
									, Parameters_MariaLost.SKELETON_ATTACK_DAMAGE
									, animationAttackLeft
									, Parameters_MariaLost.SKELETON_ATTACK_DURATION);


        ////	attackDown		////
		List<Image> spriteAttackDown = SpriteSheetLoader.load(Parameters_MariaLost.SKELETON
										, Parameters_MariaLost.SKELETON_SPRITE_NB_ROW
										, Parameters_MariaLost.SKELETON_SPRITE_NB_COLUMN				
										, 183
										, 188);
		Animation animationAttackDown = new Animation(Parameters_MariaLost.FRAME_ANIMATION_DURATION, spriteAttackDown);
		meleeDown = new MeleeAttack(Parameters_MariaLost.SKELETON_ATTACK_DIMENSION
									, Direction.DOWN
									, Parameters_MariaLost.SKELETON_ATTACK_DAMAGE
									, animationAttackDown
									, Parameters_MariaLost.SKELETON_ATTACK_DURATION);

        ////	attackRight		////
		List<Image> spriteAttackRight = SpriteSheetLoader.load(Parameters_MariaLost.SKELETON
										, Parameters_MariaLost.SKELETON_SPRITE_NB_ROW
										, Parameters_MariaLost.SKELETON_SPRITE_NB_COLUMN				
										, 196
										, 201);
		Animation animationAttackRight = new Animation(Parameters_MariaLost.FRAME_ANIMATION_DURATION, spriteAttackRight);
		meleeRight = new MeleeAttack(Parameters_MariaLost.SKELETON_ATTACK_DIMENSION
									, Direction.RIGHT
									, Parameters_MariaLost.SKELETON_ATTACK_DAMAGE
									, animationAttackRight
									, Parameters_MariaLost.SKELETON_ATTACK_DURATION);

        ////	Death	////
		
		List<Image> deathSprite = SpriteSheetLoader.load(Parameters_MariaLost.SKELETON
									, Parameters_MariaLost.SKELETON_SPRITE_NB_ROW
									, Parameters_MariaLost.SKELETON_SPRITE_NB_COLUMN				
									, 261
									, 266);
		List<Image> lastSprite=SpriteSheetLoader.load(Parameters_MariaLost.SKELETON
								, Parameters_MariaLost.SKELETON_SPRITE_NB_ROW
								, Parameters_MariaLost.SKELETON_SPRITE_NB_COLUMN				
								, 266
								, 266);
		
		deathSprite.addAll(lastSprite);
		deathSprite.addAll(lastSprite);
		deathSprite.addAll(lastSprite);

		death = new Animation(Parameters_MariaLost.FRAME_ANIMATION_DURATION, deathSprite);

		
		actualMovement = goDown;
		actualAttack = meleeDown;

		death.setAutoReplay(false);
	}
}