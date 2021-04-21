import biuoop.DrawSurface;

/**
 * Sprite -this interface will be used by all objects
 * that can be drawn on the screen.
 *
 * @author Uriel Schwell
 * @version 15.04.2018
 */
public interface Sprite {

    /**
     * drawOn - this function will draw the sprite on the
     *          screen.
     * @param d - the drawing surface
     */
    void drawOn(DrawSurface d);

    /**
     * timePassed- this function will determine
     *          the time that has passed.
     */
    void timePassed();
}
