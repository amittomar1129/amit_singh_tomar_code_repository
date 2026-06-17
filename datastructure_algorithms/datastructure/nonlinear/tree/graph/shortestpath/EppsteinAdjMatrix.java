package datastructure_algorithms.datastructure.nonlinear.tree.graph.shortestpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.IntStream;

// Yen’s Algorithm for finding K shortest simple paths.
public class EppsteinAdjMatrix {

  private int[][] matrix;
  private int vertices; // No. of vertices

  public EppsteinAdjMatrix() {
  }

  public EppsteinAdjMatrix(int vertices) {
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

  // Gives shortest distance from all vertices to target/destination
  private int[] dijkstra(int destination) {
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

  private ArrayList<Route> eppsteinShortestRoutes(int source, int destination, int k) {
    ArrayList<Route> result = new ArrayList<>();
    int[] distance = dijkstra(destination);

    // shortest path first
    Route firstRoute = buildShortestPath(source, destination, distance);
    if (firstRoute == null) {
      return result;
    }

    PriorityQueue<Route> queue = new PriorityQueue<>();
    Set<String> seen = new HashSet<>();
    queue.offer(firstRoute);
    seen.add(firstRoute.getVertices().toString());

    while (!queue.isEmpty() && result.size() < k) {
      Route route = queue.poll();
      result.add(route);

      // expand with sidetracks
      int lastNode = route.getVertices().get(route.getVertices().size() - 1);
      if (lastNode == destination) {
        for (int i = 0; i < route.getVertices().size(); i++) {
          int node = route.getVertices().get(i);
          for (int j = 0; j < vertices; j++) {

            if (matrix[node][j] != 0 && !route.getVertices().contains(j)
                && distance[j] != Integer.MAX_VALUE) {
              List<Integer> newPath = new ArrayList<>(route.getVertices().subList(0, i + 1));
              newPath.add(j);
              // complete to destination via shortest path tree
              int newCost = route.getCost() + (matrix[node][j] + distance[j] - distance[node]);
              buildShortestSuffix(j, destination, distance, newPath);
              if (!seen.contains(newPath.toString())) {
                queue.offer(new Route(newPath, newCost));
                seen.add(newPath.toString());
              }
            }
          }
        }

      }
    }
    return result;
  }

  // Find the shortest route from all vertices to target using parent tracking
  private Route buildShortestPath(int source, int destination, int[] distance) {
    List<Integer> path = new ArrayList<>();
    path.add(source);
    int u = source;
    int cost = 0;
    while (u != destination) {
      int next = -1;
      for (int v = 0; v < vertices; v++) {
        if (matrix[u][v] > 0 && distance[u] == matrix[u][v] + distance[v]) {
          next = v;
          cost += matrix[u][v];
          break;
        }
      }
      if (next == -1) {
        return null; // no path
      }
      u = next;
      path.add(u);
    }
    return new Route(path, cost);
  }

  // Attaches suffix path along shortest path tree
  private void buildShortestSuffix(int u, int destination, int[] distance, List<Integer> path) {
    while (u != destination) {
      int next = -1;
      for (int v = 0; v < vertices; v++) {
        if (matrix[u][v] > 0 && distance[u] == matrix[u][v] + distance[v]) {
          next = v;
          break;
        }
      }
      if (next == -1) {
        return;
      }
      path.add(next);
      u = next;
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
    EppsteinAdjMatrix graph = new EppsteinAdjMatrix(8);
    graph.addEdge(0, 1, 4);
    graph.addEdge(0, 3, 1);
    graph.addEdge(0, 2, 3);
    graph.addEdge(1, 3, 2);
    graph.addEdge(2, 3, 6);
    graph.addEdge(2, 4, 5);
    graph.addEdge(3, 5, 1);
    graph.addEdge(4, 5, 2);
    graph.addEdge(4, 7, 3);
    graph.addEdge(5, 6, 7);
    graph.addEdge(6, 7, 1);
    System.out.println(graph);
    List<Route> routes = graph.eppsteinShortestRoutes(0, 7, 5);
    System.out.println(routes);

  }
}

