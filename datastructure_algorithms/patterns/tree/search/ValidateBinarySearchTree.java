package datastructure_algorithms.patterns.tree.search;

//  Given the root of a binary tree, determine if it is a valid binary search tree (BST).
//  A valid BST is defined as follows:
//      - The left subtree of a node contains only nodes with keys **less than** the node's key.
//      - The right subtree of a node contains only nodes with keys **greater than** the node's key.
//      - Both the left and right subtrees must also be binary search trees.

//  Time -> O(n) (visit every node once)
//  Space -> O(h) recursion stack
//    balanced -> O(log n)
//    skewed -> O(n)

public class ValidateBinarySearchTree {

  static class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
      this.val = val;
    }
  }

  public static boolean isValidBST(TreeNode root) {
    return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
  }

  private static boolean validate(TreeNode node, long min, long max) {
    if (node == null) {
      return true;
    }
    // current value must be strictly inside range
    if (node.val <= min || node.val >= max) {
      return false;
    }

    // left -> max becomes current value
    // right -> min becomes current value
    return validate(node.left, min, node.val) && validate(node.right, node.val, max);
  }

  // main method with output
  public static void main(String[] args) {

        /*
              5
             / \
            3   7
           / \   \
          2   4   8
        */

    TreeNode root = new TreeNode(5);
    root.left = new TreeNode(3);
    root.right = new TreeNode(7);
    root.left.left = new TreeNode(2);
    root.left.right = new TreeNode(4);
    root.right.right = new TreeNode(8);

    System.out.println("Is valid BST -> " + isValidBST(root));
  }
}
