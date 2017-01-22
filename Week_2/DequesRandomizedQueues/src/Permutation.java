import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Problem statement: Write a client program Permutation.java that takes a command-line integer k;
 * reads in a sequence of strings from standard input using StdIn.readString();
 * and prints exactly k of them, uniformly at random.
 * Print each item from the sequence at most once.
 * You may assume that 0 ≤ k ≤ n, where n is the number of string on standard input.
 *
 * @author Dmitry Strebkov
 *         <p>
 *         Created by upokatik on 21.01.17.
 */

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            String string = StdIn.readString();
            randomizedQueue.enqueue(string);
        }

        for (int i = 0; i < k; ++i) {
            StdOut.println(randomizedQueue.dequeue());
        }
    }
}
