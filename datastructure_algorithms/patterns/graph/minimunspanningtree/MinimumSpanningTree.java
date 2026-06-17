package datastructure_algorithms.patterns.graph.minimunspanningtree;

//  You are given an undirected weighted connected graph with n nodes labeled from 0 to n-1 and an array edges
//  where edges[i] = [u_i, v_i, w_i] represents a weighted edge between nodes u_i and v_i with weight w_i.
//  A minimum spanning tree (MST) is a subset of the edges that connects all the vertices together, without
//  any cycles and with the minimum possible total edge weight.
//  Return the total weight of the minimum spanning tree of the given graph.
//
//  Example 1:
//  Input: n = 4, edges = [[0,1,1],[1,2,2],[0,2,3],[2,3,4]]
//  Output: 7
//  Explanation: The MST includes edges (0,1), (1,2), and (2,3) with total weight 1 + 2 + 4 = 7.
//
//  Example 2:
//  Input: n = 3, edges = [[0,1,1],[1,2,1],[0,2,1]]
//  Output: 2
//  Explanation: The MST can be formed by any two edges with weight 1, total weight 2.

//  Solution: Kruskal’s Algorithm + Union-Find (DSU) is the best choice.
//  “Use Kruskal’s algorithm: sort edges by weight and union components while avoiding cycles.”
//  Time	O(E log E)
//  Space	O(N)

import java.util.Arrays;

public class MinimumSpanningTree {

  // Union Find (Disjoint Set)
  static class UnionFind {
    int[] parent;
    int[] rank;

    UnionFind(int n) {
      parent = new int[n];
      rank = new int[n];
      for (int i = 0; i < n; i++) {
        parent[i] = i;
      }
    }

    int find(int x) {
      if (parent[x] != x) {
        parent[x] = find(parent[x]); // path compression
      }
      return parent[x];
    }

    boolean union(int x, int y) {
      int px = find(x);
      int py = find(y);

      if (px == py) return false; // cycle

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

  public static int findMSTWeight(int n, int[][] edges) {

    // Sort edges by weight
    Arrays.sort(edges, (a, b) -> a[2] - b[2]);

    UnionFind uf = new UnionFind(n);
    int totalWeight = 0;
    int edgesUsed = 0;

    for (int[] edge : edges) {
      if (uf.union(edge[0], edge[1])) {
        totalWeight += edge[2];
        edgesUsed++;
        if (edgesUsed == n - 1) break;
      }
    }

    return totalWeight;
  }

  // Main method
  public static void main(String[] args) {

    int n = 4;
    int[][] edges = {
        {0, 1, 1},
        {0, 2, 4},
        {1, 2, 2},
        {1, 3, 5},
        {2, 3, 3}
    };

    int result = findMSTWeight(n, edges);
    System.out.println("Total weight of MST = " + result);
  }
}
