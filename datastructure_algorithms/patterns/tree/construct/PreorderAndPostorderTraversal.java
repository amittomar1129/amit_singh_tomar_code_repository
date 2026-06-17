package datastructure_algorithms.patterns.tree.construct;

//  Return any binary tree that matches the given preorder and postorder traversals. Values in the traversals
//  pre and post are distinct positive integers.
//
//  Example 1:
//  Input: pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
//  Output: [1,2,3,4,5,6,7]
//  Explanation: One possible answer is [1,2,3,4,5,6,7].
//
//  Example 2:
//  Input: pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
//  Output: [1,2,3,4,5,6,7]
//  Explanation: The answer [1,2,3,4,5,6,7] is the same as the first example, but rearranged.
//
//  Example 3:
//  Input: pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
//  Output: [1,2,3,4,5,6,7]
//  Explanation: The answer [1,2,3,4,5,6,7] is the same as the first example, but rearranged.

//  Solution: With preorder + postorder only, we cannot uniquely determine the tree in general.
//  But If a node has only one child, that child can be either left or right. We are allowed to choose any valid structure.
//  So we assume, If a node has one child, we attach it as the left child. This assumption is accepted by the problem.
//  Take current root from preorder[preIndex]
//  If this root == postorder[postIndex]
//      -> this is a leaf -> return
//  Otherwise:
//  Build left subtree
//  Then build right subtree
//  Finally, consume root in postorder

//  Time -> O(n)
//  Space -> O(n)

public class PreorderAndPostorderTraversal {

  class Node {

    int val;
    Node left, right;

    Node(int val) {
      this.val = val;
    }
  }

  // Global indices
  private int preIndex = 0;
  private int postIndex = 0;

  public Node constructFromPrePost(int[] preorder, int[] postorder) {
    return build(preorder, postorder);
  }

//    preorder =  {1, 2, 4, 5, 3, 6, 7};
//    postorder = {4, 5, 2, 6, 7, 3, 1};
  private Node build(int[] preorder, int[] postorder) {
    Node root = new Node(preorder[preIndex++]);
    // build left subtree if root not closed
    if (root.val != postorder[postIndex]) {
      root.left = build(preorder, postorder);
    }
    // build right subtree if root not closed
    if (root.val != postorder[postIndex]) {
      root.right = build(preorder, postorder);
    }
    // close root
    postIndex++;

    return root;
  }

  // -------- TRAVERSAL HELPERS FOR OUTPUT --------
  private static void printPreorder(Node root) {
    if (root == null) {
      return;
    }
    System.out.print(root.val + " ");
    printPreorder(root.left);
    printPreorder(root.right);
  }

  private static void printPostorder(Node root) {
    if (root == null) {
      return;
    }
    printPostorder(root.left);
    printPostorder(root.right);
    System.out.print(root.val + " ");
  }

  // -------- MAIN METHOD --------
  public static void main(String[] args) {

    PreorderAndPostorderTraversal sol = new PreorderAndPostorderTraversal();

    int[] preorder = {1, 2, 4, 5, 3, 6, 7};
    int[] postorder = {4, 5, 2, 6, 7, 3, 1};

    Node root = sol.constructFromPrePost(preorder, postorder);

    System.out.print("Preorder -> ");
    printPreorder(root);

    System.out.println();

    System.out.print("Postorder -> ");
    printPostorder(root);
  }

}
