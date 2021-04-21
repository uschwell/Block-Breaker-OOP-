/**
 * this class defines velocity and
 * all the functions related to it.
 *
 * @author Uriel Schwell
 * @version 03.04.2018
 */
public class Velocity {

    //members of object
    private double dx;
    private double dy;

    /**
     * Velocity - a constructor.
     *
     * @param dx - the value to out in this.dx
     * @param dy - the value to put in this.dy
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * fromAngleAndSpeed - a function which creates velocity,
     * along with angle and speed.
     * we do this by modify our velocity
     * into 'dx' and 'dy'. (therefore velocity
     * will also change position)
     *
     * @param angle - the angle, our direction of movement
     * @param speed - the force, the 'quickness' of movement active
     *              in this direction
     * @return velocity - our new velocity, as dx, and dy) values
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        //we find our dx and dy with sin and cos Math functions
        double dy = (-1) * Math.sin(Math.toRadians(angle));
        double dx = Math.cos(Math.toRadians(angle));
        //return the velocity as (dx,dy) factors
        return new Velocity((dx * speed), (dy * speed));
    }

    /**
     * getDx - a function that returns the Dx value of this class.
     *
     * @return this.dy - the Dx value of this object
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * getDy - a function that returns the dy value of this class.
     *
     * @return this.dy - the dy value of this object
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * applyToPoint - a function that takes a point with value of (x,y)
     * and returns a new point with value of (x+dx, y+dy).
     * (i.e after some movement)
     *
     * @param p - the original point we receive
     * @return moved Point - a new Point, after adding the additional (dx, dy) values
     */
    public Point applyToPoint(Point p) {
        Point moved = new Point(p.getX() + this.getDx(), p.getY() + this.dy);
        return moved;
    }

    /**
     * Flips the X component of a Blocks' movement.
     */
    public void flipX() {
        this.dx = (-1) * this.dx;

    }

}