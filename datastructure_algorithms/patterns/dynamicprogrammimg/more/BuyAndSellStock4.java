package datastructure_algorithms.patterns.dynamicprogrammimg.more;

//  Rules:
//  Unlimited transactions allowed
//  Must sell before buying again
//  Each time you sell -> you must pay a transaction fee

//  prices = [1,3,2,8,4,9]
//  fee = 2
//
//  Best transactions:
//
//  Buy at 1 -> Sell at 8
//  Profit = 8 - 1 - 2 = 5
//
//  Buy at 4 -> Sell at 9
//  Profit = 9 - 4 - 2 = 3
//
//  Total = 8

//  Time  = O(n)
//  Space = O(1)

public class BuyAndSellStock4 {

  //  When we sell:
  // cash = hold + price - fee
  //  When we buy:
  // hold = cash - price
  public static int maxProfit(int[] prices, int fee) {

    int maxProfitWithSelling = 0; // not holding
    int maxProfitWithBuying = -prices[0]; // holding

    for (int i = 1; i < prices.length; i++) {
      int profit = Math.max(maxProfitWithSelling, maxProfitWithBuying + prices[i] - fee); // selling
      int newHold = Math.max(maxProfitWithBuying, maxProfitWithSelling - prices[i]); // buying
      maxProfitWithSelling = profit;
      maxProfitWithBuying = newHold;
    }

    return maxProfitWithSelling;
  }

  public static void main(String[] args) {

    int[] prices = {1, 3, 2, 8, 4, 9};
    int fee = 2;

    System.out.println("Max Profit = " + sol(prices, fee));
  }

  public static int sol(int[] prices, int fee) {
    int maxProfitBySelling = 0;
    int maxProfitByBuying = -prices[0];

    for(int i = 1; i < prices.length; i++) {
      int profit = Math.max(maxProfitBySelling, maxProfitByBuying + prices[i] - fee);
      int newHold = Math.max(maxProfitByBuying, maxProfitBySelling - prices[i]);
      maxProfitBySelling = profit;
      maxProfitByBuying = newHold;
    }

    return maxProfitBySelling;
  }

}
