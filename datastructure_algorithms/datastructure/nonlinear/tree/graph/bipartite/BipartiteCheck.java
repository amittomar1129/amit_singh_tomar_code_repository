package datastructure_algorithms.datastructure.nonlinear.tree.graph.bipartite;

import datastructure_algorithms.datastructure.nonlinear.tree.graph.Edge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;

public class BipartiteCheck {

  private List<List<Edge>> adj;
  private int vertices; // No. of vertices

  public BipartiteCheck(int vertices) {
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

  private boolean isBipartite() {
    int[] color = new int[vertices];
    Arrays.fill(color, -1);

    for (int i = 0; i < vertices; i++) {
      if (color[i] == -1) {
        boolean isBipartite = isBipartiteByBfs(i, color);
        if (!isBipartite) {
          return false;
        }
      }
    }
    return true;
  }

  private boolean isBipartiteByBfs(int node, int[] color) {
    Queue<Integer> queue = new LinkedList<>();
    queue.offer(node);
    color[node] = 0;

    while (!queue.isEmpty()) {
      Integer current = queue.poll();
      for (Edge edge : adj.get(current)) {
        if (color[edge.getTo()] == -1) {
          color[edge.getTo()] = 1 - color[current];
          queue.offer(edge.getTo());
        } else if (color[current] == color[edge.getTo()]) {
          return false;
        }
      }
    }
    return true;
  }

  private boolean isBipartiteDfs() {
    int[] color = new int[vertices];
    Arrays.fill(color, -1);

    for (int i = 0; i < vertices; i++) {
      if (color[i] == -1) {
        boolean isBipartite = isBipartiteByDfs(i, 0, color);
        if (!isBipartite) {
          return false;
        }
      }
    }
    return true;
  }

  private boolean isBipartiteByDfs(int node, int currentColor, int[] color) {
    color[node] = currentColor;

    for (Edge edge : adj.get(node)) {
      if (color[edge.getTo()] == -1) {
        if (!isBipartiteByDfs(edge.getTo(), 1 - currentColor, color)) {
          return false;
        }
      } else if (color[node] == color[edge.getTo()]) {
        return false;
      }
    }

    return true;
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
    BipartiteCheck graph = new BipartiteCheck(5);
    graph.addEdge(0, 1);
    graph.addEdge(1, 2);
    graph.addEdge(2, 3);

    graph.addEdge(3, 4);
    System.out.println(graph);
    // Using BFS
    System.out.println(graph.isBipartite());
    // Using DFS
    System.out.println(graph.isBipartiteDfs());
  }
}
