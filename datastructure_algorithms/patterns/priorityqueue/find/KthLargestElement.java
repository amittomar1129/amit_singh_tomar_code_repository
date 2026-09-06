package datastructure_algorithms.patterns.priorityqueue.find;

//  Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order,
//  not the kth distinct element.
//
//  Example 1:
//  Input: [3,2,1,5,6,4] 2
//  Output: 5
//  Explanation: Return the second largest element which is 5.
//
//  Example 2:
//  Input: [3,2,3,1,2,4,5,5,6] 4
//  Output: 4
//  Explanation: Return the fourth largest element which is 4.

//  Solution: Quickselect, Average O(n) time, In-place, Same idea as Quicksort but only explores one side.
//  kth largest -> (n - k)th smallest
//  Average Time -> O(n)
//  Worst Time -> O(n²) (rare, acceptable to mention)
//  Space -> O(1)


import java.util.PriorityQueue;

public class KthLargestElement {

  public static int findKthLargest(int[] nums, int k) {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    for (int num : nums) {
      minHeap.offer(num);

      if (minHeap.size() > k) {
        minHeap.poll();
      }
    }

    return minHeap.peek();
  }

  // main method
  public static void main(String[] args) {
    int[] nums = {3, 2, 1, 5, 6, 4};
    int k = 3;

    System.out.println("Kth largest element -> " + findKthLargest(nums, k));
  }
}
