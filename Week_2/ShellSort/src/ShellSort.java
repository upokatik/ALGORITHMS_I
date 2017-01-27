/**
 * Problem statement: Implement Shell sort.
 *
 * @author Dmitry Strebkov
 * <p>
 * Created by upokatik on 27.01.17.
 */

import edu.princeton.cs.algs4.StdRandom;

public class ShellSort {

    /**
     * Sorts a integer array using Shell sort algorithm
     *
     * @param array an array to be sorted
     */
    private static void shellSort(int[] array) {

        int N = array.length;
        int h = 1;
        while (h < N / 3) {
            h = 3 * h;
        }

        // NOTE: Uncomment to cast Shell sort into Insertion sort
        //h = 1;

        for (; h >= 1; h /= 3) {

            for (int i = h; i < N; ++i) {

                for (int j = i; j >= h && array[j - h] > array[j]; j -= h) {

                    int temp = array[j];
                    array[j] = array[j - h];
                    array[j - h] = temp;

                }

            }

        }
    }

    public static void main(String[] args) {
        int[] array = new int[90000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }

        StdRandom.shuffle(array);

        long startTime = System.currentTimeMillis();
        shellSort(array);
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
