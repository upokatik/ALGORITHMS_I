/**
 * Problem statement: Implement quick sort.
 *
 * @author Dmitry Strebkov
 * <p>
 * Created by upokatik on 01.03.17.
 */

import edu.princeton.cs.algs4.StdRandom;

public class QuickSort {

    /**
     * Performs partitioning in quick sort algorithm.
     *
     * @param array an array to be sorted
     * @param low   index of the first element of the array
     * @param high  index of the last element of the array
     */
    private static int partition(int[] array, int low, int high) {

        int i = low + 1;
        int j = high;
        while (true) {
            while (array[i] < array[low] && i < high) ++i;

            while (array[j] > array[low]) --j;

            if (i >= j) {
                break;
            }

            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

        int temp = array[j];
        array[j] = array[low];
        array[low] = temp;
        return j;
    }

    /**
     * Sorting function that performs partitioning and recursively calls itself to
     * perform sorting on the resulting halves.
     *
     * @param array an array to be sorted
     * @param low   index of the first element of the array
     * @param high  index of the last element of the array
     */
    private static void sort(int[] array, int low, int high) {

        if (low >= high) {
            return;
        }

        int pivot = partition(array, low, high);

        sort(array, low, pivot - 1);
        sort(array, pivot + 1, high);
    }

    /**
     * Sorts a integer array using quick sort algorithm.
     *
     * @param array an array to be sorted
     */
    private static void quickSort(int[] array) {

        StdRandom.shuffle(array);
        sort(array, 0, array.length - 1);
    }

    public static void main(String[] args) {
        int[] array = new int[90000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }

        StdRandom.shuffle(array);

        long startTime = System.currentTimeMillis();
        quickSort(array);
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
