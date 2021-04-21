import java.util.ArrayList;

/**
 * AlienFleet - this class will define a Fleet, made from several
 * Columns of Aliens.
 *
 * @author Uriel Schwell
 * @version 21.06.2018
 */
public class AlienFleet {
    private ArrayList<AlienColumn> fleet;
    private Velocity speed;

    /**
     * Constructor.
     *
     * @param width  -how many columns of Aliens.
     * @param height -the hieght of each column.
     * @param speed  -the speed.
     * @param start  -the starting point.
     */
    public AlienFleet(int width, int height, Velocity speed, Point start) {
        double x = start.getX();
        double y = start.getY();
        this.fleet = new ArrayList<AlienColumn>();
        for (int i = 0; i < width; i++) {
            x = x + 50;
            Point tempPoint = new Point(x, y);
            AlienColumn temp = new AlienColumn(height, tempPoint, speed);
            this.fleet.add(temp);
        }

    }

    /**
     * Returns the fleet List.
     *
     * @return this.fleet - the current fleet.
     */
    public ArrayList<AlienColumn> getFleet() {
        return this.fleet;
    }

    /**
     * Returns a List of all individual Aliens.
     *
     * @return -all the aliens.
     */
    public ArrayList<Block> getAllAliens() {
        ArrayList<Block> allAliens = new ArrayList<>();
        for (int i = 0; i < fleet.size(); i++) {
            AlienColumn tempCOl = fleet.get(i);
            for (int k = 0; k < tempCOl.getAlienColumn().size(); k++) {
                allAliens.add(tempCOl.getAlienColumn().get(k));
            }
        }
        return allAliens;
    }

}
