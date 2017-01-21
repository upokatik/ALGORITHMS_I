import java.util.Iterator;

/**
 * Problem statement: Write a generic data type for a deque.
 *
 * @author Dmitry Strebkov
 *         <p>
 *         Created by upokatik on 21.01.17.
 */

public class Deque<Item> implements Iterable<Item> {
    /**
     * Construct an empty deque.
     */
    public Deque() {

    }

    /**
     * Is the deque empty?
     *
     * @return true if the deque is empty, false otherwise
     */
    public boolean isEmpty() {
        return true;
    }

    /**
     * Return the number of items on the deque
     *
     * @return the number of items on the deque
     */
    public int size() {
        return 0;
    }

    /**
     * Add the item to the front
     *
     * @param item an element to be added to the front of the deque
     */
    public void addFirst(Item item) {

    }

    /**
     * Add the item to the end
     *
     * @param item an element to be added to the end of the deque
     */
    public void addLast(Item item) {

    }

    /**
     * Remove and return the item from the front
     *
     * @return first item from the front
     */
    public Item removeFirst() {
        return null;
    }

    /**
     * Remove and return the item from the end
     *
     * @return first item from the end
     */
    public Item removeLast() {
        return null;
    }

    /**
     * Return an iterator over items in order from front to end
     *
     * @return an iterator over items in order from front to end
     */
    public Iterator<Item> iterator() {
        return null;
    }

    /**
     * Unit testing (optional)
     */
    public static void main(String[] args) {

    }
}
