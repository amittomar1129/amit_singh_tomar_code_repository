package datastructure_algorithms.patterns.tree.construct;

//  Given the head of a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
//  For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees
//  of every node never differ by more than 1.
//
//  Example 1:
//  Input: head = [-10,-3,0,5,9]
//  Output: [0,-3,9,-10,null,5]
//  Explanation: One possible answer is [0,-3,9,-10,null,5], which represents the shown height balanced BST.
//
//      Example 2:
//  Input: head = [1,3]
//  Output: [3,1]
//  Explanation: The answer [3,1] is also accepted.
//
//      Example 3:
//  Input: head = [0]
//  Output: [0]
//  Explanation: The answer [0] is also accepted.

//  Solution: “Since the linked list is sorted, I simulate an inorder traversal of a BST, advancing the list
//  pointer exactly once per node, which builds a height-balanced tree in O(n) time.”
//  Count the length of the linked list. Recursively build BST using inorder traversal.
//  Maintain a global pointer head that moves forward exactly once per node.
//  Time -> O(n)
//  Space -> O(log n) recursion stack (balanced tree)

import java.util.LinkedList;
import java.util.Queue;

public class SortedListToBinarySearchTree {

  class ListNode {

    int val;
    ListNode next;

    ListNode(int val) {
      this.val = val;
    }
  }

  // -------- INNER TREE NODE CLASS --------
  class TreeNode {

    int val;
    TreeNode left, right;

    TreeNode(int val) {
      this.val = val;
    }
  }

  // Global pointer to list head
  private ListNode current;

  public TreeNode sortedListToBST(ListNode head) {
    int size = getSize(head);
    current = head;
    return buildBST(0, size - 1);
  }

  private TreeNode buildBST(int left, int right) {
    if (left > right) {
      return null;
    }

    int mid = left + (right - left) / 2;
    // build left subtree
    TreeNode leftChild = buildBST(left, mid - 1);
    // current list node becomes root
    TreeNode root = new TreeNode(current.val);
    current = current.next;
    // build right subtree
    TreeNode rightChild = buildBST(mid + 1, right);

    root.left = leftChild;
    root.right = rightChild;

    return root;
  }

  private int getSize(ListNode head) {
    int count = 0;
    while (head != null) {
      count++;
      head = head.next;
    }
    return count;
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

    SortedListToBinarySearchTree sol = new SortedListToBinarySearchTree();

    // Create linked list -> -10 -> -3 -> 0 -> 5 -> 9
    ListNode head = sol.new ListNode(-10);
    head.next = sol.new ListNode(-3);
    head.next.next = sol.new ListNode(0);
    head.next.next.next = sol.new ListNode(5);
    head.next.next.next.next = sol.new ListNode(9);

    TreeNode root = sol.sortedListToBST(head);

    System.out.println("Level Order Traversal ->");
    printLevelOrder(root);
  }
}
