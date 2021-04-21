import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MenuAnimation - this class will define a Menu Animation.
 *
 * @author Uriel Schwell
 * @version 11.06.2018
 */

/**
 * @param <T> - the type of Object we are dealing with.
 */
public class MenuAnimation<T> implements Menu<T> {
    private List<ObjectSelection<T>> selection;
    private List<T> tasks;
    private T status = null;
    private KeyboardSensor sensor;
    private List<String> keys;
    private List<String> message;
    private boolean isPressed = false;
    private Map<String, Menu<T>> map;
    private AnimationRunner runner;


    /**
     * MenuAnimation -constructor.
     *
     * @param sensor - sensor we are using.
     * @param runner - animation runner
     */
    public MenuAnimation(KeyboardSensor sensor, AnimationRunner runner) {
        this.sensor = sensor;
        this.selection = new ArrayList<ObjectSelection<T>>();
        this.keys = new ArrayList<String>();
        this.message = new ArrayList<String>();
        this.tasks = new ArrayList<T>();
        this.map = new HashMap<>();
        this.runner = runner;
    }

    /**
     * @param key          - key.
     * @param printMessage -message.
     * @param returnVal    -the returned Object.
     */
    public void addSelection(String key, String printMessage, T returnVal) {
        this.selection.add(new ObjectSelection<T>(key, printMessage, returnVal));
        this.keys.add(key);
        this.message.add(printMessage);
        this.tasks.add(returnVal);
        this.tasks.add(returnVal);
    }

    @Override
    public T getStatus() {
        return this.status;
    }

    @Override
    public void addSubMenu(String key, String message1, Menu<T> subMenu) {

    }

    /**
     * does one frame.
     * @param d -surface
     * @param dt -frmerate.
     */
    public void doOneFrame(DrawSurface d, double dt) {

        d.drawText(50, 80, "Arkaniod", 50);

        int y = 150;
        for (int i = 0; i < this.keys.size(); i++) {
            d.drawText(80, y + 30 * i, "(" + this.keys.get(i) + ")", 30);
            d.drawText(180, y + 30 * i, this.message.get(i), 30);
            if (sensor.isPressed(this.keys.get(i))) {
                this.status = this.tasks.get(i);
                this.isPressed = true;
            }
        }

        for (String s : map.keySet()) {
            if (sensor.isPressed(s)) {
                runner.run(map.get(s));
                this.status = map.get(s).getStatus();
                isPressed = true;
            }
        }
    }

    @Override
    public void doOneFrame(DrawSurface d) {

    }

    @Override
    public boolean shouldStop() {
        return isPressed;
    }
}
