package datastructure_algorithms.sorting;

//  Quick Sort is a divide-and-conquer, Picks a pivot.
//  Partitions the array so -> elements < pivot go left -> elements > pivot go right.
//  Recursively sorts both sides.

//  [10, 7, 8, 9, 1, 5 ]

//  Space: In-place O(1)
//  Excellent cache performance

//  How to Avoid Worst Case?
//      Randomized pivot
//      Median-of-three pivot

//  Java uses Dual-Pivot QuickSort for primitives because: Fewer comparisons, Better cache locality,
// Faster in practice.

import java.util.Arrays;

public class QuickSort {

  public static void quickSort(int[] input) {
    if (input == null || input.length <= 1) {
      return;
    }
    quickSort(input, 0, input.length - 1);
  }

  private static void quickSort(int[] input, int left, int right) {
    if (left >= right) {
      return;
    }

    int pivotIndex = partition(input, left, right);

    quickSort(input, left, pivotIndex - 1); // left side
    quickSort(input, pivotIndex + 1, right); // right side
  }

  private static int partition(int[] input, int left, int right) {
    int pivot = input[right]; // choosing last element as pivot
    int i = left - 1;

    for (int j = left; j < right; j++) {
      if (input[j] <= pivot) {
        i++;
        swap(input, i, j);
      }
    }

    swap(input, i + 1, right);
    return i + 1; // final pivot position
  }

  private static void swap(int[] input, int i, int j) {
    int temp = input[i];
    input[i] = input[j];
    input[j] = temp;
  }

  // ---------------- MAIN METHOD ----------------
  public static void main(String[] args) {
    int[] arr = {10, 7, 8, 9, 1, 5};

    System.out.println("Before Sorting:");
    System.out.println(Arrays.toString(arr));

    quickSort(arr);

    System.out.println("After Sorting:");
    System.out.println(Arrays.toString(arr));
  }
}
