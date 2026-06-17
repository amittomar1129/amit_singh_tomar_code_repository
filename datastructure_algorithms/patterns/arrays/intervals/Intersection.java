package datastructure_algorithms.patterns.arrays.intervals;

//  Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted
// order.
//  Return the intersection of these two interval lists.
//
//  Examples:
//  Example 1:
//  Input: [[0,2],[5,10],[13,23],[24,25]], [[1,5],[8,12],[15,24],[25,26]]
//  Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
//
//  Example 2:
//  Input: [[1,3],[5,9]], [],
//  Output: []
//
//  Example 3:
//  Input: [], [[4,8],[10,12]]
//  Output: []

//  Solution: Pointer Movement Rule (Very Important)
//  After processing: If aEnd < bEnd -> move pointer i, Else -> move pointer j
//  Why? The interval that ends first cannot intersect any future interval.

//  Given:
//  A = [aStart, aEnd]
//  B = [bStart, bEnd]
//  The intersection (if any) is:
//  start = max(aStart, bStart)
//  end   = min(aEnd, bEnd)

//  Step-by-Step Intersections:
//      A	            B	                      Intersection
//    [0,2]	        [1,5]                     	[1,2]
//    [5,10]	      [1,5]                     	[5,5]
//    [5,10]	      [8,12]	                    [8,10]
//    [13,23]	      [15,24]	                    [15,23]
//    [24,25]	      [15,24]	                    [24,24]
//    [24,25]	      [25,26]	                    [25,25]

import java.util.ArrayList;
import java.util.List;

public class Intersection {

  public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
    List<int[]> result = new ArrayList<>();
    int i = 0, j = 0;
    while (i < firstList.length && j < secondList.length) {
      int start = Math.max(firstList[i][0], secondList[j][0]);
      int end = Math.min(firstList[i][1], secondList[j][1]);

      // Check if there is an intersection
      if (start <= end) {
        result.add(new int[] {start, end});
      }

      // Move the pointer with smaller end
      if (firstList[i][1] < secondList[j][1]) {
        i++;
      } else {
        j++;
      }
    }
    return result.toArray(new int[result.size()][]);
  }

  public static void main(String[] args) {
    Intersection intersection = new Intersection();
    int[][] ints =
        intersection.sol(
            new int[][] {{0, 2}, {5, 10}, {13, 23}, {24, 25}},
            new int[][] {{1, 5}, {8, 12}, {15, 24}, {25, 26}});
    for (int[] interval : ints) {
      System.out.println("(" + interval[0] + ", " + interval[1] + ")");
    }
  }

  public int[][] sol(int[][] interval1, int[][] interval2) {
    ArrayList<int[]> result = new ArrayList<>();
    int m = 0;
    int n = 0;

    while (m < interval1.length && n < interval2.length) {
      int start = Math.max(interval1[m][0], interval2[n][0]);
      int end = Math.min(interval1[m][1], interval2[n][1]);

      if (start <= end) {
        result.add(new int[] {start, end});
      }

      if (interval1[m][1] < interval2[n][1]) {
        m++;
      } else {
        n++;
      }
    }

    return result.toArray(new int[result.size()][]);
  }
}
