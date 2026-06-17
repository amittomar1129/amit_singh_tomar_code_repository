package datastructure_algorithms.datastructure.nonlinear.tree.graph.bipartite;

import datastructure_algorithms.datastructure.nonlinear.tree.graph.Edge;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class FordFulkerson {

  private List<List<Edge>> adj;
  private int vertices; // No. of vertices

  public FordFulkerson(int vertices) {
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

  public void addEdgeWithReverse(int i, int j, int weight) {
    if (i >= vertices || j >= vertices) {
      throw new IllegalArgumentException(
          String.format("Edge index beyond the vertices size: [%s]", vertices));
    }
    Edge newEdge = new Edge(j, weight);
    Edge backEdge = new Edge(i, 0);
    newEdge.setReverseEdge(backEdge);
    backEdge.setReverseEdge(newEdge);
    if (!adj.get(i).contains(newEdge)) {
      adj.get(i).add(newEdge);
    }
    if (!adj.get(j).contains(backEdge)) {
      adj.get(j).add(backEdge);
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

  public int findMaximumFlow(int source, int sink) {
    int maxFlow = 0;
    while (true) {
      boolean[] visited = new boolean[vertices];
      int flow = dfs(source, sink, Integer.MAX_VALUE, visited);
      if (flow == 0) {
        break;
      }
      maxFlow += flow;
    }
    return maxFlow;
  }

  private int dfs(int source, int sink, int flow, boolean[] visited) {
    if (source == sink) {
      return flow;
    }
    visited[source] = true;

    for (Edge edge : adj.get(source)) {
      if (edge.getWeight() > 0 && !visited[edge.getTo()]) {
        int bottleneck = dfs(edge.getTo(), sink, Math.min(flow, edge.getWeight()), visited);
        if (bottleneck > 0) {
          edge.setWeight(edge.getWeight() - bottleneck);
          edge.getReverseEdge().setWeight(edge.getReverseEdge().getWeight() + bottleneck);
          return bottleneck;
        }
      }
    }
    return 0;
  }

  @Override
  public String toString() {
    StringBuilder builder =
        new StringBuilder("Graph:").append(" V = ").append(this.vertices).append("\n");

    IntStream.range(0, adj.size())
        .forEach(index -> builder.append(index).append(": ").append(adj.get(index)).append("\n"));

    return builder.toString();
  }

  public static void main(String[] args) {
    FordFulkerson graph = new FordFulkerson(6);
    graph.addEdgeWithReverse(0, 1, 10);
    graph.addEdgeWithReverse(0, 3, 10);
    graph.addEdgeWithReverse(1, 2, 4);
    graph.addEdgeWithReverse(1, 3, 2);
    graph.addEdgeWithReverse(1, 4, 8);
    graph.addEdgeWithReverse(2, 5, 10);
    graph.addEdgeWithReverse(3, 4, 9);
    graph.addEdgeWithReverse(4, 2, 6);
    graph.addEdgeWithReverse(4, 5, 10);
    System.out.println(graph);
    System.out.println("Maximum Flow: " + graph.findMaximumFlow(0, 5));
  }
}
