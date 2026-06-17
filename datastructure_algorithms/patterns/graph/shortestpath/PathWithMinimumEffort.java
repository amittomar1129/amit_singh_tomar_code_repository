package datastructure_algorithms.patterns.graph.shortestpath;

//  You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns,
//  where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0),
//  and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left,
//  or right, and you wish to find a route that minimizes the maximum absolute difference in heights between two consecutive
//  cells of the route.
//  Return the minimum effort required to travel from the top-left cell to the bottom-right cell.

//    You have a grid of heights
//    You start at (0,0) and want to reach (rows-1, cols-1)
//    You can move up, down, left, right
//    Moving from one cell to another has a cost: effort = abs(height[current] - height[next])
//    The total cost of a path is: the maximum effort of any single step along the path.
//    You want to minimize this maximum step effort.

//  Example 1:
//  Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
//  Output: 2
//  Explanation: The route with the minimum effort is [1,3,5,3,5]. The maximum absolute difference in heights is 2.
//
//  Example 2:
//  Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
//  Output: 1
//  Explanation: The route with the minimum effort is [1,2,3,4,5]. The maximum absolute difference in heights is 1.
//
//  Example 3:
//  Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
//  Output: 0
//  Explanation: The route with the minimum effort is along the leftmost column and bottom row with all heights equal,
//  so the maximum absolute difference is 0.

//  Solution: This is NOT: sum of efforts or total distance.
//  This is a minimize the maximum edge weight along a path.
//  This is a classic Minimax Path problem.
//  Why Dijkstra Works Here? Even though the cost is unusual, Dijkstra still works because:
//  The effort to reach a cell is monotonic.
//  Once we find a path with smaller maximum effort, it’s always better.
//  Modified cost relaxation: newEffort = max(currentEffort, abs(height[curr] - height[next]))
//  Time	O(R * C * log(R * C))
//  Space	O(R * C)   R=Row, C=Column

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class PathWithMinimumEffort {

  public static int minimumEffortPath(int[][] heights) {
    int rows = heights.length;
    int cols = heights[0].length;

    int[][] effort = new int[rows][cols];
    for (int[] row : effort) {
      Arrays.fill(row, Integer.MAX_VALUE);
    }

    // Min-heap: effort, row, col
    PriorityQueue<int[]> pq =
        new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

    pq.offer(new int[]{0, 0, 0}); // effort, row, col
    effort[0][0] = 0;

    int[][] dirs = {
        {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };

    while (!pq.isEmpty()) {
      int[] curr = pq.poll();
      int currEffort = curr[0];
      int r = curr[1];
      int c = curr[2];

      if (r == rows - 1 && c == cols - 1) {
        return currEffort;
      }

      if (currEffort > effort[r][c]) {
        continue;
      }

      for (int[] d : dirs) {
        int nr = r + d[0];
        int nc = c + d[1];

        if (nr >= 0 && nc >= 0 && nr < rows && nc < cols) {
          int stepEffort =
              Math.abs(heights[r][c] - heights[nr][nc]);
          int newEffort =
              Math.max(currEffort, stepEffort);

          if (newEffort < effort[nr][nc]) {
            effort[nr][nc] = newEffort;
            pq.offer(new int[]{newEffort, nr, nc});
          }
        }
      }
    }

    return 0;
  }

  public static void main(String[] args) {
    int[][] heights = {
        {1, 2, 2},
        {3, 8, 2},
        {5, 3, 5}
    };

    System.out.println("Minimum Effort -> " + minimumEffortPath(heights));
  }
}
