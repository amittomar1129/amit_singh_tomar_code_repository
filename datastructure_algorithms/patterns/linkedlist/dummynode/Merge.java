package datastructure_algorithms.patterns.linkedlist.dummynode;

//  Merge two sorted linked lists and return it as a new sorted list. The new list should be made by
//  splicing together the nodes of the first two lists.
//
//  Example 1:
//  Input: [1,2,4], [1,3,4]
//  Output: [1,1,2,3,4,4]
//  Explanation: Example 1: Input: l1 = 1->2->4, l2 = 1->3->4. Output: 1->1->2->3->4->4
//
//  Example 2:
//  Input: [], []
//  Output: []
//  Explanation: Example 2: Input: l1 = [], l2 = []. Output: []
//
//  Example 3:
//  Input: [], [0]
//  Output: [0]
//  Explanation: Example 3: Input: l1 = [], l2 = 0. Output: 0

//  Time -> O(n + m)
//  Space -> O(1) (no new nodes created)

import java.util.PriorityQueue;

public class Merge {

  static class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
      this.val = val;
    }
  }

  public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    // Dummy node to simplify edge cases
    ListNode dummy = new ListNode(-1);
    ListNode current = dummy;

    while (l1 != null && l2 != null) {
      if (l1.val <= l2.val) {
        current.next = l1;
        l1 = l1.next;
      } else {
        current.next = l2;
        l2 = l2.next;
      }
      current = current.next;
    }

    // Attach remaining nodes
    if (l1 != null) {
      current.next = l1;
    } else {
      current.next = l2;
    }

    return dummy.next;
  }

  //  O(N log k)
  //  O(k)
  public static ListNode mergeKListsUsingHeap(ListNode[] lists) {
    PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));

    for (ListNode node : lists) {
      if (node != null) pq.offer(node);
    }

    ListNode dummy = new ListNode(-1);
    ListNode curr = dummy;

    while (!pq.isEmpty()) {
      ListNode temp = pq.poll();
      curr.next = temp;
      curr = curr.next;

      if (temp.next != null) {
        pq.offer(temp.next);
      }
    }

    return dummy.next;
  }

  //  O(N log k)
  //  O(log k)
  public static ListNode mergeKListsUsingDivideAndConquer(ListNode[] lists) {
    if (lists == null || lists.length == 0) return null;
    return mergeRange(lists, 0, lists.length - 1);
  }

  private static ListNode mergeRange(ListNode[] lists, int left, int right) {
    if (left == right) {
      return lists[left];
    }

    int mid = left + (right - left) / 2;

    ListNode l1 = mergeRange(lists, left, mid);
    ListNode l2 = mergeRange(lists, mid + 1, right);

    return mergeTwoList(l1, l2);
  }

  private static ListNode mergeTwoList(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(-1);
    ListNode curr = dummy;

    while (l1 != null && l2 != null) {
      if (l1.val <= l2.val) {
        curr.next = l1;
        l1 = l1.next;
      } else {
        curr.next = l2;
        l2 = l2.next;
      }
      curr = curr.next;
    }

    curr.next = (l1 != null) ? l1 : l2;
    return dummy.next;
  }

  public static void main(String[] args) {
    // list1 = 1 -> 2 -> 4
    ListNode l1 = new ListNode(1);
    l1.next = new ListNode(2);
    l1.next.next = new ListNode(4);

    // list2 = 1 -> 3 -> 4
    ListNode l2 = new ListNode(1);
    l2.next = new ListNode(3);
    l2.next.next = new ListNode(4);

    ListNode result = mergeTwoLists(l1, l2);

    while (result != null) {
      System.out.print(result.val + " -> ");
      result = result.next;
    }
    System.out.println("null");

    // list1 = 1 -> 2 -> 4
    ListNode l3 = new ListNode(1);
    l3.next = new ListNode(2);
    l3.next.next = new ListNode(4);

    // list2 = 1 -> 3 -> 4
    ListNode l4 = new ListNode(1);
    l4.next = new ListNode(3);
    l4.next.next = new ListNode(4);

    ListNode result1 = mergeKListsUsingHeap(new ListNode[] {l3, l4});

    while (result1 != null) {
      System.out.print(result1.val);
      if (result1.next != null) System.out.print(" -> ");
      result1 = result1.next;
    }
    System.out.println("null");

    // list1: 1 -> 2 -> 4
    ListNode l5 = new ListNode(1);
    l5.next = new ListNode(2);
    l5.next.next = new ListNode(4);

    // list2: 1 -> 3 -> 4
    ListNode l6 = new ListNode(1);
    l6.next = new ListNode(3);
    l6.next.next = new ListNode(4);

    ListNode result3 = mergeKListsUsingDivideAndConquer(new ListNode[] {l5, l6});
    while (result3 != null) {
      System.out.print(result3.val);
      if (result3.next != null) System.out.print(" -> ");
      result3 = result3.next;
    }
    System.out.println("null");
  }
}
