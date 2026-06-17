package datastructure_algorithms.patterns.stackqueue.monotonic;

import java.util.ArrayDeque;
import java.util.Deque;

//  Write a class StockSpanner which collects daily price quotes for some stock, and returns the span of that stock's price
//  for the current day. The span of the stock's price today is defined as the maximum number of consecutive days
//  (starting from today and going backwards) for which the price of the stock was less than or equal to today's price.

//  You are tracking the price of a stock every day. Each day, you are given today’s price, and you must return today’s span.
//  The span for today’s price is:
//  The maximum number of consecutive days, starting from today and going backwards, for which
//  the stock price was less than or equal to today’s price.

//  Solution: I maintain a monotonic decreasing stack of (price, span) pairs. When a new price arrives,
//  I merge all smaller or equal prices into it by accumulating their spans. Each element is pushed and popped once,
//  giving amortized O(1) time.
//  Time: Amortized O(1) per call
//  Space: O(n) (stack)

public class OnlineStockSpan {

  private Deque<int[]> stack;

  public OnlineStockSpan() {
    stack = new ArrayDeque<>();
  }

  public int next(int price) {
    int span = 1;
    while (!stack.isEmpty() && stack.peek()[0] <= price) {
      span = span + stack.pop()[1];
    }
    stack.push(new int[]{price, span});
    return span;
  }

  public static void main(String[] args) {
    OnlineStockSpan spanner = new OnlineStockSpan();
    int[] prices = {100, 80, 60, 70, 60, 75, 85};

    for (int price : prices) {
      System.out.print(spanner.next(price) + " ");
    }
  }
}
