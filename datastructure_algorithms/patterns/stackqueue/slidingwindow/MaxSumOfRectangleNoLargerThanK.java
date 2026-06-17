package datastructure_algorithms.patterns.stackqueue.slidingwindow;

//  Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that
//  its sum is no larger than k.
//
//  Example 1:
//  Input: [[1,0,1],[0,-2,3]], 2
//  Output: 2
//  Explanation: Because the sum of rectangle [[0, 1], [-2, 3]] is 2, and 2 is the max number no larger than k (k = 2).
//
//  Example 2:
//  Input: [[2,2,-1]], 3
//  Output: 3
//  Explanation: Because the sum of rectangle [[2, 2]] is 4, and 4 is the max number no larger than k (k = 3).
//
//  Example 3:
//  Input: [[2,2,-1]], 0
//  Output: -1
//  Explanation: There is no rectangle with a sum no larger than 0.

//  Solution: “I reduce the 2D problem into multiple 1D max-subarray-with-limit problems using column compression
//  and solve each using prefix sums and a TreeSet.”
//  Time	O(C² · R · log R)
//      Space	O(R)                   R= Row, C= Column

import java.util.TreeSet;

public class MaxSumOfRectangleNoLargerThanK {

  public static int maxSumSubmatrix(int[][] matrix, int k) {
    int rows = matrix.length;
    int cols = matrix[0].length;
    int result = Integer.MIN_VALUE;

    // Fix left column
    for (int left = 0; left < cols; left++) {
      int[] rowSum = new int[rows];
      // Fix right column
      for (int right = left; right < cols; right++) {
        // Build row sums
        for (int r = 0; r < rows; r++) {
          rowSum[r] += matrix[r][right];
        }
        // Find max subarray sum <= k
        result = Math.max(result, maxSubArrayNoMoreThanK(rowSum, k));
      }
    }
    return result;
  }

  private static int maxSubArrayNoMoreThanK(int[] nums, int k) {
    TreeSet<Integer> set = new TreeSet<>();
    set.add(0);
    int prefixSum = 0;
    int max = Integer.MIN_VALUE;

    for (int num : nums) {
      prefixSum += num;
      Integer target = set.ceiling(prefixSum - k);
      if (target != null) {
        max = Math.max(max, prefixSum - target);
      }
      set.add(prefixSum);
    }
    return max;
  }

  // ---------------- MAIN METHOD ----------------
  public static void main(String[] args) {

    int[][] matrix = {{1, 0, 1}, {0, -2, 3}};
    int k = 2;

    System.out.println(maxSumSubmatrix(matrix, k)); // 2
  }
}
