package datastructure_algorithms.patterns.priorityqueue.find;

//  Given an n x n matrix where each of the rows and columns are sorted in non-decreasing order,
// return the kth smallest
//  element in the matrix. Note that it is the kth smallest element in the sorted order, not the kth
// distinct element.
//
//  Example 1:
//  Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
//  Output: 13
//  Explanation: The 8th smallest element in the matrix is 13.

//  Solution: Each row is sorted. each column is sorted. We can treat the matrix like n sorted
// lists.
//  Push the first element of each row into a min-heap. Each heap entry keeps: value, row index,
// column index
//  Then Pop smallest element k times. Each pop -> push next element from the same row.

//  Time -> O(n log (max - min))
//  Space -> O(1)

import java.util.PriorityQueue;

public class KthSmallestElementInASortedMatrix {

  static class Node {

    int val;
    int row;
    int col;

    Node(int val, int row, int col) {
      this.val = val;
      this.row = row;
      this.col = col;
    }
  }

  public static int kthSmallest(int[][] matrix, int k) {
    PriorityQueue<Node> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

    // push first element of each row
    for (int r = 0; r < matrix.length; r++) {
      minHeap.offer(new Node(matrix[r][0], r, 0));
    }

    int result = 0;

    for (int i = 0; i < k; i++) {
      Node cur = minHeap.poll();
      result = cur.val;

      // push next element from same row
      if (cur.col + 1 < matrix.length) {
        minHeap.offer(new Node(matrix[cur.row][cur.col + 1], cur.row, cur.col + 1));
      }
    }

    return result;
  }

  // main method
  public static void main(String[] args) {
    int[][] matrix = {
        {1, 5, 9},
        {10, 11, 13},
        {12, 13, 15}};

    int k = 8;

    System.out.println("Kth smallest element -> " + kthSmallest(matrix, k));
  }
}
