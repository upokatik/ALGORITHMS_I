/**
 * Problem statement: Taxicab numbers.
 * A taxicab number is an integer that can be expressed as the sum of two cubes of integers in two different ways:
 * a3+b3=c3+d3.
 * For example, 1729=93+103=13+123.
 * Design an algorithm to find all taxicab numbers with a, b, c, and d less than n.
 * <p>
 * Version 2: Use time proportional to n2logn and space proportional to n.
 *
 * @author Dmitry Strebkov
 *         <p>
 *         Created by upokatik on 21.03.17.
 */

public class TaxicabNumbers {

    private static class MinHeap {
        private int[] heap = null;
        private int heapSize = 0;

        MinHeap(int capacity) {
            heap = new int[capacity + 1]; // for unused 0-th element
        }

        public boolean isEmpty() {
            return heapSize == 1;
        }

        public void add(int e) {
            if (heapSize + 1 == heap.length) {
                throw new RuntimeException();
            }

            heap[++heapSize] = e;
            swim();
        }

        public int removeTop() {
            if (isEmpty()) {
                throw new RuntimeException();
            }

            int e = heap[1];
            exchange(1, heapSize--);
            sink();
            return e;
        }

        private void swim() {
            int i = heapSize;
            while (i > 1 && less(i, i / 2)) {
                exchange(i, i / 2);
                i /= 2;
            }
        }

        private void sink() {
            int i1 = 1;
            while (2 * i1 <= heapSize) {
                int i2 = 2 * i1;
                if (2 * i1 + 1 <= heapSize && !less(2 * i1, 2 * i1 + 1)) {
                    i2++;
                }
                if (less(i1, i2)) {
                    break;
                }

                exchange(i1, i2);
                i1 = i2;
            }
        }

        private boolean less(int i1, int i2) {
            return heap[i1] < heap[i2];
        }

        private void exchange(int i1, int i2) {
            int temp = heap[i1];
            heap[i1] = heap[i2];
            heap[i2] = temp;
        }
    }

    public static void main(String[] args) {
        final int N = 100;

        MinHeap minHeap = new MinHeap(N * N);

        for (int i = 1; i < N; ++i) {
            for (int j = i; j < N; ++j) {
                int value = i * i * i + j * j * j;
                minHeap.add(value);
            }
        }

        int previous = 0;
        while (!minHeap.isEmpty()) {
            int value = minHeap.removeTop();
            if (previous == value) {
                System.out.println("Next Taxicab number : " + value);
            }
            previous = value;
        }
    }
}
