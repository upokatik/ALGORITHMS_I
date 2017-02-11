/**
 * Problem statement: Implement bottom-up merge sort.
 *
 * @author Dmitry Strebkov
 * <p>
 * Created by upokatik on 09.02.17.
 */

import edu.princeton.cs.algs4.StdRandom;

public class MergeSortBU {

    /**
     * Function that merges two sorted halves of the array into single sorted array
     * Uses an auxiliary array
     *
     * @param array an array to be sorted
     * @param aux   an auxiliary array used by merging procedure
     * @param low   index of the first element of the first half
     * @param mid   index of the last element of the first half
     * @param high  index of the last element of the second half
     */
    private static void merge(int[] array, int[] aux, int low, int mid, int high) {

        for (int i = low; i <= high; ++i) {
            aux[i] = array[i];
        }

        int i = low;
        int j = mid + 1;
        for (int k = low; k <= high; ++k) {
            if (i > mid) {
                array[k] = aux[j++];
            } else if (j > high) {
                array[k] = aux[i++];
            } else if (aux[i] < aux[j]) {
                array[k] = aux[i++];
            } else {
                array[k] = aux[j++];
            }
        }
    }

    /**
     * Sorts a integer array using a bottom-up merge sort algorithm
     *
     * @param array an array to be sorted
     */
    private static void mergeSortBU(int[] array) {
        int[] aux = new int[array.length];

        for (int len = 1; len < array.length; len *= 2) {

            for (int low = 0; low < array.length; low += 2 * len) {

                int high = low + 2 * len - 1;
                int mid = low + len - 1;
                if (high >= array.length) {
                    high = array.length - 1;
                }

                merge(array, aux, low, mid, high);
            }
        }

        return;
    }

    public static void main(String[] args) {
        int[] array = new int[100000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }

        StdRandom.shuffle(array);

        long startTime = System.currentTimeMillis();
        mergeSortBU(array);
        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("Time: " + elapsedTime / 1000.);

        for (int i = 1; i < array.length; ++i) {
            if (array[i] < array[i - 1]) {
                System.out.println("Error: Array isn't sorted.");
                return;
            }
        }

        System.out.println("OK!");
    }
}
