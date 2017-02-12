/**
 * Problem statement: Merging with smaller auxiliary array.
 * Suppose that the subarray 𝚊[𝟶] to 𝚊[𝚗−𝟷] is sorted  * and the subarray 𝚊[𝚗] to 𝚊[𝟸∗𝚗−𝟷] is sorted.
 * How can you merge the two subarrays so that 𝚊[𝟶] to 𝚊[𝟸∗𝚗−𝟷] is sorted
 * using an auxiliary array of length n (instead of 2n)?
 *
 * @author Dmitry Strebkov
 * <p>
 * Created by upokatik on 11.02.17.
 */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Merge;

public class MergingWithSmallerArray {

    private static void merge(Integer[] array) {
        int n = array.length / 2;
        Integer[] aux = new Integer[n];
        for (int i = 0; i < n; ++i) {
            aux[i] = array[i];
        }

        int i = 0; // indexing aux from 0 to n
        int j = n; // indexing second half of the array from n to 2*n - 1
        for (int k = 0; k < 2 * n; ++k) {
            if (i >= n) {
                array[k] = array[j++];
            } else if (j >= 2 * n) {
                array[k] = aux[i++];
            } else if (array[j] < aux[i]) {
                array[k] = array[j++];
            } else {
                array[k] = aux[i++];
            }
        }
    }

    public static void main(String[] args) {
        int n = 100000;
        Integer[] firstHalf = new Integer[n];
        Integer[] secondHalf = new Integer[n];
        for (int i = 0; i < n; i++) {
            firstHalf[i] = StdRandom.uniform(2 * n);
            secondHalf[i] = StdRandom.uniform(2 * n);
        }

        Merge.sort(firstHalf);
        Merge.sort(secondHalf);

        Integer[] array = new Integer[2 * n];
        for (int i = 0; i < n; ++i) {
            array[i] = firstHalf[i];
        }

        for (int i = n; i < 2 * n; ++i) {
            array[i] = secondHalf[i - n];
        }

        merge(array);

        for (int i = 1; i < array.length; ++i) {
            if (array[i] < array[i - 1]) {
                System.out.println("Error: Array isn't sorted.");
                return;
            }
        }

        System.out.println("OK!");
    }
}
