import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * this class defines a stopping point for our
 * currently running animation.
 *
 * @author Uriel Schwell
 * @version 03.06.2018
 */
public class KeyPressStoppableAnimation implements Animation {

    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stopCondition;
    private boolean isAlreadyPressed;


    /**
     * KeyPressStoppableAnimation - constructor.
     *
     * @param sensor -sensor.
     * @param key -keystroke.
     * @param animation -animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stopCondition = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (sensor.isPressed(key) && (!isAlreadyPressed)) {
            this.stopCondition = true;
        } else {
            animation.doOneFrame(d);
            //this prevents us counting the same key press twice
            if (!sensor.isPressed(key)) {
                this.isAlreadyPressed = false;
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return stopCondition;
    }
}
