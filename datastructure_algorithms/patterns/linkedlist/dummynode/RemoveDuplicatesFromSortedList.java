package datastructure_algorithms.patterns.linkedlist.dummynode;


//  Given the head of a sorted linked list, delete all duplicates such that each element appears only once.
//  Return the linked list sorted as well.
//
//  Example 1:
//  Input: head = [1,1,2]
//  Output: [1,2]
//  Explanation: Input: 1->1->2, Output: 1->2
//
//  Example 2:
//  Input: head = [1,1,2,3,3]
//  Output: [1,2,3]
//  Explanation: Input: 1->1->2->3->3, Output: 1->2->3
//
//  Example 3:
//  Input: head = [0,0,1,1,1,2,2,3,3,4]
//  Output: [0,1,2,3,4]
//  Explanation: Input: 0->0->1->1->1->2->2->3->3->4, Output: 0->1->2->3->4

//  Time -> O(n)
//  Space -> O(1)

public class RemoveDuplicatesFromSortedList {

  static class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
      this.val = val;
    }
  }

  public static ListNode deleteDuplicates(ListNode head) {
    ListNode current = head;

    while (current != null && current.next != null) {
      if (current.val == current.next.val) {
        // Skip duplicate
        current.next = current.next.next;
      } else {
        current = current.next;
      }
    }

    return head;
  }

  public static void main(String[] args) {
    // Input: 1 -> 1 -> 2 -> 3 -> 3
    ListNode head = new ListNode(1);
    head.next = new ListNode(1);
    head.next.next = new ListNode(2);
    head.next.next.next = new ListNode(3);
    head.next.next.next.next = new ListNode(3);

    ListNode result = deleteDuplicates(head);

    while (result != null) {
      System.out.print(result.val + " -> ");
      result = result.next;
    }
    System.out.println("null");
  }

}
