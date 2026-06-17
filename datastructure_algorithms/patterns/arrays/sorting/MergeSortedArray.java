package datastructure_algorithms.patterns.arrays.sorting;

//  You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two
// integers m and n,
//  representing the number of elements in nums1 and nums2 respectively. Merge nums1 and nums2 into
// a single array
//  sorted in non-decreasing order. The final sorted array should not be returned, but instead be
// stored inside
//  the array nums1. To accommodate this, nums1 has a length of m + n, where the first m elements
// denote the elements
//  that should be merged, and the last n elements are set to 0 and should be ignored. nums2 has a
// length of n.
//
//  Example 1:
//  Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
//  Output: [1,2,2,3,5,6]
//  Explanation: Merge nums1 and nums2 into a single array such that the resulting array is
// [1,2,2,3,5,6].
//
//  Example 2:
//  Input: nums1 = [1], m = 1, nums2 = [], n = 0
//  Output: [1]
//  Explanation: Merge nums1 and nums2 into a single array such that the resulting array is [1].
//
//  Example 3:
//  Input: nums1 = [0], m = 0, nums2 = [1], n = 1
//  Output: [1]
//  Explanation: Merge nums1 and nums2 into a single array such that the resulting array is [1].

import java.util.Arrays;

//  Solution: “We merge from the end to avoid overwriting elements.”
public class MergeSortedArray {

  public static void merge(int[] nums1, int m, int[] nums2, int n) {
    int i = m - 1; // pointer for nums1
    int j = n - 1; // pointer for nums2
    int k = m + n - 1; // pointer for merged array

    // Merge from the back
    while (i >= 0 && j >= 0) {
      if (nums1[i] > nums2[j]) {
        nums1[k--] = nums1[i--];
      } else {
        nums1[k--] = nums2[j--];
      }
    }

    // Copy remaining elements of nums2 if any
    while (j >= 0) {
      nums1[k--] = nums2[j--];
    }
  }

  public static void main(String[] args) {
    int[] nums1 = {1, 2, 3, 0, 0, 0};
    int m = 3;
    int[] nums2 = {2, 5, 6};
    int n = 3;
    merge(nums1, m, nums2, n);
    System.out.println("Merged array: " + Arrays.toString(nums1));
  }

  public static int[] sol(int[] input1, int m, int[] input2, int n) {
    int i = m - 1;
    int j = n - 1;

    int k = m + n - 1;

    while (i >= 0 && j >= 0) {
      if (input1[i] > input2[j]) {
        input1[k--] = input1[i--];
      } else {
        input1[k--] = input2[j--];
      }
    }

    while (i >= 0) {
      input1[k--] = input1[i--];
    }
    while (j >= 0) {
      input1[k--] = input2[j--];
    }

    return input1;
  }
}
