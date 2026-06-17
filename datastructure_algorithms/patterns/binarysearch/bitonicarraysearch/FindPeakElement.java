package datastructure_algorithms.patterns.binarysearch.bitonicarraysearch;

//  A peak element is an element that is strictly greater than its neighbors. Given an integer array nums,
//  find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.
//
//  Example 1:
//  Input: [1,2,3,1]
//  Output: 2
//  Explanation: In this case, 3 is a peak element and your function should return the index number 2.
//
//  Example 2:
//  Input: [1,2,1,3,5,6,4]
//  Output: 5
//  Explanation: In this case, 6 is a peak element and your function should return the index number 5.
//
//  Example 3:
//  Input: [1]
//  Output: 0
//  Explanation: In this case, 1 is a peak element and your function should return the index number 0.

//  Solution: I use binary search by comparing mid with mid+1 and eliminate half the array each time, guaranteeing O(log n) time.
//  Time	O(log n)
//  Space	O(1)

public class FindPeakElement {

  public static int findPeakElement(int[] input) {
    int left = 0;
    int right = input.length - 1;

    while (left < right) {
      int mid = left + (right - left) / 2;

      if (input[mid] < input[mid + 1]) {
        left = mid + 1;   // peak on right side
      } else {
        right = mid;      // peak on left side or mid
      }
    }

    return left;
  }

  public static void main(String[] args) {
    int[] nums1 = {1, 2, 3, 1};
    System.out.println(sol(nums1)); // Output -> 2

    int[] nums2 = {1, 2, 1, 3, 5, 6, 4};
    System.out.println(sol(nums2)); // Output -> 5 (or 1)
  }

  public static int sol(int[] input) {
    int left = 0;
    int right = input.length - 1;

    while (left < right) {

      int mid = left + (right - left) / 2;

      if (input[mid] > input[mid + 1]) {
        right = mid;
      } else {
        left = mid + 1;
      }

    }
    return left;
  }

}
