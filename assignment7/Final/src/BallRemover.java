/**
 * BallRemover - function in charge of removing balls from the GameLevel.
 *
 * @author Uriel Schwell
 * @version 21.04.2018
 */


public class BallRemover implements HitListener {

    private GameLevel game;
    private Counter remainingBalls;

    /**
     * BallRemover - constructor.
     *
     * @param game  -game we are working in.
     * @param balls -amount of existing balls.
     */
    public BallRemover(GameLevel game, Counter balls) {
        this.game = game;
        this.remainingBalls = balls;
    }

    /**
     * hitEvent - in the event we hit a block.
     * @param beingHit -block being hit.
     * @param hitter - ball doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        game.getBallsLeft().decrease(1);
    }
}
