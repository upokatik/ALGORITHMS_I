/**
 * Problem statement: Dynamic median.
 * Design a data type that supports insert in logarithmic time,
 * find-the-median in constant time, and remove-the-median in logarithmic time.
 *
 * @author Dmitry Strebkov
 * <p>
 * Created by upokatik on 21.03.17.
 */

import edu.princeton.cs.algs4.StdRandom;

public class DynamicMedian {

    private static final int HEAPS_CAPACITY = 1024;

    private abstract class Heap {
        int[] heap;
        int heapSize;

        Heap(int capacity) {
            heap = new int[capacity];
            heapSize = 0;
        }

        abstract boolean inOrder(int i1, int i2);

        int getTop() {
            return heap[0];
        }

        int getSize() {
            return heapSize;
        }

        void insert(int e) {
            heap[heapSize++] = e;
            swim();
        }

        int removeTop() {
            int top = getTop();
            exchange(0, --heapSize);
            sink();
            return top;
        }

        private int parentIndex(int i) {
            if (i > 2) {
                return i / 2;
            } else if (i > 0) {
                return 0;
            }
            throw new IllegalArgumentException();
        }

        private int firstChild(int i) {
            if (i > 0) {
                return 2 * i;
            } else if (i == 0) {
                return 1;
            }
            throw new IllegalArgumentException();
        }

        private void swim() {
            int i = heapSize - 1;
            while (i > 0 && !inOrder(parentIndex(i), i)) {
                exchange(i, parentIndex(i));
                i = parentIndex(i);
            }
        }

        void sink() {
            int i1 = 0;
            while (firstChild(i1) < heapSize) {
                int i2 = firstChild(i1);
                if (i2 + 1 < heapSize && !inOrder(i2, i2 + 1)) {
                    i2++;
                }
                if (inOrder(i1, i2)) {
                    break;
                }

                exchange(i1, i2);
                i1 = i2;
            }
        }

        protected void exchange(int i1, int i2) {
            int temp = heap[i1];
            heap[i1] = heap[i2];
            heap[i2] = temp;
        }
    }

    private class MaxHeap extends Heap {
        MaxHeap(int capacity) {
            super(capacity);
        }

        @Override
        public boolean inOrder(int i1, int i2) {
            return heap[i1] > heap[i2];
        }
    }

    private class MinHeap extends Heap {
        MinHeap(int capacity) {
            super(capacity);
        }

        @Override
        public boolean inOrder(int i1, int i2) {
            return heap[i1] < heap[i2];
        }
    }

    private MaxHeap maxHeap = null;
    private MinHeap minHeap = null;

    public DynamicMedian(int[] array, int median) {
        maxHeap = new MaxHeap(HEAPS_CAPACITY);
        minHeap = new MinHeap(HEAPS_CAPACITY);

        for (int anArray : array) {
            if (anArray <= median) {
                maxHeap.insert(anArray);
            } else {
                minHeap.insert(anArray);
            }
        }
    }

    public void insert(int e) {
        if (e > minHeap.getTop()) {
            minHeap.insert(e);
            balanceHeaps();
        } else {
            maxHeap.insert(e);
            balanceHeaps();
        }
    }

    public int findMedian() {
        return maxHeap.getTop();
    }

    public void removeMedian() {
        maxHeap.removeTop();
        balanceHeaps();
    }

    private void balanceHeaps() {
        while (maxHeap.getSize() - 1 > minHeap.getSize()) {
            int top = maxHeap.removeTop();
            minHeap.insert(top);
        }
        while (minHeap.getSize() >= maxHeap.getSize()) {
            int top = minHeap.removeTop();
            maxHeap.insert(top);
        }
    }

    public static void main(String[] args) {
        final int N = 9; // up to 8

        int[] array = new int[N];
        for (int i = 0; i < N; ++i) {
            array[i] = i;
        }

        StdRandom.shuffle(array);

        assert array.length > 1;
        DynamicMedian dynamicMedian = new DynamicMedian(array, N / 2);
        System.out.println("Initial median = " + dynamicMedian.findMedian());
        System.out.println("-----------");

        for (int i = 9; i < 19; ++i) {
            dynamicMedian.insert(i);
            if (i % 2 == 0) {
                System.out.println("Median after insertion of 2 elements = " + dynamicMedian.findMedian());
            }
        }
        System.out.println("-----------");

        for (int i = 0; i < 7; ++i) {
            dynamicMedian.removeMedian();
            dynamicMedian.removeMedian();
            System.out.println("Median after removing two elements = " + dynamicMedian.findMedian());
        }

        System.out.println("OK!");
    }
}
