package datastructure_algorithms.patterns.arrays.prefixsum;

//  Given an array nums and an integer target, return the maximum number of non-empty non-overlapping subarrays
//  such that the sum of values in each subarray is equal to target.
//
//  Example 1:
//  Input: [1,1,1,1,1]
//  Output: 2
//  Explanation: The subarrays are [1,1] and [1,1] which have sum equals to target = 2.
//
//  Example 2:
//  Input: [1,2,3]
//  Output: 0
//  Explanation: There are no subarrays with sum equals to target = 3.

//  Time Complexity -> O(n)
//  Space Complexity -> O(1)

import java.util.HashSet;
import java.util.Set;

public class NonOverlappingSubarrays {


  public static int maxNonOverlapping(int[] input, int target) { // 1, 2, 3, 4, 5
    Set<Integer> prefixSums = new HashSet<>();
    prefixSums.add(0); // to handle subarrays starting at index 0

    int count = 0;
    int prefixSum = 0;

    for (int num : input) {
      prefixSum += num;

      // Check if a valid subarray ending here exists
      if (prefixSums.contains(prefixSum - target)) {
        count++;            // found one non-overlapping subarray
        prefixSum = 0;      // reset prefix sum for next segment
        prefixSums.clear();
        prefixSums.add(0);  // start fresh
      } else {
        prefixSums.add(prefixSum);
      }
    }

    return count;
  }

  public static void main(String[] args) {
    int[] nums1 = {1, 1, 1, 1, 1};
    int target1 = 2;

    int[] nums2 = {1, 2, 3, 4, 5};
    int target2 = 5;

    int[] nums3 = {3, 3, 3, 3, 3, 3};
    int target3 = 6;

    System.out.println(maxNonOverlapping(nums1, target1)); // 2
    System.out.println(maxNonOverlapping(nums2, target2)); // 2
    System.out.println(maxNonOverlapping(nums3, target3)); // 3
  }

  public static int sol(int[] input, int target) {
    int result = 0;
    Set<Integer> temp = new HashSet<>();
    temp.add(0);

    int prefixSum = 0;

    for(int i = 0; i < input.length; i++) {
      prefixSum += input[i];

      if (temp.contains(Math.abs(prefixSum - target))) {
        result++;
        prefixSum = 0;
        temp.clear();
        temp.add(0);
      } else {
        temp.add(prefixSum);
      }
    }

    return result;
  }
}
