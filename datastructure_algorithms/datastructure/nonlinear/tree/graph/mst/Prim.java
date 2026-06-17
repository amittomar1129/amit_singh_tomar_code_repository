package datastructure_algorithms.datastructure.nonlinear.tree.graph.mst;

import datastructure_algorithms.datastructure.nonlinear.tree.graph.Edge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Prim {

  private List<List<Edge>> adj;
  private int vertices; // No. of vertices

  public Prim(int vertices) {
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

  public List<Edge> primsMST() {
    List<Edge> result = new ArrayList<>();
    boolean[] visited = new boolean[vertices];

    PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
    queue.offer(new int[]{-1, 0, 0}); // {parent, node, weight}

    while (!queue.isEmpty() && result.size() < vertices - 1) {
      int[] curr = queue.poll();
      int parent = curr[0], node = curr[1], weight = curr[2];

      if (visited[node]) {
        continue;
      }
      visited[node] = true;

      if (parent != -1) {
        Edge edge = new Edge(parent, node, weight);
        result.add(edge);
      }

      for (Edge edge : adj.get(node)) {
        if (!visited[edge.getTo()]) {
          queue.offer(new int[]{node, edge.getTo(), edge.getWeight()});
        }
      }
    }
    return result;
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
    Prim graph = new Prim(4);
    graph.addEdge(0, 1, 1);
    graph.addEdge(0, 2, 4);
    graph.addEdge(0, 3, 3);
    graph.addEdge(1, 3, 2);
    graph.addEdge(2, 3, 5);
    System.out.println(graph);
    List<Edge> mst = graph.primsMST();
    List<String> mstEdges = mst.stream().map(edge -> edge.getFrom() + "->" + edge.getTo())
        .collect(Collectors.toList());
    int totalWeight = mst.stream().mapToInt(e -> e.getWeight()).sum();
    System.out.println("MST total weight: " + totalWeight);
    System.out.println("MST edges: " + mstEdges);
  }
}
