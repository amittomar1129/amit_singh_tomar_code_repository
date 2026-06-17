package datastructure_algorithms.patterns.tree.zigzag;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//  Solution: “Yes, zigzag traversal can be implemented using DFS by tracking the level and inserting values
//  at either the beginning or end of each level’s list.”
//  Time	O(n)
//  Space	O(n)

public class ZigzagDFS {

  public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    dfs(root, 0, result);
    return result;
  }

//         3
//        / \
//       9  20
//          / \
//          15  7

  //
//  Zigzag Level Order Output:
//        [
//        [3],
//        [20, 9],
//        [15, 7]
//        ]
  private static void dfs(TreeNode node, int level, List<List<Integer>> result) {
    if (node == null) {
      return;
    }
    // Create list for this level if needed
    if (result.size() == level) {
      result.add(new LinkedList<>());
    }
    // Zigzag insertion
    if (level % 2 == 0) {
      result.get(level).add(node.val);          // left to right
    } else {
      result.get(level).add(0, node.val);       // right to left
    }

    dfs(node.left, level + 1, result);
    dfs(node.right, level + 1, result);
  }

  // ---------------- MAIN METHOD ----------------
  public static void main(String[] args) {

    TreeNode root = new TreeNode(3);
    root.left = new TreeNode(9);
    root.right = new TreeNode(20);
    root.right.left = new TreeNode(15);
    root.right.right = new TreeNode(7);

    System.out.println(zigzagLevelOrder(root));
  }
}

class TreeNode {
  int val;
  TreeNode left, right;

  TreeNode(int val) {
    this.val = val;
  }
}