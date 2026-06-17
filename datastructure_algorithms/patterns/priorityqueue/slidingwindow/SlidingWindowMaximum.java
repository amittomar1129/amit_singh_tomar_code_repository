package datastructure_algorithms.patterns.priorityqueue.slidingwindow;

//  You are given an array of integers nums, there is a sliding window of size k which is moving from
//  the very left of the array to the very right. You can only see the k numbers in the window.
//  Each time the sliding window moves right by one position. Return the max sliding window.
//
//  Example 1:
//  Input: [1,3,-1,-3,5,3,6,7]
//  Output: [3,3,5,5,6,7]
//  Explanation: Window position: [1,3,-1], -3, 5, 3, 6, 7. Max value in the window: 3

//  Solution: I use a monotonic decreasing deque that stores indices.
//  The front always holds the maximum of the current window.
//  Each element is processed once, giving O(n) time complexity.”
//  Time: O(n)
//  Space: O(k)

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class SlidingWindowMaximum {

  public static int[] maxSlidingWindow(int[] nums, int k) {
    if (nums == null || nums.length == 0) {
      return new int[0];
    }
    int n = nums.length;
    int[] result = new int[n - k + 1];

    Deque<Integer> deque = new ArrayDeque<>();

    for (int i = 0; i < n; i++) {
      // Remove indices out of current window
      while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
        deque.pollFirst();
      }
      // Remove smaller elements from back
      while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
        deque.pollLast();
      }
      // Add current index
      deque.offerLast(i);
      // Store result once window is complete
      if (i >= k - 1) {
        result[i - k + 1] = nums[deque.peekFirst()];
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
