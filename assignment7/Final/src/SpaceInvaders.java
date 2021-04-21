
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * SpaceInvaders - this class will define the level
 * called "Space Invaders".
 *
 * @author Uriel Schwell
 * @version 21.06.2018
 */
public class SpaceInvaders implements LevelInformation {

    /**
     * Constructor. (empty).
     */
    public SpaceInvaders() {

    }

    @Override
    public int numberOfBalls() {
        return 0;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return null;
    }

    @Override
    public int paddleSpeed() {
        return 6;
    }

    @Override
    public int paddleWidth() {
        return 60;
    }

    @Override
    public String levelName() {
        return "SpaceInvaders";
    }

    @Override
    public Sprite getBackground() {
        return (new BackgroundSpaceInvaders());
    }

    @Override
    public ArrayList<Block> blocks() {
        AlienFleet fleet = new AlienFleet(10, 5, new Velocity(2, 2), new Point(50, 50));
        ArrayList<Block> allBlocks = new ArrayList<Block>();
        allBlocks.addAll(fleet.getAllAliens());
        int x = 200;
        int y = 500;
        //add some shields
        for (int i = 0; i < 10; i++) {
            Point tempPoint = new Point(x, y);
            Rectangle tempRec = new Rectangle(tempPoint, 40, 5);
            Block shieldBlock = new Block(tempRec, Color.CYAN, 1, 0);
            allBlocks.add(shieldBlock);
            x = x + 40;
        }

        return allBlocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 50;
    }
}
