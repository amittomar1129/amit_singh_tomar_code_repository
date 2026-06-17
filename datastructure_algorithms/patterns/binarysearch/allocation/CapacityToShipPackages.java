package datastructure_algorithms.patterns.binarysearch.allocation;

//  A conveyor belt has packages that must be shipped from one port to another within D days. The
// i-th package
//  on the conveyor belt has a weight of weights[i]. Each day, we load the ship with packages on the
// conveyor belt
//  (in the order given by weights). We may not load more weight than the maximum weight capacity of
// the ship.
//  Return the least weight capacity of the ship that will result in all the packages on the
// conveyor belt being
//  shipped within D days.
//
//  Example 1:
//  Input: [1,2,3,4,5,6,7,8,9,10]
//  Output: 15
//  Explanation: A ship capacity of 5 is the minimum to ship all packages in 5 days like this: 1, 2,
// 3, 4, 5 and
//  the rest of the packages in 4 days.

//  Solution: “I binary search on the ship’s capacity. For each capacity,
//  I greedily simulate shipping to check if packages can be shipped within D days.”
//  Time	O(n log sum(weights))
//  Space	O(1)

public class CapacityToShipPackages {

  public static int shipWithinDays(int[] weights, int D) {
    int left = 0;
    int right = 0;

    for (int w : weights) {
      left = Math.max(left, w); // max weight
      right += w; // total weight
    }

    while (left < right) {
      int mid = left + (right - left) / 2;

      if (canShip(weights, D, mid)) {
        right = mid; // try smaller capacity
      } else {
        left = mid + 1;
      }
    }

    return left;
  }

  private static boolean canShip(int[] weights, int D, int capacity) {
    int days = 1;
    int currentLoad = 0;

    for (int w : weights) {
      currentLoad = currentLoad + w;

      if (currentLoad > capacity) {
        days++;
        currentLoad = w;
      }
      if (days > D) {
        return false;
      }
    }

    return true;
  }

  public static void main(String[] args) {
    int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int D = 5;
    System.out.println(sol(weights, D)); // 15
  }

  public static int sol(int[] input, int days) {
    int left = 0;
    int total = 0;

    for (int weight : input) {
      total += weight;
      left = Math.max(left, weight);
    }

    int right = total;

    while (left < right) {
      int mid = left + (right - left) / 2;
      if (canShipAll(input, days, mid)) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }
    return left;
  }

  //  {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}     {10, 14, 30, 10}
  public static boolean canShipAll(int[] input, int days, int mid) {
    int total = 0;
    int curDays = 1;
    for (int weight : input) {
      total += weight;
      if (total > mid) {
        curDays++;
        total = weight;
      }
    }
    return curDays <= days;
  }
}
