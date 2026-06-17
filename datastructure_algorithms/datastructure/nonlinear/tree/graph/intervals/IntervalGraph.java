package datastructure_algorithms.datastructure.nonlinear.tree.graph.intervals;

import datastructure_algorithms.datastructure.nonlinear.tree.graph.Edge;
import datastructure_algorithms.datastructure.nonlinear.tree.graph.coloring.Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class IntervalGraph {

  private List<List<Edge>> adj;
  private int vertices; // No. of vertices

  public IntervalGraph(int vertices) {
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

  public boolean isIntervalGraph() {
    // Step 1: Generate LexBFS ordering
    List<Integer> order = lexBfs(0);

    // Step 2: Check if Chordal (must have PEO)
    if (!isChordal(order)) {
      return false;
    }

    // Step 3: Consecutive clique check (interval property)
    return checkConsecutiveCliques(order);
  }

  // ? LexBFS with dynamic label priority queue
  private List<Integer> lexBfs(int start) {
    List<Integer> order = new ArrayList<>();
    List<LinkedList<Integer>> labels = new ArrayList<>();
    boolean[] visited = new boolean[vertices];
    for (int i = 0; i < vertices; i++) {
      labels.add(new LinkedList<>());
    }

    PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> {
      LinkedList<Integer> la = labels.get(a);
      LinkedList<Integer> lb = labels.get(b);
      int cmp = compareLabels(la, lb);
      return (cmp != 0) ? cmp : Integer.compare(a, b);
    });

    queue.offer(start);

    while (!queue.isEmpty()) {
      int node = queue.poll();
      if (visited[node]) {
        continue;
      }
      visited[node] = true;
      order.add(node);

      int stamp = order.size();
      for (Edge edge : adj.get(node)) {
        int i = edge.getTo();
        if (!visited[i]) {
          labels.get(i).addFirst(stamp);
          queue.offer(i);
        }
      }
    }
    return order;
  }

  private int compareLabels(LinkedList<Integer> a, LinkedList<Integer> b) {
    Iterator<Integer> ia = a.iterator();
    Iterator<Integer> ib = b.iterator();
    while (ia.hasNext() && ib.hasNext()) {
      int x = ia.next(), y = ib.next();
      if (x != y) {
        return Integer.compare(y, x);
      }
    }
    return Integer.compare(b.size(), a.size());
  }

  // ? Check for Perfect Elimination Ordering ? graph must be chordal
  private boolean isChordal(List<Integer> order) {
    int[] perfectOrder = new int[vertices];
    for (int i = 0; i < vertices; i++) {
      perfectOrder[order.get(i)] = i;
    }

    for (int node : order) {
      int parent = -1;
      for (Edge edge : adj.get(node)) {
        if (perfectOrder[edge.getTo()] > perfectOrder[node] && (parent == -1
            || perfectOrder[edge.getTo()] < perfectOrder[parent])) {
          parent = edge.getTo();
        }
      }
      if (parent == -1) {
        continue;
      }
      for (Edge edge : adj.get(node)) {
        if (edge.getTo() != parent && perfectOrder[edge.getTo()] > perfectOrder[node] && !contains(
            parent, edge.getTo())) {
          return false;
        }
      }
    }
    return true;
  }

  private boolean contains(int u, int v) {
    for (Edge edge : adj.get(u)) {
      if (edge.getTo() == v) {
        return true;
      }
    }
    return false;
  }

  // ? Interval property: neighbors appear consecutively in LexBFS order
  private boolean checkConsecutiveCliques(List<Integer> order) {
    int[] left = new int[vertices];
    int[] right = new int[vertices];
    int[] perfectOrdering = new int[vertices];

    Arrays.fill(left, Integer.MAX_VALUE);
    Arrays.fill(right, -1);
    for (int i = 0; i < vertices; i++) {
      perfectOrdering[order.get(i)] = i;
    }

    for (int i = 0; i < vertices; i++) {
      for (Edge edge : adj.get(i)) {
        left[i] = Math.min(left[i], perfectOrdering[edge.getTo()]);
        right[i] = Math.max(right[i], perfectOrdering[edge.getTo()]);
      }
    }

    // must be fully connected inside its interval span
    for (int i = 0; i < vertices; i++) {
      for (int j = left[i]; j <= right[i]; j++) {
        int v = order.get(j);
        if (i != v && !contains(i, v)) {
          return false;
        }
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
    IntervalGraph graph = new IntervalGraph(8);
    graph.addEdge(0, 1);
    graph.addEdge(0, 2);
    graph.addEdge(1, 3);
    graph.addEdge(1, 4);
    graph.addEdge(2, 4);
    graph.addEdge(2, 5);
    graph.addEdge(3, 6);
    graph.addEdge(4, 6);
    graph.addEdge(4, 7);
    graph.addEdge(5, 7);
    graph.addEdge(6, 7);
    System.out.println(graph);

    System.out.println("Is Interval Graph: " + graph.isIntervalGraph());
  }
}
