package datastructure_algorithms.patterns.arrays.twopointers;

//  Given a sorted array nums, remove the duplicates in-place such that each element appears only
// once
//  and returns the new length. Do not allocate extra space for another array; you must do this
//  by modifying the input array in-place with O(1) extra memory.

//  Example 1:
//  Input: [1,1,2]
//  Output: 2
//  Explanation: Your function should return length = 2, with the first two elements of nums being 1
// and 2 respectively.
//  It doesn't matter what you leave beyond the returned length.
//
//  Example 2:
//  Input: [0,0,1,1,1,2,2,3,3,4]
//  Output: 5
//  Explanation: Your function should return length = 5, with the first five elements of nums being
// modified to 0, 1, 2, 3, and 4
//  respectively. It doesn't matter what values are set beyond the returned length.
//
//  Example 3:
//  Input: [1,1,1]
//  Output: 1
//  Explanation: Your function should return length = 1, with the first element of nums being 1. It
// doesn't matter what
//  you leave beyond the returned length.

//  Solution: Because the array is already sorted, all duplicates are adjacent.
//  We can keep one pointer for the last unique position and another for scanning.
//  Time: O(n)
//  Space: O(1) extra space

import java.util.Arrays;
import java.util.List;

public class SortedArray {

  public int removeDuplicatesFromSortedArray(int[] input) {
    if (input.length == 0) {
      return 0;
    }
    int left = 1; // index of last unique element
    for (int i = 1; i < input.length; i++) {
      if (input[i] != input[i - 1]) {
        input[left] = input[i];
        left++;
      }
    }
    return left;
  }

  //  Given an integer array nums sorted in non-decreasing order, return an array of the squares of
  // each number
  //  sorted in non-decreasing order.
  //    Example 1:
  //    Input: [-4,-1,0,3,10]
  //    Output: [0,1,9,16,100]
  //    Explanation: After squaring, the array becomes [16,1,0,9,100]. After sorting, it becomes
  // [0,1,9,16,100].
  //
  //    Example 2:
  //    Input: [-7,-3,2,3,11]
  //    Output: [4,9,9,49,121]
  //    Explanation: After squaring, the array becomes [49,9,4,9,121]. After sorting, it becomes
  // [4,9,9,49,121].
  //
  //    Example 3:
  //    Input: [-5,-4,-2,-1]
  //    Output: [1,4,16,25]
  //    Explanation: After squaring, the array becomes [25,16,4,1]. After sorting, it becomes
  // [1,4,16,25].

  //  Solution: Even though the array is sorted, squaring breaks the order because negative numbers
  // become positive.
  //  The largest square must come from either the leftmost (most negative) or rightmost (most
  // positive) value.
  //  Time: O(n)
  //  Space: O(n) (output array only)
  public int[] sortedSquares1(int[] input) {
    int[] output = new int[input.length];
    int outputIndex = input.length - 1;
    int first = 0;
    int last = input.length - 1;

    for (int i = 0; i < input.length; i++) {
      int firstS = input[first] * input[first];
      int lastS = input[last] * input[last];

      if (firstS > lastS) {
        output[outputIndex] = firstS;
        first++;
      } else {
        output[outputIndex] = lastS;
        last--;
      }
      outputIndex--;
    }

    return output;
  }

  public static void main(String[] args) {
    SortedArray obj = new SortedArray();
    int[] nums = {0, 0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
    int len = obj.removeDuplicatesFromSortedArray(nums);
    System.out.println("New length: " + len);
    System.out.print("Array after removing duplicates: ");
    for (int i = 0; i < len; i++) {
      System.out.print(nums[i] + " ");
    }

    System.out.println("--------");
    int[] ints = obj.sol(new int[] {-4, -1, 0, 3, 10});
    System.out.println(Arrays.toString(ints));
  }

  public int[] sol(int[] input) {
    int left = 0;
    int right = input.length - 1;
    int[] result = new int[input.length];
    int index = input.length - 1;

    while (left < right) {
      int lSquare = input[left] * input[left];
      int rSquare = input[right] * input[right];

      if (lSquare > rSquare) {
        result[index--] = lSquare;
        left++;
      } else {
        result[index--] = rSquare;
        right--;
      }
    }

    return result;
  }
}
