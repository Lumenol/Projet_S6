package mariaLost.items.interfaces;

/**
 * Created by crede on 31/01/2017.
 */
public interface ActionableItem extends Item {

    /**
     * The action this item do when it hit another item.
     *
     * @param o The item hit.
     */
    void action(Item o);

}
