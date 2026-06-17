package datastructure_algorithms.patterns.dynamicprogrammimg.substructure;

//  Given an integer array nums, return the length of the longest strictly increasing subsequence.
//  A subsequence is a sequence that can be derived from an array by deleting some or no elements
//  without changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of
// the array [0,3,1,6,2,2,7].
//
//  You need to find the length of the longest subsequence such that:
//  Elements are in strictly increasing order
//  Elements keep their original order
//  You may skip elements
//  You are not asked to return the subsequence, only its length.

//  Example 1:
//  Input: [10,9,2,5,3,7,101,18]
//  Output: 4
//  Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
//
//  Example 2:
//  Input: [0,1,0,3,2,3]
//  Output: 4
//  Explanation: The longest increasing subsequence is [0,1,2,3], therefore the length is 4.
//
//  Example 3:
//  Input: [7,7,7,7,7,7,7]
//  Output: 1
//  Explanation: The longest increasing subsequence is [7], therefore the length is 1.

//  Solution: This is the Patience Sorting + Binary Search DP approach.
//  tails[i] = smallest possible ending value of an increasing subsequence of length (i + 1)

//  For each number x in nums:
//  Use binary search on tails.
//  Find the first index where tails[index] >= x
//  Replace it with x
//  If no such index exists, append x
//  tails is not the actual LIS, Its length is the LIS length.

//  Time -> O(n log n)
//  Space -> O(n)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LongestIncreasingSubsequence {

  public static int lengthOfLIS(int[] input) {
    int[] tails = new int[input.length];
    int size = 0;

    for (int num : input) {
      int left = 0;
      int right = size;

      while (left < right) {
        int mid = left + (right - left) / 2;
        if (tails[mid] < num) {
          left = mid + 1;
        } else {
          right = mid;
        }
      }

      tails[left] = num;
      if (left == size) {
        size++;
      }
    }

    return size;
  }

  public static List<Integer> findLIS(int[] input) {
    int n = input.length;

    int[] tails = new int[n]; // stores indices of smallest tail
    int[] prev = new int[n]; // to reconstruct path
    Arrays.fill(prev, -1);

    int size = 0;

    for (int i = 0; i < n; i++) {
      int left = 0, right = size;

      // Binary search on tails[]
      while (left < right) {
        int mid = left + (right - left) / 2;
        if (input[tails[mid]] < input[i]) {
          left = mid + 1;
        } else {
          right = mid;
        }
      }

      // Link to previous element
      if (left > 0) {
        prev[i] = tails[left - 1];
      }

      tails[left] = i;

      if (left == size) {
        size++;
      }
    }

    // Reconstruct LIS
    List<Integer> result = new ArrayList<>();
    int index = tails[size - 1];

    while (index != -1) {
      result.add(input[index]);
      index = prev[index];
    }

    Collections.reverse(result);
    return result;
  }

  public static void main(String[] args) {
    int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
    int result = lengthOfLIS(nums);
    System.out.println("Length of LIS -> " + result);

    List<Integer> result1 = findLIS(nums);

    System.out.println("LIS Length: " + result1.size());
    System.out.println("LIS Sequence: " + result1);
  }
}
