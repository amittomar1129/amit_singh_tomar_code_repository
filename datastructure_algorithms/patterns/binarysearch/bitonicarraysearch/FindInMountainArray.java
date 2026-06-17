package datastructure_algorithms.patterns.binarysearch.bitonicarraysearch;

//  You may recall that an array A is a mountain array if and only if: A.length >= 3, there exists
//  some i with 0 < i < A.length - 1 such that: A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1].
//  Given a mountain array mountainArr, return the index such that target is found in mountainArr, or return -1 if target is not found.
//
//  Example 1:
//  Input: mountainArr = [1, 2, 3, 4, 5, 3, 1], target = 3
//  Output: 2
//  Explanation: 3 exists in the mountain array, where 3 is at index 2.
//
//  Example 2:
//  Input: mountainArr = [0, 5, 3, 1], target = 1
//  Output: 3
//  Explanation: 1 exists in the mountain array, where 1 is at index 3.
//
//  Example 3:
//  Input: mountainArr = [1, 2, 3, 4, 5, 3, 1], target = 2
//  Output: 1
//  Explanation: 2 exists in the mountain array, where 2 is at index 1.

//  Solution: I first find the peak using binary search, then perform binary search on the
//  increasing and decreasing halves separately.
//  Total	O(log n)
//  Space -> O(1)
public class FindInMountainArray {

  public static int findInMountainArray(int[] input, int target) {
    int peak = findPeak(input);

    // Search in increasing part
    int index = binarySearchAsc(input, target, 0, peak);
    if (index != -1) {
      return index;
    }

    // Search in decreasing part
    return binarySearchDesc(input, target, peak + 1, input.length - 1);
  }

  private static int findPeak(int[] input) {
    int left = 0, right = input.length - 1;

    while (left < right) {
      int mid = left + (right - left) / 2;

      if (input[mid] < input[mid + 1]) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }
    return left; // peak index
  }

  private static int binarySearchAsc(int[] input, int target, int left, int right) {
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
    return -1;
  }

  private static int binarySearchDesc(int[] input, int target, int left, int right) {
    while (left <= right) {
      int mid = left + (right - left) / 2;

      if (input[mid] == target) {
        return mid;
      } else if (input[mid] > target) {
        left = mid + 1; // reversed
      } else {
        right = mid - 1;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    int[] mountainArr = {1, 2, 3, 4, 5, 3, 1};
    int target = 3;
    System.out.println(sol(mountainArr, target)); // Output -> 2 or 5
  }

  public static int sol(int[] input, int target) {
    int peekIndex = fPeek(input);

    int index = sInUpMountain(input, 0, peekIndex, target);
    if (index != -1) {
      return index;
    }
    return sInDownMountain(input, peekIndex, input.length - 1, target);
  }

  public static int sInUpMountain(int[] input, int left, int right, int target) {

    while (left < right) {
      int mid = left + (right - left) / 2;

      if (input[mid] == target) {
        return mid;
      } else if(input[mid] < target){
        left = mid + 1;
      } else {
        right = mid;
      }
    }

    return -1;
  }

  public static int sInDownMountain(int[] input, int left, int right, int target) {

    while (left < right) {
      int mid = left + (right - left) / 2;

      if (input[mid] == target) {
        return mid;
      } else if(input[mid] < target){
        right = mid;
      } else {
        left = mid + 1;
      }
    }

    return -1;
  }

  public static int fPeek(int[] input) {
    int left = 0;
    int right = input.length - 1;

    while (left < right) {

      int mid = left + (right - left) / 2;

      if (input[mid] < input[mid + 1]) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }

    return left;
  }























}
