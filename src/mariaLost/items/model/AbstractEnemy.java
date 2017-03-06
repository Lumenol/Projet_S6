package mariaLost.items.model;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import mariaLost.gamePlay.tools.Monnayeur;
import mariaLost.parameters.Parameters_MariaLost;

public abstract class AbstractEnemy extends AbstractMobileItem {
	
	
	protected int lifePoint;
	protected int agroRadius;
	boolean isAgro=false;
	private double contactTime;
	protected int damageContact;
	protected int damageDealt=0;
	Movement actualMovement;
	



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
		return actualMovement.getImage();
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
	
	
	public abstract void behave(Player player);

	
	
	
}
