/**
 * Collidable -this interface will be used by all objects
 * that can be collided with and defines all the functions
 * that will be used in the event of collisions.
 *
 * @author Uriel Schwell
 * @version 15.04.2018
 */
public interface Collidable {

    /**
     * getCollisionRectangle - this function returns the shape of the
     * object we collided with (at the moment that can only be a
     * rectangle).
     *
     * @return -the shape of the object we coliided with
     */
    Rectangle getCollisionRectangle();

    /**
     * hit - this notifies our object that we collided with another object at
     * collisionPoint with a given velocity.
     *
     * @param hitter - ball doing the hitting.
     * @param collisionPoint  -the point of Collision
     * @param currentVelocity - the Balls' current velocity.
     * @return - the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     **/
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}

