package datastructure_algorithms.patterns.graph.shortestpath;

//  You are given an integer array nums and an integer x. In one operation, you can either remove the
//  leftmost or the rightmost element from the array nums and subtract x from x.
//  Return the minimum number of operations to reduce x to exactly 0 if it is possible, otherwise, return -1.
//  Note that removing an element means the array is shortened by one from the respective end.
//
//  Example 1:
//  Input: nums = [1,1,4,2,3], x = 5
//  Output: 2
//  Explanation: Remove the last two elements (3 and 2) to reduce x to zero.
//
//  Example 2:
//  Input: nums = [5,6,7,8,9], x = 4
//  Output: -1
//  Explanation: It is not possible to reduce x to zero by removing elements from either end.
//
//  Example 3:
//  Input: nums = [3,2,20,1,1,3], x = 10
//  Output: 5
//  Explanation: Remove the first three elements and the last two elements to reduce x to zero.

//  Solution:
//  Input:
//      nums = [1,1,4,2,3], x = 5
//      total = 11
//      target = total - x = 6
//  Longest subarray sum = 6 ? [4,2] (length = 2)
//  Minimum operations = n - length = 5 - 2 = 3 ?
//  Operations:
//  Remove left 1,1 (2 ops)
//  Remove right 3 (1 op)
//  Total = 3

import java.util.HashMap;
import java.util.Map;

public class MinCost {

  public static int minOperations(int[] nums, int x) {
    int n = nums.length;
    int total = 0;
    for (int num : nums) {
      total += num;
    }

    int target = total - x;
    if (target < 0) {
      return -1;
    }
    if (target == 0) {
      return n; // remove all elements
    }

    Map<Integer, Integer> prefixSum = new HashMap<>();
    prefixSum.put(0, -1); // sum 0 at index -1

    int sum = 0;
    int maxLen = -1;

    for (int i = 0; i < n; i++) {
      sum += nums[i];
      if (prefixSum.containsKey(sum - target)) {
        maxLen = Math.max(maxLen, i - prefixSum.get(sum - target));
      }
      prefixSum.put(sum, i);
    }

    return maxLen == -1 ? -1 : n - maxLen;
  }

  public static void main(String[] args) {
    int[] nums = {1, 1, 4, 2, 3};
    int x = 5;

    System.out.println("Minimum Operations -> " + minOperations(nums, x));
  }
}
