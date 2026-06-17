package datastructure_algorithms.datastructure.nonlinear.tree.graph.connectivity;

import datastructure_algorithms.datastructure.nonlinear.tree.graph.Edge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;

public class Kosaraju {

  private List<List<Edge>> adj;
  private int vertices; // No. of vertices

  public Kosaraju(int vertices) {
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


  private String printPath(int destination, int[] parent) {
    if (destination == 0) {
      return destination + "";
    }

    String path = printPath(parent[destination], parent);

    return path + ", " + destination;
  }

  public List<List<Integer>> findSCC() {
    // Step 1: Fill order of vertices through first DFS (finish times)
    Stack<Integer> stack = new Stack<>();
    boolean[] visited = new boolean[vertices];
    for (int i = 0; i < vertices; i++) {
      if (!visited[i]) {
        dfsFillOrder(i, visited, stack);
      }
    }

    // Step 2: Transpose the graph (Reverse graph edges)
    List<List<Edge>> transpose = getTranspose();

    // Step 3: Process all vertices in order defined by stack
    List<List<Integer>> result = new ArrayList<>();
    Arrays.fill(visited, false);

    while (!stack.isEmpty()) {
      int node = stack.pop();
      if (!visited[node]) {
        List<Integer> component = new ArrayList<>();
        dfsCollectSCC(node, visited, transpose, component);
        result.add(component);
      }
    }

    return result;
  }

  // DFS to fill vertices in stack according to finish times
  private void dfsFillOrder(int node, boolean[] visited, Stack<Integer> stack) {
    visited[node] = true;
    for (Edge edge : adj.get(node)) {
      if (!visited[edge.getTo()]) {
        dfsFillOrder(edge.getTo(), visited, stack);
      }
    }
    stack.push(node);
  }

  // DFS on transposed graph to collect SCC
  private void dfsCollectSCC(int node, boolean[] visited, List<List<Edge>> transpose, List<Integer> component) {
    visited[node] = true;
    component.add(node);
    for (Edge edge : transpose.get(node)) {
      if (!visited[edge.getTo()]) {
        dfsCollectSCC(edge.getTo(), visited, transpose, component);
      }
    }
  }

  // Transpose the graph
  private List<List<Edge>> getTranspose() {
    List<List<Edge>> transpose = new ArrayList<>();
    for (int i = 0; i < vertices; i++) {
      transpose.add(new ArrayList<>());
    }

    for (int i = 0; i < vertices; i++) {
      for (Edge edge : adj.get(i)) {
        transpose.get(edge.getTo()).add(new Edge(i));
      }
    }
    return transpose;
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
    Kosaraju graph = new Kosaraju(5);
    graph.addEdge(1, 0);
    graph.addEdge(0, 2);
    graph.addEdge(2, 1);
    graph.addEdge(0, 3);
    graph.addEdge(3, 4);
    System.out.println(graph);
    List<List<Integer>> sccs = graph.findSCC();
    System.out.println("Strongly Connected Components:");
    for (List<Integer> scc : sccs) {
      System.out.println(scc);
    }
  }
}
