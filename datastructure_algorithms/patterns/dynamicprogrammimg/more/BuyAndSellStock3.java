package datastructure_algorithms.patterns.dynamicprogrammimg.more;

//  Given: prices[], K = maximum allowed transactions
//
//  Rules: You can complete at most K buy-sell pairs. Must sell before buying again. Only one stock
// at a time.

//  Time  = O(n * K)
//  Space = O(n * K)

public class BuyAndSellStock3 {

  public static int maxProfit(int[] prices, int K) {
    int n = prices.length;
    if (n == 0 || K == 0) {
      return 0;
    }

    // If K >= n/2 -> unlimited transactions
    if (K >= n / 2) {
      return unlimitedTransactions(prices);
    }

    int[][] dp = new int[K + 1][n];

    for (int i = 1; i <= K; i++) {
      int best = -prices[0];
      for (int j = 1; j < n; j++) {
        dp[i][j] = Math.max(dp[i][j - 1], prices[j] + best);
        best = Math.max(best, dp[i - 1][j] - prices[j]);
      }
    }

    return dp[K][n - 1];
  }

  private static int unlimitedTransactions(int[] prices) {

    int profit = 0;

    for (int i = 1; i < prices.length; i++) {
      if (prices[i] > prices[i - 1]) {
        profit += prices[i] - prices[i - 1];
      }
    }

    return profit;
  }

  public static void main(String[] args) {

    int[] prices = {7, 1, 5, 3, 6, 4};
    int K = 2;

    System.out.println("Max Profit = " + sol(prices, K));
  }

  public static int sol(int[] prices, int k) {
    if (k >= prices.length / 2) {
      return 0; // unlimited transactions
    }

    int[][] dp = new int[k+1][prices.length];

    for(int i = 1; i <= k; i++) {
      int best = -prices[0];
      for(int j = 1; j < prices.length; j++) {
        dp[i][j] = Math.max(dp[i][j-1], prices[j] + best);
        best = Math.max(best, dp[i-1][j] - prices[j]);
      }
    }
    return dp[k][prices.length-1];
  }


}
