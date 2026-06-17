package datastructure_algorithms.patterns.dynamicprogrammimg.basic;

//  You are a professional robber planning to rob houses along a street. Each house has a certain
// amount of money stashed.
//  All houses at this place are arranged in a circle. That means the first house is the neighbor of
// the last one.
//  Meanwhile, adjacent houses have a security system connected, and it will automatically contact
// the police
//  if two adjacent houses were broken into on the same night. Given a list of non-negative integers
// nums
//  representing the amount of money of each house, return the maximum amount of money you can rob
// tonight without alerting the police.
//
//  Example 1:
//  Input: [2,3,2]
//  Output: 3
//  Explanation: Rob house 1 (money = 2) and then rob house 3 (money = 2). Total amount you can rob
// = 2 + 1 = 3.
//
//  Example 2:
//  Input: [1,2,3,1]
//  Output: 4
//  Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3). Total amount you can rob
// = 1 + 3 = 4.

//  Solution: You cannot rob two adjacent houses. In a circle, you cannot take both:
//    the first house
//    and the last house

//    So split the problem into two linear DP problems:
//    Case 1: Rob houses from index 0 to n-2 (exclude last)
//    Case 2: Rob houses from index 1 to n-1 (exclude first)
//    Final answer = max(case1, case2)
//    This converts the problem into House Robber I (linear) twice.

//   dp[i] = max(dp[i-1], dp[i-2] + nums[i])
//   dp[i] = maximum money you can rob from houses 0 to i (inclusive) without robbing two adjacent
// houses
//  Time	O(n)
//  Space	O(1) ?

public class HouseRobber {

  // Helper method: standard House Robber (linear) using optimized DP
  private static int robLinear(int[] nums, int start, int end) {
    int prev1 = 0; // dp[i-1]
    int prev2 = 0; // dp[i-2]

    for (int i = start; i < end; i++) {
      int curr = Math.max(prev1, prev2 + nums[i]);
      prev2 = prev1;
      prev1 = curr;
    }

    return prev1;
  }

  //  {1, 2, 3, 1, 5, 2, 7, 1, 5}
  public static int rob(int[] nums) {
    // Edge case
    if (nums.length == 1) {
      return nums[0];
    }

    // Case 1: exclude last house
    int case1 = robLinear(nums, 0, nums.length - 1);

    // Case 2: exclude first house
    int case2 = robLinear(nums, 1, nums.length);

    return Math.max(case1, case2);
  }

  public static void main(String[] args) {

    int[] nums1 = {2, 3, 2};
    System.out.println("Max money robbed -> " + sol(nums1)); // 3

    int[] nums2 = {1, 2, 3, 1, 5, 2, 7, 1, 5};
    System.out.println("Max money robbed -> " + sol(nums2)); // 20

    int[] nums3 = {1, 2, 3};
    System.out.println("Max money robbed -> " + sol(nums3)); // 3

    int[] nums4 = {5};
    System.out.println("Max money robbed -> " + sol(nums4)); // 5
  }

  public static int sol(int[] input) {
    return Math.max(countMax(input, 0, input.length - 1), countMax(input, 1, input.length));
  }

  public static int countMax(int[] input, int start, int end) {
    if (input.length == 1) {
      return input[0];
    }

    int prev1 = 0;
    int prev2 = 0;

    for (int i = start; i < end; i++) {
      int curr = Math.max(prev1, prev2 + input[i]);
      prev2 = prev1;
      prev1 = curr;
    }

    return prev1;
  }
}
