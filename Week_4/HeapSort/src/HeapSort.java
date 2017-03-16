/**
 * Problem statement: Implement Heap Sort algorithm.
 *
 * @author Dmitry Strebkov
 * <p>
 * Created by upokatik on 16.03.17.
 */

import edu.princeton.cs.algs4.StdRandom;

public class HeapSort {

    public static void heapSort(int[] array) {

        int arraySize = array.length;

        // Turning an array into binary heap representation
        for (int i = arraySize / 2; i > 0; --i) {
            sink(array, arraySize, i - 1);
        }

        // Extracting maximum element from the binary heap on each step and move it to the sorted array
        while (arraySize > 0) {
            // Extracting next element by moving it to the end
            exchange(array, 0, arraySize - 1);

            // Sinking the binary heap of size - 1 (excluding the extracted element)
            sink(array, --arraySize, 0);
        }
    }

    private static void sink(int[] array, int arraySize, int i) {
        int j = 2 * i;
        while (j < arraySize) {
            if (j + 1 < arraySize && less(array, j, j + 1)) {
                ++j;
            }

            if (less(array, i, j)) {
                exchange(array, i, j);
                i = j;
                j = 2 * i;
            } else {
                break;
            }
        }

    }

    private static void exchange(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static boolean less(int[] array, int i, int j) {
        return array[i] < array[j];
    }

    public static void main(String[] args) {
        final int N = 100;

        int[] array = new int[N];
        for (int i = 0; i < N; ++i) {
            array[i] = i;
        }

        StdRandom.shuffle(array);
        heapSort(array);

        for (int i = 1; i < N; ++i) {
            if (array[i - 1] > array[i]) {
                System.out.println("FAIL!");
                return;
            }
        }

        System.out.println("OK!");
    }
}
