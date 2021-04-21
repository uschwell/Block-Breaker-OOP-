import biuoop.DrawSurface;

import java.awt.Color;

/**
 * LivesIndicator - this class will track and draw the lives
 * we have remaining.
 *
 * @author Uriel Schwell
 * @version 22.04.2018
 */
public class LivesIndicator implements Sprite {
    private Counter lifeCounter;

    /**
     * LivesIndicator -constructor.
     * @param lives - lives we currently have.
     */
    public LivesIndicator(Counter lives) {
        this.lifeCounter = lives;
    }

    /**
     * addToGame - lets me add my indicator to a game.
     * @param g -game we are joining.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    @Override
    public void drawOn(DrawSurface d) {
        String print = ("Lives: " + lifeCounter.getValue());
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, 200, 20);
        d.setColor(Color.BLACK);
        d.drawText(100, 12, print, 15);
    }

    @Override
    public void timePassed() {

    }
}
