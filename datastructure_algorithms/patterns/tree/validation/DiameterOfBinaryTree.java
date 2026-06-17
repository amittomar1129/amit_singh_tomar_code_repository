package datastructure_algorithms.patterns.tree.validation;

//  Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is
//  the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
//
//  Example 1:
//  Input: [1,2,3,4,5]
//  Output: 3
//  Explanation: The length of the longest path is [4,2,1,3] or [5,2,1,3].

//  Time -> O(n) (each node visited once)
//  Space -> O(h) recursion stack

public class DiameterOfBinaryTree {

  static class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
      this.val = val;
    }
  }

  static int maxDiameter = 0;

  public static int diameterOfBinaryTree(TreeNode root) {
    height(root);
    return maxDiameter;
  }

  private static int height(TreeNode node) {
    if (node == null) {
      return 0;
    }

    int leftHeight = height(node.left);
    int rightHeight = height(node.right);

    // update diameter at this node
    maxDiameter = Math.max(maxDiameter, leftHeight + rightHeight);

    // return height to parent
    return Math.max(leftHeight, rightHeight) + 1;
  }

  // main method with output
  public static void main(String[] args) {

        /*
                 1
                / \
               2   3
              / \   \
             4   5   6
        */

    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    root.right.right = new TreeNode(6);
    root.left.left = new TreeNode(4);
    root.left.right = new TreeNode(5);

    System.out.println("Diameter of tree -> " + diameterOfBinaryTree(root));
  }
}
