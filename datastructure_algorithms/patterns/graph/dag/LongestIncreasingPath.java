package datastructure_algorithms.patterns.graph.dag;

//  Given an m x n integers matrix, return the length of the longest increasing path in matrix.
//  From each cell, you can either move in four directions: left, right, up, or down. You may not
//  move diagonally or move outside the boundary (i.e., wrap-around is not allowed).
//  The longest increasing path is defined as a path where each consecutive cell's value is strictly
//  greater than the previous cell's value.
//
//  Example 1:
//  Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
//  Output: 4
//  Explanation: The longest increasing path is [1, 2, 6, 9].
//
//  Example 2:
//  Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
//  Output: 4
//  Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.

//  Solution: “I treat the matrix as a DAG where edges go from smaller to larger values.
//  I run DFS from each cell and memoize the result so that each cell is computed once.”
//  “Use DFS with memoization to compute the longest increasing path starting from each cell.”
//  Memoization = remembering results
//  Time	O(M * N)
//  Space	O(M * N)

public class LongestIncreasingPath {

  static int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};
  static int m, n;

  public static int longestIncreasingPath(int[][] matrix) {
    if (matrix == null || matrix.length == 0) return 0;

    m = matrix.length;
    n = matrix[0].length;

    int[][] dp = new int[m][n];
    int maxLen = 0;

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        maxLen = Math.max(maxLen, dfs(matrix, i, j, dp));
      }
    }

    return maxLen;
  }

  private static int dfs(int[][] matrix, int r, int c, int[][] dp) {
    if (dp[r][c] != 0) return dp[r][c];

    int max = 1; // path length includes this cell

    for (int[] d : dirs) {
      int nr = r + d[0];
      int nc = c + d[1];

      if (nr >= 0 && nc >= 0 && nr < m && nc < n &&
          matrix[nr][nc] > matrix[r][c]) {

        max = Math.max(max, 1 + dfs(matrix, nr, nc, dp));
      }
    }

    dp[r][c] = max;
    return max;
  }

  // Main method
  public static void main(String[] args) {

    int[][] matrix = {
        {9, 9, 4},
        {6, 6, 8},
        {2, 1, 1}
    };

    int result = longestIncreasingPath(matrix);
    System.out.println("Longest Increasing Path Length = " + result);
  }
}
