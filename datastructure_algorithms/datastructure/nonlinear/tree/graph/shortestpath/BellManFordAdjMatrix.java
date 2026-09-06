package datastructure_algorithms.datastructure.nonlinear.tree.graph.shortestpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BellManFordAdjMatrix {

  private int[][] matrix;
  private int vertices; // No. of vertices

  public BellManFordAdjMatrix() {
  }

  public BellManFordAdjMatrix(int vertices) {
    this.matrix = new int[vertices][vertices];
    this.vertices = vertices;

    for (int i = 0; i < vertices; i++) {
      for (int j = 0; j < vertices; j++) {
        if (i == j) {
          matrix[i][j] = 0;
        } else {
          matrix[i][j] = Integer.MAX_VALUE;
        }
      }
    }
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
  }

  public void bellmanFordShortestPath(int source) {
    int[] distance = new int[vertices];
    int[] parent = new int[vertices];
    Arrays.fill(distance, Integer.MAX_VALUE);
    Arrays.fill(parent, -1);
    distance[source] = 0;

    // Step 1: Relax all edges V-1 times
    for (int k = 0; k < vertices - 1; k++) {
      for (int i = 0; i < vertices; i++) {
        for (int j = 0; j < vertices; j++) {
          if (matrix[i][j] != Integer.MAX_VALUE && distance[i] != Integer.MAX_VALUE
              && distance[j] > distance[i] + matrix[i][j]) {
            distance[j] = distance[i] + matrix[i][j];
            parent[j] = i;
          }
        }
      }
    }

    // Step 2: Detect negative cycle
    for (int i = 0; i < vertices; i++) {
      for (int j = 0; j < vertices; j++) {
        if (matrix[i][j] != Integer.MAX_VALUE && distance[i] != Integer.MAX_VALUE
            && distance[j] > distance[i] + matrix[i][j]) {
          System.out.println("Graph contains negative weight cycle!");
          return;
        }
      }
    }

    for (int i = 0; i < distance.length; i++) {
      if (distance[i] == Integer.MAX_VALUE) {
        System.out.println(i + ": Unreachable ");
      } else {
        System.out.println(i + ": " + distance[i] + " | " + printPath(parent, i));
      }
    }
  }

  private String printPath(int[] parent, int node) {
    if (parent[node] == -1) {
      return node + ", ";
    }
    String build = printPath(parent, parent[node]);

    return build + node + ", ";
  }

  public void bellmanFordDelectedNegativeWeightedCycles(int source) {
    int[] distance = new int[vertices];
    int[] parent = new int[vertices];
    Arrays.fill(distance, Integer.MAX_VALUE);
    Arrays.fill(parent, -1);
    distance[source] = 0;

    for (int k = 0; k < vertices - 1; k++) {
      for (int i = 0; i < vertices; i++) {
        for (int j = 0; j < vertices; j++) {
          if (matrix[i][j] != Integer.MAX_VALUE && distance[i] != Integer.MAX_VALUE
              && distance[i] + matrix[i][j] < distance[j]) {
            distance[j] = distance[i] + matrix[i][j];
            parent[j] = i;
          }
        }
      }
    }

    for (int i = 0; i < vertices; i++) {
      for (int j = 0; j < vertices; j++) {
        if (distance[i] != Integer.MAX_VALUE && matrix[i][j] != Integer.MAX_VALUE
            && distance[i] + matrix[i][j] < distance[j]) {
//           Negative cycle found
          Stack<Integer> stack = new Stack<>();
          stack.push(j);
          int node = j;
          while (parent[node] != -1 && parent[node] != j) {
            node = parent[node];
            stack.push(node);
          }
          stack.push(parent[node]);
          System.out.print("Negative Weight Cycle: ");
          while (!stack.isEmpty()) {
            System.out.print(stack.pop() + ", ");
          }
          System.out.println();
        }
      }
    }
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

    BellManFordAdjMatrix graph1 = new BellManFordAdjMatrix(5);
//     Example 1
    graph1.addEdge(0, 1, -1);
    graph1.addEdge(0, 2, 4);
    graph1.addEdge(1, 2, 3);
    graph1.addEdge(1, 3, 2);
    graph1.addEdge(1, 4, 2);
    graph1.addEdge(3, 1, 1);
    graph1.addEdge(3, 2, 5);
    graph1.addEdge(4, 3, -3);
    System.out.println(graph1);
    graph1.bellmanFordShortestPath(0);
    System.out.println("----------------------------------- \n Example 2");
//     Example 2
    BellManFordAdjMatrix graph2 = new BellManFordAdjMatrix(6);
    graph2.addEdge(0, 1, 6);
    graph2.addEdge(0, 2, 4);
    graph2.addEdge(0, 3, 5);
    graph2.addEdge(1, 4, -1);
    graph2.addEdge(2, 1, -2);
    graph2.addEdge(2, 4, 3);
    graph2.addEdge(3, 2, -2);
    graph2.addEdge(3, 5, -1);
    graph2.addEdge(4, 5, 3);
    System.out.println(graph2);
    graph2.bellmanFordShortestPath(0);
    System.out.println("----------------------------------- \n Example 3");
    // Example 3
    BellManFordAdjMatrix graph = new BellManFordAdjMatrix(4);
    graph.addEdge(0, 1, 4);
    graph.addEdge(0, 2, 5);
    graph.addEdge(1, 3, 7);
    graph.addEdge(2, 1, 7);
    graph.addEdge(3, 2, -15);
    System.out.println(graph);
    // Bellman Ford algorithm does not guarantee to find out all the negative weighted cycles but give the exiting one.
    graph.bellmanFordDelectedNegativeWeightedCycles(0);

  }
}
