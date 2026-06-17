package datastructure_algorithms.patterns.tree.pathsum;

//  Given a non-empty binary tree, find the maximum path sum. For this problem, a path is defined as any sequence
//  of nodes from some starting node to any node in the tree along the parent-child connections.
//  The path must contain at least one node and does not need to go through the root.

//  Input -> root of a non-empty binary tree
//  Output -> maximum path sum, where a path: can start and end at any node, must go along parent-child connections.
//          must contain at least one node.
//  Path does not need to go through root.

//  Example 1:
//  Input: [1,2,3]
//  Output: 6
//  Explanation: The maximum path sum is 2 + 1 + 3 = 6.

//  Solution: “I compute the maximum gain from each node recursively, updating a global max sum at each node
//  considering both left and right subtrees, and return the maximum gain to parent.”
//  Time -> O(n), visit each node once
//  Space -> O(h) recursion stack
//    Worst-case skewed tree -> O(n)
//    Balanced tree -> O(log n)


public class MaximumPathSum {

  static class TreeNode {

    int val;
    TreeNode left, right;

    TreeNode(int val) {
      this.val = val;
    }
  }

  private int maxSum = Integer.MIN_VALUE;

  public int maxPathSum(TreeNode root) {
    dfs(root);
    return maxSum;
  }

  // returns max gain from current node to one side
  private int dfs(TreeNode node) {
    if (node == null) {
      return 0;
    }

    // max gain from left/right subtree, ignore negatives
    int leftGain = Math.max(dfs(node.left), 0);
    int rightGain = Math.max(dfs(node.right), 0);

    // price of new path including both sides
    int priceNewPath = node.val + leftGain + rightGain;
    // update global max
    maxSum = Math.max(maxSum, priceNewPath);
    // return max gain to parent
    return node.val + leftGain + rightGain;
  }

  // -------- MAIN METHOD --------
  public static void main(String[] args) {

    MaximumPathSum sol = new MaximumPathSum();

        /*
            Tree ->
                  -10
                  /  \
                 9   20
                    /  \
                   15   7
        */

//      Explanation:
//      Path: 15 -> 20 -> 7
//      Sum = 15 + 20 + 7 = 42

    TreeNode root = new TreeNode(-10);
    root.left = new TreeNode(9);
    root.right = new TreeNode(20);
    root.right.left = new TreeNode(15);
    root.right.right = new TreeNode(7);

    int maxSum = sol.maxPathSum(root);

    System.out.println("Maximum Path Sum -> " + maxSum);
  }
}
