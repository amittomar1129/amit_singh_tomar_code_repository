package datastructure_algorithms.patterns.graph.minimunspanningtree;

//  There are n houses in a village. We want to supply water for all the houses by building wells and laying pipes.
//  For each house i, we can either build a well inside it directly with cost wells[i], or pipe water from another well to it.
//  The costs to lay pipes between houses are given by the array pipes, where pipes[j] = [house1j, house2j, costj] represents
//  the cost to connect house1j and house2j together using a pipe.
//  Return the minimum total cost to supply water to all houses.
//  Note:
//    - You can assume that the village has at least one house.
//    - The pipes array may not connect all houses directly, so building wells is necessary in some cases.

//  Each house has two ways to get water:
//  Build a well directly -> cost = wells[i]
//  Connect via pipe to another house that already has water
//  Model this by introducing a virtual node (node 0):
//  Connect node 0 -> house i with cost wells[i]
//  Pipes remain normal edges between houses
//  Now the problem becomes:
//  Find the MST of a graph with (n + 1) nodes

//  Example 1:
//  Input: n = 3, wells = [1,2,2], pipes = [[1,2,1],[2,3,1]]
//  Output: 3
//  Explanation: Build a well in house 1 with cost 1, then connect house 2 and 3 with pipes costing 1 each, total cost = 3.
//
//  Example 2:
//  Input: n = 2, wells = [1,1], pipes = [[1,2,1]]
//  Output: 2
//  Explanation: Either build wells in both houses with cost 1 each or build a well in one house and pipe to the other house with cost 1. Minimum total cost is 2.
//
//  Example 3:
//  Input: n = 3, wells = [1,2,2], pipes = [[1,2,1],[2,3,1]]
//  Output: 3
//  Explanation: Repeated example as only two official examples are provided.

//  Solution: “I convert well construction into edges from a virtual node with cost equal to the well cost.
//  Then I run Kruskal’s algorithm on this augmented graph.
//  The MST ensures every house gets water at minimum cost.”
//  “Add a virtual node connecting to each house with well cost, then run Kruskal’s MST.”
//  Kruskal’s Algorithm + Union-Find (DSU).
//  Time	O(E log E)
//  Space	O(N)


import java.util.ArrayList;
import java.util.List;

public class MinCostToProvideWater {


  static class UnionFind {

    int[] parent, rank;

    UnionFind(int n) {
      parent = new int[n];
      rank = new int[n];
      for (int i = 0; i < n; i++) {
        parent[i] = i;
      }
    }

    int find(int x) {
      if (parent[x] != x) {
        parent[x] = find(parent[x]);
      }
      return parent[x];
    }

    boolean union(int x, int y) {
      int px = find(x);
      int py = find(y);

      if (px == py) {
        return false;
      }

      if (rank[px] < rank[py]) {
        parent[px] = py;
      } else if (rank[px] > rank[py]) {
        parent[py] = px;
      } else {
        parent[py] = px;
        rank[px]++;
      }
      return true;
    }
  }

  public static int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {

    List<int[]> edges = new ArrayList<>();

    // Virtual node 0 -> house i with cost wells[i-1]
    for (int i = 1; i <= n; i++) {
      edges.add(new int[]{0, i, wells[i - 1]});
    }

    // Pipe edges
    for (int[] p : pipes) {
      edges.add(new int[]{p[0], p[1], p[2]});
    }

    // Sort edges by cost
    edges.sort((a, b) -> a[2] - b[2]);

    UnionFind uf = new UnionFind(n + 1);
    int totalCost = 0;

    for (int[] edge : edges) {
      if (uf.union(edge[0], edge[1])) {
        totalCost += edge[2];
      }
    }

    return totalCost;
  }

  // Main method
  public static void main(String[] args) {

    int n = 3;
    int[] wells = {1, 2, 2};
    int[][] pipes = {{1, 2, 1}, {2, 3, 1}};

    int result = minCostToSupplyWater(n, wells, pipes);
    System.out.println("Minimum cost to supply water = " + result);
  }
}
