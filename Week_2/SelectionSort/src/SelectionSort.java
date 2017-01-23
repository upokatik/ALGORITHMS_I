/**
 * Problem statement: Implement selection sort.
 *
 * @author Dmitry Strebkov
 *         <p>
 *         Created by upokatik on 23.01.17.
 */

public class SelectionSort {

    /**
     * Sorts a integer array using selection sort algorithm
     *
     * @param array an array to be sorted
     */
    private static void selectionSort(int[] array) {
        for (int i = 0; i < array.length; ++i) {

            int min = i;
            for (int j = i + 1; j < array.length; ++j) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }

            int temp = array[i];
            array[i] = array[min];
            array[min] = temp;
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{7, 10, 5, 3, 8, 4, 2, 9, 6};

        selectionSort(array);

        for (int i = 1; i < array.length; ++i) {
            if (array[i] < array[i - 1]) {
                System.out.println("Error: Array isn't sorted.");
                return;
            }
        }

        System.out.println("OK!");
    }
}
