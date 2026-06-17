package datastructure_algorithms.patterns.tree.zigzag;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//  Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).

//  Zigzag traversal:
//  Level 0 Direction: L -> R
//  Level 1 Direction: R -> L
//  Level 2 Direction: L -> R

//         3
//        / \
//        9  20
  //        / \
  //        15  7

//
//  Zigzag Level Order Output:
//        [
//        [3],
//        [20, 9],
//        [15, 7]
//        ]

//  Solution: “I perform a BFS using a queue and alternate insertion direction at each level using a boolean flag and a deque.”
//  Time	O(n)
//  Space	O(n)

public class ZigzagLevelOrderTraversal {

  public static List<List<Integer>> zigzagLevelOrder(Node root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) {
      return result;
    }
    Queue<Node> queue = new LinkedList<>();
    queue.offer(root);

    boolean leftToRight = true;
    while (!queue.isEmpty()) {
      LinkedList<Integer> output = new LinkedList<>();
      for (int i = 0; i < queue.size(); i++) {
        Node node = queue.poll();
        if (leftToRight) {
          output.addLast(node.val);
        } else {
          output.addFirst(node.val);
        }
        if (node.left != null) {
          queue.offer(node.left);
        }
        if (node.right != null) {
          queue.offer(node.right);
        }
      }
      result.add(output);
      leftToRight = !leftToRight;
    }
    return result;
  }

  // ---------------- MAIN METHOD ----------------
  public static void main(String[] args) {
    Node root = new Node(3);
    root.left = new Node(9);
    root.right = new Node(20);
    root.right.left = new Node(15);
    root.right.right = new Node(7);
    System.out.println(zigzagLevelOrder(root));
  }
}

class Node {

  int val;
  Node left, right;

  Node(int val) {
    this.val = val;
  }
}