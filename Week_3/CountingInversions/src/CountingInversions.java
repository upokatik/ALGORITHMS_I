/**
 * Problem statement: Counting inversions.
 * An inversion in an array a[] is a pair of entries a[i] and a[j] such that i<j, but a[i]>a[j].
 * Given an array, design a linearithmic algorithm to count the number of inversions.
 *
 * @author Dmitry Strebkov
 * <p>
 * Created by upokatik on 11.02.17.
 */

import edu.princeton.cs.algs4.StdRandom;

public class CountingInversions {

    /**
     * Internal class created as an adapter of Java's "long" primitive.
     * Created to make "long" mutable.
     */
    private static class MutableLong {
        /**
         * An adaptee: "long" value
         */
        private long value;

        /**
         * Constructor.
         *
         * @param n Initialization value.
         */
        MutableLong(int n) {
            value = n;
        }

        /**
         * Increases the stored value by the value of the parameter.
         *
         * @param value a value that the stored value should be increased by.
         */
        void increaseBy(long value) {
            this.value += value;
        }

        /**
         * Returns stored value of long variable "value"
         *
         * @return stored value
         */
        long getValue() {
            return value;
        }
    }

    /**
     * Function that merges two sorted halves of the array into single sorted array
     * Uses an auxiliary array
     *
     * @param array           an array to be sorted
     * @param aux             an auxiliary array used by merging procedure
     * @param low             index of the first element of the first half
     * @param high            index of the last element of the second half
     * @param inversionsCount inversions counter
     */
    private static void mergeWithInversionsCounting(int[] array, int[] aux, int low, int high, MutableLong inversionsCount) {

        for (int i = low; i <= high; ++i) {
            aux[i] = array[i];
        }

        int mid = low + (high - low) / 2;

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
                // Handling inversion case
                inversionsCount.increaseBy(mid - i + 1);
                array[k] = aux[j++];
            }
        }
    }

    /**
     * Merge-sort's recursive sorting function.
     * Performs sorting of the two halves of the array, merges them into one, counts a number of inversions
     * during the process.
     *
     * @param array           an array to be sorted
     * @param aux             an auxiliary array used by merging procedure
     * @param low             index of the first element of the first half
     * @param high            index of the last element of the second half
     * @param inversionsCount inversions counter
     */
    private static void sortWithInversionsCounting(int[] array, int[] aux, int low, int high, MutableLong inversionsCount) {

        if (low >= high) {
            return;
        }

        int mid = low + (high - low) / 2;
        sortWithInversionsCounting(array, aux, low, mid, inversionsCount);
        sortWithInversionsCounting(array, aux, mid + 1, high, inversionsCount);

        mergeWithInversionsCounting(array, aux, low, high, inversionsCount);
    }

    /**
     * Sorts an integer array using a merge sort algorithm,
     * counts number of inversions in the array.
     *
     * @param array an array to be sorted and analyzed for a number of inversions
     */
    private static MutableLong sortWithInversionsCounting(int[] array) {
        int[] aux = new int[array.length];

        MutableLong inversionsCount = new MutableLong(0);
        sortWithInversionsCounting(array, aux, 0, array.length - 1, inversionsCount);
        return inversionsCount;
    }

    /**
     * Performs counting of inversion using two-cycled O(n^2) algorithm.
     * Function is designed for testing purposes only.
     *
     * @param array an array that should be analyzed for a number of inversions
     * @return a number of inversion
     */
    private static long bruteForceInversionsCounting(int[] array) {
        long inversionsCount = 0;
        for (int i = 0; i < array.length; ++i) {
            for (int j = i + 1; j < array.length; ++j) {
                if (array[i] > array[j]) {
                    ++inversionsCount;
                }
            }
        }

        return inversionsCount;
    }

    public static void main(String[] args) {
        int[] array = new int[100000];
        for (int i = 0; i < array.length; ++i) {
            array[i] = i + 1;
        }

        StdRandom.shuffle(array);

        long bruteForceInversionsCount = bruteForceInversionsCounting(array);

        long startTime = System.currentTimeMillis();
        MutableLong inversionsCount = sortWithInversionsCounting(array);
        long elapsedTime = System.currentTimeMillis() - startTime;

        for (int i = 1; i < array.length; ++i) {
            if (array[i] < array[i - 1]) {
                System.out.println("Error: Array isn't sorted.");
                return;
            }
        }

        if (bruteForceInversionsCount != inversionsCount.getValue()) {
            System.out.println("Error: Wrong inversions count.");
            return;
        }

        System.out.println("Inversions count: " + inversionsCount.getValue());
        System.out.println("Time: " + elapsedTime / 1000.);

        System.out.println("OK!");
    }
}
