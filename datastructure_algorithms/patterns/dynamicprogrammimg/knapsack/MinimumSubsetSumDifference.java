package datastructure_algorithms.patterns.dynamicprogrammimg.knapsack;

//  Given a non-empty array nums containing only positive integers, find the minimum difference
//  between the sums of the two subsets.
//
//  Example 1:
//  Input: [1,2,3,9]
//  Output: 3
//  Explanation: The minimum difference can be achieved by selecting subset1 = [1,2,3] and subset2 = [9] which gives |6 - 9| = 3.
//
//  Example 2:
//  Input: [1,2,7,1,5]
//  Output: 0
//  Explanation: The minimum difference can be achieved by selecting subset1 = [1,2,5] and subset2 = [7,1] which gives |8 - 8| = 0.

//  Time	O(n * totalSum)
//  Space	O(totalSum)

public class MinimumSubsetSumDifference {

  public static int minSubsetSumDifference(int[] nums) {
    int totalSum = 0;
    for (int num : nums) {
      totalSum += num;
    }

    int target = totalSum / 2;
    boolean[] dp = new boolean[target + 1];
    dp[0] = true;

    for (int num : nums) {
      for (int i = target; i >= num; i--) {
        dp[i] = dp[i] || dp[i - num];
      }
    }

    int best = 0;
    for (int i = target; i >= 0; i--) {
      if (dp[i]) {
        best = i;
        break;
      }
    }

    return Math.abs(totalSum - 2 * best);
  }

  public static void main(String[] args) {
    int[] nums1 = {1, 6, 11, 5};
    int[] nums2 = {1, 2, 3, 9};

    System.out.println("Minimum difference -> " + sol(nums1));
    System.out.println("Minimum difference -> " + sol(nums2));
  }

  public static int sol(int[] input) {
    int total = 0;
    for (int a : input) {
      total += a;
    }
    int target = total / 2;
    boolean[] dp = new boolean[target+1];
    dp[0] = true;
    for (int a : input) {
      for(int i = target; i >= a ; i--) {
        dp[i] = dp[i] || dp[i - a];
      }
    }
    int best = 0;
    for(int i = target; i >= 0 ; i--) {
      if (dp[i]) {
        best = i;
        break;
      }
    }

    return total -  (2 * best);
  }










}
