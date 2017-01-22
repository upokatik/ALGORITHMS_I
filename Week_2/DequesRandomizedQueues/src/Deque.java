import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Problem statement: Write a generic data type for a deque.
 *
 * @author Dmitry Strebkov
 *         <p>
 *         Created by upokatik on 21.01.17.
 */

public class Deque<Item> implements Iterable<Item> {

    private class Node {
        private Item value;
        private Node next;
        private Node prev;

        Node() {
            next = null;
            prev = null;
        }
    }

    private Node first = null;
    private Node last = null;
    private int size;

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
        return size == 0;
    }

    /**
     * Return the number of items on the deque
     *
     * @return the number of items on the deque
     */
    public int size() {
        return size;
    }

    /**
     * Add the item to the front
     *
     * @param item an element to be added to the front of the deque
     */
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException("Null item adds not supported.");
        }

        Node oldFirst = first;

        first = new Node();
        first.value = item;

        if (oldFirst != null) {
            first.next = oldFirst;
            oldFirst.prev = first;
        } else {
            last = first;
        }

        ++size;
    }

    /**
     * Add the item to the end
     *
     * @param item an element to be added to the end of the deque
     */
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException("Null item adds not supported.");
        }

        Node oldLast = last;

        last = new Node();
        last.value = item;

        if (oldLast != null) {
            last.prev = oldLast;
            oldLast.next = last;
        } else {
            first = last;
        }

        ++size;
    }

    /**
     * Remove and return the item from the front
     *
     * @return first item from the front
     */
    public Item removeFirst() {
        return remove(first);
    }

    /**
     * Remove and return the item from the end
     *
     * @return first item from the end
     */
    public Item removeLast() {
        return remove(last);
    }

    private Item remove(Node node) {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot remove from an empty deque.");
        }

        Item oldItem = null;

        if (node.prev == null) {
            // Removing first

            oldItem = first.value;
            first = first.next;

            if (first != null) {
                first.prev = null;
            } else {
                last = null;
            }
        } else {
            // Removing last

            oldItem = last.value;
            last = last.prev;

            if (last != null) {
                last.next = null;
            } else {
                first = null;
            }
        }

        --size;

        return oldItem;
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Iterator doesn't have next item.");
            }

            Item value = current.value;
            current = current.next;
            return value;
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove operation is prohibited for iterators.");
        }
    }

    /**
     * Return an iterator over items in order from front to end
     *
     * @return an iterator over items in order from front to end
     */
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    /**
     * Unit testing (optional)
     */
    public static void main(String[] args) {

        Deque<String> deque1 = new Deque<>();
        deque1.addFirst("1.1");
        deque1.addFirst("1.2");
        deque1.addFirst("1.3");
        deque1.addFirst("1.4");
        validateDequeIteration(deque1, new String[]{"1.4", "1.3", "1.2", "1.1"});

        Deque<String> deque2 = new Deque<>();
        deque2.addLast("2.1");
        deque2.addLast("2.2");
        deque2.addLast("2.3");
        deque2.addLast("2.4");
        validateDequeIteration(deque2, new String[]{"2.1", "2.2", "2.3", "2.4"});

        Deque<String> deque3 = new Deque<>();
        deque3.addLast("3.3");
        deque3.addLast("3.4");
        deque3.addFirst("3.2");
        deque3.addFirst("3.1");
        validateDequeIteration(deque3, new String[]{"3.1", "3.2", "3.3", "3.4"});

        Deque<String> deque4 = new Deque<>();
        deque4.addLast("4.3");
        deque4.addFirst("4.2");
        deque4.addLast("4.4");
        deque4.addFirst("4.1");

        validateDequeIteration(deque4, new String[]{"4.1", "4.2", "4.3", "4.4"});

        Deque<String> deque5 = new Deque<>();
        deque5.addFirst("5.1");
        deque5.addFirst("5.2");
        deque5.addFirst("5.3");
        deque5.addFirst("5.4");
        validateDequeRemove(deque5, new String[]{"5.4", "5.3", "5.2", "5.1"}, true);

        Deque<String> deque6 = new Deque<>();
        deque6.addLast("6.1");
        deque6.addLast("6.2");
        deque6.addLast("6.3");
        deque6.addLast("6.4");
        validateDequeRemove(deque6, new String[]{"6.4", "6.3", "6.2", "6.1"}, false);

        System.out.println("OK: Tests passed!");
    }

    private static void validateEmpty(Deque<String> deque) {
        if (!deque.isEmpty()) {
            System.out.println("Error: deque.isEmpty() returned 'false' for empty deque.");
        }
        if (deque.size() != 0) {
            System.out.println("Error: deque.size() returned non-zero for empty deque.");
        }
    }

    private static void validateDequeIteration(Deque<String> deque, String[] expected) {
        int i = 0;
        for (String s : deque) {
            if (!s.equals(expected[i])) {
                System.out.println("Error: '" + s + "' is not equal to '" + expected[i] + "'");
            }
            ++i;
        }

        while (deque.size() > 0) {
            deque.removeFirst();
        }

        validateEmpty(deque);
    }

    private static void validateDequeRemove(Deque<String> deque, String[] expected, boolean removeFirst) {
        int i = 0;
        while (deque.size() > 0) {
            String s = removeFirst ? deque.removeFirst() : deque.removeLast();
            if (!s.equals(expected[i])) {
                System.out.println("Error: '" + s + "' is not equal to '" + expected[i] + "'");
            }
            ++i;
        }

        validateEmpty(deque);
    }
}
