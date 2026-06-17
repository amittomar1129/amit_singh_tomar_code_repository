package datastructure_algorithms.patterns.arrays.twopointers;

import java.util.Arrays;

//  The pair sum of a pair (a,b) is equal to a + b. The maximum pair sum is the largest pair sum in a list of pairs.
//  For example, if we have pairs (1,2), (2,3), and (4,5), the maximum pair sum would be max(1+2, 2+3, 4+5) = 9.
//  Given an array nums of even length n, pair up the elements of nums into n/2 pairs such that the maximum pair sum is minimized.
//  Return the minimized maximum pair sum.
//
//  Example 1:
//  Input: [3,5,2,3]
//  Output: 7
//  Explanation: The minimized maximum pair sum would be max(3+5, 2+3) = 7.

//
//  Example 2:
//  Input: [3,5,4,2,4,6]
//  Output: 8
//  Explanation: The minimized maximum pair sum would be max(3+5, 4+4, 2+6) = 8.
//
//  Example 3:
//  Input: [1,2,3,4,5,6]
//  Output: 7
//  Explanation: The minimized maximum pair sum would be max(1+6, 2+5, 3+4) = 7.

public class MinimizeMaximumPairSum {

  public static int minPairSum(int[] input) {
    Arrays.sort(input);

    int left = 0;
    int right = input.length - 1;
    int maxPairSum = 0;

    while (left < right) {
      int currentSum = input[left] + input[right];
      maxPairSum = Math.max(maxPairSum, currentSum);
      left++;
      right--;
    }

    return maxPairSum;
  }

  public static void main(String[] args) {
    int[] nums = {1, 5, 6, 7};
    System.out.println(minPairSum(nums)); // Output -> 11
  }
}
