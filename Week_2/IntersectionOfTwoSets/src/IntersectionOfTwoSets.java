/**
 * Problem statement: Intersection of two sets. Given two arrays 𝚊[] and 𝚋[],
 * each containing n distinct 2D points in the plane, design a subquadratic algorithm
 * to count the number of points that are contained both in array 𝚊[] and array 𝚋[].
 *
 * @author Dmitry Strebkov
 * <p>
 * Created by upokatik on 02.02.17.
 */

import edu.princeton.cs.algs4.Shell;
import edu.princeton.cs.algs4.StdRandom;

public class IntersectionOfTwoSets {

    public static void main(String[] args) {
        Integer[] array = new Integer[90000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }

        StdRandom.shuffle(array);

        long startTime = System.currentTimeMillis();
        Shell.sort(array);
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
