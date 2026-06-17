package datastructure_algorithms.patterns.recursion;

//  Write a program to solve a Sudoku puzzle by filling the empty cells. A sudoku solution must
// satisfy all of
//  the following rules: Each of the digits 1-9 must occur exactly once in each row. Each of the
// digits 1-9 must
//  occur exactly once in each column. Each of the digits 1-9 must occur exactly once in each of the
// 9 3x3 sub-boxes
//  of the grid. The '.' character indicates empty cells.
//
//  Example 1:
//  Input:
// [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],
//
// ["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],
//
// [".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
//  Output:
// [["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"],["1","9","8","3","4","2","5","6","7"],
//
// ["8","5","9","7","6","1","4","2","3"],["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],
//
// ["9","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","5","2","8","6","1","7","9"]]
//  Explanation: Example 1: The input represents a Sudoku board. The output represents the solved
// Sudoku board.
//
//      Example 2:
//  Input:
// [[".",".","9","7","4","8",".",".","."],["7",".",".",".",".",".",".",".","."],[".","2",".","1",".","9",".",".","."],
//
// [".",".","7",".",".",".","2","4","."],[".","6","4",".","1",".","5","9","."],[".","9","8",".",".",".","3",".","."],
//
// [".",".",".","8",".","3",".","2","."],[".",".",".",".",".",".",".",".","6"],[".",".",".","2","7","5","9",".","."]]
//  Output:
// [["5","1","9","7","4","8","6","3","2"],["7","8","3","6","5","2","4","1","9"],["4","2","6","1","3","9","8","7","5"],
//
// ["3","5","7","9","8","6","2","4","1"],["2","6","4","3","1","7","5","9","8"],["1","9","8","5","2","4","3","6","7"],
//
// ["9","7","5","8","6","3","1","2","4"],["8","3","2","4","9","1","7","5","6"],["6","4","1","2","7","5","9","8","3"]]
//  Explanation: Example 3: The input represents a Sudoku board. The output represents the solved
// Sudoku

//  Solution: I optimize the backtracking by maintaining boolean arrays for rows, columns, and
// boxes.
//  This reduces validity checks from O(9) to O(1), making the solver much faster in practice.
//  O(9^(empty cells))   (but very fast due to O(1) checks)
//  O(1)

public class SudokuSolver {

  static boolean[][] rows = new boolean[9][9];
  static boolean[][] cols = new boolean[9][9];
  static boolean[][] boxes = new boolean[9][9];

  public static void solveSudoku(char[][] board) {
    // initialize boolean arrays
    for (int r = 0; r < 9; r++) {
      for (int c = 0; c < 9; c++) {
        if (board[r][c] != '.') {
          int num = board[r][c] - '1';
          rows[r][num] = true;
          cols[c][num] = true;
          boxes[getBoxIndex(r, c)][num] = true;
        }
      }
    }
    backtrack(board);
  }

  private static boolean backtrack(char[][] board) {
    for (int r = 0; r < 9; r++) {
      for (int c = 0; c < 9; c++) {
        if (board[r][c] == '.') {
          for (int num = 0; num < 9; num++) {
            int box = getBoxIndex(r, c);
            if (!rows[r][num] && !cols[c][num] && !boxes[box][num]) {
              // place number
              board[r][c] = (char) (num + '1');
              rows[r][num] = cols[c][num] = boxes[box][num] = true;
              if (backtrack(board)) {
                return true;
              }
              // backtrack
              board[r][c] = '.';
              rows[r][num] = cols[c][num] = boxes[box][num] = false;
            }
          }
          return false;
        }
      }
    }
    return true; // solved
  }

  private static int getBoxIndex(int r, int c) {
    return (r / 3) * 3 + (c / 3);
  }

  public static void main(String[] args) {
    char[][] board = {
      {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
      {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
      {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
      {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
      {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
      {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
      {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
      {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
      {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
    };

    solveSudoku(board);

    for (char[] row : board) {
      for (char c : row) {
        System.out.print(c + " ");
      }
      System.out.println();
    }
  }
}
