package datastructure_algorithms.patterns.tree.pathsum;

//  Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
//  Find the total sum of all root-to-leaf numbers.
//  Each root-to-leaf path in a binary tree represents a number formed by concatenating node values.
//  Input -> root of binary tree.
//  Output -> sum of all root-to-leaf numbers.

//  Explanation:
//  Paths:
//      1->2 = 12
//      1->3 = 13
//  Sum = 12 + 13 = 25

//  Example 1:
//  Input: [1,2,3]
//  Output: 25
//  Explanation: The root-to-leaf path 1->2 represents the number 12. The root-to-leaf path 1->3 represents the number 13. Therefore, the sum is 12 + 13 = 25.

//  Solution: “I traverse the tree with DFS, building the number along the path as currentNumber * 10 + node.val,
//  and add it to total when I reach leaf nodes.”
//  Time -> O(n) (visit each node once)
//  Space -> O(h) recursion stack
//    Worst-case skewed tree -> O(n)
//    Balanced tree -> O(log n)


public class SumRootToLeafNumbers {

  static class TreeNode {

    int val;
    TreeNode left, right;

    TreeNode(int val) {
      this.val = val;
    }
  }

  public int sumNumbers(TreeNode root) {
    return dfs(root, 0);
  }

  private int dfs(TreeNode node, int currentNumber) {
    if (node == null) {
      return 0;
    }

    currentNumber = currentNumber * 10 + node.val;

    // leaf node
    if (node.left == null && node.right == null) {
      return currentNumber;
    }

    return dfs(node.left, currentNumber) + dfs(node.right, currentNumber);
  }

  // -------- MAIN METHOD --------
  public static void main(String[] args) {

    SumRootToLeafNumbers sol = new SumRootToLeafNumbers();

        /*
            Tree ->
                  1
                /   \
               2     3
        */

    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);

    int sum = sol.sumNumbers(root);

    System.out.println("Total Sum of Root-to-Leaf Numbers -> " + sum);
  }
}
