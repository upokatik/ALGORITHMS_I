/**
 * Problem statement: Permutation. Given two integer arrays of size n, design a subquadratic algorithm
 * to determine whether one is a permutation of the other.
 * That is, do they contain exactly the same entries but, possibly, in a different order.
 *
 * @author Dmitry Strebkov
 * <p>
 * Created by upokatik on 02.02.17.
 */

import edu.princeton.cs.algs4.Shell;

public class Permutation {

    /**
     * Perform a permutation check for two arrays
     *
     * @param a first input array
     * @param b second input array
     * @return true if sorted arrays a[] and b[] are element-wise equal, false otherwise
     */
    private static boolean isPermutation(Integer[] a, Integer[] b) {

        if (a.length != b.length) {
            return false;
        }

        Shell.sort(a);
        Shell.sort(b);

        for (int i = 0; i < a.length; ++i) {
            if (!a[i].equals(b[i])) {
                return false;
            }
        }

        return true;
    }

    /**
     * Helper function that performs a kind of unit testing
     *
     * @param a first input array
     * @param b second input array
     * @param r expected result of the permutation check
     * @return true if arrays represent a permutation, false otherwise
     */
    private static boolean verify(Integer[] a, Integer[] b, boolean r) {

        boolean result = isPermutation(a, b);

        return result == r;
    }

    public static void main(String[] args) {

        Integer[] a = null;
        Integer[] b = null;

        // Test 1
        a = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        b = new Integer[]{3, 1, 4, 6, 2, 8, 7, 5, 9, 0};
        System.out.println(verify(a, b, true) ? "OK" : "FAIL");

        // Test 2
        a = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        b = new Integer[]{3, 1, 4, 6, 2, 8, 7, 5, 9, 5};
        System.out.println(verify(a, b, false) ? "OK" : "FAIL");

        // Test 3
        a = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        b = new Integer[]{3, 1, 4, 6, 2, 8, 7, 5, 9};
        System.out.println(verify(a, b, false) ? "OK" : "FAIL");

        // Test 4
        a = new Integer[]{0, 1, 2};
        b = new Integer[]{0, 1, 2};
        System.out.println(verify(a, b, true) ? "OK" : "FAIL");

        // Test 5
        a = new Integer[]{0, 0, 0};
        b = new Integer[]{0, 0, 0};
        System.out.println(verify(a, b, true) ? "OK" : "FAIL");

        // Test 6
        a = new Integer[]{-1, 0, 1};
        b = new Integer[]{1, 0, 1};
        System.out.println(verify(a, b, false) ? "OK" : "FAIL");

        // Test 7
        a = new Integer[]{};
        b = new Integer[]{};
        System.out.println(verify(a, b, true) ? "OK" : "FAIL");

        // Test 8
        a = new Integer[]{1, 1, 1, 1, -1};
        b = new Integer[]{-1, 1, 1, 1, 1};
        System.out.println(verify(a, b, true) ? "OK" : "FAIL");

        // Test 9
        a = new Integer[]{0, 2, 4, 0};
        b = new Integer[]{2, 4, 0, 0};
        System.out.println(verify(a, b, true) ? "OK" : "FAIL");

        // Test 10
        a = new Integer[]{0};
        b = new Integer[]{0};
        System.out.println(verify(a, b, true) ? "OK" : "FAIL");

        System.out.println("Done!");
    }
}
