package datastructure_algorithms.patterns.arrays.prefixsum;

//  Given an array A of integers, return the number of (contiguous, non-empty) subarrays that have a sum divisible by K.
//
//  Example 1:
//  Input: [4,5,0,-2,-3,1]
//  Output: 7
//  Explanation: There are 7 subarrays with a sum divisible by K = 5: [4,5,0,-2,-3,1], [5], [5,0], [5,0,-2,-3], [0],
//  [0,-2,-3], [-2,-3].
//
//  Example 2:
//  Input: [5]
//  Output: 0
//  Explanation: There is no subarray with a sum divisible by K = 5.

//  Solution: I use prefix sums modulo K. If two prefix sums have the same remainder, the subarray between
//  them is divisible by K. I count remainders using a HashMap.
//  Time	O(n)
//  Space	O(K)

import java.util.HashMap;

public class SubarraySumsDivisibleByK {

  public static int subarraysDivByK(int[] nums, int K) {
    HashMap<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);

    int prefixSum = 0;
    int count = 0;

    for (int num : nums) {
      prefixSum += num;

      int remainder = prefixSum % K;
      if (remainder < 0) {
        remainder = remainder + K;
      }

      count += map.getOrDefault(remainder, 0);

      map.put(remainder, map.getOrDefault(remainder, 0) + 1);
    }

    return count;
  }

  public static void main(String[] args) {
    int[] nums = {4,5,0,-2,-3,1};
    int K = 5;

    System.out.println("Number of subarrays divisible by K -> " + subarraysDivByK(nums, K));
  }
}
