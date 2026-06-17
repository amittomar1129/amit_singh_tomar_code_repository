package datastructure_algorithms.datastructure.nonlinear.tree.graph.coloring;

import datastructure_algorithms.datastructure.nonlinear.tree.graph.Edge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Backtracking {

  private List<List<Edge>> adj;
  private int vertices; // No. of vertices

  public Backtracking(int vertices) {
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
    newEdge.setFrom(i);
    if (!adj.get(i).contains(newEdge)) {
      adj.get(i).add(newEdge);
    }
    Edge newEdge1 = new Edge(i);
    newEdge1.setFrom(j);
    if (!adj.get(j).contains(newEdge1)) {
      adj.get(j).add(newEdge1);
    }
  }

  public void addEdge(int i, int j, int weight) {
    if (i >= vertices || j >= vertices) {
      throw new IllegalArgumentException(
          String.format("Edge index beyond the vertices size: [%s]", vertices));
    }
    Edge newEdge = new Edge(j, weight);
    newEdge.setFrom(i);
    if (!adj.get(i).contains(newEdge)) {
      adj.get(i).add(newEdge);
    }
    Edge newEdge1 = new Edge(i, weight);
    newEdge1.setFrom(j);
    if (!adj.get(j).contains(newEdge1)) {
      adj.get(j).add(newEdge1);
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

  // Returns an array of colors assigned (0 to 3)
  public boolean colorGraph(int k, int[] colors) {
    Arrays.fill(colors, -1);
    return backtrack(0, k, colors);
  }

  private boolean backtrack(int node, int k, int[] colors) {
    if (node == vertices) {
      return true; // All vertices colored
    }

    for (int i = 0; i < k; i++) {
      if (isSafe(node, i, colors)) {
        colors[node] = i;
        if (backtrack(node + 1, k, colors)) {
          return true;
        }
        colors[node] = -1; // Backtrack
      }
    }
    return false; // No valid color found
  }

  private boolean isSafe(int node, int color, int[] colors) {
    for (Edge edge : adj.get(node)) {
      if (colors[edge.getTo()] == color) {
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
    Backtracking graph = new Backtracking(6);
    graph.addEdge(0, 1);
    graph.addEdge(0, 2);
    graph.addEdge(0, 3);
    graph.addEdge(1, 2);
    graph.addEdge(2, 3);
    graph.addEdge(3, 4);
    graph.addEdge(4, 5);
    System.out.println(graph);
    int[] colors = new int[graph.vertices];
    int k = 3;
    if (graph.colorGraph(k, colors)) {
      System.out.println("Graph colored with " + k + " colors:");
      for (int i = 0; i < graph.vertices; i++) {
        System.out.println("Vertex " + i + " -> Color " + colors[i]);
      }
    } else {
      System.out.println("Cannot color graph with " + k + " colors.");
    }
  }
}
