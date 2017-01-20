import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by upokatik on 10.09.16.
 */

/*
public class PercolationVirtualBottom {

    public PercolationVirtualBottom(int n) { // create n-by-n grid, with all sites blocked
        gridDimension = n;

        sites = new boolean[gridDimension * gridDimension + 2]; // +1 for virtual top and +1 for virtual bottom
        sites[0] = true;

        weightedQU = new WeightedQuickUnionUF(gridDimension * gridDimension + 2);
    }

    public void open(int i, int j) { // open site (row i, column j) if it is not open already
        int index = getSiteIndexBy(i, j);
        sites[index] = true;

        //left
        if (j > 1) {
            int leftSiteIndex = getSiteIndexBy(i, j - 1);
            if (sites[leftSiteIndex]) {
                weightedQU.union(index, leftSiteIndex);
            }
        }

        //right
        if (j < gridDimension) {
            int rightSiteIndex = getSiteIndexBy(i, j + 1);
            if (sites[rightSiteIndex]) {
                weightedQU.union(index, rightSiteIndex);
            }
        }

        // top
        if (i == 1) {
            weightedQU.union(0, index); // connect to the virtual top
        }
        else {
            int topSiteIndex = getSiteIndexBy(i - 1, j);
            if (sites[topSiteIndex]) {
                weightedQU.union(index, topSiteIndex);
            }
        }

        // bottom
        if (i == gridDimension) {
            weightedQU.union(index, gridDimension * gridDimension + 1); // connect to the virtual bottom
        }
        else {
            int bottomSiteIndex = getSiteIndexBy(i + 1, j);
            if (sites[bottomSiteIndex]) {
                weightedQU.union(index, bottomSiteIndex);
            }
        }
    }

    public boolean isOpen(int i, int j) { // is site (row i, column j) open?
        int index = getSiteIndexBy(i, j);
        return sites[index];
    }

    public boolean isFull(int i, int j) { // is site (row i, column j) full?
        int index = getSiteIndexBy(i, j);

        if (i == gridDimension) { // backwash fix

        }

        return weightedQU.connected(0, index);
    }

    public boolean percolates() { // does the system percolate?
        return weightedQU.connected(0, gridDimension * gridDimension + 1);
    }

    private int getSiteIndexBy(int i, int j)
    {
        return gridDimension * (i - 1) + j;
    }

    public static void main(String[] args) { // test client (optional)
    }

    private boolean[] sites;
    private int gridDimension;
    private WeightedQuickUnionUF weightedQU;
}
*/