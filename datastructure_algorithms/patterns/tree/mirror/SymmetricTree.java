package datastructure_algorithms.patterns.tree.mirror;

//  Given a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
//  Symmetric -> left subtree is a mirror of right subtree
//  Two trees are mirrors if:
//  Their root values are equal
//  Left subtree of one == right subtree of the other
//  Right subtree of one == left subtree of the other

//  Example 1:
//  Input: [1,2,2,3,4,4,3]
//  Output: true
//  Explanation: Example 1: The above binary tree is symmetric.

//  Solution: “I recursively compare the left and right subtrees in mirrored order, ensuring values match and structure is symmetric.”
//  Time -> O(n) (each node visited once)
//  Space -> O(h) recursion stack
//    Worst -> O(n)
//    Best -> O(log n)

public class SymmetricTree {

  static class TreeNode {

    int val;
    TreeNode left, right;

    TreeNode(int val) {
      this.val = val;
    }
  }

  public boolean isSymmetric(TreeNode root) {
    if (root == null) {
      return true;
    }
    return isMirror(root.left, root.right);
  }

  private boolean isMirror(TreeNode left, TreeNode right) {

    if (left == null && right == null) {
      return true;
    }
    if (left == null || right == null) {
      return false;
    }
    if (left.val != right.val) {
      return false;
    }

    return isMirror(left.left, right.right) && isMirror(left.right, right.left);
  }

  // -------- MAIN METHOD --------
  public static void main(String[] args) {

    SymmetricTree sol = new SymmetricTree();

        /*
            Symmetric Tree ->
                  1
                /   \
               2     2
              / \   / \
             3   4 4   3
        */

    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(2);
    root.left.left = new TreeNode(3);
    root.left.right = new TreeNode(4);
    root.right.left = new TreeNode(4);
    root.right.right = new TreeNode(3);

    System.out.println("Is Tree Symmetric -> " + sol.isSymmetric(root));
  }

}
