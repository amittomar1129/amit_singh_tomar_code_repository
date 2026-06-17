package datastructure_algorithms.patterns.priorityqueue.find;

//  We have a list of points on the plane. Find the K closest points to the origin (0, 0).
//
//  Example 1:
//  Input: [[1,3],[-2,2]]
//  Output: [[-2,2]]
//  Explanation: The distance between (1, 3) and the origin is sqrt(10). The distance between (-2, 2) and
//  the origin is sqrt(8). Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
//
//  Example 2:
//  Input: [[3,3],[5,-1],[-2,4]]
//  Output: [[3,3],[-2,4]]
//  Explanation: The distances are sqrt(18), sqrt(26), and sqrt(20) respectively. Out of these, sqrt(18) < sqrt(20) < sqrt(26),
//  so the K = 2 closest points are [3,3] and [-2,4].

//  You need to find K points whose distance from the origin (0, 0) is smallest.
//  distance² = x² + y²

//  Solution: Use a max heap (largest distance at top)
//  Add points one by one
//  If heap size exceeds K, remove the farthest point
//  At the end, heap contains K closest points
//  Time	O(n log k)
//  Space	O(k)


import java.util.PriorityQueue;

public class KClosestPointsToOrigin {

  public static int[][] kClosest(int[][] points, int k) {

    // max heap based on distance
    PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
        (a, b) -> (b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1])
    );

    for (int[] point : points) {
      maxHeap.offer(point);

      if (maxHeap.size() > k) {
        maxHeap.poll(); // remove farthest
      }
    }

    int[][] result = new int[k][2];
    int i = 0;

    for (int[] p : maxHeap) {
      result[i++] = p;
    }

    return result;
  }

  // main method
  public static void main(String[] args) {

    int[][] points = {{1, 3}, {-2, 2}, {5, 8}, {0, 1}};
    int k = 2;

    int[][] ans = kClosest(points, k);

    System.out.println("K Closest Points:");
    for (int[] p : ans) {
      System.out.println(p[0] + " -> " + p[1]);
    }
  }
}
