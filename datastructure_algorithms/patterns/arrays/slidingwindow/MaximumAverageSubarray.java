package datastructure_algorithms.patterns.arrays.slidingwindow;

//  Given an array consisting of n integers, find the contiguous subarray of given length k that has
// the maximum average value.
//  You need to output the maximum average value.
//
//  Example 1:
//  Input: [1, 12, -5, -6, 50, 3]
//  Output: 12.75
//  Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75
//
//  Example 2:
//  Input: [5]
//  Output: 5.0
//  Explanation: Maximum average is 5/1 = 5
//
//  Example 3:
//  Input: [0, 4, 0, 3, 2]
//  Output: 2.4
//  Explanation: Maximum average is (4+0+3+2)/4 = 9/4 = 2.25

//  Solution: “Because the subarray length is fixed, I use a sliding window. I keep track of the
// current window sum and
//  update it in O(1) as I slide the window.”
//  Time: O(n)
//  Space: O(1)

public class MaximumAverageSubarray {

  public static double findMaxAverage(int[] input, int k) {
    int windowSum = 0;

    // First window
    for (int i = 0; i < k; i++) {
      windowSum = windowSum + input[i];
    }

    int maxSum = windowSum;

    // Slide the window
    for (int i = k; i < input.length; i++) {
      windowSum += input[i]; // add next element
      windowSum -= input[i - k]; // remove left element
      maxSum = Math.max(maxSum, windowSum);
    }

    return (double) maxSum / k;
  }

  public static void main(String[] args) {
    int[] nums = {1, 12, -5, -6, 50, 3};
    int k = 4;

    System.out.println(sol(nums, k)); // 12.75
  }

  public static double sol(int[] input, int k) {
    double maxAverage = 0;
    double sum = 0;

    for(int i = 0; i < k; i++) {
      sum += input[i];
    }
    maxAverage = sum / k;

    for (int i = k; i < input.length; i++) {
      sum += input[i];
      sum -= input[i-k];

      maxAverage = Math.max(maxAverage, sum/k);
    }

    return maxAverage;
  }
}
