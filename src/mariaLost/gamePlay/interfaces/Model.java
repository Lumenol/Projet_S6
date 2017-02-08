package mariaLost.gamePlay.interfaces;

import mariaLost.gamePlay.tools.Direction;

/**
 * Created by crede on 06/02/2017.
 */
public interface Model extends Followable {
    /**
     * Permet de changer la direction du personnage
     *
     * @param direction
     */
    void setDirectionPlayer(Direction direction);

    /**
     * Démarre la boucle du jeu
     */
    void start();

    /**
     * Arrete la boucle du jeu
     */
    void stop();
}
