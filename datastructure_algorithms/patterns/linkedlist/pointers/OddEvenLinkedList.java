package datastructure_algorithms.patterns.linkedlist.pointers;


//  Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are
//  talking about the node number and not the value in the nodes. You should try to do it in place.
//  The program should run in O(1) space complexity and O(nodes) time complexity.
//
//  Example 1:
//  Input: 1->2->3->4->5
//  Output: 1->3->5->2->4
//  Explanation: The input linked list has 1->2->3->4->5 as nodes. The odd nodes are 1->3->5 and the even nodes are 2->4. The output should preserve the original order of odd and even nodes.
//
//  Example 2:
//  Input: 2->1->3->5->6->4->7
//  Output: 2->3->6->7->1->5->4
//  Explanation: The input linked list has 2->1->3->5->6->4->7 as nodes. The odd nodes are 2->3->6->7 and the even nodes are 1->5->4. The output should preserve the original order of odd and even nodes.
//
//  Example 3:
//  Input: 1->2->3
//  Output: 1->3->2
//  Explanation: The input linked list has 1->2->3 as nodes. The odd nodes are 1->3 and the even nodes are 2. The output should preserve the original order of odd and even nodes.

//  Solution: I maintain two chains for odd and even positions, rearrange pointers in one pass, and connect them
//  at the end using O(1) space.
//  Time -> O(n)
//  Space -> O(1)

public class OddEvenLinkedList {

  static class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
      this.val = val;
      this.next = null;
    }
  }

  // 1 -> 2 -> 3 -> 4 -> 5
  public static ListNode oddEvenList(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode odd = head;
    ListNode even = head.next;
    ListNode evenHead = even;
    while (even != null && even.next != null) {
      odd.next = even.next;
      odd = odd.next;

      even.next = odd.next;
      even = even.next;
    }
    odd.next = evenHead;
    return head;
  }

  public static void main(String[] args) {
    // 1 -> 2 -> 3 -> 4 -> 5
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(5);

    head = oddEvenList(head);

    // Print result
    ListNode curr = head;
    while (curr != null) {
      System.out.print(curr.val + " -> ");
      curr = curr.next;
    }
    System.out.println("null");
  }
}
