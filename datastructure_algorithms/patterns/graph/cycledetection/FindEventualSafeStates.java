package datastructure_algorithms.patterns.graph.cycledetection;

//  In a directed graph, we start at some node and every turn, walk along a directed edge of the graph.
//  If we reach a node that is terminal (i.e., it has no outgoing edges), we stop.
//  Now, say our starting node is eventually safe if and only if we must eventually walk to a terminal node.
//  More specifically, there exists a natural number K so that for any choice of where to walk,
//  we must have stopped at a terminal node in less than K steps.
//  Return an array containing all the nodes that are eventually safe. The answer should be sorted in ascending order.

//  You have a directed graph.
//  You can walk along outgoing edges.
//  If you reach a terminal node (no outgoing edges) -> you stop.
//  A node is eventually safe if:
//  Every possible path from it leads to a terminal node
//  i.e., it cannot be part of a cycle and cannot reach a cycle
//  Key Insight
//  Nodes that are part of cycles, or that can reach a cycle, are not safe.

//  Example 1:
//  Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
//  Output: [2,4,5,6]
//  Explanation: The given graph is shown above. Nodes 2, 4, 5, and 6 are eventually safe because starting
//  from these nodes, we cannot enter any cycle.
//
//  Example 2:
//  Input: graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]

//  Output: [4]
//  Explanation: Only node 4 is eventually safe because it is a terminal node, and all other nodes can reach cycles.

//  Solution: Safe nodes = nodes that do NOT lead to cycles, Unsafe nodes = nodes involved in cycles or reaching cycles.
//  Eventually safe nodes are those that cannot reach any cycle. We find them by reversing the graph and performing topological
//  pruning starting from terminal nodes.
//  This problem is part of the “reverse graph + topological sort” pattern.
//  Time	O(V + E)
//  Space	O(V + E)

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FindEventualSafeStates {

  public static List<Integer> eventualSafeNodes(int[][] graph) {
    int n = graph.length;

    List<List<Integer>> reverseGraph = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      reverseGraph.add(new ArrayList<>());
    }

    int[] outDegree = new int[n];

    // Build reverse graph and outdegree
    for (int u = 0; u < n; u++) {
      outDegree[u] = graph[u].length;
      for (int v : graph[u]) {
        reverseGraph.get(v).add(u);
      }
    }

    Queue<Integer> queue = new LinkedList<>();

    // Terminal nodes
    for (int i = 0; i < n; i++) {
      if (outDegree[i] == 0) {
        queue.offer(i);
      }
    }

    boolean[] safe = new boolean[n];

    while (!queue.isEmpty()) {
      int node = queue.poll();
      safe[node] = true;

      for (int parent : reverseGraph.get(node)) {
        outDegree[parent]--;
        if (outDegree[parent] == 0) {
          queue.offer(parent);
        }
      }
    }

    List<Integer> result = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      if (safe[i]) {
        result.add(i);
      }
    }

    return result;
  }

  public static void main(String[] args) {
    int[][] graph = {
        {1, 2},
        {2, 3},
        {5},
        {0},
        {5},
        {},
        {}
    };
    System.out.println("Safe Nodes -> " + eventualSafeNodes(graph));
  }
}
