package datastructure_algorithms.datastructure.nonlinear.tree.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.IntStream;

public class GraphAdList {

  private List<List<Integer>> adj;
  private int vertices; // No. of vertices

  public GraphAdList(int vertices) {
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
    if (!adj.get(i).contains(Integer.valueOf(j))) {
      adj.get(i).add(j);
    }
    if (!adj.get(j).contains(Integer.valueOf(i))) {
      adj.get(j).add(i);
    }
  }

  public void removeEdge(int i, int j) {
    if (i >= vertices || j >= vertices) {
      throw new IllegalArgumentException(
          String.format("Edge index beyond the vertices size: [%s]", vertices));
    }
    adj.get(i).remove(Integer.valueOf(j));
    adj.get(j).remove(Integer.valueOf(i));
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
      for (int j = 0; j < num; j++) {
        adj.get(i).remove(Integer.valueOf(vertices - 1 - j));
      }
    }
    this.vertices = newVertices;
  }

  public void dfs() {
    boolean[] visited = new boolean[vertices];
    dfs(0, visited);
  }

  private void dfs(int index, boolean[] visited) {
    visited[index] = true;

    System.out.print(index + ", ");

    for (int node : adj.get(index)) {
      if (!visited[node]) {
        dfs(node, visited);
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
          if (!visited[adj.get(node).get(i)]) {
            stack.push(adj.get(node).get(i));
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

        for (int n : adj.get(node)) {
          if (!visited[n]) {
            queue.offer(n);
          }
        }
      }
    }
  }

  private void getAllPossibleRoutes(int source, int destination) {
    List<List<Integer>> routes = new ArrayList();
    boolean[] visited = new boolean[vertices];
    List<Integer> path = new ArrayList<>();
    getAllRoutes(source, destination, visited, routes, path);
    System.out.println(routes);
  }

  private void getAllRoutes(int source, int destination, boolean[] visited,
      List<List<Integer>> routes, List<Integer> path) {
    visited[source] = true;
    path.add(source);
    if (source == destination) {
      routes.add(new ArrayList<>(path));
      visited[source] = false;
      path.remove(path.size() - 1);
      return;
    }

    for (int node : adj.get(source)) {
      if (!visited[node]) {
        getAllRoutes(node, destination, visited, routes, path);
      }
    }

    visited[source] = false;
    path.remove(path.size() - 1);
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
    GraphAdList graph = new GraphAdList(6);
    System.out.println(graph);
    graph.addEdge(0, 1);
    graph.addEdge(0, 3);
    graph.addEdge(0, 5);
    graph.addEdge(1, 4);
    graph.addEdge(2, 3);
    graph.addEdge(3, 0);
    graph.addEdge(3, 2);
    graph.addEdge(4, 3);
    graph.addEdge(4, 4);
    System.out.println(graph);
    graph.dfs();
    System.out.println();
    graph.dfsUsingStack();
    System.out.println();
    graph.bfs();
//    graph.getAllPossibleRoutes(0, 3);
  }
}
