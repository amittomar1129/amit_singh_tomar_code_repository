package datastructure_algorithms.patterns.dynamicprogrammimg.knapsack;

//  You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2
//  symbols + and -. For each integer, you should choose one from + and - as its new symbol.
//  Find out how many ways to assign symbols to make sum of integers equal to target S.
//
//  Example 1:
//  Input: [1, 1, 1, 1, 1]
//  Output: 3
//  Explanation: There are 3 ways to assign symbols to make the sum equal to 3: +1-1+1+1-1,
// -1+1-1+1+1, +1+1+1-1-1
//
//  Example 2:
//  Input: [1]
//  Output: 1
//  Explanation: There is 1 way to assign symbols to make the sum equal to 1: +1

//  We can transform the sign assignment problem into a subset sum problem using algebraic
//  manipulation. It reduces to counting subsets whose sum equals (S + totalSum) / 2.
//  Time	O(n * target)
//  Space	O(target)

public class TargetSum {

  public static int findTargetSumWays(int[] nums, int sum) {
    int totalSum = 0;
    for (int num : nums) {
      totalSum += num;
    }

    // Invalid cases
    if (sum > totalSum || (sum + totalSum) % 2 != 0) {
      return 0;
    }

    int target = (sum + totalSum) / 2;
    int[] dp = new int[target + 1];
    dp[0] = 1;

    for (int num : nums) {
      for (int i = target; i >= num; i--) {
        dp[i] = dp[i] + dp[i - num];
      }
    }
    return dp[target];
  }

  public static void main(String[] args) {
    int[] nums1 = {1, 1, 1, 1, 1};
    int S1 = 3;

    int[] nums2 = {1, 2, 3};
    int S2 = 1;

    System.out.println("Number of ways -> " + sol(nums1, S1));
    System.out.println("Number of ways -> " + sol(nums2, S2));
  }

  public static int sol(int[] input, int sum) {
    int total = 0;
    for (int a : input) {
      total += a;
    }
    if (total < sum || (total + sum) % 2 == 1) {
      return 0;
    }

    int target = (total + sum) / 2;
    int[] dp = new int[target + 1];
    dp[0] = 1;
    for(int a : input) {
      for(int i = target; i >= a ; i--) {
        dp[i] = dp[i] + dp[i - a];
      }
    }

    return dp[target];
  }



















}
