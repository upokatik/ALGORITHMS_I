import java.util.Iterator;

/**
 * Problem statement: Write a generic data type for a randomized queue.
 *
 * @author Dmitry Strebkov
 *         <p>
 *         Created by upokatik on 21.01.17.
 */

public class RandomizedQueue<Item> implements Iterable<Item> {
    /**
     * Construct an empty randomized queue
     */
    public RandomizedQueue() {

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
     * Add the item
     *
     * @param item an element to be added
     */
    public void enqueue(Item item) {

    }

    /**
     * Remove and return a random item
     *
     * @return random item that is removed
     */
    public Item dequeue() {
        return null;
    }

    /**
     * Return (but do not remove) a random item
     *
     * @return random item that is sampled
     */
    public Item sample() {
        return null;
    }

    /**
     * Return an independent iterator over items in random order
     *
     * @return an independent iterator over items in random order
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
