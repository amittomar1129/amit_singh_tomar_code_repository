package datastructure_algorithms.patterns.graph.bipartition;

//  You have N gardens, labeled 1 to N, and an array paths where paths[i] = [x_i, y_i] describes a bidirectional
//  path between garden x_i and garden y_i. In each garden, you want to plant one of 4 types of flowers.
//  All gardens have at most 3 paths coming into or leaving it. Your task is to choose a flower type for each garden
//  such that, for any two gardens connected by a path, they have different types of flowers.
//  Return any such a choice as an array answer, where answer[i] is the type of flower planted in
//  the (i+1)-th garden. The flower types are denoted 1, 2, 3, or 4. It is guaranteed an answer exists.
//
//  Each garden has degree <= 3
//  We have 4 flower types
//  By graph coloring theory:
//  A node with at most 3 neighbors can always be colored using 4 colors
//  So we do NOT need backtracking, DFS, or BFS.
//  Greedy coloring works perfectly

//  Example 1:
//  Input: N = 3, paths = [[1,2],[2,3],[3,1]]
//  Output: [1,2,3]
//  Explanation: Gardens 1, 2, and 3 can be planted with flowers 1, 2, and 3 respectively, so that no adjacent gardens have the same flower.
//
//  Example 2:
//  Input: N = 4, paths = [[1,2],[3,4]]
//  Output: [1,2,1,2]
//  Explanation: Gardens 1 and 2 are connected, so they must have different flowers. Similarly, gardens 3 and 4 are connected and must have different flowers.

//  Solution: “Since each garden has at most 3 neighbors and we have 4 flower types,
//  a greedy coloring approach works. For each garden, I choose any flower not used by its neighbors.
//  This guarantees a valid solution.”
//  Time	O(N)
//  Space	O(N + E)


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlowerPlanting {


  public static void addEdge(List<List<Integer>> graph, int i, int j) {
    if (i >= graph.size() || j >= graph.size()) {
      throw new IllegalArgumentException("Invalid edge");
    }
    if (!graph.get(i).contains(Integer.valueOf(j))) {
      graph.get(i).add(j);
    }
    if (!graph.get(j).contains(Integer.valueOf(i))) {
      graph.get(j).add(i);
    }
  }



  public static int[] gardenNoAdj(int n, int[][] paths) {

    List<List<Integer>> graph = new ArrayList();
    for (int i = 0; i < n; i++) {
      graph.add(new ArrayList<>());
    }

    // Build graph (0-indexed)
    for (int[] p : paths) {
      int u = p[0] - 1;
      int v = p[1] - 1;
      addEdge(graph, u, v);
    }

    int[] colors = new int[n];
    Arrays.fill(colors, -1); // -1 means uncolored

    for (int i = 0; i < n; i++) {
      boolean[] used = new boolean[5]; // index 1..4

      for (Integer neighbor : graph.get(i)) {
        if (colors[neighbor] != -1) {
          used[colors[neighbor]] = true;
        }
      }

      // Pick the smallest available flower
      for (int j = 1; j <= 4; j++) {
        if (!used[j]) {
          colors[i] = j;
          break;
        }
      }
    }

    return colors;
  }

  public static void main(String[] args) {
    int n = 4;
    int[][] paths = {
        {1, 2},
        {3, 4}
    };

    int[] result = gardenNoAdj(n, paths);
    System.out.println(Arrays.toString(result));
  }
}
