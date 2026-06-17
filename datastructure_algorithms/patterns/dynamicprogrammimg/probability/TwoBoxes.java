package datastructure_algorithms.patterns.dynamicprogrammimg.probability;

//  You are given an array of integers representing the weights of items and two boxes that can each hold
//  a maximum weight capacity. Your task is to determine if it is possible to place all items into the two boxes
//  without exceeding the capacity of either box. Return true if it is possible to distribute all items into the
//  two boxes without exceeding their capacities, otherwise return false.

//  You are given:
//  weights[] -> array of item weights
//  Two boxes with capacities:
//  cap1
//  cap2
//  Rules:
//  Each item must go into exactly one box
//  Total weight in each box must be <= its capacity
//  Goal:
//  Return true if all items can be placed
//  Otherwise return false

//  Example 1:
//  Input: weights = [1,2,3,4], boxCapacity = 5
//  Output: true
//  Explanation: One possible distribution is: Box 1 contains items with weights [1,4], Box 2 contains
//  items with weights [2,3]. Both boxes have total weight 5 which does not exceed the capacity.
//
//  Example 2:
//  Input: weights = [2,2,2,2], boxCapacity = 3
//  Output: false
//  Explanation: No matter how the items are distributed, at least one box will exceed the capacity of 3.


//Time Complexity -> O(n * cap1)
//Space Complexity -> O(cap1)

public class TwoBoxes {

  public static boolean canDistribute(int[] weights, int cap1, int cap2) {
    int total = 0;
    for (int w : weights) {
      total += w;
    }

    // Necessary condition
    if (total > cap1 + cap2) {
      return false;
    }

    boolean[] dp = new boolean[cap1 + 1];
    dp[0] = true;

    for (int w : weights) {
      for (int i = cap1; i >= w; i--) {
        dp[i] = dp[i] || dp[i - w];
      }
    }

    for (int i = 0; i <= cap1; i++) {
      if (dp[i] && total - i <= cap2) {
        return true;
      }
    }

    return false;
  }

  public static void main(String[] args) {
    int[] weights1 = {2, 3, 4};
    System.out.println(canDistribute(weights1, 5, 5)); // true

    int[] weights2 = {4, 5, 6};
    System.out.println(canDistribute(weights2, 5, 5)); // false
  }
}
