import biuoop.DrawSurface;

import java.awt.Color;


/**
 * GameLevel - this class will define and draw the level
 * name (on top of the screen).
 *
 * @author Uriel Schwell
 * @version 17.04.2018
 */
public class LevelNameIndicator implements Sprite {

    private String levelName;

    /**
     * LevelNameIndicator - constructor.
     *
     * @param levelName -the name of our level
     */
    public LevelNameIndicator(String levelName) {
        this.levelName = levelName;
    }

    /**
     * addToGame - lets me add my indicator to a game.
     *
     * @param g - game we are joining.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    @Override
    public void drawOn(DrawSurface d) {
        String print = (levelName);
        d.setColor(Color.WHITE);
        d.fillRectangle(600, 0, 200, 20);
        d.setColor(Color.BLACK);
        d.drawText(600, 12, print, 15);
    }

    @Override
    public void timePassed() {

    }
}
