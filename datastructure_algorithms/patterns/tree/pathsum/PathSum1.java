package datastructure_algorithms.patterns.tree.pathsum;

//  Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
//  Return a list of all such paths.
//  Input -> root of a binary tree, integer sum.
//  Output -> list of all root->leaf paths where sum of nodes equals target sum.

//  Example 1:
//  Input: [5,4,8,11,null,13,4,7,2,null,null,5,1], 22
//  Output: [[5,4,11,2],[5,8,4,5]]
//  Explanation: The path 5 -> 4 -> 11 -> 2 sum to 22. The path 5 -> 8 -> 4 -> 5 sum to 22.

//  Solution: “I perform a DFS keeping track of the current path and remaining sum, and backtrack
//  after visiting each node to explore all root-to-leaf paths.”
//  Time -> O(n * h)
//      n nodes, each leaf path copied to result (max length h)
//  Space -> O(h) recursion stack + O(h) path list
//      Worst-case -> skewed tree O(n)

import java.util.ArrayList;
import java.util.List;

public class PathSum1 {

  static class TreeNode {

    int val;
    TreeNode left, right;

    TreeNode(int val) {
      this.val = val;
    }
  }

  public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
    List<List<Integer>> result = new ArrayList<>();
    dfs(root, targetSum, new ArrayList<>(), result);
    return result;
  }

  private void dfs(TreeNode node, int sum, List<Integer> path, List<List<Integer>> result) {
    if (node == null) {
      return;
    }
    path.add(node.val);
    sum -= node.val;

    // leaf node
    if (node.left == null && node.right == null && sum == 0) {
      result.add(new ArrayList<>(path));
    }

    dfs(node.left, sum, path, result);
    dfs(node.right, sum, path, result);

    // backtrack
    path.remove(path.size() - 1);
  }

  // -------- MAIN METHOD --------
  public static void main(String[] args) {
    PathSum1 sol = new PathSum1();

        /*
            Tree ->
                  5
                /   \
               4     8
              /     / \
             11    13  4
            /  \       / \
           7    2     5   1
        */

    TreeNode root = new TreeNode(5);
    root.left = new TreeNode(4);
    root.right = new TreeNode(8);
    root.left.left = new TreeNode(11);
    root.left.left.left = new TreeNode(7);
    root.left.left.right = new TreeNode(2);
    root.right.left = new TreeNode(13);
    root.right.right = new TreeNode(4);
    root.right.right.left = new TreeNode(5);
    root.right.right.right = new TreeNode(1);

    int targetSum = 22;

    List<List<Integer>> paths = sol.pathSum(root, targetSum);

    System.out.println("Paths with sum " + targetSum + " ->");
    for (List<Integer> path : paths) {
      System.out.println(path);
    }
  }
}
