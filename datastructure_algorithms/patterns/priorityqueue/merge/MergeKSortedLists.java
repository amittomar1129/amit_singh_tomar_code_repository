package datastructure_algorithms.patterns.priorityqueue.merge;

//  You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
//  Merge all the linked-lists into one sorted linked-list and return it.
//
//  Example 1:
//  Input: [[1,4,5],[1,3,4],[2,6]]
//  Output: [1,1,2,3,4,4,5,6]
//  Explanation: The linked-lists are [1->4->5, 1->3->4, 2->6], and the merged sorted linked-list is 1->1->2->3->4->4->5->6.

//  Solution: I use a min heap containing the current head of each list.
//  Each extraction gives the next smallest element, and I push its successor.
//  This gives O(n log k) time, which is optimal.
//  Time	O(n log k)
//  Space	O(k)

import java.util.PriorityQueue;

public class MergeKSortedLists {

  static class ListNode {

    int val;
    ListNode next;

    ListNode(int val) {
      this.val = val;
    }
  }

  public static ListNode mergeKLists(ListNode[] lists) {

    PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

    // Add initial heads
    for (ListNode node : lists) {
      if (node != null) {
        minHeap.offer(node);
      }
    }

    ListNode dummy = new ListNode(-1);
    ListNode curr = dummy;

    while (!minHeap.isEmpty()) {
      ListNode node = minHeap.poll();
      curr.next = node;
      curr = curr.next;

      if (node.next != null) {
        minHeap.offer(node.next);
      }
    }

    return dummy.next;
  }

  // main method with output
  public static void main(String[] args) {

        /*
            List1: 1 -> 4 -> 5
            List2: 1 -> 3 -> 4
            List3: 2 -> 6
        */

    ListNode l1 = new ListNode(1);
    l1.next = new ListNode(4);
    l1.next.next = new ListNode(5);

    ListNode l2 = new ListNode(1);
    l2.next = new ListNode(3);
    l2.next.next = new ListNode(4);

    ListNode l3 = new ListNode(2);
    l3.next = new ListNode(6);

    ListNode[] lists = {l1, l2, l3};

    ListNode result = mergeKLists(lists);

    System.out.print("Merged List -> ");
    while (result != null) {
      System.out.print(result.val + " -> ");
      result = result.next;
    }
    System.out.println("null");
  }
}
