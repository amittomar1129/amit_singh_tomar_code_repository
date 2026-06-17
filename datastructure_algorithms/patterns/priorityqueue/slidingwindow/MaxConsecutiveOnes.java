package datastructure_algorithms.patterns.priorityqueue.slidingwindow;

//  Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the
//  array if you can flip at most k 0's.

//  You are given:
//  nums ? binary array (0’s and 1’s)
//  k ? max number of 0’s you can flip to 1
//  You want:
//  The longest contiguous subarray that contains all 1’s after flipping at most k zeros.

//
//  Example 1:
//  Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
//  Output: 6
//  Explanation: Flip the two zeros at positions 5 and 10 to get the longest subarray of 1s of length 6.
//
//  Example 2:
//  Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
//  Output: 10
//  Explanation: Flip three zeros to get the longest subarray of 1s of length 10.

//  Solution: I maintain a variable-size sliding window. I expand it while flipping at most k zeros.
//  If zeros exceed k, I shrink the left pointer. At every step, the window length gives the max consecutive ones.
//  This is O(n) time and O(1) space.
//  Time	O(n) each element visited once
//  Space	O(1) constant extra space

public class MaxConsecutiveOnes {

  public static int longestOnes(int[] nums, int k) {
    int left = 0;
    int zerosCount = 0;
    int maxLength = 0;

    for (int right = 0; right < nums.length; right++) {

      if (nums[right] == 0) zerosCount++;

      while (zerosCount > k) {
        if (nums[left] == 0) zerosCount--;
        left++;
      }

      maxLength = Math.max(maxLength, right - left + 1);
    }

    return maxLength;
  }

  public static void main(String[] args) {

    int[] nums = {1, 1, 0, 0, 1, 1, 1, 0, 1};
    int k = 2;

    int result = longestOnes(nums, k);
    System.out.println("Maximum consecutive 1's -> " + result);
  }
}
