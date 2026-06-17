package datastructure_algorithms.patterns.arrays.slidingwindow;

//  Given an unsorted array of integers, find the length of longest continuous increasing subsequence (subarray).
//
//  Example 1:
//  Input: [1,3,5,4,7]
//  Output: 3
//  Explanation: The longest continuous increasing subsequence is [1,3,5] with length 3.
//
//  Example 2:
//  Input: [2,2,2,2,2]
//  Output: 1
//  Explanation: The longest continuous increasing subsequence is [2] with length 1.
//
//  Example 3:
//  Input: [1,3,5,7]
//  Output: 4
//  Explanation: The entire array is a continuous increasing subsequence, so the length is 4.

//  Solution: Maintain: currLen -> current increasing subarray length, maxLen -> longest seen so far.
//  Time	O(n)
//  Space	O(1)

public class LongestContinuousIncreasingSubsequence {

  public static int findLengthOfLCIS(int[] input) {
    if (input == null || input.length == 0) {
      return 0;
    }
    int maxLen = 1;
    int currLen = 1;
    for (int i = 1; i < input.length; i++) {
      if (input[i] > input[i - 1]) {
        currLen++;
      } else {
        currLen = 1;
      }
      maxLen = Math.max(maxLen, currLen);
    }
    return maxLen;
  }

  public static void main(String[] args) {
    int[] nums = {1, 3, 5, 4, 7};
    System.out.println("Longest continuous increasing subarray length: "
        + findLengthOfLCIS(nums));
  }


  public static int sol(int[]  input) {
    int maxLength = 1;
    int curLength = 1;

    for(int i = 1; i < input.length; i++) {
      if (input[i] > input[i-1]) {
        curLength++;
      } else {
        curLength = 1;
      }
      maxLength = Math.max(maxLength, curLength);
    }

    return  maxLength;
  }








}
