
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * PauseScreen - function creates our pause screen.
 *
 * @author Uriel Schwell
 * @version 21.04.2018
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * PauseScreen - constructor.
     *
     * @param k - keyboard input.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }


    /**
     * doOneFrame - draws one screen instance.
     *
     * @param d - our drawing surface.
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    /**
     * shouldStop - if we should stop or not.
     *
     * @return -stop condition.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
