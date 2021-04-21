import biuoop.DrawSurface;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

/**
 * this class defines Line objects (from 2 Points)
 * and all the functions related to it.
 *
 * @author Uriel Schwell
 * @version 03.04.2018
 */
public class Line {
    //members of our object
    private Point start;
    private Point end;
    private static final double EPSILON = 0.0001;

    /**
     * Line - a constructor function
     * it defines a line between two Points
     * (from the smaller x to the larger X).
     *
     * @param start - the leftmost Point of the Line
     * @param end   -the rightmost Point of the Line
     */
    public Line(Point start, Point end) {
        //we define start as the point with the lowest X value
        this.start = start;
        this.end = end;
    }

    /**
     * Line - a constructor function.
     * in case the line values are given as 4 doubles,
     * instead of two points, we call the other constructor.
     *
     * @param x1 - first point x's value
     * @param x2 - second point x's value
     * @param y2 - second point y's value
     * @param y1 - first point y's value
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * length - a function that returns the length of
     * our Line segment.
     *
     * @return length-the distance between our 2 Points
     */
    public double length() {
        double length = 0;
        //we use the function we already defined in Point
        length = start.distance(end);
        return length;
    }

    /**
     * middle - a function that defines the middle Point of our Line.
     *
     * @return middle-the midpoint of the Line segment
     */
    public Point middle() {
        double x3 = (start.getX() + ((end.getX() - start.getX()) / 2));
        double y3 = (start.getY() + ((end.getY() - start.getX()) / 2));
        Point middle = new Point(x3, y3);
        return middle;
    }

    // Returns the start point of the line

    /**
     * start - a function that returns our Line's starting Point.
     *
     * @return start - the starting Point of our line segment
     */
    public Point start() {
        return start;
    }

    /**
     * end - a function that returns our Line's end Point.
     *
     * @return end - the end Point of our Line segment
     */
    public Point end() {
        return end;
    }

    /**
     * isIntersecting - function that receives a Line segment and checks (true/false)
     * if it intersects with our current Line segment.
     *
     * @param other - the other Line we need to check for intersections
     * @return intersect - true if our Line segments intersect, otherwise returns false
     */
    public boolean isIntersecting(Line other) {
        Point intersectPoint;
        intersectPoint = this.intersectionWith(other);
        return (intersectPoint != null);
    }


    /**
     * slope - function receives a line and returns the slope (gradient) of said line.
     *
     * @param findSlope - receives a Line object
     * @return slope - a double containing the 'slope' of the received line
     **/
    public double slope(Line findSlope) {
        double x1, x2, y1, y2;
        double slope;
        x1 = findSlope.start.getX();
        x2 = findSlope.end.getX();
        y1 = findSlope.start.getY();
        y2 = findSlope.end.getY();
        slope = ((y2 - y1) / (x2 - x1));
        return slope;
    }

    /**
     * findEquationB - receives a Line and finds the 'b' for the line equation
     * y=m*x+b.
     *
     * @param input - the Line we are locating the b for
     * @return b - the b of any given Line
     **/
    public double findEquationB(Line input) {
        double y = input.start.getY();
        double x = input.start.getX();
        double m = slope(input);
        double b = (y - (m * x));
        return b;
    }


    /**
     * confirmSegment - receives two Line segments and a Point on the (same) Lines,
     * returns True if the Point also exists on that Line segment
     * we can already assume (because we designed it that way) that the given
     * Point is already a part of the SAME (infinite) Line-we need only to
     * check the Line segment.
     *
     * @param input  - Line segment we are confirming
     * @param other  - the other Line segment we are confirming
     * @param exists - Point we are confirming (on the Line segments)
     * @return true/false -if we confirmed the segment or not
     */
    public boolean confirmSegment(Line input, Line other, Point exists) {
        double x1 = input.start().getX();
        double y1 = input.start().getY();

        double x2 = input.end().getX();
        double y2 = input.end().getY();

        double x3 = other.start().getX();
        double y3 = other.start().getY();

        double x4 = other.end().getX();
        double y4 = other.end().getY();

        double xM = exists.getX();
        double yM = exists.getY();

        boolean line1 = false;
        boolean line2 = false;

        double highXLine1 = Math.max(x1, x2);
        double lowXLine1 = Math.min(x1, x2);
        double highYLine1 = Math.max(y1, y2);
        double lowYLine1 = Math.min(y1, y2);
        //we check if xM,yM is congruent to line1
        if ((biggerOrEqual(highXLine1, xM) && biggerOrEqual(xM, lowXLine1))
                && ((biggerOrEqual(highYLine1, yM) && biggerOrEqual(yM, lowYLine1)))) {
            line1 = true;
        }
        double highXLine2 = Math.max(x3, x4);
        double lowXLine2 = Math.min(x3, x4);
        double highYLine2 = Math.max(y3, y4);
        double lowYLine2 = Math.min(y3, y4);
        if ((biggerOrEqual(highXLine2, xM) && biggerOrEqual(xM, lowXLine2))
                && ((biggerOrEqual(highYLine2, yM) && biggerOrEqual(yM, lowYLine2)))) {
            line2 = true;
        }
        return (line1 && line2);
    }


