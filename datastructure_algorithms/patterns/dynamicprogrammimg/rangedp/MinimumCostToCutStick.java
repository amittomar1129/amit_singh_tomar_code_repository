package datastructure_algorithms.patterns.dynamicprogrammimg.rangedp;

//  Given a wooden stick of length `n` units. The stick is labeled from `0` to `n`. You are given an
//  integer array `cuts` where `cuts[i]` denotes a position you should perform a cut at.
//  You should perform the cuts in any order you want. The cost of one cut is the length of the stick to be cut.
//  Return the minimum total cost of the cuts.
//  Note: The position of the cuts is always between `0` and `n` (exclusive).
//
//  Example 1:
//  Input: n = 7, cuts = [1,3,4,5]
//  Output: 16
//  Explanation: The optimal order to perform the cuts is: first cut at 3, then at 5, then at 1, and finally at 4.
//  The total cost is 7 + 4 + 3 + 2 = 16.
//
//  Example 2:
//  Input: n = 9, cuts = [5,6,1,4,2]
//  Output: 22
//  Explanation: One optimal order is to cut at 5, then 6, then 1, then 4, and finally 2. The total cost
//  is 9 + 4 + 3 + 2 + 4 = 22.

//  Solution: This is an interval DP problem.
//  We assume each cut is performed last in a segment, which makes the cut cost fixed.
//  We compute optimal costs for smaller intervals and build up to the full stick.”
//  dp[i][j] = min(dp[i][k] + dp[k][j] + cost_of_interval)

//  Time: O(m³)
//  Space: O(m²)
//      This is optimal and expected

import java.util.Arrays;

public class MinimumCostToCutStick {

  public static int minCost(int[] cuts, int stickLength) {
    int n = cuts.length;

    // Create new cuts array with boundaries
    int[] arr = new int[n + 2];
    arr[0] = 0;
    arr[n + 1] = stickLength;

    for (int i = 0; i < n; i++) {
      arr[i + 1] = cuts[i];
    }

    Arrays.sort(arr);

    // dp[i][j] = min cost to cut stick between arr[i] and arr[j]
    int[][] dp = new int[n + 2][n + 2];

    // length is the gap between i and j
    for (int length = 2; length < n + 2; length++) {
      for (int i = 0; i + length < n + 2; i++) {
        int j = i + length;
        dp[i][j] = Integer.MAX_VALUE;

        for (int k = i + 1; k < j; k++) {
          int cost = dp[i][k] + dp[k][j] + (arr[j] - arr[i]);
          dp[i][j] = Math.min(dp[i][j], cost);
        }
      }
    }

    return dp[0][n + 1];
  }

  public static void main(String[] args) {
    int n = 7;
    int[] cuts = {1, 3, 4, 5};

    System.out.println("Minimum cost to cut stick -> " + sol(cuts, n));
  }

  public static int sol(int[] cuts, int stickLength) {
    int n = cuts.length;
    int[] array = new int[n + 2];
    array[0] = 0;
    array[n+1] = stickLength;
    for(int i = 0; i < cuts.length; i++) {
      array[i+1] = cuts[i];
    }
    Arrays.sort(array);
    int[][] dp = new int[n+2][n+2];

    for(int length = 2; length < n+2; length++) {
      for(int i = 0; i + length < n+2 ; i++) {
        int j = i + length;
        dp[i][j] = Integer.MAX_VALUE;
        for(int k = i + 1; k < j; k++) {
          int cost = dp[i][k] + dp[k][j] + array[j] - array[i];
          dp[i][j] = Math.min(dp[i][j], cost);
        }
      }
    }
    return dp[0][n+1];
  }










}
