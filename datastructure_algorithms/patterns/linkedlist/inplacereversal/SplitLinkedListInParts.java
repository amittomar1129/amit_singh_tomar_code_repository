package datastructure_algorithms.patterns.linkedlist.inplacereversal;

//  Given the head of a singly linked list and an integer k, split the linked list into k consecutive linked list parts.
//  The length of each part should be as equal as possible: no two parts should have a size differing by more than one.
//  This may lead to some parts being null. The parts should be in the order of occurrence in the input list,
//  and parts occurring earlier should always have a size greater than or equal to parts occurring later.
//
//  Example 1:
//  Input: [1, 2, 3], k = 5
//  Output: [[1],[2],[3],[],[]]
//  Explanation: The input and each element of the output are ListNodes, not arrays. For example, the input linked list is [1, 2, 3].
//
//  Example 2:
//  Input: [1, 2, 3], k = 3
//  Output: [[1],[2],[3]]
//  Explanation: The input and each element of the output are ListNodes, not arrays. For example, the input linked list is [1, 2, 3].
//
//  Example 3:
//  Input: [1, 2, 3], k = 1
//  Output: [[1, 2, 3]]
//  Explanation: The input and each element of the output are ListNodes, not arrays. For example, the input linked list is [1, 2, 3].

//  Time	O(n)
//  Extra Space	O(k) (output array only)
//  In-place nodes	Yes

public class SplitLinkedListInParts {

  // Inner ListNode class
  static class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
      this.val = val;
    }
  }

  public static ListNode[] splitListToParts(ListNode head, int k) {
    ListNode[] result = new ListNode[k];

    // Step 1: Count length
    int length = 0;
    ListNode curr = head;
    while (curr != null) {
      length++;
      curr = curr.next;
    }

    // Step 2: Calculate part sizes
    int minSize = length / k;
    int extra = length % k;

    // Step 3: Split
    curr = head;
    for (int i = 0; i < k; i++) {
      result[i] = curr;
      int partSize = minSize + (i < extra ? 1 : 0);

      for (int j = 1; j < partSize; j++) {
        if (curr != null) {
          curr = curr.next;
        }
      }

      if (curr != null) {
        ListNode next = curr.next;
        curr.next = null; // break
        curr = next;
      }
    }

    return result;
  }

  // Helper to print list
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
    head.next.next.next.next.next = new ListNode(6);
    head.next.next.next.next.next.next = new ListNode(7);

    int k = 3;
    ListNode[] parts = splitListToParts(head, k);

    for (int i = 0; i < parts.length; i++) {
      System.out.print("Part " + (i + 1) + ": ");
      printList(parts[i]);
    }
  }

}
