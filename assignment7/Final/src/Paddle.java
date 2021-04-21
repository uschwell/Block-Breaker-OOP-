import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * Paddle - this class will build the Paddle (i.e. the user)
 * it also includes all functions for moving
 * said paddle.
 *
 * @author Uriel Schwell
 * @version 17.04.2018
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rec;
    private java.awt.Color color;
    private GUI gui;
    private int speed;


    /**
     * paddle - this function builds our paddle.
     *
     * @param gui -the GUI we are working in.
     */
    public Paddle(GUI gui) {
        this.rec = new Rectangle(new Point(250, 589), 75, 3);
        this.rec = rec;
        this.color = Color.ORANGE;
        this.gui = gui;
        this.keyboard = gui.getKeyboardSensor();
        this.speed = 5;
    }

    /**
     paddle - this function builds our paddle (when we want a specific-NOT a generic
     * paddle).
     * @param gui -GUI.
     * @param startPoint starting Point.
     * @param width -width of the Paddle.
     * @param speed -movement speed.
     */
    public Paddle(GUI gui, Point startPoint, int width, int speed) {
        this.gui = gui;
        this.rec = new Rectangle(startPoint, width, 15);
        this.color = Color.ORANGE;
        this.keyboard = gui.getKeyboardSensor();
        this.speed = speed;
    }


    /**
     * moveLeft-moves our paddle to the left.
     */
    public void moveLeft() {
        this.rec.setUpperLeftLeft(this.speed);
    }

    /**
     * moveRight - moves our paddle to the right.
     */
    public void moveRight() {
        this.rec.setUpperLeftRight(this.speed);
    }

    /**
     * setLocation - this sets the paddle to the new location.
     *
     * @param newLocation - the new upperLeft point of our paddle.
     */
    public void setLocation(Point newLocation) {
        this.rec.setUpperLeft(newLocation);
    }

    /**
     * timePassed - a function we implemented from Sprite, will
     * permit movement of the paddle with pressing of keys.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.rec.setUpperLeftLeft(this.speed);
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.rec.setUpperLeftRight(this.speed);
        }
    }


    /**
     * drawOn - function receives a rectangular block (our paddle)
     * and draws it (in the specified color) onto our GUI.
     *
     * @param object - the surface we wish to draw upon
     */
    public void drawOn(DrawSurface object) {
        int x = (int) this.rec.getUpperLeft().getX();
        int y = (int) this.rec.getUpperLeft().getY();
        int width = (int) this.rec.getWidth();
        int height = (int) this.rec.getHeight();
        object.setColor(this.color);
        object.fillRectangle(x, y, width, height);
    }

    /**
     * getCollisionRectangle - this function gives us
     * our collision rectangle.
     *
     * @return this.rec - the Collision Rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }

    /**
     * hit -this function determines what happens when we Hit another object.
     *
     * @param collisionPoint  -the point of Collision
     * @param currentVelocity - the Balls' current velocity.
     * @param hitter -ball hitting.
     * @return the (changed) velocity after we impact on an object.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelocity = currentVelocity;
        //we divide our paddle into 5 regions
        double section = this.rec.getWidth() / 5;
        double region = (collisionPoint.getX() - this.rec.getUpperLeft().getX());
        double currentSpeed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));
        //region1
        if (region <= section) {
            newVelocity = Velocity.fromAngleAndSpeed(300, currentSpeed);
            //region2
        } else if ((region <= 2 * section)) {
            newVelocity = Velocity.fromAngleAndSpeed(330, currentSpeed);
            //region3
        } else if ((region <= 3 * section)) {
            newVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * (-1));
            //region4
        } else if ((region <= 4 * section)) {
            newVelocity = Velocity.fromAngleAndSpeed(30, currentSpeed);
            //region5
        } else if ((region <= 5 * section)) {
            newVelocity = Velocity.fromAngleAndSpeed(60, currentSpeed);
        }
        return newVelocity;
    }

    /**
     * addToGame - this function adds the Paddle into the
     * required list/interfaces.
     *
     * @param g - the game paddle is a part of.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
