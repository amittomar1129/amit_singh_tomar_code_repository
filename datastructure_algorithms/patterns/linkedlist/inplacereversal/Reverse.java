package datastructure_algorithms.patterns.linkedlist.inplacereversal;
//  Srinivas Epam 7337337539
//  Reverse a singly linked list.
//
//  Example 1:
//  Input: 1->2->3->4->5
//  Output: 5->4->3->2->1
//  Explanation: Example 1
//
//  Example 2:
//  Input: 1->2
//  Output: 2->1
//  Explanation: Example 2
//
//  Example 3:
//  Input:
//  Output:
//  Explanation: Example 3 (empty list)

//  Solution: I iteratively reverse the pointers by maintaining prev, curr, and next pointers.
//  This allows reversing the list in one pass with O(1) extra space.
//  O(n)
//  O(1)

public class Reverse {

  static class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
      this.val = val;
    }
  }

  // 1 -> 2 -> 3 -> 4
  public static ListNode reverseList(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;

    while (curr != null) {
      ListNode temp = curr.next; // save next
      curr.next = prev;          // reverse link
      prev = curr;               // move prev
      curr = temp;               // move curr
    }

    return prev;
  }

  // ---------- PRINT ----------
  static void printList(ListNode head) {
    while (head != null) {
      System.out.print(head.val);
      if (head.next != null) System.out.print(" -> ");
      head = head.next;
    }
    System.out.println();
  }

  // ---------- MAIN ----------
  public static void main(String[] args) {
    // 1 -> 2 -> 3 -> 4
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);

    System.out.println("Original:");
    printList(head);

    head = sol(head);

    System.out.println("Reversed:");
    printList(head);
  }

  public static ListNode sol(ListNode head) {
    ListNode curr = head;
    ListNode prev = null;

    while (curr != null) {
      ListNode temp = curr.next;
      curr.next = prev;
      prev = curr;
      curr = temp;
    }
    return prev;
  }
}
