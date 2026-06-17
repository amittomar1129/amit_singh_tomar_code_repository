package datastructure_algorithms.patterns.tree.mirror;

//  Invert a binary tree. Invert -> swap left and right children for every node.
//
//  Example 1:
//  Input: [4,2,7,1,3,6,9]
//  Output: [4,7,2,9,6,3,1]
//  Explanation: The input binary tree is 4 / \ 2 7 / \ / \ 1 3 6 9 The inverted binary tree is 4 / \ 7 2 / \ / \ 9 6 3 1
//
//  Example 2:
//  Input: []
//  Output: []
//  Explanation: The input binary tree is empty, so the inverted tree is also empty.
//
//  Example 3:
//  Input: [1,2]
//  Output: [1,null,2]
//  Explanation: The input binary tree is 1 / 2 The inverted binary tree is 1 2

//  Solution:
//  Time -> O(n) (visit every node once)
//  Space -> O(h) recursion stack
//    Worst case -> O(n) (skewed tree)
//    Best case -> O(log n) (balanced tree)

import java.util.LinkedList;
import java.util.Queue;

public class InvertBinaryTree {

  // -------- INNER TREE NODE CLASS --------
  static class TreeNode {

    int val;
    TreeNode left, right;

    TreeNode(int val) {
      this.val = val;
    }
  }

  public TreeNode invertTree(TreeNode root) {
    if (root == null) {
      return null;
    }
    // swap left and right
    TreeNode temp = root.left;
    root.left = root.right;
    root.right = temp;

    invertTree(root.left);
    invertTree(root.right);

    return root;
  }

  // -------- LEVEL ORDER PRINTING --------
  private static void printLevelOrder(TreeNode root) {
    if (root == null) {
      return;
    }

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        TreeNode node = queue.poll();
        System.out.print(node.val + " ");
        if (node.left != null) {
          queue.offer(node.left);
        }
        if (node.right != null) {
          queue.offer(node.right);
        }
      }
      System.out.println();
    }
  }

  // -------- MAIN METHOD --------
  public static void main(String[] args) {

    InvertBinaryTree sol = new InvertBinaryTree();

        /*
            Original Tree ->
                  4
                /   \
               2     7
              / \   / \
             1   3 6   9
        */
        /*
            Inverted Tree ->
                  4
                /   \
               7     2
              / \   / \
             9   6 3   1
        */


    TreeNode root = new TreeNode(4);
    root.left = new TreeNode(2);
    root.right = new TreeNode(7);
    root.left.left = new TreeNode(1);
    root.left.right = new TreeNode(3);
    root.right.left = new TreeNode(6);
    root.right.right = new TreeNode(9);

    System.out.println("Original Tree Level Order ->");
    printLevelOrder(root);

    sol.invertTree(root);

    System.out.println("Inverted Tree Level Order ->");
    printLevelOrder(root);
  }
}
