package datastructure_algorithms.datastructure.nonlinear.tree.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.IntStream;

// Undirected, Unweighted Graph
public final class GraphAdMatrix {

  private int[][] matrix;
  private int vertices; // No. of vertices

  public GraphAdMatrix(int vertices) {
    this.vertices = vertices;
    this.matrix = new int[vertices][vertices];
  }

  public void addEdge(int i, int j) {
    if (i >= vertices || j >= vertices) {
      throw new IllegalArgumentException(
          String.format("Edge index beyond the vertices size: [%s]", vertices));
    }
    matrix[i][j] = 1;
    matrix[j][i] = 1;
  }

  public void removeEdge(int i, int j) {
    if (i >= vertices || j >= vertices) {
      throw new IllegalArgumentException(
          String.format("Edge index beyond the vertices size: [%s]", vertices));
    }
    matrix[i][j] = 0;
    matrix[j][i] = 0;
  }

  // Adds new vertices having size more than the previous vertices.
  // Creates new matrix with new size and copy relevant data from old matrix.
  public void addVertex(int num) {
    if (num <= 0) {
      return;
    }
    int newVertices = vertices + num;
    int[][] newMatrix = new int[newVertices][newVertices];
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        newMatrix[i][j] = matrix[i][j];
      }
    }
    this.matrix = newMatrix;
    this.vertices = newVertices;
  }

  // Removes some vertices having size less than the previous vertices.
  // Creates new matrix with new lesser size and copy relevant data from old matrix.
  public void removeVertex(int num) {
    if (num <= 0 || num > this.vertices) {
      return;
    } else if (num == this.vertices) { // Reset the graph
      this.matrix = new int[0][0];
      this.vertices = 0;
      return;
    }
    int newVertices = this.vertices - num;
    int[][] newMatrix = new int[newVertices][newVertices];
    for (int i = 0; i < newMatrix.length; i++) {
      for (int j = 0; j < newMatrix[i].length; j++) {
        newMatrix[i][j] = this.matrix[i][j];
      }
    }
    this.matrix = newMatrix;
    this.vertices = newVertices;
  }

  public void dfs() {
    boolean[] visited = new boolean[vertices];
    dfs(0, visited);
  }

  private void dfs(int index, boolean[] visited) {
    visited[index] = true;
    System.out.print(index + ", ");
    for (int i = 0; i < vertices; i++) {
      if (matrix[index][i] != 0 && !visited[i]) {
        dfs(i, visited);
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
        for (int i = vertices - 1; i >= 0; i--) {
          if (matrix[node][i] != 0 && !visited[i]) {
            stack.push(i);
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
        for (int i = 0; i < vertices; i++) {
          if (matrix[node][i] != 0 && !visited[i]) {
            queue.offer(i);
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

    for (int i = 0; i < this.vertices; i++) {
      if (matrix[source][i] != 0 && !visited[i]) {
        getAllRoutes(i, destination, visited, routes, path);
      }
    }

    visited[source] = false;
    path.remove(path.size() - 1);
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("Graph:").append("\n").append("   ");
    IntStream.range(0, vertices)
        .forEach(index -> builder.append(getVerticesNameMap().get(index)).append(", "));
    builder.append("\n");
    for (int i = 0; i < matrix.length; i++) {
      builder.append(getVerticesNameMap().get(i)).append(": ");
      for (int j = 0; j < matrix[i].length; j++) {
        builder.append(matrix[i][j]).append(", ");
      }
      builder.append("\n");
    }
    return builder.toString();
  }

  // Optional: For vertices naming purpose
  private Map<Integer, String> getVerticesNameMap() {
    Map<Integer, String> verticesNameMap = new HashMap<>();
    verticesNameMap.put(0, "A");
    verticesNameMap.put(1, "B");
    verticesNameMap.put(2, "C");
    verticesNameMap.put(3, "D");
    verticesNameMap.put(4, "E");
    verticesNameMap.put(5, "F");
    verticesNameMap.put(6, "G");
    verticesNameMap.put(7, "H");
    verticesNameMap.put(8, "I");
    verticesNameMap.put(9, "J");
    verticesNameMap.put(10, "K");
    verticesNameMap.put(11, "L");
    verticesNameMap.put(12, "M");
    verticesNameMap.put(13, "N");
    verticesNameMap.put(14, "O");
    verticesNameMap.put(15, "P");
    verticesNameMap.put(16, "Q");
    verticesNameMap.put(17, "R");
    verticesNameMap.put(18, "S");
    verticesNameMap.put(19, "T");
    verticesNameMap.put(20, "U");
    verticesNameMap.put(21, "V");
    verticesNameMap.put(22, "W");
    verticesNameMap.put(23, "X");
    verticesNameMap.put(24, "Y");
    verticesNameMap.put(25, "Z");
    return verticesNameMap;
  }

  public static void main(String[] args) {
    GraphAdMatrix graph = new GraphAdMatrix(6);
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
    graph.getAllPossibleRoutes(0, 3);
  }
}
