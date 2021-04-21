import java.util.List;

/**
 * LevelInformation - this interface will define all the requirements for
 * a Game Level.
 *
 * @author Uriel Schwell
 * @version 21.04.2018
 */

public interface LevelInformation {
    /**
     * @return number of balls.
     */
    int numberOfBalls();

    /**
     * @return The initial velocity of each ball.
     */
    List<Velocity> initialBallVelocities();

    /**
     *
     * @return paddle speed.
     */
    int paddleSpeed();

    /**
     * @return paddle width.
     */
    int paddleWidth();

    /**
     *
     * @return name of the level.
     */
    String levelName();

    /**
     *
     * @return background (as Sprite).
     */
    Sprite getBackground();


    /**
     * The Blocks that make up this level.
     * @return -The Blocks that make up this level
     */
    List<Block> blocks();


    /**
     *Number of blocks that should be removed to clear the level.
     * @return Number of blocks that should be removed.
     */
    int numberOfBlocksToRemove();
}
