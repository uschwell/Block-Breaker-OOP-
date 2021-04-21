import biuoop.KeyboardSensor;

import java.util.List;

/**
 * GameFlow - this class will help define our levels, and movement
 * between levels.
 *
 * @author Uriel Schwell
 * @version 22.04.2018
 */
public class GameFlow {

    private AnimationRunner animationRunner;
    private List<LevelInformation> levelInformation;
    private KeyboardSensor keyboardSensor;
    private int livesLeft;

    /**
     * \
     * constructor.
     *
     * @param levelInfo -level Information.
     * @param ar        -our animation runner.
     * @param k         -keyboard sensor.
     * @param lives     -live left.
     */
    public GameFlow(List<LevelInformation> levelInfo, AnimationRunner ar, KeyboardSensor k, int lives) {
        this.animationRunner = ar;
        this.levelInformation = levelInfo;
        this.keyboardSensor = k;
        this.livesLeft = lives;
    }

    /**
     * @param levels -level to run.
     */
    public void runLevels(List<LevelInformation> levels) {
        //our win/lose condition
        boolean winLose = true;

        //we create a Listener to track how many lives we have left.
        Counter lifeCounter = new Counter();
        lifeCounter.increase(this.livesLeft);

        Counter scoreCounter = new Counter();

        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.animationRunner,
                    this.keyboardSensor, lifeCounter, scoreCounter);
            level.initialize();
            //level has blocks left, and player has more lives, we keep playing.
            while (level.blocksLeft() > 0 && (lifeCounter.getValue() > 0)) {
                level.playOneTurn();
            }

            //if we die before getting rid of all blocks.
            if (level.blocksLeft() > 0) {
                lifeCounter.decrease(1);
            }
            //we are out of lives, we play the "you lost" animation
            if (lifeCounter.getValue() == 0) {
                winLose = false;
                break;
            }
        }
        animationRunner.run(new EndGame(scoreCounter, this.keyboardSensor, winLose));

    }
}

