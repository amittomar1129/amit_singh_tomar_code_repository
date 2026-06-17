package datastructure_algorithms.patterns.tree.construct;

//  Given preorder and inorder traversal of a tree, construct the binary tree. You may assume that duplicates do not exist in the tree.
//
//  Example 1:
//  Input: [3,9,20,15,7], [9,3,15,20,7]
//  Output: [3,9,20,null,null,15,7]
//  Explanation: Example 1: The preorder and inorder traversals for the binary tree are [3,9,20,15,7] and [9,3,15,20,7] respectively.
//
//      Example 2:
//  Input: [-1], [-1]
//  Output: [-1]
//  Explanation: Example 2: The preorder and inorder traversals for the binary tree are [-1] and [-1] respectively.
//
//      Example 3:
//  Input: [1,2,3], [3,2,1]
//  Output: [1,null,2,null,3]
//  Explanation: Example 3: The preorder and inorder traversals for the binary tree are [1,2,3] and [3,2,1] respectively.

//  Solution: “Preorder gives me the root, inorder lets me split left and right subtrees.
//  Using a hashmap for inorder indices, I can construct the tree recursively in linear time.”
//  Time	O(n)
//  Space	O(n)

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class PreorderAndInorderTraversal {

  static class Node {

    int val;
    Node left, right;

    Node(int val) {
      this.val = val;
    }
  }

  private static int preIndex = 0;
  private static Map<Integer, Integer> inorderMap;

  //    preorder = {3, 9, 20, 15, 7};
  //    inorder =  {9, 3, 15, 20, 7};
  public static Node buildTree(int[] preorder, int[] inorder) {
    inorderMap = new HashMap<>();
    for (int i = 0; i < inorder.length; i++) {
      inorderMap.put(inorder[i], i);
    }
    return build(preorder, 0, inorder.length - 1);
  }

  private static Node build(int[] preorder, int inStart, int inEnd) {
    if (inStart > inEnd) {
      return null;
    }

    int rootVal = preorder[preIndex++];
    Node root = new Node(rootVal);

    int inIndex = inorderMap.get(rootVal);

    root.left = build(preorder, inStart, inIndex - 1);
    root.right = build(preorder, inIndex + 1, inEnd);

    return root;
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
      List<Integer> output = new ArrayList<>();
      for (int i = 0; i < queue.size(); i++) {
        Node node = queue.poll();
        output.add(node.val);

        if (node.left != null) {
          queue.offer(node.left);
        }
        if (node.right != null) {
          queue.offer(node.right);
        }
      }
      result.add(output);
    }
    return result;
  }

  // ---------------- MAIN METHOD ----------------
  public static void main(String[] args) {

    int[] preorder = {3, 9, 20, 15, 7};
    int[] inorder = {9, 3, 15, 20, 7};

    Node root = buildTree(preorder, inorder);

    // Print level order to verify
    System.out.println(levelOrder(root));
  }
}
