package mariaLost.items.model;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import mariaLost.gamePlay.tools.DebitOnlyMonnayeur;
import mariaLost.gamePlay.tools.Direction;
import mariaLost.gamePlay.tools.Timer;
import mariaLost.items.model.animation.Animation;
import mariaLost.parameters.Parameters_MariaLost;

public abstract class AbstractEnemy extends AbstractMobileItem {
	
	protected int lifePoint;
	protected int agroRadius;
	protected int attackRange;
	protected int damageContact;
	protected int damageDealt=0;
	protected Movement actualMovement;
	protected MeleeAttack actualAttack;
	protected Movement goUp;
	protected Movement goDown;
	protected Movement goLeft;
	protected Movement goRight;
	protected MeleeAttack meleeUp;
	protected MeleeAttack meleeDown;
	protected MeleeAttack meleeLeft;
	protected MeleeAttack meleeRight;
	protected Animation death;
    boolean isAgro = false;
    private Timer recoveryTimer=new Timer(Parameters_MariaLost.DAMAGE_RECOVERY_TIME);
    private boolean clignotement=true;
    

    public AbstractEnemy(double x, double y,double speedLimit) {
        super(x, y, Parameters_MariaLost.MOVABLE_ITEM_WIDTH, Parameters_MariaLost.MOVABLE_ITEM_HEIGHT, new DebitOnlyMonnayeur(0), speedLimit);
    }

	public int getLifePoint() {
		return lifePoint;
	}
	
	public void setLifePoint(int lifePoint) {
		this.lifePoint = lifePoint;
	}
	
	public int getAgroRadius() {
		return agroRadius;
	}
	
	public int getDamageContact(){
		return damageContact;
	}
	
	public boolean isAgro(){
		return isAgro;
	}
	
	public void agro(){
		isAgro=true;
	}

		
	@Override
	public Image getImage() {
		if(lifePoint==0)
			return death.getImage();
		if(!recoveryTimer.isOver()){
			if(clignotement){
				clignotement=false;
				return new Image(Parameters_MariaLost.TRANSPARANT_IMAGE);
			}else{
				clignotement=true;
			}
		}
		return actualAttack.isRunning()?actualAttack.getImage():actualMovement.getImage();
	}
	
	@Override
	public boolean isFinished(){
		return death.isOver()&&lifePoint==0;
	}
	
	public boolean hasDealtDamage(){
		return damageDealt!=0;
	}
	
	public int getDamage(){
		int damage=damageDealt;
		damageDealt=0;
		return damage;
	}
		
	public void takeDamage(int damage){
		if(damage<0){
			throw new IllegalArgumentException("Les dégats infligés ne peuvent être négatif");
		}
		if(recoveryTimer.isOver()){
			recoveryTimer.start();
			if(lifePoint-damage>0){
				System.out.println("damage taken="+damage);
				lifePoint-=damage;
			}else{
				lifePoint=0;
				death.play();
				this.setSpeed(Direction.ANY.getDirection());
			}
		}
	}
	
	
	//return true if the player is in physical contact with an enemy
	public boolean contact(AbstractMobileItem player){
		Rectangle2D rect= new Rectangle2D(player.getPosition().getX()-1
										,player.getPosition().getY()-1
										,player.getBounds().getWidth()+2
										,player.getBounds().getHeight()+2);
		return rect.intersects(this.getBounds());
	}
	
	protected void checkDamageContact(Player player){
		if(contact(player))
			damageDealt=damageContact;
	}
	
	protected Point2D getAttackStartingPoint(Direction direction){
		if(direction.equals(Direction.RIGHT)){
			return new Point2D(this.getPosition().getX()+this.getBounds().getWidth()
					,this.getPosition().getY()+(this.getBounds().getHeight()/2));
		}else if(direction.equals(Direction.LEFT)){
			return new Point2D(this.getPosition().getX()
					,this.getPosition().getY()+(this.getBounds().getHeight()/2));
		}else if(direction.equals(Direction.DOWN)){
			return new Point2D(this.getPosition().getX()+(this.getBounds().getWidth()/2)
					,this.getPosition().getY()+this.getBounds().getHeight());
		}else{
			return new Point2D(this.getPosition().getX()+(this.getBounds().getWidth()/2)
					,this.getPosition().getY());
		}
	}
	
