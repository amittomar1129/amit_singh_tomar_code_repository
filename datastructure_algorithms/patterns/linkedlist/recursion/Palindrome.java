package datastructure_algorithms.patterns.linkedlist.recursion;

//  Given the head of a singly linked list, return true if it is a palindrome.
//
//  Example 1:
//  Input: head = [1,2,2,1]
//  Output: true
//  Explanation: The list is [1,2,2,1], which is a palindrome.
//
//  Example 2:
//  Input: head = [1,2]
//  Output: false
//  Explanation: The list is [1,2], which is not a palindrome.
//
//      Example 3:
//  Input: head = [1]
//  Output: true
//  Explanation: The list is [1], which is a palindrome.

//  Solution: We split the list into two halves, reverse the second half in-place, and compare both halves.
//  This achieves O(n) time and O(1) space.
//  Time -> O(n)
//  Space -> O(1)

import datastructure_algorithms.patterns.linkedlist.inplacereversal.Reverse;

public class Palindrome {

  static class ListNode {

    int val;
    ListNode next;

    ListNode(int val) {
      this.val = val;
    }
  }

  public static boolean isPalindrome(ListNode head) {
    if (head == null || head.next == null) {
      return true;
    }
    // Find middle
    ListNode slow = head;
    ListNode fast = head;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    // Reverse second half
    ListNode secondHalf = reverse(slow);
    ListNode firstHalf = head;
    // Compare halves
    while (secondHalf != null) {
      if (firstHalf.val != secondHalf.val) {
        return false;
      }
      firstHalf = firstHalf.next;
      secondHalf = secondHalf.next;
    }
    return true;
  }

  private static ListNode reverse(ListNode head) {
    ListNode prev = null;
    ListNode curr = head;

    while (curr != null) {
      ListNode next = curr.next; // save next
      curr.next = prev;          // reverse link
      prev = curr;               // move prev
      curr = next;               // move curr
    }

    return prev;
  }

  // ---------- MAIN ----------
  public static void main(String[] args) {
    // 1 -> 2 -> 3 -> 3 -> 2 -> 1
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    head.next.next.next = new ListNode(5);
    head.next.next.next.next = new ListNode(3);
    head.next.next.next.next.next = new ListNode(2);
    head.next.next.next.next.next.next = new ListNode(1);
    System.out.println(isPalindrome(head)); // true
  }
}
