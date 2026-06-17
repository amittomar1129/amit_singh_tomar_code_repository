package datastructure_algorithms.patterns.priorityqueue.merge;

//  You have k lists of sorted integers in non-decreasing order. Find the smallest range that includes
//  at least one number from each of the k lists. We define the range [a, b] is smaller than
//  range [c, d] if b - a < d - c or a < c if b - a == d - c.

//  You have k sorted lists.
//  You must pick one number from each list such that:
//  The range [min, max] covering those numbers is as small as possible
//  If multiple ranges have same size, pick the one with smaller start

//  Example 1:
//  Input: [[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]
//  Output: [20,24]
//  Explanation: List 1: [20,24] contains 24 from list 1, 20 from list 2, and 22 from list 3.
//
//  Example 2:
//  Input: [[1,2,3],[1,2,3],[1,2,3]]
//  Output: [1,1]
//  Explanation: The range [1,1] contains 1 from each list.

//  Solution: I maintain one element from each list using a min-heap.
//  The heap gives me the minimum, and I track the maximum separately.
//  This guarantees the range always includes all k lists.
//  I shrink the range greedily by advancing the list contributing the minimum.
//  Time	O(N log k) (N = total elements)
//  Space	O(k)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class SmallestRange {

  static class Node {

    int value;
    int listIndex;
    int elementIndex;

    Node(int value, int listIndex, int elementIndex) {
      this.value = value;
      this.listIndex = listIndex;
      this.elementIndex = elementIndex;
    }
  }

  public static int[] smallestRange(List<List<Integer>> nums) {
    PriorityQueue<Node> minHeap = new PriorityQueue<>((a, b) -> a.value - b.value);

    int max = Integer.MIN_VALUE;

    // initialize heap
    for (int i = 0; i < nums.size(); i++) {
      int val = nums.get(i).get(0);
      minHeap.offer(new Node(val, i, 0));
      max = Math.max(max, val);
    }

    int rangeStart = 0, rangeEnd = Integer.MAX_VALUE;

    while (minHeap.size() == nums.size()) {

      Node minNode = minHeap.poll();
      int min = minNode.value;

      // update best range
      if (max - min < rangeEnd - rangeStart || (max - min == rangeEnd - rangeStart
          && min < rangeStart)) {
        rangeStart = min;
        rangeEnd = max;
      }

      // move forward in same list
      int nextIndex = minNode.elementIndex + 1;
      if (nextIndex < nums.get(minNode.listIndex).size()) {
        int nextVal = nums.get(minNode.listIndex).get(nextIndex);
        minHeap.offer(new Node(nextVal, minNode.listIndex, nextIndex));
        max = Math.max(max, nextVal);
      }
    }

    return new int[]{rangeStart, rangeEnd};
  }

  public static void main(String[] args) {
    List<List<Integer>> nums = new ArrayList<>();
    nums.add(Arrays.asList(4, 10, 15, 24, 26));
    nums.add(Arrays.asList(0, 9, 12, 20));
    nums.add(Arrays.asList(5, 18, 22, 30));

    int[] result = smallestRange(nums);
    System.out.println("Smallest Range -> [" + result[0] + ", " + result[1] + "]");
  }
}
