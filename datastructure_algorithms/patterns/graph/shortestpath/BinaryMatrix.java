package datastructure_algorithms.patterns.graph.shortestpath;

//  Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is
//  no clear path, return -1.
//  A clear path in a binary matrix is a path from the top-left cell (0, 0) to the bottom-right cell (n - 1, n - 1) such that:
//      - All the visited cells of the path are 0.
//      - All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an
//      edge or a corner).
//  The length of a clear path is the number of visited cells of this path.

//  You are given:
//  An n x n binary matrix
//  0 -> free cell
//  1 -> blocked cell
//  You need:
//  Shortest path from (0,0) to (n-1,n-1)
//  You can move in 8 directions:
//  up, down, left, right
//  4 diagonals
//  Path length = number of cells visited

//
//  Example 1:
//  Input: [[0,1],[1,0]]
//  Output: 2
//  Explanation: The shortest path is (0,0) -> (1,1).
//
//  Example 2:
//  Input: [[0,0,0],[1,1,0],[1,1,0]]
//  Output: 4
//  Explanation: One shortest path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2).

//  Solution: We want the shortest path in an unweighted grid, Use BFS (not DFS, not Dijkstra). Why BFS Works Here
//  Each move costs the same (1 step)
//  BFS guarantees the shortest path
//  8-directional movement just changes the direction array
//  Time	O(n²)
//  Space	O(n²)

import java.util.LinkedList;
import java.util.Queue;

public class BinaryMatrix {

  public static int shortestPathBinaryMatrix(int[][] grid) {
    int n = grid.length;

    // Edge case
    if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
      return -1;
    }

    // 8 directions
    int[][] dirs = {
        {1, 0}, {-1, 0}, {0, 1}, {0, -1},
        {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
    };

    Queue<int[]> queue = new LinkedList<>();
    queue.offer(new int[]{0, 0, 1}); // row, col, path length
    grid[0][0] = 1; // mark visited

    while (!queue.isEmpty()) {
      int[] curr = queue.poll();
      int r = curr[0];
      int c = curr[1];
      int dist = curr[2];

      if (r == n - 1 && c == n - 1) {
        return dist;
      }

      for (int[] d : dirs) {
        int nr = r + d[0];
        int nc = c + d[1];

        if (nr >= 0 && nc >= 0 && nr < n && nc < n && grid[nr][nc] == 0) {
          grid[nr][nc] = 1; // mark visited
          queue.offer(new int[]{nr, nc, dist + 1});
        }
      }
    }

    return -1;
  }

  public static void main(String[] args) {
    int[][] grid = {
        {0,0,0},
        {1,1,0},
        {1,1,0}
    };

    System.out.println("Shortest Path Length -> " +
        shortestPathBinaryMatrix(grid));
  }

}
