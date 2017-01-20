/**
 * Created by upokatik on 10.09.16.
 */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {

    private double[] percolationThresholds;

    public PercolationStats(int n, int trials) { // perform trials independent experiments on an n-by-n grid
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException("PercolationStats requires n and trials values.");

        percolationThresholds = new double[trials];
        for (int t = 0; t < trials; ++t) {
            Percolation perc = new Percolation(n);
            double openedSitesCount = 0.;
            while (!perc.percolates()) {
                int i, j;
                do {
                    i = StdRandom.uniform(n) + 1;
                    j = StdRandom.uniform(n) + 1;

                } while (perc.isOpen(i, j));

                perc.open(i, j);
                ++openedSitesCount;
            }

            percolationThresholds[t] = openedSitesCount / Math.pow(n, 2);
        }
    }

    public double mean() { // sample mean of percolation threshold
        double mean = 0.;
        for (double v : percolationThresholds) {
            mean += v;
        }
        mean /= percolationThresholds.length;
        return mean;
    }

    public double stddev() { // sample standard deviation of percolation threshold
        double mean = mean();
        double sum = 0.;
        for (double v : percolationThresholds) {
            double temp = v - mean;
            sum += Math.pow(temp, 2);
        }
        double stddev = sum / (percolationThresholds.length - 1);
        return Math.sqrt(stddev);
    }

    public double confidenceLo() { // low  endpoint of 95% confidence interval
        double mean = mean();
        double stddev = stddev();
        return mean - 1.96 * stddev / Math.sqrt(percolationThresholds.length);
    }

    public double confidenceHi() { // high endpoint of 95% confidence interval
        double mean = mean();
        double stddev = stddev();
        return mean + 1.96 * stddev / Math.sqrt(percolationThresholds.length);
    }

    public static void main(String[] args) { // test client (described below)
        if (args.length < 2) throw new IllegalArgumentException("PercolationStats requires n and trials values.");

        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(n, trials);
        StdOut.println("mean                    = " + stats.mean());
        StdOut.println("stddev                  = " + stats.stddev());
        StdOut.println("95% confidence interval = " + stats.confidenceLo() + ", " + stats.confidenceHi());
    }
}
