package datastructure_algorithms.patterns.graph.bipartition;

//  Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.
//  Each person may dislike some other people, and they should not go into the same group.
//  Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.
//  Return true if and only if it is possible to split everyone into two groups in this way.

//  You want to split people into 2 groups
//  If a dislikes b, then:
//  a and b must be in different groups
//  This is exactly the definition of a Bipartite Graph
//  If we can color the graph using 2 colors such that:
//  no adjacent nodes have the same color
//  then the split is possible.

//  Example 1:
//  Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
//  Output: true
//  Explanation: Group 1: [1,4], Group 2: [2,3]
//
//  Example 2:
//  Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
//  Output: false
//  Explanation: It is impossible to split into two groups without putting a disliked pair together.

//  Solution: “I modeled people as nodes and dislikes as edges. The problem reduces to checking if the
//  graph is bipartite. I used BFS-based graph coloring, assigning opposite groups to adjacent nodes.
//  If a conflict appears, I return false.”
//  Time	O(N + E)
//  Space	O(N + E)

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PossibleBipartition {

  public static boolean possibleBipartition(int n, int[][] dislikes) {

    // Build graph
    List<Integer>[] graph = new ArrayList[n + 1];
    for (int i = 1; i <= n; i++) {
      graph[i] = new ArrayList<>();
    }

    for (int[] d : dislikes) {
      graph[d[0]].add(d[1]);
      graph[d[1]].add(d[0]);
    }

    int[] color = new int[n + 1]; // 0 = uncolored, 1 / -1 = two groups

    for (int i = 1; i <= n; i++) {
      if (color[i] == 0) {
        if (!bfsCheck(i, graph, color)) {
          return false;
        }
      }
    }

    return true;
  }

  private static boolean bfsCheck(int start, List<Integer>[] graph, int[] color) {
    Queue<Integer> queue = new LinkedList<>();
    queue.offer(start);
    color[start] = 1;

    while (!queue.isEmpty()) {
      int u = queue.poll();

      for (int v : graph[u]) {
        if (color[v] == 0) {
          color[v] = -color[u];
          queue.offer(v);
        } else if (color[v] == color[u]) {
          return false; // conflict
        }
      }
    }
    return true;
  }

  public static void main(String[] args) {
    int n = 4;
    int[][] dislikes = {
        {1, 2},
        {1, 3},
        {2, 4}
    };

    System.out.println(possibleBipartition(n, dislikes)); // true
  }
}
