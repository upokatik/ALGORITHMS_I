/**
 * Brute Force algorithm for Collinear Points detection.
 *
 * @author Dmitry Strebkov
 * <p>
 * Created by upokatik on 14.02.17.
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class BruteCollinearPoints {

    private static final int MIN_POINTS_PER_LINE = 4;

    private LineSegment[] lineSegments = null;
    private int lineSegmentsCount = 0;

    /**
     * Finds all line segments containing 4 points
     *
     * @param points to be searched for line segments
     */
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException();
        }

        LineSegment[] tempSegments = new LineSegment[points.length];

        for (int i = 0; i < points.length; ++i) {
            for (int j = i + 1; j < points.length; ++j) {

                if (0 == points[i].compareTo(points[j])) {
                    throw new IllegalArgumentException();
                }

                for (int k = j + 1; k < points.length; ++k) {
                    for (int l = k + 1; l < points.length; ++l) {

                        if (areCollinear(points[i], points[j], points[k], points[l])) {
                            LineSegment segment = new LineSegment(points[i], points[l]);
                            tempSegments[lineSegmentsCount++] = segment;
                        }

                    }
                }
            }
        }

        lineSegments = new LineSegment[lineSegmentsCount];
        lineSegments = Arrays.copyOfRange(tempSegments, 0, lineSegmentsCount);
    }

    /**
     * Returns the number of line segments
     *
     * @return number of line segments
     */
    public int numberOfSegments() {
        return lineSegmentsCount;
    }

    /**
     * Returns the line segments
     *
     * @return line segments
     */
    public LineSegment[] segments() {
        return lineSegments;
    }

    /**
     * Checks if points passed as parameters are collinear
     *
     * @param a first point
     * @param b second point
     * @param c third point
     * @param d fourth point
     * @return true if points are collinear, false otherwise
     */
    private boolean areCollinear(Point a, Point b, Point c, Point d) {

        double abSlope = a.slopeTo(b);
        double acSlope = a.slopeTo(c);
        double adSlope = a.slopeTo(d);

        return abSlope == acSlope && abSlope == adSlope;
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
