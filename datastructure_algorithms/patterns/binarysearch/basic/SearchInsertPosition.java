package datastructure_algorithms.patterns.binarysearch.basic;

//  Given a sorted array and a target value, return the index if the target is found. If not, return
//  the index where it would be if it were inserted in order.
//
//  Example 1:
//  Input: [1,3,5,6], 5
//  Output: 2
//  Explanation: The target '5' is found at index '2'.
//
//  Example 2:
//  Input: [1,3,5,6], 2
//  Output: 1
//  Explanation: The target '2' is not found, so the index where it would be inserted is '1'.
//
//  Example 3:
//  Input: [1,3,5,6], 7
//  Output: 4
//  Explanation: The target '7' is not found, so the index where it would be inserted is '4'.

//  Solution: Because the array is sorted, we use Binary Search.
//  Time	O(log n)
//  Space	O(1)

public class SearchInsertPosition {

  public static int searchInsert(int[] input, int target) {
    int left = 0;
    int right = input.length - 1;

    while (left <= right) {
      int mid = left + (right - left) / 2;

      if (input[mid] == target) {
        return mid;
      } else if (input[mid] < target) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    // left is the correct insert position
    return left;
  }

  public static void main(String[] args) {
    int[] nums = {1, 3, 5, 6};
    System.out.println(sol(nums, 5)); // 2
    System.out.println(sol(nums, 2)); // 1
    System.out.println(sol(nums, 7)); // 4
    System.out.println(sol(nums, 0)); // 0
  }

  public static int sol(int[] input, int target) {
    int left = 0;
    int right = input.length - 1;

    while (left <= right) {
      int mid = left + (right - left) / 2;

      if (input[mid] < target) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    return left;
  }




















}
