package datastructure_algorithms.patterns.linkedlist.pointers;

//  Given head, the head of a linked list, determine if the linked list has a cycle in it. There is a cycle
//  in a linked list if there is some node in the list that can be reached again by continuously following the next pointer.
//  Return true if there is a cycle in the linked list. Otherwise, return false.
//
//  Example 1:
//  Input: head = [3,2,0,-4], pos = 1
//  Output: true
//  Explanation: There is a cycle in the linked list, where the tail connects to the second node.
//
//      Example 2:
//  Input: head = [1,2], pos = 0
//  Output: true
//  Explanation: There is a cycle in the linked list, where the tail connects to the first node.
//
//      Example 3:
//  Input: head = [1], pos = -1
//  Output: false
//  Explanation: There is no cycle in the linked list.

//  Solution: I use Floyd’s cycle detection. Two pointers move at different speeds. If there’s a cycle,
//  they must meet. If not, fast reaches null.
//  Time -> O(n)
//  Space -> O(1)

public class Cycle {

  static class ListNode {

    int val;
    ListNode next;

    ListNode(int val) {
      this.val = val;
    }
  }


  public static boolean hasCycle(ListNode head) {
    if (head == null || head.next == null) {
      return false;
    }
    ListNode slow = head;
    ListNode fast = head;
    while (fast != null && fast.next != null) {
      slow = slow.next;          // 1 step
      fast = fast.next.next;     // 2 steps

      if (slow == fast) {
        return true;           // cycle detected
      }
    }
    return false;                  // no cycle
  }

  //  Time -> O(n)
//  Space -> O(1)
  //  1 -> 2 -> 3 -> 4 -> 2
  public static int cycleLength(ListNode head) {
    if (head == null || head.next == null) {
      return 0;
    }
    ListNode slow = head;
    ListNode fast = head;
    // Detect cycle
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;

      if (slow == fast) {
        int length = 1;
        fast = fast.next;

        while (fast != slow) {
          fast = fast.next;
          length++;
        }
        return length;
      }
    }
    return 0; // no cycle
  }

  //  Time -> O(n)
//  Space -> O(1)
  //  1 -> 2 -> 3 -> 4 -> 2
  public static ListNode findStartOfCycle(ListNode head) {
    if (head == null || head.next == null) {
      return null;
    }
    ListNode slow = head;
    ListNode fast = head;
    // Phase 1: detect cycle
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;

      if (slow == fast) {
        break;
      }
    }
    if (fast == null || fast.next == null) {
      return null; // no cycle
    }
    // Phase 2: find cycle start
    slow = head;
    while (slow != fast) {
      slow = slow.next;
      fast = fast.next;
    }
    return slow; // cycle start
  }

  public static void main(String[] args) {
    // Create cycle example: 1 -> 2 -> 3 -> 4 -> 2
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = head.next; // cycle

    System.out.println(hasCycle(head)); // true
    System.out.println(cycleLength(head));
    System.out.println(findStartOfCycle(head).val);
  }

}
