package datastructure_algorithms.patterns.priorityqueue.find;

//  The median is the middle value in an ordered integer list. If the size of the list is even, there is no
//  middle value and the median is the mean of the two middle values. Design a data structure that supports
//  the following two operations: void addNum(int num) - Add an integer number from the data stream to the
//  data structure. double findMedian() - Return the median of all elements so far.

//  Solution: Maintain two heaps:
//  Max Heap (left half)
//   stores the smaller half of numbers
//   top = largest of smaller half
//  Min Heap (right half)
//   stores the larger half of numbers
//   top = smallest of larger half
//  Every element in left heap ? every element in right heap
//  Odd  =>	top of maxHeap
//  Even =>	(top of maxHeap + top of minHeap) / 2

//  addNum	O(log n)
//  findMedian	O(1)
//  Space	O(n)

import java.util.PriorityQueue;

public class FindMedianFromDataStream {

  // max heap -> left half
  private PriorityQueue<Integer> maxHeap;

  // min heap -> right half
  private PriorityQueue<Integer> minHeap;

  public FindMedianFromDataStream() {
    maxHeap = new PriorityQueue<>((a, b) -> b - a);
    minHeap = new PriorityQueue<>();
  }

  public void addNum(int num) {

    // step 1 -> add to maxHeap
    maxHeap.offer(num);

    // step 2 -> balance order
    minHeap.offer(maxHeap.poll());

    // step 3 -> balance size
    if (minHeap.size() > maxHeap.size()) {
      maxHeap.offer(minHeap.poll());
    }
  }

  public double findMedian() {

    if (maxHeap.size() > minHeap.size()) {
      return maxHeap.peek();
    }

    return (maxHeap.peek() + minHeap.peek()) / 2.0;
  }

  // main method
  public static void main(String[] args) {

    FindMedianFromDataStream mf = new FindMedianFromDataStream();

    mf.addNum(1);
    System.out.println(mf.findMedian()); // 1.0

    mf.addNum(2);
    System.out.println(mf.findMedian()); // 1.5

    mf.addNum(3);
    System.out.println(mf.findMedian()); // 2.0

    mf.addNum(4);
    System.out.println(mf.findMedian()); // 2.5
  }
}
