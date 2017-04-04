/******************************************************************************
 *  Compilation:  javac PuzzleChecker.java
 *  Execution:    java PuzzleChecker filename1.txt filename2.txt ...
 *  Dependencies: Board.java Solver.java
 *
 *  This program creates an initial board from each filename specified
 *  on the command line and finds the minimum number of moves to
 *  reach the goal state.
 *
 *  % java PuzzleChecker puzzle*.txt
 *  puzzle00.txt: 0
 *  puzzle01.txt: 1
 *  puzzle02.txt: 2
 *  puzzle03.txt: 3
 *  puzzle04.txt: 4
 *  puzzle05.txt: 5
 *  puzzle06.txt: 6
 *  ...
 *  puzzle3x3-impossible: -1
 *  ...
 *  puzzle42.txt: 42
 *  puzzle43.txt: 43
 *  puzzle44.txt: 44
 *  puzzle45.txt: 45
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PuzzleChecker {

    public static void main(String[] args) {

        String[] puzzles = new String[]
                {
                        "./src/puzzle00.txt",
                        "./src/puzzle01.txt",
                        "./src/puzzle02.txt",
                        "./src/puzzle03.txt",
                        "./src/puzzle04.txt",
                        "./src/puzzle05.txt",
                        "./src/puzzle07.txt",
                        "./src/puzzle08.txt",
                        "./src/puzzle09.txt",
                        "./src/puzzle10.txt",
                        "./src/puzzle11.txt",
                        "./src/puzzle12.txt",
                        "./src/puzzle13.txt",
                        "./src/puzzle14.txt",
                        "./src/puzzle15.txt",
                        "./src/puzzle16.txt",
                        "./src/puzzle17.txt",
                        "./src/puzzle18.txt",
                        "./src/puzzle19.txt",
                        "./src/puzzle20.txt",
                        "./src/puzzle21.txt",
                        "./src/puzzle22.txt",
                        "./src/puzzle23.txt",
                        "./src/puzzle24.txt",
                        "./src/puzzle25.txt",
                        "./src/puzzle26.txt",
                        "./src/puzzle27.txt",
                        "./src/puzzle28.txt",
                        "./src/puzzle29.txt",
                        "./src/puzzle30.txt",
                        "./src/puzzle31.txt",
                        "./src/puzzle32.txt",
                        "./src/puzzle33.txt",
                        "./src/puzzle34.txt",
                        "./src/puzzle35.txt",
                        "./src/puzzle36.txt",
                        "./src/puzzle37.txt",
                        "./src/puzzle38.txt",
                        "./src/puzzle39.txt",
                        "./src/puzzle40.txt",
                        "./src/puzzle41.txt",
                        "./src/puzzle42.txt",
                        "./src/puzzle43.txt",
                        "./src/puzzle44.txt",
                        "./src/puzzle45.txt",
                        "./src/puzzle46.txt",
                        "./src/puzzle47.txt",
                        "./src/puzzle48.txt",
                        "./src/puzzle49.txt",
                        "./src/puzzle50.txt",
                };

        String[] puzzles2x2 = new String[]
                {
                        "./src/puzzle2x2-00.txt",
                        "./src/puzzle2x2-01.txt",
                        "./src/puzzle2x2-02.txt",
                        "./src/puzzle2x2-03.txt",
                        "./src/puzzle2x2-04.txt",
                        "./src/puzzle2x2-05.txt",
                        "./src/puzzle2x2-06.txt",
                };

        String[] puzzles3x3 = new String[]
                {
                        "./src/puzzle3x3-00.txt",
                        "./src/puzzle3x3-01.txt",
                        "./src/puzzle3x3-02.txt",
                        "./src/puzzle3x3-03.txt",
                        "./src/puzzle3x3-04.txt",
                        "./src/puzzle3x3-05.txt",
                        "./src/puzzle3x3-06.txt",
                        "./src/puzzle3x3-07.txt",
                        "./src/puzzle3x3-08.txt",
                        "./src/puzzle3x3-09.txt",
                        "./src/puzzle3x3-10.txt",
                        "./src/puzzle3x3-11.txt",
                        "./src/puzzle3x3-12.txt",
                        "./src/puzzle3x3-13.txt",
                        "./src/puzzle3x3-14.txt",
                        "./src/puzzle3x3-15.txt",
                        "./src/puzzle3x3-16.txt",
                        "./src/puzzle3x3-17.txt",
                        "./src/puzzle3x3-18.txt",
                        "./src/puzzle3x3-19.txt",
                        "./src/puzzle3x3-20.txt",
                        "./src/puzzle3x3-21.txt",
                        "./src/puzzle3x3-22.txt",
                        "./src/puzzle3x3-23.txt",
                        "./src/puzzle3x3-24.txt",
                        "./src/puzzle3x3-25.txt",
                        "./src/puzzle3x3-26.txt",
                        "./src/puzzle3x3-27.txt",
                        "./src/puzzle3x3-28.txt",
                        "./src/puzzle3x3-29.txt",
                        "./src/puzzle3x3-30.txt",
                        "./src/puzzle3x3-31.txt",
                };

        String[] puzzles4x4 = new String[]
                {
                        "./src/puzzle4x4-00.txt",
                        "./src/puzzle4x4-01.txt",
                        "./src/puzzle4x4-02.txt",
                        "./src/puzzle4x4-03.txt",
                        "./src/puzzle4x4-04.txt",
                        "./src/puzzle4x4-05.txt",
                        "./src/puzzle4x4-06.txt",
                        "./src/puzzle4x4-07.txt",
                        "./src/puzzle4x4-08.txt",
                        "./src/puzzle4x4-09.txt",
                        "./src/puzzle4x4-10.txt",
                        "./src/puzzle4x4-11.txt",
                        "./src/puzzle4x4-12.txt",
                        "./src/puzzle4x4-13.txt",
                        "./src/puzzle4x4-14.txt",
                        "./src/puzzle4x4-15.txt",
                        "./src/puzzle4x4-16.txt",
                        "./src/puzzle4x4-17.txt",
                        "./src/puzzle4x4-18.txt",
                        "./src/puzzle4x4-19.txt",
                        "./src/puzzle4x4-20.txt",
                        "./src/puzzle4x4-21.txt",
                        "./src/puzzle4x4-22.txt",
                        "./src/puzzle4x4-23.txt",
                        "./src/puzzle4x4-24.txt",
                        "./src/puzzle4x4-25.txt",
                        "./src/puzzle4x4-26.txt",
                        "./src/puzzle4x4-27.txt",
                        "./src/puzzle4x4-28.txt",
                        "./src/puzzle4x4-29.txt",
                        "./src/puzzle4x4-30.txt",
                        "./src/puzzle4x4-31.txt",
                        "./src/puzzle4x4-32.txt",
                        "./src/puzzle4x4-33.txt",
                        "./src/puzzle4x4-34.txt",
                        "./src/puzzle4x4-35.txt",
                        "./src/puzzle4x4-36.txt",
                        "./src/puzzle4x4-37.txt",
                        "./src/puzzle4x4-38.txt",
                        "./src/puzzle4x4-39.txt",
                        "./src/puzzle4x4-40.txt",
                        "./src/puzzle4x4-41.txt",
                        "./src/puzzle4x4-42.txt",
                        "./src/puzzle4x4-43.txt",
                        "./src/puzzle4x4-44.txt",
                        "./src/puzzle4x4-45.txt",
                        "./src/puzzle4x4-46.txt",
                        "./src/puzzle4x4-47.txt",
                        "./src/puzzle4x4-48.txt",
                        "./src/puzzle4x4-49.txt",
                        "./src/puzzle4x4-50.txt",
                        "./src/puzzle4x4-78.txt",
                        "./src/puzzle4x4-80.txt",
                };

        String[] unsolvable = new String[]
                {
                        "./src/puzzle2x2-unsolvable1.txt",
                        "./src/puzzle2x2-unsolvable2.txt",
                        "./src/puzzle2x2-unsolvable3.txt",
                        "./src/puzzle3x3-unsolvable.txt",
                        "./src/puzzle3x3-unsolvable1.txt",
                        "./src/puzzle3x3-unsolvable2.txt",
                        "./src/puzzle4x4-unsolvable.txt",
                };

        List<String> overall = new ArrayList<String>(puzzles.length + puzzles2x2.length + puzzles3x3.length +
                puzzles4x4.length + unsolvable.length);
        Collections.addAll(overall, puzzles);
        Collections.addAll(overall, puzzles2x2);
        Collections.addAll(overall, puzzles3x3);
        Collections.addAll(overall, puzzles4x4);
        Collections.addAll(overall, unsolvable);

        for (String filename : overall) {

            // read in the board specified in the filename
            In in = new In(filename);
            int n = in.readInt();
            int[][] tiles = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    tiles[i][j] = in.readInt();
                }
            }

            // solve the slider puzzle
            Board initial = new Board(tiles);
            Solver solver = new Solver(initial);
            StdOut.println(filename + ": " + solver.moves());
        }
    }
}
