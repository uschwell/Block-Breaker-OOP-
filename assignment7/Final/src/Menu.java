/**
 * Menu - this interface will help define our Menu.
 *
 * @author Uriel Schwell
 * @version 11.06.2018
 */

/**
 * @param <T> -task to perform.
 */
public interface Menu<T> extends Animation {
    /**
     * @param key -key we want.
     * @param message -to print.
     * @param returnVal -the task to perform.
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * get the task.
     * @return the status.
     */
    T getStatus();

    /**
     * add a sub-menu.
     *
     * @param key     -selectionKey.
     * @param message -
     * @param subMenu -the subMenu to create.
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
}
