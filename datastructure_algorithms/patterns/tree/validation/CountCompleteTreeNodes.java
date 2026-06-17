package datastructure_algorithms.patterns.tree.validation;

//  Given a complete binary tree, count the number of nodes.

//  Time -> O(log² n)
//  Space -> O(log n) recursion stack
//  Why log² n?
//  height calculation -> O(log n)
//  done at each level -> O(log n)

public class CountCompleteTreeNodes {

  static class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
      this.val = val;
    }
  }

  public static int countNodes(TreeNode root) {
    if (root == null) {
      return 0;
    }

    int leftHeight = getLeftHeight(root);
    int rightHeight = getRightHeight(root);

    // perfect binary tree
    if (leftHeight == rightHeight) {
      return (1 << leftHeight) - 1;
    }

    // otherwise recurse
    return 1 + countNodes(root.left) + countNodes(root.right);
  }

  private static int getLeftHeight(TreeNode node) {
    int height = 0;
    while (node != null) {
      height++;
      node = node.left;
    }
    return height;
  }

  private static int getRightHeight(TreeNode node) {
    int height = 0;
    while (node != null) {
      height++;
      node = node.right;
    }
    return height;
  }

  // main method
  public static void main(String[] args) {

        /*
                 1
               /   \
              2     3
             / \   /
            4   5 6
        */

    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    root.left.left = new TreeNode(4);
    root.left.right = new TreeNode(5);
    root.right.left = new TreeNode(6);

    System.out.println("Number of nodes -> " + countNodes(root));
  }
}
