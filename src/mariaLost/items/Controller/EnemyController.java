package mariaLost.items.Controller;

import mariaLost.items.model.AbstractEnemy;
import mariaLost.items.model.AbstractItem;
import mariaLost.items.model.AbstractMobileItem;
import mariaLost.items.model.Player;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class EnemyController {

    //Extract the player and the enemy from the item collection
    public static void handleEnemies(Collection<? extends AbstractItem> items) {
        AbstractMobileItem player = null;
        Collection<AbstractMobileItem> enemyList = new LinkedList<AbstractMobileItem>();
        Iterator<AbstractItem> iterator = (Iterator<AbstractItem>) items.iterator();
        while (iterator.hasNext()) {
            AbstractItem currentItem = iterator.next();
            if (currentItem instanceof Player) {
                player = (AbstractMobileItem) currentItem;
            }
            if (currentItem instanceof AbstractEnemy) {
                enemyList.add((AbstractMobileItem) currentItem);
            }
        }
        directEnemies(player, enemyList);
    }


    //Set the direction and the behavior of the enemies
    public static void directEnemies(AbstractMobileItem itemPlayer, Collection<? extends AbstractItem> enemyList) {
        Player player = (Player) itemPlayer;
        Iterator<AbstractEnemy> iterator = (Iterator<AbstractEnemy>) enemyList.iterator();
        while (iterator.hasNext()) {
            AbstractEnemy currentEnemy = iterator.next();
            if (currentEnemy.isAgro()) {
                currentEnemy.behave(player);
                // damage checking
                if (currentEnemy.hasDealtDamage()) {
                    player.takeDamage(currentEnemy.getDamage());
                }
            } else {
                //agro checking
                if (agroRange(player, currentEnemy)) {
                    currentEnemy.agro();
                }
            }
        }
    }


    //return true if the player is in the agro range of the enemy
    public static boolean agroRange(Player player, AbstractEnemy enemy) {
        return player.center().distance(enemy.center()) < enemy.getAgroRadius();
    }


}
