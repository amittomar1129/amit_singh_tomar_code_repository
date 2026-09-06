package datastructure_algorithms.datastructure.nonlinear.tree.graph.shortestpath;

import datastructure_algorithms.datastructure.nonlinear.tree.graph.Edge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.IntStream;

public class AStarAdjList {

  private List<List<Edge>> adj;
  private int vertices; // No. of vertices

  public AStarAdjList(int vertices) {
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

  private ArrayList<Integer> aStarShortestPath(int source, int destination, int[] heuristics) {
    ArrayList<Integer> result = new ArrayList<>();
    int[] distance = new int[vertices];
    int[] parent = new int[vertices];
    Arrays.fill(distance, Integer.MAX_VALUE);
    Arrays.fill(parent, -1);
    distance[source] = 0;

    if (heuristics == null) {
      return result;
    }
    Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
    queue.offer(new int[]{source, distance[source] + heuristics[source]});

    boolean[] visited = new boolean[vertices];

    while (!queue.isEmpty()) {
      int[] element = queue.poll();
      int node = element[0];
      if (visited[node]) {
        continue;
      }
      visited[node] = true;
      if (node == destination) {
        buildResult(source, destination, parent, result);
        System.out.println("Total Cost: " + element[1]);
      }
      for (Edge edge : adj.get(node)) {
        if (edge.getWeight() < 0) {
          throw new IllegalArgumentException("A* does not support negative weights");
        }
        if (edge.getWeight() != 0 && !visited[edge.getTo()]
            && distance[node] != Integer.MAX_VALUE
            && distance[edge.getTo()] > distance[node] + edge.getWeight()) {
          distance[edge.getTo()] = distance[node] + edge.getWeight();
          parent[edge.getTo()] = node;
          queue.offer(new int[]{edge.getTo(), distance[edge.getTo()] + heuristics[edge.getTo()]});
        }
      }
    }
    return result;
  }

  private void buildResult(int source, int destination, int[] parent, List<Integer> result) {
    if (destination == source) {
      result.add(source);
      return;
    }

    buildResult(source, parent[destination], parent, result);
    result.add(destination);
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
    AStarAdjList graph = new AStarAdjList(5);
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
    // It`s optional to pass heuristics, It runs dijkstra to find heuristics(estimated cost) for each vertex to target if not passed.
    ArrayList<Integer> route = graph.aStarShortestPath(0, 2, new int[]{1, 12, 1, 121, 1});
    System.out.println(route);
  }
}
