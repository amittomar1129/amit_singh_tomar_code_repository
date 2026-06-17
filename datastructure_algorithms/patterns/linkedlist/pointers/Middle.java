package datastructure_algorithms.patterns.linkedlist.pointers;

//  Given a non-empty, singly linked list with head node head, return a middle node of linked list.
//  If there are two middle nodes, return the second middle node.
//
//  Example 1:
//  Input: [1,2,3,4,5]
//  Output: 3
//  Explanation: The input list has 5 nodes. The middle node is node 3.
//
//  Example 2:
//  Input: [1,2,3,4,5,6]
//  Output: 4
//  Explanation: The input list has 6 nodes. The middle node is node 4.
//
//  Example 3:
//  Input: [1]
//  Output: 1
//  Explanation: The input list has 1 node. The middle node is node 1.

//  Solution: I use two pointers where fast moves twice as fast as slow. When fast reaches the end,
//  slow is guaranteed to be at the middle. For even-length lists, this naturally returns the second middle node.
//  O(n)
//  O(1)

import datastructure_algorithms.datastructure.nonlinear.tree.graph.Edge;

public class Middle {

  static class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
      this.val = val;
    }
  }

  public static ListNode middleNode(ListNode head) {
    ListNode slow = head;
    ListNode fast = head; // To print first element, fast = head.next

    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    return slow;
  }

  public static void main(String[] args) {
    // Create list: 1 -> 2 -> 3 -> 4 -> 5 -> 6
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(5);
    head.next.next.next.next.next = new ListNode(6);

    ListNode middle = middleNode(head);
    System.out.println("Middle Node Value -> " + middle.val); // 4
  }
}
