package datastructure_algorithms.patterns.priorityqueue.slidingwindow;

//  Given an array of integers nums and an integer k, return the maximum sum of a subarray of size k.
//
//  Example 1:
//  Input: [1,2,3,4], k = 2
//  Output: 7
//  Explanation: Subarray [3,4] has the maximum sum of 7.
//
//  Example 2:
//  Input: [1,2,3,4,5], k = 3
//  Output: 12
//  Explanation: Subarray [3,4,5] has the maximum sum of 12.

//  Solution: I use a fixed-size sliding window.
//  I maintain the current sum, add the new element, and remove the element leaving the window.
//  This gives O(n) time and O(1) space.
//  Time	O(n)
//  Space	O(1)

public class MaximumSumOfSubarray {

  public static int maxSumSubarray(int[] nums, int k) {
    int windowSum = 0;
    int maxSum = Integer.MIN_VALUE;

    for (int i = 0; i < nums.length; i++) {

      windowSum += nums[i];

      // when window size reaches k
      if (i >= k - 1) {
        maxSum = Math.max(maxSum, windowSum);
        windowSum -= nums[i - k + 1]; // remove left element
      }
    }

    return maxSum;
  }

  public static void main(String[] args) {

    int[] nums = {2, 1, 5, 1, 3, 2};
    int k = 3;

    int result = maxSumSubarray(nums, k);
    System.out.println("Maximum sum -> " + result);
  }
}
