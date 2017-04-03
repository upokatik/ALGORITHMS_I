/**
 * Created by upokatik on 21.03.17.
 */

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.StdRandom;

import java.util.LinkedList;
import java.util.List;

public class Board {
    private int[][] blocks = null;

    private int zeroRow;
    private int zeroColumn;

    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        if (blocks == null) {
            throw new NullPointerException();
        }

        this.blocks = new int[blocks.length][blocks.length];

        for (int row = 0; row < blocks.length; ++row) {
            for (int column = 0; column < blocks.length; ++column) {
                this.blocks[row][column] = blocks[row][column];

                if (blocks[row][column] == 0) {
                    zeroRow = row;
                    zeroColumn = column;
                }
            }
        }
    }

    // board dimension n
    public int dimension() {
        return blocks.length;
    }

    // number of blocks out of place
    public int hamming() {
        int outs = 0;

        for (int row = 0; row < dimension(); ++row) {
            for (int column = 0; column < dimension(); ++column) {
                if (!isZero(row, column) && (row != targetRow(row, column) || column != targetColumn(row, column))) {
                    ++outs;
                }
            }
        }

        return outs;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int sumDistances = 0;

        for (int row = 0; row < dimension(); ++row) {
            for (int column = 0; column < dimension(); ++column) {
                if (!isZero(row, column)) {
                    sumDistances += Math.abs(row - targetRow(row, column));
                    sumDistances += Math.abs(column - targetColumn(row, column));
                }
            }
        }

        return sumDistances;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        Board twin = new Board(this.blocks);

        int row1, column1;

        do {
            row1 = StdRandom.uniform(twin.blocks.length);
            column1 = StdRandom.uniform(twin.blocks.length);
        } while (isZero(row1, column1));

        int row2 = row1;
        int column2 = column1 + 1 < dimension() ? column1 + 1 : column1 - 1;

        Board.swapBlocks(twin, row1, column1, row2, column2);
        return twin;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (!(y instanceof Board)) throw new IllegalArgumentException();

        Board other = (Board) y;

        if (this.zeroRow != other.zeroRow || this.zeroColumn != other.zeroColumn) {
            return false;
        }

        for (int row = 0; row < dimension(); ++row) {
            for (int column = 0; column < dimension(); ++column) {
                if (this.blocks[row][column] != other.blocks[row][column]) {
                    return false;
                }
            }
        }

        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Bag<Board> neighborBoards = new Bag<>();

        if (zeroRow > 0) {
            Board neighborBoard = new Board(this.blocks);
            Board.swapBlocks(neighborBoard, zeroRow, zeroColumn, zeroRow - 1, zeroColumn);
            neighborBoards.add(neighborBoard);
        }
        if (zeroRow + 1 < blocks.length) {
            Board neighborBoard = new Board(this.blocks);
            Board.swapBlocks(neighborBoard, zeroRow, zeroColumn, zeroRow + 1, zeroColumn);
            neighborBoards.add(neighborBoard);
        }

        if (zeroColumn > 0) {
            Board neighborBoard = new Board(this.blocks);
            Board.swapBlocks(neighborBoard, zeroRow, zeroColumn, zeroRow, zeroColumn - 1);
            neighborBoards.add(neighborBoard);
        }
        if (zeroColumn + 1 < blocks.length) {
            Board neighborBoard = new Board(this.blocks);
            Board.swapBlocks(neighborBoard, zeroRow, zeroColumn, zeroRow, zeroColumn + 1);
            neighborBoards.add(neighborBoard);
        }

        return neighborBoards;
    }

    private static void swapBlocks(Board board, int row1, int column1, int row2, int column2) {
        int temp = board.blocks[row1][column1];
        board.blocks[row1][column1] = board.blocks[row2][column2];
        board.blocks[row2][column2] = temp;
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int row = 0; row < dimension(); ++row) {
            for (int column = 0; column < dimension(); ++column) {
                res.append(blocks[row][column]);
                if (column + 1 < blocks[row].length) {
                    res.append(' ');
                }
            }
            res.append('\n');
        }
        return res.toString();
    }

    private int targetRow(int row, int column) {
        return (blocks[row][column] - 1) / dimension();
    }

    private int targetColumn(int row, int column) {
        return (blocks[row][column] - 1) % dimension();
    }

    private boolean isZero(int row, int column) {
        return blocks[row][column] == 0;
    }

    // unit tests (not graded)
    public static void main(String[] args) {
        final int dimension = 3;

        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < dimension * dimension; i++) {
            list.add(i);
        }

        int[][] blocks = new int[dimension][dimension];
        for (int i = 0; i < blocks.length; ++i) {
            for (int j = 0; j < blocks[i].length; ++j) {
                int index = StdRandom.uniform(list.size());
                blocks[i][j] = list.get(index);
                list.remove(index);
            }
        }

        Board board = new Board(blocks);

        System.out.println(board);
        System.out.println("hamming = " + board.hamming());
        System.out.println("manhattan = " + board.manhattan());

        System.out.println("neighborBoards:");
        Iterable<Board> neighborBoards = board.neighbors();
        for (Board neighborBoard : neighborBoards) {
            System.out.println(neighborBoard);
        }
    }
}
