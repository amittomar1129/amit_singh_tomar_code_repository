package datastructure_algorithms.patterns.tree.pathsum;

//  Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf
//  path such that adding up all the values along the path equals targetSum. A leaf is a node with no children.
//  Input -> root of a binary tree, integer targetSum
//  Output -> true if there exists a root -> leaf path whose sum equals targetSum
//  Leaf -> node with no left and no right child

//  Example 1:
//  Input: [5,4,8,11,null,13,4,7,2,null,null,null,1]
//  Output: true
//  Explanation: Example 1: The path 5 -> 4 -> 11 -> 2 sums up to 22.

//  Solution: “I perform a DFS, subtracting the node value from the target sum and checking at leaf nodes whether
//  the remaining sum is zero.”
//  Time -> O(n) (visit each node once)
//  Space -> O(h) recursion stack
//    worst -> O(n)
//    balanced -> O(log n)

public class PathSum {

  static class TreeNode {

    int val;
    TreeNode left, right;

    TreeNode(int val) {
      this.val = val;
    }
  }

  public boolean hasPathSum(TreeNode root, int targetSum) {
    if (root == null) {
      return false;
    }
    return dfs(root, targetSum);
  }

  private boolean dfs(TreeNode node, int sum) {
    if (node == null) {
      return false;
    }
    sum -= node.val;
    // leaf node
    if (node.left == null && node.right == null) {
      return sum == 0;
    }

    return dfs(node.left, sum) || dfs(node.right, sum);
  }

  // -------- MAIN METHOD --------
  public static void main(String[] args) {

    PathSum sol = new PathSum();

        /*
           Tree ->
                  5
                /   \
               4     8
              /     / \
             11    13  4
            /  \         \
           7    2         1
        */

    TreeNode root = new TreeNode(5);
    root.left = new TreeNode(4);
    root.right = new TreeNode(8);
    root.left.left = new TreeNode(11);
    root.left.left.left = new TreeNode(7);
    root.left.left.right = new TreeNode(2);
    root.right.left = new TreeNode(13);
    root.right.right = new TreeNode(4);
    root.right.right.right = new TreeNode(1);

    int targetSum = 22;

    System.out.println("Has Path Sum -> " + sol.hasPathSum(root, targetSum));
  }
}
