import biuoop.DrawSurface;

import java.awt.Image;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

/**
 * Alien - this class will define the individual Alien (enemy).
 *
 * @author Uriel Schwell
 * @version 21.06.2018
 */
public class Alien implements Sprite {
    private Point locationPoint;
    private Block hitBox;
    private Image enemy = null;
    private Velocity speed;
    private int points;

    /**
     * Constructor.
     *
     * @param startPoint -the starting point for each Alien.
     * @param speed      -movement speed.
     */
    public Alien(Point startPoint, Velocity speed) {
        this.points = 100;
        Rectangle box = new Rectangle(startPoint, 40, 30);
        try {
            this.enemy = ImageIO.read(new File("src/enemy.png"));
        } catch (IOException ex) {
            System.out.println("we failed to load the enemy image");
        }
        this.hitBox = new Block(box, this.enemy, 1, this.points);
        this.locationPoint = startPoint;
        this.speed = speed;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawImage((int) locationPoint.getX(), (int) locationPoint.getY(), this.enemy);
    }

    @Override
    public void timePassed() {
    }

    /**
     * Get the current Velocity.
     *
     * @return -the Velocity.
     */
    public Velocity getSpeed() {
        return this.speed;
    }

    /**
     * Increase the Velocity by the given percentage (only along the Horizontal axis).
     *
     * @param percentIncrease - the percentage to increase our spped by.
     */
    public void increaseAlienSpeed(int percentIncrease) {
        double dx = this.speed.getDx();
        double dy = this.speed.getDy();
        double increase = 1 + (percentIncrease / 10);
        //we increase the speed along the X axis only
        this.speed = new Velocity(dx * increase, dy);
    }

    /**
     * Move the Alien one step to the Horizontal (depending on our current speed).
     */
    public void moveOneStepX() {
        this.locationPoint = new Point(this.locationPoint.getX() + this.speed.getDx(), this.locationPoint.getY());
    }

    /**
     * Move the Alien one step to the Vertical (depending on the Current speed).
     */
    public void moveOneStepY() {
        this.locationPoint = new Point(this.locationPoint.getX(), this.locationPoint.getY() + this.speed.getDy());
    }
}
