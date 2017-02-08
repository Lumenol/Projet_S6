package mariaLost.items.interfaces;

/**
 * Created by crede on 31/01/2017.
 */
public interface ActionableItem extends Item {

    /**
     * Acction déclancher quand o percute l'Item
     *
     * @param o
     */
    void action(Item o);

}
