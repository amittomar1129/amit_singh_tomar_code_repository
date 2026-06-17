package datastructure_algorithms.patterns.arrays.sorting;

//  Given an array nums with n objects colored red, white, or blue, sort them in-place so that
// objects
//  of the same color are adjacent, with the colors in the order red, white, and blue. Here, we will
// use
//  the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

//  Example 1:
//  Input: [2,0,2,1,1,0]
//  Output: [0,0,1,1,2,2]
//
//  Example 2:
//  Input: [2,0,1]
//  Output: [0,1,2]
//
//  Example 3:
//  Input: [0]
//  Output: [0]

//  Solution: Since there are only three distinct values (0,1,2), we can partition the array in one
// pass
//  using three pointers instead of sorting.
//  “I maintain three partitions for 0s, 1s, and 2s.
//  I iterate once, swapping elements into their correct region while maintaining invariants.”
//  This shows invariant-based reasoning.
//  Time: O(n) (single pass)
//  Space: O(1) (in-place)

import java.util.Arrays;

public class SortColors {

  //  [2,0,2,1,1,0]
  public void sortColors(int[] input) {
    int left = 0;
    int mid = 0;
    int right = input.length - 1;

    while (mid <= right) {
      if (input[mid] == 0) {
        swap(input, left, mid);
        left++;
        mid++;
      } else if (input[mid] == 1) {
        mid++;
      } else {
        swap(input, mid, right);
        right--;
      }
    }
  }

  private void swap(int[] input, int i, int j) {
    int temp = input[i];
    input[i] = input[j];
    input[j] = temp;
  }

  public static void main(String[] args) {
    SortColors colors = new SortColors();
    int[] ints = {2, 0, 2, 1, 0, 2, 1, 0, 0, 0, 0, 1, 1, 1, 2, 2, 1, 2, 1, 1, 0, 0, 2, 1, 1, 0};
    colors.sol(ints);
    Arrays.stream(ints).forEach(a -> System.out.print(a + ", "));
  }

  public int[] sol(int[] input) {
    int left = 0;
    int mid = 0;
    int right = input.length - 1;

    while (mid <= right) {
      if (input[mid] == 0) {
        swap(input, mid, left);
        mid++;
        left++;
      } else if(input[mid] == 1){
        mid++;
      } else {
        swap(input, mid, right);
        right--;
      }
    }

    return input;
  }
}
