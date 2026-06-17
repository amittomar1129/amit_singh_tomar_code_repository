package datastructure_algorithms.datastructure.nonlinear.tree.graph.shortestpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.IntStream;

public class AStarAdjMatrix {

  private int[][] matrix;
  private int vertices; // No. of vertices

  public AStarAdjMatrix() {
  }

  public AStarAdjMatrix(int vertices) {
    this.matrix = new int[vertices][vertices];
    this.vertices = vertices;
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

  public ArrayList<Integer> aStarShortestPath(int source, int destination, int[] heuristics) {
    ArrayList<Integer> result = new ArrayList<>();
    int[] distance = new int[vertices];
    int[] parent = new int[vertices];
    Arrays.fill(distance, Integer.MAX_VALUE);
    Arrays.fill(parent, -1);
    distance[source] = 0;

    if (heuristics == null) {
      heuristics = calculateHeuristics(destination);
    }
    PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
    queue.offer(new int[]{source, distance[source] + heuristics[source]});

    boolean[] visited = new boolean[vertices];
    while (!queue.isEmpty()) {
      int[] element = queue.poll();
      int i = element[0];
      if (visited[i]) {
        continue;
      }
      visited[i] = true;
      if (i == destination) {
        buildResult(source, destination, parent, result);
        System.out.println("Total Cost: " + element[1]);
      }
      for (int j = 0; j < vertices; j++) {
        if (!visited[j] && matrix[i][j] != 0 && distance[i] != Integer.MAX_VALUE
            && distance[j] > distance[i] + matrix[i][j]) {
          distance[j] = distance[i] + matrix[i][j];
          parent[j] = i;
          queue.offer(new int[]{j, distance[j] + heuristics[j]});
        }
      }
    }
    return result;
  }

  private void buildResult(int source, int destination, int[] parent, List<Integer> result) {
    if (destination == source) {
      result.add(source);
      return;
    }

    buildResult(source, parent[destination], parent, result);
    result.add(destination);
  }

  // Using Reverse Dijkstra to find heuristics for each vertex
  private int[] calculateHeuristics(int destination) {
    int[] distance = new int[vertices];
    Arrays.fill(distance, Integer.MAX_VALUE);
    distance[destination] = 0;

    boolean[] visited = new boolean[vertices];
    for (int i = 0; i < vertices; i++) {
      int node = minDistanceNode(distance, visited);
      if (node == -1) {
        break;
      }
      visited[node] = true;
      for (int j = 0; j < vertices; j++) {
        if (matrix[j][node] != 0 && distance[j] > distance[node] + matrix[j][node]) {
          distance[j] = distance[node] + matrix[j][node];
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

    AStarAdjMatrix graph = new AStarAdjMatrix(5);
    graph.addEdge(0, 1, 10);
    graph.addEdge(0, 4, 5);
    graph.addEdge(1, 2, 1);
    graph.addEdge(1, 4, 2);
    graph.addEdge(2, 3, 4);
    graph.addEdge(3, 0, 7);
    graph.addEdge(3, 2, 6);
    graph.addEdge(4, 1, 3);
    graph.addEdge(4, 2, 9);
    graph.addEdge(4, 3, 2);
    System.out.println(graph);
    // It`s optional to pass heuristics, It runs dijkstra to find heuristics(estimated cost) for each vertex to target if not passed.
    ArrayList<Integer> route = graph.aStarShortestPath(0, 2, new int[]{1, 12, 1, 121, 1});
    System.out.println(route);
  }
}
