package datastructure_algorithms.patterns.priorityqueue.find;

//  Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order,
//  not the kth distinct element.


//  Solution: I maintain a min heap of size k.
//The heap stores the k largest elements seen so far.
//The root of the heap is always the kth largest element.
//Each insertion costs log k, which is optimal for a stream.
//  add	O(log k)
//  get kth largest	O(1)
//  Space	O(k)

import java.util.PriorityQueue;

public class KthLargestElementInAStream {

  private PriorityQueue<Integer> minHeap;
  private int k;

  public KthLargestElementInAStream(int k, int[] nums) {
    this.k = k;
    minHeap = new PriorityQueue<>();

    for (int num : nums) {
      add(num);
    }
  }

  public int add(int val) {
    minHeap.offer(val);

    if (minHeap.size() > k) {
      minHeap.poll();
    }

    return minHeap.peek();
  }

  // main method with output
  public static void main(String[] args) {

    int[] nums = {4, 5, 8, 2};
    KthLargestElementInAStream kthLargest = new KthLargestElementInAStream(3, nums);

    System.out.println(kthLargest.add(3));   // -> 4
    System.out.println(kthLargest.add(5));   // -> 5
    System.out.println(kthLargest.add(10));  // -> 5
    System.out.println(kthLargest.add(9));   // -> 8
    System.out.println(kthLargest.add(4));   // -> 8
  }
}
