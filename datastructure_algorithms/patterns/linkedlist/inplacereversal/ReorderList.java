package datastructure_algorithms.patterns.linkedlist.inplacereversal;

//  Given a singly linked list L: L0->L1->…->Ln-1->Ln, reorder it to: L0->Ln->L1->Ln-1->L2->Ln-2->…
//
//  Example 1:
//  Input: 1->2->3->4
//  Output: 1->4->2->3
//  Explanation: Given linked list: 1->2->3->4, reorder it to 1->4->2->3.
//
//  Example 2:
//  Input: 1->2->3->4->5
//  Output: 1->5->2->4->3
//  Explanation: Given linked list: 1->2->3->4->5, reorder it to 1->5->2->4->3.
//
//  Example 3:
//  Input: 1
//  Output: 1
//  Explanation: Given linked list: 1, reorder it to 1.

//  Time	O(n)
//  Space	O(1)

public class ReorderList {

  // Inner ListNode class
  static class ListNode {

    int val;
    ListNode next;

    ListNode(int val) {
      this.val = val;
    }
  }

//  1->2->3->4->5
  public static void reorderList(ListNode head) {
    if (head == null || head.next == null) {
      return;
    }
    // 1. Find middle
    ListNode slow = head;
    ListNode fast = head.next;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    // 2. Reverse second half
    ListNode second = reverse(slow.next);
    slow.next = null; // split list
    // 3. Merge two halves
    ListNode first = head;
    while (second != null) { //      1->2->3         5->4
      ListNode temp1 = first.next;
      ListNode temp2 = second.next;

      first.next = second;
      second.next = temp1;

      first = temp1;
      second = temp2;
    }
  }

  private static ListNode reverse(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;

    while (curr != null) {
      ListNode next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }
    return prev;
  }

  // Helper to print list
  public static void printList(ListNode head) {
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
    head.next.next.next.next.next = new ListNode(6);

    reorderList(head);
    printList(head);
  }

}
