package datastructure_algorithms.datastructure.nonlinear.tree.graph.bipartite;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Hungarian {

  private int[][] matrix;
  private int vertices; // No. of vertices
  private int[] labelI, labelJ, assignments, way;


  public Hungarian() {
  }

  public Hungarian(int vertices) {
    this.matrix = new int[vertices][vertices];
    this.vertices = vertices;
    this.labelI = new int[vertices + 1];
    this.labelJ = new int[vertices + 1];
    this.assignments = new int[vertices + 1];
    this.way = new int[vertices + 1];
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

  public int[] getAssignments(int[][] matrix) {
    for (int i = 1; i <= vertices; i++) {
      int[] distance = new int[vertices + 1];
      boolean[] visited = new boolean[vertices + 1];
      Arrays.fill(distance, Integer.MAX_VALUE);
      int node = 0;
      assignments[node] = i;

      do {
        visited[node] = true;
        int assignment = assignments[node];
        int delta = Integer.MAX_VALUE;
        int temp = -1;

        for (int j = 1; j <= vertices; j++) {
          if (!visited[j]) {
            if (matrix[assignment - 1][j - 1] - labelI[assignment] - labelJ[j] < distance[j]) {
              distance[j] = matrix[assignment - 1][j - 1] - labelI[assignment] - labelJ[j];
              way[j] = node;
            }
            if (distance[j] < delta) {
              delta = distance[j];
              temp = j;
            }
          }
        }

        for (int j = 0; j <= vertices; j++) {
          if (visited[j]) {
            labelI[assignments[j]] += delta;
            labelJ[j] -= delta;
          } else {
            distance[j] -= delta;
          }
        }
        node = temp;
      } while (assignments[node] != 0);

      do {
        int temp = way[node];
        assignments[node] = assignments[temp];
        node = temp;
      } while (node != 0);
    }

    int[] result = new int[vertices];
    for (int i = 1; i <= vertices; i++) {
      result[assignments[i] - 1] = i - 1;
    }
    return result;
  }

  public int totalCost() {
    int costSum = 0;
    for (int i = 1; i <= vertices; i++) {
      costSum += matrix[i - 1][assignments[i] - 1];
    }
    return costSum;
  }

  public int[] getMaximumCostAssignments() {
    int maxValue = Integer.MIN_VALUE;

    // Step 1: Find max value for conversion
    for (int i = 0; i < vertices; i++) {
      for (int j = 0; j < vertices; j++) {
        maxValue = Math.max(maxValue, matrix[i][j]);
      }
    }

    // Step 2: Convert max-cost to min-cost
    int[][] newMatrix = new int[vertices][vertices];
    for (int i = 0; i < vertices; i++) {
      for (int j = 0; j < vertices; j++) {
        newMatrix[i][j] = maxValue - this.matrix[i][j];
      }
    }
    return getAssignments(newMatrix);
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
    // For Min Cost
    Hungarian graph = new Hungarian(3);
    graph.addEdge(0, 0, 4);
    graph.addEdge(0, 1, 2);
    graph.addEdge(0, 2, 5);
    graph.addEdge(1, 0, 3);
    graph.addEdge(1, 1, 2);
    graph.addEdge(1, 2, 3);
    graph.addEdge(2, 0, 4);
    graph.addEdge(2, 1, 3);
    graph.addEdge(2, 2, 2);

    System.out.println(graph);

    int[] assignments = graph.getAssignments(graph.matrix);
    for (int i = 0; i < assignments.length; i++) {
      System.out.println("Assignment for worker " + i + " is job -> " + assignments[i]);
    }
    System.out.println("Minimum total cost: " + graph.totalCost());

    // For Max Cost
    Hungarian graph1 = new Hungarian(4);
    graph1.addEdge(0, 0, 9);
    graph1.addEdge(0, 1, 2);
    graph1.addEdge(0, 2, 7);
    graph1.addEdge(0, 3, 8);
    graph1.addEdge(1, 0, 6);
    graph1.addEdge(1, 1, 4);
    graph1.addEdge(1, 2, 3);
    graph1.addEdge(1, 3, 7);

    graph1.addEdge(2, 0, 5);
    graph1.addEdge(2, 1, 8);
    graph1.addEdge(2, 2, 1);
    graph1.addEdge(2, 3, 8);

    graph1.addEdge(3, 0, 7);
    graph1.addEdge(3, 1, 6);
    graph1.addEdge(3, 2, 9);
    graph1.addEdge(3, 3, 4);
    System.out.println(graph1);

    int[] assignments1 = graph1.getMaximumCostAssignments();
    int totalMaxCost = 0;
    for (int i = 0; i < assignments1.length; i++) {
      totalMaxCost += graph1.matrix[i][assignments1[i]];
      System.out.println("Assignment for worker " + i + " is job -> " + assignments1[i]);
    }
    System.out.println("Maximum total cost: " + totalMaxCost);
  }
}
