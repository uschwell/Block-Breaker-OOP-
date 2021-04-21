import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;


/**
 * this class animates a High Score list.
 *
 * @author Uriel Schwell
 * @version 03.06.2018
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable scores;
    private String endKey;
    private KeyboardSensor sensor;

    /**
     * HighScoresAnimation -constructor.
     *
     * @param scores - the HighScores.
     * @param endKey -the key to end.
     * @param sensor -our keyboard sensor.
     *
     */
    public HighScoresAnimation(HighScoresTable scores, String endKey, KeyboardSensor sensor) {

        this.scores = scores;
        this.endKey = endKey;
        this.sensor = sensor;
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.LIGHT_GRAY);
        d.fillRectangle(0, 30, 800, 600);
        int x1 = 200;
        int x2 = 375;
        int y1 = 200;
        int y2 = 225;
        for (int i = 0; i < scores.size(); i++) {
            ScoreInfo current = scores.getList().get(i);
            if (i % 2 == 0) {
                d.setColor(Color.GREEN);
                d.fillRectangle(x1, y1 + i * 50, 500, 50);
                d.setColor(Color.BLACK);
                d.drawText(x2, y2 + (i * 50), current.getName() + ": " + current.getScore(), 25);
            } else {
                d.setColor(Color.YELLOW);
                d.fillRectangle(x1, y1 + i * 50, 500, 50);
                d.setColor(Color.RED);
                d.drawText(x2, y2 + (i * 50), current.getName() + ": " + current.getScore(), 25);

            }
        }
        d.setColor(Color.BLACK);
        d.drawText(250, 100, "High Scores", 50);

    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
