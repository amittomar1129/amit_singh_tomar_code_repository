package datastructure_algorithms.datastructure.nonlinear.tree.graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.IntStream;

// Undirected, Unweighted Graph
public final class GraphAdMatrixDirected {

  private int[][] matrix;
  private int vertices; // No. of vertices

  public GraphAdMatrixDirected(int vertices) {
    this.vertices = vertices;
    this.matrix = new int[vertices][vertices];
  }

  public void addEdge(int i, int j) {
    if (i >= vertices || j >= vertices) {
      throw new IllegalArgumentException(
          String.format("Edge index beyond the vertices size: [%s]", vertices));
    }
    matrix[i][j] = 1;
  }

  public void addEdge(int i, int j, int weight) {
    if (i >= vertices || j >= vertices) {
      throw new IllegalArgumentException(
          String.format("Edge index beyond the vertices size: [%s]", vertices));
    }
    matrix[i][j] = weight;
  }

  public void removeEdge(int i, int j) {
    if (i >= vertices || j >= vertices) {
      throw new IllegalArgumentException(
          String.format("Edge index beyond the vertices size: [%s]", vertices));
    }
    matrix[i][j] = 0;
  }

  // Adds new vertices having size more than the previous vertices.
  // Creates new matrix with new size and copy relevant data from old matrix.
  public void addVertices(int num) {
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

  // Adds a new vertex on the given index in the graph.
  public void addVertex(int index) {
    if (index < 0 && index > this.vertices) {
      return;
    }

    int newVertices = this.vertices + 1;
    int[][] newMatrix = new int[newVertices][newVertices];

    for (int i = 0; i < matrix.length; i++) {
      if (i < index) {
        for (int j = 0; j < matrix[i].length; j++) {
          if (j < index) {
            newMatrix[i][j] = matrix[i][j];
          } else {
            if (j == index) {
              newMatrix[i][j] = 0;
            }
            newMatrix[i][j + 1] = matrix[i][j];
          }
        }
      } else {
        for (int j = 0; j < matrix[i].length; j++) {
          if (i == index) {
            newMatrix[i][j] = 0;
          }
          if (j < index) {
            newMatrix[i + 1][j] = matrix[i][j];
          } else {
            if (j == index) {
              newMatrix[i][j] = 0;
            }
            newMatrix[i + 1][j + 1] = matrix[i][j];
          }
        }
      }
    }

    this.matrix = newMatrix;
    this.vertices = newVertices;
  }

  // Removes some vertices having size less than the previous vertices.
  // Creates new matrix with new lesser size and copy relevant data from old matrix.
  public void removeVertices(int num) {
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

  // Removes a vertex that present on give index in graph.
  public void removeVertex(int index) {
    if (index < 0 && index >= vertices) {
      return;
    }
    int newVertices = this.vertices - 1;
    int[][] newMatrix = new int[newVertices][newVertices];

    for (int i = 0; i < matrix.length - 1; i++) {
      if (i < index) {
        for (int j = 0; j < matrix[i].length - 1; j++) {
          if (j < index) {
            newMatrix[i][j] = this.matrix[i][j];
          } else {
            newMatrix[i][j] = this.matrix[i][j + 1];
          }
        }
      } else {
        for (int j = 0; j < matrix[i].length - 1; j++) {
          if (j < index) {
            newMatrix[i][j] = this.matrix[i + 1][j];
          } else {
            newMatrix[i][j] = this.matrix[i + 1][j + 1];
          }
        }
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
    Stack<Integer> nodes = new Stack<>();
    boolean[] visited = new boolean[vertices];

    nodes.push(0);
    while (!nodes.isEmpty()) {
      Integer node = nodes.pop();
      if (!visited[node]) {
        System.out.print(node + ", ");
        visited[node] = true;
        for (int i = vertices - 1; i >= 0; i--) {
          if (matrix[node][i] != 0 && !visited[i]) {
            nodes.push(i);
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
    for (int i = 0; i < this.vertices; i++) {
      if (matrix[node][i] != 0 && !visited[i]) {
        topologicalDfsHelper(i, stack, visited);
      }
    }
    stack.push(node);
  }

  // Topological Sort using Kahn`s Algorithm BFS
  public void topologicalSortUsingBfs() {
    System.out.print("Topological Order BFS (Kahn`s Algorithm): ");
    int[] inDegrees = new int[this.vertices];
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        if (matrix[i][j] == 1) {
          inDegrees[j]++;
        }
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
      for (int i = 0; i < matrix.length; i++) {
        if (matrix[node][i] == 1 && --inDegrees[i] == 0) {
          queue.offer(i);
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
    Arrays.fill(parent, Integer.MIN_VALUE);
    distance[source] = 0;

    Stack<Integer> stack = new Stack();
    boolean[] visited = new boolean[this.vertices];
    topologicalDfsHelper(source, stack, visited);

    while (!stack.isEmpty()) {
      int i = stack.pop();
      if (distance[i] != Integer.MIN_VALUE) {
        for (int j = 0; j < this.vertices; j++) {
          if (matrix[i][j] != 0 && distance[j] < distance[i] + matrix[i][j]) {
            distance[j] = distance[i] + matrix[i][j];
            parent[j] = i;
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
    if (node == Integer.MIN_VALUE) {
      return;
    }
    printPath(parent, parent[node]);
    System.out.print(node + " ");
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
    GraphAdMatrixDirected graph = new GraphAdMatrixDirected(6);
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
    graph.removeVertices(6); // Resetting/deleting the graph
    graph.addVertices(5); // Creates the new graph with 5 vertices
    graph.addEdge(0, 1);
    graph.addEdge(0, 3);
    graph.addEdge(1, 2);
    graph.addEdge(1, 3);
    graph.addEdge(3, 2);
    graph.addEdge(3, 4);
    System.out.print(graph);
    graph.topologicalSortUsingDfs();
    System.out.println();
    graph.topologicalSortUsingBfs(); // Kahn`s Algorithm
    System.out.println("\n-------------------------------------------------------");
    GraphAdMatrixDirected graph1 = new GraphAdMatrixDirected(6);
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
