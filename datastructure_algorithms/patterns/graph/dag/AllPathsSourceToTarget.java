package datastructure_algorithms.patterns.graph.dag;

//  Given a directed, acyclic graph of N nodes. Find all possible paths from node 0 to node N-1, and return them in any order.
//  The graph is given as follows: the nodes are 0, 1, ..., graph.length - 1. graph[i] is a list of all nodes you can visit
//  from node i (i.e., there is a directed edge from node i to node graph[i][j]).
//
//  Example 1:
//  Input: graph = [[1,2],[3],[3],[]]
//  Output: [[0,1,3],[0,2,3]]
//  Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
//
//  Example 2:
//  Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
//  Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
//  Explanation: There are five paths from node 0 to node 4.

//  Solution: “Since the graph is a DAG, I used DFS with backtracking. I maintain a path list,
//  and whenever I reach the target node, I store a copy of the path.”
//  Time: O(number of paths × path length)
//  Space: O(path length) (recursion stack)

import java.util.ArrayList;
import java.util.List;

public class AllPathsSourceToTarget {


  public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    dfs(0, graph, path, result);
    return result;
  }

  private static void dfs(int node, int[][] graph,
      List<Integer> path,
      List<List<Integer>> result) {

    path.add(node);

    // If reached target
    if (node == graph.length - 1) {
      result.add(new ArrayList<>(path));
    } else {
      for (int next : graph[node]) {
        dfs(next, graph, path, result);
      }
    }

    // Backtrack
    path.remove(path.size() - 1);
  }

  public static void main(String[] args) {
    int[][] graph = {
        {1, 2},
        {3},
        {3},
        {}
    };

    List<List<Integer>> paths = allPathsSourceTarget(graph);

    System.out.println("All paths from 0 to N-1:");
    for (List<Integer> path : paths) {
      System.out.println(path);
    }
  }
}
