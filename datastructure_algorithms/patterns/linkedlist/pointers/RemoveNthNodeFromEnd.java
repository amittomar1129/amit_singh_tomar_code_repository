package datastructure_algorithms.patterns.linkedlist.pointers;

//  Given the head of a linked list, remove the nth node from the end of the list and return its head.
//
//  Example 1:
//  Input: [1,2,3,4,5], 2
//  Output: [1,2,3,5]
//  Explanation: Given linked list: 1->2->3->4->5, and n = 2. After removing the second node from the end, the linked list becomes 1->2->3->5.
//
//  Example 2:
//  Input: [1], 1
//  Output: []
//  Explanation: Given linked list: 1, and n = 1. After removing the first node from the end, the linked list becomes [] (empty list).
//
//  Example 3:
//  Input: [1,2], 1
//  Output: [1]
//  Explanation: Given linked list: 1->2, and n = 1. After removing the first node from the end, the linked list becomes 1.


//  Solution: I maintain a fixed gap of n nodes between two pointers. When fast reaches the end,
//  slow is positioned just before the node to remove.
//  Dummy node ensures head removal works cleanly.
//  Time -> O(n)
//  Space -> O(1)


public class RemoveNthNodeFromEnd {

  static class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
      this.val = val;
      this.next = null;
    }
  }

  public static ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;

    ListNode slow = dummy;
    ListNode fast = dummy;

    for (int i = 0; i <= n; i++) {
      fast = fast.next;
    }
    // Move both pointers
    while (fast != null) {
      fast = fast.next;
      slow = slow.next;
    }
    // Delete the nth node from end
    slow.next = slow.next.next;
    return dummy.next;
  }

  public static void main(String[] args) {
    // 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(5);
    head.next.next.next.next.next = new ListNode(6);
    head.next.next.next.next.next.next = new ListNode(7);
    head.next.next.next.next.next.next.next = new ListNode(8);
    head.next.next.next.next.next.next.next.next = new ListNode(9);
    head.next.next.next.next.next.next.next.next.next = new ListNode(10);

    head = removeNthFromEnd(head, 2);

    // Print result
    ListNode curr = head;
    while (curr != null) {
      System.out.print(curr.val + " -> ");
      curr = curr.next;
    }
    System.out.println("null");
  }
}
