package datastructure_algorithms.datastructure.nonlinear.tree.graph.coloring;

import datastructure_algorithms.datastructure.nonlinear.tree.graph.Edge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class DSATUR {

  private List<List<Edge>> adj;
  private int vertices; // No. of vertices

  public DSATUR(int vertices) {
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

  public int[] colorGraph() {
    int[] colors = new int[vertices];
    int[] degrees = new int[vertices];
    int[] saturation = new int[vertices];
    boolean[] colored = new boolean[vertices];

    Arrays.fill(colors, -1);
    for (int i = 0; i < vertices; i++) {
      degrees[i] = adj.get(i).size();
    }

    for (int i = 0; i < vertices; i++) {
      // Select uncolored vertex with max saturation (tie-break: max degree)
      int maxSaturation = -1, maxDegree = -1; int temp = -1;
      for (int j = 0; j < vertices; j++) {
        if (!colored[j]) {
          if (saturation[j] > maxSaturation || (saturation[j] == maxSaturation && degrees[j] > maxDegree)) {
            temp = j;
            maxSaturation = saturation[j];
            maxDegree = degrees[j];
          }
        }
      }
      // Find the smallest available color
      boolean[] used = new boolean[vertices];
      for (Edge edge : adj.get(temp)) {
        if (colors[edge.getTo()] != -1) {
          used[colors[edge.getTo()]] = true;
        }
      }
      int color = 0;
      while (color < vertices && used[color]) {
        color++;
      }
      colors[temp] = color;
      colored[temp] = true;
      // Update saturation of neighbors
      for (Edge edge : adj.get(temp)) {
        if (!colored[edge.getTo()]) {
          Set<Integer> neighborColors = new HashSet<>();
          for (Edge nei : adj.get(edge.getTo())) {
            if (colors[nei.getTo()] != -1) {
              neighborColors.add(colors[nei.getTo()]);
            }
          }
          saturation[edge.getTo()] = neighborColors.size();
        }
      }
    }

    return colors;
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
    DSATUR graph = new DSATUR(6);
    graph.addEdge(0, 1);
    graph.addEdge(0, 2);
    graph.addEdge(0, 3);
    graph.addEdge(1, 2);
    graph.addEdge(2, 3);
    graph.addEdge(3, 4);
    graph.addEdge(4, 5);
    System.out.println(graph);
    int[] colors = graph.colorGraph();
    for (int i = 0; i < colors.length; i++) {
      System.out.println("Vertex " + i + " -> Color " + colors[i]);
    }
  }
}
