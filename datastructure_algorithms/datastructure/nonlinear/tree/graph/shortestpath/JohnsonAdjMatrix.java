package datastructure_algorithms.datastructure.nonlinear.tree.graph.shortestpath;

import designpattern.structural.proxy.Image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.stream.IntStream;

public class JohnsonAdjMatrix {

  private int[][] matrix;
  private int vertices; // No. of vertices

  public JohnsonAdjMatrix() {
  }

  public JohnsonAdjMatrix(int vertices) {
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

  public int[][] johnsonShortestPathsAllVertices() {
    int[][] result = new int[vertices][vertices];
    int[][] extendedGraph = new int[vertices + 1][vertices + 1];
    copyGraph(this.matrix, extendedGraph);
    System.out.println(print(extendedGraph));

    int[] h = bellmanFord(extendedGraph, vertices);
    if (h == null) {
      return result;
    }

    int[][] reweightMatrix = new int[vertices][vertices];
    for (int i = 0; i < vertices; i++) {
      for (int j = 0; j < vertices; j++) {
        if (matrix[i][j] != Integer.MAX_VALUE) {
          reweightMatrix[i][j] = matrix[i][j] + h[i] - h[j];
        } else {
          reweightMatrix[i][j] = Integer.MAX_VALUE;
        }
      }
    }

    // Run Dijkstra on reweighted graph
    for (int i = 0; i < vertices; i++) {
      int[] distance = dijkstra(reweightMatrix, i);
      for (int j = 0; j < vertices; j++) {
        if (distance[j] < Integer.MAX_VALUE) {
          result[i][j] =  distance[j] - h[i] + h[j];
        } else
          result[i][j] =  Integer.MAX_VALUE;
      }
    }
    return result;
  }

  private void copyGraph(int[][] graph, int[][] target) {
    for (int i = 0; i < graph.length; i++) {
      for (int j = 0; j < graph[i].length; j++) {
        target[i][j] = graph[i][j];
      }
      target[i][graph.length] = Integer.MAX_VALUE;
    }
  }


  private int[] bellmanFord(int[][] matrix, int source) {
    int[] distance = new int[matrix.length];
    Arrays.fill(distance, Integer.MAX_VALUE);
    distance[source] = 0;

    for (int i = 0; i < matrix.length - 1; i++) {
      for (int j = 0; j < matrix.length; j++) {
        for (int k = 0; k < matrix.length; k++) {
          if (matrix[j][k] != Integer.MAX_VALUE && distance[j] != Integer.MAX_VALUE
              && distance[k] > distance[j] + matrix[j][k]) {
            distance[k] = distance[j] + matrix[j][k];
          }
        }
      }
    }

    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix.length; j++) {
        if (matrix[i][j] != Integer.MAX_VALUE && distance[i] != Integer.MAX_VALUE
            && distance[j] > distance[i] + matrix[i][j]) {
          System.out.println("Graph contains negative weight cycle!");
          return null;
        }
      }
    }
    return distance;
  }

  private int[] dijkstra(int[][] matrix, int source) {
    int vertices = matrix.length;
    int[] distance = new int[vertices];
    Arrays.fill(distance, Integer.MAX_VALUE);
    distance[source] = 0;

    boolean[] visited = new boolean[vertices];
    for (int i = 0; i < vertices; i++) {
      int node = minDistanceNode(distance, visited);
      if (node == -1) {
        break;
      }
      visited[node] = true;
      for (int j = 0; j < vertices; j++) {
        if (matrix[node][j] != Integer.MAX_VALUE && !visited[j] && distance[node] != Integer.MAX_VALUE &&
            distance[j] > distance[node] + matrix[node][j]) {
          distance[j] = distance[node] + matrix[node][j];
        }
      }
    }
    return distance;
  }

  private int minDistanceNode(int[] distance, boolean[] visited) {
    int dis = Integer.MAX_VALUE;
    int minNode = -1;
    for (int i = 0; i < vertices; i++) {
      if (!visited[i] && dis > distance[i]) {
        dis = distance[i];
        minNode = i;
      }
    }
    return minNode;
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

  public String print(int[][] matrix) {
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

    JohnsonAdjMatrix graph = new JohnsonAdjMatrix(5);
    graph.addEdge(0, 1, -1);
    graph.addEdge(0, 2, 4);
    graph.addEdge(1, 2, 3);
    graph.addEdge(1, 3, 2);
    graph.addEdge(1, 4, 2);
    graph.addEdge(3, 2, 5);
    graph.addEdge(3, 1, 1);
    graph.addEdge(4, 3, -3);
    System.out.println(graph);
    int[][] result = graph.johnsonShortestPathsAllVertices();
    if (result != null) {
      System.out.println("All pairs shortest paths:");
      for (int i = 0; i < result.length; i++) {
        for (int j = 0; j < result.length; j++) {
          if (result[i][j] == Integer.MAX_VALUE)
            System.out.print("INF ");
          else
            System.out.print(result[i][j] + " ");
        }
        System.out.println();
      }
    }

  }
}
