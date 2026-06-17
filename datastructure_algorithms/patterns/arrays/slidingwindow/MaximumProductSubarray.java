package datastructure_algorithms.patterns.arrays.slidingwindow;

//  Given an integer array nums, find the contiguous subarray within an array (containing at least
// one number)
//  which has the largest product.
//
//  Example 1:
//  Input: [2,3,-2,4]
//  Output: 6
//  Explanation: The contiguous subarray [2,3] has the largest product = 6.
//
//  Example 2:
//  Input: [-2,0,-1]
//  Output: 0
//  Explanation: The contiguous subarray [-2,0] has the largest product = 0. Since the product is 0,
// any subarray would have a product of 0.
//
//  Example 3:
//  Input: [-2]
//  Output: -2
//  Explanation: The result cannot be 0, as the array must contain at least one number.

//  Solution: we must track two values at every index.
//  Time	O(n)
//  Space	O(1)
public class MaximumProductSubarray {

  //  {2, 3, -2, 1, 4, 6}
  public static int maxProduct(int[] input) {
    if (input == null || input.length == 0) {
      return 0;
    }

    int result = input[0]; // store the max product found so far
    int max = input[0]; // max product ending at current index
    int min = input[0]; // min product ending at current index (for negatives)

    for (int i = 1; i < input.length; i++) {
      int current = input[i];
      // If current number is negative, swap max and min
      //      A negative number flips signs
      //      positive × negative -> negative
      //      negative × negative -> positive
      //      So max can become min and min can become max.
      if (current < 0) {
        int temp = max;
        max = min;
        min = temp;
      }
      // Update max/min ending at current index
      max = Math.max(current, current * max);
      min = Math.min(current, current * min);
      // Update result
      result = Math.max(result, max);
    }

    return result;
  }

  public static void main(String[] args) {
    int[] nums = {2, 3, -2, 1, 4, 6};
    System.out.println("Maximum product subarray = " + sol(nums));
  }



  public static int sol(int[] input) {
    int max = input[0];
    int min = input[0];
    int result = 0;

    for(int i = 1; i < input.length; i++) {

      if (input[i] < 0) {
        int temp = max;
        max = min;
        min = temp;
      }

      max = Math.max(input[i], max * input[i]);
      min = Math.min(input[i], min * input[i]);
      result = Math.max(result, max);
    }
    return result;
  }













}
