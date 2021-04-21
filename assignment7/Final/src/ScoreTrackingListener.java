/**
 * ScoreTrackingListener - function in charge of tracking Scores.
 *
 * @author Uriel Schwell
 * @version 21.04.2018
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * ScoreTrackingListener - constructor.
     *
     * @param scoreCounter - the scorecount.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * hitEvent - function for changing the score when block is hit.
     *
     * @param beingHit -block being hit.
     * @param hitter   - ball doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == 1) {
            currentScore.increase(beingHit.scoreWorth());
        }
    }

    /**
     *
     * @return current Count.
     */
    public int getScore() {
        return currentScore.getValue();
    }
}
