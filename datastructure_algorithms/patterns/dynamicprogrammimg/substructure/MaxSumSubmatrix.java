package datastructure_algorithms.patterns.dynamicprogrammimg.substructure;

//  Given a 2D matrix (can have positive & negative numbers), find a rectangular submatrix with
// maximum possible sum.
//  Time  = O(n³)
//  Space = O(n)

import java.util.TreeSet;

public class MaxSumSubmatrix {

  public static int maxSumSubmatrix(int[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;

    int maxSum = Integer.MIN_VALUE;

    // Fix left column
    for (int left = 0; left < n; left++) {

      int[] temp = new int[m];
      // Expand right column
      for (int right = left; right < n; right++) {
        // Add values column-wise
        for (int i = 0; i < m; i++) {
          temp[i] += matrix[i][right];
        }
        // Apply Kadane on compressed array
        int currentMax = kadane(temp);

        maxSum = Math.max(maxSum, currentMax);
      }
    }

    return maxSum;
  }

  // Standard Kadane's Algorithm
  private static int kadane(int[] arr) {

    int maxSoFar = arr[0];
    int current = arr[0];

    for (int i = 1; i < arr.length; i++) {
      current = Math.max(arr[i], current + arr[i]);
      maxSoFar = Math.max(maxSoFar, current);
    }

    return maxSoFar;
  }

  public static void main(String[] args) {

    int[][] matrix = {
      {1, 2, -1, -4},
      {-8, -3, 4, 2},
      {3, 8, 10, 1},
      {-4, -1, 1, 7}
    };

    int result = sol(matrix);
    System.out.println("Maximum Sum Submatrix = " + result);
  }

  public static int sol(int[][] input) {
    int m = input.length;
    int n = input[0].length;
    int max = Integer.MIN_VALUE;

    int K = 15;

    for (int left = 0; left < n; left++) {
      int[] temp = new int[m];
      for (int right = left; right < n; right++) {

        for (int i = 0; i < m; i++) {
          temp[i] += input[i][right];
        }
        int curr = kadane1(temp, K);
        max = Math.max(curr, max);
      }
    }
    return max;
  }

  public static int kadane1(int[] input, int K) {
    TreeSet<Integer> treeSet = new  TreeSet<>();
    int prefix = 0;
    int max = 0;

    for(int i = 0; i < input.length; i++) {
      prefix += input[i];

      Integer target = treeSet.ceiling(prefix - K);
      if (target != null) {
        max = Math.max(max, prefix - target);
      }
      treeSet.add(prefix);
    }
    return max;
  }
}
