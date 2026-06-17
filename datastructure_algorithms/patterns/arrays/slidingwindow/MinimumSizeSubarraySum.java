package datastructure_algorithms.patterns.arrays.slidingwindow;

//  Given an array of positive integers nums and a positive integer target, return the minimal
// length of
//  a contiguous subarray [numsl, numsl+1, ..., numsr-1, numsr] of which the sum is greater than or
// equal to target.
//  If there is no such subarray, return 0 instead.
//
//  Example 1:
//  Input: [2,3,1,2,4,3], 7
//  Output: 2
//  Explanation: The subarray [4,3] has the minimal length under the problem constraint.
//
//  Example 2:
//  Input: [1,4,4], 4
//  Output: 1
//  Explanation: The subarray [4] has the minimal length under the problem constraint.
//
//  Example 3:
//  Input: [1,1,1,1,1,1,1,1], 11
//  Output: 0
//  Explanation: There is no subarray that meets the problem constraint.

//  Solution: Since all numbers are positive, the optimal solution is Sliding Window (Two Pointers).
//  Because all elements are positive, expanding the window always increases the sum.
//  Shrinking the window always decreases the sum.
//  Time	O(n)
//  Space	O(1)

public class MinimumSizeSubarraySum {

  // [2,3,1,2,4,3], 7
  public static int minSubArrayLen(int[] input, int target) {
    int left = 0;
    int sum = 0;
    int minLen = Integer.MAX_VALUE;

    for (int i = 0; i < input.length; i++) {
      sum = sum + input[i];
      // Try to shrink the window
      while (sum >= target) {
        if (sum == target) {
          minLen = Math.min(minLen, i - left + 1);
        }
        sum = sum - input[left++];
      }
    }
    return minLen == Integer.MAX_VALUE ? 0 : minLen;
  }

  public static void main(String[] args) {
    int[] nums = {2, 3, 1, 2, 4, 3};
    int target = 7;

    int result = sol(nums, target);
    System.out.println("Minimum subarray length: " + result);
  }

  public static int sol(int[] input, int target) {
    int result = Integer.MAX_VALUE;
    int sum = 0;
    int left = 0;
    int curWindow = Integer.MAX_VALUE;

    for (int i = 0; i < input.length; i++) {
      sum += input[i];

      while (sum >= target) {
        if (sum == target) {
          curWindow = i - left + 1;
        }
        sum -= input[left++];
      }
      result = Math.min(result, curWindow);
    }
    return result;
  }
}
