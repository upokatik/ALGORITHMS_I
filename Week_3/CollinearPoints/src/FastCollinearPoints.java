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

    private LineSegmentMeta[] segmentsMeta = null;
    private int segmentsMetaCount = 0;

    private Point[] sortedPoints = null;
    private int[][] segmentIndicesForPoints = null;

    private class LineSegmentMeta {
        private LineSegment segment;
        private double slope;

        LineSegmentMeta(LineSegment segment, double slope) {
            this.segment = segment;
            this.slope = slope;
        }
    }

    /**
     * Finds all line segments containing 4 or more points
     *
     * @param points to be searched for line segments
     */
    public FastCollinearPoints(Point[] points) {

        sortedPoints = Arrays.copyOfRange(points, 0, points.length);
        Arrays.sort(sortedPoints);

        segmentIndicesForPoints = new int[points.length][points.length];
        Arrays.fill(segmentIndicesForPoints, null);

        Point[] pointsCopy = Arrays.copyOfRange(points, 0, points.length);

        int sqrt = (int) Math.sqrt((double) pointsCopy.length);
        final int dimension = 2 * sqrt * pointsCopy.length;

        segmentsMeta = new LineSegmentMeta[dimension];
        segmentsMetaCount = 0;

        for (Point originPoint : points) {

            Comparator<Point> comp = originPoint.slopeOrder();
            Arrays.sort(pointsCopy, comp);

            fetchSegments(pointsCopy, originPoint);
        }
    }

    /**
     * Returns the number of line segments
     *
     * @return number of line segments
     */
    public int numberOfSegments() {
        return segmentsMetaCount;
    }

    /**
     * Returns the line segments
     *
     * @return line segments
     */
    public LineSegment[] segments() {

        LineSegment[] lineSegments = new LineSegment[segmentsMetaCount];
        for (int i = 0; i < segmentsMetaCount; ++i) {
            lineSegments[i] = segmentsMeta[i].segment;
        }

        return lineSegments;
    }

    /**
     * Fetches line segments that correspond to the current origin point
     *
     * @param points      array of points sorted according to the "slope-with-the-origin" order
     * @param originPoint an origin point
     */
    private void fetchSegments(Point[] points, Point originPoint) {

        Point[] collinearPoints = new Point[points.length];
        int collinearPointsCount = 0;

        collinearPoints[collinearPointsCount++] = originPoint;
        double slope = Double.NEGATIVE_INFINITY;

        for (int i = 1; i < points.length; ++i) {

            Point currentPoint = points[i];

            double newSlope = originPoint.slopeTo(currentPoint);

            if (newSlope == Double.NEGATIVE_INFINITY) {
                throw new IllegalArgumentException();
            }

            if (0 == Double.compare(newSlope, slope)) {

                // Continuing with the same slope
                collinearPoints[collinearPointsCount++] = currentPoint;

            } else {

                // New slope
                handleLineSegmentCandidate(collinearPoints, collinearPointsCount, slope);

                collinearPointsCount = 0;
                collinearPoints[collinearPointsCount++] = originPoint;
                collinearPoints[collinearPointsCount++] = currentPoint;
                slope = newSlope;
            }
        }

        // Check if collinearPoint form a line segment after last iteration
        handleLineSegmentCandidate(collinearPoints, collinearPointsCount, slope);
    }

    /**
     * Handles a line segment candidate to determine if:
     * - it contains enough points to represent a line segment (4+);
     * - it's a line segment not met before
     *
     * @param collinearPoints      points that (probably!) represent a line segment
     * @param collinearPointsCount count of points in "collinearPoints" array
     * @param slope                slope between origin point and points from the "collinearPoints" array
     */
    private void handleLineSegmentCandidate(Point[] collinearPoints, int collinearPointsCount, double slope) {

        if (collinearPointsCount >= MIN_POINTS_PER_LINE) {

            int pointIndex = Arrays.binarySearch(sortedPoints, collinearPoints[0]);

            if (isNewLineSegment(slope, pointIndex)) {

                // Forming new line segment
                Arrays.sort(collinearPoints, 0, collinearPointsCount);

                LineSegment segment = new LineSegment(collinearPoints[0], collinearPoints[collinearPointsCount - 1]);
                segmentsMeta[segmentsMetaCount] = new LineSegmentMeta(segment, slope);

                for (int i = 0; i < collinearPointsCount; ++i) {

                    pointIndex = Arrays.binarySearch(sortedPoints, collinearPoints[i]);

                    int[] segmentsIndices = segmentIndicesForPoints[pointIndex];
                    if (segmentsIndices == null) {
                        segmentIndicesForPoints[pointIndex] = new int[1];
                        segmentIndicesForPoints[pointIndex][0] = segmentsMetaCount;
                    } else {
                        int[] newSegmentIndices = new int[segmentsIndices.length + 1];
                        for (int j = 0; j < segmentsIndices.length; ++j) {
                            newSegmentIndices[j] = segmentsIndices[j];
                        }
                        newSegmentIndices[segmentsIndices.length] = segmentsMetaCount;
                        segmentIndicesForPoints[pointIndex] = newSegmentIndices;
                    }
                }

                ++segmentsMetaCount;
            }
        }
    }

    /**
     * Determines if there already was a line segment with a slope "slope" going through a point
     * indexed as "pointIndex", or not
     *
     * @param slope      slope of the line between point indexed as "pointIndex" and origin point
     * @param pointIndex index of the point
     * @return true if there was no line segment from point indexed as "pointIndex" with slope "slope", false otherwise
     */
    private boolean isNewLineSegment(double slope, int pointIndex) {

        int[] segmentsIndices = segmentIndicesForPoints[pointIndex];
        if (segmentsIndices == null) {
            return true;
        }

        for (int segmentIndex : segmentsIndices) {

            LineSegmentMeta segmentMeta = segmentsMeta[segmentIndex];

            if (0 == Double.compare(segmentMeta.slope, slope)) {
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
