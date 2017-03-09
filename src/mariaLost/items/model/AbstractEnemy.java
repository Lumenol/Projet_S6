package mariaLost.items.model;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import mariaLost.gamePlay.tools.Direction;
import mariaLost.gamePlay.tools.Monnayeur;
import mariaLost.items.interfaces.Item;
import mariaLost.parameters.Parameters_MariaLost;

public abstract class AbstractEnemy extends AbstractMobileItem {
	
	
	protected int lifePoint;
	protected int agroRadius;
	protected int attackRange;
	boolean isAgro=false;
	private double contactTime;
	protected int damageContact;
	protected int damageDealt=0;
	Movement actualMovement;
	Attack actualAttack;



	protected Image image;
	
	public AbstractEnemy(double x, double y,double speedLimit) {
		super(x, y,Parameters_MariaLost.MOVABLE_ITEM_WIDTH, Parameters_MariaLost.MOVABLE_ITEM_HEIGHT,new Monnayeur(0),speedLimit);
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
	public void noticeContact(){
		if(contactTime==0){
			contactTime=System.currentTimeMillis();
		}
	}
	
	public double getContactDuration() {
		if(contactTime==0){
			return 0;
		}
		return System.currentTimeMillis()-contactTime;
	}
	public void resetContactTime(){
		contactTime=0;
	}
	
	@Override
	public Image getImage() {
		return ((actualAttack.isRunning())?actualAttack.getImage():actualMovement.getImage());
	}	
	
	
	public boolean hasDealtDamage(){
		return damageDealt!=0;
	}
	public int getDamage(){
		int damage=damageDealt;
		damageDealt=0;
		return damage;
	}
	
	//return true if the player is in contact with the enemy for 1 seconde
	public boolean repeatedContact(AbstractMobileItem player){
		if(contact(player)){
			noticeContact();
			if(getContactDuration()>500){
				resetContactTime();
				return true;
			}
		}else{
			resetContactTime();
		}
		return false;
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
		if(repeatedContact(player)){
			damageDealt=damageContact;
		}			
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
		if(itemIsLeft(player)){
			playerAxisRect=new Rectangle2D(
					player.getBounds().getMinX()
					,player.getBounds().getMinY()
					,this.getBounds().getMaxX()-player.getBounds().getMinX()
					,player.getBounds().getHeight());
		}else if(itemIsRight(player)){
			playerAxisRect=new Rectangle2D(
					player.getBounds().getMinX()
					,player.getBounds().getMinY()
					,player.getBounds().getMinX()-this.getBounds().getMinX()
					,player.getBounds().getHeight());
		}else if(itemIsDown(player)){
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
	

	
	public abstract void behave(Player player);

	
	
	
}
