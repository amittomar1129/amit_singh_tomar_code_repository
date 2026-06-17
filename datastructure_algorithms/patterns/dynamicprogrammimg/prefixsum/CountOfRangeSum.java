package datastructure_algorithms.patterns.dynamicprogrammimg.prefixsum;

//  Given an integer array nums, return the number of range sums that lie in [lower, upper]
// inclusive.
//  Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ? j),
// inclusive.

//  You are given:
//      An integer array nums
//      Two integers: lower and upper
//  You must count:
//      How many subarrays have sum between lower and upper (inclusive).

//  Example 1:
//  Input: nums = [-2,5,-1], lower = -2, upper = 2
//  Output: 3
//  Explanation: The three ranges are: [-2], [-2,5,-1], and [-1] and
//  their respective sums are: -2, 2, -1.
//
//  Example 2:
//  Input: nums = [0], lower = 0, upper = 0
//  Output: 1
//  Explanation: The only range is [0,0] and its sum is 0.

//  Solution: I convert the problem to prefix sums, then use merge sort to count valid ranges in O(n
// log n)
//  time by leveraging sorted prefix differences.
//  Time	O(n log n)
//  Space	O(n)

//  Explanation:
//  prefix [0, -2, 3, 2]
//  We want, lower <= prefix[j] - prefix[i] <= upper
//  So, -2 <= prefix[j] - prefix[i] <= 2
//  Start Merge Sort, mergeSort(prefix, 0, 3)
//  Mid = 1,  Split into: Left: [0, -2]  Right: [3, 2]
//  Sort both left and right, Left: [-2, 0]  Right: [2, 3]
//  Count Cross Pairs: We now count valid pairs: For each element in Right, find how many in Left
// satisfy.

//  lower <= prefix[j] - prefix[i] <= upper
// Rearrange:
// prefix[j] - upper <= prefix[i] <= prefix[j] - lower

import java.util.Arrays;

public class CountOfRangeSum {

  public static int countRangeSum(int[] input, int lower, int upper) {
    long[] prefix = new long[input.length + 1];

    for (int i = 1; i <= input.length; i++) {
      prefix[i] = prefix[i - 1] + input[i - 1];
    }

    return mergeSort(prefix, 0, prefix.length - 1, lower, upper);
  }

  private static int mergeSort(long[] prefix, int left, int right, int lower, int upper) {
    if (left >= right) {
      return 0;
    }

    int mid = left + (right - left) / 2;
    int count = 0;

    count += mergeSort(prefix, left, mid, lower, upper);
    count += mergeSort(prefix, mid + 1, right, lower, upper);

    int l = left, r = left;

    for (int i = mid + 1; i <= right; i++) {
      while (l <= mid && prefix[l] < prefix[i] - upper) {
        l++;
      }
      while (r <= mid && prefix[r] <= prefix[i] - lower) {
        r++;
      }
      count += (r - l);
    }

    Arrays.sort(prefix, left, right + 1);
    return count;
  }

  public static void main(String[] args) {
    int[] nums = {-2, 5, -1};
    int lower = -2;
    int upper = 2;

    System.out.println("Number of valid range sums -> " + countRangeSum(nums, lower, upper));
  }







}
