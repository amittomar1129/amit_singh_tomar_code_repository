package datastructure_algorithms.patterns.tree.search;

//  Given the root of a binary search tree and a target value, return the value in the BST that is closest to the target.

//  Time -> O(h)
//  balanced BST -> O(log n)
//  skewed BST -> O(n)
//  Space -> O(1) (iterative)

public class ClosestBinarySearchTreeValue {

  static class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
      this.val = val;
    }
  }

  public static int closestValue(TreeNode root, double target) {
    int closest = root.val;

    while (root != null) {

      // update closest if current node is nearer to target
      if (Math.abs(root.val - target) < Math.abs(closest - target)) {
        closest = root.val;
      }

      // move in BST direction
      if (target < root.val) {
        root = root.left;
      } else {
        root = root.right;
      }
    }

    return closest;
  }

  // main method with output
  public static void main(String[] args) {

        /*
                 4
               /   \
              2     7
             / \
            1   3
        */

    TreeNode root = new TreeNode(4);
    root.left = new TreeNode(2);
    root.right = new TreeNode(7);
    root.left.left = new TreeNode(1);
    root.left.right = new TreeNode(3);

    double target = 3.714;

    int result = closestValue(root, target);

    System.out.println("Closest value to " + target + " -> " + result);
  }
}
