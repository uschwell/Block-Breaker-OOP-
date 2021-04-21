import java.util.List;
import java.util.ArrayList;

/**
 * this class defines Rectangle objects (from 1 point, a height,
 * and a width) and all the functions related to it.
 *
 * @author Uriel Schwell
 * @version 15.04.2018
 */
public class Rectangle {
    //members of our object
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Rectangle - a constructor function
     * it defines a rectangle from one Point
     * and then from that Point a height and
     * width.
     *
     * @param upperLeft - the upper left Point of the Rectangle
     * @param width     -the width of the Rectangle
     * @param height    - the height of the Rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * intersectionPoints - a function that receives a Line
     * and returns a list of all (if any) points of
     * intersection between our rectangle and the Line.
     *
     * @param line - the Line we need to check for
     *             intersection Points
     * @return intersectionList -a list of all Points
     * of intersection between our Rectangle and
     * our Line (null if none exist)
     */
    public java.util.List intersectionPoints(Line line) {
        Point upperRight = new Point(this.upperLeft.getX() + this.getWidth(), this.upperLeft.getY());
        Point lowerLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.getHeight());
        Point lowerRight = new Point(this.upperLeft.getX() + this.getWidth(), this.upperLeft.getY() + this.getHeight());
        Line topLine = new Line(this.upperLeft, upperRight);
        Line lowerLine = new Line(lowerLeft, lowerRight);
        Line leftLine = new Line(this.upperLeft, lowerLeft);
        Line rightLine = new Line(upperRight, lowerRight);
        List<Point> intersectionList = new ArrayList();

        if (topLine.isIntersecting(line)) {
            intersectionList.add(line.intersectionWith(topLine));
        }
        if (lowerLine.isIntersecting(line)) {
            intersectionList.add(line.intersectionWith(lowerLine));
        }
        if (leftLine.isIntersecting(line)) {
            intersectionList.add(line.intersectionWith(leftLine));
        }
        if (rightLine.isIntersecting(line)) {
            intersectionList.add(line.intersectionWith(rightLine));
        }
        //after checking all 4 lines we confirm ANY intersection Points we found
        if (intersectionList.size() > 0) {
            return intersectionList;
        } else {
            return null;
        }
    }

    /**
     * getWidth - a function that returns our Rectangle's width.
     *
     * @return width - the width of our Rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * getHeight - a function that returns our Rectangle's height.
     *
     * @return width - the height of our Rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * getUpperLeft - a function that returns our Rectangle's upper
     * Left Point.
     *
     * @return upperLeft - the upper left Point of our Rectangle
     */
    public Point getUpperLeft() {
        return upperLeft;
    }


    /**
     * setUpperLeftLeft - this function moves our (paddle)
     * rectangle to the Left (or not at all if we are
     * at the screen edge).
     * @param speed -movement speed.
     */
    public void setUpperLeftLeft(int speed) {
        if (this.getUpperLeft().getX() - speed >= 15) {
            this.upperLeft = new Point(this.getUpperLeft().getX() - speed, this.getUpperLeft().getY());
        } else {
            this.upperLeft = new Point(this.getUpperLeft().getX(), this.getUpperLeft().getY());
        }
    }

    /**
     * setUpperLeftLRight - this function moves our (paddle)
     * rectangle to the Right (or not at all if we are
     * at the screen edge).
     * @param speed -movement speed.
     */
    public void setUpperLeftRight(int speed) {
        if (this.getUpperLeft().getX() + this.getWidth() <= (785)) {
            this.upperLeft = new Point(this.getUpperLeft().getX() + speed, this.getUpperLeft().getY());
        } else {
            this.upperLeft = new Point(this.getUpperLeft().getX(), this.getUpperLeft().getY());
        }
    }

    /**
     * set the upper Left Point.
     * @param newUpper -new Point placement.
     */
    public void setUpperLeft(Point newUpper) {
        this.upperLeft = newUpper;
    }

}
