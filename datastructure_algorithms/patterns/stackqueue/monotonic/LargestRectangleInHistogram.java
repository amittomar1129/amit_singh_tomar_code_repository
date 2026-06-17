package datastructure_algorithms.patterns.stackqueue.monotonic;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
//  Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
//  find the area of the largest rectangle in the histogram.
//
//  Example 1:
//  Input: [2,1,5,6,2,3]
//  Output: 10
//  Explanation: The largest rectangle is shown in the shaded area, which has an area = 10 units.
//
//      Example 2:
//  Input: [2,4]
//  Output: 4
//  Explanation: The largest rectangle is shown in the shaded area, which has an area = 4 units.
//
//      Example 3:
//  Input: [2]
//  Output: 2
//  Explanation: The largest rectangle is shown in the shaded area, which has an area = 2 units.

//  Solution: I use a monotonic increasing stack of indices. When a smaller height appears, I pop and compute
//  the area using the popped height as the limiting bar. Each bar is processed once, giving O(n) time.
//  Time: O(n)
//  Space: O(n)


public class LargestRectangleInHistogram {

  public static int largestRectangleArea(int[] heights) {
    Stack<Integer> stack = new Stack<>();
    int maxArea = 0;
    for (int i = 0; i <= heights.length; i++) {
      int currHeight = (i == heights.length) ? 0 : heights[i];

      while (!stack.isEmpty() && currHeight < heights[stack.peek()]) {
        int height = heights[stack.pop()];
        int width = stack.isEmpty() ? i : i - stack.peek() - 1;
        maxArea = Math.max(maxArea, height * width);
      }

      stack.push(i);
    }
    return maxArea;
  }

  public static void main(String[] args) {
    int[] heights = {2, 1, 5, 6, 2, 3};
    System.out.println("Largest Rectangle Area: " + largestRectangleArea(heights));
  }
}
