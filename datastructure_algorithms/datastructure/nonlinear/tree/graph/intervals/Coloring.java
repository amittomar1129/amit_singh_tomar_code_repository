package datastructure_algorithms.datastructure.nonlinear.tree.graph.intervals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Coloring {

  public static int[] colorIntervals(Interval[] intervals) {
    int[] colors = new int[intervals.length];
    // Sort intervals by start time
    Arrays.sort(intervals, Comparator.comparingInt(a -> a.getStart()));
    // Min-heap: [end time, color]
    PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
    int color = 0;

    for (Interval interval : intervals) {
      // Reuse color if possible
      if (!queue.isEmpty() && interval.getStart() >= queue.peek()[0]) {
        int[] node = queue.poll();
        colors[interval.getIndex()] = node[1];
        queue.offer(new int[]{interval.getEnd(), node[1]});
      } else {
        // Assign new color
        colors[interval.getIndex()] = color;
        queue.offer(new int[]{interval.getEnd(), color});
        color++;
      }
    }
    return colors;
  }

  public static void main(String[] args) {
    Interval[] intervals = new Interval[]{
        new Interval(1, 4, 0),
        new Interval(2, 5, 1),
        new Interval(3, 6, 2),
        new Interval(7, 8, 3)
    };

    int[] colors = colorIntervals(intervals);

    System.out.println("Interval -> Color mapping:");
    for (int i = 0; i < intervals.length; i++) {
      System.out.println(
          "[" + intervals[i].getStart() + "," + intervals[i].getEnd() + "] -> Color " + colors[i]);
    }
  }
}
