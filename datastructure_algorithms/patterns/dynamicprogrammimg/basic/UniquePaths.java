package datastructure_algorithms.patterns.dynamicprogrammimg.basic;

//  A robot is located at top-left corner of a m x n grid. The robot can only move either down or
// right
//  at any point in time. The robot is trying to reach the bottom-right corner of the grid.
//  How many possible unique paths are there?
//
//  Example 1:
//  Input: m = 3, n = 7
//  Output: 28
//  Explanation: From the top-left corner, the robot can only move right or down. There are 28
// unique paths to the bottom-right corner.
//
//  Example 2:
//  Input: m = 3, n = 2
//  Output: 3
//  Explanation: From the top-left corner, the robot can move right, down, down to reach the
// bottom-right corner. There are 3 unique paths.

//  Solution: At each cell, the robot can come either from the top or from the left, so the number
// of
//  paths is the sum of those two. Since each state only depends on the current and previous column,
//  I optimized the DP from 2D to 1D, achieving O(n) space.
//  Time	O(m · n)
//  Space	O(n) ?

// Mathematics Formula:
//  C(N,r) = N! / r! (N-r)!    , where N = total no of items, r = total no of item chosen

//  You must move:
//  Down = (m - 1) times
//  Right = (n - 1) times
//  So total moves:  (m-1) + (n-1)= m+n-2
//  Number of Down moves: m-1
//  Number of Right moves: n-1
//  So total required moves: Math.Min((m-1),(n-1))
//  N = (m+n-2),   r = (m-1) or (n-1)

public class UniquePaths {

  public static int uniquePaths(int m, int n) {
    // dp[j] represents number of ways to reach current cell in column j
    int[] dp = new int[n];

    // First row has only one way
    for (int i = 0; i < n; i++) {
      dp[i] = 1;
    }

    // Fill DP row by row
    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        dp[j] = dp[j] + dp[j - 1];
      }
    }

    return dp[n - 1];
  }

  public static void main(String[] args) {

    System.out.println("Unique paths (3x7) -> " + sol1(3, 7)); // 28
    System.out.println("Unique paths (3x2) -> " + sol1(3, 2)); // 3
    System.out.println("Unique paths (7x3) -> " + sol1(7, 3)); // 28
    System.out.println("Unique paths (3x3) -> " + sol1(3, 3)); // 6


    System.out.println(
        "Unique paths in All Direction (3x3) -> " + sol2AllDirection2(3, 3)); // 12
    System.out.println(
        "Unique paths in All Direction (3x3) -> " + uniquePathsWithAllDirection(3, 3)); // 12
  }

  public static int sol1(int m, int n) {
    int[] dp = new int[n];

    for(int i = 0; i < n; i++) {
      dp[i] = 1;
    }

    for(int i = 1; i < m; i++) {
      for(int j = 1; j < n; j++) {
        dp[j] = dp[j] + dp[j-1];
      }
    }

    return dp[n-1];
  }

  public static int sol2AllDirection2(int m, int n) {
    count = 0;
    boolean[][] visited = new boolean[m][n];

    solRec(0,0, m, n, visited);

    return count;
  }

  public static void solRec(int i, int j, int m, int n,boolean[][] visited) {
    if (i < 0 || j < 0 || i >= m || j >= n || visited[i][j]) {
      return;
    }

    if (i == m-1 && j == n-1) {
      count++;
      return;
    }

    visited[i][j] = true;

    solRec(i-1, j, m, n, visited);
    solRec(i, j-1, m, n, visited);
    solRec(i+1, j, m, n, visited);
    solRec(i, j+1, m, n, visited);

    visited[i][j] = false;

  }








  // Allowed to visit all direction without revisiting a cell,
  // Only Correct Approach -> Backtracking + DFS

  //    Solution:   “If revisiting is allowed, number of paths becomes infinite.
  //    If revisiting is not allowed, counting simple paths in a graph is exponential time and
  //    cannot be optimized to polynomial time.”
  //    Time complexity:  O(4^(m*n))

  static int count = 0;

  public static int uniquePathsWithAllDirection(int m, int n) {
    boolean[][] visited = new boolean[m][n];
    dfs(0, 0, m, n, visited);
    return count;
  }

  private static void dfs(int r, int c, int m, int n, boolean[][] visited) {

    if (r < 0 || c < 0 || r >= m || c >= n || visited[r][c]) {
      return;
    }

    if (r == m - 1 && c == n - 1) {
      count++;
      return;
    }

    visited[r][c] = true;

    dfs(r + 1, c, m, n, visited); // down
    dfs(r - 1, c, m, n, visited); // up
    dfs(r, c + 1, m, n, visited); // right
    dfs(r, c - 1, m, n, visited); // left

    visited[r][c] = false; // backtrack
  }
}
