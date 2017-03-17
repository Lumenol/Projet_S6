package mariaLost.items.Controller;

import mariaLost.items.model.AbstractEnemy;
import mariaLost.items.model.AbstractItem;
import mariaLost.items.model.Player;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class EnemyController {
	
/**
 * Extract the player and the enemy from the AbstractItem collection
 * @param items
 */
    public static void handleEnemies(Collection<AbstractItem> items) {
        Player player = null;
        Collection<AbstractEnemy> enemyList = new LinkedList<AbstractEnemy>();
        Iterator<AbstractItem> iterator =  items.iterator();
        while (iterator.hasNext()) {
            AbstractItem currentItem = iterator.next();
            if (currentItem instanceof Player) {
                player = (Player) currentItem;
            }
            if (currentItem instanceof AbstractEnemy) {
                enemyList.add((AbstractEnemy) currentItem);
            }
        }
        directEnemies(player, enemyList);
    }

/**
 * Check if each enemy is agro, call their behave method if so, else check if they are in agro range.
 * Check if they have dealt damage.
 * @param player  
 * @param enemyList
 * 					A collection of all the enemies of the current level
 */
    public static void directEnemies(Player player, Collection<AbstractEnemy> enemyList) {

        Iterator<AbstractEnemy> iterator =enemyList.iterator();
        while (iterator.hasNext()) {
            AbstractEnemy currentEnemy = iterator.next();
            if (currentEnemy.isAgro()) {
                currentEnemy.behave(player);
                if (currentEnemy.hasDealtDamage()) {
                    player.takeDamage(currentEnemy.getDamageDealt());
                }
            } else {
                if (agroRange(player, currentEnemy)) {
                    currentEnemy.agro();
                }
            }
        }
    }


    /**
     * Check if the player is in the agro radius of an enemy
     * @param player
     * @param enemy
     * @return a boolean set to true if the player is in the agro radius of the enemy, false if not.
     */
    public static boolean agroRange(Player player, AbstractEnemy enemy) {
        return player.center().distance(enemy.center()) < enemy.getAgroRadius();
    }


}
