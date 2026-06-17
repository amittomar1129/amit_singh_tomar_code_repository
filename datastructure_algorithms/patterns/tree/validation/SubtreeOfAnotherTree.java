package datastructure_algorithms.patterns.tree.validation;

//  Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values
//  with a subtree of s. A subtree of s is a tree that consists of a node in s and all of this node's descendants.
//  The tree s could also be considered as a subtree of itself.

//  Time -> O(n * m) worst case
//      n = nodes in s
//      m = nodes in t
//  Space -> O(h) recursion stack

public class SubtreeOfAnotherTree {

  static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
      this.val = val;
    }
  }

  public static boolean isSubtree(TreeNode s, TreeNode t) {
    if (s == null) {
      return false;
    }

    if (isSameTree(s, t)) {
      return true;
    }

    return isSubtree(s.left, t) || isSubtree(s.right, t);
  }

  private static boolean isSameTree(TreeNode a, TreeNode b) {
    if (a == null && b == null) {
      return true;
    }
    if (a == null || b == null) {
      return false;
    }

    if (a.val != b.val) {
      return false;
    }

    return isSameTree(a.left, b.left)
        && isSameTree(a.right, b.right);
  }

  // main method with output
  public static void main(String[] args) {

        /*
                Tree s:
                     3
                    / \
                   4   5
                  / \
                 1   2

                Tree t:
                     4
                    / \
                   1   2
        */

    TreeNode s = new TreeNode(3);
    s.left = new TreeNode(4);
    s.right = new TreeNode(5);
    s.left.left = new TreeNode(1);
    s.left.right = new TreeNode(2);

    TreeNode t = new TreeNode(4);
    t.left = new TreeNode(1);
    t.right = new TreeNode(2);

    System.out.println("Is subtree -> " + isSubtree(s, t));
  }
}