	protected boolean isInAttackRange(Rectangle2D playerBound){
		Rectangle2D leftSide=new Rectangle2D(
				this.getBounds().getMinX()-attackRange
				,this.getBounds().getMinY()
				,this.getBounds().getWidth()
				,this.getBounds().getHeight());
		
		Rectangle2D upperSide=new Rectangle2D(
				this.getBounds().getMinX()
				,this.getBounds().getMinY()-attackRange
				,this.getBounds().getWidth()
				,this.getBounds().getHeight());
		
		Rectangle2D rightSide=new Rectangle2D(
				this.getBounds().getMinX()+attackRange
				,this.getBounds().getMinY()
				,this.getBounds().getWidth()
				,this.getBounds().getHeight());
		Rectangle2D rightLower=new Rectangle2D(
				this.getBounds().getMinX()
				,this.getBounds().getMinY()+attackRange
				,this.getBounds().getWidth()
				,this.getBounds().getHeight());
		
		return playerBound.intersects(leftSide)
				||playerBound.intersects(upperSide)
				||playerBound.intersects(rightSide)
				||playerBound.intersects(rightLower);
	}
	
	protected boolean isInPlayerAxis(Player player){
		Rectangle2D	playerAxisRect;
		if(isLeft(player)){
			playerAxisRect=new Rectangle2D(
					player.getBounds().getMinX()
					,player.getBounds().getMinY()
					,this.getBounds().getMaxX()-player.getBounds().getMinX()
					,player.getBounds().getHeight());
		}else if(isRight(player)){
			playerAxisRect=new Rectangle2D(
					player.getBounds().getMinX()
					,player.getBounds().getMinY()
					,player.getBounds().getMinX()-this.getBounds().getMinX()
					,player.getBounds().getHeight());
		}else if(isDown(player)){
			playerAxisRect=new Rectangle2D(
					player.getBounds().getMinX()
					,this.getBounds().getMinY()
					,player.getBounds().getWidth()
					,player.getBounds().getMaxY()-this.getBounds().getMinY());
		}else{
			playerAxisRect=new Rectangle2D(
					player.getBounds().getMinX()
					,player.getBounds().getMinY()
					,player.getBounds().getWidth()
					,this.getBounds().getMaxY()-player.getBounds().getMinY());
		}
		
		return playerAxisRect.intersects(player.getBounds());
	}
	
	public void behave(Player player){	
		if(lifePoint!=0){
			if(!actualAttack.isRunning()){
				//on a rien
				if(!actualMovement.isRunning()){
					if(isInAttackRange(player.getBounds())&&aligned(this,player)){
						attackChoosing(player);
					}else{
						movementChoosing(player);
					}
				//movement is running	
				}else{
					alignToPlayer(player);
				}
				checkDamageContact(player);
			}else{
				if(actualAttack.hasHit(player.getBounds())){
					this.damageDealt=actualAttack.getDamage();
				}
			}
		}
	}
	
	public void attackChoosing(Player player){
		if(isRight(player)){
			actualAttack=meleeRight;
			actualAttack.start(getAttackStartingPoint(Direction.RIGHT),Direction.RIGHT);
		}else if(isLeft(player)){
			actualAttack=meleeLeft;
			actualAttack.start(getAttackStartingPoint(Direction.LEFT),Direction.LEFT);
		}else if(isUp(player)){
			actualAttack=meleeUp;
			actualAttack.start(getAttackStartingPoint(Direction.UP),Direction.UP);
		}else{
			actualAttack=meleeDown;
			actualAttack.start(getAttackStartingPoint(Direction.DOWN),Direction.DOWN);
		}
		this.setSpeed(Direction.ANY.getDirection());
	}
	
	public void movementChoosing(Player player){
		if(isRight(player)){
			actualMovement=goRight;
		}else if(isLeft(player)){
			actualMovement=goLeft;
		}else if(isUp(player)){
			actualMovement=goUp;
		}else{
			actualMovement=goDown;
		}
		
		actualMovement.start();
		this.setSpeed(actualMovement.getSpeed());
	}
	
	public void alignToPlayer(Player player){
		if(isInPlayerAxis(player)&&isInAttackRange(player.getBounds())){					
			if(aligned(this,player)){
				actualMovement.stop();
			}else{
				this.setSpeed(speedToAlign(player));
			}
		}
	}

}
	
	

