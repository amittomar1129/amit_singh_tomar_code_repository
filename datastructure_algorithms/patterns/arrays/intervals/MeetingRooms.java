package datastructure_algorithms.patterns.arrays.intervals;

//  Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...]
// (s < e),
//  find the minimum number of conference rooms required.

//  Example 1:
//  Input: [[0, 30],[5, 10],[15, 20]]
//  Output: 2
//  Explanation: A room is needed for [0, 30] and another room for [5, 10], [15, 20].
//
//  Example 2:
//  Input: [[7,10],[2,4]]
//  Output: 1
//  Explanation: Only one room is needed as [7,10] overlaps with [2,4].
//
//  Example 3:
//  Input: [[0, 5],[2, 6],[3, 7],[4, 8]]
//  Output: 4
//  Explanation: At one point, all four meetings overlap (between time 4 and 5). Therefore, four
// rooms are required to accommodate them.

//  Solution: The minimum number of rooms required equals the maximum number of overlapping meetings
//  at any time. So the problem reduces to tracking concurrent intervals.
//  Time: O(n log n)
//  Space: O(n) (heap)

import java.util.Arrays;
import java.util.PriorityQueue;

public class MeetingRooms {

  //    [[0, 30],[5, 10],[15, 20]]
  public int minMeetingRooms(int[][] intervals) {
    if (intervals.length == 0) return 0;

    Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
    // Min-heap of meeting end times
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    // Add end time of first meeting
    minHeap.offer(intervals[0][1]);

    for (int i = 1; i < intervals.length; i++) {
      // If earliest ending meeting is done, reuse the room
      if (intervals[i][0] >= minHeap.peek()) {
        minHeap.poll();
      }
      // Allocate room (new or reused)
      minHeap.offer(intervals[i][1]);
    }

    return minHeap.size();
  }

  public static void main(String[] args) {
    int[][] intervals = {
      {0, 30},
      {5, 10},
      {15, 20}
    };
    MeetingRooms meetingRooms = new MeetingRooms();
    int result = meetingRooms.minMeetingRooms(intervals);
    System.out.println("Minimum number of conference rooms required: " + result);
  }
}
