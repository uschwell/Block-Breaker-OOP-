/**
 * Counter - function used to help Count things.
 *
 * @author Uriel Schwell
 * @version 21.04.2018
 */
public class Counter {
    private int num;

    /**
     * add number to current count.
     * @param number -increase by this amount.
     */
    void increase(int number) {
        this.num += number;
    }

    /**
     *subtract number from current count.
     * @param number -decrease by this amount.
     */
    void decrease(int number) {
        this.num -= number;
    }

    /**
     * get current count.
     * @return -current count.
     */
    int getValue() {
        return num;
    }
}
