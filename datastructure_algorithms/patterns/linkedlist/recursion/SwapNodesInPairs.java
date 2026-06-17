package datastructure_algorithms.patterns.linkedlist.recursion;

//  Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem
//  without modifying the values in the list's nodes (i.e., only nodes themselves may be changed).
//
//  Example 1:
//  Input: head = [1,2,3,4]
//  Output: [2,1,4,3]
//  Explanation: You should swap the first two nodes and then the next two nodes.
//
//      Example 2:
//  Input: head = []
//  Output: []
//  Explanation: The list is empty, so no swapping is needed.
//
//  Example 3:
//  Input: head = [1]
//  Output: [1]
//  Explanation: The list has only one node, so no swapping is possible.

//  Time -> O(n)
//  Space -> O(1)

public class SwapNodesInPairs {

  static class ListNode {

    int val;
    ListNode next;

    ListNode(int val) {
      this.val = val;
    }
  }

  public static ListNode swapPairs(ListNode head) {
    ListNode dummy = new ListNode(-1);
    dummy.next = head;

    ListNode curr = dummy;

    while (curr.next != null && curr.next.next != null) {
      ListNode first = curr.next;
      ListNode second = first.next;

      // Swapping
      first.next = second.next;
      second.next = first;
      curr.next = second;

      // Move prev two nodes ahead
      curr = first;
    }

    return dummy.next;
  }

  public static ListNode swapPairsRecursive(ListNode head) {
    if (head == null || head.next == null) return head;

    ListNode first = head;
    ListNode second = head.next;

    first.next = swapPairsRecursive(second.next);
    second.next = first;

    return second;
  }

  // ---------- MAIN ----------
  public static void main(String[] args) {
    // Input: 1 -> 2 -> 3 -> 4
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);

    ListNode result = swapPairs(head);

    while (result != null) {
      System.out.print(result.val);
      if (result.next != null) {
        System.out.print(" -> ");
      }
      result = result.next;
    }
    System.out.println();
  }
}
