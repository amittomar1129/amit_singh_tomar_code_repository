package datastructure_algorithms.patterns.arrays.prefixsum;

import java.util.ArrayList;
import java.util.List;

//  Given an array of integers, 1 <= a[i] <= n (n = size of array), some elements appear twice and
// others appear once.
//  Find all the elements that appear twice in this array. Could you do it without extra space and
// in O(n) runtime?
//
//  Example 1:
//  Input: [4,3,2,7,8,2,3,1]
//  Output: [2,3]
//
//  Example 2:
//  Input: [1,1,2]
//  Output: [1]
//
//  Example 3:
//  Input: [1]
//  Output: []

//  Solution: When we see a number x. Go to index x - 1. Flip the sign at that index.
//  If it’s already negative -> we have seen x before -> duplicate.
//    Time	O(n)
//    Space	O(1) (excluding output)

public class FindDuplicates {

  public static List<Integer> findDuplicates(int[] input) {
    List<Integer> result = new ArrayList<>();

    for (int i = 0; i < input.length; i++) {
      int curr = Math.abs(input[i]);
      if (curr < 1 || curr > input.length) {
        continue; // skip invalid values
      }
      int index = curr - 1;
      if (input[index] < 0) {
        result.add(curr);
      } else {
        input[index] = -input[index];
      }
    }

    return result;
  }

  public static void main(String[] args) {
    int[] nums = {4, 3, 2, 7, 8, 2, 1, 3};
    System.out.println(sol(nums));
  }

  public static List<Integer> sol(int[] input) {
    List<Integer> result = new ArrayList<>();

    for (int i = 0; i < input.length; i++) {
      int num = Math.abs(input[i]);

      int position = num - 1;
      if (input[position] < 0) {
        result.add(num);
      } else {
        input[position] = -input[position];
      }
    }

    return result;
  }
}
