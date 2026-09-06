package datastructure_algorithms.patterns.arrays.prefixsum;

//  Given an array of integers nums and an integer k, return the total number of continuous
// subarrays whose sum equals to k.
//
//  Example 1:
//  Input: [1,1,1], 2
//  Output: 2
//  Explanation: There are two subarrays that sum up to 2: [1,1] and [1,1].
//
//  Example 2:
//  Input: [1,2,3], 3
//  Output: 2
//  Explanation: There are two subarrays that sum up to 3: [1,2] and [3].

//  Solution: I use prefix sums and a HashMap. For each prefix sum, I check how many times
// (prefixSum - k) occurred before.
//  That gives the number of valid subarrays ending at this index.
//  Time	O(n)
//  Space	O(n)

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {

  public static int subarraySum(int[] nums, int k) {
    HashMap<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);

    int prefixSum = 0;
    int count = 0;

    for (int num : nums) {
      prefixSum += num;

      count = count + map.getOrDefault(prefixSum - k, 0);

      map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
    }

    return count;
  }

  public static void main(String[] args) {
    int[] nums = {1, 1, 1, 4, 5, 6, 1, 3, 5, 2, 1, 1, 2, 3};
    int k = 9;

    int[] nums1 = {3, 3, 3, 3, 3, 3};
    int k1 = 6;

    System.out.println("Total subarrays with sum = k -> " + subarraySum(nums, k));
    System.out.println("Total subarrays with sum = k -> " + subarraySum(nums1, k1));
  }


  public static int sol(int[] input, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);
    int count = 0;
    int prefixSum = 0;

    for(int i = 0; i < input.length; i++) {
      prefixSum += input[i];
      count += map.getOrDefault(prefixSum - target, 0);
      map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
    }

    return count;
  }






}
