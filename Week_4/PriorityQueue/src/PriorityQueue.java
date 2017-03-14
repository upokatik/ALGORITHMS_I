import java.util.Comparator;

/**
 * Problem statement: Counting inversions.
 * An inversion in an array a[] is a pair of entries a[i] and a[j] such that i<j, but a[i]>a[j].
 * Given an array, design a linearithmic algorithm to count the number of inversions.
 *
 * @author Dmitry Strebkov
 *         <p>
 *         Created by upokatik on 14.03.17.
 */

public class PriorityQueue<Key extends Comparable<Key>> {

    private Key[] pq = null;
    private int N;

    /**
     * Creates an instance of the priority queue.
     *
     * @param capacity capacity of the priority queue being created.
     */
    public PriorityQueue(int capacity) {
        pq = (Key[]) new Comparable[capacity];
        N = 0;
    }

    /**
     * Adds a key to the priority queue.
     *
     * @param key a key to be added to the priority queue.
     */
    public void add(Key key) {
        pq[++N] = key;
        swim(N);
    }

    /**
     * Removes the key with highest priority (max) from the priority queue.
     *
     * @return the key with highest priority that has been removed from the priority queue.
     */
    public Key delMax() {
        return null;
    }

    /**
     * Helper functions that performs comparison of two keys using compareTo() method.
     *
     * @param key1 first key
     * @param key2 second key
     * @return result of the comparison
     */
    private int less(Key key1, Key key2) {
        return key1.compareTo(key2);
    }

    /**
     * Performs swim-up-the-parents operation for the key stored at index i
     *
     * @param i index of the key for swimming
     */
    private void swim(int i) {
        while (i > 1 && (less(pq[i], pq[i / 2]) > 0)) {
            exchange(i, i / 2);
            i = i / 2;
        }
    }

    /**
     * Helper functions that performs swapping of keys in two positions passed as parameters.
     *
     * @param i index of the first key for swapping
     * @param j index of the second key for swapping*
     */
    private void exchange(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    public static void main(String[] args) {

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(8);

        priorityQueue.add(4);
        priorityQueue.add(5);
        priorityQueue.add(7);
        priorityQueue.add(2);
        priorityQueue.add(1);
        priorityQueue.add(3);
        priorityQueue.add(6);

        System.out.println("OK!");
    }
}
