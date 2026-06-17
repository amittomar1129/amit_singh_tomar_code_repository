package datastructure_algorithms.patterns.graph.cycledetection;


//  In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all other
//  nodes are descendants of this node, plus every node has exactly one parent, except for the root node which has no parents.
//  The given input is a directed graph that started as a rooted tree with n nodes (with distinct values from 1 to n),
//  with one additional directed edge added. The added edge has two different vertices chosen from 1 to n, and was not an edge
//  that already existed. The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] that
//  represents a directed edge connecting nodes u and v, where u is a parent of child v. Return an edge that can be removed so
//  that the resulting graph is a rooted tree of n nodes. If there are multiple answers, return the answer that occurs last
//  in the given 2D-array.

//  A valid rooted tree must satisfy ALL of these:
//  Exactly one root (indegree = 0).
//  Every other node has exactly one parent.
//  No cycles.
//  Total edges = n - 1
//  But here:
//  The graph was a tree.
//  One extra directed edge was added.
//  Now something is broken.

//  (Only 3 cases):
//  Case 1: A node has two parents, NO cycle
//  Case 2: A cycle, but no node has two parents
//  Case 3: A node has two parents AND a cycle

//  Example 1:
//  Input: edges = [[1,2],[1,3],[2,3]]
//  Output: [2,3]
//  Explanation: The edge [2,3] creates a node with two parents, so removing it results in a rooted tree.
//
//  Example 2:
//  Input: edges = [[1,2],[2,3],[3,4],[4,1],[1,5]]
//  Output: [4,1]
//  Explanation: The edge [4,1] creates a cycle, removing it results in a rooted tree.

//  Solution: “A rooted tree violation can happen due to a cycle or a node having two parents. We detect the two-parent case first,
//  then use union-find to detect cycles and determine which edge to remove.”
//  Time	O(n ?(n)) ? O(n)
//  Space	O(n)

public class RedundantConnection {

  static class UnionFind {
    int[] parent;

    UnionFind(int n) {
      parent = new int[n + 1];
      for (int i = 1; i <= n; i++) parent[i] = i;
    }

    int find(int x) {
      if (parent[x] != x)
        parent[x] = find(parent[x]);
      return parent[x];
    }

    boolean union(int u, int v) {
      int pu = find(u);
      int pv = find(v);
      if (pu == pv) return false; // cycle
      parent[pv] = pu;
      return true;
    }
  }

  public static int[] findRedundantDirectedConnection(int[][] edges) {
    int n = edges.length;
    int[] parent = new int[n + 1];

    int[] edge1 = null;
    int[] edge2 = null;

    // Step 1: Detect two parents
    for (int[] e : edges) {
      int u = e[0], v = e[1];
      if (parent[v] == 0) {
        parent[v] = u;
      } else {
        edge1 = new int[]{parent[v], v};
        edge2 = e;
        e[1] = 0; // mark invalid temporarily
      }
    }

    // Step 2: Union-Find
    UnionFind uf = new UnionFind(n);
    for (int[] e : edges) {
      if (e[1] == 0) continue;
      if (!uf.union(e[0], e[1])) {
        // cycle detected
        return edge1 != null ? edge1 : e;
      }
    }

    // Step 3: No cycle ? two-parent case
    return edge2;
  }

  public static void main(String[] args) {
    int[][] edges = {
        {1,2},
        {1,3},
        {2,3}
    };

    int[] res = findRedundantDirectedConnection(edges);
    System.out.println("Remove Edge -> [" + res[0] + ", " + res[1] + "]");
  }

}
