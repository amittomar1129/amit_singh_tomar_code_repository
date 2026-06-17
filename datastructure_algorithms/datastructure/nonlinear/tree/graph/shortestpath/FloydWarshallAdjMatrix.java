package datastructure_algorithms.datastructure.nonlinear.tree.graph.shortestpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.IntStream;

public class FloydWarshallAdjMatrix {

  private int[][] matrix;
  private int vertices; // No. of vertices

  public FloydWarshallAdjMatrix() {
  }

  public FloydWarshallAdjMatrix(int vertices) {
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

  public void floydWarshallShortestPathsAllVertices() {
    int[][] distance = Arrays.copyOf(this.matrix, vertices);
    int[][] parent = new int[vertices][vertices];
    for (int i = 0; i < vertices; i++) {
      for (int j = 0; j < vertices; j++) {
        if (matrix[i][j] != Integer.MAX_VALUE && matrix[i][j] != 0) {
          parent[i][j] = i;
        } else {
          parent[i][j] = -1;
        }
      }
    }

    for (int i = 0; i < vertices; i++) {
      for (int j = 0; j < vertices; j++) {
        for (int k = 0; k < vertices; k++) {
          if (distance[j][i] != Integer.MAX_VALUE && distance[i][k] != Integer.MAX_VALUE
              && distance[j][k] > distance[j][i] + distance[i][k]) {
            distance[j][k] = distance[j][i] + distance[i][k];
            parent[j][k] = parent[i][k];
          }
        }
      }
    }

    // Detects Negative Weighted Cycle
    for (int i = 0; i < vertices; i++) {
      if (distance[i][i] < 0) {
        System.out.println("This Graph has a Negative Weighted Cycle.");
        printNegativeWeightedCycle(i, parent);
        return;
      }
    }

    System.out.print("Shortest Distance Matrix for all pair of vertices: \n");
    for (int i = 0; i < vertices; i++) {
      for (int j = 0; j < vertices; j++) {
        if (distance[i][j] != Integer.MAX_VALUE) {
          System.out.print(distance[i][j] + ", ");
        } else {
          System.out.print("INF" + ", ");
        }
      }
      System.out.println();
    }

    // Print Parents from 0 to 3 vertex
    ArrayList<Integer> list = new ArrayList<>();
    printPath(0, 3, parent, list);
    System.out.println(list);
  }

  private void printPath(int i, int j, int[][] parent, ArrayList<Integer> list) {
    if (parent[i][j] == -1) {
      list.add(i);
      return;
    }

    printPath(i, parent[i][j], parent, list);
    list.add(j);
  }

  private void printNegativeWeightedCycle(int start, int[][] parent) {
    int node = start;
    for (int i = 0; i <vertices ; i++) {
      node = parent[start][node];
    }
    int startingNode = node;
    Stack<Integer> stack = new Stack<>();
    stack.add(startingNode);
    node = parent[start][node];

    while (node != -1 && node != startingNode) {
      stack.add(node);
      node = parent[start][node];
    }
    stack.add(node);
    System.out.print("Cycle: ");
    while (!stack.isEmpty())
    System.out.print(stack.pop() +", ");
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
    FloydWarshallAdjMatrix graph = new FloydWarshallAdjMatrix(4);
    graph.addEdge(0, 1, 3);
    graph.addEdge(0, 3, 7);
    graph.addEdge(1, 0, 8);
    graph.addEdge(1, 2, 2);
    graph.addEdge(2, 0, 5);
    graph.addEdge(2, 3, 1);
    graph.addEdge(3, 0, 2);
    System.out.println(graph);
    graph.floydWarshallShortestPathsAllVertices();
  }
}
