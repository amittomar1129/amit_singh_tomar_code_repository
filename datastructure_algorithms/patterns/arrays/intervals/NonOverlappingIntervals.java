package datastructure_algorithms.patterns.arrays.intervals;

//  Given a collection of intervals, find the minimum number of intervals you need to remove to make
// the rest of the
//  intervals non-overlapping.

//  Example 1:
//  Input: [[1,2],[2,3],[3,4],[1,3]]
//  Output: 1
//
//  Example 2:
//  Input: [[1,2],[1,2],[1,2]]
//  Output: 2
//
//  Example 3:
//  Input: [[1,2],[2,3]]
//  Output: 0

import java.util.Arrays;

//  Solution: To keep the maximum number of non-overlapping intervals, always pick the interval that
// ends earliest.
//  The minimum removals = total intervals ? max non-overlapping intervals.
//  Time: O(n log n) (sorting)
//  Space: O(1) extra space
public class NonOverlappingIntervals {

  public int nonOverlappingIntervals(int[][] intervals) {
    if (intervals.length == 0) {
      return 0;
    }
    // Sort by end time
    Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1])); // [[1,2],[2,3],[1,3],[3,4]]

    int removals = 0;
    int end = intervals[0][1];

    for (int i = 1; i < intervals.length; i++) {
      if (intervals[i][0] < end) {
        // Overlapping ? remove current interval
        removals++;
      } else {
        // Non-overlapping ? keep it
        end = intervals[i][1];
      }
    }
    return removals;
  }

  public static void main(String[] args) {
    NonOverlappingIntervals intervals = new NonOverlappingIntervals();
    System.out.println(
        intervals.nonOverlappingIntervals(new int[][] {{1, 2}, {2, 3}, {3, 4}, {1, 3}}));
  }

  public int sol(int[][] intervals) {
    Arrays.sort(intervals, (a, b) -> a[1] - b[1]);  // {1, 2}, {2, 3}, {1, 3}, {3, 4}
    int result = 0;

    int start = intervals[0][0];
    int end = intervals[0][1];

    for(int i = 1; i < intervals.length; i++) {
      if (intervals[i][0] < end) {
        result++;
      } else {
        start = intervals[i][0];
        end = intervals[i][1];
      }
    }
    return result;
  }
}
