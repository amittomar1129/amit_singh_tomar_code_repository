package datastructure_algorithms.datastructure.nonlinear.tree.graph.shortestpath;

import datastructure_algorithms.datastructure.nonlinear.tree.graph.Edge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class DijkstraAdjList {

  private List<List<Edge>> adj;
  private int vertices; // No. of vertices

  public DijkstraAdjList(int vertices) {
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

  public int[] dijkstraShortestPath(int source) {
    int[] distance = new int[vertices];
    int[] parent = new int[vertices];
    Arrays.fill(distance, Integer.MAX_VALUE);
    Arrays.fill(parent, -1);
    distance[source] = 0;

    PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
    queue.offer(new int[]{distance[source], source});

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

    System.out.println("Shortest Distance from 0:");
    for (int i = 0; i < distance.length; i++) {
      if (distance[i] == Integer.MAX_VALUE) {
        System.out.println(i + ": INF" + ", Path: " + printPath(i, parent));
      } else {
        System.out.println(i + ": " + distance[i] + ", Path: " + printPath(i, parent));
      }
    }

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
    DijkstraAdjList graph = new DijkstraAdjList(5);
    graph.addEdge(0, 1, 10);
    graph.addEdge(0, 4, 5);
    graph.addEdge(1, 2, 1);
    graph.addEdge(1, 4, 2);
    graph.addEdge(2, 3, 4);
    graph.addEdge(3, 0, 7);
    graph.addEdge(3, 2, 6);
    graph.addEdge(4, 1, 3);
    graph.addEdge(4, 2, 9);
    graph.addEdge(4, 3, 2);
    System.out.println(graph);
    int[] results = graph.dijkstraShortestPath(0);
  }
}
