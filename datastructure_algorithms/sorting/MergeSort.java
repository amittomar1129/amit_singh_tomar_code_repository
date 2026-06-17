package datastructure_algorithms.sorting;

//  Merge Sort is a divide-and-conquer, Recursively divides the array into halves. Sorts each half.
//  Merges the sorted halves in linear time.
//  Divide until single element. Conquer by merging. Combine results.

//  Dry Run:
//                            [5, 2, 4, 6, 1, 3]
//  divide                [5, 2, 4]       [6, 1, 3]
//  divide                [5] [2, 4]      [6] [1, 3]
//  Sort [2, 4 ]          [5] [2, 4]      [6] [1, 3]
//  Merge [5] and [2,4]
//  Compare 5 & 2 -> take 2
//  Compare 5 & 4 -> take 4
//                        [2, 4, 5]       [1, 3, 6]
//  Merge
//      2 vs 1	1
//      2 vs 3	2
//      4 vs 3	3
//      4 vs 6	4
//      5 vs 6	5
//      remaining	6
//  Final Result:        [1, 2, 3, 4, 5, 6]

//  Space:
//  O(n) auxiliary space
//  O(log n) recursion stack

//  Why Java does NOT use MergeSort for primitives?
//  Extra memory overhead -> QuickSort / Dual-Pivot QuickSort faster.

import java.util.Arrays;

public class MergeSort {

  public static void mergeSort(int[] input) {
    if (input == null || input.length <= 1) {
      return;
    }
    mergeSort(input, 0, input.length - 1);
  }

  private static void mergeSort(int[] input, int left, int right) {
    if (left >= right) {
      return;
    }

    int mid = left + (right - left) / 2;

    // Divide
    mergeSort(input, left, mid);
    mergeSort(input, mid + 1, right);

    // Conquer
    merge(input, left, mid, right);
  }

  private static void merge(int[] input, int left, int mid, int right) {
    int[] temp = new int[right - left + 1];

    int i = left; // pointer for left half
    int j = mid + 1; // pointer for right half
    int k = 0; // pointer for temp array

    // Merge both halves
    while (i <= mid && j <= right) {
      if (input[i] <= input[j]) { // <= ensures stability
        temp[k++] = input[i++];
      } else {
        temp[k++] = input[j++];
      }
    }

    // Copy remaining elements
    while (i <= mid) {
      temp[k++] = input[i++];
    }

    while (j <= right) {
      temp[k++] = input[j++];
    }

    // Copy back to original array
    System.arraycopy(temp, 0, input, left, temp.length);
  }

  // ---------------- MAIN METHOD ----------------
  public static void main(String[] args) {

    int[] arr = {5, 2, 4, 6, 1, 3, 9, 7};

    System.out.println("Before Sorting:");
    System.out.println(Arrays.toString(arr));

    mergeSort(arr);

    System.out.println("After Sorting:");
    System.out.println(Arrays.toString(arr));
  }
}
