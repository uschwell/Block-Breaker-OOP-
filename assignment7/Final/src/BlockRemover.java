/**
 * BlockRemover - function in charge of removing blocks from the GameLevel.
 *
 * @author Uriel Schwell
 * @version 21.04.2018
 */


public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * BlockRemover - constructor function.
     *
     * @param game          - the GameLevel.
     * @param removedBlocks - the Block Counter
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * determines what happens when the block is hit.
     * @param beingHit -block thats been hit.
     * @param hitter -ball doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        //we alter the amount of hits our block has
        if (beingHit.getHitPoints() > 1) {
            beingHit.setHitPoints(beingHit.getHitPoints() - 1);
            //if the hit counter has reached zero we remove the block
        } else {
            this.remainingBlocks.decrease(1);
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(this.game);
        }

    }
}
