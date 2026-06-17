package datastructure_algorithms.patterns.arrays.sorting;

//  Given a list of non-negative integers, arrange them such that they form the largest number.
//
//  Example 1:
//  Input: [10,2]
//  Output: 210
//  Explanation: The output is the largest number that can be formed is 210.
//
//  Example 2:
//  Input: [3,30,34,5,9]
//  Output: 9534330
//  Explanation: The output is the largest number that can be formed is 9534330.
//
//  Example 3:
//  Input: [1]
//  Output: 1
//  Explanation: The output is the largest number that can be formed is 1.

//  Solution: “This is a custom sorting problem where the comparison is based on concatenated
// strings.
//  For any two numbers, I decide their order by comparing ab and ba.
//  Sorting by this rule guarantees the maximum possible number.” This shows comparator-based greedy
// reasoning.

//  Time: O(n log n * k),           k = average number of digits
//  Space: O(n)

import java.util.Arrays;

public class LargestNumber {

  //    {3, 30, 34, 5, 9}
  public static String largestNumber(int[] input) {
    // Convert ints to strings
    String[] array = new String[input.length];
    for (int i = 0; i < input.length; i++) {
      array[i] = String.valueOf(input[i]);
    }
    // Custom sort
    Arrays.sort(array, (a, b) -> (b + a).compareTo(a + b));

    // Edge case: all zeros
    if (array[0].equals("0")) {
      return "0";
    }

    // Build result
    StringBuilder sb = new StringBuilder();
    for (String s : array) {
      sb.append(s);
    }

    return sb.toString();
  }

  public static void main(String[] args) {
    int[] nums = {3, 30, 34, 5, 9};
    String result = sol(nums);
    System.out.println("Largest Number: " + result);
  }

  public static String sol(int[] input) {
    String[] nums = new String[input.length];
    for (int i = 0; i < input.length; i++) {
      nums[i] = String.valueOf(input[i]);
    }

    Arrays.sort(nums, (a, b) -> (b + a).compareTo(a + b));


    return Arrays.stream(nums).reduce((a, b) -> a + b).get();
  }
}
