/**
 * HitNotifier -this interface will be used whenever we hit a block
 *      a Listener to a specific event.
 * @author Uriel Schwell
 * @version 17.05.2018
 */
public interface HitListener {

    /**
     * @param beingHit block being hit.
     * @param hitter -ball hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}