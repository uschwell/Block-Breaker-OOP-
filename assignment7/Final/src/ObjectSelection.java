
/**
 * MenuAnimation - this class will define a Menu Animation.
 *
 * @author Uriel Schwell
 * @version 11.06.2018
 */

/**
 * @param <T> -the task.
 */
public class ObjectSelection<T> {
    private String key;
    private String message;
    private T returnVal;

    /**
     * ObjectSelection -Constructor.
     *
     * @param key       - key.
     * @param message   - printed message.
     * @param returnVal -return value.
     */
    public ObjectSelection(String key, String message, T returnVal) {
        this.key = key;
        this.message = message;
        this.returnVal = returnVal;
    }

    /**
     * getKey -get the key.
     *
     * @return -key
     */
    public String getKey() {
        return this.key;
    }

    /**
     * getMessage -get the message.
     *
     * @return -message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * getReturnVal -get the return value.
     *
     * @return -ReturnVal
     */
    public T getReturnVal() {
        return this.returnVal;
    }
}
