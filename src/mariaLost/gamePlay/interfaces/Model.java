package mariaLost.gamePlay.interfaces;

import mariaLost.gamePlay.tools.Direction;

/**
 * Created by crede on 06/02/2017.
 */
public interface Model extends Followable {

    void setDirectionPlayer(Direction direction);

    void start();

    void stop();
}
