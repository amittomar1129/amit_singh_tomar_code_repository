package datastructure_algorithms.datastructure.nonlinear.tree.graph.planar;

import datastructure_algorithms.datastructure.nonlinear.tree.graph.Edge;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.IntStream;

public class Euler {

  private List<List<Edge>> adj;
  private int vertices; // No. of vertices

  public Euler(int vertices) {
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

  public boolean isPlanar() {
    if (vertices < 3) return true; // trivially planar

    if (!isConnected()) {
      System.out.println("Graph must be connected for Euler test.");
      return false;
    }

    int edgeCount = getEdgeCount();
    int upperBound = 3 * vertices - 6;

    return edgeCount <= upperBound;
  }

  // Count edges in undirected graph
  private int getEdgeCount() {
    int count = 0;
    for (int i = 0; i < vertices; i++) {
      count += adj.get(i).size();
    }
    return count / 2; // undirected counted twice
  }

  // Check if graph is connected using BFS
  private boolean isConnected() {
    boolean[] visited = new boolean[vertices];
    Queue<Integer> queue = new LinkedList<>();
    queue.add(0);
    visited[0] = true;

    int visitedCount = 1;
    while (!queue.isEmpty()) {
      int u = queue.poll();
      for (Edge e : adj.get(u)) {
        if (!visited[e.getTo()]) {
          visited[e.getTo()] = true;
          queue.add(e.getTo());
          visitedCount++;
        }
      }
    }
    return visitedCount == vertices;
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
    Euler graph = new Euler(6);
    graph.addEdge(0, 1);
    graph.addEdge(0, 2);
    graph.addEdge(0, 3);
    graph.addEdge(1, 2);
    graph.addEdge(2, 3);
    graph.addEdge(3, 4);
    graph.addEdge(4, 5);

    System.out.println("Is Graph Planar? " + graph.isPlanar());
  }
}
