package datastructure_algorithms.patterns.graph.bipartition;

//  Given an undirected graph and an integer M, determine if the graph can be colored with at most M colors such
//  that no two adjacent vertices of the graph are colored with the same color.
//  You are given an integer M and a graph represented as an adjacency matrix. Your task is to assign colors
//  to all vertices such that no two adjacent vertices share the same color using at most M different colors.
//  Return true if such a coloring is possible, otherwise return false.
//
//  Example 1:
//  Input: M = 3, graph = [[0,1,1,1],[1,0,1,0],[1,1,0,1],[1,0,1,0]]
//  Output: true
//  Explanation: It is possible to color the graph using 3 colors such that no two adjacent vertices have the same color.
//
//  Example 2:
//  Input: M = 2, graph = [[0,1,1,1],[1,0,1,0],[1,1,0,1],[1,0,1,0]]
//  Output: false
//  Explanation: It is not possible to color the graph using only 2 colors without two adjacent vertices sharing the same color.

//  Solution: “This is the classical M-Coloring problem. Since greedy fails in general graphs,
//  I used backtracking. I assign colors one by one, validate constraints, and backtrack when conflicts occur.”
//  Time	O(M^N) (worst case)
//  Space	O(N) (recursion + colors)

public class MColoringProblem {

  public static boolean graphColoring(int[][] graph, int m, int n) {
    int[] colors = new int[n];
    return solve(0, graph, colors, m, n);
  }

  private static boolean solve(int node, int[][] graph,
      int[] colors, int m, int n) {

    // All vertices are colored
    if (node == n) {
      return true;
    }

    // Try all colors
    for (int color = 1; color <= m; color++) {
      if (isSafe(node, graph, colors, color, n)) {
        colors[node] = color;

        if (solve(node + 1, graph, colors, m, n)) {
          return true;
        }

        // Backtrack
        colors[node] = 0;
      }
    }
    return false;
  }

  private static boolean isSafe(int node, int[][] graph,
      int[] colors, int color, int n) {

    for (int i = 0; i < n; i++) {
      if (graph[node][i] == 1 && colors[i] == color) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {

    int[][] graph = {
        {0, 1, 1, 1},
        {1, 0, 1, 0},
        {1, 1, 0, 1},
        {1, 0, 1, 0}
    };

    int m = 3; // number of colors
    int n = graph.length;

    System.out.println(graphColoring(graph, m, n));
  }
}
