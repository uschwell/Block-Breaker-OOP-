import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;

/**
 * this class defines a Ball object
 * and all  functions related to it.
 *
 * @author Uriel Schwell
 * @version 03.04.2018
 */
public class Ball implements Sprite, HitNotifier {
    //members of our object
    private Point location;
    private double size;
    private Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvir;
    private ArrayList<HitListener> hitListeners;

    /**
     * Ball - our constructor.
     *
     * @param center - this.location
     * @param r      - this.size
     * @param color  - this.color
     * @param gE     -this.GameEnvironment
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment gE) {
        this.location = center;
        this.size = r;
        this.color = color;
        this.velocity = new Velocity(1, 1);
        this.gameEnvir = gE;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * setVelocity - a setter-function which receives a
     * current Velocity and changees it to the
     * setVelocity received for it.
     *
     * @param v - a new velocity
     */
    public void setVelocity(Velocity v) {
        this.setVelocity(v.getDx(), v.getDy());
    }

    /**
     * setVelocity - a setter-function which receives two doubles.
     * then changes the velocity to the new one,
     * as defined by those doubles
     *
     * @param dx - the new dx value for the current velocity
     * @param dy - the new dy value for the current velocity
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * setLocation - a setter-function, which changes the value
     * of our current location Point.
     *
     * @param newLocation - Point, the new location
     */
    public void setLocation(Point newLocation) {
        this.setLocation(newLocation.getX(), newLocation.getY());
    }

    /**
     * setLocation - a setter-function, which changes the value
     * of our current location Point.
     *
     * @param x - the new x location.
     * @param y - the new y location.
     */
    public void setLocation(double x, double y) {
        this.location = new Point(x, y);
    }

    /**
     * getX - a getter-function, returns the x value of the ball's location.
     *
     * @return (int)this.location.getX() - this location's x's value.
     */
    public int getX() {
        return (int) this.location.getX();
    }

    /**
     * getY - a getter-function, returns the y value of the ball's location.
     *
     * @return (int)this.location.getY() - this location's y's value.
     */
    public int getY() {
        return (int) this.location.getY();
    }


    /**
     * getSize - a getter-function, returns the radius of the ball.
     *
     * @return (int)this.size - the radius of the ball.
     */
    public int getSize() {
        return (int) this.size;
    }

    /**
     * getVelocity - a getter-function, returns the velocity of the ball.
     *
     * @return this.velocity - the current velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * getColor - a getter-function, returns the ball's color.
     *
     * @return this.color - the color of this ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * drawOn - a function that draws the ball onto
     * a given surface.
     *
     * @param surface - a surface to draw the ball onto.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * timePassed - this function determines
     * how much time has passed.
     */
    public void timePassed() {
        this.moveOneStep();

    }

    /**
     * moveOneStep - a function that runs checks on the ball's location,
     * fixes them into  a correct place and then moves them
     * step by using methods we defined in our Velocity class.
     */
    public void moveOneStep() {
        Point movePoint = new Point(this.getX() + this.getVelocity().getDx(), this.getY() + this.getVelocity().getDy());
        Line trajectory = new Line(this.location, movePoint);
        CollisionInfo ci = this.gameEnvir.getClosestCollision(trajectory);
        int linearDistance = (int) Math.sqrt(velocity.getDx() * velocity.getDx() + velocity.getDy() * velocity.getDy());
        //we check if we have a collision point
        if (ci != null) {
            Point collisionPoint = new Point(ci.getCollisionPoint().getX(), ci.getCollisionPoint().getY());
            //we check if we have reached (or almost reached) our collision point
            if ((ci.getCollisionPoint() == this.location)
                    || (ci.getCollisionPoint().distance(this.location) < linearDistance)) {
                if (this.getVelocity().getDx() != 0 && this.getVelocity().getDy() != 0) {
                    this.setLocation(new Point(collisionPoint.getX()
                            - (this.velocity.getDx() / Math.abs(this.velocity.getDx())),
                            collisionPoint.getY() - (this.velocity.getDy() / Math.abs(this.velocity.getDy()))));
                } else if (this.getVelocity().getDx() == 0 && this.getVelocity().getDy() != 0) {
                    this.setLocation(new Point(collisionPoint.getX(), collisionPoint.getY()
                            - (this.velocity.getDy() / Math.abs(this.velocity.getDy()))));
                } else if (this.getVelocity().getDx() != 0 && this.getVelocity().getDy() == 0) {
                    this.setLocation(new Point(collisionPoint.getX()
                            - (this.velocity.getDx() / Math.abs(this.velocity.getDx())), collisionPoint.getY()));
                } else if (this.getVelocity().getDx() == 0 && this.getVelocity().getDy() == 0) {
                    this.setLocation(new Point(collisionPoint.getX(), collisionPoint.getY()));
                }

                this.setVelocity(ci.getCollisionObject().hit(this, ci.getCollisionPoint(), this.velocity));
            }
            //otherwise we move one step
            this.setLocation(this.getX() + this.velocity.getDx(), this.getY() + this.velocity.getDy());
            //if we do not yet have a collision point
        } else {
            this.setLocation(this.getX() + this.velocity.getDx(), this.getY() + this.velocity.getDy());
        }
    }


    /**
     * setEnvironment - this function sets the game environment our ball
     * will operate within.
     *
     * @param gameEn - the game environment we need
     */
    public void setEnvironment(GameEnvironment gameEn) {
        this.gameEnvir = gameEn;
    }

    /**
     * addToGame - this function adds a game item (a block)
     * to both the collidable and the sprite lists.
     *
     * @param g - the game item we wanted to add
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * removeFromGame - removes this ball from the game.
     *
     * @param game -game we are removing from.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }


    /**
     * addHitListener - adds a Listener to our ball.
     *
     * @param hl -the listener to add.
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
}
