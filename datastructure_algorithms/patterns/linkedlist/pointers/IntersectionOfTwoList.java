package datastructure_algorithms.patterns.linkedlist.pointers;

//  Write a program to find the node at which the intersection of two singly linked lists begins.
//
//  Example 1:
//  Input: listA = [4,1,8,4,5], listB = [5,0,1,8,4,5]
//  Output: ListNode(8)
//  Explanation: The intersected node is located at position 2 (0-indexed) in listA.
//
//  Example 2:
//  Input: listA = [0,9,1,2,4], listB = [3,2,4]
//  Output: ListNode(2)
//  Explanation: The intersected node is located at position 3 (0-indexed) in listA.
//
//  Example 3:
//  Input: listA = [2,6,4], listB = [1,5]
//  Output: None
//  Explanation: There is no intersection between the two lists.

//  Solutoin: I use two pointers traversing both lists. When one pointer reaches the end,
//  I redirect it to the other list’s head. This ensures both pointers traverse equal total lengths
//  and meet at the intersection point if it exists, otherwise both become null.
//  Time  -> O(n + m)
//  Space -> O(1)
public class IntersectionOfTwoList {

  static class ListNode {

    int val;
    ListNode next;

    ListNode(int val) {
      this.val = val;
    }
  }

  public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if (headA == null || headB == null) {
      return null;
    }

    ListNode p1 = headA;
    ListNode p2 = headB;

    while (p1 != p2) {
      p1 = (p1 == null) ? headB : p1.next;
      p2 = (p2 == null) ? headA : p2.next;
    }
    return p1;
  }

  public static void main(String[] args) {

    // Common part: 8 -> 4 -> 5
    ListNode common = new ListNode(1);
    common.next = new ListNode(8);
    common.next.next = new ListNode(4);
    common.next.next.next = new ListNode(5);

    // listA: 4 -> 1 -> 8 -> 4 -> 5
    ListNode headA = new ListNode(4);
    headA.next = common;

    // listB: 5 -> 0 -> 1 -> 8 -> 4 -> 5
    ListNode headB = new ListNode(5);
    headB.next = new ListNode(0);
    headB.next.next = common;

    ListNode intersection = getIntersectionNode(headA, headB);

    if (intersection != null) {
      System.out.println("Intersection value = " + intersection.val);
    } else {
      System.out.println("No intersection");
    }
  }
}
