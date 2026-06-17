package datastructure_algorithms.patterns.dynamicprogrammimg.rangedp;

//  You are given n balloons, indexed from 0 to n - 1. Each balloon is painted with a number on it
// represented
//  by an array nums. You are asked to burst all the balloons.
//  If you burst the i-th balloon, you will get nums[left] * nums[i] * nums[right] coins. Here left
// and right are
//  the indices of the balloons adjacent to i. After the burst, the left and right then become
// adjacent.
//  Find the maximum coins you can collect by bursting the balloons wisely.
//
//  Note:
//      - You may imagine nums[-1] = nums[n] = 1. They are not real therefore you cannot burst them.
//      - 0 ? n ? 500, 0 ? nums[i] ? 100
//
//  Example 1:
//  Input: nums = [3,1,5,8]
//  Output: 167
//  Explanation: nums = [3,1,5,8] -> burst balloon 1: coins = 3*1*5 = 15; nums = [3,5,8] Burst
// balloon 5: coins = 3*5*8 = 120;
//  nums = [3,8] Burst balloon 3: coins = 1*3*8 = 24; nums = [8] Burst balloon 8: coins = 1*8*1 = 8
//  Total coins = 15 + 120 + 24 + 8 = 167
//
//  Example 2:
//  Input: nums = [1,5]
//  Output: 10
//  Explanation: Burst balloon 1: coins = 1*5*1 = 5 Burst balloon 5: coins = 1*1*1 = 1 Total coins =
// 5 + 1 = 6
//  But better to burst balloon 5 first: coins = 1*5*1 = 5 Then burst balloon 1: coins = 1*1*1 = 1
//  Total coins = 5 + 1 = 6 Actually the maximum coins is 10 by bursting balloons in order 1 then 5
// considering the boundaries.

//  Solution: This is an interval DP problem.
//  We assume each balloon as the last to burst in a subarray, which makes its neighbors fixed.
//  This removes dependency issues and allows a clean DP formulation.
//  If balloon k is the last balloon burst in a range (i, j):
//  All balloons between i and j except k are already gone
//  So k's neighbors are fixed: i and j
//  Coins gained:
//  nums[i] * nums[k] * nums[j]
//      This is deterministic ? perfect for DP.

//  Time: O(n³)
//  Space: O(n²)
//  (This is optimal for this problem)

public class BurstBalloons {

  public static int maxCoins(int[] input) {
    int n = input.length;
    // Create new array with 1 at both ends
    int[] arr = new int[n + 2];
    arr[0] = 1;
    arr[n + 1] = 1;

    for (int i = 0; i < n; i++) {
      arr[i + 1] = input[i];
    }
    // dp[i][j] = max coins for bursting balloons in (i, j)
    int[][] dp = new int[n + 2][n + 2];
    // length is the gap between i and j
    for (int length = 2; length < n + 2; length++) {
      for (int i = 0; i + length < n + 2; i++) {
        int j = i + length;

        for (int k = i + 1; k < j; k++) {
          int coins = dp[i][k] + dp[k][j] + arr[i] * arr[k] * arr[j];
          dp[i][j] = Math.max(dp[i][j], coins);
        }
      }
    }
    return dp[0][n + 1];
  }

  public static void main(String[] args) {
    int[] nums = {3, 1, 5, 8};
    System.out.println("Maximum coins: " + sol(nums));
  }

  public static int sol(int[] input) {
    int n = input.length;
    int[] array = new int[n + 2];
    array[0] = 1;
    array[n + 1] = 1;
    for (int i = 0; i < input.length; i++) {
      array[i + 1] = input[i];
    }
    int[][] dp = new int[n + 2][n + 2];

    for (int length = 2; length < n + 2; length++) {
      for (int i = 0; i + length < n + 2; i++) {
        int j = i + length;
        for (int k = i + 1; k < j; k++) {
          int cost = dp[i][k] + dp[k][j] + array[i] * array[j] * array[k];
          dp[i][j] = Math.max(dp[i][j], cost);
        }
      }
    }
    return dp[0][n + 1];
  }
}
