package datastructure_algorithms.datastructure.nonlinear.tree.graph.mst;

import datastructure_algorithms.datastructure.nonlinear.tree.graph.Edge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Kruskal {

  private List<List<Edge>> adj;
  private int vertices; // No. of vertices

  int[] parent;
  int[] rank;

  public Kruskal(int vertices) {
    this.vertices = vertices;
    adj = new ArrayList<>();
    for (int i = 0; i < vertices; i++) {
      adj.add(new ArrayList<>());
    }
    // For Union Set
    parent = new int[vertices];
    rank = new int[vertices];
    for (int i = 0; i < vertices; i++) {
      parent[i] = i;
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

  public List<Edge> kruskalMST() {
    boolean[][] visited = new boolean[vertices][vertices];
    List<Edge> edges = new ArrayList<>();

    for (int i = 0; i < vertices; i++) {
      for (Edge edge : adj.get(i)) {
        if (!visited[i][edge.getTo()]) {
          edges.add(new Edge(i, edge.getTo(), edge.getWeight()));
          visited[i][edge.getTo()] = visited[edge.getTo()][i] = true; // mark both directions
        }
      }
    }

    Collections.sort(edges); // sort edges by weight
    List<Edge> result = new ArrayList<>();

    for (Edge edge : edges) {
      if (union(edge.getFrom(), edge.getTo())) {
        result.add(edge);
      }
    }
    return result;
  }

  int find(int node, int[] parent) {
    if (parent[node] != node) {
      parent[node] = find(parent[node], parent);
    }
    return parent[node];
  }

  boolean union(int source, int destination) {
    int sourceRoot = find(source, parent);
    int sourceDestination = find(destination, parent);
    if (sourceRoot == sourceDestination) {
      return false; // cycle
    }
    if (rank[sourceDestination] > rank[sourceRoot]) {
      parent[sourceRoot] = sourceDestination;
      rank[sourceDestination] += rank[sourceRoot];
    } else {
      parent[sourceDestination] = sourceRoot;
      rank[sourceRoot] += rank[sourceDestination];
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
    Kruskal graph = new Kruskal(4);
    graph.addEdge(0, 1, 1);
    graph.addEdge(0, 2, 4);
    graph.addEdge(0, 3, 3);
    graph.addEdge(1, 3, 2);
    graph.addEdge(2, 3, 5);
    System.out.println(graph);
    List<Edge> mst = graph.kruskalMST();
    List<String> mstEdges = mst.stream().map(edge -> edge.getFrom() + "->" + edge.getTo())
        .collect(Collectors.toList());
    int totalWeight = mst.stream().mapToInt(e -> e.getWeight()).sum();
    System.out.println("MST total weight: " + totalWeight);
    System.out.println("MST edges: " + mstEdges);
  }
}
