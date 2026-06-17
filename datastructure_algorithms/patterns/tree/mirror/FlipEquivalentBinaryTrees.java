package datastructure_algorithms.patterns.tree.mirror;

//  For a binary tree T, we can define a flip operation as follows: choose any node, and swap the left and right
//  child subtrees. A binary tree X is flip equivalent to a binary tree Y if and only if we can make X equal to Y
//  after some number of flip operations. Write a function to determine if two binary trees are flip equivalent.
//  The trees are given by root nodes root1 and root2.

//  You are given two binary trees root1 and root2.
//  You can perform flip operations on any node.
//  Flip -> swap left and right children.
//  Two trees are flip equivalent if one can be transformed into the other using any number of flips
//  Return true or false.

//
//  Example 1:
//  Input: [1,2,3,4,5,6,null,null,null,7,8]
//  Output: true
//  Explanation: We can flip the children of nodes 1, 3, and 6.

//  Solution: “Two trees are flip equivalent if at every node their values match and either their children
//  match directly or match after swapping left and right.”
//  Time -> O(n) Every node is compared once
//  Space -> O(h) recursion stack
//      Worst -> O(n)
//      Balanced -> O(log n)


public class FlipEquivalentBinaryTrees {

  static class TreeNode {

    int val;
    TreeNode left, right;

    TreeNode(int val) {
      this.val = val;
    }
  }

  public boolean flipEquiv(TreeNode root1, TreeNode root2) {
    // both null -> equivalent
    if (root1 == null && root2 == null) {
      return true;
    }
    // one null -> not equivalent
    if (root1 == null || root2 == null) {
      return false;
    }
    // values differ -> not equivalent
    if (root1.val != root2.val) {
      return false;
    }

    // no flip case
    boolean noFlip = flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right);
    // flip case
    boolean flip = flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left);

    return noFlip || flip;
  }

  // -------- MAIN METHOD --------
  public static void main(String[] args) {

    FlipEquivalentBinaryTrees sol = new FlipEquivalentBinaryTrees();

        /*
            Tree 1 ->
                  1
                /   \
               2     3
              / \
             4   5
                / \
               7   8
        */

    TreeNode root1 = new TreeNode(1);
    root1.left = new TreeNode(2);
    root1.right = new TreeNode(3);
    root1.left.left = new TreeNode(4);
    root1.left.right = new TreeNode(5);
    root1.left.right.left = new TreeNode(7);
    root1.left.right.right = new TreeNode(8);

        /*
            Tree 2 ->
                  1
                /   \
               3     2
                    / \
                   4   5
                      / \
                     8   7
        */

    TreeNode root2 = new TreeNode(1);
    root2.left = new TreeNode(3);
    root2.right = new TreeNode(2);
    root2.right.left = new TreeNode(4);
    root2.right.right = new TreeNode(5);
    root2.right.right.left = new TreeNode(8);
    root2.right.right.right = new TreeNode(7);

    System.out.println("Are Trees Flip Equivalent -> " + sol.flipEquiv(root1, root2));
  }
}
