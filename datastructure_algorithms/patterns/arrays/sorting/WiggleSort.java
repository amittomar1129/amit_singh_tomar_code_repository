package datastructure_algorithms.patterns.arrays.sorting;

//  Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
//
//  Example 1:
//  Input: [1, 5, 1, 1, 6, 4]
//  Output: [1, 6, 1, 5, 1, 4]
//  Explanation: One possible answer is [1, 6, 1, 5, 1, 4]
//
//  Example 2:
//  Input: [1, 3, 2, 2, 3, 1]
//  Output: [2, 3, 1, 3, 1, 2]
//  Explanation: One possible answer is [2, 3, 1, 3, 1, 2]
//
//  Example 3:
//  Input: [1, 1, 2, 1, 2, 2, 1]
//  Output: [1, 2, 1, 2, 1, 2, 1]
//  Explanation: One possible answer is [1, 2, 1, 2, 1, 2, 1]

//  Solution: Greedy One-Pass
//  Time: O(n)
//  Space: O(1)

import java.util.Arrays;

public class WiggleSort {

  public static void wiggleSort(int[] input) {  // {3, 5, 2, 1, 6, 4}
    for (int i = 1; i < input.length; i++) {
      // odd index -> nums[i] should be greater than nums[i-1]
      if (i % 2 == 1 && input[i] < input[i - 1]) {
        swap(input, i, i - 1);
      }

      // even index -> nums[i] should be smaller than nums[i-1]
      if (i % 2 == 0 && input[i] > input[i - 1]) {
        swap(input, i, i - 1);
      }
    }
  }

  private static void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

// Wiggle Sort with duplicate [1, 3, 5, 1, 1, 6, 4, 9, 4, 7, 6, 1, 8, 2, 1]
// Solution: “Sorting fails with duplicates.
// We place larger elements at odd indices using virtual indexing and perform a 3-way partition around the median.”
// Uses Quickselect + 3-way partition + virtual indexing
//    Time: O(n) average
//    Space: O(1)
//  1.  Median-based partitioning avoids duplicate collisions
//  2.  Virtual indexing forces:
//    larger elements -> odd indices
//    smaller elements -> even indices
//  3.  Dutch National Flag (3-way partition) around median

  public static void wiggleSortWithDuplicate(int[] input) {
    int length = input.length;
    int median = findKth(input, (length + 1) / 2);

    int left = 0, index = 0, right = length - 1; // [ 1, 1, 1, 1, 1, 2, 3, 4, 4, 5, 6, 9, 8, 6, 7 ]

    while (index <= right) {
      int mappedIndex = newIndex(index, length);
      if (input[mappedIndex] > median) {
        swap(input, newIndex(left++, length), mappedIndex);
        index++;
      } else if (input[mappedIndex] < median) {
        swap(input, newIndex(right--, length), mappedIndex);
      } else {
        index++;
      }
    }
  }

  // Virtual index mapping
  private static int newIndex(int index, int n) {
    int i = n | 1;
    return (1 + 2 * index) % (n | 1);
  }

  // QuickSelect to find kth smallest
  private static int findKth(int[] input, int k) {
    int left = 0, right = input.length - 1;
    while (true) {
      int pivot = partition(input, left, right);
      if (pivot == k - 1) {
        return input[pivot];
      }
      if (pivot > k - 1) {
        right = pivot - 1;
      } else {
        left = pivot + 1;
      }
    }
  }

  private static int partition(int[] input, int left, int right) {
    int pivot = input[right];
    int index = left;

    for (int i = left; i < right; i++) {
      if (input[i] <= pivot) {
        swap(input, index++, i);
      }
    }
    swap(input, index, right);

    return index;
  }


  public static void main(String[] args) {
    int[] input = {3, 5, 2, 1, 6, 4};
    wiggleSort(input);
    System.out.println(Arrays.toString(input));

    int[] input1 = {1, 3, 5, 1, 1, 6, 4, 9, 4, 7, 6, 1, 8, 2, 1};
    wiggleSortWithDuplicate(input1);
    System.out.println(Arrays.toString(input1));
  }

  public static int[] sol(int[] input) {

    for(int i = 1; i < input.length; i++) {
      if((i & 1) == 1 && input[i] < input[i-1]) { // odd
          swap(input, i, i-1);
      } else if((i & 1) == 0 && input[i] > input[i-1]){ // even
        swap(input, i, i-1);
      }
    }

    return input;
  }




}
