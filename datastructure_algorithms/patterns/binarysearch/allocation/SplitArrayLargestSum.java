package datastructure_algorithms.patterns.binarysearch.allocation;

//  Given an array nums which consists of non-negative integers and an integer m, you can split the
// array into m non-empty
//  continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.
//
//  Example 1:
//  Input: [7,2,5,10,8]
//  Output: 18
//  Explanation: There are four ways to split nums into two subarrays. The best way is to split it
// into [7,2,5] and [10,8], where the largest sum among the two subarrays is only 18.
//
//  Example 2:
//  Input: [1,2,3,4,5]
//  Output: 9
//  Explanation: If you split the array into [1,2,3] and [4,5], the largest sum among the two
// subarrays is only 9.
//
//  Example 3:
//  Input: [1,4,4]
//  Output: 4
//  Explanation: It is not possible to split the array into multiple subarrays such that the largest
// sum among them is 4.

//  Time	O(n log(sum(nums)))
//  Space	O(1)

public class SplitArrayLargestSum {

  //  {7, 2, 5, 10, 8}
  public static int splitArray(int[] input, int m) {
    int left = 0;
    int right = 0;

    for (int num : input) {
      left = Math.max(left, num); // max element
      right += num; // sum of array
    }

    while (left < right) {
      int mid = left + (right - left) / 2;

      if (canSplit(input, m, mid)) {
        right = mid; // try smaller largest sum
      } else {
        left = mid + 1;
      }
    }

    return left;
  }

  private static boolean canSplit(int[] nums, int m, int maxSum) {
    int subarrays = 1;
    int currentSum = 0;

    for (int num : nums) {
      currentSum += num;

      if (currentSum > maxSum) {
        subarrays++;
        currentSum = num;

        if (subarrays > m) {
          return false;
        }
      }
    }

    return true;
  }

  public static void main(String[] args) {
    int[] nums = {7, 2, 5, 10, 8};
    int m = 2;
    System.out.println(splitArray(nums, m)); // 18
  }

  public static int sol(int[] input, int m) {
    int left = Integer.MAX_VALUE;
    int total = 0;

    for (int num : input) {
      total += num;
      left = Math.min(left, num);
    }

    int right = total;

    while (left < right) {
      int mid = left + (right - left) / 2;
      if (canSplitAll(input, m, mid)) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }

    return left;
  }

  //  {7, 2, 5, 10, 8}
  private static boolean canSplitAll(int[] input, int m, int mid) {
    int total = 0;
    int no = 1;

    for (int num : input) {
      total += num;
      if (total > mid) {
        no++;
        total = num;
      }
    }

    return no <= m;
  }
}
