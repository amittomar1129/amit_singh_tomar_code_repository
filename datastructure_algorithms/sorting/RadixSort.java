package datastructure_algorithms.sorting;

//  Radix Sort sorts numbers digit by digit instead of comparing numbers directly.
//  Sort numbers based on least significant digit (LSD) to most significant digit (MSD).
//  Use a stable sort (usually Counting Sort) at each digit.

//  [170, 45, 75, 90, 802, 24, 2, 66], Max = 802 -> 3 digits so 3 passes

//      Pass 1:
//      Number	            Units Digit
//      170	                  0
//      45	                  5
//      75	                  5
//      90                  	0
//      802	                  2
//      24	                  4
//      2	                    2
//      66	                  6
//      Sorted by units:
//      [170, 90, 802, 2, 24, 45, 75, 66]
//
//      Pass 2:
//      Number	            Tens Digit
//      170	                  7
//      90	                  9
//      802	                  0
//      2	                    0
//      24	                  2
//      45	                  4
//      75	                  7
//      66	                  6
//      Sorted:
//      [802, 2, 24, 45, 66, 170, 75, 90]
//
//      Pass 3:
//      Number            	Hundreds Digit
//      802	                  8
//      2	                    0
//      24	                  0
//      45	                  0
//      66	                  0
//      170	                  1
//      75	                  0
//      90	                  0
//      Final Sorted:
//      [2, 24, 45, 66, 75, 90, 170, 802]


//  When Radix Sort is Used:
//  Large number of integers
//  Fixed digit length
//  Linear time sorting

//  Time Complexity: O(d * (n + k))      d -> number of digits, k -> base (10)
//  Space Complexity:    O(n + k)

import java.util.Arrays;

public class RadixSort {

  public static void radixSort(int[] input) {
    int max = getMax(input);

    // Apply counting sort for each digit
    for (int i = 1; max / i > 0; i *= 10) {
      countingSort(input, i);
    }
  }

  private static int getMax(int[] input) {
    int max = input[0];
    for (int num : input) {
      if (num > max) max = num;
    }
    return max;
  }

  private static void countingSort(int[] input, int exp) {
    int[] output = new int[input.length];
    int[] count = new int[10]; // digits 0-9

    // Count occurrences
    for (int num : input) {
      int digit = (num / exp) % 10;
      count[digit]++;
    }

    // Prefix sum
    for (int i = 1; i < 10; i++) {
      count[i] += count[i - 1];
    }

    // Build output (RIGHT to LEFT -> stability)
    for (int i = input.length - 1; i >= 0; i--) {
      int digit = (input[i] / exp) % 10;
      output[count[digit] - 1] = input[i];
      count[digit]--;
    }

    // Copy back
    System.arraycopy(output, 0, input, 0, input.length);
  }

  // ---------------- MAIN METHOD ----------------
  public static void main(String[] args) {
    int[] arr = {170, 45, 75, 90, 802, 24, 2, 66};

    System.out.println("Before Sorting:");
    System.out.println(Arrays.toString(arr));

    radixSort(arr);

    System.out.println("After Sorting:");
    System.out.println(Arrays.toString(arr));
  }
}
