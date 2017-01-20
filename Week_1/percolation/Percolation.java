import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by upokatik on 10.09.16.
 */

public class Percolation {

    private boolean[] sites;
    private int[] lastRowSites; // indexed by by roots of connected components
    private int gridDimension;
    private int percolationSite;
    private WeightedQuickUnionUF weightedQU;

    public Percolation(int n) { // create n-by-n grid, with all sites blocked
        if (n <= 0) throw new IllegalArgumentException("PercolationStats requires n >= 1");

        gridDimension = n;
        percolationSite = -1;

        sites = new boolean[gridDimension * gridDimension + 1]; // +1 for virtual top
        sites[0] = true;

        lastRowSites = new int[gridDimension * gridDimension + 1]; // +1 for virtual top
        lastRowSites[0] = -1;
        for (int i = 1; i <= gridDimension; ++i) {
            for (int j = 1; j <= gridDimension; ++j) {
                int index = getSiteIndexBy(i, j);
                if (i < gridDimension) {
                    lastRowSites[index] = -1;
                }
                else {
                    lastRowSites[index] = index;
                }
            }
        }

        weightedQU = new WeightedQuickUnionUF(gridDimension * gridDimension + 1);
    }

    public void open(int i, int j) { // open site (row i, column j) if it is not open already
        int index = getSiteIndexBy(i, j);
        sites[index] = true;

        int lastRowSite = -1; // it'll be -1 for all sites except the last row ...
        if (i == gridDimension) {
            lastRowSite = lastRowSites[index]; // ... for the last row it'll be an index of the site
        }

        // Left
        if (j > 1) {
            int leftSiteIndex = getSiteIndexBy(i, j - 1);
            lastRowSite = connectSiteWithNeighbor(index, leftSiteIndex, lastRowSite);
        }

        // Right
        if (j < gridDimension) {
            int rightSiteIndex = getSiteIndexBy(i, j + 1);
            lastRowSite = connectSiteWithNeighbor(index, rightSiteIndex, lastRowSite);
        }

        // Top
        if (i == 1) {
            weightedQU.union(0, index); // connect to the virtual top
        }
        else {
            int topSiteIndex = getSiteIndexBy(i - 1, j);
            lastRowSite = connectSiteWithNeighbor(index, topSiteIndex, lastRowSite);
        }

        // Bottom
        if (i < gridDimension) {
            int bottomSiteIndex = getSiteIndexBy(i + 1, j);
            lastRowSite = connectSiteWithNeighbor(index, bottomSiteIndex, lastRowSite);
        }

        int rootIndex = weightedQU.find(index);
        lastRowSites[rootIndex] = lastRowSite;

        if (percolationSite == -1 &&
            lastRowSite != -1 &&
            weightedQU.connected(0, index)) {
            percolationSite = lastRowSite;
        }
    }

    public boolean isOpen(int i, int j) { // is site (row i, column j) open?
        int index = getSiteIndexBy(i, j);
        return sites[index];
    }

    public boolean isFull(int i, int j) { // is site (row i, column j) full?
        int index = getSiteIndexBy(i, j);
        return weightedQU.connected(0, index);
    }

    public boolean percolates() { // does the system percolate?
        return percolationSite != -1;
    }

    private int getSiteIndexBy(int i, int j)
    {
        if (i <= 0 || j <= 0 || i > gridDimension || j > gridDimension)
            throw new IndexOutOfBoundsException("PercolationStats requires i and j be >= 1 and <= n");
        return gridDimension * (i - 1) + j;
    }

    /**
     * Connects current site referred by siteIndex to the neighbor site referred by neighborIndex (if it is opened)
     * Returns lastRowSite: original if lastRowSite was NOT -1, otherwise lastRowSite of the neighbor site
     */
    private int connectSiteWithNeighbor(int siteIndex, int neighborIndex, int lastRowSite) {
        if (sites[neighborIndex]) {
            if (lastRowSite == -1) {
                int rootOfNeighborSite = weightedQU.find(neighborIndex);
                lastRowSite = lastRowSites[rootOfNeighborSite];
            }

            weightedQU.union(siteIndex, neighborIndex);
        }

        return lastRowSite;
    }

    public static void main(String[] args) { // test client (optional)
    }
}
