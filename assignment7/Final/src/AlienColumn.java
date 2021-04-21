import biuoop.DrawSurface;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * AlienColumn - this class will define a Column of Aliens.
 *
 * @author Uriel Schwell
 * @version 21.06.2018
 */
public class AlienColumn implements HitListener, Sprite {
    private ArrayList<Block> col;
    private Image enemy = null;
    private Velocity speed;


    /**
     * Constructor.
     *
     * @param size       -the amount of Aliens this Column contains.
     * @param startPoint -the startPoint of the first Alien.
     * @param speed      -the Velocity of each Alien in the Column.
     */
    public AlienColumn(int size, Point startPoint, Velocity speed) {

        this.speed = speed;
        this.col = new ArrayList<Block>();
        double x = startPoint.getX();
        double y = startPoint.getY();
        try {
            this.enemy = ImageIO.read(new File("src/enemy.png"));
        } catch (IOException ex) {
            System.out.println("we failed to load the enemy image");
        }
        for (int i = 0; i < size; i++) {
            y = y + 40;
            Point tempPoint = new Point(x, y);
            Rectangle tempRec = new Rectangle(tempPoint, 40, 30);
            if (this.enemy != null) {
                Block block = new Block(tempRec, this.enemy, 1, 100);
                block.setBlockSpeed(speed);
                this.col.add(block);
            }
        }

    }

    /**
     * Move all the Aliens one step to the Horizontal (depending on our current speed).
     */
    public void moveOneStepX() {
        double rightMostX = this.col.get(col.size() - 1).getCollisionRectangle().getUpperLeft().getX();
        double leftMostX = this.col.get(0).getCollisionRectangle().getUpperLeft().getX();
        if ((leftMostX == 0) || (rightMostX == 750)) {
            moveOneStepY();
            increaseSpeed(10);
        } else {
            for (Block alien : this.col) {
                //if we hit the sides of the screen.
                Point currentLocation = alien.getCollisionRectangle().getUpperLeft();
                Point newPoint = new Point(currentLocation.getX() + this.speed.getDx(), currentLocation.getY());
                alien.getCollisionRectangle().setUpperLeft(newPoint);
            }
        }
    }

    /**
     * Move the Alien one step to the Vertical (depending on the Current speed).
     */
    public void moveOneStepY() {
        for (Block alien : this.col) {
            Point currentLocation = alien.getCollisionRectangle().getUpperLeft();
            Point newPoint = new Point(currentLocation.getX(), currentLocation.getY() + this.speed.getDy());
            alien.getCollisionRectangle().setUpperLeft(newPoint);
        }
    }

    /**
     * Return the Column of Aliens.
     * @return -the column of Alien Blocks.
     */
    public ArrayList<Block> getAlienColumn() {
        return this.col;
    }

    /**
     * Increase the speed of the entire Column.
     *
     * @param speedPercent -the percentage to increase by.
     */
    public void alterColunmSpeed(int speedPercent) {
        for (Block alien : this.col) {
            increaseSpeed(speedPercent);
        }
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.col.remove(beingHit);
    }

    /**
     * Increase the Velocity by the given percentage (only along the Horizontal axis).
     *
     * @param percentIncrease - the percentage to increase our spped by.
     */
    public void increaseSpeed(int percentIncrease) {
        double dx = this.speed.getDx();
        double dy = this.speed.getDy();
        double increase = 1 + (percentIncrease / 10);
        //we increase the speed along the X axis only
        this.speed = new Velocity(dx * increase, dy);
    }

    @Override
    public void drawOn(DrawSurface d) {

    }

    @Override
    public void timePassed() {
        moveOneStepX();
    }
}
