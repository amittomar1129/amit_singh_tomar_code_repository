package datastructure_algorithms.patterns.stackqueue.monotonic;

//  Given a list of daily temperatures T, return a list such that, for each day in the input,
//  tells you how many days you would have to wait until a warmer temperature.
//  If there is no future day with a warmer temperature, put 0 instead.
//
//  Example 1:
//  Input: [73, 74, 75, 71, 69, 72, 76, 73]
//  Output: [1, 1, 4, 2, 1, 1, 0, 0]
//  Explanation: For the first day, the temperature is 73 and the next day is 74, so you have to
// wait 1 day to get a warmer temperature. For the second day, the temperature is 74 and the next
// day is 75, so you have to wait 1 day to get a warmer temperature. For the third day, the
// temperature is 75 and the next day is 71, which means you have to wait 4 days to get a warmer
// temperature.
//
//  Example 2:
//  Input: [30, 40, 50, 60]
//  Output: [1, 1, 1, 0]
//  Explanation: For each day, the next day has a warmer temperature.
//
//      Example 3:
//  Input: [30, 60, 90]
//  Output: [1, 1, 0]
//  Explanation: For the first day, the temperature is 30 and the next day is 60, so you have to
// wait 1 day to get a warmer temperature. For the second day, the temperature is 60 and the next
// day is 90, so you have to wait 1 day to get a warmer temperature.

//  Solution: I use a monotonic decreasing stack of indices. When a warmer day appears, I resolve
// all
//  previous colder days by calculating the day difference.
//  Time: O(n)
//  Space: O(n)

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

public class DailyTemperatures {

  public static int[] dailyTemperatures(int[] temperatures) {
    int[] result = new int[temperatures.length];
    Stack<Integer> stack = new Stack<>();

    for (int i = 0; i < temperatures.length; i++) {
      while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
        int prevDay = stack.pop();
        result[prevDay] = i - prevDay;
      }
      stack.push(i);
    }
    return result;
  }

  public static void main(String[] args) {
    int[] T = {73, 74, 75, 71, 69, 72, 76, 73};
    System.out.println(Arrays.toString(dailyTemperatures(T)));
  }
}
