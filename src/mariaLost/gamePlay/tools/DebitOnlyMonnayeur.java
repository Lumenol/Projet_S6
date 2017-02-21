package mariaLost.gamePlay.tools;

/**
 * Created by crede on 21/02/2017.
 */
public class DebitOnlyMonnayeur extends Monnayeur {

    public DebitOnlyMonnayeur(double value) {
        super(value);
    }

    @Override
    public boolean add(double v) {
        return true;
    }
}
