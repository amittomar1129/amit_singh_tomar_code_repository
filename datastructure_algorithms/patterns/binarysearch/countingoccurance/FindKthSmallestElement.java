package datastructure_algorithms.patterns.binarysearch.countingoccurance;

//  Given an n x n matrix where each of the rows and columns are sorted in ascending order, return
// the kth smallest element
//  in the matrix. Note that it is the kth smallest element in the sorted order, not the kth
// distinct element.
//
//  Example 1:
//  Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
//  Output: 13
//  Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest
// number is 13
//
//  Example 2:
//  Input: matrix = [[-5]], k = 1
//  Output: -5
//  Explanation: The elements in the matrix are [-5], and the 1st smallest number is -5
//
//  Example 3:
//  Input: matrix = [[1,2],[1,3]], k = 2
//  Output: 1
//  Explanation: The elements in the matrix are [1,1,2,3], and the 2nd smallest number is 1

//  Solution:
//  Time	O(n log range)
//  Space	O(1)

public class FindKthSmallestElement {

  public static int kthSmallest(int[][] matrix, int k) {
    int length = matrix.length;

    int left = matrix[0][0];
    int right = matrix[length - 1][length - 1];

    while (left < right) {
      int mid = left + (right - left) / 2;

      int count = countLessEqual(matrix, mid);

      if (count < k) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }

    return left;
  }

  private static int countLessEqual(int[][] matrix, int target) {
    int m = matrix.length;
    int row = 0;
    int col = matrix[0].length - 1;
    int count = 0;

    while (row < m && col >= 0) {
      if (matrix[row][col] <= target) {
        count += col + 1; // all elements left of col are <= mid
        row++; // move down
      } else {
        col--; // move left
      }
    }

    return count;
  }

  public static void main(String[] args) {
    int[][] matrix = {
      {1, 5, 9},
      {10, 11, 13},
      {12, 13, 15}
    };

    int k = 8;
    System.out.println(sol(matrix, k)); // 13
  }

  public static int countLessThanEqualToMid(int[][] input, int mid) {
    int m = input.length;
    int n = input[0].length;

    int row = 0;
    int col = n - 1;
    int count = 0;

    while (row < m && col >= 0) {
      if (input[row][col] > mid) {
        col--;
      } else {
        count += col + 1;
        row++;
      }
    }
    return count;
  }

  public static int sol(int[][] input, int k) {
    int m = input.length;
    int n = input[0].length;

    int left = input[0][0];
    int right = input[m-1][n-1];

    while (left < right) {
      int mid = left + (right - left) / 2;

      if (countLessThanEqualToMid(input, mid) < k) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }
    return left;
  }




















}
