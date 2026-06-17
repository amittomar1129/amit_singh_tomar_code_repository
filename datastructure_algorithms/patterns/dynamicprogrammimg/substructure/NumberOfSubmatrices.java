package datastructure_algorithms.patterns.dynamicprogrammimg.substructure;

//  Given a matrix and a target, return the number of non-empty submatrices that sum to target.
//  A submatrix x1, y1, x2, y2 is the set of all cells matrix[x][y] with x1 <= x <= x2 and y1 <= y
// <= y2.
//  Two submatrices (x1, y1, x2, y2) and (x1', y1', x2', y2') are different if they have some
// coordinate
//  that is different: for example, if x1 != x1'.

//  A submatrix is any rectangle inside the matrix:
//  Choose top row x1
//  Choose bottom row x2
//  Choose left column y1
//  Choose right column y2
//  All cells inside that rectangle are included.

//  Example 1:
//  Input: matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
//  Output: 4
//  Explanation: The four 1x1 submatrices with zero sum are the four 0's in the matrix.
//
//  Example 2:
//  Input: matrix = [[1,-1],[-1,1]], target = 0
//  Output: 5
//  Explanation: There are 5 submatrices that sum to 0: four 1x1 submatrices with 0 sum and one 2x2
// submatrix.

//  Solution: I fix two rows, compress the matrix into a 1D array of column sums, then count
//  subarrays equal to target using prefix sums and a HashMap
//  Time	O(rows² × cols)
//  Space	O(cols)

import java.util.HashMap;

public class NumberOfSubmatrices {

  public static int numSubmatrixSumTarget(int[][] matrix, int target) {
    int m = matrix.length;
    int n = matrix[0].length;
    int count = 0;

    for (int left = 0; left < n; left++) {
      int[] temp = new int[m];

      for (int right = left; right < n; right++) {

        // Build 1D array for rows [top -> bottom]
        for (int i = 0; i < m; i++) {
          temp[i] += matrix[i][right];
        }

        // Count subarrays with sum == target
        count += countSubarrays(temp, target);
      }
    }

    return count;
  }

  private static int countSubarrays(int[] nums, int target) {
    HashMap<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);

    int prefixSum = 0;
    int count = 0;

    for (int num : nums) {
      prefixSum += num;

      count += map.getOrDefault(prefixSum - target, 0);

      map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
    }

    return count;
  }

  public static void main(String[] args) {
    int[][] matrix = {
      {0, 1, 0},
      {1, 1, 1},
      {0, 1, 0}
    };

    int target = 0;

    System.out.println(
        "Number of submatrices with sum = target -> " + numSubmatrixSumTarget(matrix, target));
  }
}
