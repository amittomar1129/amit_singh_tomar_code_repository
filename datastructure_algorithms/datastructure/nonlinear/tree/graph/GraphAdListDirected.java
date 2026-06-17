package datastructure_algorithms.datastructure.nonlinear.tree.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.IntStream;

public class GraphAdListDirected {

  private List<List<Edge>> adj;
  private int vertices; // No. of vertices

  public GraphAdListDirected(int vertices) {
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

  public void dfs() {
    boolean[] visited = new boolean[vertices];
    dfs(0, visited);
  }

  private void dfs(int startIndex, boolean[] visited) {
    visited[startIndex] = true;

    System.out.print(startIndex + ", ");

    for (Edge edge : adj.get(startIndex)) {
      if (!visited[edge.getTo()]) {
        dfs(edge.getTo(), visited);
      }
    }
  }

  private void dfsUsingStack() {
    Stack<Integer> stack = new Stack<>();
    boolean[] visited = new boolean[vertices];

    stack.push(0);
    while (!stack.isEmpty()) {
      Integer node = stack.pop();

      if (!visited[node]) {
        System.out.print(node + ", ");
        visited[node] = true;
        for (int i = adj.get(node).size() - 1; i >= 0; i--) {
          if (!visited[adj.get(node).get(i).getTo()]) {
            stack.push(adj.get(node).get(i).getTo());
          }
        }
      }
    }
  }

  public void bfs() {
    Queue<Integer> queue = new LinkedList<>();
    boolean[] visited = new boolean[vertices];

    queue.offer(0);
    while (!queue.isEmpty()) {
      Integer node = queue.poll();
      if (!visited[node]) {
        System.out.print(node + ", ");
        visited[node] = true;

        for (Edge edge : adj.get(node)) {
          if (!visited[edge.getTo()]) {
            queue.offer(edge.getTo());
          }
        }
      }
    }
  }

  // Topological Sort using DFS
  public void topologicalSortUsingDfs() {
    Stack<Integer> stack = new Stack<>();
    boolean[] visited = new boolean[this.vertices];
    topologicalDfsHelper(0, stack, visited);
    System.out.print("Topological Order DFS: ");
    while (!stack.isEmpty()) {
      System.out.print(stack.pop() + ", ");
    }
  }

  private void topologicalDfsHelper(int node, Stack stack, boolean[] visited) {
    visited[node] = true;

    for (Edge edge : adj.get(node)) {
      if (!visited[edge.getTo()]) {
        topologicalDfsHelper(edge.getTo(), stack, visited);
      }
    }

    stack.push(node);
  }

  // Topological Sort using Kahn`s Algorithm BFS
  public void topologicalSortUsingBfs() {
    System.out.print("Topological Order BFS (Kahn`s Algorithm): ");
    int[] inDegrees = new int[this.vertices];

    for (List<Edge> list : adj) {
      for (Edge edge : list) {
        inDegrees[edge.getTo()]++;
      }
    }

    Queue<Integer> queue = new LinkedList<>();
    int visited = 0;
    for (int i = 0; i < vertices; i++) {
      if (inDegrees[i] == 0) {
        queue.offer(i);
      }
    }
    while (!queue.isEmpty()) {
      Integer node = queue.poll();
      System.out.print(node + ", ");
      visited++;

      for (Edge edge : adj.get(node)) {
        if (--inDegrees[edge.getTo()] == 0) {
          queue.offer(edge.getTo());
        }
      }
    }
    if (visited != this.vertices) {
      System.out.println("Graph has a cycle! Topological sort not possible.");
    }
  }

  // Find Longest Path in DAG using Topological Order
  public void findLongestPathInDAG(int source) {
    int[] distance = new int[this.vertices];
    int[] parent = new int[this.vertices];
    Arrays.fill(distance, Integer.MIN_VALUE);
    Arrays.fill(parent, -1);
    distance[source] = 0;

    Stack<Integer> stack = new Stack();
    boolean[] visited = new boolean[this.vertices];
    topologicalDfsHelper(source, stack, visited);

    while (!stack.isEmpty()) {
      int node = stack.pop();
      if (distance[node] != Integer.MIN_VALUE) {
        for (Edge edge : adj.get(node)) {
          if (distance[edge.getTo()] < distance[node] + edge.getWeight()) {
            distance[edge.getTo()] = distance[node] + edge.getWeight();
            parent[edge.getTo()] = node;
          }
        }
      }
    }
    System.out.println("Longest distances from source " + source + ":");
    for (int i = 0; i < vertices; i++) {
      if (distance[i] == Integer.MIN_VALUE) {
        System.out.print("INF | Unreachable");
        System.out.println();
      } else {
        System.out.print(distance[i] + " | Path: ");
        printPath(parent, i);
        System.out.println();
      }
    }
  }

  // Utility to print path using parent[]
  private void printPath(int[] parent, int node) {
    if (node == -1) {
      return;
    }
    printPath(parent, parent[node]);
    System.out.print(node + " ");
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
    GraphAdListDirected graph = new GraphAdListDirected(6);
    graph.addEdge(0, 1);
    graph.addEdge(0, 3);
    graph.addEdge(0, 5);
    graph.addEdge(1, 2);
    graph.addEdge(2, 3);
    graph.addEdge(4, 3);
    System.out.print(graph);
    graph.dfs();
    System.out.println();
    graph.dfsUsingStack();
    System.out.println();
    graph.bfs();
    System.out.println("\nTopological Sort DFS-------------------------------");
    graph.removeVertex(6); // Resetting/deleting the graph
    graph.addVertex(5); // Creates the new graph with 5 vertices
    graph.addEdge(0, 1);
    graph.addEdge(0, 3);
    graph.addEdge(1, 2);
    graph.addEdge(1, 3);
    graph.addEdge(3, 2);
    graph.addEdge(3, 4);
    System.out.print(graph);

    graph.topologicalSortUsingDfs();
    System.out.println();
    graph.topologicalSortUsingBfs();
    System.out.println("\n-------------------------------------------------------");
    GraphAdListDirected graph1 = new GraphAdListDirected(6);
    graph1.addEdge(0, 1, 5);
    graph1.addEdge(0, 2, 3);
    graph1.addEdge(1, 3, 6);
    graph1.addEdge(1, 2, 2);
    graph1.addEdge(2, 4, 4);
    graph1.addEdge(2, 5, 2);
    graph1.addEdge(2, 3, 7);
    graph1.addEdge(3, 5, 1);
    graph1.addEdge(3, 4, -1);
    graph1.addEdge(4, 5, -2);
    System.out.println(graph1);
    graph1.findLongestPathInDAG(1);
  }
}
