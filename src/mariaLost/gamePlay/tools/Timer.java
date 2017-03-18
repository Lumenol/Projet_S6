package mariaLost.gamePlay.tools;

import javafx.util.Duration;

/**
 * This class is a timer.
 * It needs a duration when created. It can be started or ended.
 * A minimum duration can be set.
 * The class offers the service to know if its timer is over.
 * If the timer hasen's been started, the isOver method return True
 *
 * @author loic
 */
public class Timer {

    private Duration duration;
    private double startTime = 0;
    private boolean running = false;
    private Duration minimum;

    public Timer(Duration duration) {
        this.duration = duration;
        minimum = duration;
    }

	/**
     * To know if the timer is over.
     * If the timer hasen't been started, it is considered over.
     *
     * @return a boolean
     */
    public boolean isOver() {
        if (System.currentTimeMillis() - startTime < minimum.toMillis()) {
            return false;
        }
        if (System.currentTimeMillis() - startTime > duration.toMillis()) {
            running = false;
        }
        return !running;
    }


    public void start() {
        startTime = System.currentTimeMillis();
        running = true;
    }


    public void end() {
        startTime = 0;
        running = false;
    }


    public void mustLast(Duration duration) {
        minimum = duration;
    }
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((duration == null) ? 0 : duration.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Timer other = (Timer) obj;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		return true;
	}

}
