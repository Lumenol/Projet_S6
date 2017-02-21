package mariaLost.gamePlay.tools;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * Created by crede on 21/02/2017.
 */
public class Monnayeur {

    private SimpleDoubleProperty value;

    public Monnayeur(double value) {
        if (value < 0) throw new IllegalArgumentException("value est négatif");
        this.value = new SimpleDoubleProperty(value);
    }

    public final double getValue() {
        return value.get();
    }

    protected final void setValue(double value) {
        this.value.set(value);
    }

    /**
     * Transfert le maximum accepter par m
     *
     * @param m
     * @return
     */
    public final void Credit(Monnayeur m) {
        double mValue1 = m.getValue();
        m.add(getValue());
        sub(m.getValue() - mValue1);
    }


    public boolean add(double v) {
        if (v < 0) return false;
        setValue(getValue() + v);
        return true;
    }

    public final boolean sub(double v) {
        if (v < 0 || getValue() < v)
            return false;
        setValue(getValue() - v);
        return true;
    }

    public final ReadOnlyDoubleProperty valueProperty() {
        return value;
    }

}
