

/**
 * CollisionInfo - this class holds all the
 * information about an existing
 * or upcoming collision between
 * objects.
 *
 * @author Uriel Schwell
 * @version 16.04.2018
 */
public class CollisionInfo {
    // the point at which the collision occurs.
    private Point collisionPoint;

    // the collidable object involved in the collision.
    private Collidable collisionObject;

    /**
     * CollisionInfo - a constructor function. that will
     * learn our point of collision, and what
     * shape we will be colliding with.
     *
     * @param collisionPoint - the point our objects will collide at
     * @param shape          - the shape we will colliding with
     */
    public CollisionInfo(Point collisionPoint, Collidable shape) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = shape;
    }

    /**
     * getCollisionPoint - function that returns the Point
     * of collision.
     *
     * @return collisionPoint - the Point we collide with an Object.
     */
    public Point getCollisionPoint() {
        return this.collisionPoint;
    }

    /**
     * getCollisionObject - a function that returns the type of
     *      Object we have collided with.
     * @return collisionObject- the Object we collided with
     */
    public Collidable getCollisionObject() {
        return collisionObject;
    }
}
