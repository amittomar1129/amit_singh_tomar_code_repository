package datastructure_algorithms.datastructure.nonlinear.tree.graph.connectivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DisjointSetUnion {

  private int[] parent; // Parent of each node
  private int[] rank; // Size of each component starting with each node
  private int totalComponents;

  public DisjointSetUnion(int vertices) {
    this.parent = new int[vertices];
    this.rank = new int[vertices];
    for (int i = 0; i < vertices; i++) {
      this.parent[i] = i; // Each node is its own parent initially
      this.rank[i] = 1;
    }
    this.totalComponents = vertices;
  }

  // Find with path compression
  public int find(int node) {
    if (parent[node] != node) {
      parent[node] = find(parent[node]); // path compression
    }
    return parent[node];
  }

  // Union by size
  public void union(int source, int destination, List<List<Integer>> cycles) {
    int sourceRoot = find(source);
    int sourceDestination = find(destination);
    if (sourceRoot == sourceDestination) {
      System.out.println("Cycle Detected");
      getCycle(source, destination, cycles);
    }

    if (rank[sourceDestination] > rank[sourceRoot]) {
      parent[sourceRoot] = sourceDestination;
      rank[sourceDestination] += rank[sourceRoot];
    } else {
      parent[sourceDestination] = sourceRoot;
      rank[sourceRoot] += rank[sourceDestination];
    }
    totalComponents--;
  }

  public void getCycle(int source, int destination, List<List<Integer>> cycles) {
    {
      System.out.println("Cycle Detected");
      ArrayList<Integer> cycle = new ArrayList<>();
      List<Integer> component = getComponent(source);
      boolean start = false;
      for (int node : component) {
        if (node == destination) {
          start = true;
        }
        if (node == source) {
          cycle.add(node);
          start = false;
        }
        if (start) {
          cycle.add(node);
        }
      }
      cycle.add(destination);
      cycles.add(cycle);
    }
  }

  // Check if two nodes are in the same component
  public boolean connected(int a, int b) {
    return find(a) == find(b);
  }

  // Count number of connected components
  public int countComponents() {
    Set<Integer> set = new HashSet<>();
    for (int i = 0; i < parent.length; i++) {
      set.add(parent[i]);
    }
    return set.size();
    // Or Just return this.totalComponents
  }

  // Count number of connected components
  public List<List<Integer>> getAllComponents() {
    HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
    for (int i = 0; i < parent.length; i++) {
      int root = parent[i];
      map.putIfAbsent(root, new ArrayList<>());
      map.get(root).add(i);
    }
    return new ArrayList<>(map.values());
  }

  // Gets connected component this node belongs to
  public List<Integer> getComponent(int node) {
    for (List<Integer> component : getAllComponents()) {
      if (component.contains(node)) {
        return component;
      }
    }
    return new ArrayList<>();
  }

  public static void main(String[] args) {

    DisjointSetUnion disjointSetUnion = new DisjointSetUnion(6); // 6 Vertices
    // Graph edges
    int[][] edges = {{0, 1}, {1, 2}, {3, 4}};

    List<List<Integer>> cycles = new ArrayList<>();
    // Apply unions
    for (int[] edge : edges) {
      disjointSetUnion.union(edge[0], edge[1], cycles);
    }
    for (List<Integer> cycle : cycles) {
      System.out.println("Cycle : " + cycle);
    }

    // Print parent array for debugging
    System.out.println("Parent array: " + Arrays.toString(disjointSetUnion.parent));
    System.out.println("Size array: " + Arrays.toString(disjointSetUnion.rank));

    // Connectivity checks
    System.out.println("0 and 2 connected? " + disjointSetUnion.connected(0, 2)); // true
    System.out.println("2 and 3 connected? " + disjointSetUnion.connected(2, 3)); // false

    // Print total no of components
    System.out.println(disjointSetUnion.countComponents());
    System.out.println(disjointSetUnion.totalComponents);
    // Print all connected components
    System.out.println(disjointSetUnion.getAllComponents());
  }
}
