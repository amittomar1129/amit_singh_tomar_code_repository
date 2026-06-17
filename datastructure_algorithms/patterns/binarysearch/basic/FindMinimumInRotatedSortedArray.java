package datastructure_algorithms.patterns.binarysearch.basic;

//  Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
//  Find the minimum element. You may assume no duplicate exists in the array.
//

//  Example 1:
//  Input: [3,4,5,1,2]
//  Output: 1
//  Explanation: The minimum element is 1.
//
//  Example 2:
//  Input: [4,5,6,7,0,1,2]
//  Output: 0
//  Explanation: The minimum element is 0.
//
//  Example 3:
//  Input: [11,13,15,17]
//  Output: 11
//  Explanation: The minimum element is 11. (Missing example from LeetCode)

//  Solution: Even after rotation:
//  One half of the array is always sorted.
//  The minimum lies in the unsorted half.
//  Time	O(log n)
//  Space	O(1)

public class FindMinimumInRotatedSortedArray {

  public static int findMin(int[] input) {
    int left = 0;
    int right = input.length - 1;

    while (left < right) {
      int mid = left + (right - left) / 2;

      // Minimum is in the right half
      if (input[mid] > input[right]) {
        left = mid + 1;
      }
      // Minimum is in the left half (including mid)
      else {
        right = mid;
      }
    }
    // left == right -> minimum element
    return input[left];
  }

  public static void main(String[] args) {
    int[] nums = {4, 5, 6, 7, 0, 1, 2};
    System.out.println(findMin(nums)); // 0
  }

  public static int sol(int[] input) {
    int left = input[0];
    int right = input[input.length - 1];

    while (left <= right) {
      int mid = left + (right - left) / 2;

      if (left < input[mid]) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }

    return input[left];
  }
}
