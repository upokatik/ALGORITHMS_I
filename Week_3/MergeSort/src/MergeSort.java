/**
 * Problem statement: Implement merge sort.
 *
 * @author Dmitry Strebkov
 * <p>
 * Created by upokatik on 29.02.17.
 */

import edu.princeton.cs.algs4.StdRandom;

public class MergeSort {

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
            aux[i] = array[i]; // backing up "array" into "aux", later on "array" will contain sorted merged array
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
     * Recursive sorting function that performs sorting of halves and merging them together
     *
     * @param array an array to be sorted
     * @param aux   an auxiliary array used by merging procedure
     * @param low   index of the first element of the array
     * @param high  index of the last element of the array
     */
    private static void sort(int[] array, int[] aux, int low, int high) {

        if (high <= low) {
            return;
        }

        int mid = low + (high - low) / 2;

        sort(array, aux, low, mid);
        sort(array, aux, mid + 1, high);

        if (array[mid] < array[mid + 1]) {
            // The array is sorted now, therefore merging is excessive
            return;
        }

        merge(array, aux, low, mid, high);
    }

    /**
     * Sorts a integer array using merge sort algorithm
     *
     * @param array an array to be sorted
     */
    private static void mergeSort(int[] array) {
        int[] aux = new int[array.length];

        sort(array, aux, 0, array.length - 1);
    }

    public static void main(String[] args) {
        int[] array = new int[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }

        StdRandom.shuffle(array);

        long startTime = System.currentTimeMillis();
        mergeSort(array);
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
