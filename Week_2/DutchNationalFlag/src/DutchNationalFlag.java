/**
 * Problem statement: Dutch national flag. Given an array of n buckets, each containing a red, white, or blue pebble,
 * sort them by color. The allowed operations are:
 * <p>
 * swap(i,j): swap the pebble in bucket i with the pebble in bucket j.
 * color(i): color of pebble in bucket i.
 * The performance requirements are as follows:
 * <p>
 * At most n calls to color().
 * At most n calls to swap().
 * Constant extra space.
 *
 * @author Dmitry Strebkov
 * <p>
 * Created by upokatik on 02.02.17.
 */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class DutchNationalFlag {

    enum Color {RED, WHITE, BLUE}

    private Color[] colors = null;

    /**
     * Constructs DutchNationalFlag class.
     *
     * @param singleFlagColorSize represents a size (in buckets) of one colored part of a flag
     */
    DutchNationalFlag(int singleFlagColorSize) {

        if (singleFlagColorSize <= 0) {
            throw new IllegalArgumentException("Single flag color's size could not be negative or zero.");
        }

        colors = new Color[3 * singleFlagColorSize];
        Color currentColor = Color.RED;

        for (int i = 0; i < 3 * singleFlagColorSize; ++i) {
            colors[i] = currentColor;

            if (i + 1 == singleFlagColorSize) {
                currentColor = Color.WHITE;
            } else if (i + 1 == 2 * singleFlagColorSize) {
                currentColor = Color.BLUE;
            }
        }


        StdRandom.shuffle(colors);
    }

    /**
     * Turns Color[] array into dutch national flag
     * The function uses at most Color[].length calls to color(),
     * and at most Color[].length calls to swap().
     *
     * @return Color[] array that represents a dutch national flag.
     */
    Color[] turnColorsIntoFlag() {

        System.out.println(Arrays.toString(colors));

        int blueIndex = 0;
        int redIndex = colors.length - 1;
        while (blueIndex < redIndex) {
            if (color(blueIndex).equals(Color.RED)) {
                ++blueIndex;
            }
            else if (color(blueIndex).equals(Color.BLUE)) {
                swap(blueIndex, blueIndex + 1);
            }

            if (!color(redIndex).equals(Color.RED)) {
                --redIndex;
            }

            if (color(blueIndex).equals(Color.BLUE) && color(redIndex).equals(Color.RED)) {
                swap(blueIndex++, redIndex--);
            }
        }

        System.out.println(Arrays.toString(colors));

        return colors;
    }

    /**
     * Returns color of a bucket
     *
     * @param i index of a bucket
     * @return Color of a buckets
     */
    private Color color(int i) {

        if (i >= colors.length || i < 0) {
            throw new ArrayIndexOutOfBoundsException("Index is out of bounds.");
        }

        return colors[i];
    }

    /**
     * Swaps colors of two buckets
     *
     * @param i first bucket index
     * @param j second bucket index
     */
    private void swap(int i, int j) {

        if (i >= colors.length || i < 0) {
            throw new ArrayIndexOutOfBoundsException("Index 'i' is out of bounds.");
        }
        if (j >= colors.length || j < 0) {
            throw new ArrayIndexOutOfBoundsException("Index 'j' is out of bounds.");
        }

        Color temp = colors[i];
        colors[i] = colors[j];
        colors[j] = temp;
    }

    /**
     * Helper function that performs a kind of unit testing
     *
     * @param colors Color[] array that is expected to represent a dutch national flag
     * @return true if colors parameter does represent dutch national flag, false otherwise
     */
    private boolean verify(Color[] colors) {

        if (colors == null || colors.length < 3) {
            return false;
        }

        int colorChangesCount = 0;
        for (int i = 1; i < colors.length; ++i) {
            if (!colors[i - 1].equals(colors[i])) {
                ++colorChangesCount;
            }
        }

        return colorChangesCount == 2 && colors[0].equals(Color.RED) && colors[colors.length - 1].equals(Color.BLUE);
    }

    public static void main(String[] args) {

        DutchNationalFlag flagFormatter = null;
        Color[] flag = null;
/*
        // Test 1
        flagFormatter = new DutchNationalFlag(1);
        flag = flagFormatter.turnColorsIntoFlag();
        System.out.println(flagFormatter.verify(flag) ? "OK" : "FAIL");
*/
        // Test 2
        flagFormatter = new DutchNationalFlag(3);
        flag = flagFormatter.turnColorsIntoFlag();
        System.out.println(flagFormatter.verify(flag) ? "OK" : "FAIL");

        if (!flagFormatter.verify(flag)) {
            int err = 0;
        }
/*
        // Test 3
        flagFormatter = new DutchNationalFlag(5);
        flag = flagFormatter.turnColorsIntoFlag();
        System.out.println(flagFormatter.verify(flag) ? "OK" : "FAIL");

        // Test 4
        flagFormatter = new DutchNationalFlag(10);
        flag = flagFormatter.turnColorsIntoFlag();
        System.out.println(flagFormatter.verify(flag) ? "OK" : "FAIL");

        // Test 5
        flagFormatter = new DutchNationalFlag(50);
        flag = flagFormatter.turnColorsIntoFlag();
        System.out.println(flagFormatter.verify(flag) ? "OK" : "FAIL");

        // Test 6
        flagFormatter = new DutchNationalFlag(100);
        flag = flagFormatter.turnColorsIntoFlag();
        System.out.println(flagFormatter.verify(flag) ? "OK" : "FAIL");

        // Test 7
        flagFormatter = new DutchNationalFlag(1000);
        flag = flagFormatter.turnColorsIntoFlag();
        System.out.println(flagFormatter.verify(flag) ? "OK" : "FAIL");

        // Test 8
        flagFormatter = new DutchNationalFlag(10000);
        flag = flagFormatter.turnColorsIntoFlag();
        System.out.println(flagFormatter.verify(flag) ? "OK" : "FAIL");

        // Test 9
        flagFormatter = new DutchNationalFlag(100000);
        flag = flagFormatter.turnColorsIntoFlag();
        System.out.println(flagFormatter.verify(flag) ? "OK" : "FAIL");

        // Test 10
        flagFormatter = new DutchNationalFlag(1000000);
        flag = flagFormatter.turnColorsIntoFlag();
        System.out.println(flagFormatter.verify(flag) ? "OK" : "FAIL");
*/
        System.out.println("Done!");
    }
}
