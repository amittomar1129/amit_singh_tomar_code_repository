package datastructure_algorithms.patterns.dynamicprogrammimg.substructure;

import java.util.TreeSet;


//  Time  O(n³ log n)
//  Space O(R)

public class MaxSumSubmatrixLessThanK {

  public static int maxSumSubmatrix(int[][] matrix, int K) {

    int m = matrix.length;
    int n = matrix[0].length;

    int maxSum = Integer.MIN_VALUE;

    // Fix left column
    for (int left = 0; left < n; left++) {

      int[] temp = new int[m];

      // Expand right column
      for (int right = left; right < n; right++) {

        // Compress rows
        for (int i = 0; i < m; i++) {
          temp[i] += matrix[i][right];
        }

        // Find max subarray sum <= K in temp[]
        maxSum = Math.max(maxSum, maxSubArrayNoMoreThanK(temp, K));
      }
    }

    return maxSum;
  }

  // 1D max subarray sum <= K using TreeSet
  private static int maxSubArrayNoMoreThanK(int[] arr, int K) {

    TreeSet<Integer> set = new TreeSet<>();
    set.add(0);

    int prefixSum = 0;
    int maxSum = Integer.MIN_VALUE;

    for (int num : arr) {

      prefixSum += num;

      // Find smallest prefix >= prefixSum - K
      Integer target = set.ceiling(prefixSum - K);

      if (target != null) {
        maxSum = Math.max(maxSum, prefixSum - target);
      }

      set.add(prefixSum);
    }

    return maxSum;
  }

  public static void main(String[] args) {

    int[][] matrix = {
        {1, 2, -1, -4},
        {-8, -3, 4, 2},
        {3, 8, 10, 1},
        {-4, -1, 1, 7}
    };

    int K = 15;

    int result = maxSumSubmatrix(matrix, K);
    System.out.println("Maximum Sum <= K: " + result);
  }
}
