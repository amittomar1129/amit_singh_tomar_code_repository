package datastructure_algorithms.patterns.arrays.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//  Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i !=
// j, i != k, and j != k,
//  and nums[i] + nums[j] + nums[k] == 0. Notice that the solution set must not contain duplicate
// triplets.

//  Example 1:
//  Input: nums = [-1,0,1,2,4,-1,3,-4]
//  Output: [[-1,-1,2],[-1,0,1]]
//  Explanation: Explanation: The solution set must not contain duplicate triplets.
//
//  Solution: By sorting the array, we can reduce the problem to 2Sum using two pointers, achieving
// O(n²) time,
//  which is optimal for 3Sum.
//  Time: O(n²)
//  Sorting: O(n log n)
//  Two-pointer scan per element: O(n)
//  Space: O(1) (excluding output)

public class ThreeSum {

  //  {-1, 0, 1, 2, 4, 3, -1, -4}
  public List<List<Integer>> threeSum(int[] input) {
    List<List<Integer>> result = new ArrayList<>();
    if (input == null || input.length < 3) {
      return result;
    }
    Arrays.sort(input); // [-4,-1,-1,0,1,2,3,4]
    int length = input.length;
    for (int i = 0; i < length - 2; i++) {
      // Skip duplicate first elements
      if (i > 0 && input[i] == input[i - 1]) {
        continue;
      }
      // Optimization: since array is sorted
      if (input[i] > 0) {
        break;
      }

      int left = i + 1;
      int right = length - 1;

      while (left < right) {
        int sum = input[i] + input[left] + input[right];

        if (sum == 0) {
          result.add(Arrays.asList(input[i], input[left], input[right]));

          // Skip duplicate left and right values
          while (left < right && input[left] == input[left + 1]) {
            left++;
          }
          while (left < right && input[right] == input[right - 1]) {
            right--;
          }

          left++;
          right--;
        } else if (sum < 0) {
          left++; // need a larger sum
        } else {
          right--; // need a smaller sum
        }
      }
    }
    return result;
  }

//  Given an integer array nums of length n and an integer target, find three integers in nums such that
//  the sum is closest to target. Return the sum of the three integers. You may assume that each input
//  would have exactly one solution.

//  Solution: “I sort the array, fix one element, and use two pointers to find the closest pair sum to
//  the remaining target, updating the closest result in O(n²) time.”
//  Time	O(n²)
//  Space	O(1) (excluding sort)

//  {-1, 2, 1, -4}
  public int threeSumClosest(int[] input, int target) {
    if (input == null || input.length < 3) {
      return 0;
    }
    Arrays.sort(input); // [-4,-1,1,2]
    int length = input.length;
    int closestSum = input[0] + input[1] + input[2];

    for (int i = 0; i < length - 2; i++) {
      // Skip duplicate first elements
      if (i > 0 && input[i] == input[i - 1]) {
        continue;
      }
      int left = i + 1;
      int right = length - 1;

      while (left < right) {
        int currentSum = input[i] + input[left] + input[right];
        if (Math.abs(target - closestSum) > Math.abs(target - currentSum)) {
          closestSum = currentSum;
        }
        if (currentSum == target) {
          return target;
        } else if (currentSum < target) {
          left++; // need a larger sum
        } else {
          right--; // need a smaller sum
        }
      }
    }
    return closestSum;
  }

  public static void main(String[] args) {
    ThreeSum threeSum = new ThreeSum();
    List<List<Integer>> lists = threeSum.threeSum(new int[] {-1, 0, 1, 2, 4, 3, -1, -4});
    System.out.println(lists);

    System.out.println(threeSum.threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
  }

  public List<List<Integer>> sol(int[] input) {
    List<List<Integer>> result = new ArrayList<>();
    Arrays.sort(input);

    for(int i = 0; i < input.length - 2; i++) {

      if (i > 0 && input[i] == input[i-1]) {
        continue;
      }

      int left = i + 1;
      int right = input.length - 1;

      while (left < right) {
        int sum = input[i] + input[left] + input[right];
        if (sum == 0) {
          ArrayList<Integer> integers = new ArrayList<>();
          integers.add(input[i]);
          integers.add(input[left++]);
          integers.add(input[right--]);
          result.add(integers);

          while (left < right && input[left] == input[left-1]) {
            left++;
          }
          while (left < right && input[right] == input[right+1]) {
            right--;
          }

        } else if(sum < 0){
          left++;
        } else {
          right--;
        }
      }


    }

    return result;
  }



}

