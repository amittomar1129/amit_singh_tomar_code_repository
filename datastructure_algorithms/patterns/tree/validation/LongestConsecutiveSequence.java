package datastructure_algorithms.patterns.tree.validation;

//  Given a binary tree, find the length of the longest consecutive sequence path. The path refers to any sequence
//  of nodes from some starting node to any node in the tree along the parent-child connections.
//  The longest consecutive path needs to be from parent to child (cannot be the reverse).

//  Key Clarification:
//  Path must be top-down only (parent -> child)
//  Values must be strictly increasing by 1
//  Path can start at any node, not necessarily root
//
//  This is NOT:
//  longest path in BST
//  not increasing sequence skipping nodes
//  not child -> parent

//  Time -> O(n)
//  Space -> O(h) recursion stack

public class LongestConsecutiveSequence {

  static class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
      this.val = val;
    }
  }

  static int maxLength = 0;

  public static int longestConsecutive(TreeNode root) {
    dfs(root, null, 0);
    return maxLength;
  }

  private static void dfs(TreeNode node, TreeNode parent, int length) {
    if (node == null) {
      return;
    }

    if (parent != null && node.val == parent.val + 1) {
      length++;
    } else {
      length = 1;
    }

    maxLength = Math.max(maxLength, length);

    dfs(node.left, node, length);
    dfs(node.right, node, length);
  }

  // main method with output
  public static void main(String[] args) {

        /*
                 1
                  \
                   3
                  / \
                 2   4
                      \
                       5
        */

    TreeNode root = new TreeNode(1);
    root.right = new TreeNode(3);
    root.right.left = new TreeNode(2);
    root.right.right = new TreeNode(4);
    root.right.right.right = new TreeNode(5);

    System.out.println("Longest consecutive path length -> " + longestConsecutive(root));
  }
}
