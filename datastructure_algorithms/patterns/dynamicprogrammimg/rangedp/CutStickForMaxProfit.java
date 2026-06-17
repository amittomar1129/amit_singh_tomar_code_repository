package datastructure_algorithms.patterns.dynamicprogrammimg.rangedp;

//  Cut the rod in such a way that total selling price is MAXIMUM.
//  Time  O(n²)
//  Space O(n)

public class CutStickForMaxProfit {

  public static int rodCutting(int[] price, int n) {
    int[] dp = new int[n + 1];
    dp[0] = 0; // To cut at zero length we get 0 profit

    for (int i = 1; i <= n; i++) {
      int maxProfit = 0;
      for (int j = 1; j <= i; j++) {
        maxProfit = Math.max(maxProfit, price[j - 1] + dp[i - j]);
      }
      dp[i] = maxProfit;
    }

    return dp[n];
  }

  public static void main(String[] args) {
    int[] price = new int[] {1, 5, 6, 9, 11, 12, 14, 16};
    int length = 8;
    System.out.println("Max Profit: " + rodCutting(price, length));
  }
}
