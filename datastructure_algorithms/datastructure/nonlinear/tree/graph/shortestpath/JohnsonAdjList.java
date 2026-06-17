package datastructure_algorithms.datastructure.nonlinear.tree.graph.shortestpath;

import datastructure_algorithms.datastructure.nonlinear.tree.graph.Edge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class JohnsonAdjList {

  private List<List<Edge>> adj;
  private List<Edge> edges;
  private int vertices; // No. of vertices

  public JohnsonAdjList(int vertices) {
    this.vertices = vertices;
    edges = new ArrayList<>();
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
    newEdge.setFrom(i);
    if (!edges.contains(newEdge)) {
      edges.add(newEdge);
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
    newEdge.setFrom(i);
    if (!edges.contains(newEdge)) {
      edges.add(newEdge);
    }
  }

  public void removeEdge(int i, int j) {
    if (i >= vertices || j >= vertices) {
      throw new IllegalArgumentException(
          String.format("Edge index beyond the vertices size: [%s]", vertices));
    }
    adj.get(i).removeIf(edge -> edge.getTo() == j);
    edges.removeIf(edge -> edge.getFrom() == i && edge.getTo() == j);
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

  public int[][] johnsonShortestPathsAllVertices() {
    int[][] result = new int[vertices][vertices];
    ArrayList<Edge> extendedEdges = new ArrayList<>(edges);
    int[] h = bellmanFord(extendedEdges);
    if (h == null) {
      return null;
    }

    List<List<Edge>> reweightedAdj = new ArrayList<>();
    for (int i = 0; i < adj.size(); i++) {
      reweightedAdj.add(new ArrayList<>());
      for (Edge edge : adj.get(i)) {
        int weight = edge.getWeight() + h[i] - h[edge.getTo()];
        reweightedAdj.get(i).add(new Edge(i, edge.getTo(), weight));
      }
    }

    for (int i = 0; i < vertices; i++) {
      int[] distance = dijkstraShortestPath(reweightedAdj, i);
      for (int j = 0; j < vertices; j++) {
        if (distance[j] < Integer.MAX_VALUE) {
          result[i][j] = distance[j] - h[i] + h[j];
        } else {
          result[i][j] = Integer.MAX_VALUE;
        }
      }
    }

    return result;
  }

  public int[] bellmanFord(List<Edge> edges) {
    int[] distance = new int[vertices + 1];
    Arrays.fill(distance, Integer.MAX_VALUE);
    distance[vertices] = 0;

    for (int i = 0; i < vertices; i++) {
      edges.add(new Edge(vertices, i, 0));
    }

    // V-1 times
    for (int i = 0; i < vertices; i++) {
      for (Edge edge : edges) {
        if (distance[edge.getFrom()] != Integer.MAX_VALUE
            && distance[edge.getTo()] > distance[edge.getFrom()] + edge.getWeight()) {
          distance[edge.getTo()] = distance[edge.getFrom()] + edge.getWeight();
        }
      }
    }

    // Negative Cycle check
    for (Edge edge : edges) {
      if (distance[edge.getTo()] > distance[edge.getFrom()] + edge.getWeight()) {
        return null;
      }
    }
    return distance;
  }

  public int[] dijkstraShortestPath(List<List<Edge>> adj, int source) {
    int[] distance = new int[vertices];
    int[] parent = new int[vertices];
    Arrays.fill(distance, Integer.MAX_VALUE);
    Arrays.fill(parent, -1);
    distance[source] = 0;

    PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
    queue.offer(new int[]{0, source});

    while (!queue.isEmpty()) {
      int[] poll = queue.poll();
      int node = poll[1];
      for (Edge edge : adj.get(node)) {
        if (distance[node] != Integer.MAX_VALUE
            && distance[edge.getTo()] > distance[node] + edge.getWeight()) {
          distance[edge.getTo()] = distance[node] + edge.getWeight();
          parent[edge.getTo()] = node;
          queue.offer(new int[]{distance[edge.getTo()], edge.getTo()});
        }
      }
    }

//    System.out.println("Shortest Distance from 0:");
//    for (int i = 0; i < distance.length; i++) {
//      if (distance[i] == -1) {
//        System.out.println(i + ": INF" + ", Path: " + printPath(i, parent));
//      } else {
//        System.out.println(i + ": " + distance[i] + ", Path: " + printPath(i, parent));
//      }
//    }

    return distance;
  }

  private String printPath(int destination, int[] parent) {
    if (destination == 0) {
      return destination + "";
    }

    String path = printPath(parent[destination], parent);

    return path + ", " + destination;
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
    JohnsonAdjList graph = new JohnsonAdjList(5);
    graph.addEdge(0, 1, -1);
    graph.addEdge(0, 2, 4);
    graph.addEdge(1, 2, 3);
    graph.addEdge(1, 3, 2);
    graph.addEdge(1, 4, 2);
    graph.addEdge(3, 2, 5);
    graph.addEdge(3, 1, 1);
    graph.addEdge(4, 3, -3);
    System.out.println(graph);
    int[][] result = graph.johnsonShortestPathsAllVertices();
    if (result != null) {
      System.out.println("All pairs shortest paths:");
      for (int i = 0; i < result.length; i++) {
        for (int j = 0; j < result.length; j++) {
          if (result[i][j] == Integer.MAX_VALUE) {
            System.out.print("INF ");
          } else {
            System.out.print(result[i][j] + " ");
          }
        }
        System.out.println();
      }
    }
  }
}
