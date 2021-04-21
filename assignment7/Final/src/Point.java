/**
 * this class defines Point objects and all
 * the functions related to it.
 *
 * @author Uriel Schwell
 * @version 03.04.2018
 */
public class Point {

    //members of the object
    private double x;
    private double y;

    /**
     * Point - our constructor.
     *
     * @param x - the x's value of the new point
     * @param y - the y's value of the new point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * distance - returns the distance of this point to the received point
     * via the square root of (x^2+y^2).
     *
     * @param other - the other point we check against
     * @return length - the calculated distance between the 2 points
     **/
    public double distance(Point other) {
        double length = 0;
        double differenceX = Math.pow((this.getX() - other.getX()), 2);
        double differenceY = Math.pow((this.getY() - other.getY()), 2);
        //length between 2 points equals the square root of (x1-x2)^2 + (y1-y2)^2
        length = Math.sqrt((differenceX + differenceY));
        return length;
    }

    /**
     * equals - returns true if the points are equal, false otherwise.
     *
     * @param other - other point to check with
     * @return true/false - true if the distance equals 0 (same point), false otherwise
     **/
    public boolean equals(Point other) {
        boolean equals = false;
        if (this.distance(other) == 0) {
            equals = true;
        }
        return equals;
    }

    /**
     * getX - returns the X value of this point.
     *
     * @return the current value of X
     */
    public double getX() {
        return this.x;
    }

    /**
     * getY - returns the Y value of this point.
     *
     * @return the current value of Y
     */
    public double getY() {
        return this.y;
    }

}
