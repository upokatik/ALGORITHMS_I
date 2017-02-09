/**
 * Problem statement: Implement merge sort.
 *
 * @author Dmitry Strebkov
 *         <p>
 *         Created by upokatik on 29.02.17.
 */

import edu.princeton.cs.algs4.StdRandom;

public class MergeSort {

    /**
     * Sorts a integer array using merge sort algorithm
     *
     * @param array an array to be sorted
     */
    private static void mergeSort(int[] array) {

    }

    public static void main(String[] args) {
        int[] array = new int[90000];
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
