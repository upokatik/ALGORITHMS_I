/**
 * Created by upokatik on 21.03.17.
 */

import edu.princeton.cs.algs4.*;

public class Solver {
    private LinkedStack<Board> solution = null;

    private class SearchNode implements Comparable {
        private Board board = null;
        private int moves;
        private SearchNode successor = null;

        SearchNode(Board board, int moves, SearchNode successor) {
            this.board = board;
            this.moves = moves;
            this.successor = successor;
        }

        @Override
        public int compareTo(Object o) {
            if (!(o instanceof SearchNode)) throw new IllegalArgumentException();

            SearchNode other = (SearchNode) o;

            int thisPriority = this.board.manhattan() + this.moves;
            int otherPriority = other.board.manhattan() + other.moves;

            return Integer.compare(thisPriority, otherPriority);
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) {
            throw new NullPointerException();
        }

        solution = null;

        MinPQ<SearchNode> initialsPQ = new MinPQ<>();
        MinPQ<SearchNode> twinsPQ = new MinPQ<>();

        initialsPQ.insert(new SearchNode(initial, 0, null));
        twinsPQ.insert(new SearchNode(initial.twin(), 0, null));

        while (true) {

            // Handling initial board

            SearchNode searchNodeFromInitials = initialsPQ.delMin();

            if (searchNodeFromInitials.board.isGoal()) {
                formSolutionBySearchNode(searchNodeFromInitials);
                break;
            }

            Iterable<Board> initialsNeighbors = searchNodeFromInitials.board.neighbors();
            for (Board neighbor : initialsNeighbors) {
                SearchNode neighborSearchNode = new SearchNode(
                        neighbor, searchNodeFromInitials.moves + 1, searchNodeFromInitials);
                initialsPQ.insert(neighborSearchNode);
            }

            // Handling twin board

            SearchNode searchNodeFromTwins = twinsPQ.delMin();

            if (searchNodeFromTwins.board.isGoal()) {
                break;
            }

            Iterable<Board> twinsNeighbors = searchNodeFromTwins.board.neighbors();
            for (Board neighbor : twinsNeighbors) {
                SearchNode neighborSearchNode = new SearchNode(
                        neighbor, searchNodeFromTwins.moves + 1, searchNodeFromTwins);
                twinsPQ.insert(neighborSearchNode);
            }
        }
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return solution != null;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (solution == null) {
            return -1;
        }

        return solution.size();
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        return solution;
    }

    private void formSolutionBySearchNode(SearchNode searchNodeFromInitials) {
        solution = new LinkedStack<>();

        while (searchNodeFromInitials.successor != null) {
            solution.push(searchNodeFromInitials.board);
            searchNodeFromInitials = searchNodeFromInitials.successor;
        }
    }

    // solve a slider puzzle (given below)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
