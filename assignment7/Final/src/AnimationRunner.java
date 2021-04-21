/**
 * AnimationRunner - function implements our Animation
 * loop.
 *
 * @author Uriel Schwell
 * @version 21.04.2018
 */

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * AnimationRunner - this class will run an animation instance.
 *
 * @author Uriel Schwell
 * @version 20.04.2018
 */
public class AnimationRunner implements Animation {

    private GUI gui;
    private int framesPerSecond;

    /**
     * AnimationRunner - constructor.
     *
     * @param gui -the GUI we are working in.
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.framesPerSecond = 60;
    }

    /**
     * @param animation - our animation.
     */
    public void run(Animation animation) {
        Sleeper sleeper = new Sleeper();
        long millisecondsPerFrame = (long) (1000 / this.framesPerSecond);
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            this.gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    @Override
    public void doOneFrame(DrawSurface d) {
    }

    @Override
    public boolean shouldStop() {
        return false;
    }

    /**
     * @return -our GUI.
     */
    public GUI getAnimationGUI() {
        return this.gui;
    }
}
