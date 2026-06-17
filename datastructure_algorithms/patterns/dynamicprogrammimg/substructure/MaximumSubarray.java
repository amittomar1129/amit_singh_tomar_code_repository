package datastructure_algorithms.patterns.dynamicprogrammimg.substructure;

//  Given an integer array nums, find the contiguous subarray (containing at least one number)
//  which has the largest sum and return its sum.

//  You must find a contiguous subarray (elements next to each other) that:
//  Contains at least one number
//  Has the maximum possible sum
//  Return only the sum, not the subarray.

//  Example 1:
//  Input: [-2,1,-3,4,-1,2,1,-5,4]
//  Output: 6
//  Explanation: Contiguous subarray [4,-1,2,1] has the largest sum = 6.
//
//  Example 2:
//  Input: [1]
//  Output: 1
//  Explanation: Contiguous subarray [1] has the largest sum = 1.
//
//  Example 3:
//  Input: [5,4,-1,7,8]
//  Output: 23

//  Solution: At each index, I decide whether to start a new subarray or extend the previous one.
//  The DP recurrence is dp[i] = max(nums[i], dp[i-1] + nums[i]), and I track the global maximum.
//  dp[i] = maximum subarray sum ending at index i
//  DP Formula: dp[i] = max(nums[i], dp[i-1] + nums[i])

//  Time -> O(n)
//  Space -> O(1)

public class MaximumSubarray {

  public static int maxSubArray(int[] input) {
    int currentSum = input[0];
    int maxSum = input[0];

    for (int i = 1; i < input.length; i++) {
      currentSum = Math.max(input[i], currentSum + input[i]);
      maxSum = Math.max(maxSum, currentSum);
    }

    return maxSum;
  }

  public static void main(String[] args) {
    int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
    int result = maxSubArray(nums);
    System.out.println("Maximum Subarray Sum -> " + result);
  }

  public static int sol(int[] input) {
    int max = 0;
    int curr = 0;
    for(int i = 0; i < input.length; i++) {
      curr = Math.max(input[i], curr + input[i]);
      max = Math.max(max, curr);
    }
    return max;
  }
























}
