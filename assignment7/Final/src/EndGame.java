

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
/**
 * EndGame - this class will define the end game
 * animation.
 *
 * @author Uriel Schwell
 * @version 22.04.2018
 */
public class EndGame implements Animation {
    private Counter scoreCounter;
    private KeyboardSensor keyboardSensor;
    private boolean hasWon;
    private boolean stop = false;

    /**
     * EndScreen -constructor.
     *
     * @param scoreCounter   -our score.
     * @param keyboardSensor -key sensor.
     * @param hasWon         -if we won or lost.
     */
    public EndGame(Counter scoreCounter, KeyboardSensor keyboardSensor, boolean hasWon) {
        this.scoreCounter = scoreCounter;
        this.keyboardSensor = keyboardSensor;
        this.hasWon = hasWon;
    }

    /**
     * @return stop - stopping condition
     */
    public boolean shouldStop() {
        return stop;
    }

    /**
     * run a single frame.
     * @param d -drawsurface
     */
    public void doOneFrame(DrawSurface d) {
        //if we won
        if (hasWon) {
            d.setColor(Color.BLUE);
            d.fillRectangle(0, 30, 800, 600);

            int numRays = 50;
            int startX = 25;
            int endX = 775;

            d.setColor(Color.GREEN);
            for (int i = 1; i <= numRays; i++) {
                d.drawLine(400, 300, (endX - startX) / numRays * i, 250);
            }
            d.setColor(Color.YELLOW);
            d.drawText(200, 300, "You Win! Your score is " + scoreCounter.getValue(), 30);


            //if we lost
        } else {
            d.setColor(Color.BLACK);
            d.fillRectangle(0, 30, 800, 600);

            d.setColor(Color.RED);
            d.drawLine(30, 30, 770, 570);
            d.drawLine(770, 30, 30, 570);

            d.setColor(Color.WHITE);
            d.drawText(200, 300, "Game Over. Your Score is " + scoreCounter.getValue(), 30);


        }


        //we stay on this screen until "space" is pressed.
        if (keyboardSensor.isPressed("space")) {
            stop = true;
        }

    }
}
