package datastructure_algorithms.datastructure.nonlinear.tree.graph.connectivity;

import datastructure_algorithms.datastructure.nonlinear.tree.graph.Edge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;

public class Tarjan {

  private List<List<Edge>> adj;
  private int vertices; // No. of vertices
  private int time;                                                // Global timer for discovery time

  public Tarjan(int vertices) {
    this.vertices = vertices;
    adj = new ArrayList<>();
    for (int i = 0; i < vertices; i++) {
      adj.add(new ArrayList<>());
    }
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

  public List<List<Integer>> findSCC() {
    List<List<Integer>> result = new ArrayList<>();
    int[] discoveryTime = new int[vertices];                     // Discovery time of each node
    int[] lowestTime = new int[vertices];                        // Lowest reachable discovery time
    boolean[] inStack = new boolean[vertices];                     // Track whether a node is on the stack
    Stack<Integer> stack = new Stack<>();                          // Stack to hold the current DFS path

    Arrays.fill(discoveryTime, -1);

    for (int i = 0; i < vertices; i++) {
      if (discoveryTime[i] == -1) {
        dfs(i, discoveryTime, lowestTime, inStack, stack, result);
      }
    }
    return result;
  }

  private void dfs(int i, int[] discoveryTime, int[] lowestTime, boolean[] inStack,
      Stack<Integer> stack, List<List<Integer>> sccs) {
    discoveryTime[i] = lowestTime[i] = time++;
    stack.push(i);
    inStack[i] = true;

    for (Edge edge : adj.get(i)) {
      if (discoveryTime[edge.getTo()] == -1) {
        dfs(edge.getTo(), discoveryTime, lowestTime, inStack, stack, sccs);
        lowestTime[i] = Math.min(lowestTime[i], lowestTime[edge.getTo()]);
      } else if (inStack[edge.getTo()]) {
        lowestTime[i] = Math.min(lowestTime[i], discoveryTime[edge.getTo()]);
      }
    }

    // If i is head of SCC
    if (lowestTime[i] == discoveryTime[i]) {
      List<Integer> scc = new ArrayList<>();
      int node;
      do {
        node = stack.pop();
        inStack[node] = false;
        scc.add(node);
      } while (node != i);
      sccs.add(scc);
    }
  }


  public void findArticulationPointAndBridge() {
    this.time = 0;
    int[] discoveryTime = new int[vertices];                     // Discovery time of each node
    int[] lowestTime = new int[vertices];                        // Lowest reachable discovery time
    int[] parent = new int[vertices];
    boolean[] articulation = new boolean[vertices];
    List<List<Integer>> bridges = new ArrayList<>();

    Arrays.fill(discoveryTime, -1);
    Arrays.fill(lowestTime, -1);
    Arrays.fill(parent, -1);

    for (int i = 0; i < vertices; i++) {
      if (discoveryTime[i] == -1) {
        dfs(i, discoveryTime, lowestTime, parent, articulation, bridges);
      }
    }
    printResults(articulation, bridges);
  }

  private void dfs(int i, int[] discoveryTime, int[] lowestTime, int[] parent,
      boolean[] articulation, List<List<Integer>> bridges) {
    discoveryTime[i] = lowestTime[i] = time++;
    int children = 0;

    for (Edge edge : adj.get(i)) {
      if (discoveryTime[edge.getTo()] == -1) { // Tree edge
        children++;
        parent[edge.getTo()] = i;
        dfs(edge.getTo(), discoveryTime, lowestTime, parent, articulation, bridges);
        lowestTime[i] = Math.min(lowestTime[i], lowestTime[edge.getTo()]);

        // Bridge check
        if (discoveryTime[i] < lowestTime[edge.getTo()]) {
          ArrayList<Integer> bridge = new ArrayList<>();
          bridge.add(i);
          bridge.add(edge.getTo());
          bridges.add(bridge);
        }
        // Articulation point check
        if ((parent[i] == -1 && children > 1) || (parent[i] != -1
            && discoveryTime[i] <= lowestTime[edge.getTo()])) {
          articulation[i] = true;
        }
      } else if (edge.getTo() != parent[i]) { // Back edge
        lowestTime[i] = Math.min(lowestTime[i], discoveryTime[edge.getTo()]);
      }
    }
  }

  public void printResults(boolean[] articulation, List<List<Integer>> bridges) {
    System.out.print("Articulation Points: ");
    for (int i = 0; i < vertices; i++) {
      if (articulation[i]) {
        System.out.print(i + ", ");
      }
    }
    System.out.println();

    System.out.println("Bridges:");
    for (List<Integer> bridge : bridges) {
      System.out.println(bridge);
    }
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("Graph:").append(" V = ").append(this.vertices)
        .append("\n");

    IntStream.range(0, adj.size())
        .forEach(index -> builder.append(index).append(": ").append(adj.get(index)).append("\n"));

    return builder.toString();
  }

  public static void main(String[] args) {
    Tarjan graph = new Tarjan(7);
    graph.addEdge(0, 1);
    graph.addEdge(1, 2);
    graph.addEdge(1, 4);
    graph.addEdge(2, 3);
    graph.addEdge(3, 0);
    graph.addEdge(4, 5);
    graph.addEdge(5, 6);
    graph.addEdge(6, 4);
    System.out.println(graph);
    List<List<Integer>> sccs = graph.findSCC();
    System.out.println("Strongly Connected Components:");
    for (List<Integer> scc : sccs) {
      System.out.println(scc);
    }

    graph.findArticulationPointAndBridge();
  }
}
