package datastructure_algorithms.patterns.graph.minimunspanningtree;

//  You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].
//  The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|,
//  where |val| denotes the absolute value of val.
//  Return the minimum cost to make all points connected. All points are connected if there is exactly
//  one simple path between any two points.

//  Points = nodes
//  Manhattan distance = edge weight
//  Want to connect all nodes with minimum total cost
//  Exactly one simple path between any two nodes
//  MST problem

//  Why NOT Kruskal here?
//  Complete graph -> edges = O(n²)
//  Sorting O(n² log n²) is expensive
//  Prim with O(n²) is optimal and clean.

//  Example 1:
//  Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
//  Output: 20
//  Explanation: One way to connect the points with minimum cost is to connect [0,0] to [2,2], [2,2] to [3,10], [2,2] to [5,2], and [5,2] to [7,0]. The total cost is 2 + 8 + 3 + 7 = 20.
//
//  Example 2:
//  Input: points = [[3,12],[-2,5],[-4,1]]
//  Output: 18
//  Explanation: Connect points as follows: [3,12] to [-2,5] and [-2,5] to [-4,1]. Total cost is 12 + 6 = 18.

//  Solution: “This is an MST problem on a complete graph. I used Prim’s algorithm with Manhattan distance, achieving O(n²) time.”
//  Time	O(N²)
//  Space	O(N)

import java.util.Arrays;

public class MinCostToConnectAllPoints {

  public static int minCostConnectPoints(int[][] points) {
    int n = points.length;

    boolean[] visited = new boolean[n];
    int[] minDist = new int[n];

    Arrays.fill(minDist, Integer.MAX_VALUE);
    minDist[0] = 0; // start from point 0

    int totalCost = 0;

    for (int i = 0; i < n; i++) {
      int curr = -1;

      // pick minimum unvisited point
      for (int j = 0; j < n; j++) {
        if (!visited[j] && (curr == -1 || minDist[j] < minDist[curr])) {
          curr = j;
        }
      }

      visited[curr] = true;
      totalCost += minDist[curr];

      // update distances
      for (int j = 0; j < n; j++) {
        if (!visited[j]) {
          int cost = Math.abs(points[curr][0] - points[j][0]) +
              Math.abs(points[curr][1] - points[j][1]);
          minDist[j] = Math.min(minDist[j], cost);
        }
      }
    }

    return totalCost;
  }

  // Main method
  public static void main(String[] args) {
    int[][] points = {
        {0, 0},
        {2, 2},
        {3, 10},
        {5, 2},
        {7, 0}
    };

    int result = minCostConnectPoints(points);
    System.out.println("Minimum cost to connect all points = " + result);
  }
}
