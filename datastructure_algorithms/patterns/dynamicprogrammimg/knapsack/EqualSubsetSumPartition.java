package datastructure_algorithms.patterns.dynamicprogrammimg.knapsack;

//  Given a non-empty array containing only positive integers, find if the array can be
//  partitioned into two subsets such that the sum of elements in both subsets is equal.
//
//  Example 1:
//  Input: [1, 5, 11, 5]
//  Output: true
//  Explanation: Subset1 = [1, 5, 5], Subset2 = [11], both have equal sum of 11.
//
//  Example 2:
//  Input: [1, 2, 3, 5]
//  Output: false
//  Explanation: No way to partition the array into two equal sum subsets.

//  Time	O(n * target)
//  Space	O(target)

public class EqualSubsetSumPartition {

  public static boolean canPartition(int[] nums) {
    int totalSum = 0;
    for (int num : nums) {
      totalSum += num;
    }

    // If total sum is odd, cannot partition equally
    if (totalSum % 2 != 0) {
      return false;
    }

    int target = totalSum / 2;
    boolean[] dp = new boolean[target + 1];
    dp[0] = true;

    for (int num : nums) {
      for (int i = target; i >= num; i--) {
        dp[i] = dp[i] || dp[i - num];
      }
    }

    return dp[target];
  }

  public static void main(String[] args) {
    int[] nums1 = {1, 5, 11, 5};
    int[] nums2 = {1, 2, 3};

    System.out.println("Can partition -> " + sol(nums1));
    System.out.println("Can partition -> " + sol(nums2));
  }

  public static boolean sol(int[] input) {
    int total = 0;
    for (int a : input){
      total += a;
    }
    if (total % 2 == 1) {
      return false;
    }
    int target = total / 2;

    boolean[] dp = new boolean[target+1];
    dp[0] = true;

    for (int num : input) {
      for(int i = target; i >= num; i--) {
        dp[i] = dp[i] || dp[i - num];
      }
    }

    return dp[target];
  }






















}
