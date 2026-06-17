package datastructure_algorithms.datastructure.nonlinear.tree.graph.bipartite;

import datastructure_algorithms.datastructure.nonlinear.tree.graph.Edge;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;

public class HopcroftKarp {

  private List<List<Edge>> adj;
  private int vertices; // No. of vertices
  private int nLeft, nRight;
  private int[] pairI;
  private int[] pairJ;
  private int[] distance;

  public HopcroftKarp(int vertices, int nLeft, int nRight) {
    this.vertices = vertices;
    this.nLeft = nLeft;
    this.nRight = nRight;
    adj = new ArrayList<>();
    for (int i = 0; i <= nLeft; i++) {
      adj.add(new ArrayList<>());
    }
    this.pairI = new int[nLeft + 1];
    this.pairJ = new int[nRight + 1];
    this.distance = new int[nLeft + 1];
  }

  public void addEdge(int i, int j) {
    if (i >= vertices || j >= vertices) {
      throw new IllegalArgumentException(
          String.format("Edge index beyond the vertices size: [%s]", vertices));
    }
    Edge newEdge = new Edge(j);
    if (!adj.get(i).contains(newEdge)) {
      adj.get(i).add(newEdge);
    }
  }

  public void addEdge(int i, int j, int weight) {
    if (i >= vertices || j >= vertices) {
      throw new IllegalArgumentException(
          String.format("Edge index beyond the vertices size: [%s]", vertices));
    }
    Edge newEdge = new Edge(j, weight);
    if (!adj.get(i).contains(newEdge)) {
      adj.get(i).add(newEdge);
    }
  }

  public void removeEdge(int i, int j) {
    if (i >= vertices || j >= vertices) {
      throw new IllegalArgumentException(
          String.format("Edge index beyond the vertices size: [%s]", vertices));
    }
    adj.get(i).removeIf(edge -> edge.getTo() == j);
  }

  // Adds new vertices having size more than the previous vertices.
  // Creates new matrix with new size and copy relevant data from old matrix.
  public void addVertex(int num) {
    if (num <= 0) {
      return;
    }
    int newVertices = vertices + num;
    for (int i = 0; i < num; i++) {
      adj.add(new ArrayList<>());
    }
    this.vertices = newVertices;
  }

  // Removes some vertices having size less than the previous vertices.
  // Creates new matrix with new lesser size and copy relevant data from old matrix.
  public void removeVertex(int num) {
    if (num <= 0 || num > this.vertices) {
      return;
    } else if (num == this.vertices) { // Reset the graph
      adj = new ArrayList<>();
      this.vertices = 0;
      return;
    }
    int newVertices = vertices - num;
    adj = adj.subList(0, newVertices);
    for (int i = 0; i < newVertices; i++) {
      int finalI = i;
      IntStream.range(vertices - num, vertices)
          .forEach(index -> adj.get(finalI).removeIf(a -> a.getTo() == index));
    }
    this.vertices = newVertices;
  }

  private int maxMatching() {
    int maxMatching = 0;
    while (bfs()) {
      for (int i = 1; i <= nLeft; i++) {
        if (pairI[i] == 0 && dfs(i)) {
          maxMatching++;
        }
      }
    }
    return maxMatching;
  }

  private boolean bfs() {
    Queue<Integer> queue = new LinkedList<>();
    for (int i = 1; i <= nLeft; i++) {
      if (pairI[i] == 0) {
        distance[i] = 0;
        queue.offer(i);
      } else {
        distance[i] = Integer.MAX_VALUE;
      }
    }
    distance[0] = Integer.MAX_VALUE;

    while (!queue.isEmpty()) {
      int node = queue.poll();
      if (distance[node] < distance[0]) {
        for (Edge edge : adj.get(node)) {
          if (distance[pairJ[edge.getTo()]] == Integer.MAX_VALUE) {
            distance[pairJ[edge.getTo()]] = distance[node] + 1;
            queue.offer(pairJ[edge.getTo()]);
          }
        }
      }
    }
    return distance[0] != Integer.MAX_VALUE;
  }

  private boolean dfs(int node) {
    if (node != 0) {
      for (Edge edge : adj.get(node)) {
        if (distance[pairJ[edge.getTo()]] == distance[node] + 1 && dfs(pairJ[edge.getTo()])) {
          pairJ[edge.getTo()] = node;
          pairI[node] = edge.getTo();
          return true;
        }
      }
      distance[node] = Integer.MAX_VALUE;
      return false;
    }
    return true;
  }

  // Optional: print matching pairs
  public void printMatching() {
    for (int i = 1; i <= nLeft; i++) {
      if (pairI[i] != 0) {
        System.out.println(i + " -> " + pairI[i]);
      }
    }
  }

  private void minVertexCoverByKonigsAlgorithm() {
    boolean[] visitedI = new boolean[nLeft + 1];
    boolean[] visitedJ = new boolean[nRight + 1];
    Queue<Integer> queue = new LinkedList<>();

    // Start BFS from unmatched U vertices
    for (int i = 1; i <= nLeft; i++) {
      if (pairI[i] == 0) {
        queue.add(i);
        visitedI[i] = true;
      }
    }

    while (!queue.isEmpty()) {
      int node = queue.poll();
      for (Edge edge : adj.get(node)) {
        if (!visitedJ[edge.getTo()]) {
          visitedJ[edge.getTo()] = true;
          if (pairJ[edge.getTo()] != 0 && !visitedI[pairJ[edge.getTo()]]) {
            visitedI[pairJ[edge.getTo()]] = true;
            queue.add(pairJ[edge.getTo()]);
          }
        }
      }
    }

    System.out.print("Minimum Vertex Cover: ");
    for (int i = 1; i <= nLeft; i++) {
      if (!visitedI[i]) {
        System.out.print("U" + i + " ");
      }
    }
    for (int j = 1; j <= nRight; j++) {
      if (visitedJ[j]) {
        System.out.print("V" + j + " ");
      }
    }
    System.out.println();
  }

  @Override
  public String toString() {
    StringBuilder builder =
        new StringBuilder("Graph:").append(" V = ").append(this.vertices).append("\n");

    IntStream.range(0, adj.size())
        .forEach(index -> builder.append(index).append(": ").append(adj.get(index)).append("\n"));

    return builder.toString();
  }

  public static void main(String[] args) {
    HopcroftKarp graph = new HopcroftKarp(8, 4, 4);
    graph.addEdge(1, 1);
    graph.addEdge(1, 2);
    graph.addEdge(2, 1);
    graph.addEdge(3, 3);
    graph.addEdge(4, 2);
    graph.addEdge(4, 4);

    int maxMatching = graph.maxMatching();
    System.out.println("Maximum Matching: " + maxMatching);
    graph.printMatching();

    graph.minVertexCoverByKonigsAlgorithm();
  }
}
