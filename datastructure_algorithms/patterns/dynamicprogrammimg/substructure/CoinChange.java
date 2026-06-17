package datastructure_algorithms.patterns.dynamicprogrammimg.substructure;

//  You are given coins of different denominations and a total amount of money amount.
//  Write a function to compute the fewest number of coins that you need to make up that amount.
//  If that amount of money cannot be made up by any combination of the coins, return -1.
//
//  Example 1:
//  Input: [1, 2, 5], 11
//  Output: 3
//  Explanation: 11 = 5 + 5 + 1
//
//  Example 2:
//  Input: [2], 3
//  Output: -1
//  Explanation: It is impossible to make 3 with only coins of 2.

//  Solution: This is an unbounded knapsack problem. We use 1D DP where dp[i] represents the minimum
// coins
//  needed to make amount i. For each amount, we try every coin and take the minimum.
//  dp[i] = minimum number of coins needed to make amount i
//  dp[i] = min(dp[i - coin] + 1) for all coins where i >= coin

//  Time -> O(amount × number_of_coins)
//  Space -> O(amount)

import java.util.Arrays;

public class CoinChange {

  public static int coinChange(int[] coins, int amount) {
    int[] dp = new int[amount + 1];
    Arrays.fill(dp, amount + 1);

    dp[0] = 0;

    for (int i = 1; i <= amount; i++) {
      for (int coin : coins) {
        if (coin <= i) {
          dp[i] = Math.min(dp[i], dp[i - coin] + 1);
        }
      }
    }
    return dp[amount] > amount ? -1 : dp[amount];
  }

  //    Time  = O(n × amount)
  //    Space = O(amount)
  public static int maxNoOfWays(int[] coins, int amount) {
    int[] dp = new int[amount + 1];
    dp[0] = 1; // Base case

    for (int coin : coins) {
      for (int i = coin; i <= amount; i++) {
        dp[i] += dp[i - coin];
      }
    }
    return dp[amount];
  }

  public static void main(String[] args) {
    int[] coins = {1, 2, 5};
    int amount = 11;

    int result = sol1(coins, amount);
    System.out.println("Minimum coins required -> " + result);

    int result1 = sol2(coins, amount);
    System.out.println("Maximum no of ways -> " + result1);
  }

  public static int sol1(int[] coins, int amount) {
    int[] dp = new int[amount + 1];

    for (int i = 0; i <= amount; i++) {
      for (int coin : coins) {
        if (coin <= i) {
          dp[i] = 1 + Math.min(dp[i], dp[i - coin]);
        }
      }
    }
    return dp[amount];
  }

  public static int sol2(int[] coins, int amount) {
    int[] dp = new int[amount + 1];
    dp[0] = 1;

    for (int coin : coins) {
      for (int i = 0; i <= amount; i++) {
        if (coin <= i) {
          dp[i] = dp[i] + dp[i - coin];
        }
      }
    }

    return dp[amount];
  }
}
