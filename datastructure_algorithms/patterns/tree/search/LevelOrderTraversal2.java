package datastructure_algorithms.patterns.tree.search;

//  Given a binary tree, return the bottom-up level order traversal of its nodes' values.
//  (i.e., from left to right, level by level from leaf to root).

//  We can do this by DFS + track depth
//  insert accordingly
//  But BFS is cleaner and preferred

//  Time -> O(n)
//  Space -> O(n) (queue + result)

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal2 {

  static class TreeNode {

    int val;
    TreeNode left, right;

    TreeNode(int val) {
      this.val = val;
    }
  }

  public List<List<Integer>> levelOrderBottom(TreeNode root) {
    List<List<Integer>> result = new LinkedList<>();
    if (root == null) {
      return result;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      List<Integer> output = new ArrayList<>();
      for (int i = 0; i < queue.size(); i++) {
        TreeNode node = queue.poll();
        output.add(node.val);

        if (node.left != null) {
          queue.offer(node.left);
        }
        if (node.right != null) {
          queue.offer(node.right);
        }
      }
      // insert at front
      result.add(0, output);
    }
    return result;
  }

  public List<List<Integer>> levelOrderBottomByDfs(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    dfs(root, 0, result);
    Collections.reverse(result);
    return result;
  }

  private void dfs(TreeNode node, int level, List<List<Integer>> result) {
    if (node == null) {
      return;
    }
    if (result.size() == level) {
      result.add(new ArrayList<>());
    }

    result.get(level).add(node.val);

    dfs(node.left, level + 1, result);
    dfs(node.right, level + 1, result);
  }

  // -------- MAIN METHOD --------
  public static void main(String[] args) {

    LevelOrderTraversal2 sol = new LevelOrderTraversal2();

        /*
              3
            /   \
           9     20
                / \
               15  7
        */

    TreeNode root = new TreeNode(3);
    root.left = new TreeNode(9);
    root.right = new TreeNode(20);
    root.right.left = new TreeNode(15);
    root.right.right = new TreeNode(7);

    List<List<Integer>> result = sol.levelOrderBottom(root);

    System.out.println("Bottom-Up Level Order -> " + result);

    List<List<Integer>> result1 = sol.levelOrderBottomByDfs(root);

    System.out.println("Bottom-Up Level Order -> " + result1);
  }
}
