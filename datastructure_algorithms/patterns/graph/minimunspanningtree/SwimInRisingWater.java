package datastructure_algorithms.patterns.graph.minimunspanningtree;

//  You are given an n x n integer matrix grid where each value grid[i][j] represents the elevation at that point (i, j).
//  The rain starts to fall, and at time t, the depth of the water everywhere is t. You can swim from a square to another
//  4-directionally adjacent square if and only if the elevation of both squares individually are at most t. You can swim
//  infinite distance in zero time.
//  Return the least time until you can reach the bottom right square (n - 1, n - 1) starting from the top left square (0, 0).

//  Water level = t
//  You can move only if both current cell and next cell elevation <= t
//  You want the minimum t such that a path exists from (0,0) to (n-1,n-1)
//  This is NOT sum of weights,
//  This is minimize the maximum elevation encountered on the path
//  This is called a Minimax Path Problem.

//  Example 1:
//  Input: grid = [[0,2],[1,3]]
//  Output: 3
//  Explanation: At time 3, you can swim to the bottom right square.
//
//  Example 2:
//  Input: grid = [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
//  Output: 16
//  Explanation: The least time to reach the bottom right is 16.

//  Solution: Dijkstra Works Here, Dijkstra minimizes sum of weights. We minimize max elevation so far.
//  So instead of: dist[new] = dist[curr] + weight
//  We do: dist[new] = max(dist[curr], grid[new])
//  “I treat each cell as a node. The cost to reach a cell is the maximum elevation seen so far.
//  I use Dijkstra with a priority queue to always expand the path with the minimum possible maximum elevation.”
//  Time	O(N² log N²) -> O(N² log N)
//  Space	O(N²)

//  t represents TIME.
//      At time t:
//      Rain has fallen enough so that
//      Water level everywhere = t

//  A cell (i, j) with elevation grid[i][j]:
//      If grid[i][j] <= t ? cell is underwater / swimmable
//      If grid[i][j] > t ? cell is still above water (blocked)

//  Why t Equals the Answer?
//      To reach the destination:
//      You must wait until water level is high enough
//      High enough means:
//      t >= max elevation on the chosen path
//      So the problem becomes:
//      Find a path where the maximum elevation is minimized
//      That minimum maximum elevation = answer t

import java.util.PriorityQueue;

public class SwimInRisingWater {

  static class Cell {

    int row, col, time;

    Cell(int r, int c, int t) {
      row = r;
      col = c;
      time = t;
    }
  }

  public static int swimInWater(int[][] grid) {
    int n = grid.length;

    boolean[][] visited = new boolean[n][n];

    PriorityQueue<Cell> pq = new PriorityQueue<>(
        (a, b) -> a.time - b.time
    );

    pq.offer(new Cell(0, 0, grid[0][0]));

    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    while (!pq.isEmpty()) {
      Cell curr = pq.poll();

      int r = curr.row;
      int c = curr.col;

      if (visited[r][c]) {
        continue;
      }
      visited[r][c] = true;

      // reached destination
      if (r == n - 1 && c == n - 1) {
        return curr.time;
      }

      for (int[] d : dirs) {
        int nr = r + d[0];
        int nc = c + d[1];

        if (nr >= 0 && nc >= 0 && nr < n && nc < n && !visited[nr][nc]) {
          int newTime = Math.max(curr.time, grid[nr][nc]);
          pq.offer(new Cell(nr, nc, newTime));
        }
      }
    }

    return -1; // should never happen
  }

  // Main method
  public static void main(String[] args) {

    int[][] grid = {
        {0, 2},
        {1, 3}
    };

    int result = swimInWater(grid);
    System.out.println("Minimum time to reach bottom-right = " + result);
  }
}
