/**
 * Problem statement: Implement Quick 3 Way sorting algorithm
 *
 * @author Dmitry Strebkov
 * <p>
 * Created by upokatik on 06.03.17.
 */

import edu.princeton.cs.algs4.StdRandom;

public class Quick3Way {

    /**
     * Exchanges two elements in the array
     *
     * @param array  an array to be sorted
     * @param index1 index of the first element
     * @param index2 of the second element
     */
    private static void exchange(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    /**
     * Performs Quick 3 Way sorting of the array
     *
     * @param array an array to be sorted
     */
    private static void quick3WaySort(int[] array, int center) {

        int lower = 0;
        int higher = array.length - 1;
        int i = 0;
        while (true) {
            if (i == higher) {
                break;
            }

            if (array[i] < center) {
                // Lower than center
                exchange(array, lower++, i++);
            } else if (array[i] > center) {
                // Higher than center
                exchange(array, i, higher--);
            } else {
                // Equal to center
                ++i;
            }
        }
    }

    /**
     * Verification function to test if array was properly 3-way sorted
     *
     * @param array an array that should be verified if it is sorted
     */
    private static void verify(int[] array, int center) {

        quick3WaySort(array, center);

        int valueIncreasings = 0;
        for (int i = 1; i < array.length; ++i) {
            if (array[i - 1] < array[i]) {
                if (valueIncreasings > 2) {
                    System.out.println("Error: Array is not 3-way sorted.");
                    return;
                }

                ++valueIncreasings;
            }
        }

        System.out.println("OK!");
    }

    public static void main(String[] args) {
        int[] array = new int[90000];
        final int n = 3;
        for (int i = 0; i < array.length; i++) {
            array[i] = StdRandom.uniform(n);
        }

        StdRandom.shuffle(array);

        verify(array, n / 3);
    }
}
