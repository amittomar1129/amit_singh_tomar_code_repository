package datastructure_algorithms.datastructure.nonlinear.tree.graph.shortestpath;

import datastructure_algorithms.datastructure.nonlinear.tree.graph.Edge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;

public class BellManFordAdjList {

  private List<Edge> adj;
  private int vertices; // No. of vertices

  public BellManFordAdjList(int vertices) {
    this.vertices = vertices;
    adj = new ArrayList<>();
  }

  public void addEdge(int i, int j) {
    if (i >= vertices || j >= vertices) {
      throw new IllegalArgumentException(
          String.format("Edge index beyond the vertices size: [%s]", vertices));
    }
    Edge newEdge = new Edge(j);
    newEdge.setFrom(i);
    if (!adj.contains(newEdge)) {
      adj.add(newEdge);
    }
  }

  public void addEdge(int i, int j, int weight) {
    if (i >= vertices || j >= vertices) {
      throw new IllegalArgumentException(
          String.format("Edge index beyond the vertices size: [%s]", vertices));
    }
    Edge newEdge = new Edge(i, j, weight);
    if (!adj.contains(newEdge)) {
      adj.add(newEdge);
    }
  }

  public void removeEdge(int i, int j) {
    if (i >= vertices || j >= vertices) {
      throw new IllegalArgumentException(
          String.format("Edge index beyond the vertices size: [%s]", vertices));
    }
    adj.removeIf(edge -> edge.getFrom() == i && edge.getTo() == j);
  }

  public int[] bellmanFordShortestPath(int source) {
    int[] distance = new int[vertices];
    int[] parent = new int[vertices];
    Arrays.fill(distance, Integer.MAX_VALUE);
    Arrays.fill(parent, -1);
    distance[source] = 0;

    for (int i = 0; i < vertices - 1; i++) {
      for (Edge edge : adj) {
        if (distance[edge.getTo()] > distance[edge.getFrom()] + edge.getWeight()) {
          distance[edge.getTo()] = distance[edge.getFrom()] + edge.getWeight();
          parent[edge.getTo()] = edge.getFrom();
        }
      }
    }

    // Negative Cycle Detection
    Queue<Integer> negativeCycle = new LinkedList<>();
    for (int i = 0; i < vertices - 1; i++) {
      for (Edge edge : adj) {
        if (distance[edge.getTo()] > distance[edge.getFrom()] + edge.getWeight()) {
          printNegativeCycle(edge.getFrom(), edge.getTo(), parent, negativeCycle);
          negativeCycle.offer(edge.getTo());
          System.out.print("Negative Cycle: ");
          while (!negativeCycle.isEmpty()) {
            System.out.print(negativeCycle.poll() + ", ");
          }
          return null;
        }
      }
    }

    System.out.println("Shortest Distance from 0:");
    for (int i = 0; i < distance.length; i++) {
      if (distance[i] == -1) {
        System.out.println(i + ": INF" + ", Path: " + printPath(i, parent));
      } else {
        System.out.println(i + ": " + distance[i] + ", Path: " + printPath(i, parent));
      }
    }

    return distance;
  }

  private String printPath(int destination, int[] parent) {
    if (destination == 0) {
      return destination + "";
    }

    String path = printPath(parent[destination], parent);

    return path + ", " + destination;
  }

  private void printNegativeCycle(int source, int destination, int[] parent, Queue<Integer> queue) {
    if (source == destination) {
      queue.offer(source);
      return;
    }

    printNegativeCycle(parent[source], destination, parent, queue);
    queue.offer(source);
  }


  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("Graph:").append(" V = ").append(this.vertices)
        .append("\n");

    IntStream.range(0, adj.size())
        .forEach(
            index -> builder.append(adj.get(index).getFrom()).append(": ").append(adj.get(index))
                .append("\n"));

    return builder.toString();
  }

  public static void main(String[] args) {

    BellManFordAdjList graph1 = new BellManFordAdjList(5);
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
    BellManFordAdjList graph2 = new BellManFordAdjList(6);
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
    BellManFordAdjList graph = new BellManFordAdjList(4);
    graph.addEdge(0, 1, 4);
    graph.addEdge(0, 2, 5);
    graph.addEdge(1, 3, 7);
    graph.addEdge(2, 1, 7);
    graph.addEdge(3, 2, -15);
    System.out.println(graph);
    // Bellman Ford algorithm does not guarantee to find out all the negative weighted cycles but give the exiting one.
    graph.bellmanFordShortestPath(0);

  }
}
