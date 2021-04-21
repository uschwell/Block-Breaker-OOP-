
import biuoop.DrawSurface;

import java.awt.Color;
/**
 * CountdownAnimation - this class will define and help initialize
 * the animated countdown.
 *
 * @author Uriel Schwell
 * @version 21.04.2018
 */
public class CountdownAnimation implements Animation {

    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private double seconds;
    private long startTime;
    private boolean end = false;

    /**
     * CountdownAnimation - constructor.
     *
     * @param numOfSeconds -number of seconds we count.
     * @param countFrom -start counting from.
     * @param gameScreen -screen we are printing on.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.seconds = ((this.numOfSeconds / this.countFrom) * 1000);
        this.startTime = System.currentTimeMillis();
    }

    /**
     * doOneFrame - will print our game screen with a countdown
     * timer overlay.
     *
     * @param d -drawing surface
     */
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.CYAN);
        if (this.countFrom > 0) {
            d.drawText(350, 300, Integer.toString(+countFrom), 30);
        }
        if (System.currentTimeMillis() > this.startTime + this.seconds) {
            this.startTime = System.currentTimeMillis();
            this.countFrom--;
        }
        if (this.countFrom == 0) {
            this.seconds--;
            d.drawText(350, 300, "Go!", 30);
        }
        if (System.currentTimeMillis() > this.startTime + this.seconds) {
            this.startTime = System.currentTimeMillis();
            this.countFrom--;
        }
        if (this.countFrom == (-1)) {
            this.end = true;
        }
    }

    /**
     * shouldStop - if the animation should stop running.
     *
     * @return - end condidtion.
     */
    public boolean shouldStop() {
        return this.end;
    }
}
