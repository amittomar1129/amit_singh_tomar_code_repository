package datastructure_algorithms.patterns.graph.shortestpath;

//  You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as
//  directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the
//  time it takes for a signal to travel from source to target.
//  We send a signal from a given node k. Return the time it takes for all the n nodes to receive the signal.
//  If it is impossible for all the n nodes to receive the signal, return -1.
//
//  Example 1:
//  Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
//  Output: 2
//  Explanation: Starting at node 2, the signal reaches node 1 and 3 in 1 unit of time, and node 4 in 2 units of time.
//
//  Example 2:
//  Input: times = [[1,2,1]], n = 2, k = 1
//  Output: 1
//  Explanation: The signal starts at node 1 and reaches node 2 in 1 unit of time.
//
//  Example 3:
//  Input: times = [[1,2,1]], n = 2, k = 2
//  Output: -1
//  Explanation: The signal starts at node 2, but there is no path to node 1, so not all nodes receive the signal.

//  Solution: This is a shortest path problem on a directed weighted graph.
//  We need the maximum of the shortest distances from node k to all other nodes in a directed weighted graph
//  Dijkstra’s Algorithm
//  Time	O(m log n)
//  Space	O(n + m)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class NetworkDelayTime {

  public static int networkDelayTime(int[][] times, int n, int k) {

    // Step 1 -> Build graph
    Map<Integer, List<int[]>> graph = new HashMap<>();
    for (int[] edge : times) {
      graph.computeIfAbsent(edge[0], x -> new ArrayList<>()).add(new int[]{edge[1], edge[2]});
    }

    // Step 2 -> Min heap (time, node)
    PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

    pq.offer(new int[]{0, k});

    int[] dist = new int[n + 1];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[k] = 0;

    // Step 3 -> Dijkstra
    while (!pq.isEmpty()) {
      int[] curr = pq.poll();
      int time = curr[0];
      int node = curr[1];

      if (time > dist[node]) {
        continue;
      }

      if (!graph.containsKey(node)) {
        continue;
      }

      for (int[] nei : graph.get(node)) {
        int nextNode = nei[0];
        int weight = nei[1];

        if (dist[nextNode] > time + weight) {
          dist[nextNode] = time + weight;
          pq.offer(new int[]{dist[nextNode], nextNode});
        }
      }
    }

    // Step 4 -> Find max distance
    int maxTime = 0;
    for (int i = 1; i <= n; i++) {
      if (dist[i] == Integer.MAX_VALUE) {
        return -1;
      }
      maxTime = Math.max(maxTime, dist[i]);
    }

    return maxTime;
  }

  public static void main(String[] args) {
    int[][] times = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
    int n = 4;
    int k = 2;

    System.out.println("Network Delay Time -> " + networkDelayTime(times, n, k));
  }
}
