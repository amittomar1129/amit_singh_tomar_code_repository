package datastructure_algorithms.datastructure.nonlinear.tree.graph.shortestpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

// Yen’s Algorithm for finding K shortest simple paths.
public class YensAdjMatrix {

  private int[][] matrix;
  private int vertices; // No. of vertices

  public YensAdjMatrix() {
  }

  public YensAdjMatrix(int vertices) {
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

  private Route dijktra(int source, int destination, HashSet<String> bannedEdges) {
    int[] distances = new int[vertices];
    int[] parent = new int[vertices];
    Arrays.fill(distances, Integer.MAX_VALUE);
    Arrays.fill(parent, -1);
    distances[source] = 0;

    boolean[] visited = new boolean[vertices];
    for (int i = 0; i < vertices; i++) {
      int node = minDistanceNode(distances, visited);
      if (node == -1) {
        break;
      }
      visited[node] = true;
      for (int j = 0; j < vertices; j++) {
        if (matrix[node][j] != 0 && distances[node] != Integer.MAX_VALUE && !visited[j]
            && distances[j] > distances[node] + matrix[node][j]) {
          if (bannedEdges.contains(node + "-" + j) || bannedEdges.contains(j + "-" + node)) {
            continue;
          }
          distances[j] = distances[node] + matrix[node][j];
          parent[j] = node;
        }
      }
    }

    if (distances[destination] != Integer.MAX_VALUE) {
      Route route = new Route();
      route.setCost(distances[destination]);
      ArrayList<Integer> vertices = new ArrayList<>();
      findRoute(destination, parent, vertices);
      route.setVertices(vertices);
      return route;
    }
    return null;
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


  private void findRoute(int destination, int[] parent, List<Integer> result) {
    if (destination == -1) {
      return;
    }
    findRoute(parent[destination], parent, result);
    result.add(destination);
  }


  private ArrayList<Route> yensShortestRoutes(int source, int destination, int k) {
    ArrayList<Route> result = new ArrayList<>();
    PriorityQueue<Route> candidates = new PriorityQueue<>();

    // Step 1: First shortest path
    Route firstRoute = dijktra(source, destination, new HashSet<>());
    if (firstRoute == null) {
      return result;
    }
    result.add(firstRoute);

    // Step 2: Find up to K-1 deviations
    for (int i = 0; i < k - 1; i++) {
      Route prevRoute = result.get(i);
      for (int j = 0; j < prevRoute.getVertices().size() - 1; j++) {
        Integer spurNode = prevRoute.getVertices().get(j);
        List<Integer> rootPath = prevRoute.getVertices().subList(0, j + 1);

        HashSet<String> bannedEdges = new HashSet();
        for (Route route : result) {
          List<Integer> nodes = route.getVertices();
          if (nodes.size() > j && nodes.subList(0, j + 1).equals(rootPath)) {
            bannedEdges.add(nodes.get(j) + "-" + nodes.get(j + 1));
          }
        }

        // Spur path
        Route spurRoute = dijktra(spurNode, destination, bannedEdges);
        if (spurRoute != null) {
          ArrayList<Integer> alternativeRoute = new ArrayList<>(rootPath);
          alternativeRoute.addAll(
              spurRoute.getVertices().subList(1, spurRoute.getVertices().size()));
          int cost = calculateCost(alternativeRoute);
          Route route = new Route(alternativeRoute, cost);
          candidates.offer(route);
        }
      }
      if (candidates.isEmpty()) {
        break;
      }
      result.add(candidates.poll());
    }

    return result;
  }

  private int calculateCost(List<Integer> nodes) {
    int cost = 0;
    for (int i = 0; i < nodes.size() - 1; i++) {
      cost += matrix[nodes.get(i)][nodes.get(i + 1)];
    }
    return cost;
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
    YensAdjMatrix graph = new YensAdjMatrix(8);
    graph.addEdge(0, 1, 4);
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
    List<Route> routes = graph.yensShortestRoutes(0, 7, 5);
    routes.forEach(route -> System.out.println(route));

  }
}
