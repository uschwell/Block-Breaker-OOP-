import biuoop.DrawSurface;

import java.awt.Image;
import java.awt.Color;
import java.util.ArrayList;

/**
 * Block - this class will define all Blocks
 * that will exist in the GameLevel Environment
 * and all the functions that interact with
 * said blocks.
 *
 * @author Uriel Schwell
 * @version 05.04.2018
 */
public class Block implements Collidable, Sprite, HitNotifier {

    //members
    private Rectangle rect;
    private Color colour = Color.BLUE;
    private int hits;
    private ArrayList<HitListener> hitListeners;
    private int points;
    private Image image = null;
    private Velocity speed = null;
    private int leftLimit = 0;

    /**
     * Block - this function builds/defines a Block.
     *
     * @param rect   - the shape of the block
     * @param cl     -the color of the block
     * @param hits   - the amount of hits the block has left
     * @param points - the points we gain for hitting this block.
     */
    public Block(Rectangle rect, Color cl, int hits, int points) {
        this.rect = rect;
        this.colour = cl;
        this.hits = hits;
        this.hitListeners = new ArrayList<HitListener>();
        this.points = points;
    }

    /**
     * Block - this function builds/defines a Block with an Image.
     *
     * @param rect   - the shape of the block
     * @param image  -the image of the block
     * @param hits   - the amount of hits the block has left
     * @param points - the points we gain for hitting this block.
     */
    public Block(Rectangle rect, Image image, int hits, int points) {
        this.rect = rect;
        this.image = image;
        this.hits = hits;
        this.hitListeners = new ArrayList<HitListener>();
        this.points = points;
    }


    /**
     * getCollisionRectangle - function returns the rectangle
     * we collided with.
     *
     * @return this.rect - the rectangle we collided with
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * getBlockColor - this function returns the color of our
     * current block.
     *
     * @return this.color - the color of our block.
     */
    public Color getBlockColor() {
        return this.colour;
    }


    /**
     * drawOn - function receives a rectangular block and draws
     * it (in the specified color) onto our GUI.
     *
     * @param object - the (rectangular) object we wish to draw
     */
    public void drawOn(DrawSurface object) {
        int x = (int) this.rect.getUpperLeft().getX();
        int y = (int) this.rect.getUpperLeft().getY();
        int placementX = (int) (x + (this.rect.getWidth() / 2));
        int placementY = (int) (y + (this.rect.getHeight() / 2));
        int width = (int) this.rect.getWidth();
        int height = (int) this.rect.getHeight();
        if (this.image == null) {
            object.setColor(this.colour);
            object.fillRectangle(x, y, width, height);
        } else {
            object.drawImage(x, y, this.image);
        }
    }


    /**
     * addToGame - this function adds a game item (a block)
     * to both the collidable and the sprite lists.
     *
     * @param g - the game item we wanted to add
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * Sets a Velocity for the block.
     *
     * @param speed1 -the new Block speed.
     */
    public void setBlockSpeed(Velocity speed1) {
        this.speed = speed1;
    }

    /**
     * gets the current Block velocity.
     *
     * @return -the Block speed.
     */
    public Velocity getBlockSpeed() {
        return this.speed;
    }


    /**
     * timePassed - this function will determine how
     * much time has passed.
     */
    public void timePassed() {
        if (this.speed != null) {
            moveOneStepX();
        }
    }

    /**
     * move one step on the Horizontal Axis.
     */
    public void moveOneStepX() {
        double x1 = this.rect.getUpperLeft().getX();
        double y1 = this.rect.getUpperLeft().getY();
        this.rect.setUpperLeft(new Point(x1 + this.speed.getDx(), y1));
    }


    /**
     * move One step on the Vertical axis.
     */
    public void moveOneStepY() {
        double x2 = this.rect.getUpperLeft().getX();
        double y2 = this.rect.getUpperLeft().getY();
        this.rect.setUpperLeft(new Point(x2, y2 + this.speed.getDy()));
    }


    /**
     * remove Block from the game.
     *
     * @param game -game we are rmoving from.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);

    }

    /**
     * addHitListener - adds a listener.
     *
     * @param hl -listener to add.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);

    }

    /**
     * removeHitListener - removes the given Listener.
     *
     * @param hl - listener to remove.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * notifyHit - function informs all Listeners that our Block has just taken a hit.
     *
     * @param hitter -the object (ball)doing the hitting.
     */
    public void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        ArrayList<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * hit -this function informs us as to the changes in our balls' velocity upon hitting
     * a Block.
     *
     * @param hitter          - the ball hitting our Block.
     * @param collisionPoint  -the point of Collision
     * @param currentVelocity - the Balls' current velocity.
     * @return newV -the new velocity (direction having been changed).
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double xPoint = this.rect.getUpperLeft().getX();
        double yPoint = this.rect.getUpperLeft().getY();
        double width = this.rect.getWidth();
        double height = this.rect.getHeight();
        Velocity newV = hitter.getVelocity();
        //if we hit along a horizontal line
        if ((collisionPoint.getX() > xPoint) && (collisionPoint.getX() < (xPoint + this.rect.getWidth()))) {
            newV = new Velocity(hitter.getVelocity().getDx(), (-1) * hitter.getVelocity().getDy());
        }
        //if we hit along a vertical line
        if ((collisionPoint.getY() > yPoint) && (collisionPoint.getY() < (yPoint + this.rect.getHeight()))) {
            newV = new Velocity((-1) * hitter.getVelocity().getDx(), hitter.getVelocity().getDy());
            //if we hit EXACTLY on a corner
        } else if (collisionPoint.equals(this.rect.getUpperLeft())
                || ((collisionPoint.getX() == xPoint + width) && (collisionPoint.getY() == yPoint))
                || ((collisionPoint.getX() == xPoint) && (collisionPoint.getY() == (yPoint + height)))
                || ((collisionPoint.getX() == xPoint + width) && (collisionPoint.getY() == yPoint + height))
                ) {
            newV = new Velocity((-1) * hitter.getVelocity().getDx(), (-1) * hitter.getVelocity().getDy());
        }

        this.notifyHit(hitter);
        return newV;
    }


    /**
     * @return current hitpoints.
     */
    public int getHitPoints() {
        return this.hits;
    }

    /**
     * @param newHitPoints -new amount of hitpoints.
     */
    public void setHitPoints(int newHitPoints) {
        this.hits = newHitPoints;

    }

    /**
     * Returns how many points you get for hitting this block.
     *
     * @return -the points we gain for hitting this block.
     */
    public int scoreWorth() {
        return this.points;
    }

}
