package datastructure_algorithms.patterns.tree.construct;

//  Given an integer array nums where the elements are sorted in non-decreasing order, convert it to a height-balanced
//  binary search tree. A height-balanced binary tree is a binary tree in which the depth of the two subtrees of
//  every node never differs by more than one.
//
//  Example 1:
//  Input: [-10,-3,0,5,9]
//  Output: [0,-3,9,-10,null,5]
//  Explanation: One possible answer is [0,-3,9,-10,null,5], which represents the following height balanced BST: 0 / \ -3 9 / / -10 5
//
//  Example 2:
//  Input: [1,3]
//  Output: [3,1]
//  Explanation: One possible answer is [3,1], which represents the following height balanced BST: 3 / 1
//
//  Example 3:
//  Input: [-10,-3,0,5,9]
//  Output: [0,-3,9,-10,null,5]
//  Explanation: One possible answer is [0,-3,9,-10,null,5], which represents the following height balanced BST: 0 / \ -3 9 / / -10 5

//  Solution: “Since the array is sorted, choosing the middle element as root at every step guarantees
//  the height difference between left and right subtrees is at most one, producing a balanced BST.”
//Time -> O(n)
//Space -> O(log n) for recursion stack (balanced tree), Worst case -> O(n) (recursion stack)

import java.util.LinkedList;
import java.util.Queue;

public class SortedArrayToBinarySearchTree {

  class Node {

    int val;
    Node left, right;

    Node(int val) {
      this.val = val;
    }
  }

  public Node sortedArrayToBST(int[] input) {
    return build(input, 0, input.length - 1);
  }

  private Node build(int[] input, int left, int right) {
    if (left > right) {
      return null;
    }
    int mid = left + (right - left) / 2;
    Node root = new Node(input[mid]);

    root.left = build(input, left, mid - 1);
    root.right = build(input, mid + 1, right);

    return root;
  }

  // -------- LEVEL ORDER PRINTING --------
  private static void printLevelOrder(Node root) {
    if (root == null) {
      return;
    }

    Queue<Node> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        Node node = queue.poll();
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

    SortedArrayToBinarySearchTree sol = new SortedArrayToBinarySearchTree();

    int[] nums = {-10, -3, 0, 5, 9};

    Node root = sol.sortedArrayToBST(nums);

    System.out.println("Level Order Traversal ->");
    printLevelOrder(root);
  }
}
