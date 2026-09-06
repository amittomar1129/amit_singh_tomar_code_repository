package datastructure_algorithms.patterns.graph.connectedcomponents;

//  Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
//  An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
//  You may assume all four edges of the grid are all surrounded by water.
//  Examples:
//
//  Example 1:
//  Input: [["1","1","1","1","0"],["1","1","0","1","0"],["1","1","0","0","0"],["0","0","0","0","0"]]
//  Output: 1
//  Explanation: There is one island in the grid.
//
//      Example 2:
//  Input: [["1","1","0","0","0"],["1","1","0","0","0"],["0","0","1","0","0"],["0","0","0","1","1"]]
//  Output: 3
//  Explanation: There are three islands in the grid.

//  Solution: This is a connected components problem on a grid.
//    Every island can be discovered by starting from a land cell and visiting all connected land cells using DFS or BFS.
//    Once visited, we mark it so it’s not counted again.
//  Time	O(m * n)
//  Space	O(m * n) in worst case (DFS recursion stack)


public class NumberOfIslands {


  public static int numIslands(char[][] graph) {
    if (graph == null || graph.length == 0) {
      return 0;
    }

    int m = graph.length;
    int n = graph[0].length;
    int islands = 0;

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (graph[i][j] == '1') {
          islands++;
          dfs(graph, i, j);
        }
      }
    }
    return islands;
  }

  private static void dfs(char[][] grid, int i, int j) {
    // boundary check
    if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) {
      return;
    }

    // stop if water or already visited
    if (grid[i][j] == '0') {
      return;
    }

    // mark as visited
    grid[i][j] = '0';

    // explore 4 directions
    dfs(grid, i + 1, j);
    dfs(grid, i - 1, j);
    dfs(grid, i, j + 1);
    dfs(grid, i, j - 1);
  }

  public static void main(String[] args) {

    char[][] grid = {
        {'1', '1', '0', '0', '0'},
        {'1', '1', '0', '0', '0'},
        {'0', '0', '1', '0', '0'},
        {'0', '0', '0', '1', '1'}
    };
    System.out.println("Number of Islands -> " + numIslands(grid));

    char[][] grid1 = {
        {'1', '1', '0', '0', '0'},
        {'1', '1', '0', '0', '0'},
        {'0', '0', '1', '0', '0'},
        {'0', '0', '0', '1', '1'}
    };
    System.out.println("Number of Islands -> " + numIslandsWithDiagonal(grid1));

    char[][] grid2 = {
        {'1', '1', '0', '0', '0'},
        {'1', '1', '0', '0', '0'},
        {'0', '0', '1', '0', '0'},
        {'0', '0', '0', '1', '1'}
    };
    System.out.println("Largest Islands -> " + maxIslandArea(grid2));
  }

  //    Time	O(m * n)
//    Space	O(m * n)
  public static int numIslandsWithDiagonal(char[][] grid) {
    if (grid == null || grid.length == 0) {
      return 0;
    }

    int m = grid.length;
    int n = grid[0].length;
    int islands = 0;

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == '1') {
          islands++;
          dfsWithDiagonal(grid, i, j);
        }
      }
    }
    return islands;
  }

  private static void dfsWithDiagonal(char[][] grid, int row, int col) {
    // Boundary check
    if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length) {
      return;
    }

    // Stop if water or already visited
    if (grid[row][col] == '0') {
      return;
    }

    // Mark current cell as visited
    grid[row][col] = '0';

    // 8 possible directions (including diagonals)
    int[][] directions = {
        {1, 0}, {-1, 0}, {0, 1}, {0, -1},
        {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
    };

    for (int[] dir : directions) {
      dfsWithDiagonal(grid, row + dir[0], col + dir[1]);
    }
  }

  //    Time	O(m * n)
//    Space	O(m * n)
  public static int maxIslandArea(char[][] grid) {
    if (grid == null || grid.length == 0) {
      return 0;
    }

    int m = grid.length;
    int n = grid[0].length;
    int maxArea = 0;

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == '1') {
          int area = dfsMaxIslandArea(grid, i, j);
          maxArea = Math.max(maxArea, area);
        }
      }
    }
    return maxArea;
  }

  private static int dfsMaxIslandArea(char[][] grid, int row, int col) {
    if (row < 0 || col < 0 ||
        row >= grid.length || col >= grid[0].length ||
        grid[row][col] == '0') {
      return 0;
    }

    // Stop if water or already visited
    if (grid[row][col] == '0') {
      return 0;
    }

    // mark visited
    grid[row][col] = '0';

    int area = 1; // current cell

    // explore 4 directions
    area += dfsMaxIslandArea(grid, row + 1, col);
    area += dfsMaxIslandArea(grid, row - 1, col);
    area += dfsMaxIslandArea(grid, row, col + 1);
    area += dfsMaxIslandArea(grid, row, col - 1);

    return area;
  }

}
