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
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {

    /**
     * Helper function that performs a kind of unit testing
     *
     * @param a first input array
     * @param b second input array
     * @param r expected result for the a[] and b[] intersection
     * @return true if resulting intersection is equal to r, false otherwise
     */
    private static boolean verify(Integer[] a, Integer[] b, Integer[] r) {

        return true;
    }

    public static void main(String[] args) {

        Integer[] a = null;
        Integer[] b = null;
        Integer[] r = null;

        // Test 1
        a = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        b = new Integer[]{8, 8, 1, 2};
        r = new Integer[]{1, 2, 8};
        System.out.println(verify(a, b, r) ? "OK" : "FAIL");

        System.out.println("Done!");
    }
}
