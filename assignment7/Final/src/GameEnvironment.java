import java.util.List;
import java.util.ArrayList;

/**
 * GameEnvironment -this class will be used by the Ball,
 * it contains the entire GameLevel Environment (including all
 * collidable objects).
 *
 * @author Uriel Schwell
 * @version 16.04.2018
 */
public class GameEnvironment {
    //a list of all the collidable objects in our environment
    private List<Collidable> collidableObjects;

    /**
     * GameEnvironment - function builds the GameEnvironemnt.
     *
     * @param collidableObjects - the list of all collidable objects in
     *                          the game environment.
     */
    public GameEnvironment(List<Collidable> collidableObjects) {
        this.collidableObjects = collidableObjects;
    }

    /**
     * GameEnvironment - function builds the GameEnvironemnt.
     */
    public GameEnvironment() {
        List<Collidable> collidable = new ArrayList<>();
        this.collidableObjects = collidable;
    }

    /**
     * addCollidable - this function adds a new collidable object
     * to our game environment.
     *
     * @param c - the Collidable object we want to add
     *          to the game environment
     */
    public void addCollidable(Collidable c) {
        collidableObjects.add(c);
    }

    /**
     * amountOfCollidables - this function will return the length
     * (amount) of collidable objects currently existing in our game
     * environment.
     *
     * @return the amount of collidable Objects on screen
     */
    public int amountOfCollidables() {
        return this.collidableObjects.size();
    }

    /**
     * objectList - this function returns the list of collidable objects that
     * exist in our current game Environment.
     *
     * @return this.collidableObjects- the list of collidable
     * objects
     */
    public List<Collidable> objectList() {
        return this.collidableObjects;
    }

    /**
     * CollisionInfo - this function receives a trajectory
     * and returns the closest point where
     * our trajectory will collide with a
     * (collidable) object.
     *
     * @param trajectory - our current trajectory (direction of
     *                   movement)
     * @return closestColiision - the closest collision Point
     * with a collidable object, along
     * our given trajectory.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Collidable current;
        CollisionInfo closestCollision = null;
        Point closest = null;
        double closestDistance = 100000;
        int blockIndex = 0;
        //we check every collidable object for intersections with our trajectory
        for (int i = 0; i < this.amountOfCollidables(); i++) {
            List<Point> interSectionPoints = new ArrayList<>();
            if (this.objectList().get(i).getCollisionRectangle().intersectionPoints(trajectory) != null) {
                interSectionPoints.addAll((this.objectList().get(i).getCollisionRectangle()
                        .intersectionPoints(trajectory)));
                for (int k = 0; k < interSectionPoints.size(); k++) {
                    if (interSectionPoints.get(k).distance(trajectory.start()) < closestDistance) {
                        closestDistance = interSectionPoints.get(k).distance(trajectory.start());
                        closest = interSectionPoints.get(k);
                        blockIndex = i;
                    }
                }
            }
        }
        //if we found our closest Point, we define closestColission around it
        if (closest != null) {
            closestCollision = new CollisionInfo(closest, this.objectList().get(blockIndex));
        }
        return closestCollision;
    }


    /**
     * removeCollidable - this function removes a collidable from the collidable List.
     *
     * @param c -collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        this.objectList().remove(c);
    }
}
