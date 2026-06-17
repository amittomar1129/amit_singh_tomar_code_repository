package datastructure_algorithms.patterns.tree.construct;

//  Given inorder and postorder traversal of a tree, construct the binary tree.
//
//  Example 1:
//  Input: [9,3,15,20,7], [9,15,7,20,3]
//  Output: [3,9,20,null,null,15,7]
//  Explanation: Example 1
//
//  Example 2:
//  Input: [-1], [-1]
//  Output: [-1]
//  Explanation: Example 2
//
//  Example 3:
//  Input: [1,2], [2,1]
//  Output: [1,null,2]
//  Explanation: Example 3


//  Solution: “I use the last element of postorder as the root, split inorder into left and right parts,
//  and recursively build the right subtree first while decrementing a global postorder index.”
//  Time	O(n)
//  Space	O(n)

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class InorderAndPostorderTraversal {

  static class Node {

    int val;
    Node left, right;

    Node(int val) {
      this.val = val;
    }
  }

  // -------- GLOBAL VARIABLES --------
  private static int postIndex;
  private static Map<Integer, Integer> inorderMap;

//    inorder =   {9, 3, 15, 20, 7};
//    postorder = {9, 15, 7, 20, 3};
  public static Node buildTree(int[] inorder, int[] postorder) {
    postIndex = postorder.length - 1;
    inorderMap = new HashMap<>();
    for (int i = 0; i < inorder.length; i++) {
      inorderMap.put(inorder[i], i);
    }
    return build(postorder, 0, inorder.length - 1);
  }

//    Why build right subtree first?
//    Since we are moving backwards in postorder. After picking the root:
//    The next elements belong to the RIGHT subtree, NOT the left subtree. So we must consume the right subtree first.
//    What If We Build Left First? Accidentally assign right subtree nodes to the left side.
//    This aligns with reverse postorder traversal: Root -> Right -> Left
  private static Node build(int[] postorder, int inStart, int inEnd) {
    if (inStart > inEnd) {
      return null;
    }

    // Root from postorder
    int rootVal = postorder[postIndex--];
    Node root = new Node(rootVal);

    int inIndex = inorderMap.get(rootVal);

    // IMPORTANT: build right first
    root.right = build(postorder, inIndex + 1, inEnd);
    root.left = build(postorder, inStart, inIndex - 1);

    return root;
  }

  // ---------------- MAIN METHOD ----------------
  public static void main(String[] args) {

    int[] inorder = {9, 3, 15, 20, 7};
    int[] postorder = {9, 15, 7, 20, 3};

    Node root = buildTree(inorder, postorder);

    System.out.println(levelOrder(root));
  }

  // Helper to verify tree
  private static List<List<Integer>> levelOrder(Node root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) {
      return result;
    }

    Queue<Node> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      int size = queue.size();
      List<Integer> level = new ArrayList<>();

      for (int i = 0; i < size; i++) {
        Node node = queue.poll();
        level.add(node.val);
        if (node.left != null) {
          queue.offer(node.left);
        }
        if (node.right != null) {
          queue.offer(node.right);
        }
      }
      result.add(level);
    }
    return result;
  }
}




