package datastructure_algorithms.patterns.priorityqueue.frequentelements;

//  Given a matrix of integers matrix, return the k most frequent elements. You can return the answer in any order.
//
//  Example 1:
//  Input: [[1, 1, 1], [2, 2, 2], [3, 3, 3]] 2
//  Output: [1, 2]
//  Explanation: The elements 1 and 2 are the most frequent.
//
//  Example 2:
//  Input: [[1, 2, 2], [1, 2, 2], [1, 2, 3]] 1
//  Output: [2]
//  Explanation: The element 2 is the most frequent.

//  Solution: HashMap + Min Heap
//  Time -> O(m*n + u log k)
//  Space -> O(u + k)

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentElementsInASortedMatrix {

  public static int[] topKFrequent(int[][] matrix, int k) {

    // Step 1: Count frequency
    Map<Integer, Integer> freqMap = new HashMap<>();
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        int val = matrix[i][j];
        freqMap.put(val, freqMap.getOrDefault(val, 0) + 1);
      }
    }

    // Step 2: Min heap based on frequency
    PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> freqMap.get(a) - freqMap.get(b));

    for (int num : freqMap.keySet()) {
      minHeap.offer(num);
      if (minHeap.size() > k) {
        minHeap.poll();
      }
    }

    // Step 3: Build result
    int[] result = new int[k];
    int index = 0;
    for (int num : minHeap) {
      result[index++] = num;
    }

    return result;
  }

  // main method with output
  public static void main(String[] args) {

    int[][] matrix = {
        {1, 1, 2},
        {2, 3, 3},
        {3, 4, 4}};

    int k = 2;

    int[] ans = topKFrequent(matrix, k);

    System.out.println("Top K Frequent Elements:");
    for (int x : ans) {
      System.out.print(x + " ");
    }
  }
}
