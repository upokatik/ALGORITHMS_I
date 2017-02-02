/**
 * Problem statement: Intersection of two sets. Given two arrays 𝚊[] and 𝚋[],
 * each containing n distinct integers, design a subquadratic algorithm
 * to count the number of integers that are contained both in array 𝚊[] and array 𝚋[].
 *
 * @author Dmitry Strebkov
 * <p>
 * Created by upokatik on 02.02.17.
 */

import edu.princeton.cs.algs4.Shell;
import edu.princeton.cs.algs4.StdRandom;

public class IntersectionOfTwoSets {

    /**
     * Sorts a integer array using Shell sort algorithm
     *
     * @param a first input array
     * @param b second input array
     * @return array that represents an intersection of a[] and b[]
     */
    private static Integer[] getIntersection(Integer[] a, Integer[] b) {

        if (a == null || b == null) {
            return new Integer[0];
        }

        // Sorting both of the arrays using Shell sort (subquadratic time complexity)
        Shell.sort(a);
        Shell.sort(b);

        Integer[] smaller = null;
        Integer[] bigger = null;

        if (a.length > b.length) {
            smaller = b;
            bigger = a;
        } else {
            smaller = a;
            bigger = b;
        }

        Integer[] intermediateResult = new Integer[smaller.length];
        Integer r = 0;
        Integer pos = 0;

        // Linear-time walk through both arrays in parallel
        for (int i = 0; i < smaller.length; ++i) {

            for (; pos < bigger.length && bigger[pos] <= smaller[i]; ++pos) {

                if (smaller[i].equals(bigger[pos])) {

                    intermediateResult[r++] = smaller[i];
                    ++pos;
                    break;
                }
            }
        }

        // Copying intersection to array of exact size of non-null elements
        Integer[] result = new Integer[r];
        for (int i = 0; i < r; ++i) {
            result[i] = intermediateResult[i];
        }

        intermediateResult = null;

        return result;
    }

    /**
     * Helper function that performs a kind of unit testing
     *
     * @param a first input array
     * @param b second input array
     * @param r expected result for the a[] and b[] intersection
     * @return true if resulting intersection is equal to r, false otherwise
     */
    private static boolean verify(Integer[] a, Integer[] b, Integer[] r) {

        StdRandom.shuffle(a);
        StdRandom.shuffle(b);

        Integer[] intersection = getIntersection(a, b);

        if (intersection.length != r.length) {
            return false;
        }

        for (int i = 0; i < r.length; ++i) {
            if (!intersection[i].equals(r[i])) {
                return false;
            }
        }

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

        // Test 2
        a = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        b = new Integer[]{0, 8};
        r = new Integer[]{0, 8};
        System.out.println(verify(a, b, r) ? "OK" : "FAIL");

        // Test 3
        a = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        b = new Integer[]{-1, 1, 2, 7};
        r = new Integer[]{1, 2, 7};
        System.out.println(verify(a, b, r) ? "OK" : "FAIL");

        // Test 4
        a = new Integer[]{4, 5, 6, 7, 8, 9};
        b = new Integer[]{4, 100, -100, 4, 7};
        r = new Integer[]{4, 7};
        System.out.println(verify(a, b, r) ? "OK" : "FAIL");

        // Test 5
        a = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        b = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        r = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(verify(a, b, r) ? "OK" : "FAIL");

        // Test 6
        a = new Integer[]{0};
        b = new Integer[]{0};
        r = new Integer[]{0};
        System.out.println(verify(a, b, r) ? "OK" : "FAIL");

        // Test 7
        a = new Integer[]{0};
        b = new Integer[]{1};
        r = new Integer[]{};
        System.out.println(verify(a, b, r) ? "OK" : "FAIL");

        // Test 8
        a = new Integer[]{0, 1, 4};
        b = new Integer[]{44, 1, 0};
        r = new Integer[]{0, 1};
        System.out.println(verify(a, b, r) ? "OK" : "FAIL");

        // Test 9
        a = new Integer[]{-1, -5};
        b = new Integer[]{1, 2, 3, -1, 7, -5, -5, -5};
        r = new Integer[]{-5, -1};
        System.out.println(verify(a, b, r) ? "OK" : "FAIL");

        // Test 10
        a = new Integer[]{0, 0, 0, 7, 8, 9};
        b = new Integer[]{0, 0, 1, 2, 3, 4, 5, 6};
        r = new Integer[]{0, 0};
        System.out.println(verify(a, b, r) ? "OK" : "FAIL");

        System.out.println("Done!");
    }
}
