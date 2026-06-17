package datastructure_algorithms.patterns.dynamicprogrammimg.more;

//  Given array is stock prices [7,1,5,3,6,4] on consecutive days we need to buy and sell stock on
//  any different days to get maximum profit and return it.

//  Each index = day
//  Value = price on that day
//  You must:
//  Buy on one day
//  Sell on a later day
//  Maximize profit

//  Day:    0  1  2  3  4  5
//  Price:  7  1  5  3  6  4
//
//  Best strategy:
//  Buy at price = 1 (Day 1)
//  Sell at price = 6 (Day 4)
//  Max Profit:
//      6 - 1 = 5

//  Time  = O(n)
//  Space = O(1)

public class BuyAndSellStock1 {

  public static int maxProfit(int[] prices) {

    int minPrice = prices[0];
    int maxProfit = 0;

    for(int i = 1; i < prices.length; i++) {
      int profit = prices[i] - minPrice;
      maxProfit = Math.max(maxProfit, profit);
      minPrice = Math.min(minPrice, prices[i]);
    }

    return maxProfit;
  }

  public static void main(String[] args) {

    int[] prices = {7, 1, 5, 3, 6, 4};

    int result = sol(prices);

    System.out.println("Maximum Profit = " + result);
  }

  public static int sol(int[] prices) {
    int max = 0;
    int min = prices[0];

    for(int i = 1; i < prices.length; i++) {
      max = Math.max(max, prices[i] - min);
      min = Math.min(min, prices[i]);
    }
    return max;
  }




















}
