import biuoop.DrawSurface;

import java.awt.Color;

/**
 * ScoreIndicator - this class will define draw the score
 * (on the top of our screen).
 *
 * @author Uriel Schwell
 * @version 22.04.2018
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * constructor.
     * @param scoreTracker -Counter.
     */
    public ScoreIndicator(Counter scoreTracker) {
        this.score = scoreTracker;
    }

    /**
     * add sprite to the game.
     * @param g -game to join.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    @Override
    public void drawOn(DrawSurface d) {
        String print = ("Score: " + score.getValue());
        d.setColor(Color.WHITE);
        d.fillRectangle(200, 0, 400, 20);
        d.setColor(Color.BLACK);
        d.drawText(350, 12, print, 15);
    }

    @Override
    public void timePassed() {
    }
}
