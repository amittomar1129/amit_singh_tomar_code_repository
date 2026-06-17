package datastructure_algorithms.patterns.graph.connectedcomponents;

//  Given an m x n matrix board containing 'X' and 'O', capture all regions that are 4-directionally surrounded by 'X'.
//  A region is captured by flipping all 'O's into 'X's in that surrounded region.

//  X X X X
//  X O O X
//  X X O X
//  X O X X
//  Rules:
//    'O' = open region
//    'X' = wall
//    Only 4-directional connections count (up, down, left, right)
//    An 'O' region is captured only if it is completely surrounded by X
//    Any 'O' connected to the border can NEVER be captured, Because water can “escape” through the boundary.
//    Only 'O' not connected to boundary should be flipped

//  Example 1:
//  Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
//  Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
//  Explanation: Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the
//  board are not flipped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the
//  border will be flipped to 'X'. Two cells are connected if they are adjacent cells connected horizontally or vertically.
//
//  Example 2:
//  Input: board = [["X"]]
//  Output: [["X"]]
//  Explanation: The board contains only one cell with 'X', so no changes are made.
//
//  Example 3:
//  Input: board = [["O"]]
//  Output: [["O"]]
//  Explanation: The board contains only one cell with 'O' on the border, so it is not flipped.

//  Solution: We mark all boundary-connected 'O's as safe, then flip the rest.
//  Initial board:
//  X X X X
//  X O O X
//  X X O X
//  X O X X
//
//  Boundary 'O's:
//  X X X X
//  X O O X
//  X X O X
//  X S X X   (S = safe)
//
//  Final board:
//  X X X X
//  X X X X
//  X X X X
//  X O X X

//  Time	O(m * n)
//  Space	O(m * n) worst-case recursion

public class SurroundedRegions {

  public static void solve(char[][] board) {
    if (board == null || board.length == 0) {
      return;
    }

    int m = board.length;
    int n = board[0].length;

    // Step 1 -> Mark boundary connected 'O's
    for (int i = 0; i < m; i++) {
      dfs(board, i, 0);
      dfs(board, i, n - 1);
    }

    for (int j = 0; j < n; j++) {
      dfs(board, 0, j);
      dfs(board, m - 1, j);
    }

    // Step 2 -> Flip and restore
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (board[i][j] == 'O') {
          board[i][j] = 'X';      // captured
        } else if (board[i][j] == '#') {
          board[i][j] = 'O';      // restore safe
        }
      }
    }
  }

  private static void dfs(char[][] board, int r, int c) {
    if (r < 0 || c < 0 ||
        r >= board.length || c >= board[0].length ||
        board[r][c] != 'O') {
      return;
    }

    board[r][c] = '#'; // mark safe

    dfs(board, r + 1, c);
    dfs(board, r - 1, c);
    dfs(board, r, c + 1);
    dfs(board, r, c - 1);
  }

  // For testing
  public static void main(String[] args) {
    char[][] board = {
        {'X', 'X', 'X', 'X'},
        {'X', 'O', 'O', 'X'},
        {'X', 'X', 'O', 'X'},
        {'X', 'O', 'X', 'X'}
    };

    solve(board);

    for (char[] row : board) {
      for (char c : row) {
        System.out.print(c + " ");
      }
      System.out.println();
    }
  }
}
