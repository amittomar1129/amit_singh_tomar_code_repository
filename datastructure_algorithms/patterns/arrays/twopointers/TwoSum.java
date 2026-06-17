package datastructure_algorithms.patterns.arrays.twopointers;

//  Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
//  You may assume that each input would have exactly one solution, and you may not use the same element twice.
//  You can return the answer in any order.

//  Example 1:
//  Input: [2,7,11,15], 9
//  Output: [0,1]
//  Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
//
//  Example 2:
//  Input: [3,2,4], 6
//  Output: [1,2]
//  Explanation: Because nums[1] + nums[2] == 6, we return [1, 2].
//
//  Example 3:
//  Input: [3,3], 6
//  Output: [0,1]
//  Explanation: Because nums[0] + nums[1] == 6, we return [0, 1].

//  Solution: This is a Single-pass hash lookup problem.
//  We trade space for time to achieve O(n) time complexity, which is optimal.
//  Optimal Approach: One-Pass HashMap. Why this is the best choice?
//  Time: O(n) — cannot do better
//  Space: O(n) — acceptable trade-off
//  ---------------------------------------------------------------------

//  If array is already sorted?
//  Solution: We can use two pointers. Start with: left at beginning, right at end. Move pointers based on current sum
//  Time: O(n) — each pointer moves at most n times
//  Space: O(1) — no extra data structures


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

  public int[] twoSum(int[] input, int target) {
    Map<Integer, Integer> seen = new HashMap<>();
    for (int i = 0; i < input.length; i++) {
      int complement = target - input[i];
      if (seen.containsKey(complement)) {
        return new int[]{seen.get(complement), i};
      }
      seen.put(input[i], i);
    }
    throw new IllegalArgumentException("No two sum solution");
  }

  public int[] twoSumWithSortedArray(int[] array, int target) {
    int left = 0;
    int right = array.length - 1;
    while (left < right) {
      int sum = array[left] + array[right];

      if (sum == target) {
        return new int[]{left, right};
      } else if (sum < target) {
        left++;   // need a larger sum
      } else {
        right--;  // need a smaller sum
      }
    }
    // Guaranteed one solution, but defensive coding
    throw new IllegalArgumentException("No two sum solution");
  }

  public static void main(String[] args) {
    TwoSum twoSum = new TwoSum();
    int[] result = twoSum.sol(new int[]{2, 3, 2, 4}, 6);
    Arrays.stream(result).forEach(System.out::println);

    int[] result1 = twoSum.twoSumWithSortedArray(new int[]{1, 2, 3, 4}, 6);
    Arrays.stream(result1).forEach(System.out::println);
  }

  public int[] sol(int[] input, int target) {
    Map<Integer, Integer> map = new HashMap<>();

    for(int i = 0; i < input.length; i++) {
      int diff = Math.abs(target - input[i]);

      if (map.containsKey(input[i])) {
        return new int[]{map.get(input[i]), i};
      }

      map.put(diff, i);
    }
    return null;
  }




















}
