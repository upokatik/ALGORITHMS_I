/**
 * Problem statement: Implement quick selection algorithm.
 *
 * @author Dmitry Strebkov
 * <p>
 * Created by upokatik on 01.03.17.
 */

import edu.princeton.cs.algs4.StdRandom;

public class QuickSelect {

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
            while (i < high && array[i] < array[low]) {
                ++i;
            }

            while (array[j] > array[low]) {
                --j;
            }

            if (j <= i) {
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
     * Selection function that performs partition-based search for the k-th smallest element in the array.
     *
     * @param array    an array to be sorted
     * @param keyIndex represents they k in k-th smallest element selection task
     */
    private static int select(int[] array, int keyIndex) {

        int result = -1;

        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            int pivot = partition(array, low, high);
            if (pivot > keyIndex) {
                high = pivot - 1;
            } else if (pivot < keyIndex) {
                low = pivot + 1;
            } else {
                result = pivot;
                break;
            }
        }

        return result;
    }

    /**
     * Performs quick selection to find the k-th smallest element in the array
     *
     * @param array    an array to be sorted
     * @param keyIndex represents they k in k-th smallest element selection task
     */
    private static int quickSelect(int[] array, int keyIndex) {

        StdRandom.shuffle(array);
        int selectedIndex = select(array, keyIndex);
        return array[selectedIndex];
    }

    /**
     * Verification function to test if quick selection works properly.
     *
     * @param array    an array that should be searched for k-th smallest element
     * @param keyIndex represents they k in k-th smallest element selection task
     */
    private static void verify(int[] array, int keyIndex) {

        int elementByIndex = quickSelect(array, keyIndex);
        if (elementByIndex != keyIndex + 1) {
            System.out.println("Error: Selected element is not correct for 'keyIndex' = " + keyIndex);
        }
    }

    public static void main(String[] args) {
        int[] array = new int[90000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }

        StdRandom.shuffle(array);

        verify(array, array.length - 1);
        verify(array, 0);
        verify(array, 1);
        verify(array, 2);
        verify(array, 4);
        verify(array, 8);
        verify(array, 100);
        verify(array, 1000);
        verify(array, 65536);

        System.out.println("OK!");
    }
}