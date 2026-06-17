package datastructure_algorithms.sorting;

//  Bubble Sort is a simple comparison-based sorting algorithm where Adjacent elements are
// repeatedly compared and swapped if they are in the wrong order.
// Larger elements “bubble up” to the end of the array.

//  Core Intuition (Say this in interview)
//  After 1st pass -> largest element is at the end
//  After 2nd pass -> 2nd largest at correct position
//  After n - 1 passes -> array is sorted

import java.sql.Array;
import java.util.Arrays;

public class BubbleSort {

  public static void bubbleSort(int[] input) {
    boolean swapped;

    for (int i = 0; i < input.length - 1; i++) {
      swapped = false;
      for (int j = 0; j < input.length - i - 1; j++) {
        if (input[j] > input[j + 1]) {
          // swap
          int temp = input[j];
          input[j] = input[j + 1];
          input[j + 1] = temp;
          swapped = true;
        }
      }

      // Optimization: stop if no swaps
      if (!swapped) {
        break;
      }
    }
  }

  public static void main(String[] args) {
    int[] arr = {5, 1, 4, 2, 8};

    bubbleSort(arr);

    System.out.println(Arrays.toString(arr));
  }
}
