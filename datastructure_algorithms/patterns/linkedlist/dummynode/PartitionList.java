package datastructure_algorithms.patterns.linkedlist.dummynode;

//  Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
//  You should preserve the original relative order of the nodes in each of the two partitions.
//
//  Example 1:
//  Input: head = 1->4->3->2->5->2, x = 3
//  Output: 1->2->2->4->3->5
//  Explanation: We want to keep the nodes in their original relative order. So 1->2->2 are before 3->4->5 where 3 is the pivot.
//
//      Example 2:
//  Input: head = 2->1, x = 2
//  Output: 1->2
//  Explanation: In this case, the pivot is 2. So 1 comes before 2.
//
//  Example 3:
//  Input: head = 1->2->3, x = 4
//  Output: 1->2->3
//  Explanation: Since the pivot is greater than all nodes, we keep the original order.

//  Solution: I maintain two stable lists using dummy heads—one for nodes less than x and one for
//  nodes greater or equal—then connect them.
//  Time: O(n)
//  Space: O(1) (only pointers, no extra nodes)

public class PartitionList {

  static class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
      this.val = val;
    }
  }

  public static ListNode partition(ListNode head, int x) {
    ListNode beforeHead = new ListNode(0);
    ListNode afterHead = new ListNode(0);

    ListNode before = beforeHead;
    ListNode after = afterHead;

    while (head != null) {
      if (head.val < x) {
        before.next = head;
        before = before.next;
      } else {
        after.next = head;
        after = after.next;
      }
      head = head.next;
    }

    // Important: avoid cycle
    after.next = null;

    // Connect two lists
    before.next = afterHead.next;

    return beforeHead.next;
  }

  public static void main(String[] args) {
    // Input: 1 -> 4 -> 3 -> 2 -> 5 -> 2
    ListNode head = new ListNode(1);
    head.next = new ListNode(4);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(2);
    head.next.next.next.next = new ListNode(5);
    head.next.next.next.next.next = new ListNode(2);

    int x = 3;
    ListNode result = partition(head, x);

    while (result != null) {
      System.out.print(result.val + " -> ");
      result = result.next;
    }
    System.out.println("null");
  }

}
