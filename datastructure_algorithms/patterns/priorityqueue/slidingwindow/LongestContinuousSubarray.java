package datastructure_algorithms.patterns.priorityqueue.slidingwindow;

//  Given an array of integers nums and an integer limit, return the size of the longest continuous subarray
//  such that the absolute difference between any two elements of this subarray is less than or equal to limit.
//  In other words, find the longest subarray where max(nums[i..j]) - min(nums[i..j]) <= limit.
//
//  Example 1:
//  Input: nums = [8,2,4,7], limit = 4
//  Output: 2
//  Explanation: The longest subarray is [2,4] with absolute difference 2.
//
//  Example 2:
//  Input: nums = [10,1,2,4,7,2], limit = 5
//  Output: 4
//  Explanation: The longest subarray is [2,4,7,2] with absolute difference 5.

//  Solution: “I use two monotonic deques to track the max and min in the current window.
//  I expand the window to include new elements, and shrink the window from the left whenever the condition max-min > limit is violated.
//  This gives O(n) time because each element is added and removed at most once.”
//  Time	O(n) each element added/removed at most once
//  Space	O(n) worst-case deques

import java.util.ArrayDeque;
import java.util.Deque;

public class LongestContinuousSubarray {

  public static int longestSubarray(int[] nums, int limit) {
    Deque<Integer> maxDeque = new ArrayDeque<>();
    Deque<Integer> minDeque = new ArrayDeque<>();
    int left = 0, maxLen = 0;

    for (int right = 0; right < nums.length; right++) {
      // maintain maxDeque (decreasing)
      while (!maxDeque.isEmpty() && nums[right] > maxDeque.peekLast()) {
        maxDeque.pollLast();
      }
      maxDeque.offerLast(nums[right]);
      // maintain minDeque (increasing)
      while (!minDeque.isEmpty() && nums[right] < minDeque.peekLast()) {
        minDeque.pollLast();
      }
      minDeque.offerLast(nums[right]);
      // shrink window if invalid
      while (maxDeque.peekFirst() - minDeque.peekFirst() > limit) {
        if (nums[left] == maxDeque.peekFirst()) {
          maxDeque.pollFirst();
        }
        if (nums[left] == minDeque.peekFirst()) {
          minDeque.pollFirst();
        }
        left++;
      }
      maxLen = Math.max(maxLen, right - left + 1);
    }

    return maxLen;
  }

  public static void main(String[] args) {
    int[] nums = {8, 2, 4, 7};
    int limit = 4;

    int result = longestSubarray(nums, limit);
    System.out.println("Longest subarray length -> " + result);
  }
}
