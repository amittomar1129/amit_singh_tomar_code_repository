package datastructure_algorithms.datastructure.nonlinear.tree.graph.cycledetection;

import datastructure_algorithms.datastructure.nonlinear.tree.graph.Edge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.IntStream;

public class CycleDetection {

  private List<List<Edge>> adj;
  private int vertices; // No. of vertices

  public CycleDetection(int vertices) {
    this.vertices = vertices;
    adj = new ArrayList<>();
    for (int i = 0; i < vertices; i++) {
      adj.add(new ArrayList<>());
    }
  }

  public void addEdgeUndirected(int i, int j) {
    if (i >= vertices || j >= vertices) {
      throw new IllegalArgumentException(
          String.format("Edge index beyond the vertices size: [%s]", vertices));
    }
    Edge newEdge = new Edge(j);
    if (!adj.get(i).contains(newEdge)) {
      adj.get(i).add(newEdge);
    }
    Edge newEdge1 = new Edge(i);
    if (!adj.get(j).contains(newEdge1)) {
      adj.get(j).add(newEdge1);
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

  public List<Integer> detectCycleUsingBfs() {
    boolean[] visited = new boolean[vertices];
    int[] parent = new int[vertices];
    Arrays.fill(parent, -1);

    // Check all disconnected components
    for (int i = 0; i < vertices; i++) {
      if (!visited[i]) {
        List<Integer> cycle = bfsDetectCycle(i, visited, parent);
        if (cycle != null) {
          return cycle;
        }
      }
    }
    return null; // no cycle
  }

  private List<Integer> bfsDetectCycle(int start, boolean[] visited, int[] parent) {
    Queue<Integer> queue = new LinkedList<>();
    visited[start] = true;
    queue.offer(start);

    while (!queue.isEmpty()) {
      int node = queue.poll();
      for (Edge edge : adj.get(node)) {
        if (!visited[edge.getTo()]) {
          visited[edge.getTo()] = true;
          parent[edge.getTo()] = node;
          queue.offer(edge.getTo());
        } else if (edge.getTo() != parent[node]) {
          // Cycle detected, reconstruct cycle
          return reconstructCycle(node, edge.getTo(), parent);
        }
      }
    }
    return null;
  }

  private List<Integer> reconstructCycle(int start, int end, int[] parent) {
    List<Integer> path1 = new ArrayList<>();
    List<Integer> path2 = new ArrayList<>();
    Set<Integer> visited = new HashSet<>();

    // Trace path from start to root
    int temp = start;
    while (temp != -1) {
      path1.add(temp);
      visited.add(temp);
      temp = parent[temp];
    }

    // Trace path from end until it meets node1 path
    temp = end;
    while (!visited.contains(temp)) {
      path2.add(temp);
      temp = parent[temp];
    }

    // temp is the common ancestor, include it
    path2.add(temp);

    // Merge paths to form cycle
    Collections.reverse(path1);
    path1.addAll(path2);

    return path1;
  }

  public List<Integer> detectCycleUsingDfs() {
    boolean[] visited = new boolean[vertices];
    boolean[] inStack = new boolean[vertices];
    int[] parent = new int[vertices];
    Arrays.fill(parent, -1);

    for (int i = 0; i < vertices; i++) {
      if (!visited[i]) {
        List<Integer> cycle = dfs(i, visited, inStack, parent);
        if (cycle != null) {
          return cycle;
        }
      }
    }
    return null; // no cycle
  }

  public List<Integer> dfs(int node, boolean[] visited, boolean[] inStack, int[] parent) {
    visited[node] = true;
    inStack[node] = true;

    for (Edge edge : adj.get(node)) {
      if (!visited[edge.getTo()]) {
        parent[edge.getTo()] = node;
        List<Integer> cycle = dfs(edge.getTo(), visited, inStack, parent);
        if (cycle != null) {
          return cycle;
        }
      } else if (inStack[edge.getTo()]) {
        return reconstructCycleDfs(edge.getTo(), node, parent);
      }
    }

    inStack[node] = false;
    return null;
  }

  private static List<Integer> reconstructCycleDfs(int start, int end, int[] parent) {
    List<Integer> cycle = new ArrayList<>();
    cycle.add(start);

    int temp = end;
    while (temp != start && temp != -1) {
      cycle.add(temp);
      temp = parent[temp];
    }
    cycle.add(start);
    Collections.reverse(cycle);
    return cycle;
  }

  public List<Integer> detectCycleUsingDfsUndirected() {
    boolean[] visited = new boolean[vertices];
    int[] parent = new int[vertices];
    Arrays.fill(parent, -1);

    for (int i = 0; i < vertices; i++) {
      if (!visited[i]) {
        List<Integer> cycle = dfs(i, -1, visited, parent);
        if (cycle != null) {
          return cycle;
        }
      }
    }
    return null;
  }

  public List<Integer> dfs(int node, int parentNode, boolean[] visited, int[] parent) {
    visited[node] = true;
    parent[node] = parentNode;

    for (Edge i : adj.get(node)) {
      if (!visited[i.getTo()]) {
        List<Integer> cycle = dfs(i.getTo(), node, visited, parent);
        if (cycle != null) {
          return cycle;
        }
      } else if (i.getTo() != parentNode) {
        // Found a cycle
        return reconstructCycleDfsUndirected(node, i.getTo(), parent);
      }
    }
    return null;
  }

  private static List<Integer> reconstructCycleDfsUndirected(int start, int end, int[] parent) {
    List<Integer> path1 = new ArrayList<>();
    List<Integer> path2 = new ArrayList<>();
    Set<Integer> visited = new HashSet<>();

    // trace path from start to root
    int temp = start;
    while (temp != -1) {
      path1.add(temp);
      visited.add(temp);
      temp = parent[temp];
    }

    // trace path from end until it meets start path
    temp = end;
    while (!visited.contains(temp)) {
      path2.add(temp);
      temp = parent[temp];
    }

    // merge paths
    path2.add(temp);
    Collections.reverse(path1);
    path1.addAll(path2);
    return path1;
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
    CycleDetection graph = new CycleDetection(5);
    graph.addEdgeUndirected(0, 1);
    graph.addEdgeUndirected(1, 2);
    graph.addEdgeUndirected(2, 3);
    graph.addEdgeUndirected(3, 0); // introduces a cycle

    System.out.println("Cycle present: " + graph.detectCycleUsingBfs());

    CycleDetection graph1 = new CycleDetection(5);
    graph1.addEdge(0, 1);
    graph1.addEdge(1, 2);
    graph1.addEdge(2, 3);
    graph1.addEdge(3, 1);
    graph1.addEdge(3, 4);

    System.out.println("Cycle present: " + graph1.detectCycleUsingDfs());

    CycleDetection graph2 = new CycleDetection(5);
    graph2.addEdgeUndirected(0, 1);
    graph2.addEdgeUndirected(1, 2);
    graph2.addEdgeUndirected(2, 3);
    graph2.addEdgeUndirected(3, 1);
    graph2.addEdgeUndirected(3, 4);

    System.out.println("Cycle present: " + graph2.detectCycleUsingDfsUndirected());
  }

}
