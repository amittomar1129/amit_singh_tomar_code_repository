package datastructure_algorithms.patterns.tree.validation;

//  Given a binary tree, determine if it is height-balanced. For this problem, a height-balanced binary tree
//  is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

//  Time -> O(n)
//  Space -> O(h) recursion stack

public class Balanced {

  static class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
      this.val = val;
    }
  }

  public static boolean isBalanced(TreeNode root) {
    return height(root) != -1;
  }

  private static int height(TreeNode node) {
    if (node == null) {
      return 0;
    }

    int leftHeight = height(node.left);
    if (leftHeight == -1) {
      return -1;
    }

    int rightHeight = height(node.right);
    if (rightHeight == -1) {
      return -1;
    }

    if (Math.abs(leftHeight - rightHeight) > 1) {
      return -1;
    }

    return Math.max(leftHeight, rightHeight) + 1;
  }

  // main method with output
  public static void main(String[] args) {

        /*
                1
               /
              2
             /
            3
        */

    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.left.left = new TreeNode(3);

    System.out.println("Is tree balanced -> " + isBalanced(root));
  }
}
