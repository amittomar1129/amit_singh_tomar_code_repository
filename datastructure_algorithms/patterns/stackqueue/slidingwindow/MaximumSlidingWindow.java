package datastructure_algorithms.patterns.stackqueue.slidingwindow;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

//  You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array
//  to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
//  Return the max sliding window.
//
//  Example 1:
//  Input: [1,3,-1,-3,5,3,6,7]
//  Output: [3,3,5,5,6,7]
//  Explanation: Window position 1: [1,3,-1], max = 3 Window position 2: [3,-1,-3], max = 3 Window position 3: [-1,-3,5], max = 5 Window position 4: [-3,5,3], max = 5 Window position 5: [5,3,6], max = 6 Window position 6: [3,6,7], max = 7
//
//  Example 2:
//  Input: [1]
//  Output: [1]
//  Explanation: Window size is 1, max = 1
//
//  Example 3:
//  Input: [1,-1]
//  Output: [1,-1]
//  Explanation: Window size is 1, max = 1, then window moves to the right, max = -1

//  Solution: “I maintain a monotonic decreasing deque of indices so the front always holds the maximum of the
//  current window, allowing O(n) time.”
//  Time: O(n) (Each index enters and leaves deque once)
//  Space: O(k)

public class MaximumSlidingWindow {

  public static int[] maxSlidingWindow(int[] input, int k) {
    if (input == null || input.length == 0) {
      return new int[0];
    }
    int[] result = new int[input.length - k + 1];
    Deque<Integer> deque = new ArrayDeque<>();
    for (int i = 0; i < input.length; i++) {
      // 1. Remove indices out of current window
      while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
        deque.pollFirst();
      }
      // 2. Maintain decreasing order
      while (!deque.isEmpty() && input[deque.peekLast()] <= input[i]) {
        deque.pollLast();
      }
      // 3. Add current index
      deque.offerLast(i);
      // 4. Record max when window is valid
      if (i >= k - 1) {
        result[i - k + 1] = input[deque.peekFirst()];
      }
    }
    return result;
  }

  public static void main(String[] args) {
    int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
    int k = 3;

    int[] output = maxSlidingWindow(nums, k);
    System.out.println(Arrays.toString(output));
  }
}
