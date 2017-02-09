package mariaLost.player.view;

import javafx.scene.image.Image;
import javafx.util.Duration;
import javafx.util.Pair;

/**
 * Created by crede on 09/02/2017.
 */
public class Animation {

    private Pair<Duration, Image> frames[];
    private int courant = 0;
    private boolean isPlay = false;
    private double lastTime = 0;
    private double ratio = 1;

    public Animation(Duration duration, Image... images) {
        if (duration.toMillis() <= 0)
            throw new IllegalArgumentException("Une durée ne dois pas etre nulle");
        if (images.length == 0)
            throw new IllegalArgumentException("Il n'y a pas de frame");
        frames = new Pair[images.length];
        for (int i = 0; i < images.length; i++) {
            frames[i] = new Pair<>(duration, images[i]);
        }
    }

    public Animation(Pair<Duration, Image>... frames) {
        if (frames.length == 0)
            throw new IllegalArgumentException("Il n'y a pas de frame");
        for (int i = 0; i < frames.length; i++) {
            if (frames[i].getKey().toMillis() <= 0)
                throw new IllegalArgumentException("Une durée ne dois pas etre nulle");
        }
        this.frames = frames;
    }

    final public Image getImage() {
        if (isPlay) {
            double dTime = System.currentTimeMillis() - lastTime;
            double delay;
            while (dTime >= (delay = frames[courant].getKey().toMillis() / ratio)) {
                dTime -= delay;
                courant = (courant + 1) % frames.length;
            }
            lastTime = System.currentTimeMillis() - dTime;
        }
        return frames[courant].getValue();
    }

    final public void play() {
        if (!isPlay) {
            isPlay = true;
            lastTime = System.currentTimeMillis();
        }
    }

    final public void stop() {
        isPlay = false;
        courant = 0;
    }

    final public void setRatio(double ratio) {
        if (ratio > 0) {
            this.ratio = ratio;
        }
    }

}
