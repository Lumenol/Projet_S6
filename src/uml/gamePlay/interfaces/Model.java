package uml.gamePlay.interfaces;

/**
 * Created by crede on 06/02/2017.
 */
public interface Model extends Followable {
    void setSpeedPlayer(double vx, double vy);

    void start();

    void stop();
}
