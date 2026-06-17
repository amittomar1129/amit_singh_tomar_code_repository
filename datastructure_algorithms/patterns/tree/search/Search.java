package datastructure_algorithms.patterns.tree.search;

//  You are given the root of a binary search tree (BST) and an integer val. Find the node in the BST that the
//  node's value equals val and return the subtree rooted with that node. If such a node does not exist, return null.

//  Time: O(h)
//  h = height of BST
//  Balanced BST -> O(log n)
//  Skewed BST -> O(n)
//
//  Space:
//  Recursive -> O(h)
//  Iterative -> O(1)

public class Search {

  static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
      this.val = val;
    }
  }

  // Search function
  public static TreeNode searchBST(TreeNode root, int val) {
    if (root == null) {
      return null;
    }

    if (root.val == val) {
      return root;
    }

    if (val < root.val) {
      return searchBST(root.left, val);
    } else {
      return searchBST(root.right, val);
    }
  }

  // Helper -> inorder traversal to print subtree
  public static void inorder(TreeNode root) {
    if (root == null) return;

    inorder(root.left);
    System.out.print(root.val + " ");
    inorder(root.right);
  }

  // main method
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

    int val = 2;

    TreeNode result = searchBST(root, val);

    if (result != null) {
      System.out.print("Subtree rooted at " + val + " -> ");
      inorder(result);
    } else {
      System.out.println("Value not found");
    }
  }
}
