package datastructure_algorithms.datastructure.nonlinear.tree.graph.shortestpath;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Bfs0And1AdjMatrix {

  private int[][] matrix;
  private int vertices; // No. of vertices

  public Bfs0And1AdjMatrix() {
  }

  public Bfs0And1AdjMatrix(int vertices) {
    this.matrix = new int[vertices][vertices];
    this.vertices = vertices;
    for (int i = 0; i < vertices; i++) {
      Arrays.fill(matrix[i], -1); // -1 means there is no path
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
    if (!(weight == 0 || weight == 1)) {
      throw new IllegalArgumentException(
          String.format("Edge cost should be either 0 or 1"));
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

  public void bfsShortestPath(int source) {
    int[] distance = new int[vertices];
    int[] parent = new int[vertices];
    Arrays.fill(distance, Integer.MAX_VALUE);
    Arrays.fill(parent, -1);
    distance[source] = 0;

    Deque<Integer> deque = new ArrayDeque<>();
    deque.offer(source);

    while (!deque.isEmpty()) {
      Integer node = deque.pollFirst();

      for (int i = 0; i < vertices; i++) {
        if (matrix[node][i] != -1 && distance[i] > distance[node] + matrix[node][i]) {
          distance[i] = distance[node] + matrix[node][i];
          parent[i] = node;
          if (distance[node] + matrix[node][i] == 0) {
            deque.offerFirst(i);
          } else {
            deque.offerLast(i);
          }
        }
      }
    }

    System.out.println("distance from source to all vertices >>");
    for (int i = 0; i < vertices; i++) {
      System.out.print(distance[i]+", ");
    }

    ArrayList<Integer> result = new ArrayList<>();
    System.out.println("\nPath from 0 to 11 is");
    if (distance[11] == Integer.MAX_VALUE) {
      System.out.println("There is no path from 0 to 11");
    } else {
      buildPath(source, 11, parent, result);
      System.out.println(result);
    }
  }

  private void buildPath(int source, int destination, int[] parent, List<Integer> result) {
    if (source == destination) {
      result.add(source);
      return;
    }
    buildPath(source, parent[destination], parent, result);
    result.add(destination);
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
    Bfs0And1AdjMatrix graph = new Bfs0And1AdjMatrix(12);
    graph.addEdge(0, 1, 0);
    graph.addEdge(0, 2, 1);
    graph.addEdge(1, 3, 1);
    graph.addEdge(2, 4, 0);
    graph.addEdge(3, 5, 1);
    graph.addEdge(4, 5, 0);
    graph.addEdge(5, 6, 1);
    graph.addEdge(6, 7, 0);
    graph.addEdge(7, 8, 1);
    graph.addEdge(8, 9, 0);
    graph.addEdge(9, 10, 1);
    graph.addEdge(10, 11, 0);
    System.out.println(graph);
    graph.bfsShortestPath(0);

  }

}
