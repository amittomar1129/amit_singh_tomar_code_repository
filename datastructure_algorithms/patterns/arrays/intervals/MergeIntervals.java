package datastructure_algorithms.patterns.arrays.intervals;

//  Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping
// intervals,
//  and return an array of the non-overlapping intervals that cover all the intervals in the input.

//  Example 1:
//  Input: [[1,3],[2,6],[8,10],[15,18]]
//  Output: [[1,6],[8,10],[15,18]]
//  Explanation: Merge intervals [1,3] and [2,6] into [1,6].
//
//  Example 2:
//  Input: [[1,4],[4,5]]
//  Output: [[1,5]]
//  Explanation: Merge intervals [1,4] and [4,5] into [1,5].
//
//  Example 3:
//  Input: [[1,4],[0,4]]
//  Output: [[0,4]]
//  Explanation: Merge intervals [1,4] and [0,4] into [0,4].

//  Solution: If we sort intervals by start time, then overlapping intervals will be adjacent.
//  We can merge greedily in a single pass.
//  Time: O(n log n) (sorting dominates)
//  Space: O(n) for output (extra space is unavoidable)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {

  public int[][] merge(int[][] intervals) {
    if (intervals.length == 0) {
      return new int[0][0];
    }

    // 1. Sort by start time, {1, 3}, {2, 6}, {8, 10}, {15, 18}
    Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

    List<int[]> result = new ArrayList<>();

    int curStart = intervals[0][0];
    int curEnd = intervals[0][1];

    // 2. Merge intervals
    for (int i = 1; i < intervals.length; i++) {
      int nextStart = intervals[i][0];
      int nextEnd = intervals[i][1];

      if (nextStart <= curEnd) {
        // Overlapping
        curEnd = Math.max(curEnd, nextEnd);
      } else {
        // Non-overlapping
        result.add(new int[] {curStart, curEnd});
        curStart = nextStart;
        curEnd = nextEnd;
      }
    }

    // Add last interval
    result.add(new int[] {curStart, curEnd});
    return result.toArray(new int[result.size()][]);
  }

  //  Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if
  // necessary).
  //  You may assume that the intervals were initially sorted according to their start times.
  //    Example 1:
  //    Input: [[1,3],[6,9]], [2,5]
  //    Output: [[1,5],[6,9]]
  //
  //    Example 2:
  //    Input: [[1,2],[3,5],[6,7],[8,10],[12,16]], [4,8]
  //    Output: [[1,2],[3,10],[12,16]]
  //
  //    Example 3:
  //    Input: [[1,5]], [2,3]
  //    Output: [[1,5]]
  //  Time: O(n)
  //  Space: O(n)

  public int[][] insert(int[][] intervals, int[] newInterval) {
    List<int[]> result = new ArrayList<>();
    int i = 0;

    //              {1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}    new: {4, 8}
    // 1. Add all intervals that come before newInterval
    while (i < intervals.length &&  intervals[i][1] < newInterval[0]) {
      result.add(intervals[i]);
      i++;
    }

    // 2. Merge overlapping intervals with newInterval
    while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
      newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
      newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
      i++;
    }

    // Add merged newInterval
    result.add(newInterval);

    // 3. Add remaining intervals
    while (i < intervals.length) {
      result.add(intervals[i]);
      i++;
    }

    return result.toArray(new int[result.size()][]);
  }

  public static void main(String[] args) {
    MergeIntervals intervals = new MergeIntervals();
    int[][] merge = intervals.merge(new int[][] {{1, 3}, {2, 6}, {8, 10}, {15, 18}});
    for (int[] interval : merge) {
      System.out.println("(" + interval[0] + ", " + interval[1] + ")");
    }

    int[][] insert =
        intervals.sol(new int[][] {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[] {4, 8});
    System.out.println("---------------");
    for (int[] interval : insert) {
      System.out.println("(" + interval[0] + ", " + interval[1] + ")");
    }
  }
  public int[][] sol(int[][] intervals, int[] target) {
    Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
    List<int[]> result = new ArrayList<>();
    int index = 0;
    while(intervals[index][1] < target[0]) {
      result.add(new int[]{intervals[index][0], intervals[index][1]});
      index++;
    }
    int start = intervals[index][0];
    int end = intervals[index][1];
    while(target[0] < intervals[index][1] && intervals[index][0] <= target[1]) {
      end = intervals[index][1];
      index++;
    }
    result.add(new int[]{start, end});

    while (index < intervals.length) {
      result.add(new int[]{intervals[index][0], intervals[index][1]});
      index++;
    }

    return result.toArray(new int[result.size()][]);
  }

}
