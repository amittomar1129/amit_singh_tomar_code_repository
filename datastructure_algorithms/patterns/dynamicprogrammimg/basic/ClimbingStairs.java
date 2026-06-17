package datastructure_algorithms.patterns.dynamicprogrammimg.basic;

//  You are climbing a staircase. It takes n steps to reach the top. Each time you can either climb 1 or 2 steps.
//  In how many distinct ways can you climb to the top?
//
//  Example 1:
//  Input: 2
//  Output: 2
//  Explanation: There are two ways to climb to the top: 1 step + 1 step or 2 steps.
//
//      Example 2:
//  Input: 3
//  Output: 3
//  Explanation: There are three ways to climb to the top: 1 step + 1 step + 1 step, 1 step + 2 steps, or 2 steps + 1 step.

//  Solution: dp[i] = dp[i-1] + dp[i-2]
//  Time	O(n)
//  Space	O(1)

public class ClimbingStairs {

  // Optimized DP function
  public static int climbStairs(int n) {
    if (n <= 1) {
      return 1; // base case
    }

    int a = 1; // dp[0]
    int b = 1; // dp[1]

    for (int i = 2; i <= n; i++) {
      int temp = a + b;
      a = b;
      b = temp;
    }

    return b; // dp[n]
  }

  public static void main(String[] args) {
    int[] tests = {1, 2, 3, 4, 5, 10};
    for (int n : tests) {
      System.out.println("Number of ways to climb " + n + " stairs -> " + sol(n));
    }
  }

  public static int sol(int n) {
    if (n <= 1) {
      return 1;
    }
    int a = 1;
    int b = 1;

    for(int i = 2; i <= n; i++) {
      int temp = a + b;
      a = b;
      b = temp;
    }

    return b;
  }





}
