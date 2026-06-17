package datastructure_algorithms.patterns.dynamicprogrammimg.prefixsum;

//  Given an integer array nums, find the sum of the elements between indices i and j (i ? j), inclusive.
//
//  Example 1:
//  Input: nums = [-2, 0, 3, -5, 2, -1], i = 0, j = 2
//  Output: 1
//  Explanation: The sum range between indices 0 and 2 is (-2 + 0 + 3) = 1.
//
//  Example 2:
//  Input: nums = [-2, 0, 3, -5, 2, -1], i = 2, j = 5
//  Output: -1
//  Explanation: The sum range between indices 2 and 5 is (3 + (-5) + 2 + (-1)) = -1.

//  Preprocessing	O(n)
//  Query	O(1)
//  Space	O(n)

public class RangeSumQueryImmutable {

  static class NumArray {
    private int[] dp;

    public NumArray(int[] input) {
      dp = new int[input.length];
      dp[0] = input[0];

      for (int i = 1; i < input.length; i++) {
        dp[i] = dp[i - 1] + input[i];
      }
    }

    public int sumRange(int i, int j) {
      if (i == 0) {
        return dp[j];
      }
      return dp[j] - dp[i - 1];
    }
  }

  public static void main(String[] args) {
    int[] nums = {1, 3, 5, 7, 9};

    NumArray obj = new NumArray(nums);

    System.out.println("Sum (0 -> 2) -> " + obj.sumRange(0, 2));
    System.out.println("Sum (1 -> 3) -> " + obj.sumRange(1, 3));
    System.out.println("Sum (2 -> 4) -> " + obj.sumRange(2, 4));
  }
}
