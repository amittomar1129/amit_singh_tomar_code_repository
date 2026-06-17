package datastructure_algorithms.patterns.linkedlist.dummynode;

//  You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order,
//  and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
//  You may assume the two numbers do not contain any leading zero, except the number 0 itself.
//
//  Example 1:
//  Input: [2,4,3], [5,6,4]
//  Output: [7,0,8]
//  Explanation: 342 + 465 = 807


//  Solution: I iterate through both lists, add digits with carry just like manual addition, and build the result list
//  using a dummy head.
//  Time -> O(max(m, n))
//  Space -> O(max(m, n)) (output list)

public class AddTwoNumbers {

  static class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
      this.val = val;
    }
  }

  public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);
    ListNode curr = dummy;

    int carry = 0;

    while (l1 != null || l2 != null || carry != 0) {
      int x = (l1 != null) ? l1.val : 0;
      int y = (l2 != null) ? l2.val : 0;

      int sum = x + y + carry;
      carry = sum / 10;

      curr.next = new ListNode(sum % 10);
      curr = curr.next;

      if (l1 != null) l1 = l1.next;
      if (l2 != null) l2 = l2.next;
    }

    return dummy.next;
  }

  public static void main(String[] args) {
    // l1 = 2 -> 4 -> 3
    ListNode l1 = new ListNode(2);
    l1.next = new ListNode(4);
    l1.next.next = new ListNode(3);

    // l2 = 5 -> 6 -> 4
    ListNode l2 = new ListNode(5);
    l2.next = new ListNode(6);
    l2.next.next = new ListNode(4);

    ListNode result = addTwoNumbers(l1, l2);

    // Print result
    while (result != null) {
      System.out.print(result.val + " -> ");
      result = result.next;
    }
    System.out.println("null");
  }

}
