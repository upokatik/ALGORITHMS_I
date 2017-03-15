/**
 * Problem statement: Implement Priority Queue based on Binary Heap data structure.
 *
 * @author Dmitry Strebkov
 * <p>
 * Created by upokatik on 14.03.17.
 */

import edu.princeton.cs.algs4.StdRandom;

public class PriorityQueue<Key extends Comparable<Key>> {

    private Key[] pq = null;
    private int N;

    /**
     * Creates an instance of the priority queue
     *
     * @param capacity capacity of the priority queue being created
     */
    public PriorityQueue(int capacity) {
        pq = (Key[]) new Comparable[capacity + 1]; // + 1 for empty element at index 0
        N = 0;
    }

    /**
     * Adds a key to the priority queue.
     *
     * @param key a key to be added to the priority queue
     */
    public void add(Key key) {
        pq[++N] = key;
        swim(N);
    }

    /**
     * Removes the key with highest priority (max) from the priority queue
     *
     * @return the key with highest priority that has been removed from the priority queue
     */
    public Key delMax() {
        exchange(1, N);
        Key key = pq[N];
        pq[N--] = null;
        sink(1);
        return key;
    }

    /**
     * Helper functions that performs comparison of two keys using compareTo() method
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
     * Performs sinking-down operation for the key stored at index i
     *
     * @param i index of the key for sinking
     */
    private void sink(int i) {
        int j = 2 * i;
        while (j <= N) {
            if (j < N && less(pq[j], pq[j + 1]) < 0) {
                ++j;
            }

            if (less(pq[i], pq[j]) < 0) {
                exchange(i, j);
                i = j;
                j = 2 * i;
            } else {
                break;
            }
        }
    }

    /**
     * Helper functions that performs swapping of keys in two positions passed as parameters
     *
     * @param i index of the first key for swapping
     * @param j index of the second key for swapping
     */
    private void exchange(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    public static void main(String[] args) {

        final int N = 100;

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(N);
        int[] array = new int[N];
        for (int i = 0; i < N; ++i) {
            array[i] = i;
        }

        StdRandom.shuffle(array);

        for (int i = 0; i < N; ++i) {
            priorityQueue.add(array[i]);
        }

        for (int i = 0; i < N; ++i) {
            Integer key = priorityQueue.delMax();
            if (key != N - i - 1) {
                System.out.println("FAIL!");
                return;
            }
        }

        System.out.println("OK!");
    }
}
