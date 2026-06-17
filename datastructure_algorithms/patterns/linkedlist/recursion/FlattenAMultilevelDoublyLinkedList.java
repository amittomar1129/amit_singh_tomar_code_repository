package datastructure_algorithms.patterns.linkedlist.recursion;

//  You are given a doubly linked list which in addition to the next and previous pointers, it could have a child pointer,
//  which may or may not point to a separate doubly linked list. These child lists may have one or more children of their own,
//  and so on, to produce a multilevel data structure. Flatten the list so that all the nodes appear in a single-level,
//  doubly linked list. You are given the head of the first level of the list.
//
//  Example 1:
//  Input: head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
//  Output: [1,2,3,7,8,11,12,9,10,4,5,6]
//  Explanation: The multilevel linked list is represented in the input. After flattening, the nodes should be in the order shown in the output.
//
//  Example 2:
//  Input: head = [1,2,null,3]
//  Output: [1,3,2]
//  Explanation: There is no child linked list, so the input linked list is already in the single-level form.
//
//  Example 3:
//  Input: head = []
//  Output: []
//  Explanation: The input list is empty, so the output should also be empty.

//  Solution: Each node is processed once. When a child is found, we recursively flatten it and splice it between
//  the current node and its next node, maintaining doubly linked list invariants.
//  This is essentially DFS on a linked structure. We flatten child lists first and splice them
//  into the main list while maintaining all prev/next invariants in-place.

//  Time -> O(n) (each node visited once)
//  Space -> O(depth) recursion stack

public class FlattenAMultilevelDoublyLinkedList {

  static class Node {

    int val;
    Node prev;
    Node next;
    Node child;

    Node(int val) {
      this.val = val;
    }
  }

  // ---------- FLATTEN ----------
  public static Node flatten(Node head) {
    dfs(head);
    return head;
  }

  /*
           Multilevel List:

           1 - 2 - 3 - 4
                   |
                   7 - 8
                       |
                       11 - 12
       */

  // Returns tail of flattened list
  private static Node dfs(Node node) {
    Node curr = node;
    Node last = null;
    while (curr != null) {
      Node next = curr.next;
      if (curr.child != null) {
        Node childHead = curr.child;
        Node childTail = dfs(childHead);
        // Attach child
        curr.next = childHead;
        childHead.prev = curr;
        // Attach tail to next
        if (next != null) {
          childTail.next = next;
          next.prev = childTail;
        }
        curr.child = null;
        last = childTail;
      } else {
        last = curr;
      }
      curr = next;
    }
    return last;
  }

  // ---------- PRINT ----------
  private static void printList(Node head) {
    Node curr = head;
    while (curr != null) {
      System.out.print(curr.val);
      if (curr.next != null) {
        System.out.print(" -> ");
      }
      curr = curr.next;
    }
    System.out.println();
  }

  // ---------- MAIN ----------
  public static void main(String[] args) {

        /*
            Multilevel List:

            1 - 2 - 3 - 4
                    |
                    7 - 8
                        |
                        11 - 12
        */

    Node n1 = new Node(1);
    Node n2 = new Node(2);
    Node n3 = new Node(3);
    Node n4 = new Node(4);

    n1.next = n2;
    n2.prev = n1;
    n2.next = n3;
    n3.prev = n2;
    n3.next = n4;
    n4.prev = n3;

    Node n7 = new Node(7);
    Node n8 = new Node(8);
    n7.next = n8;
    n8.prev = n7;

    Node n11 = new Node(11);
    Node n12 = new Node(12);
    n11.next = n12;
    n12.prev = n11;

    n3.child = n7;
    n8.child = n11;

    System.out.println("Before Flatten:");
    printList(n1);

    Node result = flatten(n1);

    System.out.println("After Flatten:");
    printList(result);
  }
}
