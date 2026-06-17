package datastructure_algorithms.patterns.binarysearch.countingoccurance;

//  Given an array of integers nums sorted in non-decreasing order, find the starting and ending
// position of a given target value.
//  If target is not found in the array, return [-1, -1]. You must write an algorithm with O(log n)
// runtime complexity.
//
//  Example 1:
//  Input: [5,7,7,8,8,10], 8
//  Output: [3,4]
//  Explanation: The value 8 is found at indices 3 and 4.
//
//  Example 2:
//  Input: [5,7,7,8,8,10], 6
//  Output: [-1,-1]
//  Explanation: The value 6 is not found in the array.
//
//  Example 3:
//  Input: [], 0
//  Output: [-1,-1]
//  Explanation: The array is empty, so no value is found.

//  Solution: Because the array is sorted, we use binary search.
//  Time	O(log n)
//  Space	O(1)

public class FindFirstAndLastPosition {

  public static int[] searchRange(int[] input, int target) {
    int first = findFirst(input, target);
    int last = findLast(input, target);
    return new int[] {first, last};
  }

  private static int findFirst(int[] input, int target) {
    int left = 0, right = input.length - 1;
    int result = -1;

    while (left <= right) {
      int mid = left + (right - left) / 2;

      if (input[mid] == target) {
        result = mid;
        right = mid - 1; // move left
      } else if (input[mid] < target) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    return result;
  }

  private static int findLast(int[] input, int target) {
    int left = 0, right = input.length - 1;
    int result = -1;

    while (left <= right) {
      int mid = left + (right - left) / 2;

      if (input[mid] == target) {
        result = mid;
        left = mid + 1; // move right
      } else if (input[mid] < target) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    return result;
  }

  public static void main(String[] args) {
    int[] nums = {5, 7, 7, 8, 8, 10};
    int target = 10;
    int[] result = sol(nums, target);

    System.out.println("[" + result[0] + ", " + result[1] + "]");
  }

  public static int[] sol(int[] input, int target) {
    int left = 0;
    int right = input.length - 1;

    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (input[mid] == target) {
        if (mid + 1 < input.length && input[mid] == input[mid + 1]) {
          return new int[] {mid, mid + 1};
        } else if (mid - 1 >= 0 && input[mid] == input[mid - 1]) {
          return new int[] {mid - 1, mid};
        } else {
          return new int[] {mid, -1};
        }
      } else if (input[mid] > target) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }

    return new int[] {-1, -1};
  }
}
