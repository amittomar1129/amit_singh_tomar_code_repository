package datastructure_algorithms.patterns.linkedlist.inplacereversal;

//  Given a linked list, rotate the list to the right by k places.
//
//  Example 1:
//  Input: 1->2->3->4->5, k = 2
//  Output: 4->5->1->2->3
//  Explanation: rotate 1 steps to the right: 5->1->2->3->4
//
//  Example 2:
//  Input: 0->1->2, k = 4
//  Output: 2->0->1
//  Explanation: rotate 2 steps to the right: 2->0->1
//
//  Example 3:
//  Input: 1, k = 0
//  Output: 1
//  Explanation: no rotation is done

//  Solution: I reduce k using modulo, form a temporary cycle, then break it at the correct position
// to rotate
//  in one pass with O(1) extra space.
//  Time -> O(n)
//  Space -> O(1)

//  k = number of times you rotate the list to the right.
//
//  Rotate right by 1 means
//      1 -> 2 -> 3 -> 4 -> 5
//      ?
//      5 -> 1 -> 2 -> 3 -> 4
//
//  Rotate right by 2
//      4 -> 5 -> 1 -> 2 -> 3

//  Why k = k % length?  Because rotating more than the list length repeats the same positions.

public class RotateList {

  static class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
      this.val = val;
    }
  }

  public static ListNode rotateRight(ListNode head, int k) {
    if (head == null || head.next == null || k == 0) return head;

    // 1?? Compute length and get tail
    ListNode tail = head;
    int length = 1;

    while (tail.next != null) {
      tail = tail.next;
      length++;
    }

    // 2?? Normalize k
    k = k % length;
    if (k == 0) return head;

    // 3?? Make circular
    tail.next = head;

    // 4?? Find new tail
    int stepsToNewTail = length - k;
    ListNode newTail = head;
    for (int i = 1; i < stepsToNewTail; i++) {
      newTail = newTail.next;
    }

    // 5?? Break the circle
    ListNode newHead = newTail.next;
    newTail.next = null;

    return newHead;
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
    // 1 -> 2 -> 3 -> 4 -> 5
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(5);

    int k = 2;

    System.out.println("Original:");
    printList(head);

    head = rotateRight(head, k);

    System.out.println("After Rotating by " + k + ":");
    printList(head);
  }
}
