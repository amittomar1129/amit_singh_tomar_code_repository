package datastructure_algorithms.patterns.graph.shortestpath;

//  There are n cities connected by m flights. Each flight starts from city u and arrives at v with a price w.
//  Now given all the cities and flights, together with starting city src and the destination dst, your task
//  is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.

//  You are given: n cities (nodes)
//  flights[i] = (u, v, w) -> directed edge with cost w
//  src -> starting city
//  dst -> destination city
//  k -> maximum number of stops allowed

//  Example 1:
//  Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
//  Output: 200
//  Explanation: The graph looks like this:
//  The cheapest price from city 0 to city 2 with up to 1 stop is 200, taking the route 0 -> 1 -> 2.
//
//  Example 2:
//  Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
//  Output: 500
//  Explanation: The cheapest price from city 0 to city 2 with up to 0 stops is 500, taking the direct flight 0 -> 2.

//  Solution: There are two correct approaches:
//  1. Modified BFS / Dijkstra (Priority Queue) (most common)
//  Time	O(m log (n * k))
//  Space	O(n * k)

//  2. Bellman-Ford (K+1 relaxations)

//  Bellman–Ford gives shortest paths from ONE source to all vertices.
//  Core Property of Bellman–Ford
//      After i iterations, Bellman–Ford computes the shortest paths that use at most i edges.
//      This single property is the entire reason it works.
//  Bellman–Ford guarantees that after i iterations, we have the shortest paths using at most i edges.
//  Since k stops means k+1 edges, running k+1 iterations gives the correct result.
//  Time	O(k * m)
//      Space	O(n)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class CheapestFlightsWithinKStops {

  static class State {

    int city;
    int cost;
    int stops;

    State(int city, int cost, int stops) {
      this.city = city;
      this.cost = cost;
      this.stops = stops;
    }
  }

  public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

    // Build graph
    Map<Integer, List<int[]>> graph = new HashMap<>();
    for (int[] f : flights) {
      graph.computeIfAbsent(f[0], x -> new ArrayList<>())
          .add(new int[]{f[1], f[2]});
    }

    // Min heap by cost
    PriorityQueue<State> pq =
        new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));

    pq.offer(new State(src, 0, 0));

    // best[city][stops] = minimum cost
    int[][] best = new int[n][k + 2];
    for (int[] row : best) {
      Arrays.fill(row, Integer.MAX_VALUE);
    }
    best[src][0] = 0;

    while (!pq.isEmpty()) {
      State curr = pq.poll();

      if (curr.city == dst) {
        return curr.cost;
      }

      if (curr.stops > k) {
        continue;
      }

      if (!graph.containsKey(curr.city)) {
        continue;
      }

      for (int[] nei : graph.get(curr.city)) {
        int nextCity = nei[0];
        int price = nei[1];
        int newCost = curr.cost + price;

        if (newCost < best[nextCity][curr.stops + 1]) {
          best[nextCity][curr.stops + 1] = newCost;
          pq.offer(new State(nextCity, newCost, curr.stops + 1));
        }
      }
    }

    return -1;
  }

  public static void main(String[] args) {
    int n = 4;
    int[][] flights = {
        {0, 1, 100},
        {1, 2, 100},
        {2, 3, 100},
        {0, 3, 500}
    };
    int src = 0, dst = 3, k = 1;

    System.out.println("Cheapest Price -> " +
        findCheapestPrice(n, flights, src, dst, k));
  }


  public static int findCheapestPriceUsingBellmanFord(int n, int[][] flights,
      int src, int dst, int k) {

    // dist[i] = minimum cost to reach city i
    int[] dist = new int[n];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[src] = 0;

    // Run Bellman-Ford for k + 1 iterations
    for (int i = 0; i <= k; i++) {
      int[] temp = dist.clone();

      for (int[] flight : flights) {
        int u = flight[0];
        int v = flight[1];
        int w = flight[2];

        if (dist[u] != Integer.MAX_VALUE) {
          temp[v] = Math.min(temp[v], dist[u] + w);
        }
      }

      dist = temp;
    }

    return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
  }
}
