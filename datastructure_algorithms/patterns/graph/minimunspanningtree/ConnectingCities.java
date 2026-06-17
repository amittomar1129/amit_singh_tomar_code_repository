package datastructure_algorithms.patterns.graph.minimunspanningtree;

//  There are n cities numbered from 1 to n. You are given connections, where each connections[i] = [city1, city2, cost]
//  represents the cost to connect city1 and city2 together. (A connection is bidirectional: connecting city1 and city2
//  is the same as connecting city2 and city1.)
//  Return the minimum cost so that for every pair of cities, there exists a path of connections (possibly of length 1)
//  that connects those two cities together. The cost is the sum of the connections' costs used.
//  If it is impossible to connect all the n cities, return -1.

//  Cities = nodes
//  Connections = undirected weighted edges
//  Want:
//  All cities connected
//  Minimum total cost
//  No cycles
//  If graph is disconnected ? return -1
//  MST with connectivity check

//  Example 1:
//  Input: n = 3, connections = [[1,2,5],[1,3,6],[2,3,1]]
//  Output: 6
//  Explanation: Choosing the connections [1,2,5] and [2,3,1] connects all cities with total cost 6.
//
//  Example 2:
//  Input: n = 4, connections = [[1,2,3],[3,4,4]]
//  Output: -1
//  Explanation: It is impossible to connect all cities since city 2 and city 3 are disconnected.

//  Solution: Kruskal + Union-Find, Edge list given, easy to detect disconnected graph
//  “I use Kruskal’s algorithm. I sort all edges by cost and union cities if they are not already connected.
//  If after processing all edges I have used fewer than n-1 edges, then the graph is disconnected and I return -1.”
//  “This is an MST problem. I used Kruskal’s algorithm and returned -1 if we can’t form n-1 edges.”
//  Time	O(E log E)
//  Space	O(N)

import java.util.Arrays;

public class ConnectingCities {

  static class UnionFind {

    int[] parent, rank;

    UnionFind(int n) {
      parent = new int[n + 1]; // cities are 1-based
      rank = new int[n + 1];
      for (int i = 1; i <= n; i++) {
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

  public static int minimumCost(int n, int[][] connections) {

    Arrays.sort(connections, (a, b) -> a[2] - b[2]);

    UnionFind uf = new UnionFind(n);
    int totalCost = 0;
    int edgesUsed = 0;

    for (int[] edge : connections) {
      if (uf.union(edge[0], edge[1])) {
        totalCost += edge[2];
        edgesUsed++;
        if (edgesUsed == n - 1) {
          break;
        }
      }
    }

    return edgesUsed == n - 1 ? totalCost : -1;
  }

  // Main method
  public static void main(String[] args) {

    int n = 3;
    int[][] connections = {
        {1, 2, 5},
        {1, 3, 6},
        {2, 3, 1}
    };

    int result = minimumCost(n, connections);
    System.out.println("Minimum cost to connect all cities = " + result);
  }
}
