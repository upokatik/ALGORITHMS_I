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
        return;
    }

    public static void main(String[] args) {
        final int N = 10;

        int[] array = new int[N];
        for (int i = 0; i < N; ++i) {
            array[i] = i + 1;
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
