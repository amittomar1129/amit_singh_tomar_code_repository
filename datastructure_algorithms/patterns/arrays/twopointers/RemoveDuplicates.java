package datastructure_algorithms.patterns.arrays.twopointers;

//  Given a sorted array nums, remove the duplicates in-place such that each element appears only once and
//  returns the new length. Do not allocate extra space for another array; you must do this by modifying
//  the input array in-place with O(1) extra memory.
//
//  Example 1:
//  Input: [1,1,2]
//  Output: 2
//  Explanation: Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. It doesn't matter what you leave beyond the returned length.
//
//  Example 2:
//  Input: [0,0,1,1,1,2,2,3,3,4]
//  Output: 5
//  Explanation: Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively. It doesn't matter what values are set beyond the returned length.
//
//  Example 3:
//  Input: [1,1,1]
//  Output: 1
//  Explanation: Your function should return length = 1, with the first element of nums being 1. It doesn't matter what you leave beyond the returned length.

//  Solution: Since the array is sorted, duplicates are adjacent. I use a two-pointer approach to overwrite duplicates in-place.
//  Time	O(n)
//  Space	O(1)

public class RemoveDuplicates {

  public static int removeDuplicates(int[] input) {
    if (input.length == 0) {
      return 0;
    }

    int left = 1; // index for placing unique elements

    for (int i = 1; i < input.length; i++) {
      if (input[i] != input[i - 1]) {
        input[left++] = input[i];
      }
    }
    return left; // new length
  }

  public static void main(String[] args) {
    int[] nums = {1, 1, 2, 2, 3, 3, 3, 3};

    int len = sol(nums);

    System.out.println("New length: " + len);
    System.out.print("Array after removing duplicates: ");
    for (int i = 0; i < len; i++) {
      System.out.print(nums[i] + " ");
    }
  }

  public static int sol(int[] input) {
    int left = 1;

    for(int i = 1; i < input.length; i++) {
      if (input[i] != input[i-1]) {
        input[left++] = input[i];
      }
    }

    return left;
  }






















}
