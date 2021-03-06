/**
 * Problem statement: Implement insertion sort.
 *
 * @author Dmitry Strebkov
 *         <p>
 *         Created by upokatik on 24.01.17.
 */

public class InsertionSort {

    /**
     * Sorts a integer array using insertion sort algorithm
     *
     * @param array an array to be sorted
     */
    private static void insertionSort(int[] array) {
        for (int i = 0; i < array.length; ++i) {

            for (int j = i; j > 0; --j) {
                if (array[j - 1] > array[j]) {
                    int temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{7, 10, 5, 3, 8, 4, 2, 9, 6};

        insertionSort(array);

        for (int i = 1; i < array.length; ++i) {
            if (array[i] < array[i - 1]) {
                System.out.println("Error: Array isn't sorted.");
                return;
            }
        }

        System.out.println("OK!");
    }
}
