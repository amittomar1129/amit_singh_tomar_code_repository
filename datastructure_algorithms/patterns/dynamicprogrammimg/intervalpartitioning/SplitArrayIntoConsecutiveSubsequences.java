package datastructure_algorithms.patterns.dynamicprogrammimg.intervalpartitioning;

//  You are given an integer array nums that is sorted in ascending order (not necessarily
// distinct).
//  Return true if and only if you can split it into one or more subsequences such that both of the
// following conditions are true:
//      - Each subsequence is a consecutive increasing sequence (i.e. each integer is exactly one
// more than the previous integer).
//      - All subsequences have a length of 3 or more.
//  Return false otherwise.
//
//  Example 1:
//  Input: nums = [1,2,3,3,4,5]
//  Output: true
//  Explanation: You can split them into two consecutive subsequences : 1, 2, 3 and 3, 4, 5
//
//  Example 2:
//  Input: nums = [1,2,3,3,4,4,5,5]
//  Output: true
//  Explanation: You can split them into two consecutive subsequences : 1, 2, 3, 4, 5 and 3, 4, 5

//  Time Complexity -> O(n)
//  Each number is processed once.
//  Space Complexity -> O(n)

import java.util.HashMap;
import java.util.Map;

public class SplitArrayIntoConsecutiveSubsequences {

  public static boolean isPossible(int[] input) {
    Map<Integer, Integer> freq = new HashMap<>();
    Map<Integer, Integer> tails = new HashMap<>();

    // Count frequency of each number  {1, 2, 3, 3, 4, 4, 5, 5}
    for (int num : input) {
      freq.put(num, freq.getOrDefault(num, 0) + 1);
    }

    for (int num : input) {
      if (freq.get(num) == 0) {
        continue; // already used
      }

      freq.put(num, freq.get(num) - 1);

      // Append to existing subsequence ending with num-1
      if (tails.getOrDefault(num - 1, 0) > 0) {
        tails.put(num - 1, tails.get(num - 1) - 1);
        tails.put(num, tails.getOrDefault(num, 0) + 1);
      }
      // Create new subsequence num, num+1, num+2
      else if (freq.getOrDefault(num + 1, 0) > 0 && freq.getOrDefault(num + 2, 0) > 0) {
        freq.put(num + 1, freq.get(num + 1) - 1);
        freq.put(num + 2, freq.get(num + 2) - 1);
        tails.put(num + 2, tails.getOrDefault(num + 2, 0) + 1);
      } else {
        return false; // cannot place num
      }
    }

    return true;
  }

  public static void main(String[] args) {
    int[] nums1 = {1, 2, 3, 3, 4, 5};
    int[] nums2 = {1, 2, 3, 3, 4, 4, 5, 5};
    int[] nums3 = {1, 2, 3, 4, 4, 5};

    System.out.println(isPossible(nums1)); // true
    System.out.println(isPossible(nums2)); // true
    System.out.println(isPossible(nums3)); // false
  }
}
