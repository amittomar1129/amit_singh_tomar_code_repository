package datastructure_algorithms.patterns.linkedlist.inplacereversal;

//  Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
//  k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes
//  is not a multiple of k then left-out nodes, in the end, should remain as it is.
//
//  Example 1:
//  Input: 1->2->3->4->5, k = 2
//  Output: 2->1->4->3->5
//  Explanation: Reverse the first two nodes.
//
//      Example 2:
//  Input: 1->2->3->4->5, k = 3
//  Output: 3->2->1->4->5
//  Explanation: Reverse the first three nodes.
//
//      Example 3:
//  Input: 1->2->3->4->5, k = 1
//  Output: 1->2->3->4->5
//  Explanation: No reversing is done as k=1.

//  Solution: We process the list in fixed-size groups, reverse each group in-place after verifying availability,
//  and reconnect the segments while leaving leftover nodes untouched.
//  Time	O(n)
//  Extra Space	O(1)
//  In-place	Yes

public class ReverseNodesInKGroup {

  // Inner ListNode class
  static class ListNode {

    int val;
    ListNode next;

    ListNode(int val) {
      this.val = val;
    }
  }

  public static ListNode reverseKGroup(ListNode head, int k) {
    if (head == null || k == 1) {
      return head;
    }

    ListNode dummy = new ListNode(0);
    dummy.next = head;

    ListNode prevGroupEnd = dummy;
// 1->2->3->4->5
    while (true) {
      // Step 1: Check if k nodes exist
      ListNode kth = prevGroupEnd;
      for (int i = 0; i < k; i++) {
        kth = kth.next;
        if (kth == null) {
          return dummy.next;
        }
      }
      // Step 2: Reverse k nodes
      ListNode groupStart = prevGroupEnd.next;
      ListNode nextGroupStart = kth.next;

      ListNode prev = nextGroupStart;
      ListNode curr = groupStart;

      while (curr != nextGroupStart) {
        ListNode temp = curr.next;
        curr.next = prev;
        prev = curr;
        curr = temp;
      }

      // Step 3: Reconnect
      prevGroupEnd.next = kth;
      prevGroupEnd = groupStart;
    }
  }

  // Helper method to print list
  static void printList(ListNode head) {
    while (head != null) {
      System.out.print(head.val + " -> ");
      head = head.next;
    }
    System.out.println("null");
  }

  public static void main(String[] args) {
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(5);

    int k = 3;
    ListNode result = reverseKGroup(head, k);
    printList(result);
  }

}
