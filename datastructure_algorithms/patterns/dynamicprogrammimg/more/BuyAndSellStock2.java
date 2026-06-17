package datastructure_algorithms.patterns.dynamicprogrammimg.more;

//  This is the Unlimited Transactions version of the stock problem.
//  Rules: You can buy and sell any number of times.
//  But: You must sell before buying again. Only one stock can be held at a time.

//  Prices:
//  Day:    0  1  2  3  4  5
//  Price:  7  1  5  3  6  4
//
//  Look at increases:
//      1 -> 5  = +4
//      3 -> 6  = +3
//
//  Total profit: 4 + 3 = 7

//  Time  = O(n)
//  Space = O(1)

public class BuyAndSellStock2 {

  public static int maxProfit(int[] prices) {

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

    int result = sol(prices);

    System.out.println("Maximum Profit = " + result);
  }

  public static int sol(int[] prices) {
    int profit = 0;

    for(int i = 1; i < prices.length; i++) {
      if (prices[i] > prices[i-1]) {
        profit += prices[i] - prices[i-1];
      }
    }
    return profit;
  }



















}
