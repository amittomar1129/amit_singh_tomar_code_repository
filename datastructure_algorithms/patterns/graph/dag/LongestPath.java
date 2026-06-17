package datastructure_algorithms.patterns.graph.dag;

//  You are given a tree (i.e., a connected, undirected graph that has no cycles) consisting of n nodes
//  numbered from 0 to n - 1 and exactly n - 1 edges. Each node has a value associated with it.
//  Return the length of the longest path in the tree such that all the nodes on the path have the same value.
//  The length of a path is the number of edges between the nodes on that path.

//  You are given a tree (connected, no cycles)
//  Each node has a value
//  You need the longest path such that:
//  All nodes on the path have the same value
//  Path:
//  Can start and end at any nodes
//  Must follow parent-child connections
//  Length = number of edges (not nodes)

//  Example 1:
//  Input: vals = [5,4,5,1,1,5], edges = [[0,1],[1,2],[1,3],[3,4],[3,5]]
//  Output: 2
//  Explanation: The longest path with the same value is between nodes 0 and 2 or nodes 2 and 5, both have value 5 and length 2.
//
//  Example 2:
//  Input: vals = [1,1,1,1,1], edges = [[0,1],[1,2],[2,3],[3,4]]
//  Output: 4
//  Explanation: All nodes have the same value 1, so the longest path is the entire tree with length 4.

//  Solution: “I perform a DFS. At each node, I compute the longest downward path with the same value.
//  If both children have the same value as the current node, I can combine both paths to update the global maximum.”
//  Time	O(N)
//  Space	O(H)   H = height of tree

public class LongestPath {

  static class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
      this.val = val;
    }
  }

  static int maxPath = 0;

  public static int longestUnivaluePath(TreeNode root) {
    dfs(root);
    return maxPath;
  }

  private static int dfs(TreeNode node) {
    if (node == null) return 0;

    int left = dfs(node.left);
    int right = dfs(node.right);

    int leftPath = 0, rightPath = 0;

    if (node.left != null && node.left.val == node.val) {
      leftPath = left + 1;
    }

    if (node.right != null && node.right.val == node.val) {
      rightPath = right + 1;
    }

    // Update global maximum
    maxPath = Math.max(maxPath, leftPath + rightPath);

    // Return longest single path
    return Math.max(leftPath, rightPath);
  }

  // Main method
  public static void main(String[] args) {

        /*
               5
              / \
             4   5
            / \   \
           1   1   5
        */

    TreeNode root = new TreeNode(5);
    root.left = new TreeNode(4);
    root.right = new TreeNode(5);
    root.left.left = new TreeNode(1);
    root.left.right = new TreeNode(1);
    root.right.right = new TreeNode(5);

    int result = longestUnivaluePath(root);
    System.out.println("Longest Univalue Path Length = " + result);
  }
}
