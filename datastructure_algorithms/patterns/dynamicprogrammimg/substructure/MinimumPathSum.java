package datastructure_algorithms.patterns.dynamicprogrammimg.substructure;

//  Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right,
//  which minimizes the sum of all numbers along its path. You can only move either down or right at
// any point in time.
//
//  Example 1:
//  Input: [[1,3,1],[1,5,1],[4,2,1]]
//  Output: 7
//  Explanation: Explanation: 1 -> 3 -> 1 -> 1 -> 1 = 7
//
//  Example 2:
//  Input: [[1,2,3],[4,5,6]]
//  Output: 12
//  Explanation: Explanation: 1 -> 2 -> 3 -> 6 = 12

//  Solution: This is a grid DP problem where each cell’s minimum cost depends on the minimum of the
//  top and left cells.
//  Using a 1D DP array optimizes space to O(n) while maintaining O(m*n) time.
//  Time Complexity -> O(m * n)
//  Space Complexity -> O(n) (optimized DP)

//  Note: If it is allowed to go in any direction Left, Right, Up, Down then it become graph's
//  shortest path problem, we can use Dijkstra.

public class MinimumPathSum {

  public static int minPathSum(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

    int[] dp = new int[n];

    // Initialize starting cell
    dp[0] = grid[0][0];

    // Fill first row
    for (int i = 1; i < n; i++) {
      dp[i] = dp[i - 1] + grid[0][i];
    }

    // Fill remaining rows
    for (int i = 1; i < m; i++) {
      dp[0] = dp[0] + grid[i][0]; // first column
      for (int j = 1; j < n; j++) {
        dp[j] = grid[i][j] + Math.min(dp[j], dp[j - 1]);
      }
    }

    return dp[n - 1];
  }

  //  time:   O(m × n)
  //  space:  O(m × n)
  public int minPathSumApproach(int[][] grid) {

    int m = grid.length;
    int n = grid[0].length;

    int[][] dp = new int[m][n];

    dp[0][0] = grid[0][0];

    // first row
    for (int i = 1; i < m; i++) {
      dp[i][0] = dp[i - 1][0] + grid[i][0];
    }

    // first column
    for (int j = 1; j < n; j++) {
      dp[0][j] = dp[0][j - 1] + grid[0][j];
    }

    // fill rest
    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
      }
    }

    return dp[m - 1][n - 1];
  }

  public static void main(String[] args) {
    int[][] grid = {
      {1, 3, 1},
      {1, 5, 1},
      {4, 2, 1}
    };

    int result = sol(grid);
    System.out.println("Minimum Path Sum -> " + result);
  }

  public static int sol(int[][] input) {
    int m = input.length;
    int n = input[0].length;
    int[][] dp = new int[m][n];
    dp[0][0] = input[0][0];

    for (int i = 1; i < m; i++) {
      dp[i][0] = input[i][0] + dp[i-1][0];
    }
    for (int i = 1; i < n; i++) {
      dp[0][i] = input[0][i] + dp[0][i-1];
    }

    for(int i = 1; i < m; i++) {
      for(int j = 1; j < n; j++) {
          dp[i][j] = input[i][j] +  Math.min(dp[i-1][j], dp[i][j-1]);
      }
    }

    return dp[m-1][n-1];
  }
}
