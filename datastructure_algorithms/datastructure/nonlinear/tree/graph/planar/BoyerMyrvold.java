package datastructure_algorithms.datastructure.nonlinear.tree.graph.planar;

import datastructure_algorithms.datastructure.nonlinear.tree.graph.Edge;
import datastructure_algorithms.datastructure.nonlinear.tree.graph.mst.Prim;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BoyerMyrvold {

  private List<List<Edge>> adj;
  private int vertices; // No. of vertices

  public BoyerMyrvold(int vertices) {
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
    return !containsK5() && !containsK33();
  }

  private boolean containsK5() {
    for (int i = 0; i < vertices; i++) {
      if (degree(i) < 4) {
        continue;
      }
      if (isClique5(i)) {
        return true;
      }
    }
    return false;
  }

  // A simple check to see if neighbors of a node form strong clique
  private boolean isClique5(int node) {
    List<Integer> neighbors = getNeighbors(node);
    if (neighbors.size() < 4) {
      return false;
    }

    int edgesBetweenNeighbors = 0;
    for (int i = 0; i < neighbors.size(); i++) {
      for (int j = i + 1; j < neighbors.size(); j++) {
        if (isConnected(neighbors.get(i), neighbors.get(j))) {
          edgesBetweenNeighbors++;
        }
      }
    }
    // Minimum edges needed between 4 neighbors to resemble K5 structure
    return edgesBetweenNeighbors >= 6;
  }

  private boolean containsK33() {
    for (int i = 0; i < vertices; i++) {
      for (int j = i + 1; j < vertices; j++) {
        if (degree(i) < 3 || degree(j) < 3) {
          continue;
        }
        if (isK33(i, j)) {
          return true;
        }
      }
    }
    return false;
  }

  private boolean isK33(int i, int j) {
    Set<Integer> common = new HashSet<>(getNeighbors(i));
    common.retainAll(getNeighbors(j));
    return common.size() >= 3;
  }

  private boolean isConnected(int i, int j) {
    for (Edge edge : adj.get(i)) {
      if (edge.getTo() == j) {
        return true;
      }
    }
    return false;
  }

  private int degree(int node) {
    return adj.get(node).size();
  }

  private List<Integer> getNeighbors(int node) {
    List<Integer> list = new ArrayList<>();
    for (Edge edge : adj.get(node)) {
      list.add(edge.getTo());
    }
    return list;
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
    BoyerMyrvold graph = new BoyerMyrvold(6);
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
