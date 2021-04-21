import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * GameLevel - this class will define and help initialize
 * the GameLevel.
 *
 * @author Uriel Schwell
 * @version 17.04.2018
 */
public class GameLevel implements Animation {


    private SpriteCollection sprites;
    private GameEnvironment environment;
    private LevelInformation levelInformation;
    private GUI screen;
    private Paddle paddle;
    private Counter remainingBlocks;
    private Counter ballsLeft;
    private Counter score;
    private Counter lives;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyBoard;

    /**
     * * GameLevel - a constructor for when we play our game with several levels.
     *
     * @param levelInfo    -level Information.
     * @param aR           -animationRunner.
     * @param k            - the keyboard sensor.
     * @param lifeCounter  - keeps track of our lives.
     * @param scoreCounter - keeps track of our score.
     */
    public GameLevel(LevelInformation levelInfo, AnimationRunner aR, KeyboardSensor k,
                     Counter lifeCounter, Counter scoreCounter) {
        this.screen = aR.getAnimationGUI();
        this.sprites = new SpriteCollection();
        sprites.addSprite(levelInfo.getBackground());
        this.environment = new GameEnvironment();
        this.levelInformation = levelInfo;

        this.remainingBlocks = new Counter();
        this.remainingBlocks.increase(levelInfo.numberOfBlocksToRemove());

        this.ballsLeft = new Counter();
        this.score = scoreCounter;
        this.lives = lifeCounter;
        this.runner = aR;
        this.running = true;
        this.keyBoard = this.screen.getKeyboardSensor();
    }

    /**
     * addCollidable - this function adds a new collidable object
     * to our game environment.
     *
     * @param c - the collidable object we wish to add
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * addSprite - this function adds a new Sprite to our
     * Sprite Collection.
     *
     * @param s - the sprite we wish to add
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }


    /**
     * getBallsLeft - the counter tracking how many balls are left.
     *
     * @return - the counter tracking how many balls are left.
     */
    public Counter getBallsLeft() {
        return this.ballsLeft;
    }

    /**
     * initialize - this function will help us to/by initializing
     * the game.
     */
    public void initialize() {
        //we initialize the paddle
        Point paddlePoint = new Point(400 - (levelInformation.paddleWidth() / 2), 565);
        this.paddle = new Paddle(this.screen, paddlePoint, levelInformation.paddleWidth(),
                levelInformation.paddleSpeed());
        //we ensure the paddle is a part of our game
        paddle.addToGame(this);


        //we initialize our Indicators
        LivesIndicator livesIndicator = new LivesIndicator(lives);
        livesIndicator.addToGame(this);

        ScoreIndicator scoreIndicator = new ScoreIndicator(score);
        scoreIndicator.addToGame(this);

        LevelNameIndicator levelNameIndicator = new LevelNameIndicator(levelInformation.levelName());
        levelNameIndicator.addToGame(this);

        //we initialize our Listeners
        HitListener blockRemover = new BlockRemover(this, remainingBlocks);
        HitListener ballRemover = new BallRemover(this, ballsLeft);
        ScoreTrackingListener scoreListener = new ScoreTrackingListener(this.score);


//        //build the relevant border blocks
//        Rectangle top = new Rectangle(new Point(0, 20), 800, 15);
//        Block topBorder = new Block(top, Color.GRAY, 0);
//        topBorder.addToGame(this);
//

        Rectangle left = new Rectangle(new Point(0, 0), 15, 650);
        Block leftBorder = new Block(left, Color.BLACK, 0, 0);
        leftBorder.addToGame(this);

        Rectangle right = new Rectangle(new Point(790, 0), 15, 650);
        Block rightBorder = new Block(right, Color.BLACK, 0, 0);
        rightBorder.addToGame(this);


        //We add one more listener
        HitListener sideHit = new HitSides(leftBorder, rightBorder);

        //build the 'death block/zones'
        Rectangle bottom = new Rectangle(new Point(0, 601), 800, 1);
        Block deathBorderBottom = new Block(bottom, Color.BLACK, 0, 0);
        deathBorderBottom.addToGame(this);
        deathBorderBottom.addHitListener(ballRemover);

        Rectangle top = new Rectangle(new Point(0, 25), 800, 1);
        Block deathBorderTop = new Block(top, Color.BLACK, 0, 0);
        deathBorderTop.addToGame(this);
        deathBorderBottom.addHitListener(ballRemover);


        //build/add all the other blocks that belong to the level
        for (Block block : levelInformation.blocks()) {
            block.addHitListener(blockRemover);
            block.addHitListener(scoreListener);
            block.addHitListener(sideHit);
            block.addToGame(this);
        }

    }

//    /**
//     * initializeBalls - lets us add balls to our level.
//     */
//    private void initializeBalls() {
//        for (Velocity ballStartVelocity : levelInformation.initialBallVelocities()) {
//            double ballX = ((400 - (levelInformation.paddleWidth() / 2)) + (levelInformation.paddleWidth() / 2));
//
//            Ball ball = new Ball(new Point(ballX, 555), 5, Color.ORANGE, environment);
//            ball.setVelocity(ballStartVelocity);
//
//            ball.addToGame(this);
//
//            ballsLeft.increase(1);
//        }
//    }

    /**
     * run - this function will run the game animation loop.
     */
    public void playOneTurn() {

        //countdown before turn starts.
        this.runner.run(new CountdownAnimation(3, 3, this.sprites));

        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    /**
     * run - this will keep us running until we lose all our lives.
     */
    public void run() {
        while (lives.getValue() != 0) {
            playOneTurn();
        }
    }


    /**
     * removeCollidable - removes a Collidable.
     *
     * @param c -collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.objectList().remove(c);
    }

    /**
     * removeSprite - removes a Sprite.
     *
     * @param s -collidable to remove.
     */
    public void removeSprite(Sprite s) {

        this.sprites.objectList().remove(s);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.keyBoard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyBoard));
        } else {
            this.sprites.drawAllOn(d);
            this.sprites.notifyAllTimePassed();
            if (remainingBlocks.getValue() == 0) {
                this.running = false;
            }
        }
    }


    /**
     * blocksLeft -counts the blocks left in our Level.
     *
     * @return the amount of blocks remaining in this Level.
     */
    public int blocksLeft() {
        return remainingBlocks.getValue();
    }

    @Override
    public boolean shouldStop() {
        return (!this.running);
    }


    /**
     * createBallsOnTopOfPaddle - creates ball/s right over
     * our paddle.
     */
    public void createBallsOnTopOfPaddle() {
        Ball b1 = new Ball(new Point(300, 590), 5, Color.RED, this.environment);
        b1.setVelocity(3, -3);
        b1.addToGame(this);
        ballsLeft.increase(1);
    }
}
