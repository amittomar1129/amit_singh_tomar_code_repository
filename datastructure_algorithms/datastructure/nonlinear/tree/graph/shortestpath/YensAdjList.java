package datastructure_algorithms.datastructure.nonlinear.tree.graph.shortestpath;

import datastructure_algorithms.datastructure.nonlinear.tree.graph.Edge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.IntStream;

public class YensAdjList {

  private List<List<Edge>> adj;
  private int vertices; // No. of vertices

  public YensAdjList(int vertices) {
    this.vertices = vertices;
    adj = new ArrayList<>();
    for (int i = 0; i < vertices; i++) {
      adj.add(new ArrayList<>());
    }
  }

  public void addEdge(int i, int j) {
    if (i >= vertices || j >= vertices) {
      throw new IllegalArgumentException(
          String.format("Edge index beyond the vertices size: [%s]", vertices));
    }
    Edge newEdge = new Edge(j);
    if (!adj.get(i).contains(newEdge)) {
      adj.get(i).add(newEdge);
    }
  }

  public void addEdge(int i, int j, int weight) {
    if (i >= vertices || j >= vertices) {
      throw new IllegalArgumentException(
          String.format("Edge index beyond the vertices size: [%s]", vertices));
    }
    Edge newEdge = new Edge(j, weight);
    if (!adj.get(i).contains(newEdge)) {
      adj.get(i).add(newEdge);
    }
  }

  public void removeEdge(int i, int j) {
    if (i >= vertices || j >= vertices) {
      throw new IllegalArgumentException(
          String.format("Edge index beyond the vertices size: [%s]", vertices));
    }
    adj.get(i).removeIf(edge -> edge.getTo() == j);
  }

  // Adds new vertices having size more than the previous vertices.
  // Creates new matrix with new size and copy relevant data from old matrix.
  public void addVertex(int num) {
    if (num <= 0) {
      return;
    }
    int newVertices = vertices + num;
    for (int i = 0; i < num; i++) {
      adj.add(new ArrayList<>());
    }
    this.vertices = newVertices;
  }

  // Removes some vertices having size less than the previous vertices.
  // Creates new matrix with new lesser size and copy relevant data from old matrix.
  public void removeVertex(int num) {
    if (num <= 0 || num > this.vertices) {
      return;
    } else if (num == this.vertices) { // Reset the graph
      adj = new ArrayList<>();
      this.vertices = 0;
      return;
    }
    int newVertices = vertices - num;
    adj = adj.subList(0, newVertices);
    for (int i = 0; i < newVertices; i++) {
      int finalI = i;
      IntStream.range(vertices - num, vertices)
          .forEach(index -> adj.get(finalI).removeIf(a -> a.getTo() == index));
    }
    this.vertices = newVertices;
  }

  public ArrayList<Route> yensShortestRoutes(int source, int destination, int k) {
    ArrayList<Route> result = new ArrayList();
    PriorityQueue<Route> candidates = new PriorityQueue<>();

    Route firstRoute = dijkstra(source, destination, new HashSet<>());
    if (firstRoute == null) {
      return result;
    }
    result.add(firstRoute);

    // Step 2: Find up to K-1 deviations
    for (int i = 0; i < k - 1; i++) {
      Route prevRoute = result.get(i);
      for (int j = 0; j < prevRoute.getVertices().size() - 1; j++) {
        Integer spurNode = prevRoute.getVertices().get(j);
        List<Integer> rootPath = prevRoute.getVertices().subList(0, j + 1);

        HashSet<String> bannedEdges = new HashSet();
        for (Route route : result) {
          List<Integer> nodes = route.getVertices();
          if (nodes.size() > j && nodes.subList(0, j + 1).equals(rootPath)) {
            bannedEdges.add(nodes.get(j) + "-" + nodes.get(j + 1));
          }
        }

        // Spur path
        Route spurRoute = dijkstra(spurNode, destination, bannedEdges);
        if (spurRoute != null) {
          ArrayList<Integer> alternativeRoute = new ArrayList<>(rootPath);
          alternativeRoute.addAll(
              spurRoute.getVertices().subList(1, spurRoute.getVertices().size()));
          int cost = calculateCost(alternativeRoute);
          Route route = new Route(alternativeRoute, cost);
          candidates.offer(route);
        }
      }
      if (candidates.isEmpty()) {
        break;
      }
      result.add(candidates.poll());
    }

    return result;
  }

  private int calculateCost(List<Integer> nodes) {
    int cost = 0;
    for (int i = 0; i < nodes.size() - 1; i++) {
      for (Edge edge : adj.get(nodes.get(i))) {
        if (edge.getTo() == nodes.get(i + 1)) {
          cost += edge.getWeight();
          break;
        }
      }
    }
    return cost;
  }

  private Route dijkstra(int source, int destination, HashSet<String> bannedEdges) {
    int[] distance = new int[vertices];
    int[] parent = new int[vertices];
    Arrays.fill(distance, Integer.MAX_VALUE);
    Arrays.fill(parent, -1);
    distance[source] = 0;

    Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
    queue.offer(new int[]{source, 0});

    while (!queue.isEmpty()) {
      int[] poll = queue.poll();
      int node = poll[0];

      for (Edge edge : adj.get(node)) {
        if (distance[node] != Integer.MAX_VALUE
            && distance[edge.getTo()] > distance[node] + edge.getWeight()) {
          if (bannedEdges.contains(node + "-" + edge.getTo()) || bannedEdges.contains(
              edge.getTo() + "-" + node)) {
            continue;
          }
          distance[edge.getTo()] = distance[node] + edge.getWeight();
          parent[edge.getTo()] = node;
          queue.offer(new int[]{edge.getTo(), distance[edge.getTo()]});
        }
      }
    }

    if (distance[destination] != Integer.MAX_VALUE) {
      Stack<Integer> stack = new Stack<>();
      int temp = destination;
      stack.push(temp);
      while (parent[temp] != -1) {
        stack.push(parent[temp]);
        temp = parent[temp];
      }
      ArrayList<Integer> nodes = new ArrayList<>();
      while (!stack.isEmpty()) {
        nodes.add(stack.pop());
      }
      return new Route(nodes, distance[destination]);
    }
    return null;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("Graph:").append(" V = ").append(this.vertices)
        .append("\n");

    IntStream.range(0, adj.size())
        .forEach(index -> builder.append(index).append(": ").append(adj.get(index)).append("\n"));

    return builder.toString();
  }


  public static void main(String[] args) {
    YensAdjList graph = new YensAdjList(8);
    graph.addEdge(0, 1, 4);
    graph.addEdge(0, 2, 3);
    graph.addEdge(1, 3, 2);
    graph.addEdge(2, 3, 6);
    graph.addEdge(2, 4, 5);
    graph.addEdge(3, 5, 1);
    graph.addEdge(4, 5, 2);
    graph.addEdge(4, 7, 3);
    graph.addEdge(5, 6, 7);
    graph.addEdge(6, 7, 1);
    System.out.println(graph);
    List<Route> routes = graph.yensShortestRoutes(0, 7, 5);
    routes.forEach(route -> System.out.println(route));
  }
}
