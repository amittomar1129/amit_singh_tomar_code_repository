package datastructure_algorithms.patterns.tree.search;

//  Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree. According to the definition
//  of LCA on Wikipedia: 'The lowest common ancestor is defined between two nodes p and q as the lowest node in T that
//  has both p and q as descendants (where we allow a node to be a descendant of itself).'

//  Given a binary tree (not BST) and two nodes p and q, find the lowest node in the tree that has both p and q in its subtree.
//  Important:
//  A node can be a descendant of itself
//  Tree is not necessarily ordered

//  At any node:
//  If both p and q are found in different subtrees
//  ? this node is the LCA
//  If current node is p or q
//  ? it can be the LCA

//  Solution: “I do a postorder DFS. If a node gets non-null from both left and right, it’s the LCA.”
//  Time -> O(n)
//  Space -> O(h)
//        worst case skewed tree -> O(n)
//        balanced tree -> O(log n)

public class LowestCommonAncestor {


  static class Node {

    int val;
    Node left, right;

    Node(int val) {
      this.val = val;
    }
  }

  public Node dfs(Node root, Node p, Node q) {
    if (root == null) {
      return null;
    }
    if (root == p || root == q) {
      return root;
    }

    Node left = dfs(root.left, p, q);
    Node right = dfs(root.right, p, q);

    if (left != null && right != null) {
      return root; // p in one side, q in other
    }
    return left != null ? left : right;
  }

  // -------- MAIN METHOD --------
  public static void main(String[] args) {

    LowestCommonAncestor sol = new LowestCommonAncestor();

        /*
              3
            /   \
           5     1
          / \   / \
         6   2 0   8
            / \
           7   4
        */

    Node root = new Node(3);
    root.left = new Node(5);
    root.right = new Node(1);
    root.left.left = new Node(6);
    root.left.right = new Node(2);
    root.right.left = new Node(0);
    root.right.right = new Node(8);
    root.left.right.left = new Node(7);
    root.left.right.right = new Node(4);

    Node p = root.left;              // 5
    Node q = root.left.right.right; // 4

    Node lca = sol.dfs(root, p, q);

    System.out.println("LCA of " + p.val + " and " + q.val + " -> " + lca.val);
  }
}