    /**
     * //intersectionWith - function for finding the intersection point between two
     * Line segments. we use the principle that all Lines can be defined \
     * as- y=mx+b  (therefore at any intersection m1*x+b1=m2*x+b2)
     *
     * @param other - the second Line we are confirming intersection with
     * @return - intersection - the intersection Point between the 2 Lines
     * (or null if none exists)
     **/
    public Point intersectionWith(Line other) {
        double x1 = this.start.getX();
        double y1 = this.start.getY();

        double x2 = this.end.getX();
        double y2 = this.end.getY();

        double x3 = other.start.getX();
        double y3 = other.start.getY();

        double x4 = other.end.getX();
        double y4 = other.end.getY();

        double xFinal;
        double yFinal;
        Point intersection = null;
        //if Line1 is vertical
        if (doubleEquals(x1, x2)) {
            //if Line2 is also vertical-they are parrallel
            if (doubleEquals(x3, x4)) {
                return intersection;
                //otherwise we solve when Line1 is vertical and Line2 is not
            } else {
                xFinal = x1;
                double slope2 = slope(other);
                yFinal = slope2 * xFinal + findEquationB(other);
                //we confirm we are on the same segment
                if (confirmSegment(this, other, new Point(xFinal, yFinal))) {
                    intersection = new Point(xFinal, yFinal);
                    return intersection;
                }
            }


            //if Line2 is vertical
        } else if (doubleEquals(x3, x4)) {
            //if Line1 is also vertical-they are parallel
            if (doubleEquals(x1, x2)) {
                return intersection;
                //otherwise we solve when Line2 is vertical and Line1 is not
            } else {
                xFinal = x3;
                double slope1 = slope(this);
                yFinal = slope1 * xFinal + findEquationB(this);
                if (confirmSegment(this, other, new Point(xFinal, yFinal))) {
                    intersection = new Point(xFinal, yFinal);
                    return intersection;
                }

            }

            //we can otherwise calculate normally, since neither line are vertical
        } else {
            //if the slopes are the same-the lines are parrallel
            double slope1 = slope(this);
            double slope2 = slope(other);
            if (doubleEquals(slope1, slope2)) {
                return intersection;
            }
            double b1 = findEquationB(this);
            double b2 = findEquationB(other);
            xFinal = (b2 - b1) / (slope1 - slope2);
            yFinal = slope1 * xFinal + b1;

            //we confirm our intersection point exists
            if (confirmSegment(this, other, new Point(xFinal, yFinal))) {
                intersection = new Point(xFinal, yFinal);
            }
        }
        return intersection;
    }


    /**
     * biggerOrEqual - this function determines if one double is smaller or
     * equal to another double (to within a reasonable degree of
     * uncertainty).
     *
     * @param num1 -the first (larger) double.
     * @param num2 -the second (smaller or equal) double.
     * @return true/false - if (num1>=num2)
     */
    public boolean biggerOrEqual(double num1, double num2) {
        return (num1 > num2) || (Math.abs(num1 - num2) < EPSILON);
    }

    /**
     * doubleEquals - this function determines if two doubles are
     * equal (within a reasonable degree of error).
     *
     * @param num1 -the first double.
     * @param num2 -the second double.
     * @return true/false num1==num2
     */
    public boolean doubleEquals(double num1, double num2) {
        return (Math.abs(num1 - num2) < EPSILON);
    }

    /**
     * notEquals - this function determines if two doubles are NOT equal
     * to each other (within a reasonable uncertainity).
     *
     * @param num1 -the first double.
     * @param num2 -the second double.
     * @return true-if they are unequal
     * false- if they ARE equal
     */
    public boolean notEquals(double num1, double num2) {
        return (Math.abs(num1 - num2) > EPSILON);
    }

    /**
     * closestIntersectionToStartOfLine - a function that receives a
     * Rectangle and returns the closest Point between the start of our
     * Line and our Line's intersection with our received Rectangle
     * (if any such Points exist).
     *
     * @param rect - the Rectangle we need to check for
     *             our Line intersecting
     * @return closestIntersectionToStartOfLine -the closest
     * Point that our Line intersects with the given Rectangle
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionPoints = new ArrayList<Point>(rect.intersectionPoints(this));
        if (intersectionPoints.size() < 1) {
            return null;
        } else {
            double closestDistance = this.start().distance(intersectionPoints.get(0));
            int closestIndex = 0;
            for (int i = 1; i < intersectionPoints.size(); i++) {
                if (this.start().distance(intersectionPoints.get(i)) < closestDistance) {
                    closestDistance = this.start().distance((intersectionPoints.get(i)));
                    closestIndex = i;
                }
            }
            return intersectionPoints.get(closestIndex);
        }
    }

    /**
     * drawLine - draws our Line.
     *
     * @param d   -drawing surface.
     * @param col -drawing color.
     */
    public void drawLine(DrawSurface d, Color col) {
        d.setColor(col);
        d.drawLine((int) this.start().getX(), (int) this.start().getY(),
                (int) this.end().getX(), (int) this.end().getY());
    }
}
