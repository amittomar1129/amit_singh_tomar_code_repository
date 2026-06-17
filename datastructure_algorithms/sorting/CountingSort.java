package datastructure_algorithms.sorting;

//  Counting Sort is a non-comparison based sorting algorithm that sorts elements by counting how
//  many times each value occurs. It works when values are in a known small range.

//  [4, 2, 2, 8, 3, 3, 1]
//  Step 1: Find max, max = 8
//  Step 2: Count frequencies, Index = value
//      Value	          Count
//      1	                1
//      2	                2
//      3	                2
//      4	                1
//      8	                1
//      Count array:    [0,1,2,2,1,0,0,0,1]
//  Step 3: Prefix sum  [0,1,3,5,6,6,6,6,7]
//  Meaning: Each value now means, count[x] = number of elements <= x
//  Examples:
//      count[1] = 1 -> 1 element  <= 1
//      count[2] = 3 -> 3 elements <= 2
//      count[3] = 5 -> 5 elements <= 3
//      count[4] = 6 -> 6 elements <= 4

//  Step 4: Build output (RIGHT -> LEFT)
//      i	         arr[i]        	position                      	output
//      6	          1	              0	                          [1,,,,,,]
//      5	          3	              4	                          [1,,,,3,,_]
//      4	          3	              3	                          [1,,,3,3,,]
//      3	          8	              6	                          [1,,,3,3,_,8]
//      2	          2	              2	                          [1,,2,3,3,,8]
//      1	          2	              1	                          [1,2,2,3,3,_,8]
//      0	          4	              5	                          [1,2,2,3,3,4,8]
//      Final Sorted Array:   [1, 2, 2, 3, 3, 4, 8]

//  When to Use Counting Sort:
//  Small range of integers
//  Huge dataset
//  Need linear time

//  Time Complexity: O(n + k)
//      n -> number of elements
//      k -> range of values
//  Space Complexity: O(n + k)

import java.util.Arrays;

public class CountingSort {

  public static void countingSort(int[] input) {
    // Step 1: Find max element
    int max = input[0];
    for (int num : input) {
      if (num > max) {
        max = num;
      }
    }

    // Step 2: Create count array
    int[] count = new int[max + 1];

    // Step 3: Count frequency
    for (int num : input) {
      count[num]++;
    }

    // Step 4: Prefix sum (positions)
    for (int i = 1; i < count.length; i++) {
      count[i] += count[i - 1];
    }

    // Step 5: Build output array (RIGHT -> LEFT for stability)
    int[] output = new int[input.length];
    for (int i = input.length - 1; i >= 0; i--) {
      output[count[input[i]] - 1] = input[i];
      count[input[i]]--;
    }

    // Step 6: Copy back
    System.arraycopy(output, 0, input, 0, input.length);
  }

  // ---------------- MAIN METHOD ----------------
  public static void main(String[] args) {
    int[] arr = {4, 2, 2, 8, 3, 3, 1};

    System.out.println("Before Sorting:");
    System.out.println(Arrays.toString(arr));

    countingSort(arr);

    System.out.println("After Sorting:");
    System.out.println(Arrays.toString(arr));
  }
}
