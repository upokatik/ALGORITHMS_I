/**
 * Problem statement: Implement Priority Queue based on Binary Heap data structure.
 *
 * @author Dmitry Strebkov
 *         <p>
 *         Created by upokatik on 14.03.17.
 */

public class PriorityQueue<Key extends Comparable<Key>> {

    private Key[] pq = null;
    private int N;

    /**
     * Creates an instance of the priority queue
     *
     * @param capacity capacity of the priority queue being created
     */
    public PriorityQueue(int capacity) {
        pq = (Key[]) new Comparable[capacity];
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

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(8);

        priorityQueue.add(4);
        priorityQueue.add(5);
        priorityQueue.add(7);
        priorityQueue.add(2);
        priorityQueue.add(1);
        priorityQueue.add(3);
        priorityQueue.add(6);

        Integer k1 = priorityQueue.delMax();
        System.out.println(k1);

        Integer k2 = priorityQueue.delMax();
        System.out.println(k2);

        Integer k3 = priorityQueue.delMax();
        System.out.println(k3);

        Integer k4 = priorityQueue.delMax();
        System.out.println(k4);

        Integer k5 = priorityQueue.delMax();
        System.out.println(k5);

        Integer k6 = priorityQueue.delMax();
        System.out.println(k6);

        Integer k7 = priorityQueue.delMax();
        System.out.println(k7);

        System.out.println("OK!");
    }
}
