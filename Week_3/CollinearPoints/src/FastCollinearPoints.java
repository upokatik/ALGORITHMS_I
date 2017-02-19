/**
 * Fast algorithm for Collinear Points detection.
 *
 * @author Dmitry Strebkov
 * <p>
 * Created by upokatik on 14.02.17.
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints {

    private static final int MIN_POINTS_PER_LINE = 4;

    private LineSegment[] lineSegments = null;
    private int lineSegmentsCount = 0;

    /**
     * Finds all line segments containing 4 or more points
     *
     * @param points to be searched for line segments
     */
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException();
        }

        LineSegment[] tempSegments = new LineSegment[points.length];

        for (int i = 0; i < points.length; ++i) {
            Point p = points[i];

            Comparator<Point> comp = p.slopeOrder();
            Arrays.sort(points, comp);
            int err = 0;

            fetchSegments(points, p, tempSegments);
        }
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

    private int fetchSegments(Point[] points, Point point, LineSegment[] tempSegments) {

        int lineSegmentsCount = 0;
        int collinearPointsCount = 0;
        Point[] collinearPoints = new Point[points.length];
        double currentSlope = Double.NEGATIVE_INFINITY;

        for (int i = 0; i < points.length; ++i) {
            Point currentPoint = points[i];
            if (currentPoint == point) {
                continue;
            }

            double slope = point.slopeTo(currentPoint);
            if (slope != currentSlope || currentSlope == Double.NEGATIVE_INFINITY) {

                // New slope

                if (collinearPointsCount >= MIN_POINTS_PER_LINE) {
                    LineSegment segment = new LineSegment(collinearPoints[0], collinearPoints[collinearPointsCount - 1]);
                    tempSegments[lineSegmentsCount++] = segment;
                }

                collinearPointsCount = 0;
                collinearPoints[collinearPointsCount++] = currentPoint;
                currentSlope = slope;
            } else {

                // Continuing with the same slope

                collinearPoints[collinearPointsCount++] = currentPoint;
            }
        }

        return lineSegmentsCount;
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
