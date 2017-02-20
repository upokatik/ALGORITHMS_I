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
import edu.princeton.cs.algs4.Queue;

import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints {

    private static final int MIN_POINTS_PER_LINE = 4;

    private LineSegment[] lineSegments = null;

    private LineSegment[] tempSegments = null;
    private LineSegmentMeta[] tempSegmentsMeta = null;
    private int tempSegmentsCount = 0;

    private class LineSegmentMeta {
        double slope;
        Point bottomLeftPoint;

        LineSegmentMeta(double slope, Point bottomLeftPoint) {
            this.slope = slope;
            this.bottomLeftPoint = bottomLeftPoint;
        }
    }

    /**
     * Finds all line segments containing 4 or more points
     *
     * @param points to be searched for line segments
     */
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException();
        }

        Queue<Point> queue = new Queue<>();
        for (Point point : points) {
            queue.enqueue(point);
        }

        tempSegments = new LineSegment[points.length];
        tempSegmentsMeta = new LineSegmentMeta[points.length];

        while (!queue.isEmpty()) {
            Point p = queue.dequeue();

            Comparator<Point> comp = p.slopeOrder();
            Arrays.sort(points, comp);

            fetchSegments(points, p);
        }

        lineSegments = new LineSegment[tempSegmentsCount];
        for (int i = 0; i < tempSegmentsCount; ++i) {
            lineSegments[i] = tempSegments[i];
        }
    }

    /**
     * Returns the number of line segments
     *
     * @return number of line segments
     */
    public int numberOfSegments() {
        return lineSegments.length;
    }

    /**
     * Returns the line segments
     *
     * @return line segments
     */
    public LineSegment[] segments() {
        return lineSegments;
    }

    private void fetchSegments(Point[] points, Point originPoint) {

        Point[] collinearPoints = new Point[points.length];
        int collinearPointsCount = 0;

        collinearPoints[collinearPointsCount++] = originPoint;
        double slope = Double.NEGATIVE_INFINITY;

        for (int i = 1; i < points.length; ++i) {

            Point currentPoint = points[i];

            double newSlope = originPoint.slopeTo(currentPoint);

            if (newSlope == slope) {

                // Continuing with the same slope
                collinearPoints[collinearPointsCount++] = currentPoint;

            } else {

                // New slope
                handleLineSegmentCandidate(collinearPoints, collinearPointsCount, slope);

                collinearPointsCount = 1;
                collinearPoints[collinearPointsCount++] = currentPoint;
                slope = newSlope;
            }
        }

        // Check if collinearPoint form a line segment after last iteration
        handleLineSegmentCandidate(collinearPoints, collinearPointsCount, slope);
    }

    private void handleLineSegmentCandidate(Point[] collinearPoints, int collinearPointsCount, double slope) {

        if (collinearPointsCount >= MIN_POINTS_PER_LINE) {

            Point first = collinearPoints[0];
            Point second = collinearPoints[1];
            Point last = collinearPoints[collinearPointsCount - 1];

            Point segmentStart = first;
            Point segmentEnd = last;

            if (first.compareTo(second) > 0) {
                segmentStart = second;
            }
            if (first.compareTo(last) > 0) {
                segmentEnd = first;
            }

            if (isNewLineSegment(slope, segmentStart)) {

                // Forming new line segment
                LineSegment segment = new LineSegment(segmentStart, segmentEnd);
                tempSegments[tempSegmentsCount] = segment;

                LineSegmentMeta meta = new LineSegmentMeta(slope, segmentStart);
                tempSegmentsMeta[tempSegmentsCount] = meta;

                ++tempSegmentsCount;
            }

        }
    }

    private boolean isNewLineSegment(double slope, Point startPoint) {

        for (int i = 0; i < tempSegmentsCount; ++i) {

            LineSegmentMeta segmentMeta = tempSegmentsMeta[i];

            if (segmentMeta.slope == slope && startPoint == segmentMeta.bottomLeftPoint) {
                return false;
            }
        }

        return true;
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
