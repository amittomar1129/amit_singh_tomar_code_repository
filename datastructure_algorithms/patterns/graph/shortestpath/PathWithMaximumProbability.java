package datastructure_algorithms.patterns.graph.shortestpath;

//  You are given an undirected weighted graph of n nodes (0-indexed), represented by an edge list where
//  edges[i] = [a, b] is an undirected edge connecting the nodes a and b with a probability of success
//  of traversing that edge succProb[i].
//  Given two nodes start and end, find the path with the maximum probability of success to go from start
//  to end and return its success probability. If there is no path from start to end, return 0.
//  Your answer will be accepted if it differs from the correct answer by at most 1e-5.

//  You are given:
//  n nodes (0-indexed)
//  Undirected weighted graph:
//  edges[i] = [a, b]
//  succProb[i] = probability of traversing edge [a,b]
//  start node and end node
//  Goal:
//  Find a path from start ? end with maximum product of probabilities
//  Return maximum probability
//  If no path ? return 0

//  Example 1:
//  Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
//  Output: 0.25000
//  Explanation: There are two paths from start to end, one has a probability of 0.2 and the other has 0.5 * 0.5 = 0.25.
//
//  Example 2:
//  Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, end = 2
//  Output: 0.30000

//  Solution: This is a maximum product path problem on a graph. We use Dijkstra with a max-heap,
//  where the path probability is multiplicative instead of additive.
//  Time	O((V + E) log V)
//  Space	O(V + E)

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class PathWithMaximumProbability {

  static class Edge {

    int node;
    double prob;

    Edge(int node, double prob) {
      this.node = node;
      this.prob = prob;
    }
  }

  public static double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {

    // Build graph
    List<List<Edge>> graph = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      graph.add(new ArrayList<>());
    }
    for (int i = 0; i < edges.length; i++) {
      int u = edges[i][0];
      int v = edges[i][1];
      double p = succProb[i];
      graph.get(u).add(new Edge(v, p));
      graph.get(v).add(new Edge(u, p)); // undirected
    }

    // Max-heap by probability
    PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> Double.compare(b.prob, a.prob));
    pq.offer(new Edge(start, 1.0));

    double[] prob = new double[n];
    prob[start] = 1.0;

    while (!pq.isEmpty()) {
      Edge curr = pq.poll();
      int u = curr.node;
      double currProb = curr.prob;

      if (u == end) {
        return currProb;
      }

      if (currProb < prob[u]) {
        continue;
      }

      for (Edge nei : graph.get(u)) {
        int v = nei.node;
        double newProb = currProb * nei.prob;
        if (newProb > prob[v]) {
          prob[v] = newProb;
          pq.offer(new Edge(v, newProb));
        }
      }
    }

    return 0.0;
  }

  public static void main(String[] args) {
    int n = 3;
    int[][] edges = {{0, 1}, {1, 2}, {0, 2}};
    double[] succProb = {0.5, 0.5, 0.2};
    int start = 0, end = 2;

    System.out.println("Maximum Probability -> " +
        maxProbability(n, edges, succProb, start, end));
  }
}
