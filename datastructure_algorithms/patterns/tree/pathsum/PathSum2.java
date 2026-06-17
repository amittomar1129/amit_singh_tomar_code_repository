package datastructure_algorithms.patterns.tree.pathsum;

//  You are given a binary tree in which each node contains an integer value. Find the number of paths that sum
//  to a given value. The path does not need to start or end at the root or a leaf, but it must go
//  downwards (traveling only from parent nodes to child nodes).

//  Given a binary tree with integer values
//  Find the number of downward paths whose sum equals targetSum
//  Path rules: Can start and end at any node.
//  must go parent -> child
//  does not need to include root or leaf.

//  Solution: “I use a prefix sum HashMap during DFS to count how many previous paths can form the required sum
//  ending at the current node, achieving O(n) time.”
//  Time -> O(n) (each node visited once)
//  Space -> O(n) (hashmap + recursion stack)

import java.util.HashMap;
import java.util.Map;

public class PathSum2 {

  static class TreeNode {

    int val;
    TreeNode left, right;

    TreeNode(int val) {
      this.val = val;
    }
  }

  public int pathSum(TreeNode root, int targetSum) {
    Map<Integer, Integer> prefixMap = new HashMap<>();
    prefixMap.put(0, 1); // base case
    return dfs(root, 0, targetSum, prefixMap);
  }

  private int dfs(TreeNode node, int currSum, int target, Map<Integer, Integer> prefixMap) {
    if (node == null) {
      return 0;
    }
    currSum = currSum + node.val;
    int path = prefixMap.getOrDefault(currSum - target, 0);
    prefixMap.put(currSum, prefixMap.getOrDefault(currSum, 0) + 1);

    path += dfs(node.left, currSum, target, prefixMap);
    path += dfs(node.right, currSum, target, prefixMap);

    // backtrack
    prefixMap.put(currSum, prefixMap.get(currSum) - 1);

    return path;
  }

  // -------- MAIN METHOD --------
  public static void main(String[] args) {

    PathSum2 sol = new PathSum2();

        /*
            Tree ->
                  10
                /    \
               5     -3
              / \      \
             3   2      11
            / \   \
           3  -2   1
        */

    TreeNode root = new TreeNode(10);
    root.left = new TreeNode(5);
    root.right = new TreeNode(-3);
    root.left.left = new TreeNode(3);
    root.left.right = new TreeNode(2);
    root.right.right = new TreeNode(11);
    root.left.left.left = new TreeNode(3);
    root.left.left.right = new TreeNode(-2);
    root.left.right.right = new TreeNode(1);

    int targetSum = 8;

    System.out.println(
        "Number of Paths with Sum " + targetSum + " -> " + sol.pathSum(root, targetSum));
  }
}
