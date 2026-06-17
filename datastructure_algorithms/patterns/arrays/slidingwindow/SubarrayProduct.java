package datastructure_algorithms.patterns.arrays.slidingwindow;

//  Your are given an array of positive integers nums. Count and return the number of subarrays where the product
//  of all the elements in the subarray is less than k.
//
//  Example 1:
//  Input: nums = [10, 5, 2, 6], k = 100
//  Output: 8
//  Explanation: The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
//
//  Example 2:
//  Input: nums = [1, 2, 3], k = 0
//  Output: 0
//  Explanation: There is no subarray with product less than 0.
//
//  Example 3:
//  Input: nums = [1, 1, 1], k = 1
//  Output: 0
//  Explanation: The product of all numbers in the array is 1, which is not less than 1.

//  Solution: Sliding Window
//  Time	O(n)
//  Space	O(1)

public class SubarrayProduct {

  public static int numSubarrayProductLessThanK(int[] input, int k) {
    if (k <= 1) {
      return 0;
    }
    int left = 0;
    long product = 1;
    int count = 0;

    for (int i = 0; i < input.length; i++) {
      product = product * input[i];
      while (product >= k) {
        product = product / input[left++];
      }
      count = count + (i - left + 1);
    }
    return count;
  }

  public static void main(String[] args) {
    int[] nums = { 10, 5, 2, 6};
    int k = 100;
    System.out.println(numSubarrayProductLessThanK(nums, k));
  }



















}
