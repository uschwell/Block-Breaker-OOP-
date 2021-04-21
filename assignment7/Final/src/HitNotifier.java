/**
 * HitNotifier -this interface will be used to add/remove
 *      a Listener to a specific event.
 * @author Uriel Schwell
 * @version 17.05.2018
 */



public interface HitNotifier {
    /**
     *Add hl as a listener to hit events.
     * @param hl -hitListener to add.
     */
    void addHitListener(HitListener hl);

    /**
     *Remove hl from the list of listeners to hit events.
     * @param hl -hitListener to remove.
     */
    void removeHitListener(HitListener hl);
}
