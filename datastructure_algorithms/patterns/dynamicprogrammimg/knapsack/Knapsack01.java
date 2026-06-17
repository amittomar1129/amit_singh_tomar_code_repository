package datastructure_algorithms.patterns.dynamicprogrammimg.knapsack;

//  Given weights and values of n items, put these items in a knapsack of capacity W to get the
//  maximum total value in the knapsack. You cannot break an item, either pick the complete item or
// don’t pick it.

//  You are given: n items
//  Each item has:
//    weight[i]
//    value[i]
//  A knapsack with maximum capacity W
//  weights = [1, 3, 4, 5]
//  values  = [1, 4, 5, 7]
//  W = 7

//  Rules
//  You cannot break an item.
//  For each item -> either take it OR leave it.
//  Goal -> maximize total value without exceeding capacity.

//  Solution:
//  Time	O(n × W)
//  Space	O(W)

public class Knapsack01 {

  public static int knapsack(int[] weights, int[] values, int W) {
    int[] dp = new int[W + 1];

    for (int i = 0; i < weights.length; i++) {
      for (int j = W; j >= weights[i]; j--) {
        dp[j] = Math.max(dp[j], values[i] + dp[j - weights[i]]);
      }
    }
    return dp[W];
  }

  public static void main(String[] args) {
    int[] weights = {1, 3, 4, 5};
    int[] values = {1, 4, 5, 7};
    int W = 7;

    System.out.println("Maximum value in knapsack -> " + sol(weights, values, W));
  }

  public static int sol(int[] weights, int[] values, int W) {
    int[] dp = new int[W+1];

    for(int i = 0; i < weights.length; i++) {
     for(int j = W; j >= weights[i] ; j--) {
       dp[j] = Math.max(dp[j],  values[i] + dp[j - weights[i]] );
     }
    }

    return dp[W];
  }





















}
