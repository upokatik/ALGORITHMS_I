import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

/**
 * Problem statement: Write a generic data type for a randomized queue.
 *
 * @author Dmitry Strebkov
 *         <p>
 *         Created by upokatik on 21.01.17.
 */

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] array = null;
    private int size = 0;

    /**
     * Construct an empty randomized queue
     */
    public RandomizedQueue() {
        array = (Item[]) new Object[1];
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
     * Add the item
     *
     * @param item an element to be added
     */
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException("Null item enqueues not supported.");
        }

        if (size == array.length) {
            resizeArray(array.length * 2);
        }

        array[size++] = item;
    }

    /**
     * Remove and return a random item
     *
     * @return random item that is removed
     */
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot remove from an empty queue.");
        }

        int index = StdRandom.uniform(size);

        Item item = array[index];
        array[index] = array[size - 1];
        array[--size] = null; // explicit null-ing to avoid loitering

        if (size > 0 && size == array.length / 4) {
            resizeArray(array.length / 2);
        }

        return item;
    }

    /**
     * Return (but do not remove) a random item
     *
     * @return random item that is sampled
     */
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot sample an empty queue.");
        }

        int index = StdRandom.uniform(size);
        return array[index];
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int[] indices = null;
        private int current = 0;

        RandomizedQueueIterator() {
            indices = new int[size];
            for (int i = 0; i < size; ++i) {
                indices[i] = i;
            }
            StdRandom.shuffle(indices);
        }

        @Override
        public boolean hasNext() {
            return current < indices.length;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Iterator doesn't have next item.");
            }

            return array[indices[current++]];
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove operation is prohibited for iterators.");
        }
    }

    /**
     * Return an independent iterator over items in random order
     *
     * @return an independent iterator over items in random order
     */
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private void resizeArray(int newSize) {
        Item[] newArray = (Item[]) new Object[newSize];

        for (int i = 0; i < size; ++i) {
            newArray[i] = array[i];
        }

        array = newArray;
    }

    /**
     * Unit testing (optional)
     */
    public static void main(String[] args) {
        RandomizedQueue<Integer> queue1 = new RandomizedQueue<>();
        queue1.enqueue(0);
        queue1.enqueue(1);
        queue1.enqueue(2);
        queue1.enqueue(3);
        validateQueueIteration(queue1);

        RandomizedQueue<Integer> queue2 = new RandomizedQueue<>();
        queue2.enqueue(0);
        queue2.enqueue(1);
        queue2.enqueue(2);
        queue2.enqueue(3);
        queue2.enqueue(4);
        queue2.enqueue(5);
        queue2.enqueue(6);
        queue2.enqueue(7);
        queue2.enqueue(8);
        queue2.enqueue(9);
        queue2.enqueue(10);
        queue2.enqueue(11);
        validateQueueDequeue(queue2);

        queue2.enqueue(0);
        queue2.enqueue(1);
        queue2.enqueue(2);
        queue2.enqueue(3);
        queue2.enqueue(4);
        queue2.enqueue(5);
        queue2.enqueue(6);
        queue2.enqueue(7);
        queue2.enqueue(8);
        queue2.enqueue(9);
        queue2.enqueue(10);
        queue2.enqueue(11);
        if (queue2.size() != 12) {
            System.out.println("Error: mistaken size of the queue.");
        }
        validateQueueDequeue(queue2);

        RandomizedQueue<Integer> queue3 = new RandomizedQueue<>();
        queue3.enqueue(21);
        queue3.dequeue();
        queue3.enqueue(10);

        System.out.println("OK: Tests passed!");
    }

    private static void validateEmpty(RandomizedQueue<Integer> queue) {
        if (!queue.isEmpty()) {
            System.out.println("Error: queue.isEmpty() returned 'false' for empty queue.");
        }
        if (queue.size() != 0) {
            System.out.println("Error: queue.size() returned non-zero for empty queue.");
        }
    }

    private static void validateQueueIteration(RandomizedQueue<Integer> queue) {
        boolean[] meetings = new boolean[queue.size()];
        for (Integer i : queue) {
            if (meetings[i]) {
                System.out.println("Error: the value '" + i + "' already met.");
            } else {
                meetings[i] = true;
            }
        }

        while (queue.size() > 0) {
            queue.dequeue();
        }

        validateEmpty(queue);
    }

    private static void validateQueueDequeue(RandomizedQueue<Integer> queue) {
        boolean[] meetings = new boolean[queue.size()];
        while (queue.size() > 0) {
            Integer i = queue.dequeue();
            if (meetings[i]) {
                System.out.println("Error: the value '" + i + "' already met.");
            } else {
                meetings[i] = true;
            }
        }

        validateEmpty(queue);
    }
}
